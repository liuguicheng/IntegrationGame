package com.console.manage.service.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.paternity.PaternityTreeBuilder;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.console.main.service.IMainService;
import com.console.manage.command.DepartmentInfo;
import com.console.manage.command.DepartmentPowerInfo;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.console.manage.service.IManageService;
import com.console.manage.service.dao.IManageDao;
import com.plugins.sn.service.SNHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;

/**
 * @description
 */
public class SpringManageService implements IManageService, ICacheConnector {

	/**
	 * dao, ע��
	 */
	private IManageDao manageDao;


	/**
	 * IOC ������ڷ������ؽӿ�
	 */
	private IMainService mainService;

	/**
	 * Ϊ���������Ĵ��룬�ʲ��ı�ú����ķ���ֵ���Ժ����д���ֱ�ӷ���ITreeNode 
	 *
	 * @see com.console.manage.service.IManageService#selectDepartmentTree(String)
	 */
	public List selectDepartmentTree(String rootId) {
		ITreeNode rootNode = (ITreeNode) CacheHelper.getInstance().getCacheObject(Department.TREE_DIC_IDENTIFICATION);
		if (rootId != null && rootId.trim().length() > 0) {
			List result = new ArrayList();
			result.add(rootNode.find(rootId.toString()));
			return result;
		}
		return rootNode.getChildren();
	}

	/**
	 * @see com.console.manage.service.IManageService#selectSubDepartment(String)
	 */
	public List selectSubDepartment(String depId) {
		return this.manageDao.selectSubDepartment(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectAllRootDepartment()
	 */
	public List selectAllRootDepartment() {
		List aList=this.manageDao.selectAllRootDepartment();
		return aList;
	}

	/**
	 * @see com.console.manage.service.IManageService#selectAllUnitDepts(java.lang.String)
	 */
	public List selectAllUnitDepts(String filterStr) {
		return this.manageDao.selectAllUnitDepts(filterStr);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartment(String)
	 */
	public Department selectDepartment(String depId) {
		Department dept = (Department) this.manageDao.load(Department.class,
			depId);
		this.manageDao.initialize(dept.getPowerSet());
		return dept;
	}

	/**
	 * @see com.console.manage.service.IManageService#doGrantPowerToDepartment(com.console.manage.command.DepartmentPowerInfo)
	 */
	public void doGrantPowerToDepartment(DepartmentPowerInfo info) {
		Department dept = this.selectDepartment(info.getDepId());

		dept.getPowerSet().clear();
		if (info != null && info.getPowerId() != null) {
			for (int i = 0; i < info.getPowerId().length; ++i) {
				dept.getPowerSet().add(
					this.manageDao.load(Power.class, info.getPowerId()[i]));
			}
		}
		this.manageDao.save(dept);

	}

	/**
	 * @see com.console.manage.service.IManageService#selectUsableOrderNumber(String)
	 */
	public Integer selectUsableOrderNumber(String parentId) {
		return this.manageDao.selectUsableOrderNumber(parentId);
	}

	/**
	 * @see com.console.manage.service.IManageService#doSortDepartment(String[])
	 */
	public void doSortDepartment(String[] depId) {
		for (int i = 0; i < depId.length; i++) {
			Department dep = this.selectDepartment(depId[i]);
			dep.setOrderNumber(new Integer(i + 1));
			this.manageDao.save(dep);
		}
		// ����֮�����ֵ�Ҳ������
		CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#saveDepartment(com.console.manage.command.DepartmentInfo)
	 */
	public void saveDepartment(DepartmentInfo info) {
		Department department;
		if (info.getId() == null || info.getId().trim().length() == 0) { // ����
			department = new Department();
			department.setId(SNHelper.getSNService().getSerialNumber(
				Department.class.getName(), "id", false));
			department.setState(Department.NORMAL_STATE);
		} else {
			department = this.selectDepartment(info.getId());
		}
		try {
			BeanUtils.copyProperties(info, department, new String[] { "id" });
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		if (info.getName() != null && info.getParentId() != null) {
			Department dept = this.manageDao.selectDepartment(info.getName(),
				info.getParentId());
			if (dept != null && !dept.getId().equals(info.getId())
					&& !Department.HIDDEN_STATE.equals(dept.getState())) {
				throw new RuntimeException("�������ơ�"
						+ info.getName() + "���ѱ�ʹ�ã������±༭�������ƣ�");
			}
		}
		this.manageDao.save(department);

		if (info.getIsIndependent() != null
				&& info.getIsIndependent().booleanValue()) {
			department.setAncestorDep(department.getId());
		} else {
			if (department.getParentId() != null
					&& department.getParentId() != "") {
				Department parentDep = this.selectDepartment(department
					.getParentId());
				department.setAncestorDep(parentDep.getAncestorDep());
			} else {// ��parentIdΪ�յ�ʱ�򣬼�������������ʱӦ�ðѸ�����id�����ڲ���id����Ϊ�Լ�
				department.setParentId(department.getId());
				department.setAncestorDep(department.getId());
			}
		}
		this.manageDao.save(department);
		// �޸�֮�������ֵ䶼Ҫˢ��
        CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
        CacheHelper.getInstance().dispatchRefreshEvent(Department.SIMPLE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#deleteDepartment(String[])
	 */
	public void deleteDepartment(String[] id) {
		if (id == null) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			Department department = this.selectDepartment(id[i]);
			if (department == null) {
				continue;
			}
			if (this.selectSubDepartment(id[i]).size() > 0) {
				throw new RuntimeException("�в��Ŵ����ڲ��š�"
						+ department.getName() + "��������ɾ����");
			} else if (this.selectDepartmentStaff(id[i]).size() > 0) {
				throw new RuntimeException("��Ա�������ڲ��š�"
						+ department.getName() + "��������ɾ����");
			} else {
				department.setState(Department.HIDDEN_STATE);
				this.manageDao.save(department);
			}
		}
		// �޸�֮�������ֵ䶼Ҫˢ��
        CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
        CacheHelper.getInstance().dispatchRefreshEvent(Department.SIMPLE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectStaff(java.lang.Integer,
	 *      boolean)
	 */
	public Staff selectStaff(String id) {
		Staff staff = (Staff) this.manageDao.load(Staff.class, id);
		// this.manageDao.initialize(staff.getFlowRole());
		this.manageDao.initialize(staff.getSystemRole());
		// this.manageDao.initialize(staff.getWorkQueue());
		return staff;
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartmentStaff(String)
	 */
	public List selectDepartmentStaff(String depId) {
		return this.manageDao.selectDepartmentStaff(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartmentStaffId(java.lang.String)
	 */
	public List selectDepartmentStaffId(String depId) {
		return this.manageDao.selectDepartmentStaffId(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#updateStaff(com.console.manage.command.StaffInfo)
	 */
	public Staff updateStaff(StaffInfo staffInfo) {
		Staff staff = null;
		if (staffInfo.getId() == null || staffInfo.getId().trim().length() == 0) {
			staff = new Staff();
			staff.setId(SNHelper.getSNService().getSerialNumber(
				Staff.class.getName(), "id", false));
			staff.setPassword(staffInfo.getPassword());
			staffInfo.setSysTemplate("1");
			staff.setRegDate(new Date());
			staff.setStaffType(staffInfo.getStaffType());
			staff.setIdNum(staffInfo.getIdNum());
			staff.setInDate(staffInfo.getInDate());
			staff.setRoomNum(staffInfo.getRoomNum());
			
		} else { // �޸�
			staff = this.selectStaff(staffInfo.getId());
			if (staffInfo.getSysTemplate() == null
					|| staffInfo.getSysTemplate().trim().length() <= 0) {
				if (staff.getSysTemplate() != null) {
					staffInfo.setSysTemplate(staff.getSysTemplate());
				} else {
					staffInfo.setSysTemplate("1");
				}
			}
		}
		
		// �жϵ�¼���Ƿ�ʹ��
		Staff tmp = this.mainService.selectStaff(staffInfo.getLoginName());
		if (tmp != null && !tmp.getId().equals(staffInfo.getId())) {
			throw new RuntimeException("��¼����"
					+ staffInfo.getLoginName() + "���ѱ�ʹ�ã�");
		}
		BeanUtils.copyProperties(staffInfo, staff, new String[] { "id",
				"department", "password" });
		// �������룬������
		if (staffInfo.getPassword() != null
				&& staffInfo.getPassword().trim().length() > 0) {
			// if
			// (staff.getPassword().equals(EncryptHelper.md5Encoding(staffInfo.getPassword())))
			// {
			// throw new
			// InvalidDataAccessResourceUsageException("���µ����벻�����ϴ�ʹ�õ�������ͬ��");
			// }
		    //�ж������Ƿ�������������CHANGE_TIME�ֶ�
		    if(!staff.getPassword().equals(EncryptHelper.md5Encoding(staffInfo.getPassword()))){
		        StaffSecurity ss = this.mainService.loadStaffSecurity(staff.getId());
	            ss.setChangeTime(new Date());
	            this.manageDao.update(ss);
             }
            staff.setPassword(EncryptHelper
                .md5Encoding(staffInfo.getPassword()));
		}
		// ���ÿ���
		//staff.setDepartment(this.selectDepartment(staffInfo.getDepartment()));
		// ���ý�ɫ����ѡ��
		if (staffInfo.getRoleId() != null) {
			Set set = new HashSet();
			for (int i = 0; i < staffInfo.getRoleId().length; i++) {
				Role role = (Role) this.manageDao.load(Role.class, staffInfo
					.getRoleId()[i]);
				set.add(role);
			}
			staff.setSystemRole(set);
		}
		// ���ù������У�queue���Ǳ�ѡ��
		// setPEWorkQueue(staff, staffInfo.getQueueName());
		// ���浽���ݿ�
		this.manageDao.save(staff);
		// ���浽ldap
		try {
			staffInfo.setDepartmentName(staff.getDepartment().getName());
			// this.ldapManageDao.saveUser(staffInfo);
			ConsoleHelper.getInstance().notifyObservers(staffInfo);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			throw new RuntimeException("����Ldap���ִ��󣬿����޷�����Ldap������������ϵϵͳ����Ա��");
		}

		// �ֵ�ע��
        CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
		
		if (staff.getSystemRole() != null) {
			Iterator roleIterator = staff.getSystemRole().iterator();
			while (roleIterator.hasNext()) {
				Role role = (Role) roleIterator.next();
				Iterator powerIterator = role.getPowers().iterator();
				while (powerIterator.hasNext()) {
					Power power = (Power) powerIterator.next();
					staff.addPowerId(power.getCode());
				}
			}
		}
//		String [] stringArr= staffInfo.getId().split(",");
//		this.doSortStaff(stringArr);
		int order = this.selectStaffUsableOrderNumber(staff.getDepartment().getId());
		staff.setOrderNumber(order);
		return staff;
	}

	// /**��PE����ͬ�������ö���Ȩ��
	// * @param staff
	// * @param queueNames
	// */
	// private void setPEWorkQueue(Staff staff, String[] queueNames) {
	// IWFAdministrator admin = WFProxy.getInstance().getAdministrator();
	//
	// Set<String> newQueues = new HashSet();
	// if (queueNames != null) {
	// for(String queue : queueNames) {
	// newQueues.add(queue);
	// }
	// }
	// Set<String> oldQueues = staff.getWorkQueue();
	// admin.updateStaffQueue(staff.getLoginName(), oldQueues, newQueues);
	// staff.setWorkQueue(newQueues);
	// }

	/**
	 * @see com.console.manage.service.IManageService#deleteStaff(java.lang.Integer)
	 */
	public Staff deleteStaff(String staffId) {
		Staff staff = this.selectStaff(staffId);
		// this.ldapManageDao.deleteUser(staff);
		ConsoleHelper.getInstance().notifyObservers(staff);
		if (staff != null) {
			staff.setValid(ConsoleHelper.NO);
			staff.setLoginName("-" + staff.getLoginName() + "-"
					+ staff.getId().toString() + "-");
			this.manageDao.save(staff);
		}
        CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
		return staff;

	}

	/**
	 * @see com.console.manage.service.IManageService#selectStaffList(java.lang.String,
	 *      Integer)
	 */
	public List selectStaffList(String moduleName, String departmentId) {
		return this.manageDao.selectStaffList(moduleName, departmentId);
	}

	/**
	 * ��non Javadoc�� ��ȡ�ò�����Ա����������
	 *
	 * @see com.console.manage.service.IManageService#selectStaffUsableOrderNumber(java.lang.Integer)
	 */
	public Integer selectStaffUsableOrderNumber(String departmentId) {
		Integer order = new Integer(1);
		List list = this.manageDao.selectStaffUsableOrderNumber(departmentId);
		if (list != null && list.get(0) != null) {
			order = ((Integer) list.get(0));
			order = new Integer(order.intValue() + 1);
		}
		return order;
	}

	/**
	 * @see com.console.manage.service.IManageService#setManageDao(com.console.manage.service.dao.IManageDao)
	 */
	public void setManageDao(IManageDao manageDao) {
		this.manageDao = manageDao;
	}

	/**
	 * ��non Javadoc��
	 *
	 * @see com.console.manage.service.IManageService#doSortStaff(java.lang.Integer[])
	 */
	public void doSortStaff(String[] staffId) {
		for (int i = 0; i < staffId.length; i++) {
			Staff staff = (Staff) this.manageDao.load(Staff.class, staffId[i]);
			staff.setOrderNumber(new Integer(i + 1));
			this.manageDao.save(staff);
		}

	}

	//
	//
	// /**
	// * @see
	// com.console.manage.service.IManageService#selectValidDuty(com.console.duty.command.DutyInfo)
	// */
	// public boolean selectValidDuty(DutyInfo info) {
	// return this.manageDao.selectValidDuty(info);
	// }
	//
	// /**
	// * @see
	// com.console.manage.service.IManageService#selectDuty(java.lang.Integer)
	// */
	// public Duty selectDuty(Integer dutyId) {
	// return (Duty) this.manageDao.load(Duty.class, dutyId);
	// }
	//
	// /**
	// * @see com.console.manage.service.IManageService#deleteDuty(Integer[])
	// */
	// public void deleteDuty(Integer[] deleteId) {
	// Duty duty;
	// for (int i = 0; i < deleteId.length; i++) {
	// duty = (Duty) this.manageDao.load(Duty.class, deleteId[i]);
	// if (duty != null) {
	// this.manageDao.delete(duty);
	// }
	// }
	// }
	//
	// /**
	// * @see com.console.manage.service.IManageService#selectMaxDutyDisplayNo()
	// */
	// public Integer selectMaxDutyDisplayNo() {
	// return this.manageDao.selectMaxDutyDisplayNo();
	// }

	/**
	 * @param mainService the mainService to set
	 */
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}

	public List<Department> selectAllDepartment() {
		return this.manageDao.selectAllDepartment();
	}

	// /**
	// * @see
	// com.console.manage.service.IManageService#selectWorkQueueStaff(java.lang.String)
	// */
	// public List<Staff> selectWorkQueueStaff(String workQueue) {
	// return this.manageDao.selectWorkQueueStaff(workQueue);
	// }

	/**
	 * ȡ��ָ�����ź�ҳ��Ȩ�ޱ������Ա�б�
	 *
	 * @see com.console.manage.service.IManageService#selectOperateStaff(java.lang.String,
	 *      java.lang.String)
	 */
	public List selectOperateStaff(String depId, String operateCode) {

		return this.manageDao.selectOperateStaff(depId, operateCode);
	}

	/**
	 * ��ȡ������Ա�б� ѡ����.
	 *
	 * @see com.console.manage.service.IManageService#selectAllStaff()
	 */
	public List selectAllStaff(StaffQueryInfo info) {
	    return this.manageDao.selectAllStaff(info);
		//return this.manageDao.loadAll(Staff.class, "department.orderNumber,id");
	}

	/**
	 * �ͱ�����ѡ���
	 */
	public List selectInsuDepartmentTree() {
		List depts = this.manageDao.selectInsuDepartmentTree();
		ITreeNode rootNode = PaternityTreeBuilder.createTree(depts);
		return rootNode.getChildren();
	}

    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
        if (Department.SIMPLE_DIC_IDENTIFICATION.equals(objName)) { // �����ֵ�
            List depts = this.manageDao.selectAllDepartment();
            return DictionaryUtils.listToDictionaryItemMap(depts, "id", "name");
        } else if (Department.TREE_DIC_IDENTIFICATION.equals(objName)) { // ������
            List depts = this.manageDao.selectAllDepartment();
            return PaternityTreeBuilder.createTree(depts);
        } else if (Staff.SIMPLE_DIC_IDENTIFICATION.equals(objName)) { // ��Ա�ֵ�
            List staffs = this.manageDao.loadAll(Staff.class, null);
            return DictionaryUtils.listToDictionaryItemMap(staffs, "id", "name");
        }else if(Member.SIMPLE_DIC_IDENTIFICATION.equals(objName)){
        	List member = this.manageDao.loadAll(Member.class,null);
        	return DictionaryUtils.listToDictionaryItemMap(member, "memberId", "userName");//��Ա�ֵ�
        }else if(Stock.SIMPLE_DIC_IDENTIFICATION.equals(objName)){
        	List stock = this.manageDao.loadAll(Stock.class,null);
        	return DictionaryUtils.listToDictionaryItemMap(stock, "id", "gradeNum");//��Ȩ�ֵ�
        }
        return null;
    }

	@Override
	public Staff loadStaff(String staffId) {
		return (Staff) this.manageDao.load(Staff.class, staffId);
	}

	@Override
	public Page selectStaffList(StaffTypeQueryInfo info) {
		
		return this.manageDao.selectStaffList(info);
	}

    @Override
    public List<Department> selectAllDepartments(DepartmentQueryInfo info) {
        Assert.notNull(info);
        return this.manageDao.selectAllDepartments(info);
    }
	@Override
	public List selectAllVaildStaff() {
		return this.manageDao.loadAll(Staff.class, "valid","1","department.orderNumber,id");
	}

//	@Override
//	public List selectAllStaff(StaffInfo info) {
//		
//		return this.manageDao.selectAllStaff(info);
//	}
	
    @Override
    public Power selectPowerByUrl(String urlpath) {
        return this.manageDao.selectPowerByUrl(urlpath);
    }

    @Override
    public List selectDepartmentName(String depName) {
        return this.manageDao.selectDepartmentName(depName);
    }

	@Override
	public Member selectMemberByStaffId(String staffId) {
		
		return this.manageDao.selectMemberByStaffId(staffId);
	}

}
