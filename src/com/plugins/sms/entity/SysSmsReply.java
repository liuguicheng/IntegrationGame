package com.plugins.sms.entity;

import java.util.Date;

/**
 * SysSmsReply entity. @author MyEclipse Persistence Tools
 */

public class SysSmsReply implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = -546087383527032448L;
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

    // Property accessors

    public String getReplyId() {
        return this.replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getRecordId() {
        return this.recordId;
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
        return this.replyInfo;
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