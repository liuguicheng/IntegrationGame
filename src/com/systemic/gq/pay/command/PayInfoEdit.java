package com.systemic.gq.pay.command;


import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class PayInfoEdit extends SpringlineCommand {
	private static final long serialVersionUID = 3517111000510295740L;
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
	private String applyState;//1��ֵ�� 2��ֵ�ɹ� 3��ֵʧ��
	/** ���ʱ�� */
	// private Date auditTime;
	/** ��ע */
	private String remark;
	/** �����IP */
	private String auditIp;

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getApplyRecordNum() {
		return applyRecordNum;
	}

	public void setApplyRecordNum(Long applyRecordNum) {
		this.applyRecordNum = applyRecordNum;
	}

	public String getRecordType() {
		return recordType;
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
		return applyLocalHost;
	}

	public void setApplyLocalHost(String applyLocalHost) {
		this.applyLocalHost = applyLocalHost;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditIp() {
		return auditIp;
	}

	public void setAuditIp(String auditIp) {
		this.auditIp = auditIp;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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
