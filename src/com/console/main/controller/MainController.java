package com.console.main.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.ITreeNode;
import org.springline.beans.utils.EncryptHelper;
import org.springline.web.WebUITool;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.OperateLog;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.console.main.service.IMainService;
import com.console.support.MenuManager;
import com.systemic.gq.entity.Member;

/**
 * @description ���Controller.
 */
public class MainController extends SpringlineMultiActionController {
	/** IManageService */
	private IMainService mainService;
	/** ϵͳ�ַ����� */
	private static String PLATFORM_ENCODING = System.getProperty("file.encoding");

	// private ICorpBehaveService corpBehaveService =
	// CorpHelper.getInstance().getCorpBehaveService();

	/**
	 * Ĭ�ϵĹ���ҳ��
	 */
	private static String DEFAULT_DESTOP_VIEW = "../main/homepage.do";

	/**
	 * ����
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HashMap model)
			throws Exception {
		// model.put("apkU", value)
		return new ModelAndView("main/index", model);
	}

	/**
	 * ��¼���������ɹ������������ɹ�ת�� mainView
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap model = new HashMap();
		StaffSecurity staffTimes = null;
		StaffSecurity inputTimes = null;
		String loginName = request.getParameter("userName");
		String password = request.getParameter("password");
		// ��ȡҳ������ʲô��������ʵ�ֵ
		String browser = request.getParameter("browser");
		String rand = (String) request.getSession().getAttribute("rand");
		String vcode = request.getParameter("code");
		if (vcode == null || "".equals(vcode)) {
			model.put("message", "��֤�벻��Ϊ�գ�");
			model.put("loginName", loginName);
			return index(request, response, model);
		}
		if (!rand.equalsIgnoreCase(vcode)) {
			model.put("message", "��֤�����");
			model.put("loginName", loginName);
			return index(request, response, model);
		}
		int i = 0;
		boolean afterfailTime = false;
		Staff staffLogin = this.mainService.selectAllStaff(loginName);// �˴�Ҫ�����·���������ָ����¼�����û����������Ƿ���Ч��
		model.put("userName", loginName);
		if (staffLogin == null) {
			model.put("message", "���û������ڣ�");
			model.put("loginName", loginName);
			return index(request, response, model);
		} else {
			StaffSecurity ss = this.mainService.loadStaffSecurity(staffLogin.getId());
			if (ss.getIsLock() != null && ss.getIsLock().equals(ConsoleHelper.NO)) {
				// ��ȡ��¼ʧ��ʱ��
				long failedRecordTime = ss.getFailedRecordTime().getTime();
				// ��ȡ��ǰϵͳʱ��
				long currentTime = new Date().getTime();
				// �õ�ǰϵͳʱ���ȥ��¼ʧ��ʱ��
				long timeRecord = (currentTime - failedRecordTime) / (1000 * 60 * 60 * 24);
				// ���С��1�����������
				if (timeRecord < 1) {
					model.put("message", "�������¼ʧ�ܵĴ����ѳ������Σ����û��Ѿ�������,����24Сʱ֮���ٽ��е�¼��");
					model.put("loginName", loginName);
					return index(request, response, model);
				} else {
					afterfailTime = true;
				}
			} else if (ss.getIsLock() != null && ss.getIsLock().equals(ConsoleHelper.LUCK)) {
				model.put("message", "�����˺��Ѿ�������,������ϵ�ͷ���Ա��");
				model.put("loginName", loginName);
				return index(request, response, model);
			}
		}

		Staff staff = this.mainService.doValidate(loginName, password);
		if (staff == null) {
			int count;
			Staff failTimes = this.mainService.selectAllStaff(loginName);
			if (failTimes != null) {
				StaffSecurity ss = this.mainService.loadStaffSecurity(failTimes.getId());
				// �������,�ж���IE��ִ��
				if (browser.equals("IE")) {
					i = ss.getTimes();
					i = i + 1;
					staffTimes = this.mainService.doTimes(loginName, i);
				}

				if (ss.getFailedTimes() == null) {
					count = 0;
					count++;
					// ��¼��¼ʧ�ܴ������͵�¼ʧ��ʱ��
					if (browser.equals("IE")) {
						inputTimes = this.mainService.doFailiedTimes(loginName, count);
					}
					if (browser.equals("")) {
						inputTimes = this.mainService.doFailiedTimes(loginName, count);
					}
				} else if (ss.getFailedTimes() < 3) {
					int countNum = ss.getFailedTimes();
					countNum++;
					// ��¼��¼ʧ�ܴ������͵�¼ʧ��ʱ��
					if (browser.equals("IE") && staffTimes.getTimes() > 1) {
						inputTimes = this.mainService.doFailiedTimes(loginName, countNum);
					}
					if (browser.equals("")) {
						inputTimes = this.mainService.doFailiedTimes(loginName, countNum);
					}
				}
				// �����û�������������ٴ�����
				int haveInputTimes = 0;
				if (inputTimes != null) {
					haveInputTimes = inputTimes.getFailedTimes();
				} else {
					haveInputTimes = ss.getFailedTimes();
				}
				haveInputTimes = 3 - haveInputTimes;
				if (haveInputTimes != 0) {
					model.put("message", "�û�������������󣡡����������" + haveInputTimes + "�Σ��˺Ž�������  ");
				} else {
					if (afterfailTime) {
						model.put("message", "�û�������������󣡡����û��Ѿ�����������������ȷ��������н�����  ");
					} else {
						model.put("message", "�û�������������󣡡���������Ѵﵽ���Σ��˺ű�������  ");
					}
				}
			}

			model.put("loginName", loginName);
			return index(request, response, model);
		}

		// �Ե�¼�ɹ�����û����н���
		staff = this.mainService.doUnlock(staff);

		AuthenticationFilter.doAuthentication(request, staff);

		// �������
		this.mainService.updateIsLogin(staff, ConsoleHelper.YES);
		// ��¼�ɹ�����ʾ��¼��־
		String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ����ϵ�¼ϵͳ";
		ConsoleHelper.getInstance().getLogService().saveOperateLog(OperateLog.LOG_TYPE_SYSTEM, staff, logContent);

		return new ModelAndView(new RedirectView("../main/home.do"));
	}

	/**
	 * ��ʾ����ͼ
	 *
	 * @param request
	 *            HttpServletRequestʵ��
	 * @param response
	 *            HttpServletResponsetʵ��
	 * @return ModelAndViewʵ��
	 */
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap model = new HashMap();
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		if (staff == null) {
			return new ModelAndView(new RedirectView("../main/index.do"));
		}

		Department ancestorDep = this.mainService.selectDepartment(staff.getDepartment().getAncestorDep());
		ITreeNode data = (ITreeNode) CacheHelper.getInstance().getCacheObject(Power.TREE_DIC_IDENTIFICATION); // SystemData.getInstance().getSystemData(Constants.MAIN_MENU_DATATYPE_KEY,
		// null);
		ITreeNode menu = MenuManager.buildMenuTree(data, staff, ancestorDep);
		if (menu == null || menu.getChildren().size() == 0) {
			model.put("message", staff.getName() + " û��ʹ��ϵͳ��Ȩ�ޣ�����ϵϵͳ����Ա������Ӧ����Ȩ������");
			return index(request, response, model);
		}

		model.put("mainMenu", menu.getChildren());

		String view = request.getParameter("rightView");
		if (view == null) {
			model.put("rightView", DEFAULT_DESTOP_VIEW);
		} else {
			view = this.encoding(view);
			model.put("rightView", view);
		}
		model.put("leftMenuView", "../main/menuTree.do");
		// ��ҵ��Ϊ��Ϣ��ʾ����ˡ�������Ϣ
		model.put("staff", staff);

		return new ModelAndView("main/mainView", model);
	}

	/**
	 * ��ʾ�˵�
	 *
	 * @param request
	 *            HttpServletRequestʵ��
	 * @param response
	 *            HttpServletResponsetʵ��
	 * @return ModelAndViewʵ��
	 */
	public Map menuTree(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Department ancestorDep = this.mainService.selectDepartment(staff.getDepartment().getAncestorDep());
		ITreeNode root = (ITreeNode) CacheHelper.getInstance().getCacheObject(Power.TREE_DIC_IDENTIFICATION);
		String moduleName = request.getParameter("moduleName");
		if (moduleName != null && moduleName.trim().length() > 0) {
			root = root.find(moduleName);
		}
		ITreeNode menu = MenuManager.buildMenuTree(root, staff, ancestorDep);
		model.put("menuTree", menu);

		return model;
	}

	/**
	 * ��ʾ��ҳ
	 *
	 * @param request
	 *            HttpServletRequestʵ��
	 * @param response
	 *            HttpServletResponsetʵ��
	 * @return ModelAndViewʵ��
	 */
	public Map homepage(HttpServletRequest request, HttpServletResponse response) {
		return new HashMap();
	}

	/**
	 * �˳�ϵͳ.
	 *
	 * @param request
	 *            HttpServletRequestʵ��
	 * @param response
	 *            HttpServletResponsetʵ��
	 * @return ModelAndViewʵ��
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		request.getSession().invalidate();
		AuthenticationFilter.cancelAuthentication(request);
		// �������
		// this.mainService.updateIsLogin(staff, ConsoleHelper.NO);
		// �˳�ϵͳ����ʾ��¼��־
		String logContent = "��IPΪ" + ConsoleHelper.getUserIp() + "�Ļ������˳�ϵͳ";
		ConsoleHelper.getInstance().getLogService().saveOperateLog(OperateLog.LOG_TYPE_SYSTEM, staff, logContent);
		return index(request, response, null);
	}

	/**
	 * �޸�����
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected Map passwordEdit(HttpServletRequest request, HttpServletResponse response, Map model) throws Exception {
		return model;
	}

	/**
	 * �����޸ĵ�����
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected Map savePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String loginName = request.getParameter("loginName");
		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");

		Staff staffLogin = this.mainService.selectStaff(loginName);
		if (staffLogin == null) {
			model.put("message", "���û������ڣ�");
			return passwordEdit(request, response, model);
		} else {
			StaffSecurity ss = this.mainService.loadStaffSecurity(staffLogin.getId());
			if (ss.getIsLock() != null && ss.getIsLock().equals(ConsoleHelper.NO)) {
				model.put("message", "���û��Ѿ���������");
				return passwordEdit(request, response, model);
			}
		}
		// �û�У��
		Staff staff = this.mainService.doValidate(loginName, oldPassword);
		if (staff == null) {
			model.put("message", "������û��������룡");
			return passwordEdit(request, response, model);
		}
		this.mainService.updatePassword(staff, password);
		model.put("returnValue", "1");
		return passwordEdit(request, response, model);
	}

	/**
	 * @param manageService
	 *            The manageService to set.
	 */
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}

	/**
	 * @param source
	 *            String
	 * @return String
	 */
	private String encoding(String source) {
		try {
			return new String(source.getBytes("GBK"), PLATFORM_ENCODING);
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.toString());
			return source;
		}
	}

	/**
	 * ��ά����Ƭ
	 * 
	 * @param request
	 *            HttpServletRequestʵ��
	 * @param response
	 *            HttpServletResponsetʵ��
	 * @return ModelAndViewʵ��
	 */
	public ModelAndView getQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = request.getParameter("msg");
		WebUITool.createQRCode(request, response, msg);
		return null;
	}
	// /**
	// *
	// */
	// private static String platformEncoding;
	// static {
	// platformEncoding = System.getProperty("file.encoding");
	// }
	// /**
	// * ��Դ�ַ����� GBK ����ת����ϵͳƽ̨����
	// * @param source Ҫת�����ַ���
	// * @return ת������ַ��������ת��ʧ���򷵻�Դ�ַ���
	// */
	// public static String gbkToPlatformEncoding(String source) {
	// try {
	// return new String(source.getBytes("GBK"), platformEncoding);
	// } catch (UnsupportedEncodingException e) {
	// System.err.println(e.toString());
	// return source;
	// }
	// }

	///////////////////////////////////////////////////////////////////////////////
	/**
	 * ��֤�߼����� lgc
	 */
	public ModelAndView verifyAdvancedPassword(HttpServletRequest request, HttpServletResponse response, HashMap model)
			throws Exception {
		String pwd = request.getParameter("pwd") != null ? request.getParameter("pwd").trim() : "";
		String url = request.getParameter("url") != null ? request.getParameter("url").trim() : "";
		String level = request.getParameter("level") != null ? request.getParameter("level").trim() : "";

		if (pwd == null || "".equals(pwd.trim())) {
			model.put("message", "���벻��Ϊ��");
			model.put("level", level);
			model.put("url", url);
			return new ModelAndView("main/passwrodView", model);
		}

		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		String memberPassword = "";
		switch (level) {
		case "����":
			memberPassword = memberinfo.getPasswodTwo();
			if (memberPassword.equals(EncryptHelper.md5Encoding(pwd))) {
				request.getSession().setAttribute("advancedPassword", memberPassword);
				return new ModelAndView("redirect:" + url, model);
			}
			break;
		case "����":
			memberPassword = memberinfo.getPasswordThree();
			if (memberPassword.equals(EncryptHelper.md5Encoding(pwd))) {
				request.getSession().setAttribute("threePassword", memberPassword);
				return new ModelAndView("redirect:" + url, model);
			}
			break;
		default:
			break;
		}
		model.put("message", level + "�������");
		model.put("level", level);
		model.put("url", url);
		return new ModelAndView("main/passwrodView", model);
	}

	/**
	 * ��ת��֤�߼�����ҳ��
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView goVerifyAdvancedPasswrod(HttpServletRequest request, HttpServletResponse response,
			HashMap model) throws Exception {
		String url = request.getParameter("url");
		String level = request.getParameter("level");
		model.put("level", level);
		model.put("url", url);
		return new ModelAndView("main/passwrodView", model);
	}

	/**
	 * ��ʾ��Ȩ�޷���ҳ��
	 */
	public ModelAndView goPromptPage(HttpServletRequest request, HttpServletResponse response, HashMap model)
			throws Exception {
		return new ModelAndView("main/promptPage", model);
	}

	/**
	 * to��������ҳ��
	 */
	public ModelAndView toForgetPassword(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ModelAndView("main/forgetPassword", null);
	}
	
	public ModelAndView doForgetPassword(HttpServletRequest request, HttpServletResponse response, HashMap model)
			throws Exception {
		String message="��ұ�Ų�����";
		String url="main/forgetPassword";
		String username = request.getParameter("userName")!=null?request.getParameter("userName").trim():"";
		String mbwt = request.getParameter("mbwt")!=null?request.getParameter("mbwt"):"";
		String mbwtDn = request.getParameter("mbwtDn")!=null?request.getParameter("mbwtDn"):"";
		Member member=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByUserName(username);
		if(member!=null){
			if(member.getMbwt().equals(mbwt)&&member.getMbwtDn().equals(mbwtDn)){
				url="main/newPassword";
				message=null;
				model.put("id", member.getStaffId());
			}else{
				message="����ܱ����ⲻƥ��";
			}
		}
		model.put("message", message);
		return new ModelAndView(url, model);
	}
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doupdatePassword(HttpServletRequest request, HttpServletResponse response, HashMap model)
			throws Exception {
		String message="��������ʧ��";
		String url="main/newPassword";
		String id = request.getParameter("id")!=null?request.getParameter("id").trim():"";
		String password = request.getParameter("password")!=null?request.getParameter("password"):"";
		String con_password = request.getParameter("con_password")!=null?request.getParameter("con_password"):"";
		if(password.equals(con_password)){
			Member member=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(id);
			if(member!=null){
				member.setPassword(con_password);
				ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
				message="��������ɹ�";
				url="main/index";
				
				String logContent = "��IPΪ" +  ConsoleHelper.getUserIp() + "�Ļ�����-�����˵�½����";
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_LOGINPASSWORD, member, logContent);
			}
		}else{
			message="�������������벻һ��";
			model.put("id", id);
		}
		model.put("message", message);
		return new ModelAndView(url, model);
	}
}
