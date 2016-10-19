package com.systemic.gq.bonus.settlement;

import java.util.Date;

import com.console.ConsoleHelper;
import com.systemic.gq.bonus.command.BonusRecordEdit;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Level;
import com.systemic.gq.entity.Member;

/**
 * ������ֹ�����
 */
public class BonusRecordHelper {

	/**
	 * �����Ƽ����� �͹������� ���� �� ֱ���Ƽ��� ���һ��* ������ ���һ��* * �ɵ�ǰ�û��ȼ� ���� ĿǰĬ��v1 ���������Ƽ��û�
	 */
	public static void doComputationalIntegral(Member member) {
		if (member.getLevleId() != null && !"".equals(member.getLevleId())) {
			// ע���û��ȼ�
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			// ��־���ݣ����ԭ��
			String log = "";
			// �Ƽ����û���Ϣ
			String refusername = member.getReferenceName();
			if (refusername != null && !"".equals(refusername)) {
				log = "�Ƽ�������˱��Ϊ:" + member.getUserName() + "���";
				Member ms=addBonusRecord(level,refusername, log);
				//�޸��û�������������
				updateMemberForUpgradeNum(ms,level.getId());
			}
			// �������û���Ϣ
			String nodeusername = member.getNoteUsername();
			//�����������Ƽ��� �ڵ���ͬ��ֻ��һ�λ���
			if (!refusername.equals(nodeusername)) {
				if (nodeusername != null && !"".equals(nodeusername)) {
					log = "����ұ��:" + member.getUserName() + "������";
					Member m=addBonusRecord(level,nodeusername, log);
					//�޸��û�������������
					updateMemberForUpgradeNum(m,level.getId());
				}
			}

		}
	}
	private static Member addBonusRecord(Level receivedLevel,String username, String log) {
		//��û����û�
		Member member = ConsoleHelper.getInstance().getSpringMemberService().selectMemberByUserName(username);

		String remark = "�û����:" + member.getUserName() + " ���" + receivedLevel.getV1_bonus_num() + "��" + receivedLevel.getV1_type()
				+ "<br/>" + "���ԭ��:" + log;
		// ��װ ���ּ�¼ʵ��
		BonusRecordEdit bonusrecordedit = new BonusRecordEdit(member.getUserName(), member.getBsid(), new Date(),
				receivedLevel.getV1_zdtype(), remark, 1, receivedLevel.getV1_bonus_num(), receivedLevel.getV1_type());
		// ��ӻ��ּ�¼
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bonusrecordedit);
		
		return member;
	}

	/**
	 * �޸��û�������������
	 * 
	 * @param member
	 *            �����Ʒ���������Ϣ
	 * @param receivedLevelid
	 *            �յ��������� id
	 */
	private static void updateMemberForUpgradeNum(Member member, String receivedLevelid) {
		// �����Ʒ��������������������Ʒ��������һ��
		if (member.getLevleId().equals(receivedLevelid)) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			member.getLevleId();
			int num = member.getApplyUpgradeNum();
			member.setApplyUpgradeNum(num + 1);
			// ������˿�������ʱ���޸��û�״̬Ϊ ������
			if (level.getV1_upgrade_num() == member.getApplyUpgradeNum()) {
				member.setUpgradeState(1);
			}
			ConsoleHelper.getInstance().getSpringMemberService().updateMermberInfo(member);
		}
	}
	
	

	/**
	 * ����������û���
	 *  String username����û����û����
	 *  String gradeusername:�����û����
	 *  Level level:��ǰ�ȼ�
	 */
	public static void doComputationalIntegralforApply(String gradeusername,String username,Level level) {
		String levelt=level.getV1_zdtype();
		int newlevelt=Integer.parseInt(levelt)+1;
		Level newlevel= ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(String.valueOf(newlevelt));
		String log="�յ����:"+gradeusername+"�����ͳ��Ļ���";
		Member m=addBonusRecord(newlevel,username,log);
		//�޸��û�������������
		updateMemberForUpgradeNum(m,newlevel.getId());
	}
}
