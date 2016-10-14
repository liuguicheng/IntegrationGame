package com.systemic.gq.bonus.settlement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.console.ConsoleHelper;
import com.systemic.gq.bonus.command.BonusRecordEdit;
import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.entity.BonusRecord;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.member.command.MemberInfo;

public abstract class SettlementHelper {

	// ÿ�ս������
	public static void doBonusSettlementForEveryDay(Member member) {

	}

	// �����������-��̬
	public static void doBonusSettlementForStatic(Member member) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// ��ȡ��Ȩ�ȼ��Ľ����������ַ���
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// �����û� ��Ȩ�ȼ��Ľ����������ַ�������ѯ��������
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent staticBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "01":// ��̬��
							staticBonusContent = bonusContent;
							break;
						case "03":// ������
							helpBonusContent = bonusContent;
							break;
						}
					}
					// ��̬��
					staticBonus(member, stock.getMoney(), helpBonusContent, staticBonusContent);
				}
			}
		}
	}

	// ��̬�� -��ʱ����
	public static void settlementStaticBonus() {
		MemberInfo info = new MemberInfo();
		info.setCreateTime(new Date());
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			for (Member member : memberlist) {
				double staticBonusMoney = staticBonusForThirtyDay(member);
				if (staticBonusMoney > 0) {

					upMemberGoldAward(member, staticBonusMoney);
				}

			}
		}
	}

	
	// �����������-�Ƽ�
	public static void doBonusSettlementForTJ(Member member, Member currentMmenber) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// ��ȡ��Ȩ�ȼ��Ľ����������ַ���
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// �����û� ��Ȩ�ȼ��Ľ����������ַ�������ѯ��������
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent tjBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "02":// �Ƽ���
							tjBonusContent = bonusContent;
							break;
						case "03":// ������
							helpBonusContent = bonusContent;
							break;
						}
					}
					// �Ƽ���
					tjBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, tjBonusContent);
				}
			}
		}
	}

	// �����������-����
	public static void doBonusSettlementForDP(Member member, Member currentMmenber) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// ��ȡ��Ȩ�ȼ��Ľ����������ַ���
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// �����û� ��Ȩ�ȼ��Ľ����������ַ�������ѯ��������
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent dpBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "04":// ������
							dpBonusContent = bonusContent;
							break;
						case "03":// ������
							helpBonusContent = bonusContent;
							break;
						}
					}
					// ����
					dpBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, dpBonusContent);
				}
			}
		}
	}

	/**
	 * һ���Խ������ member ���Ƽ��Ļ�Ա currentMmenber �Ƽ���
	 */
	public static void doBonusSettlementForDisposable(Member member) {
		if (member != null) {
			Member currentMmenber=ConsoleHelper.getInstance().getSpringMemberService().selectMemberById(member.getReferenceId());
			Stock stock = member.getStock();
			if (stock != null) {
				// ��ȡ��Ȩ�ȼ��Ľ����������ַ���
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// �����û� ��Ȩ�ȼ��Ľ����������ַ�������ѯ��������
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent staticBonusContent = null;
					BonusContent dpBonusContent = null;
					BonusContent tjBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "01":// ��̬��
							staticBonusContent = bonusContent;
							break;
						case "02":// �Ƽ���
							tjBonusContent = bonusContent;
							break;
						case "03":// ������
							helpBonusContent = bonusContent;
							break;
						case "04":// ������
							dpBonusContent = bonusContent;
							break;
						}
					}
					// ��̬��
					staticBonus(member, stock.getMoney(), helpBonusContent, staticBonusContent);
					// �Ƽ���
					tjBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, tjBonusContent);
					// ����
					dpBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, dpBonusContent);

				}

			}
		}

	}

	// ##################################################�����ڲ�����#####################################################//
	/**
	 * ����
	 * 
	 * @param member
	 *            ע���Ա
	 * @param currentMmenber
	 *            �Ƽ���
	 * @param stockMoney
	 * @param helpBonusContent
	 * @param dpBonusContent
	 */
	private static void dpBonus(Member member, Member currentMmenber, double stockMoney, BonusContent helpBonusContent,
			BonusContent dpBonusContent) {
		double bonusMoney;
		/**
		 * ���� 1��1 ��С��ҵ���İٷֱȼ��� ���裺
		 * 1.��Ӧ�����Ƽ����Ƿ�һ��,��ע����Ϊ����������ͬ�Ƽ��˳��Լ����������������Ա�������㣻����ͬ�� 2.�Խ��С����Ϊ��ȡ���������׼
		 * 3.������ͬһ��
		 */
		// ע���û�����
		String region = member.getRegion();
		// ͬ�㡢ͬ����ͬ��һ���ڵ�
		Member regionMember = selectMemberByRegion(member, currentMmenber);
		if (regionMember != null) {
			double money = stockMoney;
			if (regionMember.getStock().getMoney() < money) {
				money = regionMember.getStock().getMoney();
			}
			// ����
			bonusMoney = saveBonusRecord(currentMmenber, money, dpBonusContent, 1);
			// �޸Ļ�Ա�����ܶ�
			upMemberGoldAward(currentMmenber, bonusMoney);
			// ����
			settlementHelpBonusMoney(currentMmenber, bonusMoney, helpBonusContent);
			bonusMoney = 0;
		}
		// ���ڵ���һ�����¶�Ӧ�ӽڵ�
		Member parentNodeMember = selectMemberByParentNode(region, currentMmenber);
		if (parentNodeMember != null) {
			Member zNodeMember = selectMemberByNode(region, parentNodeMember);
			if (zNodeMember != null) {
				double money = stockMoney;
				if (zNodeMember.getStock().getMoney() < money) {
					money = zNodeMember.getStock().getMoney();
				}
				// ����
				bonusMoney = saveBonusRecord(currentMmenber, money, dpBonusContent, 1);
				// �޸Ļ�Ա�����ܶ�
				upMemberGoldAward(currentMmenber, bonusMoney);
				// ����
				settlementHelpBonusMoney(currentMmenber, bonusMoney, helpBonusContent);
				bonusMoney = 0;
			}
		}
	}

	/**
	 * �Ƽ���
	 * 
	 * @param member
	 *            ע���û�
	 * @param currentMmenber
	 *            ע���û��Ƽ���
	 * @param stockMoney
	 *            ע���û���Ȩ���
	 * @param helpBonusContent
	 * @param tjBonusContent
	 */
	private static void tjBonus(Member member, Member currentMmenber, double stockMoney, BonusContent helpBonusContent,
			BonusContent tjBonusContent) {
		double bonusMoney;
		// �Ƽ�
		bonusMoney = saveBonusRecord(currentMmenber, stockMoney, tjBonusContent, 1);
		// �޸Ļ�Ա�����ܶ�
		upMemberGoldAward(currentMmenber, bonusMoney);
		// ����
		settlementHelpBonusMoney(member, bonusMoney, helpBonusContent);
		bonusMoney = 0;
	}

	/**
	 * ��̬��
	 * 
	 * @param member
	 *            ����û�
	 * @param stockMoney
	 *            �û���Ȩ���
	 * @param helpBonusContent
	 * @param staticBonusContent
	 */
	private static void staticBonus(Member member, double stockMoney, BonusContent helpBonusContent,
			BonusContent staticBonusContent) {
		double bonusMoney;
		// ��̬
		if (selectDay(new Date(), member.getCreateTime())) {
			// ��¼���� ����¼ ״̬Ϊδ����
			bonusMoney = saveBonusRecord(member, stockMoney, staticBonusContent, 0);

			// �޸Ļ�Ա�����ܶ� ��ÿ����30��ϵͳ��ʱ����ͳһ����

			// ����
			settlementHelpBonusMoney(member, bonusMoney, helpBonusContent);
			bonusMoney = 0;
		}
	}

	/**
	 * ���ݻ�Ա��Ȩ�ȼ�����ȡ�õȼ��Ľ�������
	 * 
	 * @param bonusContentStr
	 *            ��Ա�Ĺ�Ȩ�ȼ��Ľ�������-�����ϣ�
	 * @return
	 */
	private static List<BonusContent> queryBonusContentListForUser(String bonusContentStr) {
		List<BonusContent> bonusContentList = null;
		if (StringUtils.isNotBlank(bonusContentStr)) {
			String[] sz = bonusContentStr.split(",");
			BonusContent bonuscontent = null;
			bonusContentList = new ArrayList<BonusContent>();
			for (String szstr : sz) {
				bonuscontent = ConsoleHelper.getInstance().getBonuscontentService().selectBonusContentByid(szstr);
				bonusContentList.add(bonuscontent);
			}
			bonuscontent = null;
		}
		return bonusContentList;
	}

	private static void upMemberGoldAward(Member member, double bonusMoney) {
		if (member != null) {
			member.setGoldAward(member.getGoldAward() + bonusMoney);
			ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
		}
	}

	/**
	 * ���� ������
	 * 
	 * @param member
	 *            ע���û�
	 * @param bonusMoney
	 *            ������
	 * @param bonusContentid
	 *            ע���û��ȼ��� ��������id
	 */
	private static void settlementHelpBonusMoney(Member member, double bonusMoney, BonusContent bonusContent) {

		int begin = bonusContent.getBegin();
		int end = bonusContent.getEnd();
		Member newmember = selectMemberByNote(member.getNote());
		System.out.println("��һ�ڵ��û���" + newmember.getUserName());
		for (int i = 1; i < begin - 1; i++) {
			newmember = selectMemberByNote(newmember.getNote());
			System.out.println("��һ�ڵ��û���" + newmember.getUserName());
		}
		// ���� ������
		double helpBonusMoney = bonusMoney * bonusContent.getProportion();
		// ��ӻ�������¼
		BonusRecordEdit bre = new BonusRecordEdit(helpBonusMoney, newmember.getMemberId(), newmember.getUserName(),
				new Date(), bonusContent.getBonusType(), bonusContent.getProportion(), "", 0);
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bre);
		// �޸Ļ�Ա�����
		upMemberGoldAward(newmember, helpBonusMoney);
		helpBonusMoney = 0;

		for (int j = 1; j < end - begin; j++) {
			newmember = selectMemberByNote(newmember.getNote());
			System.out.println("��һ�ڵ��û���" + newmember.getUserName());
			// ���� ������
			helpBonusMoney = bonusMoney * bonusContent.getProportion();
			// ��ӻ�������¼
			bre = new BonusRecordEdit(helpBonusMoney, newmember.getMemberId(), newmember.getUserName(), new Date(),
					bonusContent.getBonusType(), bonusContent.getProportion(), "", 0);
			ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bre);
			// �޸Ļ�Ա�����
			upMemberGoldAward(newmember, helpBonusMoney);
		}
	}

	/**
	 * ���ݽڵ��ѯ ��Ա
	 */
	private static Member selectMemberByNote(String note) {
		MemberInfo info = new MemberInfo();
		info.setNote(note);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * ��ѯ���ڵ������һ�߶�Ӧ����ڵ�
	 * 
	 * @param member
	 *            ע���Ա
	 * @param currentMmenber
	 *            �Ƽ���
	 * @return
	 */
	private static Member selectMemberByRegion(Member member, Member currentMmenber) {
		String region = "";
		if (member.getRegion().equals("0")) {
			region = "1";
		} else if (member.getRegion().equals("1")) {
			region = "0";
		}
		MemberInfo info = new MemberInfo();
		info.setReferenceId(currentMmenber.getMemberId());
		info.setRegion(region);
		info.setNote(member.getNote());
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * ��ѯ���ڵ�ĸ��ڵ�
	 * 
	 * @param member
	 *            ע���Ա
	 * @param currentMmenber
	 *            �Ƽ���
	 * @return
	 */
	private static Member selectMemberByParentNode(String region, Member currentMmenber) {
		if (region.equals("0")) {
			region = "1";
		} else if (region.equals("1")) {
			region = "0";
		}
		MemberInfo info = new MemberInfo();
		info.setMemberId(currentMmenber.getReferenceId());
		info.setRegion(region);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * ��ѯ���ڵ����ӽڵ��
	 * 
	 * @param member
	 *            ע���Ա
	 * @param currentMmenber
	 *            �Ƽ���
	 * @return
	 */
	private static Member selectMemberByNode(String region, Member member) {
		MemberInfo info = new MemberInfo();
		info.setNote(member.getMemberId());
		info.setRegion(region);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param member
	 *            ��Ա
	 * @param stockMoney
	 *            ��Ȩ���
	 * @param bonusContent
	 *            ��Ȩ��������
	 * @param sendType
	 *            ���𷢷ű�ʶ 0 δ���� 1�ѷ���
	 * @return
	 */
	private static double saveBonusRecord(Member member, double stockMoney, BonusContent bonusContent, int sendType) {

		String bonustype = bonusContent.getBonusType();
		double bonusMoney = stockMoney * bonusContent.getProportion();
		BonusRecordEdit bonusRecordEdit = new BonusRecordEdit(bonusMoney, member.getMemberId(), member.getUserName(),
				new Date(), bonustype, bonusContent.getProportion(), "", sendType);
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bonusRecordEdit);
		return bonusMoney;
	}

	// ��̬�� ���� ���޸ļ�¼״̬ �����������ӻ�Ա�����
	private static double staticBonusForThirtyDay(Member member) {
		double moeny = 0;
		// ���𵽴﷽��ʱ�䣬���޸�δ����״̬����ȡ�����������û������
		BonusRecordInfo bri = new BonusRecordInfo();
		bri.setIsSend(0);
		bri.setUserid(member.getMemberId());
		List<BonusRecord> brlist = ConsoleHelper.getInstance().getBonusRecordService().selectBonusRecordByMember(bri);
		if (brlist != null) {
			for (BonusRecord bonusRecord : brlist) {
				moeny += bonusRecord.getMoney();
				bonusRecord.setIsSend(1);
				bonusRecord.setSend_time(new Date());
				ConsoleHelper.getInstance().getBonusRecordService().updateBonusRecord(bonusRecord);
			}
			return moeny;
		}
		return moeny;
	}

	// �Ƚ�ʱ������λ �Ƿ���ͬ
	private static boolean selectDay(Date newdate, Date userDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(newdate);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		ca = Calendar.getInstance();
		ca.setTime(userDate);
		int userday = ca.get(Calendar.DAY_OF_MONTH);

		if (day == userday) {
			return true;
		}
		return false;
	}
}
