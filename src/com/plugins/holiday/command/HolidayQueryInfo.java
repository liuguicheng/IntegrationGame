package com.plugins.holiday.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class HolidayQueryInfo extends PaginationInfo{

    //��ѯ������ʱ�� ,��Ҫ�ں��潫��ֽ�Ϊ��+�յ���ʽ  �����ѯ
    private String minHolidayDate;
    private String maxHolidayDate;
    //����ʱ��ת��Ϊũ��
    private String minNlHolidayDate;
    private String maxNlHolidayDate;
    /**��������*/
    private String holidayName;
    /**�Ƿ�ż�*/
    private String isLeave;
    /**�ż�ԭ��*/
    private String holidayReason;
    public String getMinHolidayDate() {
        return minHolidayDate;
    }
    public void setMinHolidayDate(String minHolidayDate) {
        this.minHolidayDate = minHolidayDate;
    }
    public String getMaxHolidayDate() {
        return maxHolidayDate;
    }
    public void setMaxHolidayDate(String maxHolidayDate) {
        this.maxHolidayDate = maxHolidayDate;
    }
    public String getMinNlHolidayDate() {
        return minNlHolidayDate;
    }
    public void setMinNlHolidayDate(String minNlHolidayDate) {
        this.minNlHolidayDate = minNlHolidayDate;
    }
    public String getMaxNlHolidayDate() {
        return maxNlHolidayDate;
    }
    public void setMaxNlHolidayDate(String maxNlHolidayDate) {
        this.maxNlHolidayDate = maxNlHolidayDate;
    }
    public String getHolidayName() {
        return holidayName;
    }
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
    public String getIsLeave() {
        return isLeave;
    }
    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }
    public String getHolidayReason() {
        return holidayReason;
    }
    public void setHolidayReason(String holidayReason) {
        this.holidayReason = holidayReason;
    }




}
