/**
 * Description:
 * History:  2013-6-13 Create
 **/

package com.systemic.basic.sms.command;

import org.springline.web.mvc.SpringlineCommand;

/**
 * @description
 */
public class SmsEditInfo extends SpringlineCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3551004172347521384L;
	/** ��������id */
	private String smsId;
	/** ������������ */
	private String smsType;
	/** �������� */
	private String smsContent;
	/**��ע*/
	private String remark;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
