package com.systemic.gq.pay.service;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.PayLog;
import com.systemic.gq.pay.command.PayLogQueryInfo;


public interface ISpringPayLogService {
	/**�����ֵ��¼
	 * @param info
	 */
	void savePayLog(String logContent,double money,String operationType,String memberId);
	
	/**��ҳ��ѯ��ֵ��¼
	 * @param info
	 * @return
	 */
	Page selectPayLog(PayLogQueryInfo info);
	
	/**����id��ȡһ����¼
	 * @param id
	 * @return
	 */
	PayLog loadPayLog(String id);
}
