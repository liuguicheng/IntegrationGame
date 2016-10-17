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
			page = this.springMemberService.selectMeber(info);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�����ϵ����Ա��" + e.getMessage());
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
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		model.addAttribute("rule", rule);
		model.addAttribute("token", token);

		return "gq/member/memberEdit";
	}

	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.POST)
	public String memberEditSave(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			MemberEditInfo info) {
		try {
			String bh = queryBh();
			info.setUserName(bh);
			this.springMemberService.saveMermber(info);
			model.addAttribute("message", "����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "����ʧ��" + e.getMessage());
		}
		model.addAttribute("command", info);
		model.addAttribute("token", token);

		// return "redirect:../member/memberManage.do";
		return "redirect:../member/memberManage.do?isActivation=0";
	}

	public String queryBh() {
		// ϵͳ����
		IntegrationGameRule rule = ConsoleHelper.getInstance().getIntegrationGameRuleService()
				.selectIntegrationGameRule();
		if (rule == null || rule.getRegisterLoginNameNum() == null || "".equals(rule.getRegisterLoginNameNum())) {
			throw new RuntimeException("ϵͳ������Ч");
		}
		// ��ұ�� 8λ�������
		String bh = se(rule);
		return bh;
	}

	private String se(IntegrationGameRule rule) {
		// ��ұ�� 8λ�������
		String bh = ConUnit.uid(rule.getRegisterLoginNameNum());
		boolean fa = this.springMemberService.selectMemberByUsername(bh);
		if(fa==true){
			return bh;
		}else{
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
	 * ��ת�鿴��������ҳ��
	 * 
	 */
	@RequestMapping(value = "/member/MemberInfo.do", method = RequestMethod.GET)
	public String toMemberInfo(HttpServletRequest request, Model model, MemberInfo info) {
		Member member = null;
		if (info.getMemberId() != null && !"".equals(info.getMemberId())) {
			member = this.springMemberService.selectMemberById(info.getMemberId());
		} else {
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}

//		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
//		model.addAttribute("rule", rule);
		model.addAttribute("command", member);
		return "/gq/member/memberUpdate";
	}

	/**
	 * �޸ĸ�������
	 */
	@RequestMapping(value = "/member/MemberInfo.do", method = RequestMethod.POST)
	public String doUpdateMember(HttpServletRequest request, Model model, MemberEditInfo info) {
		if (info != null) {
			Member member = this.springMemberService.selectMemberById(info.getId());
			member.setBsid(info.getBsid());
			member.setMbwt(info.getMbwt());
			member.setMbwtDn(info.getMbwtDn());
			member.setLan(info.getLan());
			member.setYong(info.getYong());
			this.springMemberService.updateMermberInfo(member);
			model.addAttribute("message", "�޸ĳɹ�");
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�޸ĸ�������";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_MEMBERINFO,
					member, logContent);
		}
		return "redirect:../member/MemberInfo.do?memberId="+info.getId();
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
			String region = queryRegion(info.getNote());
			if (region.equals("-1")) {
				edm.setMessage("����ʧ��,��ǰ������Ĺ����ڵ����޿�λ�ɷ�,��������������ڵ���!");
			} else {
				member.setRegion(region);
				Member notemember=this.springMemberService.selectMemberByUserName(info.getNoteUsername());
				member.setNotelan(notemember.getNotelan());
				member.setNoteyong(notemember.getNoteyong());
				member.setNoteUsername(notemember.getUserName());
				member.setNote(notemember.getStaffId());
				notemember=null;
				member.setActivationTime(new Date());
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

			}
		}
		msg = ConUnit.tojson(edm);
		return msg;
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
		List<Member> noteMember = this.springMemberService.selectMemberListByNode(note);
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
			if (istrue.equals("2")) {
				// ���ͬ�� �������

				// ���ɶ�ά��
				String qRCodeContent = BarcodeFactory.qRCodeContent + member.getMemberId();
				String qRCodeImageUrl = BarcodeFactory.path + member.getStaffId() + ".png";
				String logourl = BarcodeFactory.logoImgUrl;
				BarcodeFactory.encode(qRCodeContent, 300, 300, logourl, qRCodeImageUrl);

				member.setqRCodeContent(qRCodeContent);
				member.setqRCodeImageUrl(qRCodeImageUrl);
			}
			member.setIsActivation(Integer.parseInt(istrue));
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("��˳ɹ�");
			edm.setCode(1);
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�������ұ��Ϊ��" + member.getStaffId() + "������Ϸ������";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_FROZEN, loginmember,
					logContent);
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
		Member member = this.springMemberService.selectMemberByUserName(info.getUserName());
		if (member != null) {
			edm.setCode(1);
			edm.setMessage(member.getLan() + "," + member.getYong());
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
				String memberlevleId=request.getParameter("memberlevleId");
				Level level= ConsoleHelper.getInstance().getIlevelService().selectlevelByzdtype(memberlevleId);
				Stock stock=ConsoleHelper.getInstance().getStockService().selectStockBygradeNum(level.getV1_zdtype());
				member.setLevleId(level.getId());
				member.setStock(stock);
				member.setProductgradeId(stock.getId());
				this.springMemberService.updateMermberInfo(member);
				edm.setMessage("�޸ĳɹ�");
				edm.setCode(1);
				// �����־
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member loginmember = ConsoleHelper.getInstance().getManageService()
						.selectMemberByStaffId(staff.getId());
				String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ�����-�޸���Ҽ���,��ұ��Ϊ��" + member.getStaffId();
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("�޸���Ҽ���",
						loginmember, logContent);
		}
		msg = ConUnit.tojson(edm);
		return msg;
	}
}
