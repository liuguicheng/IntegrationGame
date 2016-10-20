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
	/**昵称 */
	private String bsid;
	/** 用户名 */
	private String userName;
	/** 密码 password */
	private String password;
	/** 二级密码 passwodTwo */
	private String passwodTwo;
	/** 三级密码*/
	private String passwordThree;
	/** 推荐人id */
	private String referenceId;
	/** 推荐人 */
	private String referenceName;
	/** 推荐人 二维码*/
	private String referenceQRCodeContent;//推荐人二维码内容（推广链接）
	private String referenceQRCodeImageUrl;//推荐人二维码图片地址
	/** 推荐人 廉通道*/
	private String referencelan;
	/** 推荐人 勇通道*/
	private String referenceyong;
	/** 推荐人 勇通道*/
	/** 股权等级id 这个项目不用*/
	private Stock stock;
	/** 股权等级id */
	private String productgradeId;
	/** 玩家等级id */
	private String levleId;
	/** 奖金币 */
	private Double goldAward;
	/** 电子币 */
	private Double electroniccurrency;
	/** 积分 */
	private int integral;
	/** 收到积分 */
	private int addIntegral;
	/** 送积分 */
	private int giveIntegral;
	/** 创建时间 */
	private Date createTime;
	/** 参与游戏时间 */
	private Date activationTime;
	/**申请参与游戏时间 */
	private Date applyTime;
	/**申请升级时间 */
	private Date applyUpgradeTime;
	/**已收到所需升级数量*/
	private Integer applyUpgradeNum;
	/**升级状态 */
	private Integer upgradeState; // 0不可升级 1 可升级 2申请中
	//升级审核时间
	private Date  auditGradeTime;
	//申请升级指定审核人
	private String auditGradeUserName;
	/**修改个人资料次数*/
	private Integer updateInfoNum;
	/**是否激活*/
	private Integer isActivation;//0未参与 1申请中 2参与 
	/** 是否正常 */
	private Integer isok; //1正常 0永久封号 2半永久封号
	/** 删除标识 */
	private Integer isdel; //0删除 1正常
	/** 所在区域 */
	private String region;//默认0  -左边0 右边1  
	/***系统登录*/
	private String staffId;
	/***节点*/
	private String note;
	//归属节点编号
	private String noteUsername;
	/** 节点 二维码*/
	private String noteQRCodeContent;//归属点二维码内容（推广链接）
	private String noteQRCodeImageUrl;//归属点二维码图片地址
	/** 归属点 廉通道*/
	private String notelan;
	/** 归属点 勇通道*/
	private String noteyong;
	//密保问题
	private String mbwt;
	//密保问题答案
	private String mbwtDn;
	//真实姓名
	private String zsxm;
	private String sfzhm;
	//联系电话
	private String lxdh;
	//联系地址
	private String lxdz;
	//电子邮箱
	private String email;
	//银行信息
	private String yhxx;
	//开户姓名
	private String khxm;
	//银行卡号
	private String yhkh;
	
	/**
	 * 二维码内容
	 */
	private String qRCodeContent;//二维码内容（推广链接）
	private String qRCodeImageUrl;//二维码图片地址
	private String lan;//廉通道
	private String yong;//勇通道
	
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

	/**
	 * @return the qRCodeContent
	 */
	public String getqRCodeContent() {
		return qRCodeContent;
	}

	/**
	 * @param qRCodeContent the qRCodeContent to set
	 */
	public void setqRCodeContent(String qRCodeContent) {
		this.qRCodeContent = qRCodeContent;
	}

	/**
	 * @return the qRCodeImageUrl
	 */
	public String getqRCodeImageUrl() {
		return qRCodeImageUrl;
	}

	/**
	 * @param qRCodeImageUrl the qRCodeImageUrl to set
	 */
	public void setqRCodeImageUrl(String qRCodeImageUrl) {
		this.qRCodeImageUrl = qRCodeImageUrl;
	}

	/**
	 * @return the referenceQRCodeContent
	 */
	public String getReferenceQRCodeContent() {
		return referenceQRCodeContent;
	}

	/**
	 * @param referenceQRCodeContent the referenceQRCodeContent to set
	 */
	public void setReferenceQRCodeContent(String referenceQRCodeContent) {
		this.referenceQRCodeContent = referenceQRCodeContent;
	}

	/**
	 * @return the referenceQRCodeImageUrl
	 */
	public String getReferenceQRCodeImageUrl() {
		return referenceQRCodeImageUrl;
	}

	/**
	 * @param referenceQRCodeImageUrl the referenceQRCodeImageUrl to set
	 */
	public void setReferenceQRCodeImageUrl(String referenceQRCodeImageUrl) {
		this.referenceQRCodeImageUrl = referenceQRCodeImageUrl;
	}

	/**
	 * @return the noteQRCodeContent
	 */
	public String getNoteQRCodeContent() {
		return noteQRCodeContent;
	}

	/**
	 * @param noteQRCodeContent the noteQRCodeContent to set
	 */
	public void setNoteQRCodeContent(String noteQRCodeContent) {
		this.noteQRCodeContent = noteQRCodeContent;
	}

	/**
	 * @return the noteQRCodeImageUrl
	 */
	public String getNoteQRCodeImageUrl() {
		return noteQRCodeImageUrl;
	}

	/**
	 * @param noteQRCodeImageUrl the noteQRCodeImageUrl to set
	 */
	public void setNoteQRCodeImageUrl(String noteQRCodeImageUrl) {
		this.noteQRCodeImageUrl = noteQRCodeImageUrl;
	}

	/**
	 * @return the integral
	 */
	public int getIntegral() {
		return integral;
	}

	/**
	 * @param integral the integral to set
	 */
	public void setIntegral(int integral) {
		this.integral = integral;
	}

	/**
	 * @return the addIntegral
	 */
	public int getAddIntegral() {
		return addIntegral;
	}

	/**
	 * @param addIntegral the addIntegral to set
	 */
	public void setAddIntegral(int addIntegral) {
		this.addIntegral = addIntegral;
	}

	/**
	 * @return the giveIntegral
	 */
	public int getGiveIntegral() {
		return giveIntegral;
	}

	/**
	 * @param giveIntegral the giveIntegral to set
	 */
	public void setGiveIntegral(int giveIntegral) {
		this.giveIntegral = giveIntegral;
	}

	/**
	 * @return the lan
	 */
	public String getLan() {
		return lan;
	}

	/**
	 * @param lan the lan to set
	 */
	public void setLan(String lan) {
		this.lan = lan;
	}

	/**
	 * @return the yong
	 */
	public String getYong() {
		return yong;
	}

	/**
	 * @param yong the yong to set
	 */
	public void setYong(String yong) {
		this.yong = yong;
	}

	/**
	 * @return the levleId
	 */
	public String getLevleId() {
		return levleId;
	}

	/**
	 * @param levleId the levleId to set
	 */
	public void setLevleId(String levleId) {
		this.levleId = levleId;
	}

	/**
	 * @return the referencelan
	 */
	public String getReferencelan() {
		return referencelan;
	}

	/**
	 * @param referencelan the referencelan to set
	 */
	public void setReferencelan(String referencelan) {
		this.referencelan = referencelan;
	}

	/**
	 * @return the referenceyong
	 */
	public String getReferenceyong() {
		return referenceyong;
	}

	/**
	 * @param referenceyong the referenceyong to set
	 */
	public void setReferenceyong(String referenceyong) {
		this.referenceyong = referenceyong;
	}

	/**
	 * @return the notelan
	 */
	public String getNotelan() {
		return notelan;
	}

	/**
	 * @param notelan the notelan to set
	 */
	public void setNotelan(String notelan) {
		this.notelan = notelan;
	}

	/**
	 * @return the noteyong
	 */
	public String getNoteyong() {
		return noteyong;
	}

	/**
	 * @param noteyong the noteyong to set
	 */
	public void setNoteyong(String noteyong) {
		this.noteyong = noteyong;
	}

	/**
	 * @return the noteUsername
	 */
	public String getNoteUsername() {
		return noteUsername;
	}

	/**
	 * @param noteUsername the noteUsername to set
	 */
	public void setNoteUsername(String noteUsername) {
		this.noteUsername = noteUsername;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyUpgradeTime() {
		return applyUpgradeTime;
	}

	public void setApplyUpgradeTime(Date applyUpgradeTime) {
		this.applyUpgradeTime = applyUpgradeTime;
	}

	public Integer getApplyUpgradeNum() {
		return applyUpgradeNum;
	}

	public void setApplyUpgradeNum(Integer applyUpgradeNum) {
		this.applyUpgradeNum = applyUpgradeNum;
	}

	public Integer getUpdateInfoNum() {
		return updateInfoNum;
	}

	public void setUpdateInfoNum(Integer updateInfoNum) {
		this.updateInfoNum = updateInfoNum;
	}
	
	/**
	 * @return the upgradeState
	 */
	public Integer getUpgradeState() {
		return upgradeState;
	}

	/**
	 * @param upgradeState the upgradeState to set
	 */
	public void setUpgradeState(Integer upgradeState) {
		this.upgradeState = upgradeState;
	}

	/**
	 * @return the auditGradeTime
	 */
	public Date getAuditGradeTime() {
		return auditGradeTime;
	}

	/**
	 * @param auditGradeTime the auditGradeTime to set
	 */
	public void setAuditGradeTime(Date auditGradeTime) {
		this.auditGradeTime = auditGradeTime;
	}

	/**
	 * @return the auditGradeUserName
	 */
	public String getAuditGradeUserName() {
		return auditGradeUserName;
	}

	/**
	 * @param auditGradeUserName the auditGradeUserName to set
	 */
	public void setAuditGradeUserName(String auditGradeUserName) {
		this.auditGradeUserName = auditGradeUserName;
	}
	
	
}