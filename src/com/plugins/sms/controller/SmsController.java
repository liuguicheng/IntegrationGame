package com.plugins.sms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Staff;
import com.plugins.sms.SmsHelper;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsController extends SpringlineMultiActionController {

    /** ����ע�� */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * ���ű༭����
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsEdit(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        model.put("now", new Date());
        model.put("message", request.getParameter("message"));
        return new ModelAndView(getViewMap().get("smsEdit").toString(), model);
    }

    /**
     * ���Ͷ���
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsSend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String content = request.getParameter("smsContent");
        String[] rNums = request.getParameterValues("rNum");
        String[] rIds = request.getParameterValues("rId");
        String[] rNames = request.getParameterValues("rName");
        // ƴ��
        String reNums = "";
        for (String rNum : rNums) {
            reNums += rNum + ",";
        }
        reNums = reNums.substring(0, reNums.length() - 1);
        String reIds = "";
        for (String rId : rIds) {
            reIds += rId + ",";
        }
        reIds = reIds.substring(0, reIds.length() - 1);
        String reNames = "";
        for (String rName : rNames) {
            reNames += rName + ",";
        }
        reNames = reNames.substring(0, reNames.length() - 1);

        SmsInfo info = new SmsInfo();
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            info.setSenderId(self.getId());
            info.setSenderName(self.getName());
            info.setSmsInfo(content);
            info.setReceiverIds(reIds);
            info.setReceiveNums(reNums);
            info.setReceiverNames(reNames);
            if (SmsHelper.getInstance().sendSms(info)) {
                model.put("message", "�ύ�ɹ���");
            } else {
                model.put("message", "����δ�ɹ������ֶ��ſ��ܷ��ͳɹ������ѯ���ŷ��ͼ�¼�Լ�鷢��ʧ�ܵĶ��ż�¼��");
            }
        } else {
            throw new RuntimeException("���������޷���ȡ��ǰ�û���Ϣ��");
        }

        return new ModelAndView(new GBRedirectView(getViewMap().get("smsSend").toString()), model);
    }

    /**
     * �����ط�
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsResend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String[] ids = request.getParameterValues("id");
        try {
//            if (this.smsService.resendSms(ids)) {
//                model.put("message", "�����ɹ���");
//            } else {
//                model.put("message", "����ʧ�ܣ�");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("message", "����ʧ�ܣ�");
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new GBRedirectView(getViewMap().get("smsResend").toString()), model);
    }

    /**
     * ȫ���ط�����
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsAllResend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // ��ѯ����ʧ�ܶ���
        SmsQueryInfo info = new SmsQueryInfo();
        info.setNotPage(true);
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            if (self.hasOperate(SysSms.OPERATE_CODE_MANAGE)) {
                // �ж���ƽ̨����Ȩ�ޣ����Բ鿴�����˷��͵Ķ���
            } else {
                // û�ж���ƽ̨����Ȩ�ޣ�ֻ�ܲ鿴���˷��͵Ķ���
                info.setSenderId(self.getId());
            }
        }
        // ��ѯ�ύʧ��
        info.setSmsState(SysSms.STATE_CREATE_FAIL);
        Page page = this.smsService.selectSmsPage(info);
        if (page != null && page.getData() != null && page.getData().size() > 0) {
            List<SysSms> list = page.getData();
            String[] rIds = new String[list.size()];
            for (int i = 0; i < rIds.length; i++) {
                rIds[i] = list.get(i).getRecordId();
            }
//            if (this.smsService.resendSms(rIds)) {
//                model.put("message", "�����ɹ���");
//            } else {
//                model.put("message", "����ʧ�ܣ�");
//            }
        } else {
            model.put("message", "�޷���ѯ���ύʧ�ܵĶ������ݣ��޷�ִ��ȫ���ط�������");
        }
        return new ModelAndView(new GBRedirectView(getViewMap().get("smsResend").toString()), model);
    }

    /**
     * ���ŷ��͹�����ͼ
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ModelAndView smsSendManage(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        return new ModelAndView(getViewMap().get("smsManage").toString(), model);
    }

}
