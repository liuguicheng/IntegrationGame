/**
 * Description:
 * History:  2010-8-19 Create
**/

package com.plugins.holiday;

import java.util.Date;

/**
 * ��ע�ڼ��ն���Ķ�����ʵ�ָü������ӿ�
 * ������Helper����ע�ᣬ�ڽڼ��ն�����ʱ���ڼ���Service��֪ͨ���еļ������ñ�����
 * @description
 */
public interface IHolidayObserver {

    void doHolidayUpdate(Date beginTime, Date endTime);
}
