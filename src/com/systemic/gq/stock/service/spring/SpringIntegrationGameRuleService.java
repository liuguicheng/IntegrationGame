package com.systemic.gq.stock.service.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.stock.command.IntegrationGameRuleEdit;
import com.systemic.gq.stock.service.IIntegrationGameRuleService;
import com.systemic.gq.stock.service.dao.IIntegrationGameRuleDao;

public class SpringIntegrationGameRuleService implements IIntegrationGameRuleService {

	IIntegrationGameRuleDao  iIntegrationGameRuleDao;

	public void setiIntegrationGameRuleDao(IIntegrationGameRuleDao iIntegrationGameRuleDao) {
		this.iIntegrationGameRuleDao = iIntegrationGameRuleDao;
	}


	@Override
	public void insert(IntegrationGameRuleEdit ruleedit) {
		IntegrationGameRule rule=new IntegrationGameRule();
		if(StringUtils.isNotBlank(ruleedit.getId())){
			BeanUtils.copyProperties(ruleedit,rule);
		}else{
			BeanUtils.copyProperties(ruleedit,rule,new String[]{"id"});
		}
		iIntegrationGameRuleDao.save(rule);
	}





	@Override
	public IntegrationGameRule selectIntegrationGameRule() {
		return iIntegrationGameRuleDao.selectIntegrationGameRule();
	}

}
