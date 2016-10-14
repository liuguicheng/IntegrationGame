
package com.console.power.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineCommandHelper;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.power.command.PowerInfo;
import com.console.power.command.PowerQueryInfo;
import com.console.power.service.IPowerService;

/**
 * @description
 */
public class PowerController extends SpringlineMultiActionController {
    /**  */
    private IPowerService powerService;


    /**
     * ��Ȩ����ͼ.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView powerList(HttpServletRequest request, HttpServletResponse response, PowerQueryInfo command) {
        Map model =  new HashMap();  //��ҳ��Ϣ����תʱ
        String tab = request.getParameter("tab");
        // ������ʾ��tabҳ
        if(StringUtils.isBlank(tab)){
            tab = PowerInfo.DIC_MODULE_NAME_WEB;
        }
        model.put("tab", tab);
        model.put("message", request.getParameter("message"));
        return new ModelAndView("manage/powerManage", model);
    }

    
    /**
     * ��Ȩ���б�.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView powerInnerList(HttpServletRequest request, HttpServletResponse response, PowerQueryInfo command) {
        PowerQueryInfo info = (PowerQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        String tab = request.getParameter("tab");
        if (info == null) {
            info = command;
        }
        Map model = new HashMap();
        model.put("message", request.getParameter("message"));
        model.put("command", info);
        info.setModuleName(tab);                                                    //���Զ˻����ֻ��˱��
        model.put("powerList", powerService.selectAllPower(info));
        model.put("token", request.getParameter("token"));
        model.put("tab", tab);
        SpringlineCommandHelper.cacheSpringlineCommand(request, info);
        return new ModelAndView("manage/powerList", model);
    }
    
    /**
     * ɾ��ָ����Ȩ����Ϣ.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView powerDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] powerId = request.getParameterValues("powerId");
        Map model = new HashMap();
        try {
            this.powerService.deletePower(powerId);
            model.put("message", "�ѳɹ�ɾ����ѡ�˵�!");
        } catch (Exception ex) {
            model.put("message", "�˵�����Ȩ����Ӧ��ɫ������ɾ����");
        }
        model.put("token", request.getParameter("token"));
        model.put("tab", request.getParameter("tab"));
        return new ModelAndView(new GBRedirectView("../manage/powerInnerList.do"), model);
    }


    /**
     * @param powerService The powerService to set.
     */
    public void setPowerService(IPowerService powerService) {
        this.powerService = powerService;
    }

}
