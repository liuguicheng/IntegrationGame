package com.console.operatelog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.operatelog.service.IOperateLogService;

public class OperateLogController extends SpringlineMultiActionController{

	/**  */
	private IOperateLogService operateLogService;

	/**
	 * @param operateLogService the operateLogService to set
	 */
	public void setOperateLogService(IOperateLogService operateLogService) {
		this.operateLogService = operateLogService;
	}

	 /**
     * ɾ����־.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView operateLogDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] id = request.getParameterValues("id");
        Map model = new HashMap();
        try {
            this.operateLogService.deleteOperateLog(id);
            model.put("message", "ɾ���ɹ�!");
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new GBRedirectView("../oplog/operateLogManage.do"), model);
    }
}
