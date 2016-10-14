package com.plugins.holiday.service;

import java.util.Date;
import java.util.List;


import com.plugins.holiday.command.HolidayEditInfo;
import com.plugins.holiday.entity.Holiday;

public interface IHolidayService {

    /**
     * ����id��ü��յ���ϸ��Ϣ
     * @param holidayId
     * @return
     */
    Holiday selectHolidayById(String holidayId);
    /**
     * ���������Ϣ
     * @param editInfo
     */
    Holiday saveHoliday(HolidayEditInfo  editInfo);
    /**
     * ɾ��������Ϣ
     * @param holidayIds
     */
    void deleteHoliday(String[] holidayIds);
    /**
     * ��ʼ������
     * @param year
     */
    void doInitHolidy(int year);
    /**
     *�������ƻ�ü��յ��б���Ϣ
     * @return
     */
    List<Holiday> getHolidayByName(String name);
    /**
     * ��ò��ظ��Ľ��յ�����
     * @return
     */
    List getNameList(String name);
    /**
     * ���ĳ�ּ��յ������ַ���  ���ڳ�ʼ��js
     * @param isStatutory 0���Ƿ������� 1�Ƿ�������
     * @param holidayType  0 ũ�� 1 ����
     * @return
     */
    List selectHolidayStr(String year,String isStatutory,String holidayType);
    /**
     * ������ݿ��д������������ڵ�����
     * @param begin ��ʼ����
     * @param end ��������
     * @return
     */
    List  doGetBetweenDays(Date begin,Date end);
    /**
     * ���ĳһ������зżٵ�����
     * @param year
     * @return
     */
    public List selectHolidays(String year);
    public List selectHolidayStr(String date);
    Holiday updateHoliday(String date);
}
