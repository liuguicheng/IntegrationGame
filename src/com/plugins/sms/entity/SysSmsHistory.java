package com.plugins.sms.entity;

import java.util.Date;

/**
 * SysSmsHistory entity. @author MyEclipse Persistence Tools
 */

public class SysSmsHistory implements java.io.Serializable {
    /**  */
    private static final long serialVersionUID = -4432935882533759580L;
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
    private Date sendTime;
    /** ����״̬ */
    private String smsState;
    /** ����Ϣ���� */
    private String smsInfo;
    /** ����ϢID */
    private String smsId;
    /** ����ʱ�� */
    private Date createTime;

    // Property accessors

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getReceiveNum() {
        return this.receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSmsState() {
        return smsState;
    }

    public void setSmsState(String smsState) {
        this.smsState = smsState;
    }

    public String getSmsInfo() {
        return this.smsInfo;
    }

    public void setSmsInfo(String smsInfo) {
        this.smsInfo = smsInfo;
    }

    public String getSmsId() {
        return this.smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}