package com.systemic.gq.entity;

import java.util.Date;


/**
 * ���ֹ���
 */

public class Withdrawals  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1018766036940509657L;
	private String WId;//id
     private String memberstaffid;//�����˱��
     private String memberUsername;//����������
     private String WAccountType;//�������ֱ���
     private Double WMoney;//������
     private Integer WCounterfree;//�����ѱ���
     private Double actualMoeny;//ʵ�ʽ��
     private String bank;//��������
     private String bankName;//�����˻�
     private String bankUsername;//��������
     private String applyLocalHost;//������ip
     private Date applyTime;//����ʱ��
     private String status;//���
     private String handleId;//�����id
     private String auditIp;//�����ip
     private Date auditTime;//���ʱ��
     private String remark;//��ע


    // Constructors

    /** default constructor */
    public Withdrawals() {
    }

    
    /** full constructor */
    public Withdrawals(String memberstaffid, String memberUsername, String WAccountType, Double WMoney, Integer WCounterfree, Double actualMoeny, String bank, String bankName, String bankUsername, String applyLocalHost, Date applyTime, String status, String handleId, String auditIp, Date auditTime, String remark) {
        this.memberstaffid = memberstaffid;
        this.memberUsername = memberUsername;
        this.WAccountType = WAccountType;
        this.WMoney = WMoney;
        this.WCounterfree = WCounterfree;
        this.actualMoeny = actualMoeny;
        this.bank = bank;
        this.bankName = bankName;
        this.bankUsername = bankUsername;
        this.applyLocalHost = applyLocalHost;
        this.applyTime = applyTime;
        this.status = status;
        this.handleId = handleId;
        this.auditIp = auditIp;
        this.auditTime = auditTime;
        this.remark = remark;
    }

   
    // Property accessors

    public String getWId() {
        return this.WId;
    }
    
    public void setWId(String WId) {
        this.WId = WId;
    }

    public String getMemberstaffid() {
        return this.memberstaffid;
    }
    
    public void setMemberstaffid(String memberstaffid) {
        this.memberstaffid = memberstaffid;
    }

    public String getMemberUsername() {
        return this.memberUsername;
    }
    
    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getWAccountType() {
        return this.WAccountType;
    }
    
    public void setWAccountType(String WAccountType) {
        this.WAccountType = WAccountType;
    }

    public Double getWMoney() {
        return this.WMoney;
    }
    
    public void setWMoney(Double WMoney) {
        this.WMoney = WMoney;
    }

    public Integer getWCounterfree() {
        return this.WCounterfree;
    }
    
    public void setWCounterfree(Integer WCounterfree) {
        this.WCounterfree = WCounterfree;
    }

    public Double getActualMoeny() {
        return this.actualMoeny;
    }
    
    public void setActualMoeny(Double actualMoeny) {
        this.actualMoeny = actualMoeny;
    }

    public String getBank() {
        return this.bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUsername() {
        return this.bankUsername;
    }
    
    public void setBankUsername(String bankUsername) {
        this.bankUsername = bankUsername;
    }

    public String getApplyLocalHost() {
        return this.applyLocalHost;
    }
    
    public void setApplyLocalHost(String applyLocalHost) {
        this.applyLocalHost = applyLocalHost;
    }

    public Date getApplyTime() {
        return this.applyTime;
    }
    
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandleId() {
        return this.handleId;
    }
    
    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getAuditIp() {
        return this.auditIp;
    }
    
    public void setAuditIp(String auditIp) {
        this.auditIp = auditIp;
    }

    public Date getAuditTime() {
        return this.auditTime;
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
   








}