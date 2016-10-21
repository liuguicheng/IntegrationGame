package com.systemic.gq.member.service.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.service.dao.IMemberDao;
import com.systemic.unit.ConUnit;

public class HibernateMemberDao extends HibernateCommonDao implements IMemberDao {
	private IQueryStringUtil memberQueryStringUtil;

	public void setMemberQueryStringUtil(IQueryStringUtil memberQueryStringUtil) {
		this.memberQueryStringUtil = memberQueryStringUtil;
	}

	@Override
	public Page selectMeber(MemberInfo info) {
		Object[] values = new Object[25];
		int idx = 0;
		StringBuffer select = new StringBuffer(" from ").append(Member.class.getName()).append(" as me  ");
		StringBuffer where = new StringBuffer(200);

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.memberQueryStringUtil.getQueryObject(info, where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
			List data = super.doQuery(select.toString(), io.getParam());
			info.setNotPage(false);
			return super.putDataToPage(data);
		}

		return super.find(io.getQueryString(), io.getParam(), info.getPageNumber(), info.getPageSize());
	}

	@Override
	public List<Member> selectMemberBy(MemberInfo info) {

		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.isok=1 ");
		if (info != null) {
			if (info.getReferenceId() != null && !"".equals(info.getReferenceId())) {
				queryStr.append(" and m.referenceId = ? ");
				values[idx++] = info.getReferenceId();
			}
			if (info.getRegion() != null && !"".equals(info.getRegion())) {
				queryStr.append(" and m.region = ? ");
				values[idx++] = info.getRegion();
			}
			if (info.getNote() != null && !"".equals(info.getNote())) {
				queryStr.append(" and m.note = ? ");
				values[idx++] = info.getNote();
			}
			if (info.getMemberId() != null && !"".equals(info.getMemberId())) {
				queryStr.append(" and m.memberId != ? ");
				values[idx++] = info.getMemberId();
			}
			if (info.getCreateTime() != null) {
				if(ConUnit.isSameDate(info.getCreateTime(),new Date())){
					//当前日期
					queryStr.append(" and datediff(NOW(),m.createTime)=0 ");
				}else{
					queryStr.append(" and datediff(NOW(),m.createTime)>30 ");
				}
				
			}
			if(info.getBsid()!=null&&!"".equals(info.getBsid())){
				queryStr.append(" and m.bsid = ? ");
				values[idx++] = info.getBsid();
			}
			if(info.getIsdel()!=null){
				queryStr.append(" and m.isdel = ? ");
				values[idx++] = info.getIsdel();
			}

		}
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public Member selectMemberByStaffid(String referenceId) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.staffId =? and m.isdel=1");
		values[idx++] = referenceId;

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return (Member) list.get(0);
		}
		return null;
	}

	@Override
	public List<Member> selectMemberListByStaffid(String id) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.referenceId =? and m.isok=1 and m.isdel=1");
		values[idx++] = id;

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public List<Member> selectMember() {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" where isdel=1 and isok=1");

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public Member selectMemberByNode(String id,String region) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.note =? and m.region=? and m.isok=1 and m.isdel=1");
		values[idx++] = id;
		values[idx++] = region;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return (Member) list.get(0);
		}
		return null;
	}

	@Override
	public List<Member> selectMemberListByNode(String staffId) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.note =? and m.isok=1 and m.isdel=1");
		values[idx++] = staffId;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return  list;
		}
		return null;
	}

	@Override
	public boolean selectMemberByUsername(String bh) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.userName =? and m.isok=1 and m.isdel=1");
		values[idx++] = bh;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return  false;
		}
		return true;
	}

	@Override
	public Member selectMemberByUserName(String userName) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.userName =? and m.isok=1 and m.isdel=1");
		values[idx++] = userName;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return  (Member) list.get(0);
		}
		return null;
	}

	@Override
	public List<Member> selectMemberListByNodeUsername(String note) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.noteUsername =? and m.isok=1 and m.isdel=1");
		values[idx++] = note;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return  list;
		}
		return null;
	}

	@Override
	public List<Member> selectMemberByAuditTime(MemberInfo info, int applynum,int timenum) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where m.memberId!='1'  and m.isok=1 and m.isdel=1");
		if (info != null) {
			if(info.getIsActivation()==2){
				queryStr.append(" and m.isActivation!=2");
			}
			if(info.getCreateTime()!=null&&!"".equals(info.getCreateTime())){
				queryStr.append(" and  (((TIMESTAMPDIFF(hour,FROM_UNIXTIME(UNIX_TIMESTAMP(m.createTime),'%Y-%m-%d %H:%i:%s'),FROM_UNIXTIME(UNIX_TIMESTAMP(now()),'%Y-%m-%d %H:%i:%s')))>="+applynum+"))");
			}
			if(info.getUpgradeState()==0){
				queryStr.append(" and m.upgradeState!=0");
			}
			if(info.getApplyUpgradeTime()!=null&&!"".equals(info.getApplyUpgradeTime())){
				queryStr.append(" and (((TIMESTAMPDIFF(hour,FROM_UNIXTIME(UNIX_TIMESTAMP(m.applyUpgradeTime),'%Y-%m-%d %H:%i:%s'),FROM_UNIXTIME(UNIX_TIMESTAMP(now()),'%Y-%m-%d %H:%i:%s')))>="+timenum+"))");
			}
		}
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

}
