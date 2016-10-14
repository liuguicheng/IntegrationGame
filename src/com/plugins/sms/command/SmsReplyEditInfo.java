package com.plugins.sms.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class SmsReplyEditInfo extends SpringlineCommand {
    /**  */
    private static final long serialVersionUID = -2520117154376240603L;
    /** �ظ�ID */
    private String replyId;
    /** ���ŷ��ͼ�¼ID */
    private String recordId;
    /** ��������ID */
    private String smsId;
    /** �ظ����� */
    private String replyNum;
    /** �ظ����� */
    private String replyInfo;
    /** �ظ�ʱ�� */
    private Date replyTime;

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

}
