package com.systemic.gq.stock.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Level;
import com.systemic.gq.entity.Member;
import com.systemic.gq.stock.command.LevelEdit;
import com.systemic.gq.stock.command.LevelInfo;
import com.systemic.gq.stock.service.ILevelService;

@Controller
@RequestMapping(value="/level")
public class LevelController {

	@Autowired
	private ILevelService levelService;
	
	//列表页面
		@RequestMapping(value = "/levelList.do")
		public String proposalInfoManage(HttpServletRequest request,
				HttpServletResponse response, Model model, LevelInfo info) {
			Page page = this.levelService.selectPageLevel(info);
			model.addAttribute("page", page);
			model.addAttribute("message", request.getParameter("message"));
			return "gq/gq/levelList";
		}
		
		//去新增页面
				@RequestMapping(value = "/levelEdit.do",method=RequestMethod.GET)
				public String tolevelEdit(HttpServletRequest request,
						HttpServletResponse response,Model model) {
					String id=request.getParameter("id");
					if(id!=null&&!"".equals(id)){
					 	Level level =this.levelService.selectlevel(id);
					 	model.addAttribute("command",level);
					}
					return "gq/gq/levelEdit";
				}

	// 添加页面
	@RequestMapping(value = "/levelEdit.do", method = RequestMethod.POST)
	public String levelEdit(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			LevelEdit leveledit) {
		try {
			this.levelService.insertlevel(leveledit);
			model.addAttribute("message", "操作成功");

			String logContent = "在IP为" + ConsoleHelper.getUserIp() + "的机器上-修改玩家等级";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("修改玩家等级", member, logContent);

		} catch (Exception e) {
			model.addAttribute("message", "操作失败" + e.getMessage());
		}
		model.addAttribute("command", leveledit);
		model.addAttribute("token", token);
		return "redirect:../level/levelList.do";
	}
		
				//删除
				@RequestMapping(value = "/levelDel.do",method=RequestMethod.POST)
				public String dodel(HttpServletRequest request,
						HttpServletResponse response, Model model, LevelEdit info) {
					try{
						if(info.getId()!=null){
							String idstr[]=info.getId().split(",");
							levelService.deletelevel(idstr);
							model.addAttribute("message", "删除成功");
							
							String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-删除玩家等级";
							Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
							Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
							ConsoleHelper.getInstance().getLogService().saveOperateLogForMember("删除玩家等级", member, logContent);
							
						}
					}catch(Exception e){
						System.out.println(e.getMessage());
						model.addAttribute("message", "删除失败"+e.getMessage());
					}
					
					return "redirect:../level/levelList.do";
				}
}
