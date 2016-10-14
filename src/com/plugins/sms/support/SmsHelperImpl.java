package com.plugins.sms.support;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springline.orm.Page;

import com.plugins.sms.SmsHelper;
import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsHelperImpl extends SmsHelper implements InitializingBean {

    /** ���ض��ŷ��� */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setInstance(this);
    }

    /**
     * ���Ͷ���Ϣ
     * 
     * @param info
     */
    public boolean sendSms(SmsInfo info) {
        if (this.smsService == null) {
            throw new DataIntegrityViolationException("δ����SmsHelperʵ������");
        }

        return this.smsService.doSendSms(info);
    }

    /**
     * ��ѯ����Ϣ���ͼ�¼
     * 
     * @param info
     * @return
     */
    public Page selectSmsPage(SmsQueryInfo info) {
        if (smsService == null) {
            throw new DataIntegrityViolationException("δ����SmsHelperʵ������");
        }

        return this.smsService.selectSmsViewPage(info);
    }

    /**
     * �������޸Ķ��ŷ��ͼ�¼
     * 
     * @param info
     * @return
     */
    public SysSms saveSms(SmsEditInfo info) {
        if (this.smsService == null) {
            throw new DataIntegrityViolationException("δ����SmsHelperʵ������");
        }

        return this.smsService.saveSms(info);
    }
}
