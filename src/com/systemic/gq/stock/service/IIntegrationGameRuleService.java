package com.systemic.gq.stock.service;

import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.stock.command.IntegrationGameRuleEdit;

public interface IIntegrationGameRuleService {

	public IntegrationGameRule selectIntegrationGameRule();

	public void insert(IntegrationGameRuleEdit ruleedit);
}
