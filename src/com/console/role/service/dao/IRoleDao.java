package com.console.role.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;


public interface IRoleDao extends ICommonDao {

    /** ȡ��ɫ��Ϣ�б�.
     * @param roleType �����ֽ�ɫ���͹���
     * @return ��ɫ�б�
     */
    List selectRoleList();

    /** ��ȡӵ�иý�ɫ����Ա�б�
     * @param roleId ��ɫId
     * @return ��Ա�б�
     */
    List selectRoleStaffList(String roleId);

    /**
     * ��ȡӵ�иý�ɫ����Ա�б�
     * @param roleName
     * @param dept
     * @return
     */
    public List selectRoleStaff(String roleName, Integer dept);

}
