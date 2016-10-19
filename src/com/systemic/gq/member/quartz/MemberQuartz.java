package com.systemic.gq.member.quartz;

import java.util.Date;
import java.util.List;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;

/**
 * 定时任务
 */
public class MemberQuartz {

	
	/**
	 * 检查申请加入游戏审核超时用户
	 * 状态修改为半永久封号
	 */
	public static void doRegisterApplyAudit(){
		IntegrationGameRule rule=queryRule();
		int applynum=rule.getRegisterAuditTime();
		int timenum=rule.getUpgradeAuditTime();
		MemberInfo info=new MemberInfo();
		info.setApplyTime(new Date());
		info.setCreateTime(new Date());
		String logContent=",由于未在规定时间操作,目前双方账号已被半永久锁定";
		String logname="";
		List<Member> overtimememberlist= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByAuditTime(info,applynum,timenum);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
				
				//获取推荐人信息
				Member regmember=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(member.getReferenceId());
				regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(regmember);
				
				//添加日志
				logname="[申请人编号:"+member.getUserName()+"]-[审核人编号:"+regmember.getUserName()+"]";
				logContent=logname+logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("账号半永久锁定",
						member, logContent);
			}
		}
	}
	
	
	public static IntegrationGameRule queryRule() {
		IntegrationGameRule rule = ConsoleHelper.getInstance().getIntegrationGameRuleService()
				.selectIntegrationGameRule();
		if (rule == null || rule.getRegisterLoginNameNum() == null || "".equals(rule.getRegisterLoginNameNum())) {
			throw new RuntimeException("系统设置无效");
		}
		return rule;
	}


	/**
	 * 检测注册超过规定时间未申请参加游戏玩家-》封号
	 */
	public static void doRegister() {
		IntegrationGameRule rule=queryRule();
		int applynum=rule.getRegisterAuditTime();
		MemberInfo info=new MemberInfo();
		info.setCreateTime(new Date());
		String logContent=",由于未在规定申请参与游戏,目前双方账号已被半永久锁定";
		String logname="";
		List<Member> overtimememberlist= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByAuditTime(info,applynum,0);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
				
				//获取推荐人信息
				Member regmember=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(member.getReferenceId());
				regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(regmember);
				
				//添加日志
				logname="[申请人编号:"+member.getUserName()+"]-[审核人编号:"+regmember.getUserName()+"]";
				logContent=logname+logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("账号半永久锁定",
						member, logContent);
			}
		}
	}
}
