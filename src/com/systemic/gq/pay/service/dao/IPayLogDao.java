package com.systemic.gq.pay.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.pay.command.PayLogQueryInfo;

public interface IPayLogDao extends ICommonDao {
	/**��ҳ��ѯ��ֵ��¼
	 * @param info
	 * @return
	 */
	Page selectPayLog(PayLogQueryInfo info);
}
