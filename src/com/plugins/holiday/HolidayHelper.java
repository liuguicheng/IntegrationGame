/**
 * Description:
 * History:  2010-8-19 Create
**/

package com.plugins.holiday;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 1���ṩ��ʵ��������ڼ���ȡ�����յĽӿ�
 * 2��ʵ�ֹ۲���ģʽ�еı��۲��߽�ɫ���ѱ�֤�ڼ��յĶ���仯�ܼ�ʱ֪ͨ��ط���
 * 3��������̳��ṩ����ʵ���������ͱ��֪ͨ
 */
public abstract class HolidayHelper {

    /**
     * ��ʵ��
     */
    private static HolidayHelper instance = null;

    /**
     * ��ʵ���������
     * @return
     */
    public static HolidayHelper getInstance() {
        return instance;
    }

    /**
     * �۲����嵥
     */
    private Set<IHolidayObserver> observers = new HashSet();

    /**
     * �ɹ۲����Լ�ע��
     * @param o
     */
    public synchronized void registerObserver(IHolidayObserver o) {
        observers.add(o);
    }

    /**
     * ���ͱ��֪ͨ�����������
     * @param beginTime
     * @param endTime
     */
    protected synchronized void doUpdate(Date beginTime, Date endTime) {
        for (IHolidayObserver o : observers) {
            o.doHolidayUpdate(beginTime, endTime);
        }
    }

    /**
     * ������ʼʱ�䡢���������������ֹʱ��
     * @param beginTime ��ʼʱ��
     * @param workDay ����������
     * @return ��ֹʱ��
     */
    public abstract Date getDeadline(Date beginTime, int workDay);

    public static void setInstance(HolidayHelper instance) {
        HolidayHelper.instance = instance;
    }


}
