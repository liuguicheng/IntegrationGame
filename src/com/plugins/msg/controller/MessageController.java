package com.plugins.msg.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.IDictionaryMapValueItem;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;
import org.springline.web.view.support.HtmlHelper;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.plugins.msg.service.IMsgService;
import com.systemic.gq.entity.Member;

public class MessageController extends SpringlineMultiActionController {
	private IMsgService msgService;

	public void setMsgService(IMsgService msgService) {
		this.msgService = msgService;
	}

	/**
	 * 
	 * ��Ϣ����tab�л�
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageManage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		model.put("message", request.getParameter("message"));
		model.put("docType", request.getParameter("docType"));
		// ������ʾ��tabҳ
		model.put("tab", request.getParameter("tab"));
		model.put("token", request.getParameter("token"));
		return new ModelAndView(getViewMap().get("messageManageView").toString(), model);
	}

	/**
	 * ������Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView messageRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String[] ids = request.getParameterValues("msgId");
		try {
			this.msgService.deleteMessage(ids);
			model.put("message", "�����ɹ���");
		} catch (Exception e) {
			model.put("message", "����ʧ�ܣ�");
		}
		model.put("token", request.getParameter("token"));
		return new ModelAndView(new GBRedirectView(getViewMap().get("messageList").toString()), model);
	}

	/**
	 * ��������δ����Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageAllRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String msgIds = request.getParameter("ids");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessage(ids);
				model.put("message", "�����ɹ���");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "����ʧ�ܣ�");
			}
		}
		return null;
	}

	/**
	 * δ����Ϣ�б�
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageUnreadedList(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		MessageQueryInfo info = new MessageQueryInfo();
		info.setNotPage(true);
		info.setIsReaded(ConsoleHelper.NO);
		if (info.getStaffId() == null || info.getStaffId().trim().length() < 1) {
			Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
			if (self != null) {
				info.setStaffId(self.getId());
			}
		}
		Page data = this.msgService.selectMessage(info);
		if (data != null && data.getData() != null) {
			model.put("msgList", data.getData());
		}
		return new ModelAndView(getViewMap().get("unreadedView").toString(), model);
	}

	/**
	 * �첽��ѯϵͳ��Ϣ
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void messageAsyncList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		try {
			List<SysMessage> msgList = new ArrayList<SysMessage>();
			MessageQueryInfo msgInfo = new MessageQueryInfo();
			msgInfo.setNotPage(true);
			msgInfo.setIsReaded(ConsoleHelper.NO);
			if (StringUtils.isNotBlank(staffId)) {
				msgInfo.setStaffId(staffId);
			} else {
				Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
				msgInfo.setStaffId(self.getId());
			}

			Page msgData = this.msgService.selectMessage(msgInfo);
			if (msgData != null && msgData.getData() != null) {
				msgList.addAll(msgData.getData());
			}
			Gson json = new GsonBuilder().create();
			out = response.getWriter();
			out.print(json.toJson(msgList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * �첽��ѯ������Ϣ
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void messageChatList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		Map staffMap = (Map) CacheHelper.getInstance().getCacheObject("dicStaff");
		try {

			List<SysMessage> jsonlist = new ArrayList();
			MessageQueryInfo msgInfo = new MessageQueryInfo();
			msgInfo.setNotPage(true);
			msgInfo.setChatState(ConsoleHelper.NO);
			if (StringUtils.isNotBlank(staffId)) {
				msgInfo.setStaffId(staffId);
			} else {
				Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
				msgInfo.setStaffId(self.getId());
			}

			Page msgData = this.msgService.selectMessage(msgInfo);
			List<SysMessage> msgList = msgData.getData();
			for (int i = 0; i < msgList.size(); i++) {
				SysMessage message = msgList.get(i);
				message.setSendManId(message.getSendMan());
				IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper.getMapData(staffMap,
						message.getSendMan());
				message.setSendMan(item.getName().toString());

				jsonlist.add(message);
			}
			Gson json = new GsonBuilder().create();
			out = response.getWriter();
			out.print(json.toJson(msgList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * �칫���֣��첽��ѯ��Ϣ��û��У��
	 * 
	 * @param request
	 * @param response
	 */
	public void msgAsyncList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		try {
			out = response.getWriter();
			if (StringUtils.isNotBlank(staffId)) {
				MessageQueryInfo info = new MessageQueryInfo();
				info.setNotPage(true);
				info.setIsReaded(ConsoleHelper.NO);
				info.setStaffId(staffId);
				Page page = this.msgService.selectMessage(info);
				if (page != null && page.getData() != null & page.getData().size() > 0) {
					Gson json = new GsonBuilder().create();
					out.print(json.toJson(page.getData()));
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * �칫���֣���������δ����Ϣ
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView msgAllRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String msgIds = request.getParameter("ids");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessage(ids);
				model.put("message", "�����ɹ���");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "����ʧ�ܣ�");
			}
		}
		return null;
	}

	/**
	 * to ��ӹ��� ҳ��
	 */
	public ModelAndView toaddNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String message=request.getParameter("message");
		if(message!=null&&!"".equals(message)){
			model.put("message", message);
		}
		return new ModelAndView(getViewMap().get("toaddNoticeMessageView").toString(), model);
	}

	/**
	 * ��ӹ���
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		addMessage(request,"2");
		return new ModelAndView(new GBRedirectView(getViewMap().get("addNoticeMessageView").toString()), null);
	}
	/**
	 * ɾ������
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView doDelNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = delMessage(request);
		return new ModelAndView(new GBRedirectView(getViewMap().get("addNoticeMessageView").toString()), model);
	}
	
	/**
	 * ȥ��Ӱ���ҳ��
	 */
	public ModelAndView toaddhelpMessage(HttpServletRequest request, HttpServletResponse response) {
		Map map = doGetMessage(request);
		return new ModelAndView(getViewMap().get("toaddhelpMessageView").toString(), map);
	}

	private Map doGetMessage(HttpServletRequest request) {
		Map map = new HashMap();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			SysMessage message = this.msgService.selectMessageById(id);
			map.put("command", message);
		}
		return map;
	}
	
	/**
	 * ��Ӱ���
	 */
	
	public ModelAndView addHelpMessage(HttpServletRequest request, HttpServletResponse response) {
		addMessage(request,"3");
		return new ModelAndView(new GBRedirectView(getViewMap().get("addhelpMessageView").toString()), null);
	}

	private void addMessage(HttpServletRequest request,String messageType) {
		try {
			String title = request.getParameter("messageTitel");
			String content = request.getParameter("content");
			String id = request.getParameter("sysMessageInfoId");
			if (id != null && !"".equals(id)) {
				SysMessage sysmessage = this.msgService.selectMessageById(id);
				sysmessage.setMessageTitel(title);
				sysmessage.setContent(content);
				this.msgService.updateMessage(sysmessage);
			} else {
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
				this.msgService.insertMessageFor(content, title, messageType, member.getMemberId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ������
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView doDelHelpMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = delMessage(request);
		return new ModelAndView(new GBRedirectView(getViewMap().get("addhelpMessageView").toString()), model);
	}
	

	private Map delMessage(HttpServletRequest request) {
		Map model = new HashMap();
		String msgIds = request.getParameter("id");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessageForDel(ids);
				model.put("message", "�����ɹ���");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "����ʧ�ܣ�");
			}
		}
		return model;
	}
	
	
	
	
	/**
	 * to ���ͷ���ҳ��
	 */
	public ModelAndView toaddEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String message=request.getParameter("message");
		if(message!=null&&!"".equals(message)){
			model.put("message", message);
		}
		return new ModelAndView(getViewMap().get("toaddemailMessageView").toString(), model);
	}
	
	

	/**
	 * ���ͷ���
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		model.put("message", "����ʧ��");
		try {
			
			String title = request.getParameter("messageTitel");
			String content = request.getParameter("content");
			String receiveMan = request.getParameter("receiveMan");
			String messageType=request.getParameter("messageType");
			
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			
			if(messageType.equals("3")){
				//������Ϣ
				//������ ϵͳ����Ա ��Ӫ��Ա
				
				receiveMan="99999999";
				this.msgService.insertMessageForEmail(receiveMan,content, title, messageType, member.getUserName(),member.getBsid());
			}
			
			model.put("message", "���ͳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new GBRedirectView(getViewMap().get("toaddView").toString()), model);
	}
	/**
	 * to �ظ����� ҳ��
	 */
	public ModelAndView toUpEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String id=request.getParameter("id");
		SysMessage sysmessage= this.msgService.selectMessageById(id);
		if(sysmessage!=null){
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if(!sysmessage.getSendMan().equals(member.getUserName())
					&&sysmessage.getIsReaded().equals("0")){
			sysmessage.setIsReaded("1");
			this.msgService.updateMessage(sysmessage);
			}
		}
		model.put("command", sysmessage);
		return new ModelAndView(getViewMap().get("toUpEmailMessage").toString(), model);
	}
	
	/**
	 * �ظ�����
	 */
	public ModelAndView doUpEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		model.put("message", "�ظ�ʧ��");
		String sysMessageInfoId=request.getParameter("sysMessageInfoId");
		String hfmessage=request.getParameter("hfmessage");
		SysMessage sysmessage= this.msgService.selectMessageById(sysMessageInfoId);
		if(sysmessage!=null){
			sysmessage.setHfmessage(hfmessage);
			sysmessage.setIsReaded("2");
			this.msgService.updateMessage(sysmessage);
			model.put("message", "�ظ��ɹ�");
		}
		return new  ModelAndView(new GBRedirectView(getViewMap().get("addemailMessageView").toString()), model);
	}
	
	/**
	 * ɾ������
	 */
	public ModelAndView doDelEmailMessage(HttpServletRequest request, 
			HttpServletResponse response) {
		Map model = new HashMap();
		model.put("message", "ɾ��ʧ��");
		String[] sysMessageid=request.getParameterValues("sysMessageid");
		if (sysMessageid != null && sysMessageid.length > 0) {
			this.msgService.deletePhyMessage(sysMessageid);
			model.put("message", "ɾ���ɹ�");
		}
		return new ModelAndView(new GBRedirectView(getViewMap().get("addemailMessageView").toString()), model);
	}

}
