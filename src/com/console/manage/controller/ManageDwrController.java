
package com.console.manage.controller;

import java.util.List;

import com.console.manage.service.IManageService;


public class ManageDwrController {

    /** manageService */
    private IManageService manageService;
    /**
     * ѡ��ָ�����ŵ�Ա��
     * @param depId ���ű��
     * @return Ա���б�
     */
    public List getDepartmentStaff(String depId) {
        return this.manageService.selectDepartmentStaff(depId);
    }


    /**
     * @return ���� manageService��
     */
    public IManageService getManageService() {
        return manageService;
    }
    /**
     * @param manageService Ҫ���õ� manageService��
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

}
