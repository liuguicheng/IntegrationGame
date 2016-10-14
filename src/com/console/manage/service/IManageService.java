package com.console.manage.service;

import java.util.List;

import org.springline.orm.Page;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.manage.command.DepartmentInfo;
import com.console.manage.command.DepartmentPowerInfo;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.systemic.gq.entity.Member;

/**
 * @description
 */
public interface IManageService {

	/**
	 * ȡ�ò�������ʹ�� <code>DepartmentTree</code> ʵ��.
	 * 
	 * @param rootId
	 *            TODO
	 * @return ������������б�.
	 */
	List selectDepartmentTree(String rootId);

	/**
	 * ȡָ�����ű�ŵ�ֱ����������
	 * 
	 * @param depId
	 *            ���ű��
	 * @return <code>Department</code>ʵ����ɵ�<code>List</code>
	 */
	List selectSubDepartment(String depId);

	/**
	 * ȡ�����е�λ���б�. ��λ��ʶ ������Id �� ����Id
	 * 
	 * @param filterStr
	 *            �����ֽ��й���ʱ�Ĺ�������
	 * @return <code>Department</code>ʵ����ɵ�<code>List</code>
	 */
	List selectAllUnitDepts(String filterStr);

	/**
	 * ȡָ���Ĳ��� <b>ע����������������Ч����</b>
	 * 
	 * @param depId
	 *            ���ű��
	 * @return Departmentʵ��
	 */
	Department selectDepartment(String depId);
	List selectDepartmentName(String depName);

	/**
	 * ��ȡĳ���ŵ��Ӳ��ŵĿ������
	 * 
	 * @param parentId
	 *            ����Id
	 * @return �����ż�1
	 */
	Integer selectUsableOrderNumber(String parentId);

	/**
	 * ���沿��
	 * 
	 * @param info
	 *            ���ű༭��Ϣ
	 */
	void saveDepartment(DepartmentInfo info);

	/**
	 * ���Ӳ��Ž�������
	 * 
	 * @param depId
	 *            �������Ӳ��ű������
	 */
	void doSortDepartment(String[] depId);

	/**
	 * ɾ������
	 * 
	 * @param id
	 *            ����Id
	 */
	void deleteDepartment(String[] id);

	/**
	 * ����λ����Ȩ��
	 * 
	 * @param info
	 *            ��Ȩ��Ϣ
	 */
	void doGrantPowerToDepartment(DepartmentPowerInfo info);

	// ����ΪԱ������

	/**
	 * ����Ա�����ȡԱ����Ϣ. <b>ע����������������Ч�û�</b>
	 * 
	 * @param id
	 *            Ա�����
	 * @return Staffʵ��
	 */
	Staff selectStaff(String id);

	/**
	 * ȡָ�����ŵ�Ա���б�.
	 * 
	 * @param depId
	 *            ���ű��
	 * @return Staffʵ����ɵ�List
	 */
	List selectDepartmentStaff(String depId);

	/**
	 * ȡָ�����ŵ�Ա��ID�б�
	 * 
	 * @param depId
	 * @return
	 */
	List selectDepartmentStaffId(String depId);

	/**
	 * �޸�Ա����Ϣ.
	 * 
	 * @param staffInfo
	 *            Staff ʵ��
	 */
	Staff updateStaff(StaffInfo staffInfo);

	/**
	 * ɾ��Ա��
	 * 
	 * @param staffId
	 *            Ա��Id
	 * @return ��ɾ����Ա������
	 */
	Staff deleteStaff(String staffId);

	Staff loadStaff(String staffId);

	/**
	 * ����ģ���������Ż�ȡ����Ӧʹ��Ȩ�޵���Ա�б�
	 * 
	 * @param moduleName
	 *            ģ����
	 * @param departmentId
	 *            ����Id
	 * @return List of Staff
	 */
	List selectStaffList(String moduleName, String departmentId);

	/**
	 * ��ȡ�ò�����Ա����������
	 * 
	 * @param departmentId
	 *            ����Id
	 * @return ��������
	 */
	Integer selectStaffUsableOrderNumber(String departmentId);

	/**
	 * ��Ա����
	 * 
	 * @param staffId
	 *            ��������ԱId����
	 */
	void doSortStaff(String[] staffId);

	/**
	 * �������� Ϊ�˿����ļ�����Ȩ��
	 */
	List<Department> selectAllDepartment();

	/**
	 * ��ȡ���в����б���ѯ��
	 * 
	 * @param info
	 * @return
	 */
	List<Department> selectAllDepartments(DepartmentQueryInfo info);

	/**
	 * ȡָ�����ź�ָ��ҳ��Ȩ�޵�Ա���б�.
	 * 
	 * @param depId
	 *            ���ű��
	 * @param operateCode
	 *            ҳ��Ȩ�ޱ���
	 * @return Staffʵ����ɵ�List
	 */
	List selectOperateStaff(String depId, String operateCode);

	// /**
	// * ȡָ�����������ڵ��û��嵥
	// * @param workQueue
	// * @return
	// */
	// List<Staff> selectWorkQueueStaff(String workQueue);

	/**
	 * ��ȡ������Ա�б� ѡ����.
	 * 
	 * @return Staffʵ����ɵ�List
	 */
	List selectAllStaff(StaffQueryInfo info);

	/**
	 * �ͱ�����ѡ���
	 */
	List selectInsuDepartmentTree();

	/**
	 * ��ȡ���и��������Լ��Ĳ����б�
	 * 
	 * @return
	 */
	List selectAllRootDepartment();

	// List selectAllStaff(StaffInfo info);
	Page selectStaffList(StaffTypeQueryInfo info);

	/**
	 * ��ȡ������Ч��Ա�б�
	 *
	 * @return Staffʵ����ɵ�List
	 */
	List selectAllVaildStaff();
	
	  /**
     * ����url����Ȩ��
     * 
     * @param urlpath ����·�� 
     * @return Power ��Ӧ��Ȩ�޲˵�
     */
	Power selectPowerByUrl(String urlpath) ;
	 /**����staffId��ȡ��Ա��Ϣ
     * @param staffId
     * @return
     */
    Member selectMemberByStaffId(String staffId);
}
