package com.systemic.gq.stock.service.dao;

import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.entity.IntegrationGameRule;

public interface IIntegrationGameRuleDao extends ICommonDao{

	public IntegrationGameRule selectIntegrationGameRule();
}
