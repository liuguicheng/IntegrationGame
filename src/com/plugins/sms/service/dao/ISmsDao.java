package com.plugins.sms.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;

public interface ISmsDao extends ICommonDao {
    /**
     * ��ҳ��ѯ���ŷ��ͼ�¼
     * 
     * @param info
     * @return
     */
    Page selectSmsPage(SmsQueryInfo info);

    /**
     * ��ҳ��ѯ���ŷ�����ʷ��¼
     * 
     * @param info
     * @return
     */
    Page selectSmsHistoryPage(SmsQueryInfo info);

    /**
     * ��ҳ��ѯ���ŷ������м�¼��������ʷ�͵�ǰ��
     * 
     * @param info
     * @return
     */
    Page selectSmsViewPage(SmsQueryInfo info);

    /**
     * ��ҳ��ѯ���Żظ���ǰ��¼
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyPage(SmsReplyQueryInfo info);

    /**
     * ��ҳ��ѯ���Żظ���ʷ��¼
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyHistoryPage(SmsReplyQueryInfo info);

    /**
     * ��ҳ��ѯ���Żظ����м�¼��������ʷ�͵�ǰ��
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyViewPage(SmsReplyQueryInfo info);
}
