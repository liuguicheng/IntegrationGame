package com.systemic.basic.sms.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsQueryInfo;

public interface ISmsDao extends ICommonDao {
	/**
	 * ��ѯ���ж���������Ϣ
	 * 
	 * @return
	 */
	List<SysSmsType> selectAllSms(SmsQueryInfo info);

}
