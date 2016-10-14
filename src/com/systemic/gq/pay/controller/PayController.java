package com.systemic.gq.pay.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;
import com.systemic.gq.pay.service.ISpringPayService;

@Controller
@RequestMapping(value = "/pay")
public class PayController {
	@Autowired
	private ISpringPayService springPayService;

	/**
	 * ֧��������ͼ
	 * 
	 * @param request
	 * @param model
	 * @param info
	 * @param viewType
	 * @return
	 */
	public String payManageView(HttpServletRequest request, Model model, PayQueryInfo info, String viewType) {
		Page page = this.springPayService.selectPay(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		model.addAttribute("command", info);
		return "gq/pay/pay" + viewType;

	}

	/**
	 * ��ֵ����
	 * ����Ա���ʵ�
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payManage.do")
	public String payManage(HttpServletRequest request, HttpServletResponse response, Model model, PayQueryInfo info) {
		return payManageView(request, model, info, "Manage");
	}
	/**
	 * ��ֵ����
	 * ��Ա�Լ��鿴�Լ����б�
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payMember.do")
	public String payMember(HttpServletRequest request, HttpServletResponse response, Model model, PayQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// ֻ��ȡ�Լ�
				info.setMemberId(member.getMemberId());
				model.addAttribute("member", member);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ��Աû��staffId����
			throw new RuntimeException("��ȡ�����б��������ϵ����Ա��");
		}
		return payManageView(request, model, info, "Manage");
	}

	/**
	 * �༭��ֵ����
	 * 
	 * @param request
	 * @param response
	 * @param numberId
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/payEdit.do", method = RequestMethod.GET)
	public String payEdit(HttpServletRequest request, HttpServletResponse response, String numberId, Model model,
			Long token) {
		MemberEditInfo command = new MemberEditInfo();
		if (StringUtils.isNotBlank(numberId)) {
			model.addAttribute("command", this.springPayService.selectPay(numberId));
		} else {
			command.setCreateTime(new Date());
			model.addAttribute("command", command);
		}
		model.addAttribute("token", token);
		return "gq/pay/payEdit";
	}

//	/**
//	 * �����ֵ���롢��˳�ֵ
//	 * 
//	 * @param request
//	 * @param response
//	 * @param editType
//	 * @param model
//	 * @param token
//	 * @param info
//	 * @return
//	 */
//	@RequestMapping(value = "/payEdit.do", method = RequestMethod.POST)
//	public String paySaveEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable String editType,
//			Model model, Long token, PayInfoEdit info) {
//		Member member = selectMember(request);
//		String logContent = "";
//		try {
//			// this.springPayService.savePay(info);
//			logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "������-����" + info.getRecordType() + "��ֵ";
//			model.addAttribute("message", "�ύ�ɹ���");
//			PayInfo pay = this.springPayService.selectPay(info.getNumberId());
//			ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent, member, pay);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "�ύʧ�ܣ�");
//		}
//		model.addAttribute("command", info);
//		model.addAttribute("token", token);
//		return "redirect:../pay/payManage.do";
//	}

	@RequestMapping(value = "/auditManage.do")
	public String auditManage(HttpServletRequest request, Model model, PayQueryInfo info) {
		return payManageView(request, model, info, "AuditManage");
	}
    
	/**
	 * �����ֵ
	 * @param request
	 * @param response
	 * @param model
	 * @param token
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/ajaxEditPay.do")
	@ResponseBody
	public String ajaxEditPay(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			PayInfoEdit info) {
		String staffId = request.getParameter("memnberStaffId");
		String memberId=request.getParameter("memberId");
		if (staffId != null && !"".equals(staffId)) {

			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffId);
			try {
				if(member==null){
					model.addAttribute("message", "��Ա��Ų����ڣ�");
					return "0";
				}
				info.setApplyState(ConsoleHelper.YES);
				this.springPayService.savePay(info, member);

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "�ύʧ�ܣ�");

			}
			model.addAttribute("command", info);
			model.addAttribute("token", token);
			if(memberId!=null&&!"".equals(memberId)){
				//����2 ��ת����Ա�Լ��ĳ�ֵ�б�
				return "2";
			}
			//����1 ��ת������Ա�����ĳ�ֵ�б�ҳ��
			return "1";
		}
		return "0";
	}
   
	@RequestMapping(value = "/payReview.do", method = RequestMethod.GET)
	public String topayReview(HttpServletRequest request, HttpServletResponse response, Model model) {
		String numberid = request.getParameter("numberId");
		PayInfo payinfo = this.springPayService.selectPay(numberid);
		model.addAttribute("payinfo", payinfo);
		return "gq/pay/payReview";
	}

	/**
	 * ��ֵ���
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payReview.do", method = RequestMethod.POST)
	@ResponseBody
	public String dopayReview(HttpServletRequest request, HttpServletResponse response, Model model, PayInfoEdit info) {
		String type = request.getParameter("istrue");
		String jg="��ͬ��";
		PayInfo payinfo = this.springPayService.selectPay(info.getNumberId());
		
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		if (payinfo != null) {
			payinfo.setHandleId(member.getMemberId());
			payinfo.setMemnberStaffId(member.getStaffId());
			payinfo.setApplyState(type);
			payinfo.setAuditTime(new Date());
			payinfo.setAuditIp(ConsoleHelper.getUserIp());
			this.springPayService.updatePayInfo(payinfo);
			model.addAttribute("message", "�����ɹ�");
			if ("2".equals(type)) {
				// ���ͬ�� �Ÿ��»�Ա��Ǯ
				//���»�Ա���ӱ�
				updateMemberElectroniccurency(payinfo);
				jg="ͬ��";
			}
			//���paylog
			addPayLog(payinfo,jg);
		}

		return "1";
	}

	private void addPayLog(PayInfo info,String jg) {
		String logContent ="�û����Ϊ:"+info.getMemnberStaffId()+"�û�,��IPΪ"+info.getApplyLocalHost()+"������-�����ֵ��"+
	      "��ֵ���Ϊ:"+info.getApplyRecordNum()+"!�����:"+info.getHandleId()+",��ipΪ"+info.getAuditIp()+"�����������,��˽��Ϊ:"+jg;
		String operationType="1";
		String memberId=info.getMemnberStaffId();
		double money=info.getApplyRecordNum();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}

	private void updateMemberElectroniccurency(PayInfo payinfo) {
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(payinfo.getMemnberStaffId());
		
		Double money=member.getElectroniccurrency();
		if(money==null){
			money=0.00;
		}
		member.setElectroniccurrency(money + payinfo.getApplyRecordNum());
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
	}
	
	

	

}
