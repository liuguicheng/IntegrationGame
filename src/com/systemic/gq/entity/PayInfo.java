package com.systemic.gq.entity;

import java.util.Date;

/**
 * PayInfo entity. 
 */

public class PayInfo implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5512640092923511985L;
	/** ��¼��� */
	private String numberId;
	/** ������id(��Ա) */
	private String memberId;
	/**��Ա��� */
	private String memnberStaffId;
	/** ��ֵ�� */
	private Long applyRecordNum;
	/** ��ֵ���� */
	private String recordType;
	/** ��ֵǰ��� */
	private Double payBeforeNum;
	/** ��ֵ���� */
	private Double payAfterNum;
	/** ����ʱ�� */
	private Date applyTime;
	/** ������ip */
	private String applyLocalHost;
	/** ����� */
	private String handleId;
	/** ���״̬ */
	private String applyState;
	/** ���ʱ�� */
	private Date auditTime;
	/** ��ע */
	private String remark;
	/** �����IP */
	private String auditIp;

	// Constructors

	/** default constructor */
	public PayInfo() {
	}

	/** full constructor */
	public PayInfo(String memberId,String memnberStaffId, Long applyRecordNum, String recordType,
			Double payBeforeNum, Double payAfterNum, Date applyTime,
			String applyLocalHost, String handleId, String applyState,
			Date auditTime, String remark, String auditIp) {
		this.memberId = memberId;
		this.memnberStaffId=memnberStaffId;
		this.applyRecordNum = applyRecordNum;
		this.recordType = recordType;
		this.payBeforeNum = payBeforeNum;
		this.payAfterNum = payAfterNum;
		this.applyTime = applyTime;
		this.applyLocalHost = applyLocalHost;
		this.handleId = handleId;
		this.applyState = applyState;
		this.auditTime = auditTime;
		this.remark = remark;
		this.auditIp = auditIp;
	}

	// Property accessors

	public String getNumberId() {
		return this.numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getApplyRecordNum() {
		return this.applyRecordNum;
	}

	public void setApplyRecordNum(Long applyRecordNum) {
		this.applyRecordNum = applyRecordNum;
	}

	public String getRecordType() {
		return this.recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	

	public Double getPayBeforeNum() {
		return payBeforeNum;
	}

	public void setPayBeforeNum(Double payBeforeNum) {
		this.payBeforeNum = payBeforeNum;
	}

	public Double getPayAfterNum() {
		return payAfterNum;
	}

	public void setPayAfterNum(Double payAfterNum) {
		this.payAfterNum = payAfterNum;
	}

	public String getApplyLocalHost() {
		return this.applyLocalHost;
	}

	public void setApplyLocalHost(String applyLocalHost) {
		this.applyLocalHost = applyLocalHost;
	}

	public String getHandleId() {
		return this.handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getApplyState() {
		return this.applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditIp() {
		return this.auditIp;
	}

	public void setAuditIp(String auditIp) {
		this.auditIp = auditIp;
	}

	/**
	 * @return the memnberStaffId
	 */
	public String getMemnberStaffId() {
		return memnberStaffId;
	}

	/**
	 * @param memnberStaffId the memnberStaffId to set
	 */
	public void setMemnberStaffId(String memnberStaffId) {
		this.memnberStaffId = memnberStaffId;
	}

}