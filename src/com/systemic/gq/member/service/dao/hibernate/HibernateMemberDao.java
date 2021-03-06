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
	private IQueryStringUtil countDownMemberQuyerStringUtil;
	/**
	 * @param countDownMemberQuyerStringUtil the countDownMemberQuyerStringUtil to set
	 */
	public void setCountDownMemberQuyerStringUtil(IQueryStringUtil countDownMemberQuyerStringUtil) {
		this.countDownMemberQuyerStringUtil = countDownMemberQuyerStringUtil;
	}

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
			if(info.getUpgradeState()!=null){
				queryStr.append(" and m.upgradeState = ? ");
				values[idx++] = info.getUpgradeState();
			}
			if(info.getIsActivation()!=null){
				queryStr.append(" and m.isActivation = ? ");
				values[idx++] = info.getIsActivation();
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
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.note =?  and m.isdel=1");
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
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.noteUsername =? and m.isdel=1");
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

	@Override
	public int selectMemberCount(MemberInfo info) {
		Object[] values = new Object[30];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  1=1 ");
		if (info != null) {
			if(info.getIsok()!=null){
				if(info.getIsok()==-1){
					queryStr.append(" and m.isok!=1 ");
				}else{
					
					queryStr.append(" and m.isok=? ");
					values[idx++] = info.getIsok();
				}
				
			}
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
			return list.size();
		}
		return 0;
	}

	@Override
	public Page selectCountDownMember(MemberInfo info, int upda) {
		Object[] values = new Object[25];
        int idx = 0;
        StringBuffer select = new StringBuffer(" from ").append(Member.class.getName()).append(" as me  ");
        StringBuffer where = new StringBuffer(200);
        where.append(" 1=1 ");
        if (info != null) {
        	if(info.getIsok()!=null){
        		where.append(" and me.isok=? ");
        		  values[idx++] =info.getIsok()   ;
        	}
        	if(info.getIsdel()!=null){
        		where.append(" and me.isdel=? ");
      		  values[idx++] =info.getIsdel()   ;
        	}
        	if(info.getApplyUpgradeNum()!=null){
        		where.append(" and ( me.applyUpgradeNum >= ? )");
      		  values[idx++] =upda  ;
        	}
        	if(info.getReferenceId()!=null&&info.getAuditGradeUserName()!=null&&info.getUserName()!=null){
        		where.append(" and ( me.referenceId=?  or me.auditGradeUserName = ? or me.userName =? )");
        		  values[idx++] =info.getReferenceId()   ;
        		  values[idx++] =info.getAuditGradeUserName()   ;
        		  values[idx++] =info.getUserName()   ;
        	}
			if(info.getIsActivation()==2&&info.getUpgradeState()==0){
				where.append(" and ( me.isActivation != 2 or me.upgradeState != 0 )");
			}
			where.append(" ) ");
		}
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.countDownMemberQuyerStringUtil.getQueryObject(info,where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
			List data = super.doQuery(select.toString(), io.getParam());
			info.setNotPage(false);
			return super.putDataToPage(data);
		}
		return super.find(io.getQueryString(), io.getParam(), info.getPageNumber(), info.getPageSize());
	}

	@Override
	public Member selectMemberByUserNametoAll(String staffid) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Member.class.getName()).append(" as m where  m.userName =?  and m.isdel=1");
		values[idx++] = staffid;
		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		List list = super.doQuery(queryStr.toString(), param);
		if (!list.isEmpty()) {
			return  (Member) list.get(0);
		}
		return null;
	}

	@Override
	public Page selectMemberByReIdandAGUsername(MemberInfo info) {
		Object[] values = new Object[25];
        int idx = 0;
        StringBuffer select = new StringBuffer(" from ").append(Member.class.getName()).append(" as me  ");
        StringBuffer where = new StringBuffer(200);
        where.append(" 1=1 ");
        if (info != null) {
        	if(info.getIsdel()!=null){
        		where.append(" and me.isdel=? ");
      		  values[idx++] =info.getIsdel()   ;
        	}
        	if(info.getUpgradeState()!=null){
        		where.append(" and ( me.upgradeState = ? )");
      		  values[idx++] =info.getUpgradeState()  ;
        	}
        	if(info.getReferenceId()!=null&&info.getAuditGradeUserName()!=null){
        		where.append(" and ( me.referenceId=?  or me.auditGradeUserName = ?  )");
        		  values[idx++] =info.getReferenceId()   ;
        		  values[idx++] =info.getAuditGradeUserName()   ;
        	}
			if(info.getIsActivation()!=null){
				where.append(" and ( me.isActivation = ? )");
				 values[idx++] =info.getIsActivation() ;
			}
			where.append(" ) ");
		}
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.countDownMemberQuyerStringUtil.getQueryObject(info,where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
			List data = super.doQuery(select.toString(), io.getParam());
			info.setNotPage(false);
			return super.putDataToPage(data);
		}
		return super.find(io.getQueryString(), io.getParam(), info.getPageNumber(), info.getPageSize());
	
	}

}
