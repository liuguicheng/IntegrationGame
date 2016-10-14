package com.systemic.basic.sms;

import java.util.Observable;


public abstract class SmsManageHelper extends Observable{
	
	/**
     * ��ʵ�����๤�� Spring IOC����
     */
	private static SmsManageHelper instance = null;
	
	
	public static void setInstance(SmsManageHelper instance) {
		SmsManageHelper.instance = instance;
	}
	
	/**
	 * ����SmsManagehHelper��ʵ������
	 */
	public static SmsManageHelper getInstance(){
		if (instance==null){
			throw new RuntimeException("δ����WebsiteHelper��ʵ������");
		}
		return instance;
	}
	/**
	 * ���뷢�����ݡ��ֻ�����
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract void smsClientSupport(String content,String mobileNum);
	
	/**
	 * ���뷢����Ϣ���͡��ֻ�����
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract String smsSubClientSupport(String smsType);
	
	/**
	 * ���뷢����Ϣ���͡��ֻ�����
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract String smsSendClientSupport(String smsType,String mobileNum);

}
