package com.systemic.gq.member.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;

public interface IMemberDao extends ICommonDao {
	/** ��ҳ��ѯ��Ա */
	Page selectMeber(MemberInfo info);
	
	List<Member> selectMemberBy(MemberInfo info);

	Member selectMemberByStaffid(String referenceId);

	List<Member> selectMemberListByStaffid(String id);

	List<Member> selectMember();

	Member selectMemberByNode(String staffId,String region);

	List<Member> selectMemberListByNode(String staffId);

	boolean selectMemberByUsername(String bh);

	Member selectMemberByUserName(String userName);
}
