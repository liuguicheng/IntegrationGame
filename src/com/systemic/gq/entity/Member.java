package com.systemic.gq.entity;

import java.util.Date;

public class Member implements java.io.Serializable {
	// Fields
	public static final String SIMPLE_DIC_IDENTIFICATION = "dicMember";
	/**
	 *
	 */
	private static final long serialVersionUID = -1948363514837972869L;
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
	// Constructors

	/** default constructor */
	public Member() {
	}

	// Property accessors

	public String getProductgradeId() {
		return productgradeId;
	}

	public Member(String memberId, String bsid, String userName,
			String password, String passwodTwo, String referenceId,
			String referenceName, Stock stock, String productgradeId,
			Double goldAward, Double electroniccurrency, Date createTime,
			Date activationTime, String note ,Integer isok, Integer isdel, String region,String staffId) {
		super();
		this.memberId = memberId;
		this.bsid = bsid;
		this.userName = userName;
		this.password = password;
		this.passwodTwo = passwodTwo;
		this.referenceId = referenceId;
		this.referenceName = referenceName;
		this.stock = stock;
		this.productgradeId = productgradeId;
		this.goldAward = goldAward;
		this.electroniccurrency = electroniccurrency;
		this.createTime = createTime;
		this.activationTime = activationTime;
		this.isok = isok;
		this.isdel = isdel;
		this.region = region;
		this.staffId = staffId;
		this.note = note;
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

	public void setProductgradeId(String productgradeId) {
		this.productgradeId = productgradeId;
	}

	public String getBsid() {
		return this.bsid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setBsid(String bsid) {
		this.bsid = bsid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswodTwo() {
		return this.passwodTwo;
	}

	public void setPasswodTwo(String passwodTwo) {
		this.passwodTwo = passwodTwo;
	}

	public String getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceName() {
		return this.referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Double getGoldAward() {
		return this.goldAward;
	}

	public void setGoldAward(Double goldAward) {
		this.goldAward = goldAward;
	}

	public Double getElectroniccurrency() {
		return this.electroniccurrency;
	}

	public void setElectroniccurrency(Double electroniccurrency) {
		this.electroniccurrency = electroniccurrency;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getActivationTime() {
		return this.activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public Integer getIsok() {
		return this.isok;
	}

	public void setIsok(Integer isok) {
		this.isok = isok;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getNote() {
		return note;
	}

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


}