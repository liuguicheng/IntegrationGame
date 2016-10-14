package com.plugins.sms.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class SmsQueryInfo extends PaginationInfo {
    /**  */
    private static final long serialVersionUID = 4241217167500192341L;
    /** ��¼ID */
    private String recordId;
    /** ���պ��� */
    private String receiveNum;
    /** ���������� */
    private String receiverName;
    /** ������ID */
    private String receiverId;
    /** ���������� */
    private String senderName;
    /** ������ID */
    private String senderId;
    /** ����ʱ�� */
    private Date sendTimeUp;
    /** ����ʱ�� */
    private Date sendTimeDown;
    /** ����״̬ */
    private String smsState;
    /** ����Ϣ���� */
    private String smsInfo;
    /** ����ϢID */
    private String smsId;
    /** ����ʱ�� */
    private Date createTimeUp;
    /** ����ʱ�� */
    private Date createTimeDown;

    /** ��ѯ�������״̬ */
    private String smsStates;
    /** �ų��������״̬ */
    private String smsExpStates;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getSendTimeUp() {
        return sendTimeUp;
    }

    public void setSendTimeUp(Date sendTimeUp) {
        this.sendTimeUp = sendTimeUp;
    }

    public Date getSendTimeDown() {
        return sendTimeDown;
    }

    public void setSendTimeDown(Date sendTimeDown) {
        this.sendTimeDown = sendTimeDown;
    }

    public String getSmsState() {
        return smsState;
    }

    public void setSmsState(String smsState) {
        this.smsState = smsState;
    }

    public String getSmsInfo() {
        return smsInfo;
    }

    public void setSmsInfo(String smsInfo) {
        this.smsInfo = smsInfo;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public Date getCreateTimeUp() {
        return createTimeUp;
    }

    public void setCreateTimeUp(Date createTimeUp) {
        this.createTimeUp = createTimeUp;
    }

    public Date getCreateTimeDown() {
        return createTimeDown;
    }

    public void setCreateTimeDown(Date createTimeDown) {
        this.createTimeDown = createTimeDown;
    }

    public String getSmsStates() {
        return smsStates;
    }

    public void setSmsStates(String smsStates) {
        this.smsStates = smsStates;
    }

    public String getSmsExpStates() {
        return smsExpStates;
    }

    public void setSmsExpStates(String smsExpStates) {
        this.smsExpStates = smsExpStates;
    }

}
