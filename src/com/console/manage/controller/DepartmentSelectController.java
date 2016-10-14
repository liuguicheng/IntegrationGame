
package com.console.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.console.entity.Department;


public class DepartmentSelectController extends AbstractController {

    /**ѡ����Ա��ͼ**/
    private String selectView;

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected final ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = getCustomDataMap(request);
        return new ModelAndView(this.selectView, model);
    }

    /** Ĭ�ϰ�staffs������ѡ�����Ա��Ϣ��������������Ҫ�󣬽����������ظú������д���
     * @param request HttpServletRequest
     * @return ��ѡ�����Ա��ϢMap
     */
    protected Map getCustomDataMap(HttpServletRequest request) {
        //������ʼѡ�����Ա
        Map model = new HashMap();
        String depId = request.getParameter("depId");
        String depName = request.getParameter("depName");
        if (depId != null && depName != null) {
            String[] id = depId.split(",");
            String[] name = depName.split(",");
            if (id.length == name.length) {
                List depList = new ArrayList();
                for (int i = 0; i < id.length; i++) {
                    if (id[i] == null || id[i].trim().length() == 0) {
                        break;
                    }
                    Department dep = new Department();
                    dep.setId(id[i]);
                    dep.setName(name[i]);
                    depList.add(dep);
                }
                model.put("depSelectedList", depList);
            }
        }
        return model;
    }

    /**
     * @param selectStaffView The selectStaffView to set.
     */
    public void setSelectView(String selectStaffView) {
        this.selectView = selectStaffView;
    }


}
