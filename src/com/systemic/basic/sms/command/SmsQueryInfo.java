/**
 * Description:
 * History:  2013-6-17 Create
 **/

package com.systemic.basic.sms.command;


import org.springline.web.pagination.PaginationInfo;

/**
 * @description
 */
public class SmsQueryInfo extends PaginationInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1191751755697844352L;
	/** ������������ */
	private String smsType;
	/** �������� */
	private String smsContent;

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

}
