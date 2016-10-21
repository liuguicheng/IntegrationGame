package com.plugins.msg.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class MessageQueryInfo extends PaginationInfo {

	/**  */
	private static final long serialVersionUID = 1520952086309936800L;
	/** �û���� */
	private String staffId;
	/** �Ƿ��Ѷ� */
	private String isReaded;
	/** ������ */
	private String receiveMan;
	/** ������ */
	private String sendMan;
	/** ����ʱ�� */
	private Date sendTime;
    private Date sendTimeUp;
    private Date sendTimeDown;
    private String content;
	/**�������״̬Ϊ�˵��������ʱ��ֻ��ʾ��ǰ������Ϣ*/
	private String chatState;
	/**��ʶ��������ϵͳ��Ϣ����������Ϣ��*/
	private String flag;
	
	private String messageType;//��Ϣ����
	 private String messageTitel;//����
	 private String receiveLevel;//���ռ���

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(String isReaded) {
		this.isReaded = isReaded;
	}

	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	public String getSendMan() {
		return sendMan;
	}

	public void setSendMan(String sendMan) {
		this.sendMan = sendMan;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getChatState() {
		return chatState;
	}

	public void setChatState(String chatState) {
		this.chatState = chatState;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the messageTitel
	 */
	public String getMessageTitel() {
		return messageTitel;
	}

	/**
	 * @param messageTitel the messageTitel to set
	 */
	public void setMessageTitel(String messageTitel) {
		this.messageTitel = messageTitel;
	}

	/**
	 * @return the receiveLevel
	 */
	public String getReceiveLevel() {
		return receiveLevel;
	}

	/**
	 * @param receiveLevel the receiveLevel to set
	 */
	public void setReceiveLevel(String receiveLevel) {
		this.receiveLevel = receiveLevel;
	}

}
