package com.console.manage.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.systemic.gq.entity.Member;

/**
 * @description
 */
public interface IManageDao extends ICommonDao {

	/**
	 * ȡָ�����ű�ŵ�ֱ����������
	 * 
	 * @param depId ���ű��
	 * @return <code>Department</code>ʵ����ɵ�<code>List</code>
	 */
	List selectSubDepartment(String depId);

	/**
	 * ȡ�����е�λ���б�. ��λ��ʶ ������Id �� ����Id
	 * 
	 * @param filterStr �����ֽ��й���ʱ�Ĺ�������
	 * @return <code>Department</code>ʵ����ɵ�<code>List</code>
	 */
	List selectAllUnitDepts(String filterStr);

	/**
	 * ȡ�����в��ŵ��б�
	 * 
	 * @return <code>Department</code>ʵ����ɵ�<code>List</code>
	 */
	List selectAllDepartment();

	/**
	 * ��ȡĳ���ŵ��Ӳ��ŵĿ������
	 * 
	 * @param parentId ����Id
	 * @return �����ż�1
	 */
	Integer selectUsableOrderNumber(String parentId);

	/**
	 * ȡָ�����ŵ�Ա���б�.
	 * 
	 * @param depId ���ű��
	 * @return Staffʵ����ɵ�List
	 */
	List selectDepartmentStaff(String depId);

	/**
	 * ����ģ���������Ż�ȡ����Ӧʹ��Ȩ�޵���Ա�б�
	 * 
	 * @param moduleName ģ����
	 * @param departmentId ����Id
	 * @return List of Staff
	 */
	List selectStaffList(String moduleName, String departmentId);

	/**
	 * ��ȡ�ò�����Ա����������
	 * 
	 * @param departmentId ����Id
	 * @return �����Ա���
	 */
	List selectStaffUsableOrderNumber(String departmentId);

	/**
	 * ���ݲ������ƻ�ȡĳ�������µĲ��Ŷ���
	 * 
	 * @param name ��������
	 * @param parentId ������Id
	 * @return ���Ŷ���
	 */
	Department selectDepartment(String name, String parentId);

	/**
	 * ���ݲ��ŵĶ��պŲ��Ҷ�Ӧ�Ĳ���
	 * 
	 * @param depCode ���պ�
	 * @return ��Ӧ�Ĳ���
	 */
	Department selectDepartmentByCode(String depCode);

	/**
	 * ȡָ�����ź�ָ��ҳ��Ȩ�޵�Ա���б�.
	 * 
	 * @param depId ���ű��
	 * @param operateCode ҳ��Ȩ�ޱ���
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
	 * �ͱ�����ѡ���
	 */
	List selectInsuDepartmentTree();

	/**
	 * ȡָ�����ŵ�Ա��ID�б�
	 * 
	 * @param depId
	 * @return
	 */
	List selectDepartmentStaffId(String depId);

	/**
	 * ��ȡ���и��������Լ��Ĳ����б�
	 * 
	 * @return
	 */
	List selectAllRootDepartment();
	
    /**
     * ��ȡ���в����б���ѯ��
     * 
     * @param info
     * @return
     */
    List<Department> selectAllDepartments(DepartmentQueryInfo info);
	
    /**
     * ��ȡ������Ա�б� ѡ����.
     * 
     * @return Staffʵ����ɵ�List
     */
    List selectAllStaff(StaffQueryInfo info);
    //List selectAllStaff(StaffInfo info);
    Page selectStaffList(StaffTypeQueryInfo info);
    
    /**
     * ����url����Ȩ��
     * 
     * @param urlpath ����·�� 
     * @return Power ��Ӧ��Ȩ�޲˵�
     */
    public Power selectPowerByUrl(String urlpath);
    List selectDepartmentName(String depName);
    
    /**����staffId��ȡ��Ա��Ϣ
     * @param staffId
     * @return
     */
    Member selectMemberByStaffId(String staffId);
}
