package com.systemic.gq.bonus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.bonus.settlement.SettlementHelper;
import com.systemic.gq.entity.Member;
/**
 * 奖金 控制器
 * @author lgc
 *
 */
@Controller
public class BonusRecordController {
	
	// 列表页面
	@RequestMapping(value = "/bonus/bonusRecordList.do")
	public String proposalInfoManage(HttpServletRequest request, HttpServletResponse response, Model model,
			BonusRecordInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
       
		if (!staff.getName().equals("系统管理员")) {
			Member member= ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(staff.getId());
			info.setUserid(member.getUserName());
		}
		Page page = ConsoleHelper.getInstance().getBonusRecordService().selectPageBonusRecord(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return "gq/bonus/bonusRecordList";
	}


}
