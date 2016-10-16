package com.systemic.gq.stock.service.dao.hibernate;

import java.util.List;

import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.stock.service.dao.IIntegrationGameRuleDao;

public class HinbernateIntegrationGameRuleDao extends HibernateCommonDao implements IIntegrationGameRuleDao {

	@Override
	public IntegrationGameRule selectIntegrationGameRule() {
		List ruleList = super.doQuery("from " + IntegrationGameRule.class.getName());
		if (ruleList.isEmpty()) {
			return null;
		}
		return (IntegrationGameRule) ruleList.get(0);
	}

}
