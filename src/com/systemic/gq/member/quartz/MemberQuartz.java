package com.systemic.gq.member.quartz;

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
	 * ������������Ϸ��˳�ʱ�û�
	 * ״̬�޸�Ϊ�����÷��
	 */
	public static void doRegisterApplyAudit(){
		IntegrationGameRule rule=queryRule();
		int applynum=rule.getRegisterAuditTime();
		int timenum=rule.getUpgradeAuditTime();
		MemberInfo info=new MemberInfo();
		info.setApplyTime(new Date());
		info.setCreateTime(new Date());
		String logContent=",����δ�ڹ涨ʱ�����,Ŀǰ˫���˺��ѱ�����������";
		String logname="";
		List<Member> overtimememberlist= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByAuditTime(info,applynum,timenum);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
				
				//��ȡ�Ƽ�����Ϣ
				Member regmember=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(member.getReferenceId());
				regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(regmember);
				
				//�����־
				logname="[�����˱��:"+member.getUserName()+"]-[����˱��:"+regmember.getUserName()+"]";
				logContent=logname+logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�˺Ű���������",
						member, logContent);
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


	/**
	 * ���ע�ᳬ���涨ʱ��δ����μ���Ϸ���-�����
	 */
	public static void doRegister() {
		IntegrationGameRule rule=queryRule();
		int applynum=rule.getRegisterAuditTime();
		MemberInfo info=new MemberInfo();
		info.setCreateTime(new Date());
		String logContent=",����δ�ڹ涨���������Ϸ,Ŀǰ˫���˺��ѱ�����������";
		String logname="";
		List<Member> overtimememberlist= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByAuditTime(info,applynum,0);
		if(overtimememberlist!=null&&!overtimememberlist.isEmpty()){
			for (Member member : overtimememberlist) {
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
				
				//��ȡ�Ƽ�����Ϣ
				Member regmember=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(member.getReferenceId());
				regmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(regmember);
				
				//�����־
				logname="[�����˱��:"+member.getUserName()+"]-[����˱��:"+regmember.getUserName()+"]";
				logContent=logname+logContent;
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�˺Ű���������",
						member, logContent);
			}
		}
	}
}
