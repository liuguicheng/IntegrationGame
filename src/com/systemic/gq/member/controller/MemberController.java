package com.systemic.gq.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.systemic.gq.bonus.settlement.BonusRecordHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Level;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.quartz.MemberQuartz;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.unit.BarcodeFactory;
import com.systemic.unit.ConUnit;
import com.systemic.unit.ErrorDataMsg;

@Controller
public class MemberController {
	@Autowired
	private ISpringMemberService springMemberService;

	/**
	 * 管理员 玩家列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/member/memberManage.do")
	public String memberManage(HttpServletRequest request, HttpServletResponse response, Model model, MemberInfo info) {
		Page page;
		String returnurl = "";

		try {
			returnurl = "gq/member/memberManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);

			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 未参与玩家列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/member/memberNotActiveManage.do")
	public String memberNotActiveManage(HttpServletRequest request, HttpServletResponse response, Model model,
			MemberInfo info) {
		Page page;
		String returnurl = "";
		try {
			returnurl = "gq/member/memberNotActiveManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);

			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(0);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 已参与玩家列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/member/memberActiveManage.do")
	public String memberActiveManage(HttpServletRequest request, HttpServletResponse response, Model model,
			MemberInfo info) {
		Page page;
		String returnurl = "";

		try {
			returnurl = "gq/member/memberActiveManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);

			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(2);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 
	 * 申请参与玩家列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/member/memberAuditManage.do")
	public String memberAuditManage(HttpServletRequest request, HttpServletResponse response, Model model,
			MemberInfo info) {
		Page page;
		String returnurl = "";
		try {
			returnurl = "gq/member/memberAuditManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);

			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(1);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 申请升级玩家列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/member/memberApplyGradeManage.do")
	public String memberApplyGradeManage(HttpServletRequest request, HttpServletResponse response, Model model,
			MemberInfo info) {
		Page page;
		String returnurl = "";
		try {
			returnurl = "gq/member/memberApplyGradeManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);

			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(2);
			info.setUpgradeState(2);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}
	
	/**
	 * 倒计时
	 */
	@RequestMapping(value = "/member/memberCountDown.do")
	public String memberCountDown(HttpServletRequest request, HttpServletResponse response, Model model,
			MemberInfo info) {
		Page page;
		String returnurl = "";
		try {
			returnurl = "gq/member/memberCountDownManage";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember=this.springMemberService.selectMemberByStaffid(staff.getId());
			Level level=ConsoleHelper.getInstance().getIlevelService().selectlevel(loginmember.getLevleId());
			IntegrationGameRule rule= ConsoleHelper.getInstance().getIntegrationGameRuleService().selectIntegrationGameRule();
			if (!staff.getName().equals("系统管理员")) {
				info.setReferenceId(staff.getId());
				info.setAuditGradeUserName(loginmember.getUserName());
				info.setUserName(loginmember.getUserName());
				model.addAttribute("typee", loginmember.getUserName());
			}else{
				model.addAttribute("typee", "0");
			}
			info.setIsActivation(2);
			info.setUpgradeState(0);
			info.setIsdel(1);
			info.setIsok(1);
			int djsnum=level.getV1_yjnum();
			int crtime=rule.getRegisterAuditTime();
			int upda=rule.getUpgradeAuditTime();
			page = this.springMemberService.selectCountDownMember(info,djsnum);
			List list=page.getData();
			List<MemberInfo> memberinfolist=new ArrayList<MemberInfo>();
			if(list!=null&&!list.isEmpty()){
				MemberInfo memberinfo=null;
				for (Object object : list) {
					Member member=(Member) object;
					memberinfo=new MemberInfo();
					BeanUtils.copyProperties(member, memberinfo);
					if(member.getIsActivation()!=2){
						memberinfo.setCreateEndTime(ConUnit.dateCalculation(member.getCreateTime(), crtime));
						memberinfo.setCreateCountDown(ConUnit.fromDeadline(memberinfo.getCreateEndTime()));
					}else if(member.getUpgradeState()!=0){
						memberinfo.setApplyUpgradeEndTime(ConUnit.dateCalculation(member.getApplyUpgradeTime(), upda));
						memberinfo.setApplyUpgradeCountDown(ConUnit.fromDeadline(memberinfo.getApplyUpgradeEndTime()));
					}
					memberinfolist.add(memberinfo);
				}
				page.getData().clear();
				page.setData(memberinfolist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}
	/**
	 * 获取最新倒计时秒数
	 */
	
	@RequestMapping(value = "/member/djsAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String djsAjax(HttpServletRequest request) {
		String msg = "";
		return msg;
	}
	
	/**
	 * 去注册
	 * @param request
	 * @param response
	 * @param model
	 * @param token
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.GET)
	public String memberEdit(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			String memberId) {
		String message = request.getParameter("message") != null ? request.getParameter("message") : null;
		String node = request.getParameter("node");
		MemberEditInfo command = new MemberEditInfo();
		if (StringUtils.isNotBlank(memberId)) {
			Member member = this.springMemberService.loadMermber(memberId);
			model.addAttribute("command", member);
		} else {// 新增默认项
			command.setCreateTime(new Date());
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			try {
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
				command.setReferenceId(member.getStaffId());
				command.setReferenceName(member.getUserName());
				command.setStaffId(member.getStaffId());
				if (node != null && !"".equals(node)) {
					command.setNote(node);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("获取数据失败！请联系管理员！");
			}
			model.addAttribute("command", command);
		}
		model.addAttribute("token", token);
		model.addAttribute("message", message);
		return "gq/member/memberEdit";
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.POST)
	public String memberEditSave(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			MemberEditInfo info) {
		String url = "redirect:../member/memberEdit.do";
		try {

			// 验证推荐人状态是否正常
			boolean fa = VerificationReferenceMemberStatus(info.getReferenceId());
			if (fa == false) {
				model.addAttribute("message", "推荐人状态异常,注册失败!");
				return url;
			}
			// 系统设置
			IntegrationGameRule rule = MemberQuartz.queryRule();
			String bh = queryBh(rule);
			info.setUserName(bh);
			info.setUpdateInfoNum(0);
			info.setUpgradeState(0);
			info.setApplyUpgradeNum(0);
			info.setRzstatus(0);
			this.springMemberService.saveMermber(info);
			model.addAttribute("message", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "保存失败" + e.getMessage());
		}
		model.addAttribute("command", info);
		model.addAttribute("token", token);

		return url;
	}

	/**
	 * ajax 注册
	 * 
	 * @param referenceId
	 * @return
	 */

	@RequestMapping(value = "/member/memberRegisterAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String memberRegisterAjax(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			MemberEditInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("注册失败");
		try {
			String code = request.getParameter("code");
			String rand = (String) request.getSession().getAttribute("rand");
			if (!rand.equalsIgnoreCase(code)) {
				edm.setMessage("验证码错误");
				msg = ConUnit.tojson(edm);
				return msg;
			}
			// 验证推荐人状态是否正常
			boolean fa = VerificationReferenceMemberStatus(info.getReferenceId());
			if (fa == false) {
				edm.setMessage("推荐人状态异常,注册失败!");
				msg = ConUnit.tojson(edm);
				return msg;
			}
			try {
				info.setBsid(URLDecoder.decode(info.getBsid(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MemberInfo einfo=new MemberInfo();
			einfo.setBsid(info.getBsid());
			einfo.setIsdel(1);
			List<Member> memberlist= this.springMemberService.selectMemberBy(einfo);
			if(memberlist!=null&&!memberlist.isEmpty()){
				edm.setMessage("昵称『"
						+ info.getBsid() + "』已被使用！");
				msg = ConUnit.tojson(edm);
				return msg;
				
			}
			// 系统设置
			IntegrationGameRule rule = MemberQuartz.queryRule();
			String bh = queryBh(rule);
			info.setUserName(bh);
			info.setUpdateInfoNum(0);
			info.setUpgradeState(0);
			info.setApplyUpgradeNum(0);
			info.setRzstatus(0);
			Member member = this.springMemberService.saveMermberAjax(info);
			edm.setCode(1);
			edm.setMessage(member.getUserName());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			edm.setMessage(e.getMessage());
		}
		msg = ConUnit.tojson(edm);

		return msg;
	}

	private boolean VerificationReferenceMemberStatus(String referenceId) {
		boolean fa = true;
		Member referencemember = this.springMemberService.selectMemberByStaffid(referenceId);
		if (referencemember.getIsok() != 1) {
			return false;
		}
		return fa;
	}

	// 玩家编号 8位随机数字
	public String queryBh(IntegrationGameRule rule) {
		String bh = se(rule);
		return bh;
	}

	private String se(IntegrationGameRule rule) {
		// 玩家编号 8位随机数字
		String bh = ConUnit.uid(rule.getRegisterLoginNameNum());
		boolean fa = this.springMemberService.selectMemberByUsername(bh);
		if (fa == true) {
			return bh;
		} else {
			return se(rule);
		}
	}

	/**
	 * 验证昵称是否存在
	 */
	@RequestMapping(value = "/member/nicknameAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxNicknameAjax(HttpServletRequest request, HttpServletResponse response, MemberEditInfo infos) {
		MemberInfo info = new MemberInfo();
		try {
			infos.setBsid(URLDecoder.decode(infos.getBsid(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setBsid(infos.getBsid());
		info.setIsdel(1);
		List<Member> memberlist = this.springMemberService.selectMemberBy(info);
		String gsonString = "";
		List list = new ArrayList();
		ErrorDataMsg ed = new ErrorDataMsg();
		Gson g = (new GsonBuilder()).create();
		if (memberlist != null && !memberlist.isEmpty()) {
			String error = "昵称『" + infos.getBsid() + "』已被使用！";
			ed.setCode(0);
			ed.setMessage(error);

		} else {
			ed.setCode(1);
		}

		list.add(ed);
		gsonString = g.toJson(list);
		System.out.println(gsonString);
		return gsonString;
	}

	@RequestMapping(value = "/member/loginUsernameAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxLoginUserName(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("userName");
		Staff tmp = ConsoleHelper.getInstance().getMainService().selectAllStaff(username);
		String gsonString = "";
		List list = new ArrayList();
		ErrorDataMsg ed = new ErrorDataMsg();
		Gson g = (new GsonBuilder()).create();
		if (tmp != null) {
			String error = "登录名『" + username + "』已被使用！";
			ed.setCode(0);
			ed.setMessage(error);

		} else {
			ed.setCode(1);
		}

		list.add(ed);
		gsonString = g.toJson(list);
		System.out.println(gsonString);
		return gsonString;
	}

	// 验证推荐人是否存在
	@RequestMapping(value = "/member/referenceAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxreference(HttpServletRequest request, HttpServletResponse response) {
		String referenceId = request.getParameter("referenceId");
		Member member = springMemberService.selectMemberByStaffid(referenceId);
		String gsonString = "";
		List list = new ArrayList();
		ErrorDataMsg ed = new ErrorDataMsg();
		Gson g = (new GsonBuilder()).create();
		if (member == null) {
			String error = "推荐人编号不存在,请核实";
			ed.setCode(0);
			ed.setMessage(error);

		} else {
			ed.setCode(1);
		}

		list.add(ed);
		gsonString = g.toJson(list);
		System.out.println(gsonString);
		return gsonString;
	}

	@RequestMapping(value = "/member/noteAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxnoteAjax(HttpServletRequest request, HttpServletResponse response) {
		String note = request.getParameter("note");
		Member member = springMemberService.selectMemberByStaffid(note);
		String gsonString = "";
		List list = new ArrayList();
		ErrorDataMsg ed = new ErrorDataMsg();
		Gson g = (new GsonBuilder()).create();
		if (member == null) {
			String error = "接点人编号不存在,请核实";
			ed.setCode(0);
			ed.setMessage(error);

		} else {
			ed.setCode(1);
		}

		list.add(ed);
		gsonString = g.toJson(list);
		System.out.println(gsonString);
		return gsonString;
	}

	@RequestMapping(value = "/member/regionAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxregion(HttpServletRequest request, HttpServletResponse response) {
		String note = request.getParameter("note");
		String region = request.getParameter("region");
		MemberInfo info = new MemberInfo();
		info.setNote(note);
		info.setRegion(region);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		String gsonString = "";
		List list = new ArrayList();
		ErrorDataMsg ed = new ErrorDataMsg();
		Gson g = (new GsonBuilder()).create();
		if (memberlist != null && !memberlist.isEmpty()) {
			String error = "接点人,区域" + region + ",已存在玩家";
			ed.setCode(0);
			ed.setMessage(error);

		} else {
			ed.setCode(1);
		}
		list.add(ed);
		gsonString = g.toJson(list);
		System.out.println(gsonString);
		return gsonString;
	}

	/**
	 * 删除玩家
	 * 
	 * @param request
	 * @param memberId
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/memberDelete.do", method = RequestMethod.POST)
	public String memberDelete(HttpServletRequest request, String[] memberId, Long token, Model model) {
		if (memberId != null && memberId.length > 0) {
			this.springMemberService.deleteMember(memberId);
			model.addAttribute("message", "删除成功");
		}

		return "redirect:../member/memberManage.do";
	}

	@RequestMapping(value = "/member/singleMember.do")
	public String singleMember(HttpServletRequest request, HttpServletResponse response, Model model, MemberInfo info) {
		Page page = this.springMemberService.selectMeber(info);
		model.addAttribute("page", page.getData());
		System.out.println(page.getData());
		model.addAttribute("message", request.getParameter("message"));
		return "gq/member/singleMember";
	}

	@RequestMapping(value = "/member/memberListAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String ajaxMember(HttpServletRequest request, HttpServletResponse response, MemberInfo info) {

		Page page = this.springMemberService.selectMeber(info);
		List list = new ArrayList();
		String gsonString = "";
		if (page.getData().size() > 0) {
			for (Iterator it = page.getData().iterator(); it.hasNext();) {
				Member m = (Member) it.next();
				Map data = new HashMap();
				data.put("memberId", m.getMemberId());
				data.put("name", m.getUserName());
				list.add(data);
			}
			Gson g = (new GsonBuilder()).create();
			gsonString = g.toJson(list);
		}
		return gsonString;
	}

	/**
	 * 跳转查看个人资料页面
	 * 
	 */
	@RequestMapping(value = "/member/MemberInfo.do", method = RequestMethod.GET)
	public String toMemberInfo(HttpServletRequest request, Model model, MemberInfo info) {
		Member member = null;
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		String view = "/gq/member/memberUpdate";
		if (info.getMemberId() != null && !"".equals(info.getMemberId())) {
			member = this.springMemberService.selectMemberById(info.getMemberId());
		} else {
			member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}
		if (staff.getName().equals("系统管理员")) {
			view = "/gq/member/memberUpdatefomanage";
		}

		model.addAttribute("command", member);
		return view;
	}

	/**
	 * 修改个人资料
	 */
	@RequestMapping(value = "/member/MemberInfo.do", method = RequestMethod.POST)
	public String doUpdateMember(HttpServletRequest request, Model model, MemberEditInfo info) {
		if (info != null) {
			Member member = this.springMemberService.selectMemberById(info.getId());
			member.setMbwt(info.getMbwt());
			member.setMbwtDn(info.getMbwtDn());
			member.setLan(info.getLan());
			member.setYong(info.getYong());
			member.setUpdateInfoNum(1);
			this.springMemberService.updateMermberInfo(member);
			model.addAttribute("message", "修改成功");
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-修改个人资料";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_MEMBERINFO,
					member, logContent);
		}
		return "redirect:../member/MemberInfo.do?memberId=" + info.getId();
	}

	/**
	 * 玩家冻结
	 */
	@RequestMapping(value = "/member/frozenMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String frozenMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("冻结失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setIsok(0);
			this.springMemberService.updateMermberInfo(member);
			ConsoleHelper.getInstance().getMainService().doFrozen(member.getUserName());
			edm.setMessage("冻结成功");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-冻结了玩家编号为：" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_FROZEN, loginmember,
					logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 玩家解冻
	 */
	@RequestMapping(value = "/member/thwaMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String thwaMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("冻结解除失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setIsok(1);
			this.springMemberService.updateMermberInfo(member);
			Staff memberstaff = ConsoleHelper.getInstance().getMainService().selectStaff(member.getUserName());
			ConsoleHelper.getInstance().getMainService().doUnlock(memberstaff);
			edm.setMessage("冻结解除成功");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-冻结解除了,玩家编号为：" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_THAW, loginmember,
					logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 去申请加入游戏页面
	 * 
	 */
	@RequestMapping(value = "/member/memberApply.do")
	public String memberApply(HttpServletRequest request, HttpServletResponse response, Model model, MemberInfo info) {
		String returnurl = "";

		try {
			returnurl = "gq/member/memberApplyError";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = this.springMemberService.selectMemberByStaffid(staff.getId());
			if (member.getIsActivation() == 0) {
				model.addAttribute("command", member);
				returnurl = "gq/member/memberApply";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据失败！请联系管理员！" + e.getMessage());
		}
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 申请参与游戏
	 */
	@RequestMapping(value = "/member/activationMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String activationMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("申请失败");
		Member member = this.springMemberService.selectMemberByStaffid(info.getStaffId());
		if (member != null) {
			if (info.getNoteUsername().equals(member.getUserName())) {
				edm.setMessage("申请失败,不能输入自身编号,请重新输入归属节点编号!");
			} else if (member.getIsok() != 1) {
				edm.setMessage("申请失败,当前玩家状态异常,请重新输入归属节点编号!");
			} else {
				String region = queryRegion(info.getNoteUsername());
				if (region.equals("-1")) {
					edm.setMessage("申请失败,当前您输入的归属节点下无空位可放,请重新输入归属节点编号!");
				} else {
					member.setRegion(region);
					Member notemember = this.springMemberService.selectMemberByUserName(info.getNoteUsername());
					member.setNotelan(notemember.getNotelan());
					member.setNoteyong(notemember.getNoteyong());
					member.setNoteUsername(notemember.getUserName());
					member.setNote(notemember.getStaffId());
					notemember = null;
					member.setApplyTime(new Date());
					member.setIsActivation(1);
					this.springMemberService.updateMermberInfo(member);
					edm.setMessage("申请成功");
					edm.setCode(1);
					// 添加日志
					Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
					Member loginmember = ConsoleHelper.getInstance().getManageService()
							.selectMemberByStaffId(staff.getId());
					String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-申请参与游戏,玩家编号为：" + member.getStaffId();
					ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_ACTIVATION,
							loginmember, logContent);

					// 添加提醒记录
					String title = "新玩家申请加入游戏提醒";
					String content = "玩家昵称" + member.getBsid() + "申请加入游戏,是否通过审核";
					sendMsg(member, member.getReferenceName(), title, content);
					sendMsg(member, member.getNoteUsername(), title, content);
				}
			}
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 添加邮件
	 * 
	 * @param member
	 *            發送者
	 * @param receiveMan
	 *            接受者
	 * @param title
	 *            標題
	 * @param content
	 *            内容
	 */
	private void sendMsg(Member member, String receiveMan, String title, String content) {
		ConsoleHelper.getInstance().getMsgService().insertMessageForEmail(receiveMan, content, title, "1",
				member.getBsid(), member.getUserName());
	}

	/**
	 * 根据节点查询 节点 下方可放节点
	 * 
	 * @param note
	 * @return
	 */
	private String queryRegion(String note) {
		String region = "";
		// 查询归属节点信息
		List<Member> noteMember = this.springMemberService.selectMemberListByNodeUsername(note);
		if (noteMember != null && !noteMember.isEmpty()) {
			int regoin = 0;
			if (noteMember.size() == 3) {
				region = "-1";
			} else {
				for (Member member2 : noteMember) {
					regoin += Integer.parseInt(member2.getRegion());
				}
				if (regoin == 0) {
					region = "1";
				} else if (regoin == 1) {
					region = "2";
				}
			}
		} else {
			region = "0";
		}
		return region;
	}

	/**
	 * 审核 --申请参加游戏
	 */
	@RequestMapping(value = "/member/fauditMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String auditMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("审核失败");
		edm.setCode(0);
		String istrue = request.getParameter("istrue");
		Member member = this.springMemberService.selectMemberByStaffid(info.getStaffId());
		if (member != null) {
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (istrue.equals("2")) {
				// 审核同意 计算积分
				BonusRecordHelper.doComputationalIntegral(member);
				// 生成二维码
				String qRCodeContent = BarcodeFactory.qRCodeContent + member.getMemberId();
				String qRCodeImageUrl = BarcodeFactory.path + member.getStaffId() + ".png";
				try {
					BarcodeFactory.doQRCode(qRCodeContent, qRCodeImageUrl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				member.setqRCodeContent(qRCodeContent);
				member.setqRCodeImageUrl(qRCodeImageUrl);
				member.setActivationTime(new Date());
				// 修改角色 游客-》玩家
				Staff mestaff = ConsoleHelper.getInstance().getMainService().selectStaffById(member.getStaffId());
				this.springMemberService.updateRole(mestaff);

				// 添加消息
				String title = "新玩家注册提醒";
				String content = "有编号" + member.getUserName() + "新玩家加入,推荐点编号" + member.getReferenceName() + ",归属点编号"
						+ member.getNoteUsername() + ",请知悉";
				sendMsg(member, member.getReferenceName(), title, content);
				sendMsg(member, member.getNoteUsername(), title, content);
			} else {
				// 封号 双方
				// 申请人
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				// 推荐人
				Member refmember = this.springMemberService.selectMemberByUserName(member.getReferenceName());
				// 如果是管理员 就不封号
				if (!refmember.getUserName().equals("99999999")) {
					refmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
					this.springMemberService.updateMermberInfo(refmember);
					String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-审核了玩家编号为：" + member.getStaffId()
							+ ",审核不通过,双方账号被封";
					ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("申请参加游戏审核", refmember,
							logContent);
				}
			}
			member.setIsActivation(Integer.parseInt(istrue));

			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("审核成功");
			edm.setCode(1);

			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-审核了玩家编号为：" + member.getStaffId() + "参与游戏的申请";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("申请参加游戏审核", loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 根据编号查询玩家信息
	 */
	@RequestMapping(value = "/member/memberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String memberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("归属点不存在,请重新输入归属点编号!");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		if (member != null) {
			if (member.getIsok() != 1) {
				edm.setMessage("归属点账号异常,不能放置玩家,请重新输入归属点编号!");
			} else if (member.getIsActivation() != 2) {
				edm.setMessage("输入的归属点未参与游戏,不能放置玩家,请重新输入归属点编号!");

			} else {
				if (!staff.getName().equals("系统管理员")) {
					if (info.getUserName().equals(loginmember.getUserName())) {
						edm.setMessage("输入的归属点不能为自己,请重新输入归属点编号!");
					} else {
						edm.setCode(1);
						edm.setMessage(member.getLan() + "," + member.getYong());
					}
				} else {
					edm.setCode(1);
					edm.setMessage(member.getLan() + "," + member.getYong());
				}

			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 手动生成二维码
	 */

	@RequestMapping(value = "/member/memberQRCodeAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String memberQRCodeAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			// 生成二维码
			String qRCodeContent = BarcodeFactory.qRCodeContent + member.getMemberId();
			String qRCodeImageUrl = BarcodeFactory.path + member.getStaffId() + ".png";
			String logourl = BarcodeFactory.logoImgUrl;
			try {
				BarcodeFactory.doQRCode(qRCodeContent, qRCodeImageUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			member.setqRCodeContent(qRCodeContent);
			member.setqRCodeImageUrl(qRCodeImageUrl);
			this.springMemberService.updateMermberInfo(member);
			edm.setCode(1);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 修改玩家级别（后台管理员）
	 */
	@RequestMapping(value = "/member/upLevelMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String upLevelMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("修改失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			String memberlevleId = request.getParameter("memberlevleId");
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(memberlevleId);
			Stock stock = ConsoleHelper.getInstance().getStockService().selectStockBygradeNum(level.getV1_zdtype());
			member.setLevleId(level.getId());
			member.setStock(stock);
			member.setProductgradeId(stock.getId());
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("修改成功");
			edm.setCode(1);
			// 添加日志
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-修改玩家级别,玩家编号为：" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("修改玩家级别", loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 申请升级
	 */
	@RequestMapping(value = "/member/applyGradeLevelAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String applyGradeLevelAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("申请失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			// 根据用户基本寻找升级审核人
			String auditGradeUserName = queryAuditGradeUserName(member, level);
			member.setAuditGradeUserName(auditGradeUserName);
			member.setApplyUpgradeTime(new Date());
			member.setUpgradeState(2);
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("申请成功");
			edm.setCode(1);
			// 送出积分-》审核人
			BonusRecordHelper.doComputationalIntegralforApply(member.getUserName(), auditGradeUserName, level);
			// 添加日志
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-申请升级,玩家编号为：" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("申请升级", loginmember, logContent);

			// 添加提醒记录
			String title = "玩家升级提醒";
			String content = "有" + member.getStock().getGradeNum() + "级编号为" + member.getUserName() + "的玩家申请升级为("
					+ (Integer.parseInt(member.getStock().getGradeNum()) + 1) + ")级，是否通过请审核";
			sendMsg(member, auditGradeUserName, title, content);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	// 根据用户基本寻找升级审核人
	private String queryAuditGradeUserName(Member member, Level level) {
		String returnUserName = "";
		// 升级审核人寻找条件
		int num = level.getV1_upgrade_tj();
		int levelnum = Integer.parseInt(level.getV1_zdtype());
		// 归属点
		returnUserName = loopSearchMember(num, levelnum, member);

		return returnUserName;
	}

	/**
	 * 递归查询 升级审核人
	 * 
	 * @param num
	 *            循环几次
	 * @param levelnum
	 *            升级人的等级
	 * @param member
	 *            第一次传入是升级人 第二次是上一次查询的玩家信息
	 * @return
	 */
	private String loopSearchMember(int num, int levelnum, Member member) {
		Member nodemember = this.springMemberService.selectMemberByUserName(member.getNoteUsername());
		String username = nodemember.getUserName();
		int nodelevelnum = Integer.parseInt(nodemember.getStock().getGradeNum());
		if (nodemember.getUserName().equals("99999999")) {
			// 如果已到顶点
			return username;
		}
		if (num == 0) {
			if (nodelevelnum < levelnum) {
				num = num + 1;
				return loopSearchMember(num, levelnum, nodemember);
			}
			return username;
		}
		num = num - 1;
		return loopSearchMember(num, levelnum, nodemember);
	}

	/**
	 * 升级审核
	 */
	@RequestMapping(value = "/member/upgradeAudit.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String upgradeAudit(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("审核失败");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		if (member != null) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			if (level.getV1_upgrade_num() == member.getApplyUpgradeNum()) {
				// 修改升级状态
				member.setUpgradeState(0);
				// 清空升级数量
				member.setApplyUpgradeNum(0);
				// 清空申请升级时间
				member.setApplyUpgradeTime(null);
				// 审核时间
				member.setAuditGradeTime(new Date());
				// 修改等级
				String levelt = level.getV1_zdtype();
				int newlevelt = Integer.parseInt(levelt) + 1;
				Level newlevel = ConsoleHelper.getInstance().getIlevelService()
						.selectlevelByzdtype(String.valueOf(newlevelt));
				Stock stock = ConsoleHelper.getInstance().getStockService()
						.selectStockBygradeNum(newlevel.getV1_zdtype());
				member.setLevleId(newlevel.getId());
				member.setStock(stock);
				member.setProductgradeId(stock.getId());

				this.springMemberService.updateMermberInfo(member);
				edm.setMessage("审核成功");
				edm.setCode(1);
				// 添加日志
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member loginmember = ConsoleHelper.getInstance().getManageService()
						.selectMemberByStaffId(staff.getId());
				String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-审核申请升级,升级玩家编号为：" + member.getStaffId();
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("审核申请升级", loginmember, logContent);
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 孝字认证申请
	 */
	@RequestMapping(value = "/member/xzrzAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String xzrzAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("申请失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			if (member.getRzstatus() == 0) {
				member.setRzstatus(1);
				edm.setCode(1);
				edm.setMessage("申请成功");
				this.springMemberService.update(member);
			} else {
				edm.setMessage("该账号已申请或认证申请中,请勿重复申请!");
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 孝字认证审核
	 */

	@RequestMapping(value = "/member/zxAudit.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String zxAudit(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("审核失败");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		if (member != null) {
			if (member.getRzstatus() == 1) {
				member.setRzstatus(2);
				edm.setCode(1);
				edm.setMessage("审核成功");
				this.springMemberService.update(member);
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/member/resetPasswordAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String resetPasswordAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("重置密码失败");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setPassword("666666");
			edm.setCode(1);
			edm.setMessage("重置密码成功,重置后密码为:666666");
			this.springMemberService.updateMermber(member);

			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-重置了玩家编号:" + member.getUserName() + "登陆密码";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_LOGINPASSWORD,
					loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	///////////////////////////////////////// 主页查询/////////////////////////////////////////////////////

	// 查询登录用户
	@RequestMapping(value = "/member/selectMemberInfoAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectMemberInfoAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		if (loginmember != null) {
		}
		msg = ConUnit.tojson(loginmember);
		return msg;
	}

	// 查询统计

	@RequestMapping(value = "/member/statisticsAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String statisticsAjax(HttpServletRequest request) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		MemberInfo info = new MemberInfo();
		info.setIsdel(1);
		// 所有玩家
		int num = this.springMemberService.selectMemberCount(info);
		info.setIsok(-1);
		// 被封玩家
		int bfhynum = this.springMemberService.selectMemberCount(info);
		info.setIsok(1);
		// 活跃参与游戏玩家
		int hynum = this.springMemberService.selectMemberCount(info);
		info = new MemberInfo();
		info.setIsdel(1);
		info.setCreateTime(new Date());
		// 今日新增玩家
		int tdhynum = this.springMemberService.selectMemberCount(info);
		edm.setMessage(num + "," + hynum + "," + bfhynum + "," + tdhynum);
		msg = ConUnit.tojson(edm);
		return msg;
	}

	// 查询最新反馈
	@RequestMapping(value = "/member/selectfeedbackAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectfeedbackAjax(HttpServletRequest request) {
		String msg = "";
		MessageQueryInfo info = new MessageQueryInfo();
		info.setPageSize(10);
		info.setMessageType("3");
		Page page = ConsoleHelper.getInstance().getMsgService().selectMessage(info);
		List list = page.getData();
		List relist = new ArrayList<SysMessage>();
		if (list != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (Object object : list) {
				SysMessage me = (SysMessage) object;
				me.setContent("");
				me.setHfmessage("");
				me.setContent(sdf.format(me.getSendTime()));
				relist.add(me);
			}
			msg = ConUnit.tojson(relist);
		}
		return msg;
	}

	// 查询消息
	@RequestMapping(value = "/member/selectmessageAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectmessageAjax(HttpServletRequest request) {
		String msg = "";
		String metype = request.getParameter("messageType");
		MessageQueryInfo info = new MessageQueryInfo();
		info.setPageSize(10);
		info.setMessageType(metype);
		Page page = ConsoleHelper.getInstance().getMsgService().selectMessage(info);
		List list = page.getData();
		List relist = new ArrayList<SysMessage>();
		if (list != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (Object object : list) {
				SysMessage me = (SysMessage) object;
				me.setContent("");
				me.setHfmessage("");
				me.setContent(sdf.format(me.getSendTime()));
				relist.add(me);
			}
			msg = ConUnit.tojson(relist);
		}
		return msg;
	}

	// 查询升级审批
	@RequestMapping(value = "/member/selectspmessageAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectspmessageAjax(HttpServletRequest request) {
		String msg = "";
		MemberInfo info = new MemberInfo();
		info.setIsdel(1);
		info.setUpgradeState(2);
		List<Member> list = this.springMemberService.selectMemberBy(info);
		List relist = new ArrayList<SysMessage>();
		if (list != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (Member me : list) {
				me.setReferenceQRCodeContent(sdf.format(me.getApplyUpgradeTime()));
				relist.add(me);
			}
			msg = ConUnit.tojson(relist);
		}
		return msg;
	}

	// 查询申请参与游戏审批
	@RequestMapping(value = "/member/selectcymemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectcymemberAjax(HttpServletRequest request) {
		String msg = "";
		MemberInfo info = new MemberInfo();
		info.setIsdel(1);
		info.setIsActivation(1);
		List<Member> list = this.springMemberService.selectMemberBy(info);
		List relist = new ArrayList<SysMessage>();
		if (list != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (Member me : list) {
				me.setReferenceQRCodeContent(sdf.format(me.getApplyTime()));
				relist.add(me);
			}
			msg = ConUnit.tojson(relist);
		}
		return msg;
	}
	
	///////////////////////////////////////////////h5//////////////////////
	/**
	 * H5登陆
	 */
	
	

}
