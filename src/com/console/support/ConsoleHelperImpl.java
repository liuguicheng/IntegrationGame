/**
 * Description:
 * History:  2009-7-30 Create
 **/

package com.console.support;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;

import com.console.ConsoleHelper;
import com.console.backup.service.IDBBackupService;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.main.service.IMainService;
import com.console.manage.service.IManageService;
import com.console.operate.service.IOperateService;
import com.console.operatelog.service.IOperateLogService;
import com.console.power.service.IPowerService;
import com.console.role.service.IRoleService;
import com.plugins.msg.service.IImagesFileService;
import com.plugins.msg.service.IMsgService;
import com.systemic.gq.bonus.service.IBonusRecordServcie;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.gq.pay.service.ISpringPayLogService;
import com.systemic.gq.stock.service.IBonusContentService;
import com.systemic.gq.stock.service.IIntegrationGameRuleService;
import com.systemic.gq.stock.service.ILevelService;
import com.systemic.gq.stock.service.IRuleService;
import com.systemic.gq.stock.service.IStockService;

/**
 * @description
 */
public class ConsoleHelperImpl extends ConsoleHelper implements
		InitializingBean {

	/**
	 * 获取 IManageService 实例， IOC注入
	 */
	private IManageService manageService;
	/**
	 * 获取 IManageService 实例， IOC注入
	 */
	private IRoleService roleService;
	/**
	 * 获取 IManageService 实例， IOC注入
	 */
	private IPowerService powerService;
	/**
	 * 获取 IOperateService 实例， IOC注入
	 */
	private IOperateService operateService;

	/**
	 * 获取 IOperateLogService 实例， IOC注入
	 */
	private IOperateLogService logService;
	/**
	 * 获取 IMainService 实例， IOC注入
	 */
	private IMainService mainService;
	/**
	 * 获取 ISpringPayLogService 实例， IOC注入
	 */
	private ISpringPayLogService springPayLogService;
	/**
	 * 获取 ISpringMemberService 实例， IOC注入
	 */
	private ISpringMemberService springMemberService;
	
	/**
	 * 获取IBonusContentService
	 */
	private IBonusContentService bonuscontentService;
	/**
	 * 获取IBonusRecordServcie
	 */
	private IBonusRecordServcie bonusRecordService;
	
	private IRuleService ruleService;
	
	private IIntegrationGameRuleService integrationGameRuleService;
	private ILevelService ilevelService;
	private IStockService stockService;
	private IMsgService msgService;
	private IDBBackupService backupService;
	private IImagesFileService imagesfileService;
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		super.setInstance(this);
        CacheHelper.getInstance().registerConnector(Role.DIC_ROLE, 
                (ICacheConnector) roleService);
        CacheHelper.getInstance().registerConnector(Power.TREE_DIC_IDENTIFICATION, 
                (ICacheConnector) powerService);   
        CacheHelper.getInstance().registerConnector(Department.SIMPLE_DIC_IDENTIFICATION, 
                (ICacheConnector) manageService);
        CacheHelper.getInstance().registerConnector(Department.TREE_DIC_IDENTIFICATION, 
                (ICacheConnector) manageService);
        CacheHelper.getInstance().registerConnector(Staff.SIMPLE_DIC_IDENTIFICATION, 
                (ICacheConnector) manageService);
        CacheHelper.getInstance().registerConnector(Member.SIMPLE_DIC_IDENTIFICATION, 
                (ICacheConnector) manageService);
        CacheHelper.getInstance().registerConnector(Stock.SIMPLE_DIC_IDENTIFICATION, 
                (ICacheConnector) manageService);
	}

	/**
	 * @param systemManager 子类注册
	 */
	public void setManageService(IManageService systemManager) {
		this.manageService = systemManager;
	}

	/**
	 * @return 获取接口
	 */
	public IManageService getManageService() {
		if (manageService == null) {
			throw new RuntimeException("ConsoleHeler无法提供IManagerService接口！");
		}
		return manageService;
	}

	/**
	 * @return the roleService
	 */
	public IRoleService getRoleService() {
		if (roleService == null) {
			throw new RuntimeException("ConsoleHeler无法提供IRoleService接口！");
		}
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the powerService
	 */
	public IPowerService getPowerService() {
		if (powerService == null) {
			throw new RuntimeException("ConsoleHeler无法提供IPowerService接口！");
		}
		return powerService;
	}

	/**
	 * @param powerService the powerService to set
	 */
	public void setPowerService(IPowerService powerService) {
		this.powerService = powerService;
	}

	/**
	 * @return the operateService
	 */
	public IOperateService getOperateService() {
		if (operateService == null) {
			throw new RuntimeException("ConsoleHeler  无法提供IOperateService接口！");
		}

		return operateService;
	}

	// /**
	// * @see com.console.ConsoleHelper#getObservable()
	// */
	// @Override
	// public Observable getObservable() {
	// if (co == null) {
	// co = new ConsoleObservable();
	// }
	//
	// return co;
	// }

	/**
	 * @param operateService the operateService to set
	 */
	public void setOperateService(IOperateService operateService) {
		this.operateService = operateService;
	}

	/**
	 * @return the logService
	 */
	public IOperateLogService getLogService() {
		if (logService == null) {
			throw new RuntimeException("ConsoleHeler无法提供IOperateLogService接口！");
		}
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(IOperateLogService logService) {
		this.logService = logService;
	}

	@Override
	public IMainService getMainService() {
		if (mainService == null) {
			throw new RuntimeException("ConsoleHeler无法提供IMainService接口！");
		}
		return mainService;
	}

	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}

	public ISpringPayLogService getSpringPayLogService() {
		if (springPayLogService == null) {
			throw new RuntimeException("ConsoleHeler无法提供ISpringPayLogService接口！");
		}
		return springPayLogService;
	}

	public void setSpringPayLogService(ISpringPayLogService springPayLogService) {
		this.springPayLogService = springPayLogService;
	}

	public ISpringMemberService getSpringMemberService() {
		if (springMemberService == null) {
			throw new RuntimeException("ConsoleHeler无法提供springMemberService接口！");
		}
		return springMemberService;
	}

	public void setSpringMemberService(ISpringMemberService springMemberService) {
		this.springMemberService = springMemberService;
	}

	/**
	 * @return the bonuscontentService
	 */
	public IBonusContentService getBonuscontentService() {
		if (bonuscontentService == null) {
			throw new RuntimeException("ConsoleHeler无法提供bonuscontentService接口！");
		}
		return bonuscontentService;
	}

	/**
	 * @param bonuscontentService the bonuscontentService to set
	 */
	public void setBonuscontentService(IBonusContentService bonuscontentService) {
		this.bonuscontentService = bonuscontentService;
	}

	/**
	 * @return the bonusRecordService
	 */
	public IBonusRecordServcie getBonusRecordService() {
		if (bonusRecordService == null) {
			throw new RuntimeException("ConsoleHeler无法提供bonusRecordService接口！");
		}
		return bonusRecordService;
	}

	/**
	 * @param bonusRecordService the bonusRecordService to set
	 */
	public void setBonusRecordService(IBonusRecordServcie bonusRecordService) {
		this.bonusRecordService = bonusRecordService;
	}

	@Override
	public IRuleService getRuleService() {
		if(ruleService==null){
			throw new RuntimeException("ConsoleHeler无法提供ruleService接口！");
		}
		return ruleService;
	}

	/**
	 * @param ruleService the ruleService to set
	 */
	public void setRuleService(IRuleService ruleService) {
		this.ruleService = ruleService;
	}

	@Override
	public IIntegrationGameRuleService getIntegrationGameRuleService() {
		// TODO Auto-generated method stub
		if(integrationGameRuleService==null){
			throw new RuntimeException("ConsoleHeler无法提供integrationGameRuleService接口！");
		}
		return integrationGameRuleService;
	}

	/**
	 * @param integrationGameRuleService the integrationGameRuleService to set
	 */
	public void setIntegrationGameRuleService(IIntegrationGameRuleService integrationGameRuleService) {
		this.integrationGameRuleService = integrationGameRuleService;
	}


	/**
	 * @return the ilevelService
	 */
	public ILevelService getIlevelService() {
		if(ilevelService==null){
			throw new RuntimeException("ConsoleHeler无法提供ilevelService接口！");
		}
		return ilevelService;
	}

	/**
	 * @param ilevelService the ilevelService to set
	 */
	public void setIlevelService(ILevelService ilevelService) {
		this.ilevelService = ilevelService;
	}

	/**
	 * @return the stockService
	 */
	public IStockService getStockService() {
		if(stockService==null){
			throw new RuntimeException("ConsoleHeler无法提供stockService接口！");
		}
		return stockService;
	}

	/**
	 * @param stockService the stockService to set
	 */
	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}
	
	public IMsgService getMsgService() {
		if(msgService==null){
			throw new RuntimeException("ConsoleHeler无法提供msgService接口！");
		}
		return msgService;
	}

	public void setMsgService(IMsgService msgService) {
		this.msgService = msgService;
	}

	/**
	 * @return the backupService
	 */
	public IDBBackupService getBackupService() {
		if(backupService==null){
			throw new RuntimeException("ConsoleHeler无法提供backupService接口！");
		}
		return backupService;
	}

	/**
	 * @param backupService the backupService to set
	 */
	public void setBackupService(IDBBackupService backupService) {
		this.backupService = backupService;
	}

	/**
	 * @return the imagesfileService
	 */
	public IImagesFileService getImagesfileService() {
		if(imagesfileService==null){
			throw new RuntimeException("ConsoleHeler无法提供imagesfileService接口！");
		}
		return imagesfileService;
	}

	/**
	 * @param imagesfileService the imagesfileService to set
	 */
	public void setImagesfileService(IImagesFileService imagesfileService) {
		this.imagesfileService = imagesfileService;
	}
	
}
