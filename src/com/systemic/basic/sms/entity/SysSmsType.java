package com.systemic.basic.sms.entity;

/**
 * SysSmsType entity. @author MyEclipse Persistence Tools
 */
public class SysSmsType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656493665934120280L;
	/**
	 * �������������˻�����
	 */
	public static final String CASE_INSPECTION_TYPE = "00";
	/**
	 * ��������а��������
	 */
	public static final String CASE_CHENGBAN_TYPE = "01";
	/**
	 * ��������а��������
	 */
	public static final String CASE_SHENGHE_TYPE = "02";
	/**
	 * ���������׼��������
	 */
	public static final String CASE_PIZHUN_TYPE = "03";
	/**
	 * �����������������
	 */
	public static final String CASE_BANJIE_TYPE = "04";
	
	/**
	 * Эͬ�����Ŀ�Ǽǰ���������
	 */
	public static final String UNIT_FINISHED_TYPE = "05";
	
	/**
	 * ��ҵ����Эͬ�������������
	 */
	public static final String INSTURATION_FINISHED_TYPE = "06";
	
	/** �����ֵ� */
	public static final String DIC_SMS_CONTENT = "dicMoibleSmsContent";
	// Fields
	private String smsId;
	private String smsType;
	private String smsContent;
	private String remark;

	// Constructors

	/** default constructor */
	public SysSmsType() {
	}

	/** minimal constructor */
	public SysSmsType(String smsId) {
		this.smsId = smsId;
	}

	/** full constructor */
	public SysSmsType(String smsId, String smsType, String smsContent,
			String remark) {
		this.smsId = smsId;
		this.smsType = smsType;
		this.smsContent = smsContent;
		this.remark = remark;
	}

	// Property accessors

	public String getSmsId() {
		return this.smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
