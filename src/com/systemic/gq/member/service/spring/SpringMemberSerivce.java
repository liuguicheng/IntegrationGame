package com.systemic.gq.member.service.spring;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.plugins.sn.service.SNHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.gq.member.service.dao.IMemberDao;
import com.systemic.unit.ConUnit;

public class SpringMemberSerivce implements ISpringMemberService {
	private IMemberDao memberDao;

	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public Page selectMeber(MemberInfo info) {

		return this.memberDao.selectMeber(info);
	}

	@Override
	public Member loadMermber(String id) {

		return (Member) this.memberDao.load(Member.class, id);
	}

	@Override
	public Member saveMermber(MemberEditInfo info) {
		Member member = new Member();
		if (StringUtils.isNotBlank(info.getId())) {
			BeanUtils.copyProperties(info, member);
		} else {
			//新增登陆账号
			Staff staff = new Staff();
			staff.setId(SNHelper.getSNService().getSerialNumber(
					Staff.class.getName(), "id", false));
			staff.setName(info.getBsid());
			// 判断登录名是否被使用
//			Staff tmp = ConsoleHelper.getInstance().getMainService().selectAllStaff(info.getUserName());
//			if (tmp != null && !tmp.getId().equals(info.getId())) {
//				throw new RuntimeException("登录名『"
//						+ info.getUserName() + "』已被使用！");
//			}
			//判断昵称是否被占用
			MemberInfo einfo=new MemberInfo();
			einfo.setBsid(info.getBsid());
			List<Member> memberlist= selectMemberBy(einfo);
			if(memberlist!=null&&!memberlist.isEmpty()){
				throw new RuntimeException("昵称『"
						+ info.getBsid() + "』已被使用！");
				
			}
			BeanUtils.copyProperties(info, staff, new String[] { "id",
					"password" });
			BeanUtils.copyProperties(info, member, new String[] { "memberId" });
			staff.setLoginName(member.getUserName());//登录名
			staff.setPassword(info.getPassword());//密码
			// 设置密码，并加密
			if (info.getPassword() != null
					&& info.getPassword().trim().length() > 0) {
			    //判断密码是否变更，变更则更新CHANGE_TIME字段
			    if(!staff.getPassword().equals(EncryptHelper.md5Encoding(info.getPassword()))){
			        StaffSecurity ss = ConsoleHelper.getInstance().getMainService().loadStaffSecurity(staff.getId());
		            ss.setChangeTime(new Date());
		            this.memberDao.update(ss);
	             }
	            staff.setPassword(EncryptHelper
	                .md5Encoding(info.getPassword()));
	            member.setPassword(EncryptHelper
		                .md5Encoding(info.getPassword()));
			}
			if(info.getPasswodTwo()!=null&&info.getPasswodTwo().trim().length()>0){
				 member.setPasswodTwo(EncryptHelper
			                .md5Encoding(info.getPasswodTwo()));
			}
			if(info.getPasswordThree()!=null&&info.getPasswordThree().trim().length()>0){
				 member.setPasswordThree(EncryptHelper
			                .md5Encoding(info.getPasswordThree()));
			}
			Set<Role> set = new HashSet<Role>();
			Role role = (Role) this.memberDao.load(Role.class, "21");//21为玩家角色ID
			set.add(role);
			staff.setSystemRole(set);
			staff.setDepartment(ConsoleHelper.getInstance().getMainService().selectDepartment("1"));
			staff.setValid(ConsoleHelper.YES);
			staff.setSysTemplate(ConsoleHelper.YES);
			this.memberDao.save(staff);
			CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
			member.setStaffId(staff.getId());//系统登录
			// TODO: Animate this.group instead 选择股权等级待处理 使用Stock id做连接
			member.setProductgradeId(info.getProductgradeId());
			member.setCreateTime(new Date());
			member.setRegion(info.getRegion());
			member.setIsok(1);
			member.setIsdel(1);
			//获取推荐人的推广链接和二维码 廉通道、勇通道
			Member referenceMember= selectMemberByStaffid(info.getReferenceId());
			member.setReferenceQRCodeContent(referenceMember.getqRCodeContent());
			member.setReferenceQRCodeImageUrl(referenceMember.getqRCodeImageUrl());
			member.setReferencelan(referenceMember.getLan());
			member.setReferenceyong(referenceMember.getYong());
			//修改次数
			member.setUpdateInfoNum(info.getUpdateInfoNum());
		} 
		this.memberDao.save(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
		return member;
	}

	@Override
	public void deleteMember(String[] ids) {
			for (String id :ids ) {
				Member member = this.loadMermber(id);
				if(member != null){
					ConsoleHelper.getInstance().notifyObservers(member);
					try {
						ConsoleHelper.getInstance().getManageService().deleteStaff(member.getStaffId());
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("删除会员出错");
						throw new RuntimeException("删除会员出错!请联系技术人员!"+this.getClass());
					}
					member.setIsdel(0);
					this.memberDao.save(member);
					CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
				}
				
			}
	}

	@Override
	public List<Member> selectMemberBy(MemberInfo info) {
		return this.memberDao.selectMemberBy(info);
	}

	@Override
	public void updateMermber(Member member) {
		
		if(member.getPassword()!=null&&!"".equals(member.getPassword().trim())){
			
			StaffSecurity ss = ConsoleHelper.getInstance().getMainService().loadStaffSecurity(member.getStaffId());
			if(ss!=null){
				ss.setChangeTime(new Date());
				this.memberDao.update(ss);
			}
            Staff staff=ConsoleHelper.getInstance().getMainService().selectStaffById(member.getStaffId());
            if(staff!=null){
            	ConsoleHelper.getInstance().getMainService().updatePassword(staff, member.getPassword());
            	CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
            }
            member.setPassword(EncryptHelper
	                .md5Encoding(member.getPassword()));
            
		}
		
		memberDao.update(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
	}

	@Override
	public Member selectMemberByStaffid(String referenceId) {
		// TODO Auto-generated method stub
		return memberDao.selectMemberByStaffid(referenceId);
	}

	@Override
	public void updateMermberInfo(Member member) {
		this.memberDao.save(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
	}

	@Override
	public Member selectMemberById(String memberId) {
		return (Member) this.memberDao.load(Member.class, memberId);
	}

	@Override
	public List<Member> selectMemberListByStaffid(String id) {
		return memberDao.selectMemberListByStaffid(id);
	}

	@Override
	public int selectMemberCountByStaffid(String staffId) {
		 List<Member> memberlist=selectMemberListByStaffid(staffId);
		 if(memberlist!=null&&!staffId.isEmpty()){
			 
			 return memberlist.size();
		 }
		return 0;
	}

	@Override
	public double selectMemberMoneyTotalByStaffid(String staffId) {
		double totalMoney=0;
		List<Member> memberlist=selectMemberListByStaffid(staffId);
		if(memberlist!=null&&!staffId.isEmpty()){
			for (Member member : memberlist) {
				totalMoney+=member.getStock().getMoney();
			}
		}
		return totalMoney;
	}

	@Override
	public List<Member> selectMember() {
		return this.memberDao.selectMember();
	}

	@Override
	public Member selectMemberByNode(String staffId,String region) {
		return memberDao.selectMemberByNode(staffId,region);
	}

	@Override
	public List<Member> selectMemberListByNode(String staffId) {
		return memberDao.selectMemberListByNode(staffId);
	}

	@Override
	public boolean selectMemberByUsername(String bh) {
		return memberDao.selectMemberByUsername(bh);
	}

	@Override
	public Member selectMemberByUserName(String userName) {
		return memberDao.selectMemberByUserName(userName);
	}

	@Override
	public List<Member> selectMemberListByNodeUsername(String note) {
		return memberDao.selectMemberListByNodeUsername(note);
	}

	@Override
	public List<Member> selectMemberByAuditTime(MemberInfo info, int timenum) {
		return memberDao.selectMemberByAuditTime(info,timenum);
	}

	
}
