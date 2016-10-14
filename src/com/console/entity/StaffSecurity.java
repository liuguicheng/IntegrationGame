package com.console.entity;

import java.util.Date;

/**
 * SysStaffSecurity entity. @author MyEclipse Persistence Tools
 */

public class StaffSecurity implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 3343200599413545438L;
    private String ssid;
    /**�������ʱ��*/
    private Date changeTime;
    /**��¼ʧ��ʱ��*/
    private Date failedRecordTime;
    /** ʧ�ܴ��� */
    private Integer failedTimes;
    /** ��¼���� */
    private Integer times;
    /** �Ƿ�����. */
    private String isLock;
    
    private String staffId;
    

    // Constructors

    /** default constructor */
    public StaffSecurity() {
    }

    /** minimal constructor */
    public StaffSecurity(String staffId) {
        this.staffId = staffId;
    }
    
    
    public StaffSecurity(String ssid, Date changeTime, Date failedRecordTime,
            Integer failedTimes, Integer times, String isLock, String staffId) {
        super();
        this.ssid = ssid;
        this.changeTime = changeTime;
        this.failedRecordTime = failedRecordTime;
        this.failedTimes = failedTimes;
        this.times = times;
        this.isLock = isLock;
        this.staffId = staffId;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    // Property accessors
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getFailedRecordTime() {
        return failedRecordTime;
    }

    public void setFailedRecordTime(Date failedRecordTime) {
        this.failedRecordTime = failedRecordTime;
    }

    public Integer getFailedTimes() {
        return failedTimes;
    }

    public void setFailedTimes(Integer failedTimes) {
        this.failedTimes = failedTimes;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}