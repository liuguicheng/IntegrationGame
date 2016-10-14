package com.systemic.gq.pay.service;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;

public interface ISpringPayService {
	/**��ҳ��ѯ����*/
	Page selectPay(PayQueryInfo info);
	/**��������*/
	void savePay(PayInfoEdit info,Member member);
	/**����id��ȡһ����������*/
	PayInfo selectPay(String id);
	void updatePayInfo(PayInfo payinfo);
}
