package com.systemic.gq.member.service;

import java.util.List;

import org.springline.orm.Page;

import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;

public interface ISpringMemberService {
	/** ��ҳ��ѯ��Ա */
	Page selectMeber(MemberInfo info);
	/**����ID��ȡһ������*/
	Member loadMermber(String id);
	/**�����Ա*/
	Member saveMermber(MemberEditInfo info);
	/**ɾ����Ա*/
	void deleteMember(String[] id);
	
	List<Member> selectMemberBy(MemberInfo info);
	/**���»�Ա �����µ�½�˺�����*/
	void updateMermber(Member member);
	/**���ݵ�½id ��ѯ��Ա��Ϣ */
	Member selectMemberByStaffid(String referenceId);
	/**���»�Ա������Ϣ*/
	void updateMermberInfo(Member member);
	/**���ݻ�Աid ��ѯ��Ա��Ϣ */
	Member selectMemberById(String memberId);
	/**�����Ƽ��˲�ѯ�����Ƽ���Ա*/
	List<Member> selectMemberListByStaffid(String id);
	/**
	 * ��ѯ���Ƽ��������Ƽ��������ܺ�
	 * @param staffId
	 * @return
	 */
	int selectMemberCountByStaffid(String staffId);
	/**
	 * ��ѯ���Ƽ������Ƽ��˵��ܽ��
	 * @param staffId
	 * @return
	 */
	double selectMemberMoneyTotalByStaffid(String staffId);
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<Member> selectMember();
	/**
	 * ��ѯ����ڵ��µ��ӽڵ�
	 * @param staffId �ڵ�id
	 * @param region ����
	 * @return
	 */
	Member selectMemberByNode(String staffId,String region);
	List<Member> selectMemberListByNode(String staffId);
	/**
	 * ���ݵ�½�˺Ų�ѯ�û��Ƿ����
	 * @param bh
	 * @return
	 */
	boolean selectMemberByUsername(String bh);
	
	Member selectMemberByUserName(String userName);
	List<Member> selectMemberListByNodeUsername(String note);
	List<Member> selectMemberByAuditTime(MemberInfo info, int applynum,int timenum);
	void updateRole(Staff mestaff);
	void update(Member member);
	int selectMemberCount(MemberInfo info);
	Member saveMermberAjax(MemberEditInfo info);
	Page selectCountDownMember(MemberInfo info, int upda);
	/**
	 * ���ݽڵ��ѯ ��������״̬
	 * @param staffid
	 * @return
	 */
	Member selectMemberByUserNametoAll(String staffid);
	Page selectMemberByReIdandAGUsername(MemberInfo info);
}
