package com.systemic.basic.sms.service;

import java.util.List;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsEditInfo;
import com.systemic.basic.sms.command.SmsQueryInfo;

public interface ISmsService {
	/**
	 * ��ѯ���ж���������Ϣ
	 * 
	 * @return
	 */
	List<SysSmsType> selectAllSms(SmsQueryInfo info);

	/**
	 * ����Id��ȡ����������Ϣ
	 * 
	 * @param sysSmsId
	 * @return
	 */
	SysSmsType loadSmsById(String sysSmsId);

	/**
	 * �������������Ϣ
	 * 
	 * @param info
	 * @return
	 */
	SysSmsType saveSms(SmsEditInfo info);

	/**
	 * ����Idɾ������������Ϣ
	 * 
	 * @param ids
	 */
	void deleteSms(String[] ids);
	
	/**
	 * ͨ���������Ͳ�ѯ����������Ϣ
	 * 
	 * @param smsType
	 */
	List<SysSmsType> selectSmsTypeList(String smsType);
}
