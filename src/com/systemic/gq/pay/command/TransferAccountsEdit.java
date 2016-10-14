package com.systemic.gq.pay.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class TransferAccountsEdit extends SpringlineCommand {

	private static final long serialVersionUID = 8701774206619256063L;
	private String taId;//id
    private String taTurnoutAccount;//ת���˺�
    private String taTurnoutName;//ת����Ա
    private String taIntoAccount;//ת���˺�
    private String taIntoName;//ת������
    private String taType;//ת��Ǯ������
    private Double taMoeny;//���
    private Date taTime;//ʱ��
    private String remark;//��ע
	/**
	 * @return the taId
	 */
	public String getTaId() {
		return taId;
	}
	/**
	 * @param taId the taId to set
	 */
	public void setTaId(String taId) {
		this.taId = taId;
	}
	/**
	 * @return the taTurnoutAccount
	 */
	public String getTaTurnoutAccount() {
		return taTurnoutAccount;
	}
	/**
	 * @param taTurnoutAccount the taTurnoutAccount to set
	 */
	public void setTaTurnoutAccount(String taTurnoutAccount) {
		this.taTurnoutAccount = taTurnoutAccount;
	}
	/**
	 * @return the taTurnoutName
	 */
	public String getTaTurnoutName() {
		return taTurnoutName;
	}
	/**
	 * @param taTurnoutName the taTurnoutName to set
	 */
	public void setTaTurnoutName(String taTurnoutName) {
		this.taTurnoutName = taTurnoutName;
	}
	/**
	 * @return the taIntoAccount
	 */
	public String getTaIntoAccount() {
		return taIntoAccount;
	}
	/**
	 * @param taIntoAccount the taIntoAccount to set
	 */
	public void setTaIntoAccount(String taIntoAccount) {
		this.taIntoAccount = taIntoAccount;
	}
	/**
	 * @return the taIntoName
	 */
	public String getTaIntoName() {
		return taIntoName;
	}
	/**
	 * @param taIntoName the taIntoName to set
	 */
	public void setTaIntoName(String taIntoName) {
		this.taIntoName = taIntoName;
	}
	/**
	 * @return the taMoeny
	 */
	public Double getTaMoeny() {
		return taMoeny;
	}
	/**
	 * @param taMoeny the taMoeny to set
	 */
	public void setTaMoeny(Double taMoeny) {
		this.taMoeny = taMoeny;
	}
	/**
	 * @return the taTime
	 */
	public Date getTaTime() {
		return taTime;
	}
	/**
	 * @param taTime the taTime to set
	 */
	public void setTaTime(Date taTime) {
		this.taTime = taTime;
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
	/**
	 * @return the taType
	 */
	public String getTaType() {
		return taType;
	}
	/**
	 * @param taType the taType to set
	 */
	public void setTaType(String taType) {
		this.taType = taType;
	}
    
}
