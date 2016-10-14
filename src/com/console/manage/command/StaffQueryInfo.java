package com.console.manage.command;

import org.springline.web.mvc.SpringlineCommand;

public class StaffQueryInfo extends SpringlineCommand {
    /**  */
    private static final long serialVersionUID = 5970311147861687881L;
    /** ����. */
    private String name;
    /** ��������. */
    private String departmentName;

    /** �������ű��� */
    private String depCode;

    /** �˻����� */
    private String staffType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

}
