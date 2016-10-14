package com.console.entity;

import java.util.Date;

/**
 * SysOperateLog entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class OperateLog implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = -3642211806744109401L;

	public static final String LOG_TYPE_SYSTEM = "ϵͳ����";
	
	public static final String LOG_TYPE_RULE="�����޸�";
	public static final String LOG_TYPE_BONUSCONTENT_UPDATE  ="��Ȩ�����޸�";
	public static final String LOG_TYPE_BONUSCONTENT_DEL  ="��Ȩ����ɾ��";
	public static final String LOG_TYPE_STOCK_UPDATE  ="��Ȩ�����޸�";
	public static final String LOG_TYPE_STOCK_DEL  ="��Ȩ�����޸�";
	public static final String LOG_TYPE_UP_LOGINPASSWORD  ="��½�����޸�";
	public static final String LOG_TYPE_UP_SECONDPASSWORD  ="���������޸�";
	public static final String LOG_TYPE_UP_THRIDPASSWORD  ="���������޸�";
	public static final String LOG_TYPE_UP_MEMBERINFO  ="���������޸�";
	public static final String LOG_TYPE_ACTIVATION  ="��Ա����";
	public static final String LOG_TYPE_FROZEN="��Ա����";
	public static final String LOG_TYPE_THAW="��Ա������";
	/** ��־id. */
	private String id;
	/** ��־����. */
	private String logType;
	/** ������. */
	private String operatorId;
    /** ������. */
	private String operatorName;
	/** ����ʱ��. */
	private Date opDate;
	/** ��־����. */
	private String logContent;

	// Constructors

	/** default constructor */
	public OperateLog() {
	}

	/** full constructor */
	public OperateLog(String logType, String operator, Date opDate,
			String logContent) {
		this.logType = logType;
		this.operatorId = operator;
		this.opDate = opDate;
		this.logContent = logContent;
	}

	// Property accessors

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
	 * @return the logType
	 */
	public String getLogType() {
		return logType;
	}

	/**
	 * @param logType the logType to set
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**
	 * @return the operator
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperatorId(String operator) {
		this.operatorId = operator;
	}

	/**
	 * @return the opDate
	 */
	public Date getOpDate() {
		return opDate;
	}

	/**
	 * @param opDate the opDate to set
	 */
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
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
     * @param operatorName the operatorName to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

}