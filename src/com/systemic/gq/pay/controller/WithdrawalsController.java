package com.systemic.gq.pay.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsEdit;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;
import com.systemic.gq.pay.service.ISpringWithdrawalsService;
import com.systemic.unit.ConUnit;

@Controller
@RequestMapping(value = "/withdrawals")
public class WithdrawalsController {

	@Autowired
	private ISpringWithdrawalsService springwithdrawalsService;

	/**
	 * @param springwithdrawalsService
	 *            the springwithdrawalsService to set
	 */
	public void setSpringwithdrawalsService(ISpringWithdrawalsService springwithdrawalsService) {
		this.springwithdrawalsService = springwithdrawalsService;
	}

	public String ManageView(HttpServletRequest request, Model model, WithdrawalsQueryInfo info, String viewType) {
		Page page = this.springwithdrawalsService.selectWithdrawalsLog(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		model.addAttribute("command", info);
		return "gq/pay/withdrawals" + viewType;

	}

	@RequestMapping(value = "/withdrawalsManage.do")
	public String withdrawalsManage(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsQueryInfo info) {
		return ManageView(request, model, info, "Manage");
	}

	@RequestMapping(value = "/withdrawalsMember.do")
	public String withdrawalsMember(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// ֻ��ȡ�Լ�
				info.setMemberstaffid(member.getStaffId());
				model.addAttribute("member", member);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// ��Աû��staffId����
			throw new RuntimeException("��ȡ�����б��������ϵ����Ա��");
		}
		return ManageView(request, model, info, "Manage");
	}

	/**
	 * ��������
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param withdrawals
	 * @return
	 */
	@RequestMapping(value = "/ajaxEditwithdrawals.do")
	@ResponseBody
	public String ajaxEditwithdrawals(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsEdit withdrawals) {
		String staffId = request.getParameter("memberstaffid");
		String memberId = request.getParameter("memberId");
		if (staffId != null && !"".equals(staffId)) {

			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffId);
			try {
				if (member == null) {
					model.addAttribute("message", "��Ա��Ų����ڣ�");
					return "0";
				}

				// ��֤����
				String returnstr = doValidationRule(model, withdrawals, member);
				if (!"".equals(returnstr)) {
					return returnstr;
				}

				// ��ȡ����
				int withdrawals_counter_fee = selbl();
				if (withdrawals_counter_fee == 0) {
					withdrawals.setActualMoeny(withdrawals.getWMoney());
					withdrawals.setWCounterfree(0);
				} else {
					// �����Ѱٷֱ�
					double bfb = ConUnit.takePercentage(withdrawals_counter_fee, 100D);
					// ������
					double sxf = withdrawals.getWMoney() * bfb;
					// ȥ��������
					double actualMoeny = withdrawals.getWMoney() - sxf;
					withdrawals.setActualMoeny(actualMoeny);
					withdrawals.setWCounterfree(withdrawals_counter_fee);
				}
				withdrawals.setBankUsername(ConUnit.toutf(withdrawals.getBankUsername()));
				withdrawals.setStatus(ConsoleHelper.YES);
				withdrawals.setMemberUsername(member.getUserName());
				this.springwithdrawalsService.saveWithdrawals(withdrawals);

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "�ύʧ��");
				return "0";

			}
			if (memberId != null && !"".equals(memberId)) {
				// ����2 ��ת����Ա�Լ��ĳ�ֵ�б�
				return "2";
			}
			// ����1 ��ת������Ա�����ĳ�ֵ�б�ҳ��
			return "1";
		}
		return "0";
	}

	// ��ȡ���ֱ���
	private int selbl() {
		int withdrawals_counter_fee = 0;
		// ��ѯϵͳ���� ��ȡ����������
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		if (rule != null) {
			// ���������� Ϊ0ʱ������
			withdrawals_counter_fee = rule.getWithdrawals_counter_fee();
		}
		return withdrawals_counter_fee;
	}

	// ��֤�������й���
	private String doValidationRule(Model model, WithdrawalsEdit withdrawals, Member member) {
		String returnstr = "";
		// ��ѯϵͳ���� ��ȡ����������
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		if (rule != null) {
			//�жϽ����Ƿ���� ����
			String withdrawlastime=rule.getWithdrawals_time();
			int todayWeek= ConUnit.dayForWeek(new Date());
			if(withdrawlastime.indexOf(String.valueOf(todayWeek))<0){
				//���ղ�������
				returnstr = "-7";
				return returnstr;
			}
			
			
			// ���������� Ϊ0ʱ������
			// ����ÿ�����޴��� Ϊ0ʱ������
			int withdrawals_count = rule.getWithdrawals_count();
			// ����ÿ�ս�� Ϊ0ʱ������
			int withdrawals_money = rule.getWithdrawals_money();
			if (withdrawals.getWMoney() > withdrawals_money) {
				// ���ֽ����涨���
				model.addAttribute("message", "�������ֽ���ѳ����޶���");
				returnstr = "-1";
				return returnstr;
			}
			if (withdrawals_count != 0 || withdrawals_money != 0) {
				// ��ѯ���ּ�¼ �����������գ�
				WithdrawalsQueryInfo info = new WithdrawalsQueryInfo();
				info.setApplyTime(new Date());
				List<Withdrawals> list = this.springwithdrawalsService.selectWithdrawalsList(info);
				if (list != null && !list.isEmpty()) {
					if (list.size() >= withdrawals_count) {
						model.addAttribute("message", "�û�Ա�������ִ����Ѵ�����,�������ٽ����������룡");
						returnstr = "-2";
						return returnstr;
					}
					double totalMoney = 0;
					for (Withdrawals w : list) {
						totalMoney += w.getWMoney();
					}
					// ���ϱ������ֽ��
					totalMoney += withdrawals.getWMoney();
					// �ж�ϵͳ����ÿ������ �Ƿ񳬳�
					if (totalMoney >= withdrawals_money) {
						model.addAttribute("message", "�û�Ա�������ֽ���Ѵ�����,�������ٽ����������룡");
						returnstr = "-3";
						return returnstr;
					}

					// �ж����
					String returstr = pdYe(withdrawals, member);
					if (!"".equals(returstr)) {
						returnstr = returstr;
						return returnstr;
					}
				}
			}

		}
		return returnstr;
	}

	// �ж����
	private String pdYe(WithdrawalsEdit withdrawals, Member member) {
		String returnstr = "";
		// ��ȡ���ֱ�������
		String waccounttype = withdrawals.getWAccountType();
		switch (waccounttype) {
		case "1":
			// ���ӱ�
			// �ж��Ƿ�����ؽ�Ǯ��������
			if (withdrawals.getWMoney() > member.getElectroniccurrency()) {
				returnstr = "-4";
			}
			break;
		case "2":
			// �����
			// �ж��Ƿ�����ؽ�Ǯ��������
			if (withdrawals.getWMoney() > member.getGoldAward()) {
				returnstr = "-4";
			}
			break;
		}

		return returnstr;
	}

	/**
	 * to���
	 */
	@RequestMapping(value = "/withdrawalsReview.do", method = RequestMethod.GET)
	public String towithdrawalsReview(HttpServletRequest request, HttpServletResponse response, Model model) {
		String WId = request.getParameter("WId");
		Withdrawals withdrawals = this.springwithdrawalsService.selectWithdrawalsById(WId);
		model.addAttribute("withdrawals", withdrawals);
		return "gq/pay/withdrawalsReview";
	}

	/**
	 * do���
	 */
	@RequestMapping(value = "/withdrawalsReview.do", method = RequestMethod.POST)
	@ResponseBody
	public String dowithdrawalsReview(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsEdit wedit) {
		String type = request.getParameter("istrue");
		String jg = "��ͬ��";
		Withdrawals withdrawals = this.springwithdrawalsService.selectWithdrawalsById(wedit.getWId());
		if (withdrawals != null) {
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			withdrawals.setAuditIp(ConsoleHelper.getUserIp());
			withdrawals.setAuditTime(new Date());
			withdrawals.setHandleId(member.getStaffId());
			withdrawals.setStatus(type);
			this.springwithdrawalsService.updateWithdrawals(withdrawals);
			model.addAttribute("message", "�����ɹ�");
			if ("2".equals(type)) {
				// ���ͬ�� �Ÿ��»�Ա��Ǯ
				// ���»�Ա���ӱ�
				updateMemberForMoney(withdrawals);
				jg = "ͬ��";
			}
			// ���paylog
			addPayLog(withdrawals, jg);

		}
		return "1";
	}

	private void updateMemberForMoney(Withdrawals withdrawlas) {

		Member member = ConsoleHelper.getInstance().getManageService()
				.selectMemberByStaffId(withdrawlas.getMemberstaffid());
		String type = withdrawlas.getWAccountType();
		Double money=0.0;
		switch (type) {
		case "1":
		    money = member.getElectroniccurrency();
			if (money == null) {
				money = 0.00;
			}
			member.setElectroniccurrency(money -withdrawlas.getWMoney());
			break;
		case "2":
			money = member.getGoldAward();
			if (money == null) {
				money = 0.00;
			}
			member.setGoldAward(money -withdrawlas.getWMoney());
			break;
		case "3":

			break;
		default:
			break;
		}
		
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
	}
	
	private void addPayLog(Withdrawals withdrawals,String jg) {
		String logContent ="�û����Ϊ:"+withdrawals.getMemberstaffid()+"�û�,��IPΪ"+withdrawals.getApplyLocalHost()+"������-�������֡�"+
	      "���ֽ��Ϊ:"+withdrawals.getWMoney()+"!�����:"+withdrawals.getHandleId()+",��ipΪ"+ConsoleHelper.getUserIp()+"�����������,��˽��Ϊ:"+jg;
		String operationType="2";
		String memberId=withdrawals.getMemberstaffid();
		double money=withdrawals.getWMoney();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}

}
