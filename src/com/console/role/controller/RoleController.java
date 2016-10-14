package com.console.role.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.console.ConsoleHelper;
import com.console.operate.command.OperateQueryInfo;
import com.console.role.service.IRoleService;


public class RoleController extends MultiActionController {
    /**
     *
     */
    private IRoleService roleService;


    /**
     * �г����н�ɫ�б�.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView roleList(HttpServletRequest request, HttpServletResponse response, HashMap model) {
        if (model == null) {
            model = new HashMap();
        }
        model.put("roleList", this.roleService.selectRoleList());
        return new ModelAndView("manage/roleList", model);
    }

    /**
     * ɾ��ָ���Ľ�ɫ.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView roleDelete(HttpServletRequest request, HttpServletResponse response) {
        String roleId = request.getParameter("roleId");
        HashMap model = new HashMap();
        try {
            this.roleService.deleteRole(roleId);
        } catch (RuntimeException ex) {
            model.put("message", "�ý�ɫ�ѱ���Ȩ������ɾ����");
        }


        return roleList(request, response, model);
    }

    /**
     * ҳ�������Ȩ�б�.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView grantOperateToRole(HttpServletRequest request, HttpServletResponse response, HashMap model) {
    	if (model == null) {
            model = new HashMap();
        }

    	String roleId = request.getParameter("roleId");
    	if(roleId != null && roleId.trim().length() > 0){
    		model.put("roleId", roleId);
    		model.put("grantOperateSet",  this.roleService.selectRole(roleId).getOperates());
    	}

        model.put("roleList", this.roleService.selectRoleList());
    	model.put("operateList", ConsoleHelper.getInstance().getOperateService().selectAllOperate(new OperateQueryInfo()));

        return new ModelAndView("manage/grantOperateToRole", model);
    }

    /**
     * ҳ�������Ȩ.
     * @param request   HttpServletRequestʵ��
     * @param response  HttpServletResponseʵ��
     * @return  ModelAndView
     */
    public ModelAndView grantOperateToRoleSubmit(HttpServletRequest request, HttpServletResponse response) {
    	HashMap model = new HashMap();

    	String roleId = request.getParameter("roleId");
    	String[] operateId = request.getParameterValues("operateId");
    	if(roleId != null && roleId.trim().length() > 0){
    		model.put("roleId", roleId);
    		this.roleService.grantOperateToRole(roleId, operateId);
    	}

    	model.put("message", "��Ȩ�ɹ���");
    	return grantOperateToRole(request,response,model);
    }

    /**
     * @param roleService The roleService to set.
     */
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }



}
