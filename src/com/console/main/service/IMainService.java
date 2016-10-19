/**
 * Description:
 * History:  2009-7-22 Create
**/

package com.console.main.service;

import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;

/**
 * @description
 */
public interface IMainService {
    /**
     * ���ݵ�¼����ȡԱ����Ϣ��δ�һ�Ա��״̬��Ч������null.
     * @param loginName ��¼����
     * @return  Staffʵ��
     */
    Staff selectStaff(String loginName);

    /**
     * У���¼���ż����룺ͨ��У�鷵�ض�Ӧ��Staffʵ�������򷵻ؿ�ֵ��NULL��.
     * @param loginName     ��¼����
     * @param password      ����
     * @return  Staffʵ��
     */
    Staff doValidate(String loginName, String password);

    /** ��ȡ��֯�ṹ����
     * @param id
     * @return
     */
    Department selectDepartment(String id);


    /** �޸��û�����
     * @param staff �û�
     * @param newPassword ������
     */
    void updatePassword(Staff staff, String newPassword);
    
  
    
    /**
     * ����/����
     * 
     * @param isLogin Staff ʵ��
     */
    Staff updateIsLogin(Staff staff,String isLogin);
    
    /**
     * ����Ա�����ȡԱ���ĵ�½��ȫ��Ϣ. <b>ע����������������Ч�û�</b>
     * 
     * @param id
     *            Ա�����
     * @return StaffSecurityʵ��
     */
    StaffSecurity loadStaffSecurity(String staffId);
    
    /**
     * ���ݵ�¼����ȡԱ����Ϣ��������Ч״̬��Ա����δ�ҵ�����null.
     * @param loginName ��¼����
     * @return  Staffʵ��
     */
    Staff selectAllStaff(String loginName);
    
    /** ��¼timesֵ
     * @param loginName �û���
     * @param times ��¼����
     */
    public StaffSecurity doTimes(String loginName, int times);
    
    /**  ��¼��¼ʧ�ܵĴ���
     * @param loginName �û���
     * @param count ʧ�ܴ���
     */
    public StaffSecurity doFailiedTimes(String loginName,int count) ;

    /** ���û�����
     * @param loginName �û�
     */
    public Staff doUnlock(Staff staff) ;
    
    public Staff selectStaffById(String id);
    /**
     * �����û�
     * @param userName
     */
	void doFrozen(String userName);
    
	/**
	 * ���½�ɫ
	 */
	void upRole(Staff staff);
   
}
