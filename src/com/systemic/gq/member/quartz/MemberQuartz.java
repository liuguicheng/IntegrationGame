package com.systemic.gq.member.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;

/**
 * ��ʱ����
 */
public class MemberQuartz {

	/**
	 * ��ѯ�����÷�� ����� 1.����ҹ涨ʱ��δ�μ���Ϸ 2.����������������δ�ڹ涨ʱ�� 3.��������ҹ涨ʱ�䲻����
	 * 4.��������ҹ涨ʱ������δ���
	 */
	public static void doRegisterApplyAudit() {
		String logContent = ",����δ�ڹ涨ʱ�����,Ŀǰ˫���˺��ѱ�����������";
		String logname = "";
		List<Member> overtimememberlist =upMemberList();
		if (overtimememberlist != null && !overtimememberlist.isEmpty()) {
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().update(member);

				// ��ȡ�Ƽ�����Ϣ
				Member regmember = ConsoleHelper.getInstance().getSpringMemberService()
						.selectMemberByStaffid(member.getReferenceId());
				if(!regmember.getMemberId().equals("1")){
					regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
					ConsoleHelper.getInstance().getSpringMemberService().update(regmember);
				}
				

				// �����־
				logname = "[�����˱��:" + member.getUserName() + "]-[����˱��:" + regmember.getUserName() + "]";
				logContent = logname + logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�˺Ű���������", member, logContent);
			}
		}
	}
	

	private static List<Member> queryRegistrOverTime(IntegrationGameRule rule) {
		// ��ҹ涨ʱ��δ ��˼�����Ϸʱ��
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
		// �������� ���������ʱ��
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
	 * ��ѯϵͳ����
	 * 
	 * @return
	 */
	public static IntegrationGameRule queryRule() {
		IntegrationGameRule rule = ConsoleHelper.getInstance().getIntegrationGameRuleService()
				.selectIntegrationGameRule();
		if (rule == null || rule.getRegisterLoginNameNum() == null || "".equals(rule.getRegisterLoginNameNum())) {
			throw new RuntimeException("ϵͳ������Ч");
		}
		return rule;
	}

}
