package com.systemic.gq.entity;

import java.util.Date;


/**
 * PayLog entity. @author MyEclipse Persistence Tools
 */

public class PayLog  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -7390492481121699622L;
	// Fields    
	 /**��¼id*/
     private String recordId;
     /**��������*/
     private String operationType;
     
     /**����ʱ��*/
     private Date recordTime;
    
     /**������*/
     private String handleId;
     /**������ip*/
     private String handleLocalHost;
     /**��־����*/
     private String logContent;
     /**������� */
     private double payMoeny;

    // Constructors

    /** default constructor */
    public PayLog() {
    }

    
    /** full constructor */
    public PayLog(double payMoeny,String operationType, Date recordTime, String handleId, String handleLocalHost,String logContent) {
        this.payMoeny=payMoeny;
        this.operationType=operationType;
    	this.recordTime = recordTime;
        this.handleId = handleId;
        this.handleLocalHost = handleLocalHost;
        this.logContent = logContent;
    }

   

    public String getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }


	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}


	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}


	/**
	 * @return the recordTime
	 */
	public Date getRecordTime() {
		return recordTime;
	}


	/**
	 * @param recordTime the recordTime to set
	 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}


	/**
	 * @return the handleId
	 */
	public String getHandleId() {
		return handleId;
	}


	/**
	 * @param handleId the handleId to set
	 */
	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}


	/**
	 * @return the handleLocalHost
	 */
	public String getHandleLocalHost() {
		return handleLocalHost;
	}


	/**
	 * @param handleLocalHost the handleLocalHost to set
	 */
	public void setHandleLocalHost(String handleLocalHost) {
		this.handleLocalHost = handleLocalHost;
	}


	/**
	 * @return the logContent
	 */
	public String getLogContent() {
		return logContent;
	}


	/**
	 * @param logContent the logContent to set
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}


	/**
	 * @return the payMoeny
	 */
	public double getPayMoeny() {
		return payMoeny;
	}


	/**
	 * @param payMoeny the payMoeny to set
	 */
	public void setPayMoeny(double payMoeny) {
		this.payMoeny = payMoeny;
	}

   

}