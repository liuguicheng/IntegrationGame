package com.console.operate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineCommandHelper;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.operate.command.OperateQueryInfo;
import com.console.operate.service.IOperateService;

public class OperateController extends SpringlineMultiActionController{

	private IOperateService operateService;

	/**
     * ��ҳ��Ȩ���б�.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView operateList(HttpServletRequest request, HttpServletResponse response, OperateQueryInfo command) {
    	OperateQueryInfo info = (OperateQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        if (info == null) {
            info = command;
        }
        Map model = new HashMap();
        model.put("message", request.getParameter("message"));
        model.put("command", info);
        model.put("operateList", this.operateService.selectAllOperate(info));
        SpringlineCommandHelper.cacheSpringlineCommand(request, info);
        return new ModelAndView("manage/operateList", model);
    }

    /**
     * ɾ��ָ����Ȩ����Ϣ.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView operateDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] operateId = request.getParameterValues("operateId");
        Map model = new HashMap();
        try {
            this.operateService.deleteOperate(operateId);
            model.put("message", "�ѳɹ�ɾ����ѡȨ�޲˵�!");
        } catch (Exception ex) {
            model.put("message", "ҳ��Ȩ�޲˵�����Ȩ����Ӧ��ɫ������ɾ����");
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new GBRedirectView("../manage/operateList.do"), model);
    }

	/**
	 * @param operateService the operateService to set
	 */
	public void setOperateService(IOperateService operateService) {
		this.operateService = operateService;
	}

}
