package com.systemic.gq.stock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.systemic.gq.entity.IntegrationGameRule;
import com.systemic.gq.entity.Member;
import com.systemic.gq.stock.command.IntegrationGameRuleEdit;
import com.systemic.gq.stock.command.RuleEdit;
import com.systemic.gq.stock.service.IIntegrationGameRuleService;

@Controller
@RequestMapping(value="/igameRule")
public class IntegrationGameRuleController {

	@Autowired
	private IIntegrationGameRuleService integrationGameRuleService;
	
	@RequestMapping(value="/toIntegrationGameRuleView.do",method=RequestMethod.GET)
	public String toIntegrationGameRuleView(HttpServletRequest request,
			Model model){
	   IntegrationGameRule igrule=	integrationGameRuleService.selectIntegrationGameRule();
	   model.addAttribute("command", igrule);
	   return "gq/gq/integrationGameRuleEdit";
	}
	
	
	@RequestMapping(value = "/toIntegrationGameRuleView.do",method=RequestMethod.POST)
	public String doIntegrationGameRule(HttpServletRequest request,
			HttpServletResponse response,Model model,Long token,IntegrationGameRuleEdit ruleedit) {
		try{
			integrationGameRuleService.insert(ruleedit);
			
			String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改规则";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_RULE, member, logContent);
			
			
			model.addAttribute("message", "操作成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			model.addAttribute("message", "操作失败"+e.getMessage());
		}
		model.addAttribute("command", ruleedit);
		model.addAttribute("token", token);
		return "redirect:../igameRule/toIntegrationGameRuleView.do";
	}
}
