package com.console.entity;

import java.io.Serializable;

public class Operate implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5101883223456863035L;

	/** Ȩ�ޱ��. */
    private String operateId;
    /** Ȩ������. */
    private String operateName;
    /** Ȩ�ޱ���. */
    private String operateCode;
    /** ��ע. */
    private String remark;

	/**
	 * @return the operateId
	 */
	public String getOperateId() {
		return operateId;
	}
	/**
	 * @param operateId the operateId to set
	 */
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	/**
	 * @return the operateName
	 */
	public String getOperateName() {
		return operateName;
	}
	/**
	 * @param operateName the operateName to set
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	/**
	 * @return the operateCode
	 */
	public String getOperateCode() {
		return operateCode;
	}
	/**
	 * @param operateCode the operateCode to set
	 */
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
