package com.systemic.gq.member.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.console.entity.Role;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemic.gq.bonus.settlement.SettlementHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.unit.ConUnit;
import com.systemic.unit.ErrorDataMsg;

@Controller
public class MemberController {
	@Autowired
	private ISpringMemberService springMemberService;

	// private static Logger LOG = Logger.getLogger(MemberController.class);
	@RequestMapping(value = "/member/memberManage.do")
	public String memberManage(HttpServletRequest request, HttpServletResponse response, Model model, MemberInfo info) {
		Page page;
		String returnurl="";
		
		try {
			int isActivation=info.getIsActivation();
			if(isActivation==0){
				returnurl="gq/member/memberNotActiveManage";
			}else if(isActivation==1){
				returnurl="gq/member/memberActiveManage";
			}else{
				returnurl="gq/member/memberManage";
			}
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			
			if(!staff.getName().equals("ϵͳ����Ա")){
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

	@RequestMapping(value = "/member/memberEdit.do", method = RequestMethod.GET)
	public String memberEdit(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			String memberId) {
		String node=request.getParameter("node");
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
				if(node!=null&&!"".equals(node)){
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
			Member member=this.springMemberService.saveMermber(info);
			// �Ƿ񼤻�
			int isActivation = info.getIsActivation();
			if (isActivation == 1) {
				/**
				 * ���㽱��
				 */
				SettlementHelper.doBonusSettlementForDisposable(member);
			}
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
		Member member=springMemberService.selectMemberByStaffid(referenceId);
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
		Member member=springMemberService.selectMemberByStaffid(note);
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
			String error = "�ӵ���,����" + region + ",�Ѵ��ڻ�Ա";
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
	@RequestMapping(value="/member/MemberInfo.do",method=RequestMethod.GET)
	public String toMemberInfo(HttpServletRequest request,Model model,MemberInfo info){
		Member member =null;
		if(info.getMemberId()!=null&&!"".equals(info.getMemberId())){
			member=this.springMemberService.selectMemberById(info.getMemberId());
		}else{
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		    member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}
		
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		model.addAttribute("rule", rule);
		model.addAttribute("command", member);
		return "/gq/member/memberUpdate";
	}
	
	
	/**
	 * �޸ĸ�������
	 */
	@RequestMapping(value="/member/MemberInfo.do",method=RequestMethod.POST)
	public String doUpdateMember(HttpServletRequest request,Model model,MemberEditInfo info){
		if(info!=null){
			
			Member member=this.springMemberService.selectMemberByStaffid(info.getStaffId());
			member.setMbwt(info.getMbwt());
			member.setMbwtDn(info.getMbwtDn());
			member.setZsxm(info.getZsxm());
			member.setSfzhm(info.getSfzhm());
			member.setLxdh(info.getLxdh());
			member.setLxdz(info.getLxdz());
			member.setEmail(info.getEmail());
			member.setKhxm(info.getKhxm());
			member.setYhxx(info.getYhxx());
			member.setYhkh(info.getYhkh());
			this.springMemberService.updateMermberInfo(member);
			model.addAttribute("message", "�޸ĳɹ�");
			String logContent = "��IPΪ" +  ConsoleHelper.getUserIp() + "�Ļ�����-�޸ĸ�������";
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_MEMBERINFO, member, logContent);
		}
		return "redirect:../member/MemberInfo.do";
	}
	
	/**
	 * �����Ա
	 */
	@RequestMapping(value="/member/activationMemberAjax.do",produces = "text/plain;charset=gbk")
	@ResponseBody
	public String  activationMemberAjax(HttpServletRequest request,MemberInfo info){
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setMessage("����ʧ��");
		Member member=this.springMemberService.selectMemberById(info.getMemberId());
		if(member!=null){
			member.setActivationTime(new Date());
			member.setIsActivation(1);
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("����ɹ�");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" +  ConsoleHelper.getUserIp() + "�Ļ�����-�����˻�Ա���Ϊ��"+member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_ACTIVATION,loginmember, logContent);
		    
			/**
			 * ���㽱��
			 */
			SettlementHelper.doBonusSettlementForDisposable(member);
		}
		msg=ConUnit.tojson(edm);
		return msg;
	}
	
	/**
	 * ��Ա����
	 */
	@RequestMapping(value="/member/frozenMemberAjax.do",produces = "text/plain;charset=gbk")
	@ResponseBody
	public String frozenMemberAjax(HttpServletRequest request,MemberInfo info){
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setMessage("����ʧ��");
		Member member=this.springMemberService.selectMemberById(info.getMemberId());
		if(member!=null){
			member.setIsok(0);
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("����ɹ�");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" +  ConsoleHelper.getUserIp() + "�Ļ�����-�����˻�Ա���Ϊ��"+member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_FROZEN,loginmember, logContent);
		}
		msg=ConUnit.tojson(edm);
		return msg;
	}
	
	/**
	 * ��Ա�ⶳ
	 */
	@RequestMapping(value="/member/thwaMemberAjax.do",produces = "text/plain;charset=gbk")
	@ResponseBody
	public String thwaMemberAjax(HttpServletRequest request,MemberInfo info){
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setMessage("������ʧ��");
		Member member=this.springMemberService.selectMemberById(info.getMemberId());
		if(member!=null){
			member.setIsok(1);
			this.springMemberService.updateMermberInfo(member);
			edm.setMessage("�������ɹ�");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member loginmember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			String logContent = "��IPΪ" +  ConsoleHelper.getUserIp() + "�Ļ�����-�������˻�Ա���Ϊ��"+member.getStaffId();
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_THAW,loginmember, logContent);
		}
		msg=ConUnit.tojson(edm);
		return msg;
	}
}
