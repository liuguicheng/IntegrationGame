package com.console.operatelog.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class OperateLogEditInfo extends SpringlineCommand{

	/**
	 *
	 */
	private static final long serialVersionUID = -1321784829376602752L;

	/** ��־id. */
	private String id;
	/** ��־����. */
	private String logType;
	/** ������. */
	private String operator;
	/** ����ʱ��. */
	private Date opDate;
	/** ��־����. */
	private String logContent;

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
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
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
}
