package com.console.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.ConsoleHelper;
import com.console.manage.command.StaffTypeQueryInfo;
import com.console.manage.service.IManageService;

/**
 * @author Ƹ����Ա
 *
 */
@Controller
public class SatffQueryController extends PaginationQueryController {
	private IManageService manageService;

	public void setManageService(IManageService manageService) {
		this.manageService = manageService;
	}

	@Override
	protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
			Map map) throws Exception {
		StaffTypeQueryInfo info = (StaffTypeQueryInfo) command;
		info.setStaffType("2");
		info.setValid(ConsoleHelper.YES);
		return this.manageService.selectStaffList(info);
	}

}
