package com.systemic.gq.member.quartz;

import java.util.Date;
import java.util.List;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;


public class MemberQuartz {
	
	/**
	 * �����صĶ�ʱ����
	 */
	
	/**
	 * ������������Ϸ��˳�ʱ�û�
	 * ״̬�޸�Ϊ�����÷��
	 */
	public static void doRegisterApplyAudit(){
		IntegrationGameRule rule=queryRule();
		int timenum=rule.getRegisterAuditTime();
		MemberInfo info=new MemberInfo();
		info.setApplyTime(new Date());
		List<Member> overtimememberlist= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByAuditTime(info,timenum);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
			}
		}
	}
	
	
	public static IntegrationGameRule queryRule() {
		IntegrationGameRule rule = ConsoleHelper.getInstance().getIntegrationGameRuleService()
				.selectIntegrationGameRule();
		if (rule == null || rule.getRegisterLoginNameNum() == null || "".equals(rule.getRegisterLoginNameNum())) {
			throw new RuntimeException("ϵͳ������Ч");
		}
		return rule;
	}
}
