package com.plugins.sms.command;

public class SmsInfo {

    /** �������� */
    private String smsInfo;
    /** ���������� */
    private String senderName;
    /** ������ID */
    private String senderId;
    /** ���պ���,����ö��Ÿ�����Ϊ��ʱҲҪ��������һ�� */
    private String receiveNums;
    /** ����������,����ö��Ÿ�����Ϊ��ʱҲҪ��������һ�� */
    private String receiverNames;
    /** ������ID,����ö��Ÿ�����Ϊ��ʱҲҪ��������һ�� */
    private String receiverIds;

    public String getSmsInfo() {
        return smsInfo;
    }

    public void setSmsInfo(String smsInfo) {
        this.smsInfo = smsInfo;
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

    public String getReceiveNums() {
        return receiveNums;
    }

    public void setReceiveNums(String receiveNums) {
        this.receiveNums = receiveNums;
    }

    public String getReceiverNames() {
        return receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

}
