package com.plugins.sms;

import org.springline.orm.Page;

import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;

/**
 * ��ʵ��Helper����
 * @description 
 */
public abstract class SmsHelper {

    private static SmsHelper instance;

    public static SmsHelper getInstance() {
        return instance;
    }

    protected void setInstance(SmsHelper instance) {
        SmsHelper.instance = instance;
    }


    /**
     * ���Ͷ���Ϣ
     * 
     * @param info
     */
    public abstract boolean sendSms(SmsInfo info);

    /**
     * ��ѯ����Ϣ���ͼ�¼
     * 
     * @param info
     * @return
     */
    public abstract Page selectSmsPage(SmsQueryInfo info);
    
    /**
     * �������޸Ķ��ŷ��ͼ�¼
     * 
     * @param info
     * @return
     */
    public abstract  SysSms saveSms(SmsEditInfo info);
}
