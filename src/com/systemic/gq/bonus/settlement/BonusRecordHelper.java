package com.systemic.gq.bonus.settlement;

import java.util.Date;

import com.console.ConsoleHelper;
import com.systemic.gq.bonus.command.BonusRecordEdit;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Level;
import com.systemic.gq.entity.Member;

/**
 * 计算积分公用类
 */
public class BonusRecordHelper {

	/**
	 * 计算推荐积分 和归属积分 规则 ： 直接推荐人 获得一个* 归属点 获得一个* * 由当前用户等级 决定 目前默认v1 参数：被推荐用户
	 */
	public static void doComputationalIntegral(Member member) {
		if (member.getLevleId() != null && !"".equals(member.getLevleId())) {
			// 注册用户等级
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			// 日志内容：获得原因
			String log = "";
			// 推荐人用户信息
			String refusername = member.getReferenceName();
			if (refusername != null && !"".equals(refusername)) {
				log = "推荐并审核了编号为:" + member.getUserName() + "玩家";
				Member ms=addBonusRecord(level,refusername, log);
				//修改用户所需升级数量
				updateMemberForUpgradeNum(ms,level.getId());
			}
			// 归属点用户信息
			String nodeusername = member.getNoteUsername();
			//当归属点与推荐人 节点相同，只算一次积分
			if (!refusername.equals(nodeusername)) {
				if (nodeusername != null && !"".equals(nodeusername)) {
					log = "是玩家编号:" + member.getUserName() + "归属点";
					Member m=addBonusRecord(level,nodeusername, log);
					//修改用户所需升级数量
					updateMemberForUpgradeNum(m,level.getId());
				}
			}

		}
	}
	private static Member addBonusRecord(Level receivedLevel,String username, String log) {
		//获得积分用户
		Member member = ConsoleHelper.getInstance().getSpringMemberService().selectMemberByUserName(username);

		String remark = "用户编号:" + member.getUserName() + " 获得" + receivedLevel.getV1_bonus_num() + "个" + receivedLevel.getV1_type()
				+ "<br/>" + "获得原因:" + log;
		// 封装 积分记录实体
		BonusRecordEdit bonusrecordedit = new BonusRecordEdit(member.getUserName(), member.getBsid(), new Date(),
				receivedLevel.getV1_zdtype(), remark, 1, receivedLevel.getV1_bonus_num(), receivedLevel.getV1_type());
		// 添加积分记录
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bonusrecordedit);
		
		return member;
	}

	/**
	 * 修改用户所需升级数量
	 * 
	 * @param member
	 *            获得人品积分玩家信息
	 * @param receivedLevelid
	 *            收到积分种类 id
	 */
	private static void updateMemberForUpgradeNum(Member member, String receivedLevelid) {
		// 获得人品积分种类与所需升级人品积分种类一致
		if (member.getLevleId().equals(receivedLevelid)) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			member.getLevleId();
			int num = member.getApplyUpgradeNum();
			member.setApplyUpgradeNum(num + 1);
			// 如果到了可以升级时候，修改用户状态为 可升级
			if (level.getV1_upgrade_num() == member.getApplyUpgradeNum()) {
				member.setUpgradeState(1);
			}
			ConsoleHelper.getInstance().getSpringMemberService().updateMermberInfo(member);
		}
	}
	
	

	/**
	 * 计算升级获得积分
	 *  String username：获得积分用户编号
	 *  String gradeusername:升级用户编号
	 *  Level level:当前等级
	 */
	public static void doComputationalIntegralforApply(String gradeusername,String username,Level level) {
		String levelt=level.getV1_zdtype();
		int newlevelt=Integer.parseInt(levelt)+1;
		Level newlevel= ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(String.valueOf(newlevelt));
		String log="收到玩家:"+gradeusername+"升级送出的积分";
		Member m=addBonusRecord(newlevel,username,log);
		//修改用户所需升级数量
		updateMemberForUpgradeNum(m,newlevel.getId());
	}
}
