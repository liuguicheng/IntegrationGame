package com.plugins.holiday.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Holiday entity. @author MyEclipse Persistence Tools
 */

public class Holiday implements java.io.Serializable {

    // Fields

    /**����ID*/
    private String holidayId;
    /**��������*/
    private String holidayType;
    /**��������*/
    private String holidayName;
    /**�Ƿ�ż�*/
    private String isLeave;
    /**�ż�ԭ��*/
    private String holidayReason;
    /**���� ���ڱ�ʾ*/
    private Date holidayDate;
    /**���� �ַ�����ʾ*/
    private String holidayStr;
    /**�Ƿ񷨶�����*/
    private String isStatutory;
    /**��ע*/
    private String remark;
    private String holidayYear;

    /**
     * ��ʾ�ż�
     */
    public static final String HOLIDAY_YES="1";
    /**
     * ��ʾ���ż�
     */
    public static final String HOLIDAY_NO="0";


    /**
     * ��ʾũ����������
     */
    public static final String LunarCalendar_TYPE="0";
    /**
     * ��ʾ����(����)��������
     */
    public static final String GregorianCalendar_TYPE="1";
    public static final Map holidayYearMap=new HashMap();

    // Constructors

    /** default constructor */
    public Holiday() {
    }

    /** minimal constructor */
    public Holiday(String holidayId) {
        this.holidayId = holidayId;
    }

    /** full constructor */
    public Holiday(String holidayId, String holidayType, String holidayName,
            String isLeave, String holidayReason, Date holidayDate,
            String holidayStr, String isStatutory, String remark) {
        this.holidayId = holidayId;
        this.holidayType = holidayType;
        this.holidayName = holidayName;
        this.isLeave = isLeave;
        this.holidayReason = holidayReason;
        this.holidayDate = holidayDate;
        this.holidayStr = holidayStr;
        this.isStatutory = isStatutory;
        this.remark = remark;
    }

    // Property accessors



    public String getHolidayId() {
        return this.holidayId;
    }

    public String getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(String holidayYear) {
        this.holidayYear = holidayYear;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayType() {
        return this.holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public String getHolidayName() {
        return this.holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getIsLeave() {
        return this.isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    public String getHolidayReason() {
        return this.holidayReason;
    }

    public void setHolidayReason(String holidayReason) {
        this.holidayReason = holidayReason;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayStr() {
        return this.holidayStr;
    }

    public void setHolidayStr(String holidayStr) {
        this.holidayStr = holidayStr;
    }

    public String getIsStatutory() {
        return isStatutory;
    }

    public void setIsStatutory(String isStatutory) {
        this.isStatutory = isStatutory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



}