package com.systemic.gq.member.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

import com.systemic.gq.entity.Stock;

public class MemberInfo extends PaginationInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6015991437795983425L;
	private String memberId;
	/** ��ʶid */
	private String bsid;
	/** �û��� */
	private String userName;
	/** ���� password */
	private String password;
	/** �������� passwodTwo */
	private String passwodTwo;
	/** ��������*/
	private String passwordThree;
	/** �Ƽ���id */
	private String referenceId;
	/** �Ƽ��� */
	private String referenceName;
	/** ��Ȩ�ȼ�id */
	private Stock stock;
	/** ��Ȩ�ȼ�id */
	private String productgradeId;
	/** ����� */
	private Double goldAward;
	/** ���ӱ� */
	private Double electroniccurrency;
	/** ����ʱ�� */
	private Date createTime;
	/** ����ʱ�� */
	private Date activationTime;
	/**�Ƿ񼤻�*/
	private Integer isActivation;//0δ����  1 ����
	/** �Ƿ����� */
	private Integer isok;
	/** ɾ����ʶ */
	private Integer isdel;
	/** �������� */
	private String region;//Ĭ��0  -���0 �ұ�1  
	/***ϵͳ��¼*/
	private String staffId;
	/***�ڵ�*/
	private String note;
	//�ܱ�����
	private String mbwt;
	//�ܱ������
	private String mbwtDn;
	//��ʵ����
	private String zsxm;
	private String sfzhm;
	//��ϵ�绰
	private String lxdh;
	//��ϵ��ַ
	private String lxdz;
	//��������
	private String email;
	//������Ϣ
	private String yhxx;
	//��������
	private String khxm;
	//���п���
	private String yhkh;
	
	
	
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the bsid
	 */
	public String getBsid() {
		return bsid;
	}
	/**
	 * @param bsid the bsid to set
	 */
	public void setBsid(String bsid) {
		this.bsid = bsid;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwodTwo
	 */
	public String getPasswodTwo() {
		return passwodTwo;
	}
	/**
	 * @param passwodTwo the passwodTwo to set
	 */
	public void setPasswodTwo(String passwodTwo) {
		this.passwodTwo = passwodTwo;
	}
	/**
	 * @return the passwordThree
	 */
	public String getPasswordThree() {
		return passwordThree;
	}
	/**
	 * @param passwordThree the passwordThree to set
	 */
	public void setPasswordThree(String passwordThree) {
		this.passwordThree = passwordThree;
	}
	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}
	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	/**
	 * @return the productgradeId
	 */
	public String getProductgradeId() {
		return productgradeId;
	}
	/**
	 * @param productgradeId the productgradeId to set
	 */
	public void setProductgradeId(String productgradeId) {
		this.productgradeId = productgradeId;
	}
	/**
	 * @return the goldAward
	 */
	public Double getGoldAward() {
		return goldAward;
	}
	/**
	 * @param goldAward the goldAward to set
	 */
	public void setGoldAward(Double goldAward) {
		this.goldAward = goldAward;
	}
	/**
	 * @return the electroniccurrency
	 */
	public Double getElectroniccurrency() {
		return electroniccurrency;
	}
	/**
	 * @param electroniccurrency the electroniccurrency to set
	 */
	public void setElectroniccurrency(Double electroniccurrency) {
		this.electroniccurrency = electroniccurrency;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the activationTime
	 */
	public Date getActivationTime() {
		return activationTime;
	}
	/**
	 * @param activationTime the activationTime to set
	 */
	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}
	/**
	 * @return the isok
	 */
	public Integer getIsok() {
		return isok;
	}
	/**
	 * @param isok the isok to set
	 */
	public void setIsok(Integer isok) {
		this.isok = isok;
	}
	/**
	 * @return the isdel
	 */
	public Integer getIsdel() {
		return isdel;
	}
	/**
	 * @param isdel the isdel to set
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}
	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the mbwt
	 */
	public String getMbwt() {
		return mbwt;
	}
	/**
	 * @param mbwt the mbwt to set
	 */
	public void setMbwt(String mbwt) {
		this.mbwt = mbwt;
	}
	/**
	 * @return the mbwtDn
	 */
	public String getMbwtDn() {
		return mbwtDn;
	}
	/**
	 * @param mbwtDn the mbwtDn to set
	 */
	public void setMbwtDn(String mbwtDn) {
		this.mbwtDn = mbwtDn;
	}
	/**
	 * @return the zsxm
	 */
	public String getZsxm() {
		return zsxm;
	}
	/**
	 * @param zsxm the zsxm to set
	 */
	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}
	/**
	 * @return the sfzhm
	 */
	public String getSfzhm() {
		return sfzhm;
	}
	/**
	 * @param sfzhm the sfzhm to set
	 */
	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh the lxdh to set
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the lxdz
	 */
	public String getLxdz() {
		return lxdz;
	}
	/**
	 * @param lxdz the lxdz to set
	 */
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the yhxx
	 */
	public String getYhxx() {
		return yhxx;
	}
	/**
	 * @param yhxx the yhxx to set
	 */
	public void setYhxx(String yhxx) {
		this.yhxx = yhxx;
	}
	/**
	 * @return the khxm
	 */
	public String getKhxm() {
		return khxm;
	}
	/**
	 * @param khxm the khxm to set
	 */
	public void setKhxm(String khxm) {
		this.khxm = khxm;
	}
	/**
	 * @return the yhkh
	 */
	public String getYhkh() {
		return yhkh;
	}
	/**
	 * @param yhkh the yhkh to set
	 */
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	/**
	 * @return the isActivation
	 */
	public Integer getIsActivation() {
		return isActivation;
	}
	/**
	 * @param isActivation the isActivation to set
	 */
	public void setIsActivation(Integer isActivation) {
		this.isActivation = isActivation;
	}

}
