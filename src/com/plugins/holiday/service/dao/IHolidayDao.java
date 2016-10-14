package com.plugins.holiday.service.dao;


import java.util.Date;
import java.util.List;
import org.springline.orm.dao.ICommonDao;
import com.plugins.holiday.entity.Holiday;

public interface IHolidayDao extends  ICommonDao{

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
     List getHolidayStr(String year,String isStatutory,String holidayType);
     /**
      * ������ݿ��д������������ڵ�����
      * @param begin ��ʼ����
      * @param end ��������
      * @return
      */
     List  getBetweenDays(Date begin,Date end);
     /**
      * ���ݴ��������  ��ѯ���ݿ� �ж���������Ƿ�ż�
      * @param date
      * @return true �ż� false���ż�
      */
     Boolean checkHolidayByDate(Date date);
     /**
      * ɾ�����ݿ��е���������
      */
     void deleteAll();
     /**
      * ���ĳһ������м�¼
      * @param year
      * @return
      */
     List getHolidayByyearStr(String year);
     /**
      * ���ĳһ������зżٵ�����
      * @param year
      * @return
      */
     List getHolidays(String year);
     /**
      * ������ݺ������ַ��� ��ѯ����
      * @param year
      * @param str
      * @return
      */
     List getHolidayStr(String date);
     /**
      * �鿴���ݿ�������Щ���ݳ�ʼ������
      * @return
      */
     List getYearFromHoliday();

}
