package com.systemic.gq.pay.service.spring;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;
import com.systemic.gq.pay.service.ISpringPayService;
import com.systemic.gq.pay.service.dao.IPayDao;

public class SpringPayService implements ISpringPayService {
	private IPayDao payDao;

	public void setPayDao(IPayDao payDao) {
		this.payDao = payDao;
	}

	@Override
	public Page selectPay(PayQueryInfo info) {
		return this.payDao.selectPay(info);
	}

	/* (non-Javadoc) ����b
	 * @see com.systemic.gq.pay.service.ISpringPayService#savePay(com.systemic.gq.pay.command.PayInfoEdit)
	 */
	@Override
	public void savePay(PayInfoEdit info,Member member) {
		PayInfo pi = null;
		if (StringUtils.isNotBlank(info.getNumberId())) {
			pi = this.selectPay(info.getNumberId());
		} else {
			pi = new PayInfo();
			BeanUtils.copyProperties(info, pi, new String[] { "numberId" });
			pi.setApplyTime(new Date());
			
		}
		pi.setApplyState(info.getApplyState());
		pi.setMemberId(member.getMemberId());
		pi.setMemnberStaffId(member.getStaffId());
		//��Ҽ���
		Double money = member.getElectroniccurrency();
		
		Double afterMoney = null;
		if(money == null){
			afterMoney = info.getApplyRecordNum()+0.00;
			pi.setPayBeforeNum(0.00);//��ֵǰ�ĵ��ӽ��
		}else{
			pi.setPayBeforeNum(money);//��ֵǰ�ĵ��ӽ��
			afterMoney = money+info.getApplyRecordNum();
		}
		
		pi.setPayAfterNum(afterMoney);//��ֵ��Ľ��
		pi.setApplyLocalHost(ConsoleHelper.getUserIp());
		this.payDao.save(pi);
		
	}

	@Override
	public PayInfo selectPay(String id) {
		return (PayInfo) this.payDao.load(PayInfo.class, id);
	}

	@Override
	public void updatePayInfo(PayInfo payinfo) {
		this.payDao.update(payinfo);
		
	}

}
