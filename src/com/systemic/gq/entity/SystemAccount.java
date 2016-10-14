package com.systemic.gq.entity;

public class SystemAccount implements java.io.Serializable {
	private static final long serialVersionUID = -2522354810725064304L;
	/**
	 * ϵͳ�˻�
	 */
	
	private String id;
	private double totalregisteredamount;//ע���ܽ��
	private double totalElectronicCurrency;//�ܵ��ӱ�
	private double totalBouns;//�ܽ���
	private double bonusPaid;//�ѷ�����
	private double pendingBonus;//��������
	private double cashWithdrawalAmount;//���ֽ��
	private int memberCount;//������

	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the totalregisteredamount
	 */
	public double getTotalregisteredamount() {
		return totalregisteredamount;
	}
	/**
	 * @param totalregisteredamount the totalregisteredamount to set
	 */
	public void setTotalregisteredamount(double totalregisteredamount) {
		this.totalregisteredamount = totalregisteredamount;
	}
	/**
	 * @return the totalElectronicCurrency
	 */
	public double getTotalElectronicCurrency() {
		return totalElectronicCurrency;
	}
	/**
	 * @param totalElectronicCurrency the totalElectronicCurrency to set
	 */
	public void setTotalElectronicCurrency(double totalElectronicCurrency) {
		this.totalElectronicCurrency = totalElectronicCurrency;
	}
	/**
	 * @return the totalBouns
	 */
	public double getTotalBouns() {
		return totalBouns;
	}
	/**
	 * @param totalBouns the totalBouns to set
	 */
	public void setTotalBouns(double totalBouns) {
		this.totalBouns = totalBouns;
	}
	/**
	 * @return the bonusPaid
	 */
	public double getBonusPaid() {
		return bonusPaid;
	}
	/**
	 * @param bonusPaid the bonusPaid to set
	 */
	public void setBonusPaid(double bonusPaid) {
		this.bonusPaid = bonusPaid;
	}
	/**
	 * @return the pendingBonus
	 */
	public double getPendingBonus() {
		return pendingBonus;
	}
	/**
	 * @param pendingBonus the pendingBonus to set
	 */
	public void setPendingBonus(double pendingBonus) {
		this.pendingBonus = pendingBonus;
	}
	/**
	 * @return the cashWithdrawalAmount
	 */
	public double getCashWithdrawalAmount() {
		return cashWithdrawalAmount;
	}
	/**
	 * @param cashWithdrawalAmount the cashWithdrawalAmount to set
	 */
	public void setCashWithdrawalAmount(double cashWithdrawalAmount) {
		this.cashWithdrawalAmount = cashWithdrawalAmount;
	}
	/**
	 * @return the memberCount
	 */
	public int getMemberCount() {
		return memberCount;
	}
	/**
	 * @param memberCount the memberCount to set
	 */
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemAccount [id=" + id + ", totalregisteredamount=" + totalregisteredamount
				+ ", totalElectronicCurrency=" + totalElectronicCurrency + ", totalBouns=" + totalBouns + ", bonusPaid="
				+ bonusPaid + ", pendingBonus=" + pendingBonus + ", cashWithdrawalAmount=" + cashWithdrawalAmount
				+ ", memberCount=" + memberCount + "]";
	}
	public SystemAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SystemAccount(String id, double totalregisteredamount, double totalElectronicCurrency, double totalBouns,
			double bonusPaid, double pendingBonus, double cashWithdrawalAmount, int memberCount) {
		super();
		this.id = id;
		this.totalregisteredamount = totalregisteredamount;
		this.totalElectronicCurrency = totalElectronicCurrency;
		this.totalBouns = totalBouns;
		this.bonusPaid = bonusPaid;
		this.pendingBonus = pendingBonus;
		this.cashWithdrawalAmount = cashWithdrawalAmount;
		this.memberCount = memberCount;
	}

}
