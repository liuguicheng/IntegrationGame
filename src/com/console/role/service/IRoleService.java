package com.console.role.service;

import java.util.List;

import com.console.entity.Role;
import com.console.role.command.RoleGrantInfo;
import com.console.role.command.RoleInfo;

/**
 * @description
 */
public interface IRoleService {


    /**
     * ������ɫ��Ϣ.
     * @param role  Roleʵ��
     * @return  ������Role
     */
    void saveRole(RoleInfo role);

    /**
     * ɾ����ɫ��Ϣ.
     * @param id    ��ɫ���
     */
    void deleteRole(String id);

    /**
     * ȡָ���Ľ�ɫ��Ϣ.
     * @param id            ��ɫ���
     * @param initialize    �Ƿ��ʼ��������Ȩ�޼�
     * @return  Roleʵ��
     */
    Role selectRole(String id);

    /** ȡ��ɫ��Ϣ�б�.
     * @param roleType �����ֽ�ɫ���͹���
     * @return ��ɫ�б�
     */
    List selectRoleList();

    /** ����ɫѡ����Ա������Ȩ
     * @param info RoleGrantInfo
     */
    void updateRoleGrantInfo(RoleGrantInfo info);

    /** ��ȡӵ�иý�ɫ����Ա�б�
     * @param roleId ��ɫId
     * @return ��Ա�б�
     */
    List selectRoleStaffList(String roleId);

    /**
     * ����ɫ����ҳ�������Ȩ
     * @param roleId
     * @param operateId
     */
    void grantOperateToRole(String roleId,String[] operateId);

    /**
     * ��ȡӵ�иý�ɫ����Ա�б�
     * @param roleName
     * @param dept
     * @return
     */
    public List selectRoleStaff(String roleName, Integer dept);
}
