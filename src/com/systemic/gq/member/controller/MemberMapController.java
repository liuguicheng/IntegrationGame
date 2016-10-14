package com.systemic.gq.member.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.unit.ConUnit;
import com.systemic.unit.TreeModel;
import com.systemic.unit.TreeUnit;

@Controller
@RequestMapping(value = "/memberMap")
public class MemberMapController {

	/**
	 * ��Աͼ�ס�ֱ����ϵͼ
	 */

	@Autowired
	private ISpringMemberService springMemberService;

	@RequestMapping(value = "/todirectoriesRelationship.do", method = RequestMethod.GET)
	public String todirectoriesRelationship(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/MemberdirectoriesRelationship";
	}

	/**
	 * ��ѯ�ڵ�
	 */
	@RequestMapping(value = "/xzChildNode.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String xzChildNode(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		List list = new ArrayList();
		TreeModel tm = null;
		Member memberinfo = null;
		/**
		 * ��ѯ��ֱ���Ƽ������л�Ա ���أ���Ա��š�ע���ע��ʱ�䡢�Ŷ��������Ŷ�ҵ�� ��(����/δ����)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// ��ѯ��ǰ��½�û�
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			staffid = staff.getId();
		}

		tm = addstaffmemberNode(memberinfo, memberinfo.getReferenceId());
		if (tm != null) {
			list.add(tm);
		}
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}

	/**
	 * ֱ����ϵ
	 */
	@RequestMapping(value = "/directoriesRelationship.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String directoriesRelationship(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		TreeModel tm = null;
		List list = new ArrayList();
		Member memberinfo = null;
		/**
		 * ��ѯ��ֱ���Ƽ������л�Ա ���أ���Ա��š�ע���ע��ʱ�䡢�Ŷ��������Ŷ�ҵ�� ��(����/δ����)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
		} else {
			// ��ѯ��ǰ��½�û�
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
		}
		// ��ѯ�ӽڵ�
		List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
		if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
			for (Member member : recommendMemberList) {
				tm = addstaffmemberNode(member, staffid);
				if (tm != null) {
					list.add(tm);
				}
			}
		}

		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
//		System.out.println("ֱ����ϵtree��jsonֵ��" + gsonString);

		return gsonString;
	}

	/**
	 * ���ݽڵ��ѯ�����ӽڵ� �����ҵ��
	 */
	private String childMemberTotalMoney(String staffid) {
		double totalMoney = 0;
		int count = 0;
		String returnstr = "";
		// ��ѯ����ǰ���ڵ��������ӽڵ�
		List<TreeModel> reemodellist = TreeUnit.getChildNodes(memberAlllist(), staffid);
		if (reemodellist != null && !reemodellist.isEmpty()) {
			for (TreeModel treeModel : reemodellist) {
				if (!staffid.equals(treeModel.getId())) {
					totalMoney += treeModel.getMember().getStock().getMoney();
				}
			}
			count = reemodellist.size() - 1;
			// ���static list ������
			TreeUnit.getReturnList().clear();

			returnstr = count + "," + totalMoney;
		}

		return returnstr;
	}

	/**
	 * ��ѯ�����û���װ�����нڵ�
	 */
	private List<TreeModel> memberAlllist() {
		List<TreeModel> reeemodel = null;
		List<Member> memberlist = this.springMemberService.selectMember();
		if (memberlist != null && !memberlist.isEmpty()) {
			reeemodel = new ArrayList<TreeModel>();
			for (Member member : memberlist) {
				TreeModel model = new TreeModel();
				model.setId(member.getStaffId());
				model.setPid(member.getNote());
				model.setMember(member);
				reeemodel.add(model);
			}
		}
		return reeemodel;
	}

	/**
	 * ��װҳ��չʾnode����
	 * 
	 * @param member
	 *            �ڵ���Ϣ����Ա��Ϣ��
	 * @param staffid
	 *            ���ڵ�
	 * @param nodename
	 *            �ڵ�name
	 * @param teamnumber
	 *            �Ƿ����ӽڵ�
	 * @return
	 */
	private TreeModel addstaffmemberNode(Member member, String staffid) {
		String nodename = "";
		// �Ŷ�����
		String teamnumber = "0";
		// �Ŷ�ҵ��
		String teamTotal = "0";
		// ��ʱ����
		String totalAndCount = "";
		// ��ѯ�����Ա��ŵ������ӽڵ�� �������ܽ��
		// �Զ��Ÿ���������ǰ count ���ź�total
		totalAndCount = childMemberTotalMoney(member.getStaffId());
		if (totalAndCount != null && !"".equals(totalAndCount)) {
			teamnumber = totalAndCount.split(",")[0];
			teamTotal = totalAndCount.split(",")[1];
		}
		// �ڵ�����
		nodename = member.getStaffId() + "[" + member.getStock().getMoney() + ","
				+ ConUnit.formateDateToString(member.getCreateTime()) + "," + teamnumber + "," + teamTotal + "]";

		TreeModel tm = new TreeModel();
		tm.setPid(staffid);
		tm.setId(member.getStaffId());
		tm.setName(nodename);
		if (member.getIsActivation() == 0) {
			tm.setIcon(TreeModel.ICOIMG_LS);
			tm.setIconClose(TreeModel.ICOIMG_LS);
			tm.setIconOpen(TreeModel.ICOIMG_LS);
		} else {
			tm.setIcon(TreeModel.ICOIMG_ZS);
			tm.setIconClose(TreeModel.ICOIMG_ZS);
			tm.setIconOpen(TreeModel.ICOIMG_ZS);
		}
		if (Integer.parseInt(teamnumber) > 0) {
			tm.setIsParent(TreeModel.OPEN_TRUE);
		}

		return tm;
	}

	////////////////////////////////////// ����ṹͼ/////////////////////////////////////////////////////////////////////////

	/**
	 * ��ת����ṹͼҳ��
	 */
	@RequestMapping(value = "/toMemberNetWork.do", method = RequestMethod.GET)
	public String toMemberNetWork(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/memberNetWork";
	}

	/**
	 * ��ѯ����ṹͼ
	 */
	@RequestMapping(value = "/selectMemberNetWork.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectMemberNetWor(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");// ��Աid
		String cennum = request.getParameter("cennum");// չ�ֲ���
		Member memberinfo = null;
		List<Member> tempList = new ArrayList<Member>();
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// ��ѯ��ǰ��½�û�
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}
		//��Ӹ��ڵ�
		tempList.add(memberinfo);
		// ѭ����ѯ����
		switch (cennum) {
		case "2":
			List<Member> ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
				}
				ndoeMemberlist = null;
			}
			break;
		case "3":
			 ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
					List<Member> ndoeMemberlist3_1 = this.springMemberService.selectMemberListByNode(member.getStaffId());
					if (ndoeMemberlist3_1 != null && !ndoeMemberlist3_1.isEmpty()) {
						for (Member member3_1 : ndoeMemberlist3_1) {
							tempList.add(member3_1);
						}
						ndoeMemberlist3_1 = null;
					}
				}
				ndoeMemberlist = null;
			}
			break;
		case "4":
			ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
					List<Member> ndoeMemberlist3_1 = this.springMemberService.selectMemberListByNode(member.getStaffId());
					if (ndoeMemberlist3_1 != null && !ndoeMemberlist3_1.isEmpty()) {
						for (Member member3_1 : ndoeMemberlist3_1) {
							tempList.add(member3_1);
							
							List<Member> ndoeMemberlist4_1 = this.springMemberService.selectMemberListByNode(member3_1.getStaffId());
							if (ndoeMemberlist4_1 != null && !ndoeMemberlist4_1.isEmpty()) {
								for (Member member4 : ndoeMemberlist4_1) {
									tempList.add(member4);
								}
								ndoeMemberlist4_1 = null;
							}
						}
						ndoeMemberlist = null;
					}
				}
				ndoeMemberlist = null;
			}
			break;		
		default:
			break;
		}
		//��ѯ�ڵ� ͳ��
		List list = selectNode(tempList,Integer.parseInt(cennum));

		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}

	private List selectNode(List<Member> tempList,int cen) {
		TreeModel tm;
		List list = new ArrayList();
		Rule reule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		// ϵͳ��������
		int reginNum = reule.getAreaNumber();
		//���� ���td������ϵͳ����
		tm = regionNumAndMaxTd(cen, reginNum);
		list.add(tm);
		
		if (tempList != null && !tempList.isEmpty()) {
			for (Member member : tempList) {
				tm = settlementNode(member,reginNum);
				if (tm != null) {
					list.add(tm);
				}
			}
		}
		return list;
	}

	public TreeModel regionNumAndMaxTd(int cen, int reginNum) {
		TreeModel tm;
		double totalTd=Math.pow(reginNum, (cen - 1));
		tm=new TreeModel();
		tm.setRegion(String.valueOf(reginNum));
		tm.setTdnum(String.valueOf(totalTd));
		tm.setTrnuml(String.valueOf(cen));
		return tm;
	}

	private TreeModel settlementNode(Member memberinfo,int reginNum) {
		String regionStr;
		TreeModel tm;
		Member nodeRegionMember;
		String temp = "";// ��ʱ����
		String temp2 = "";// ��ʱ����
		String temp3 = "";// ��ʱ����
		String temp4 = "";// ��ʱ����
		for (int i = 0; i < reginNum; i++) {
			// ���ݵ�ǰ�ڵ�id������id�� ��ѯ��ǰ�ڵ��¹涨����ڵ� ��Ϣ
			nodeRegionMember = this.springMemberService.selectMemberByNode(memberinfo.getStaffId(), String.valueOf(i));
			if (nodeRegionMember != null) {
				temp = nodechildMemberTotalMoney(nodeRegionMember.getStaffId());
				if (temp != null && !temp.equals("")) {
					temp2 += "����" + (i + 1) + ":" + temp.split(",")[1]+" ";
					temp3 += "����" + (i + 1) + "����:" + temp.split(",")[0]+" ";
					temp4 += "����" + (i + 1) + "����:"+temp.split(",")[2]+" ";
				}
			} else {
				temp2 += "����" + (i + 1) + ":0"+" ";
				temp3 += "����" + (i + 1) + "����:0"+" ";
				temp4 += "����" + (i + 1) + "����:0.0"+" ";
			}

		}
		regionStr = temp2 + "</br>" + temp4 + "</br>" + temp3 + "</br>";

		tm = addstaffmemberNetWorkNode(memberinfo, regionStr);
		return tm;
	}

	private TreeModel addstaffmemberNetWorkNode(Member member, String regionStr) {
		/**
		 * ��ѯ������ͼ ���أ���Ա��š������Ŷ�ҵ�� ��������ҵ��������������(����/δ����)������ nodename
		 * ���ӣ���ҵ����60000.00 <br>
		 * </a>������60000.00 ���� ��0.00 ���� ��0.00<br/>
		 * ����������0.00 �������� ��0.00 �������� ��0.00<br/>
		 * ����������2 ����������0 ����������0 <br/>
		 */
		// �ڵ� title
		String nodename = "";
		// �Ŷ�ҵ��
		String teamTotal = "0";
		// ��ʱ����
		String totalAndCount = "";
		// ��ѯ�����Ա��ŵ������ӽڵ�� �������ܽ��! �Զ��Ÿ���������ǰ count ���ź�total
		totalAndCount = childMemberTotalMoney(member.getStaffId());
		if (totalAndCount != null && !"".equals(totalAndCount)) {
			teamTotal = totalAndCount.split(",")[1];
		}

		nodename = "��ҵ����" + teamTotal + " <br></a>" + regionStr + " <br/>";

		TreeModel tm = new TreeModel();
		tm.setPid(member.getReferenceId());
		tm.setId(member.getStaffId());
		tm.setName(nodename);
		tm.setRegion(member.getRegion());
		tm.setConnectionId(member.getNote());
		if (member.getIsActivation() == 0) {
			tm.setIcon(TreeModel.NETWORK_Activation_NO);
		} else {
			tm.setIcon(TreeModel.NETWORK_Activation_YES);
		}
		return tm;
	}

	private String nodechildMemberTotalMoney(String staffid) {
		double totalMoney = 0;
		double newAddTotalMoney=0;
		int count = 0;
		String returnstr = "";
		// ��ѯ����ǰ���ڵ��������ӽڵ�
		List<TreeModel> reemodellist = TreeUnit.getChildNodes(memberAlllist(), staffid);
		if (reemodellist != null && !reemodellist.isEmpty()) {
			for (TreeModel treeModel : reemodellist) {
				totalMoney += treeModel.getMember().getStock().getMoney();
				if(ConUnit.isSameDate(treeModel.getMember().getCreateTime(), new Date())){
					newAddTotalMoney+=treeModel.getMember().getStock().getMoney();
				}
			}
			count = reemodellist.size();
			// ���static list ������
			TreeUnit.getReturnList().clear();

			returnstr = count + "," + totalMoney+","+newAddTotalMoney;
		}

		return returnstr;
	}

	
	//////////////////////////////////////////////////ҵ����ѯ/////////////////////////////////////////
	
	@RequestMapping(value = "/toMemberPerformanceQuery.do", method = RequestMethod.GET)
	public String toMemberPerformanceQuery(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberPerformanceQuery";
	}
	
	/**
	 * ҵ����ѯ
	 */
	@RequestMapping(value = "/doMemberPerformanceQuery.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String MemberPerformanceQuery(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		TreeModel tm = null;
		List list = new ArrayList();
		Member memberinfo = null;
		/**
		 * ��ѯ��ֱ���Ƽ������л�Ա ���أ���Ա��š�ע���ע��ʱ�䡢�Ŷ��������Ŷ�ҵ�� ��(����/δ����)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
			memberinfo= ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// ��ѯ��ǰ��½�û�
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
			memberinfo= ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		}
		// ��ѯ�ӽڵ�
		List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
		if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
			recommendMemberList.add(memberinfo);
		}else{
			recommendMemberList=new ArrayList<Member>();
			recommendMemberList.add(memberinfo);
		}
		
		for (Member member : recommendMemberList) {
			tm = addMemberPerformance(member, staffid);
			if (tm != null) {
				list.add(tm);
			}
		}
		
		
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}
	
	/**
	 * ��װ��ѯ����ҵ��
	 * @param member ��ѯ��Ա
	 * @param staffid ���ڵ�
	 * @return
	 */
	private TreeModel addMemberPerformance(Member member, String staffid) {
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String nodename = "";
			// �Ŷ�����
			String teamnumber = "0";
			// �Ŷ�ҵ��
			String teamTotal = "0";
			int ztnum=0;//ֱ������
			double zttotalmoney=0;//ֱ�ƽ��
			double newtotalmoeny=0;//��������
			// ��ʱ����
			String totalAndCount = "";
			String datetime="";
			// ��ѯ�����Ա��ŵ������ӽڵ�� �������ܽ��
			// �Զ��Ÿ���������ǰ count ���ź�total
			totalAndCount = childMemberTotalMoney(member.getStaffId());
			if (totalAndCount != null && !"".equals(totalAndCount)) {
				teamnumber = totalAndCount.split(",")[0];
				teamTotal = totalAndCount.split(",")[1];
			}
			List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
			if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
				for (Member member2 : recommendMemberList) {
					zttotalmoney+=member2.getStock().getMoney();
					if(ConUnit.isSameDate(member2.getCreateTime(), new Date())){
						newtotalmoeny+=member2.getStock().getMoney();
					}
				}
				ztnum=recommendMemberList.size();
			}
			recommendMemberList=null;
			// ������ֱ�ӿ�����2����60000.00�Ŷӹ�2����60000.00����������ҵ��0.00
			nodename = "ֱ�ӿ�����"+ztnum+"��,"+zttotalmoney+"<br/>�Ŷӹ�"+teamnumber+"��,"+teamTotal+"��������"+newtotalmoeny;
					

			TreeModel tm = new TreeModel();
			tm.setPid(staffid);
			tm.setId(member.getStaffId());
			tm.setName(nodename);
			
			tm.setRegisterTime(sdf.format(member.getCreateTime()));
			if(member.getIsActivation()==0){
				tm.setActivationTime("");
				tm.setStatus("Ͷ�ʽ��:"+member.getStock().getMoney()+"(δ����)");
			}else{
				if(member.getActivationTime()!=null&&!"".equals(member.getActivationTime())){
					datetime=sdf.format(member.getActivationTime());
				}
				tm.setActivationTime(datetime);
				tm.setStatus("Ͷ�ʽ��:"+member.getStock().getMoney()+"(�Ѽ���)");
			}
			return tm;
	}
	
}
