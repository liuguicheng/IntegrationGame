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
	/** 标识id */
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
	private String referenceQRCodeContent;//二维码内容（推广链接）
	private String referenceQRCodeImageUrl;//二维码图片地址
	/** 股权等级id */
	private Stock stock;
	/** 股权等级id */
	private String productgradeId;
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
	/** 激活时间 */
	private Date activationTime;
	/**是否激活*/
	private Integer isActivation;//0未激活  1 激活
	/** 是否正常 */
	private Integer isok;
	/** 删除标识 */
	private Integer isdel;
	/** 所在区域 */
	private String region;//默认0  -左边0 右边1  
	/***系统登录*/
	private String staffId;
	/***节点*/
	private String note;
	/** 节点 二维码*/
	private String noteQRCodeContent;//二维码内容（推广链接）
	private String noteQRCodeImageUrl;//二维码图片地址
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

}
