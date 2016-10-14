package com.plugins.sms.service;

import org.springline.orm.Page;

import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyEditInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.entity.SysSmsHistory;
import com.plugins.sms.entity.SysSmsReply;

/**
 * ����Ϣ����ӿ�
 * 
 * @author ydl 2014-5-15
 * 
 */
public interface ISmsService {

    /**
     * ��ҳ��ѯ���ŷ��ͼ�¼
     * 
     * @param info
     * @return
     */
    Page selectSmsPage(SmsQueryInfo info);

    /**
     * ����ҵ��ID��ѯ���ŷ��ͼ�¼
     * 
     * @param recordId
     * @return
     */
    SysSms selectSms(String recordId);

    /**
     * �������޸Ķ��ŷ��ͼ�¼
     * 
     * @param info
     * @return
     */
    SysSms saveSms(SmsEditInfo info);

    /**
     * ɾ�����ŷ��ͼ�¼
     * 
     * @param ids
     */
    void deleteSms(String[] ids);

    /**
     * ��ҳ��ѯ���ŷ�����ʷ��¼
     * 
     * @param info
     * @return
     */
    Page selectSmsHistoryPage(SmsQueryInfo info);

    /**
     * ����ҵ��ID��ѯ���ŷ�����ʷ��¼
     * 
     * @param recordId
     * @return
     */
    SysSmsHistory selectSmsHistory(String recordId);

    /**
     * ɾ�����ŷ�����ʷ��¼
     * 
     * @param ids
     */
    void deleteSmsHistory(String[] ids);

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

    /**
     * ����ظ�
     * 
     * @param info
     * @return
     */
    SysSmsReply saveSmsReply(SmsReplyEditInfo info);

    /**
     * ���ɶ���
     * 
     * @return
     */
    boolean doSendSms(SmsInfo info);

}
