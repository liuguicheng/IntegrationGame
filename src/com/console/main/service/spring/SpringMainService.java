/**
 * Description:
 * History:  2009-7-22 Create
**/

package com.console.main.service.spring;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.console.main.service.IMainService;
import com.console.main.service.dao.IMainDao;

/**
 * @description
 */
public class SpringMainService implements IMainService {

    /**
     * IOC
     */
    private IMainDao mainDao;
    /**
     * @see com.console.main.service.IMainService#selectDepartment(java.lang.Integer)
     */
    public Department selectDepartment(String id) {
        Department dept = (Department) this.mainDao.load(Department.class, id);
        this.mainDao.initialize(dept.getPowerSet());
        return dept;
    }

    /**
     * @see com.console.main.service.IMainService#selectStaff(java.lang.String)
     */
    public Staff selectStaff(String loginName) {
        return this.mainDao.selectStaff(loginName);
    }

    /**
     * @see com.console.main.service.IMainService#updatePassword(com.console.entity.Staff, java.lang.String)
     */
    public void updatePassword(Staff staff, String newPassword) {
        staff.setPassword(EncryptHelper.md5Encoding(newPassword));
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, 300);
        this.mainDao.save(staff);
        
    }

    /**
     * @see com.console.main.service.IMainService#doValidate(java.lang.String, java.lang.String)
     */
    public Staff doValidate(String loginName, String password) {
        Staff staff = this.mainDao.selectAllStaff(loginName);

        if (staff != null && staff.getPassword().equals(EncryptHelper.md5Encoding(password))) {
//            this.mainDao.initialize(staff.getWorkQueue());
            Iterator roleIterator = staff.getSystemRole().iterator();
            while (roleIterator.hasNext()) {
                Role role = (Role) roleIterator.next();
                Iterator powerIterator = role.getPowers().iterator();
                while (powerIterator.hasNext()) {
                    Power power = (Power) powerIterator.next();
                    staff.addPowerId(power.getCode());
                }
            }
            return staff;
        }
        return null;
    }
    /**
     * @param mainDao the mainDao to set
     */
    public void setMainDao(IMainDao mainDao) {
        this.mainDao = mainDao;
    }
    
  
    
    @Override
    public Staff updateIsLogin(Staff staff,String isLogin) {
        staff.setIsLogin(isLogin);
        this.mainDao.update(staff);
        return staff;
    }
    
    @Override
    public StaffSecurity loadStaffSecurity(String staffId) {
        List<StaffSecurity> list = this.mainDao.loadAll(StaffSecurity.class, "staffId", staffId, null);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }else{
            StaffSecurity ss = new StaffSecurity(staffId);
            this.mainDao.save(ss);
            return ss;
        }
    }

    @Override
    public Staff selectAllStaff(String loginName) {
        return this.mainDao.selectAllStaff(loginName);
    }

    @Override
    public StaffSecurity doTimes(String loginName, int times) {
        Staff staff = this.mainDao.selectStaff(loginName);
        if(staff !=null){
            StaffSecurity ss = this.loadStaffSecurity(staff.getId());
            ss.setTimes(times);
            this.mainDao.save(ss);
            return ss;
        }
        return null;
    }

    @Override
    public StaffSecurity doFailiedTimes(String loginName, int count) {
        Staff staff = this.mainDao.selectStaff(loginName);
        if(staff !=null){
            StaffSecurity ss = this.loadStaffSecurity(staff.getId());
            ss.setFailedTimes(count);
            ss.setFailedRecordTime(new Date());
            ss.setTimes(0);
            if(count == 3){
              ss.setIsLock(ConsoleHelper.NO);
            } 
            this.mainDao.save(ss);
            return ss;
        }
        return null;
    }

    @Override
    public Staff doUnlock(Staff staff) {
        if(staff !=null){
            StaffSecurity ss = this.loadStaffSecurity(staff.getId());
            ss.setTimes(0);
            ss.setFailedTimes(null);
            ss.setFailedRecordTime(null);
            ss.setIsLock(ConsoleHelper.YES);
            this.mainDao.save(ss);
        }
        return staff;
    }

	@Override
	public Staff selectStaffById(String id) {
		return (Staff) this.mainDao.load(Staff.class, id);
	}

	@Override
	public void doFrozen(String userName) {
		Staff staff = this.mainDao.selectStaff(userName);
        if(staff !=null){
            StaffSecurity ss = this.loadStaffSecurity(staff.getId());
            ss.setFailedTimes(0);
            ss.setFailedRecordTime(new Date());
            ss.setTimes(0);
            ss.setIsLock(ConsoleHelper.NO);
            this.mainDao.save(ss);
        }
	}
}
