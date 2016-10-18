package com.systemic.gq.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemic.gq.bonus.settlement.SettlementHelper;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Level;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
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

	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.GET)
	public String memberEdit(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			String memberId) {
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

		return "gq/member/memberEdit";
	}

	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.POST)
	public String memberEditSave(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			MemberEditInfo info) {
		String url="gq/member/memberEdit";
		try {
			
			//验证推荐人状态是否正常
			boolean fa=VerificationReferenceMemberStatus(info.getReferenceId());
			if(fa==false){
				model.addAttribute("message", "推荐人状态异常,注册失败!");
				return url;
			}
			// 系统设置
			IntegrationGameRule rule = MemberQuartz.queryRule();
			String bh = queryBh(rule);
			info.setUserName(bh);
			info.setUpdateInfoNum(0);
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

	private boolean  VerificationReferenceMemberStatus(String referenceId) {
		boolean fa=true;
		Member referencemember=this.springMemberService.selectMemberByStaffid(referenceId);
		if(referencemember.getIsok()!=1){
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
		if(fa==true){
			return bh;
		}else{
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

	@RequestMapping(value = "/member/memberDelete.do", method = RequestMethod.POST)
	public String memberDelete(HttpServletRequest request, String[] memberId, Long token, Model model) {
		if (memberId != null && memberId.length > 0) {
			this.springMemberService.deleteMember(memberId);
		}
		model.addAttribute("token", token);
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
		String view="/gq/member/memberUpdate";
		if (info.getMemberId() != null && !"".equals(info.getMemberId())) {
			member = this.springMemberService.selectMemberById(info.getMemberId());
		} else {
			member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}
		if (staff.getName().equals("系统管理员")) {
			view="/gq/member/memberUpdatefomanage";
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
		return "redirect:../member/MemberInfo.do?memberId="+info.getId();
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
			String region = queryRegion(info.getNoteUsername());
			if (region.equals("-1")) {
				edm.setMessage("申请失败,当前您输入的归属节点下无空位可放,请重新输入归属节点编号!");
			} else {
				member.setRegion(region);
				Member notemember=this.springMemberService.selectMemberByUserName(info.getNoteUsername());
				member.setNotelan(notemember.getNotelan());
				member.setNoteyong(notemember.getNoteyong());
				member.setNoteUsername(notemember.getUserName());
				member.setNote(notemember.getStaffId());
				notemember=null;
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

			}
		}
		msg = ConUnit.tojson(edm);
		return msg;
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
			if (istrue.equals("2")) {
				// 审核同意 计算积分

				// 生成二维码
				String qRCodeContent = BarcodeFactory.qRCodeContent + member.getMemberId();
				String qRCodeImageUrl = BarcodeFactory.path + member.getStaffId() + ".png";
				String logourl = BarcodeFactory.logoImgUrl;
				BarcodeFactory.encode(qRCodeContent, 300, 300, logourl, qRCodeImageUrl);

				member.setqRCodeContent(qRCodeContent);
				member.setqRCodeImageUrl(qRCodeImageUrl);
				member.setActivationTime(new Date());
			}
			member.setIsActivation(Integer.parseInt(istrue));
			
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("审核成功");
			edm.setCode(1);
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-审核了玩家编号为：" + member.getStaffId() + "参与游戏的申请";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_FROZEN, loginmember,
					logContent);
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
		if (member != null) {
			if(member.getIsok()!=1){
				edm.setMessage("归属点账号异常,不能放置玩家,请重新输入归属点编号!");
			}else{
				edm.setCode(1);
				edm.setMessage(member.getLan() + "," + member.getYong());
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
			BarcodeFactory.encode(qRCodeContent, 300, 300, logourl, qRCodeImageUrl);
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
				String memberlevleId=request.getParameter("memberlevleId");
				Level level= ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(memberlevleId);
				Stock stock=ConsoleHelper.getInstance().getStockService().selectStockBygradeNum(level.getV1_zdtype());
				member.setLevleId(level.getId());
				member.setStock(stock);
				member.setProductgradeId(stock.getId());
				this.springMemberService.updateMermberInfo(member);
				edm.setMessage("修改成功");
				edm.setCode(1);
				// 添加日志
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member loginmember = ConsoleHelper.getInstance().getManageService()
						.selectMemberByStaffId(staff.getId());
				String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-修改玩家级别,玩家编号为：" + member.getStaffId();
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("修改玩家级别",
						loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}
}
