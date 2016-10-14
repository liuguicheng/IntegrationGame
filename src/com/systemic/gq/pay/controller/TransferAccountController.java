package com.systemic.gq.pay.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.TransferAccounts;
import com.systemic.gq.pay.command.TransferAccountsEdit;
import com.systemic.gq.pay.command.TransferAccountsQueryInfo;
import com.systemic.gq.pay.service.ITransferAccountService;
import com.systemic.unit.ConUnit;
@Controller
@RequestMapping(value="/transferAccount")
public class TransferAccountController {

	@Autowired
	private ITransferAccountService  transferAccountService;
	public void setTransferAccountService(ITransferAccountService transferAccountService) {
		this.transferAccountService = transferAccountService;
	}
	
	@RequestMapping(value="/selectTransferAccountPage.do")
	public String selectTransferAccountPage(HttpServletRequest reuquest,
			HttpServletResponse response,Model model,TransferAccountsQueryInfo info ){
		
		Page page=transferAccountService.selectTransferAccountsPage(info);
		model.addAttribute("page", page);
		return "gq/pay/transferAccountManage";
		
	}
	
	
	@RequestMapping(value = "/selectTransferAccountPageMember.do")
	public String selectTransferAccountPageMember(HttpServletRequest request, HttpServletResponse response, Model model, TransferAccountsQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// ֻ��ȡ�Լ�
				info.setTaTurnoutAccount(member.getStaffId());
				model.addAttribute("member", member);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ��Աû��staffId����
			throw new RuntimeException("��ȡ�����б��������ϵ����Ա��");
		}
		Page page=transferAccountService.selectTransferAccountsPage(info);
		model.addAttribute("page", page);
		return "gq/pay/transferAccountManage";
	}
	
	@RequestMapping(value="/ajaxTransferAccount.do")
	@ResponseBody
	public String ajaxTransferAccount(HttpServletRequest request,
			HttpServletResponse response,Model model,TransferAccountsEdit edit){
		    String turnoutid=edit.getTaTurnoutAccount();
		    String intoid=edit.getTaIntoAccount();
		    String memberId=request.getParameter("memberId");
			if(turnoutid!=null&&!"".equals(turnoutid)&&
					intoid!=null&&!"".equals(intoid)){
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(turnoutid);
				Member intomember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(intoid);
				try {
					if(member==null){
						//ת����Ա��Ų�����
						return "-5";
					}
					if(intomember==null){
						//ת���Ա��Ų�����
						return "-6";
					}
					//��֤ϵͳ����
					String returnstr=doValidationRule(model,edit,member);
					if(!"".equals(returnstr)){
						return returnstr;
					}
					edit.setTaTurnoutName(member.getUserName());
					edit.setTaIntoName(intomember.getUserName());
					edit.setTaTime(new Date());
					this.transferAccountService.save(edit);
					
					
					//��Ӳ�����¼
					addPayLog(edit);
					//����˫���û���Ǯ
					updateMemberForMoney(member,intomember,edit.getTaType(),edit.getTaMoeny());
					
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "�ύʧ�ܣ�");

				}
				if(memberId!=null&&!"".equals(memberId)){
					//����2 ��ת����Ա�Լ��ĳ�ֵ�б�
					return "2";
				}
				//����1 ��ת������Ա�����ĳ�ֵ�б�ҳ��
				return "1";
				
			}
			return "0";
		
	}
	
	
	private void addPayLog(TransferAccountsEdit tr) {
		String logContent ="�û����Ϊ:"+tr.getTaTurnoutAccount()+"�û�,��IPΪ"+ConsoleHelper.getUserIp()+"������,��"+
				"�û����Ϊ:"+tr.getTaIntoAccount()+"������ת��,ת�˽��Ϊ:"+tr.getTaMoeny();
		String operationType="3";
		String memberId=tr.getTaTurnoutAccount();
		double money=tr.getTaMoeny();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}
	
	private void updateMemberForMoney(Member member,Member intoMember,String type,Double Tranmoney) {
		
		Double money=0.0;
		//��������
		switch (type) {
		case "1":
		    money = member.getElectroniccurrency();
			if (money == null) {
				money = 0.00;
			}
			member.setElectroniccurrency(money -Tranmoney);
			intoMember.setElectroniccurrency(money +Tranmoney);
			break;
		case "2":
			money = member.getGoldAward();
			if (money == null) {
				money = 0.00;
			}
			member.setGoldAward(money -Tranmoney);
			intoMember.setGoldAward(money +Tranmoney);
			break;
		case "3":

			break;
		default:
			break;
		}
		
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(intoMember);
	}
	
	
	
	// ��֤ת�����й���
		private String doValidationRule(Model model, TransferAccountsEdit edit, Member member) {
			String returnstr = "";
			// ��ѯϵͳ���� ��ȡת��������
			Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
			if (rule != null) {
				//�жϽ����Ƿ���� ת��
				String transfertime=rule.getTransfer_time();
				int todayWeek= ConUnit.dayForWeek(new Date());
				if(transfertime.indexOf(String.valueOf(todayWeek))<0){
					//���ղ���ת��
					returnstr = "-7";
					return returnstr;
				}
				// ת�������� Ϊ0ʱ������
				// ת��ÿ�����޴��� Ϊ0ʱ������
				int withdrawals_count = rule.getWithdrawals_count();
				// ת��ÿ�ս�� Ϊ0ʱ������
				int withdrawals_money = rule.getWithdrawals_money();
				if (edit.getTaMoeny() > withdrawals_money) {
					// ת�˽����涨���
					model.addAttribute("message", "����ת�˽���ѳ����޶���");
					returnstr = "-1";
					return returnstr;
				}
				if (withdrawals_count != 0 || withdrawals_money != 0) {
					// ��ѯת�˼�¼ �����������գ�
					TransferAccountsQueryInfo info=new TransferAccountsQueryInfo();
					info.setTaTime(new Date());
					List<TransferAccounts> list = this.transferAccountService.selectTransferAccountsList(info);
					if (list != null && !list.isEmpty()) {
						if (list.size() >= withdrawals_count) {
							model.addAttribute("message", "�û�Ա����ת�˴����Ѵ�����,�������ٽ���ת�ˣ�");
							returnstr = "-2";
							return returnstr;
						}
						double totalMoney = 0;
						for (TransferAccounts t : list) {
							totalMoney += t.getTaMoeny();
						}
						// ���ϱ���ת�˽��
						totalMoney += edit.getTaMoeny();
						// �ж�ϵͳת��ÿ������ �Ƿ񳬳�
						if (totalMoney >= withdrawals_money) {
							model.addAttribute("message", "�û�Ա����ת�˽���Ѵ�����,�������ٽ���ת�ˣ�");
							returnstr = "-3";
							return returnstr;
						}

						// �ж����
						String returstr = pdYe(edit, member);
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
		private String pdYe(TransferAccountsEdit transferAccounts, Member member) {
			String returnstr = "";
			// ��ȡת�˱�������
			String waccounttype = transferAccounts.getTaType();
			switch (waccounttype) {
			case "1":
				// ���ӱ�
				// �ж��Ƿ�����ؽ�Ǯ����ת��
				if (transferAccounts.getTaMoeny() > member.getElectroniccurrency()) {
					returnstr = "-4";
				}
				break;
			case "2":
				// �����
				// �ж��Ƿ�����ؽ�Ǯ����ת��
				if (transferAccounts.getTaMoeny() > member.getGoldAward()) {
					returnstr = "-4";
				}
				break;
			}

			return returnstr;
		}
}
