package com.plugins.msg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.plugins.msg.service.IMsgService;
import com.systemic.gq.entity.Member;
import com.systemic.unit.ConUnit;

public class MessageQueryController extends PaginationQueryController {
	
	
    private IMsgService msgService;

    public void setMsgService(IMsgService msgService) {
        this.msgService = msgService;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        MessageQueryInfo info = (MessageQueryInfo) command;
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		String sysMessageInfoId=request.getParameter("sysMessageInfoId");
		if(sysMessageInfoId!=null&&!"".equals(sysMessageInfoId)){
			info.setSysMessageInfoId(sysMessageInfoId);
		}
		if (!staff.getName().equals("系统管理员")) {
			info.setSendMan(member.getUserName());
			model.put("member", 0);
		}else{
			model.put("member", 1);
		}
		info.setMessageType("-1");
         
        Page page =  ConUnit.doPage(this.msgService.selectMessagebyMsg(info));
        
        return page;
    }
}
