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
	 * ����Ա ����б�
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

			if (!staff.getName().equals("ϵͳ����Ա")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * δ��������б�
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

			if (!staff.getName().equals("ϵͳ����Ա")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(0);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * �Ѳ�������б�
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

			if (!staff.getName().equals("ϵͳ����Ա")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(2);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * 
	 * �����������б�
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

			if (!staff.getName().equals("ϵͳ����Ա")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(1);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * ������������б�
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

			if (!staff.getName().equals("ϵͳ����Ա")) {
				info.setReferenceId(staff.getId());
			}
			info.setIsActivation(2);
			info.setUpgradeState(2);
			info.setIsdel(1);
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}
	
	/**
	 * ����ʱ
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
			if (!staff.getName().equals("ϵͳ����Ա")) {
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
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}
	/**
	 * ��ȡ���µ���ʱ����
	 */
	
	@RequestMapping(value = "/member/djsAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String djsAjax(HttpServletRequest request) {
		String msg = "";
		return msg;
	}
	
	/**
	 * ȥע��
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
		} else {// ����Ĭ����
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
				throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��");
			}
			model.addAttribute("command", command);
		}
		model.addAttribute("token", token);
		model.addAttribute("message", message);
		return "gq/member/memberEdit";
	}

	/**
	 * ע��
	 */
	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.POST)
	public String memberEditSave(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			MemberEditInfo info) {
		String url = "redirect:../member/memberEdit.do";
		try {

			// ��֤�Ƽ���״̬�Ƿ�����
			boolean fa = VerificationReferenceMemberStatus(info.getReferenceId());
			if (fa == false) {
				model.addAttribute("message", "�Ƽ���״̬�쳣,ע��ʧ��!");
				return url;
			}
			// ϵͳ����
			IntegrationGameRule rule = MemberQuartz.queryRule();
			String bh = queryBh(rule);
			info.setUserName(bh);
			info.setUpdateInfoNum(0);
			info.setUpgradeState(0);
			info.setApplyUpgradeNum(0);
			info.setRzstatus(0);
			this.springMemberService.saveMermber(info);
			model.addAttribute("message", "����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "����ʧ��" + e.getMessage());
		}
		model.addAttribute("command", info);
		model.addAttribute("token", token);

		return url;
	}

	/**
	 * ajax ע��
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
		edm.setMessage("ע��ʧ��");
		try {
			String code = request.getParameter("code");
			String rand = (String) request.getSession().getAttribute("rand");
			if (!rand.equalsIgnoreCase(code)) {
				edm.setMessage("��֤�����");
				msg = ConUnit.tojson(edm);
				return msg;
			}
			// ��֤�Ƽ���״̬�Ƿ�����
			boolean fa = VerificationReferenceMemberStatus(info.getReferenceId());
			if (fa == false) {
				edm.setMessage("�Ƽ���״̬�쳣,ע��ʧ��!");
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
				edm.setMessage("�ǳơ�"
						+ info.getBsid() + "���ѱ�ʹ�ã�");
				msg = ConUnit.tojson(edm);
				return msg;
				
			}
			// ϵͳ����
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

	// ��ұ�� 8λ�������
	public String queryBh(IntegrationGameRule rule) {
		String bh = se(rule);
		return bh;
	}

	private String se(IntegrationGameRule rule) {
		// ��ұ�� 8λ�������
		String bh = ConUnit.uid(rule.getRegisterLoginNameNum());
		boolean fa = this.springMemberService.selectMemberByUsername(bh);
		if (fa == true) {
			return bh;
		} else {
			return se(rule);
		}
	}

	/**
	 * ��֤�ǳ��Ƿ����
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
			String error = "�ǳơ�" + infos.getBsid() + "���ѱ�ʹ�ã�";
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
			String error = "��¼����" + username + "���ѱ�ʹ�ã�";
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

	// ��֤�Ƽ����Ƿ����
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
			String error = "�Ƽ��˱�Ų�����,���ʵ";
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
			String error = "�ӵ��˱�Ų�����,���ʵ";
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
			String error = "�ӵ���,����" + region + ",�Ѵ������";
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
	 * ɾ�����
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
			model.addAttribute("message", "ɾ���ɹ�");
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
	 * ��ת�鿴��������ҳ��
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
		if (staff.getName().equals("ϵͳ����Ա")) {
			view = "/gq/member/memberUpdatefomanage";
		}

		model.addAttribute("command", member);
		return view;
	}

	/**
	 * �޸ĸ�������
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
			model.addAttribute("message", "�޸ĳɹ�");
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�޸ĸ�������";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_MEMBERINFO,
					member, logContent);
		}
		return "redirect:../member/MemberInfo.do?memberId=" + info.getId();
	}

	/**
	 * ��Ҷ���
	 */
	@RequestMapping(value = "/member/frozenMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String frozenMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("����ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setIsok(0);
			this.springMemberService.updateMermberInfo(member);
			ConsoleHelper.getInstance().getMainService().doFrozen(member.getUserName());
			edm.setMessage("����ɹ�");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-��������ұ��Ϊ��" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_FROZEN, loginmember,
					logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ��ҽⶳ
	 */
	@RequestMapping(value = "/member/thwaMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String thwaMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("������ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setIsok(1);
			this.springMemberService.updateMermberInfo(member);
			Staff memberstaff = ConsoleHelper.getInstance().getMainService().selectStaff(member.getUserName());
			ConsoleHelper.getInstance().getMainService().doUnlock(memberstaff);
			edm.setMessage("�������ɹ�");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-��������,��ұ��Ϊ��" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_THAW, loginmember,
					logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ȥ���������Ϸҳ��
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
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
		}
		model.addAttribute("message", request.getParameter("message"));
		return returnurl;
	}

	/**
	 * ���������Ϸ
	 */
	@RequestMapping(value = "/member/activationMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String activationMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("����ʧ��");
		Member member = this.springMemberService.selectMemberByStaffid(info.getStaffId());
		if (member != null) {
			if (info.getNoteUsername().equals(member.getUserName())) {
				edm.setMessage("����ʧ��,��������������,��������������ڵ���!");
			} else if (member.getIsok() != 1) {
				edm.setMessage("����ʧ��,��ǰ���״̬�쳣,��������������ڵ���!");
			} else {
				String region = queryRegion(info.getNoteUsername());
				if (region.equals("-1")) {
					edm.setMessage("����ʧ��,��ǰ������Ĺ����ڵ����޿�λ�ɷ�,��������������ڵ���!");
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
					edm.setMessage("����ɹ�");
					edm.setCode(1);
					// �����־
					Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
					Member loginmember = ConsoleHelper.getInstance().getManageService()
							.selectMemberByStaffId(staff.getId());
					String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-���������Ϸ,��ұ��Ϊ��" + member.getStaffId();
					ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_ACTIVATION,
							loginmember, logContent);

					// ������Ѽ�¼
					String title = "��������������Ϸ����";
					String content = "����ǳ�" + member.getBsid() + "���������Ϸ,�Ƿ�ͨ�����";
					sendMsg(member, member.getReferenceName(), title, content);
					sendMsg(member, member.getNoteUsername(), title, content);
				}
			}
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ����ʼ�
	 * 
	 * @param member
	 *            �l����
	 * @param receiveMan
	 *            ������
	 * @param title
	 *            ���}
	 * @param content
	 *            ����
	 */
	private void sendMsg(Member member, String receiveMan, String title, String content) {
		ConsoleHelper.getInstance().getMsgService().insertMessageForEmail(receiveMan, content, title, "1",
				member.getBsid(), member.getUserName());
	}

	/**
	 * ���ݽڵ��ѯ �ڵ� �·��ɷŽڵ�
	 * 
	 * @param note
	 * @return
	 */
	private String queryRegion(String note) {
		String region = "";
		// ��ѯ�����ڵ���Ϣ
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
	 * ��� --����μ���Ϸ
	 */
	@RequestMapping(value = "/member/fauditMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String auditMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setMessage("���ʧ��");
		edm.setCode(0);
		String istrue = request.getParameter("istrue");
		Member member = this.springMemberService.selectMemberByStaffid(info.getStaffId());
		if (member != null) {
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (istrue.equals("2")) {
				// ���ͬ�� �������
				BonusRecordHelper.doComputationalIntegral(member);
				// ���ɶ�ά��
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
				// �޸Ľ�ɫ �ο�-�����
				Staff mestaff = ConsoleHelper.getInstance().getMainService().selectStaffById(member.getStaffId());
				this.springMemberService.updateRole(mestaff);

				// �����Ϣ
				String title = "�����ע������";
				String content = "�б��" + member.getUserName() + "����Ҽ���,�Ƽ�����" + member.getReferenceName() + ",��������"
						+ member.getNoteUsername() + ",��֪Ϥ";
				sendMsg(member, member.getReferenceName(), title, content);
				sendMsg(member, member.getNoteUsername(), title, content);
			} else {
				// ��� ˫��
				// ������
				member.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
				// �Ƽ���
				Member refmember = this.springMemberService.selectMemberByUserName(member.getReferenceName());
				// ����ǹ���Ա �Ͳ����
				if (!refmember.getUserName().equals("99999999")) {
					refmember.setIsok(Integer.parseInt(ConsoleHelper.LUCK));
					this.springMemberService.updateMermberInfo(refmember);
					String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�������ұ��Ϊ��" + member.getStaffId()
							+ ",��˲�ͨ��,˫���˺ű���";
					ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("����μ���Ϸ���", refmember,
							logContent);
				}
			}
			member.setIsActivation(Integer.parseInt(istrue));

			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("��˳ɹ�");
			edm.setCode(1);

			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�������ұ��Ϊ��" + member.getStaffId() + "������Ϸ������";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("����μ���Ϸ���", loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ���ݱ�Ų�ѯ�����Ϣ
	 */
	@RequestMapping(value = "/member/memberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String memberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("�����㲻����,�����������������!");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		if (member != null) {
			if (member.getIsok() != 1) {
				edm.setMessage("�������˺��쳣,���ܷ������,�����������������!");
			} else if (member.getIsActivation() != 2) {
				edm.setMessage("����Ĺ�����δ������Ϸ,���ܷ������,�����������������!");

			} else {
				if (!staff.getName().equals("ϵͳ����Ա")) {
					if (info.getUserName().equals(loginmember.getUserName())) {
						edm.setMessage("����Ĺ����㲻��Ϊ�Լ�,�����������������!");
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
	 * �ֶ����ɶ�ά��
	 */

	@RequestMapping(value = "/member/memberQRCodeAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String memberQRCodeAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			// ���ɶ�ά��
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
	 * �޸���Ҽ��𣨺�̨����Ա��
	 */
	@RequestMapping(value = "/member/upLevelMemberAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String upLevelMemberAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("�޸�ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			String memberlevleId = request.getParameter("memberlevleId");
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(memberlevleId);
			Stock stock = ConsoleHelper.getInstance().getStockService().selectStockBygradeNum(level.getV1_zdtype());
			member.setLevleId(level.getId());
			member.setStock(stock);
			member.setProductgradeId(stock.getId());
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("�޸ĳɹ�");
			edm.setCode(1);
			// �����־
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�޸���Ҽ���,��ұ��Ϊ��" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�޸���Ҽ���", loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ��������
	 */
	@RequestMapping(value = "/member/applyGradeLevelAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String applyGradeLevelAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("����ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			// �����û�����Ѱ�����������
			String auditGradeUserName = queryAuditGradeUserName(member, level);
			member.setAuditGradeUserName(auditGradeUserName);
			member.setApplyUpgradeTime(new Date());
			member.setUpgradeState(2);
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("����ɹ�");
			edm.setCode(1);
			// �ͳ�����-�������
			BonusRecordHelper.doComputationalIntegralforApply(member.getUserName(), auditGradeUserName, level);
			// �����־
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-��������,��ұ��Ϊ��" + member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("��������", loginmember, logContent);

			// ������Ѽ�¼
			String title = "�����������";
			String content = "��" + member.getStock().getGradeNum() + "�����Ϊ" + member.getUserName() + "�������������Ϊ("
					+ (Integer.parseInt(member.getStock().getGradeNum()) + 1) + ")�����Ƿ�ͨ�������";
			sendMsg(member, auditGradeUserName, title, content);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	// �����û�����Ѱ�����������
	private String queryAuditGradeUserName(Member member, Level level) {
		String returnUserName = "";
		// ���������Ѱ������
		int num = level.getV1_upgrade_tj();
		int levelnum = Integer.parseInt(level.getV1_zdtype());
		// ������
		returnUserName = loopSearchMember(num, levelnum, member);

		return returnUserName;
	}

	/**
	 * �ݹ��ѯ ���������
	 * 
	 * @param num
	 *            ѭ������
	 * @param levelnum
	 *            �����˵ĵȼ�
	 * @param member
	 *            ��һ�δ����������� �ڶ�������һ�β�ѯ�������Ϣ
	 * @return
	 */
	private String loopSearchMember(int num, int levelnum, Member member) {
		Member nodemember = this.springMemberService.selectMemberByUserName(member.getNoteUsername());
		String username = nodemember.getUserName();
		int nodelevelnum = Integer.parseInt(nodemember.getStock().getGradeNum());
		if (nodemember.getUserName().equals("99999999")) {
			// ����ѵ�����
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
	 * �������
	 */
	@RequestMapping(value = "/member/upgradeAudit.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String upgradeAudit(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("���ʧ��");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		if (member != null) {
			Level level = ConsoleHelper.getInstance().getIlevelService().selectlevel(member.getLevleId());
			if (level.getV1_upgrade_num() == member.getApplyUpgradeNum()) {
				// �޸�����״̬
				member.setUpgradeState(0);
				// �����������
				member.setApplyUpgradeNum(0);
				// �����������ʱ��
				member.setApplyUpgradeTime(null);
				// ���ʱ��
				member.setAuditGradeTime(new Date());
				// �޸ĵȼ�
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
				edm.setMessage("��˳ɹ�");
				edm.setCode(1);
				// �����־
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member loginmember = ConsoleHelper.getInstance().getManageService()
						.selectMemberByStaffId(staff.getId());
				String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�����������,������ұ��Ϊ��" + member.getStaffId();
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�����������", loginmember, logContent);
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * Т����֤����
	 */
	@RequestMapping(value = "/member/xzrzAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String xzrzAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("����ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			if (member.getRzstatus() == 0) {
				member.setRzstatus(1);
				edm.setCode(1);
				edm.setMessage("����ɹ�");
				this.springMemberService.update(member);
			} else {
				edm.setMessage("���˺����������֤������,�����ظ�����!");
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * Т����֤���
	 */

	@RequestMapping(value = "/member/zxAudit.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String zxAudit(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("���ʧ��");
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		if (member != null) {
			if (member.getRzstatus() == 1) {
				member.setRzstatus(2);
				edm.setCode(1);
				edm.setMessage("��˳ɹ�");
				this.springMemberService.update(member);
			}

		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	/**
	 * ��������
	 */
	@RequestMapping(value = "/member/resetPasswordAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String resetPasswordAjax(HttpServletRequest request, MemberInfo info) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		edm.setMessage("��������ʧ��");
		Member member = this.springMemberService.selectMemberById(info.getMemberId());
		if (member != null) {
			member.setPassword("666666");
			edm.setCode(1);
			edm.setMessage("��������ɹ�,���ú�����Ϊ:666666");
			this.springMemberService.updateMermber(member);

			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-��������ұ��:" + member.getUserName() + "��½����";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_LOGINPASSWORD,
					loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}

	///////////////////////////////////////// ��ҳ��ѯ/////////////////////////////////////////////////////

	// ��ѯ��¼�û�
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

	// ��ѯͳ��

	@RequestMapping(value = "/member/statisticsAjax.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String statisticsAjax(HttpServletRequest request) {
		String msg = "";
		ErrorDataMsg edm = new ErrorDataMsg();
		MemberInfo info = new MemberInfo();
		info.setIsdel(1);
		// �������
		int num = this.springMemberService.selectMemberCount(info);
		info.setIsok(-1);
		// �������
		int bfhynum = this.springMemberService.selectMemberCount(info);
		info.setIsok(1);
		// ��Ծ������Ϸ���
		int hynum = this.springMemberService.selectMemberCount(info);
		info = new MemberInfo();
		info.setIsdel(1);
		info.setCreateTime(new Date());
		// �����������
		int tdhynum = this.springMemberService.selectMemberCount(info);
		edm.setMessage(num + "," + hynum + "," + bfhynum + "," + tdhynum);
		msg = ConUnit.tojson(edm);
		return msg;
	}

	// ��ѯ���·���
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

	// ��ѯ��Ϣ
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

	// ��ѯ��������
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

	// ��ѯ���������Ϸ����
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
	 * H5��½
	 */
	
	

}
