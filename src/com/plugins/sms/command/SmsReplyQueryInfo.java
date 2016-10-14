package com.plugins.sms.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class SmsReplyQueryInfo extends PaginationInfo {
    /**  */
    private static final long serialVersionUID = 1011599255213444987L;
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
    private Date replyTimeUp;
    /** �ظ�ʱ�� */
    private Date replyTimeDown;

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

    public Date getReplyTimeUp() {
        return replyTimeUp;
    }

    public void setReplyTimeUp(Date replyTimeUp) {
        this.replyTimeUp = replyTimeUp;
    }

    public Date getReplyTimeDown() {
        return replyTimeDown;
    }

    public void setReplyTimeDown(Date replyTimeDown) {
        this.replyTimeDown = replyTimeDown;
    }

}
