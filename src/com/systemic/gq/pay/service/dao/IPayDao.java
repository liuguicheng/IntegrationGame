package com.systemic.gq.pay.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.pay.command.PayQueryInfo;

public interface IPayDao extends ICommonDao {
	/**��ҳ��ѯ����*/
	Page selectPay(PayQueryInfo info);
}
