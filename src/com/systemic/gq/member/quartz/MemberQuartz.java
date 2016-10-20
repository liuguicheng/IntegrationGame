package com.systemic.gq.member.quartz;

import java.util.ArrayList;
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
	 * 查询半永久封号 情况： 1.新玩家规定时间未参加游戏 2.新玩家申请加入申请未在规定时间 3.待升级玩家规定时间不升级
	 * 4.待升级玩家规定时间申请未审核
	 */
	public static void doRegisterApplyAudit() {
		String logContent = ",由于未在规定时间操作,目前双方账号已被半永久锁定";
		String logname = "";
		List<Member> overtimememberlist =upMemberList();
		if (overtimememberlist != null && !overtimememberlist.isEmpty()) {
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().update(member);

				// 获取推荐人信息
				Member regmember = ConsoleHelper.getInstance().getSpringMemberService()
						.selectMemberByStaffid(member.getReferenceId());
				if(!regmember.getMemberId().equals("1")){
					regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
					ConsoleHelper.getInstance().getSpringMemberService().update(regmember);
				}
				

				// 添加日志
				logname = "[申请人编号:" + member.getUserName() + "]-[审核人编号:" + regmember.getUserName() + "]";
				logContent = logname + logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("账号半永久锁定", member, logContent);
			}
		}
	}
	

	private static List<Member> queryRegistrOverTime(IntegrationGameRule rule) {
		// 玩家规定时间未 审核加入游戏时间
		int applynum = rule.getRegisterAuditTime();
		MemberInfo info = new MemberInfo();
		info.setCreateTime(new Date());
		info.setIsActivation(2);
		info.setUpgradeState(1);
		List<Member> overtimememberlist = ConsoleHelper.getInstance().getSpringMemberService()
				.selectMemberByAuditTime(info, applynum, 0);
		return overtimememberlist;
	}

	private static List<Member> queryGradeOverTime(IntegrationGameRule rule) {
		// 升级申请 及申请审核时间
		int timenum = rule.getUpgradeAuditTime();
		MemberInfo info = new MemberInfo();
		info.setUpgradeState(0);
		info.setIsActivation(0);
		info.setApplyUpgradeTime(new Date());
		List<Member> overtimememberlist = ConsoleHelper.getInstance().getSpringMemberService()
				.selectMemberByAuditTime(info, 0, timenum);
		return overtimememberlist;
	}

	private static List<Member> upMemberList(){
		List<Member> memberlist=new ArrayList<Member>();
		IntegrationGameRule rule = queryRule();
		List<Member> overtimememberlist = queryRegistrOverTime(rule);
		List<Member> gradeovertimememberlist = queryGradeOverTime(rule);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			memberlist.addAll(overtimememberlist);
		}
		if(gradeovertimememberlist!=null&&!gradeovertimememberlist.isEmpty()){
			memberlist.addAll(gradeovertimememberlist);
		}
		return memberlist;
	}
	/**
	 * 查询系统规则
	 * 
	 * @return
	 */
	public static IntegrationGameRule queryRule() {
		IntegrationGameRule rule = ConsoleHelper.getInstance().getIntegrationGameRuleService()
				.selectIntegrationGameRule();
		if (rule == null || rule.getRegisterLoginNameNum() == null || "".equals(rule.getRegisterLoginNameNum())) {
			throw new RuntimeException("系统设置无效");
		}
		return rule;
	}

}
