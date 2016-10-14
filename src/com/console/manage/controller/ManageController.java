package com.console.manage.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.service.IManageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ManageController extends MultiActionController {
    /**
     * service,springע��
     */
    private IManageService manageService;

    /** ���Ź�������ͼ */
    private String departmentManageView;
    /** ��Ƕ���Ӳ����б���ͼ */
    private String departmentListView;
    /** ��������ͼ **/
    private String departmentTreeView;
    /** ���Ŷ�ѡ��ͼ--ѡ��ʽ */
    private String deptMultiSelectVile;
    /** ��ѡ��������ͼ **/
    private String departmentMultiTreeView;
    /** ����ѡ�� �� �Ӳ������� **/
    private String depInnerFilterListView;
    /**  */
    private String subDepSelectView;
    /** ������Ա���� **/
    private String staffManageView;
    /** ��Աѡ����ͼ **/
    private String staffSelectView;
    /** ��Ա�б� **/
    private String staffSubDepartmentListView;
    /** ��Աɾ�� **/
    private String staffListUrl;
    /** ��ԱͨѶ¼��ͼ by ydl 20140429 */
    private String staffContactsView;
    private String selectAllStaffListView;
    private String staffSubDepartmentListInnerView;
    private String staffListUrlInner;

    /**
     * ���Ź�������ͼ.
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView departmentManage(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // ����ά�����˵�λ����֯�ṹ
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        Department topDepart = staff.getBelong();
        String rootId = topDepart != null ? topDepart.getId() : null;
        model.put("departmentTree", this.manageService.selectDepartmentTree(rootId));

        model.put("parentId", staff.getDepartment().getId());
        return new ModelAndView(this.departmentManageView, model);
    }

    /**
     * �Ӳ����б�.
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView subDepartmentList(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String parentId = request.getParameter("parentId");
        String message = request.getParameter("message");
        if (message != null) {
            model.put("message", message);
        }

        if (parentId != null && parentId.trim().length() > 0) {
            model.put("parentDep", this.manageService.selectDepartment(parentId));
            model.put("departmentList", this.manageService.selectSubDepartment(parentId));
        } else {// ��������Ϊ��ʱ����ѯ���и�����=�����ŵĲ���
            Department dep = new Department();
            // dep.setName(request.getParameter("name"));
            dep.setId("");
            model.put("parentDep", dep);
            model.put("departmentList", this.manageService.selectAllRootDepartment());
        }
        return new ModelAndView(this.departmentListView, model);
    }

    /**
     * ��������
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView departmentSort(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("depId");
        if (ids != null && ids.length > 0) {
            this.manageService.doSortDepartment(ids);
        }

        return subDepartmentList(request, response);
    }

    /**
     * ����ɾ��
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView departmentDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("depId");
        Map model = new HashMap();
        if (ids != null && ids.length > 0) {
            try {
                this.manageService.deleteDepartment(ids);

            } catch (Exception ex) {
                model.put("message", ex.getMessage());
            }
        }

        String parentId = request.getParameter("parentId");
        if (parentId != null && parentId.trim().length() > 0) {
            model.put("parentDep", this.manageService.selectDepartment(parentId));
            model.put("departmentList", this.manageService.selectSubDepartment(parentId));
        }
        return new ModelAndView(this.departmentListView, model);

    }

    /**
     * ������
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView departmentTree(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String rootId = request.getParameter("rootId");
        model.put("departmentTree", this.manageService.selectDepartmentTree(rootId));
        model.put("mapId", request.getParameter("mapId"));
        String nextUrl = request.getParameter("nextUrl");
        if (nextUrl != null && nextUrl.length() != 0) {
            model.put("nextUrl", nextUrl);
        }
        return new ModelAndView(this.departmentTreeView, model);
    }

    /**
     * ������(��ѡ��ʽ)
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView departmentMultiTree(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String rootId = request.getParameter("rootId");
        model.put("departmentTree", this.manageService.selectDepartmentTree(rootId));
        model.put("mapId", request.getParameter("mapId"));
        String nextUrl = request.getParameter("nextUrl");
        if (nextUrl != null && nextUrl.length() != 0) {
            model.put("nextUrl", nextUrl);
        }
        return new ModelAndView(this.departmentMultiTreeView, model);
    }

    /**
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ModelAndView
     */
    public ModelAndView subDepSelect(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String filterStr = request.getParameter("filterStr");
        List depOriginalList = new ArrayList();
        if (filterStr != null && filterStr.trim().length() > 0) {
            String[] depIds = filterStr.split(",");
            for (int i = 0; i < depIds.length; i++) {
                try {
                    depOriginalList.add(ConsoleHelper.getDepartment(depIds[i]));
                } catch (Exception ex) {

                }
            }
            model.put("depOriginalList", depOriginalList);
        }
        return new ModelAndView(this.subDepSelectView, model);
    }

    // /**
    // * ������
    // * @param request HttpServletRequestʵ��
    // * @param response HttpServletResponseʵ��
    // * @return ModelAndViewʵ��
    // */
    // public ModelAndView parentDepTree(HttpServletRequest request,
    // HttpServletResponse response) {
    // Map model = new HashMap();
    // model.put("departmentTree",
    // this.manageService.selectDepartmentTree(null));
    // return new ModelAndView(this.parentDepTreeView, model);
    // }

    /**
     * ��λ����
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView unitDeptFilter(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String filterStr = request.getParameter("filterStr");
        List departments = this.manageService.selectAllUnitDepts(filterStr);
        model.put("depOriginalList", departments);

        return new ModelAndView(this.depInnerFilterListView, model);
    }

    /**
     * ���Ŷ�ѡʱ���Ӳ����б�
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView deptMultiSelect(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String parentId = request.getParameter("parentId");
        List subList = this.manageService.selectSubDepartment(parentId);
        // for (int i = subList.size() - 1; i >= 0; --i) {
        // Department dep = (Department) subList.get(i);
        // }
        model.put("depList", subList);
        return new ModelAndView(this.deptMultiSelectVile, model);
    }

    // /**
    // * ���Ŷ�ѡʱ���Ӳ����б�
    // * @param request HttpServletRequestʵ��
    // * @param response HttpServletResponseʵ��
    // * @return ModelAndViewʵ��
    // */
    // public ModelAndView subDepList(HttpServletRequest request,
    // HttpServletResponse response) {
    // Map model = new HashMap();
    // String parentId = request.getParameter("parentId");
    // if (parentId != null && parentId.trim().length() > 0) {
    // List subList = this.manageService.selectSubDepartment(parentId);
    // for (int i = subList.size() - 1; i >= 0; --i) {
    // Department dep = (Department) subList.get(i);
    // if (!dep.getId().equals(dep.getAncestorDep())) { //������ǵ�λ������˵�
    // subList.remove(i);
    // }
    // }
    // model.put("depList", subList);
    // }
    // return new ModelAndView(this.depInnerFilterListView, model);
    // }
    // /**
    // * �г�ָ�����ŵ�Ա���б�.
    // * @param request HttpServletRequestʵ��
    // * @param response HttpServletResponseʵ��
    // * @return ModelAndViewʵ��
    // */
    // public ModelAndView departmentStaffList(
    // HttpServletRequest request, HttpServletResponse response) {
    //
    // Map model;
    // String depId = request.getParameter("depId");
    // if (depId != null) {
    // model = new HashMap();
    // model.put("staffList",
    // this.manageService.selectDepartmentStaff(new Integer(depId)));
    // } else {
    // model = Collections.EMPTY_MAP;
    // }
    //
    // return new ModelAndView(this.departmentStaffListView, model);
    // }

    // /**
    // * @param request HttpServletRequestʵ��
    // * @param response HttpServletResponseʵ��
    // * @return ModelAndViewʵ��
    // */
    // public ModelAndView staffList(HttpServletRequest request,
    // HttpServletResponse response) {
    // String depId = request.getParameter("depId");
    //
    // Map model = new HashMap();
    // model.put("mapId", request.getParameter("mapId"));
    // model.put("staffList", this.manageService.selectDepartmentStaff(new
    // Integer(depId)));
    //
    // return new ModelAndView(this.staffListView, model);
    // }

    // /**
    // * @param request HttpServletRequestʵ��
    // * @param response HttpServletResponseʵ��
    // * @return ModelAndViewʵ��
    // */
    // public ModelAndView departmentStaffDelete(HttpServletRequest request,
    // HttpServletResponse response) {
    // Integer staffId = new Integer(request.getParameter("staffId"));
    // Staff staff = this.manageService.deleteStaff(staffId);
    // return new ModelAndView(new
    // GBRedirectView("../manage/departmentStaffList.do"), "depId",
    // staff.getDepartment().getId());
    // }

    /**
     * ��Ա��������ͼ.
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffManage(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // ����ά�����˵�λ����֯�ṹ
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        Department topDepart = staff.getBelong();
        String rootId = topDepart != null ? topDepart.getId() : null;
        model.put("departmentTree", this.manageService.selectDepartmentTree(rootId));

        model.put("departmentId", staff.getDepartment().getId());
        return new ModelAndView(this.staffManageView, model);
    }

    /** �ڱ���Ƹ */
    public ModelAndView staffManageInner(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // ����ά�����˵�λ����֯�ṹ
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        Department topDepart = staff.getBelong();
        String rootId = topDepart != null ? topDepart.getId() : null;
        model.put("departmentTree", this.manageService.selectDepartmentTree(rootId));

        model.put("departmentId", staff.getDepartment().getId());
        return new ModelAndView(this.selectAllStaffListView, model);
    }

    /**
     * ��Աѡ������ͼ.
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSelect(HttpServletRequest request, HttpServletResponse response) {
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        Map model = new HashMap();
        String depId = staff.getDepartment().getAncestorDep();
        List tree = this.manageService.selectDepartmentTree(depId);
        model.put("departmentTree", tree);
        if (depId == null && tree.size() > 0) {
            depId = ((Department) tree.get(0)).getId();
        }
        model.put("departmentId", depId);
        return new ModelAndView(this.staffSelectView, model);
    }

    /**
     * �г�ָ�����ŵ�Ա���б�.
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSubDepartmentList(HttpServletRequest request, HttpServletResponse response) {

        Map model = new HashMap();
        String depId = request.getParameter("departmentId");
        String message = request.getParameter("message");
        if (message != null && message.trim().length() > 0) {
            model.put("message", message);
        }
        if (depId != null) {
            model.put("parentDep", this.manageService.selectDepartment(depId));
            model.put("staffList", this.manageService.selectDepartmentStaff(depId));
        } else {
            model = Collections.EMPTY_MAP;
        }
        return new ModelAndView(this.staffSubDepartmentListView, model);
    }

    /**
     * �г�ָ�����ŵ�Ա���б�.�ڱ�
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSubDepartmentListInner(HttpServletRequest request, HttpServletResponse response) {

        Map model = new HashMap();
        String depId = request.getParameter("departmentId");
        String message = request.getParameter("message");
        if (message != null && message.trim().length() > 0) {
            model.put("message", message);
        }
        if (depId != null) {
            model.put("parentDep", this.manageService.selectDepartment(depId));
            model.put("staffList", this.manageService.selectDepartmentStaff(depId));
        } else {
            model = Collections.EMPTY_MAP;
        }
        return new ModelAndView(this.staffSubDepartmentListInnerView, model);
    }

    /**
     * ��Ա����
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSort(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("staffId");
        String departmentId = request.getParameter("departmentId");
        if (ids != null && ids.length > 0) {
            this.manageService.doSortStaff(ids);
        }

        return new ModelAndView(new GBRedirectView(this.staffListUrl), "departmentId", departmentId);
    }

    /**
     * inner��Ա����
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSortInner(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("staffId");
        String departmentId = request.getParameter("departmentId");
        if (ids != null && ids.length > 0) {
            this.manageService.doSortStaff(ids);
        }

        return new ModelAndView(new GBRedirectView(this.staffListUrlInner), "departmentId", departmentId);
    }

    /**
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSubDepartmentDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] staffStr = request.getParameterValues("staffId");
        String staffId = null;
        String departmentId = request.getParameter("departmentId");
        Staff currentUser = (Staff) AuthenticationFilter.getAuthenticator(request);
        Map model = new HashMap();
        for (int i = 0; i < staffStr.length; i++) {
            if (staffStr[i] != null && staffStr[i].trim().length() > 0) {
                staffId = staffStr[i];
                if (!currentUser.getId().equals(staffId)) {
                    this.manageService.deleteStaff(staffId);
                } else {
                    String message = "���ܽ��Լ�ɾ��������ɾ������Ч��";
                    model.put("message", message);
                }
            }
        }
        model.put("departmentId", departmentId);
        return new ModelAndView(new GBRedirectView(this.staffListUrl), model);
    }

    /**
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView staffSubDepartmentDeleteInner(HttpServletRequest request, HttpServletResponse response) {
        String[] staffStr = request.getParameterValues("staffId");
        String staffId = null;
        String departmentId = request.getParameter("departmentId");
        Staff currentUser = (Staff) AuthenticationFilter.getAuthenticator(request);
        Map model = new HashMap();
        for (int i = 0; i < staffStr.length; i++) {
            if (staffStr[i] != null && staffStr[i].trim().length() > 0) {
                staffId = staffStr[i];
                if (!currentUser.getId().equals(staffId)) {
                    this.manageService.deleteStaff(staffId);
                } else {
                    String message = "���ܽ��Լ�ɾ��������ɾ������Ч��";
                    model.put("message", message);
                }
            }
        }
        model.put("departmentId", departmentId);
        return new ModelAndView(new GBRedirectView(this.staffListUrlInner), model);
    }

    /**
     * @param manageService
     *            The manageService to set.
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * @param departmentListView
     *            The departmentListView to set.
     */
    public void setDepartmentListView(String departmentListView) {
        this.departmentListView = departmentListView;
    }

    /**
     * @param departmentTreeView
     *            The departmentTreeView to set.
     */
    public void setDepartmentTreeView(String departmentTreeView) {
        this.departmentTreeView = departmentTreeView;
    }

    /**
     * @param subDepTreeView
     *            The subDepTreeView to set.
     */
    public void setDepInnerFilterListView(String subDepTreeView) {
        this.depInnerFilterListView = subDepTreeView;
    }

    /**
     * @param departmentManageView
     *            The departmentManageView to set.
     */
    public void setDepartmentManageView(String departmentManageView) {
        this.departmentManageView = departmentManageView;
    }

    /**
     * @param staffManageView
     *            Ҫ���õ� staffManageView��
     */
    public void setStaffManageView(String staffManageView) {
        this.staffManageView = staffManageView;
    }

    /**
     * @param staffSubDepartmentListView
     *            Ҫ���õ� staffSubDepartmentListView��
     */
    public void setStaffSubDepartmentListView(String staffSubDepartmentListView) {
        this.staffSubDepartmentListView = staffSubDepartmentListView;
    }

    /**
     * @param staffSubDepartmentDeleteView
     *            Ҫ���õ� staffSubDepartmentDeleteView��
     */
    public void setStaffListUrl(String staffSubDepartmentDeleteView) {
        this.staffListUrl = staffSubDepartmentDeleteView;
    }

    /**
     * @param staffSelectView
     *            The staffSelectView to set.
     */
    public void setStaffSelectView(String staffSelectView) {
        this.staffSelectView = staffSelectView;
    }

    public void setStaffListUrlInner(String staffListUrlInner) {
        this.staffListUrlInner = staffListUrlInner;
    }

    /**
     * @param departmentMultiTreeView
     *            The departmentMultiTreeView to set.
     */
    public void setDepartmentMultiTreeView(String departmentMultiTreeView) {
        this.departmentMultiTreeView = departmentMultiTreeView;
    }

    /**
     * @param subDepSelectView
     *            The subDepSelectView to set.
     */
    public void setSubDepSelectView(String subDepSelectView) {
        this.subDepSelectView = subDepSelectView;
    }

    /**
     * @param deptMultiSelectVile
     *            the deptMultiSelectVile to set
     */
    public void setDeptMultiSelectVile(String deptMultiSelectVile) {
        this.deptMultiSelectVile = deptMultiSelectVile;
    }

    public void setStaffContactsView(String staffContactsView) {
        this.staffContactsView = staffContactsView;
    }

    /**
     * ����ͱ������
     * 
     * @param request
     * @param response
     * @return
     */

    public ModelAndView insuDepartmentTree(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        model.put("departmentTree", this.manageService.selectInsuDepartmentTree());
        model.put("mapId", request.getParameter("mapId"));
        String nextUrl = request.getParameter("nextUrl");
        if (nextUrl != null && nextUrl.length() != 0) {
            model.put("nextUrl", nextUrl);
        }
        return new ModelAndView(this.departmentMultiTreeView, model);
        // return new ModelAndView("manage/departmentMultipleSelect",model);
    }

    public ModelAndView staffDWRList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charSet=gbk");
        String depId = request.getParameter("depId");
        List<Staff> list = new ArrayList<Staff>();
        list = this.manageService.selectDepartmentStaff(depId);
        List<Staff> jsonList = new ArrayList<Staff>();
        Staff staff = null;
        Staff toStaff = null;
        for (int i = 0; i < list.size(); i++) {
            staff = new Staff();
            toStaff = list.get(i);
            staff.setId(toStaff.getId());
            staff.setName(toStaff.getName());
            staff.setId(toStaff.getId());
            staff.setName(toStaff.getName());
            staff.setDepartment(new Department());
            staff.getDepartment().setId(toStaff.getDepartment().getId());
            staff.getDepartment().setName(toStaff.getDepartment().getName());
            staff.setMobile(toStaff.getMobile());
            // ����Ѳ����Աѡ���Զ����
            staff.setLoginName(toStaff.getLoginName());
            staff.setStaffType(toStaff.getStaffType());
            staff.setDuty(toStaff.getDuty());
            staff.setSex(toStaff.getSex());
            staff.setInDate(toStaff.getInDate());
            staff.setRegDate(toStaff.getRegDate());
            staff.setRoomNum(toStaff.getRoomNum());
            if (staff.getDuty() == null) {
                staff.setDuty("");
            }
            if (staff.getRoomNum() == null) {
                staff.setRoomNum("");
            }
            if (staff.getMobile() == null) {
                staff.setMobile("");
            }
            jsonList.add(staff);
        }

        // ����Աȡ
        String staffs = request.getParameter("staffs");
        if (StringUtils.isNotBlank(staffs)) {
            String[] staffIds = staffs.split(",");
            for (String id : staffIds) {
                staff = new Staff();
                toStaff = ConsoleHelper.getStaff(id);
                staff.setId(toStaff.getId());
                staff.setName(toStaff.getName());
                staff.setDepartment(new Department());
                staff.getDepartment().setId(toStaff.getDepartment().getId());
                staff.getDepartment().setName(toStaff.getDepartment().getName());
                staff.setMobile(toStaff.getMobile());
                if (staff.getMobile() == null) {
                    staff.setMobile("");
                }
                jsonList.add(staff);

            }
        }
        // ��������ȡ
        String staffName = request.getParameter("staffName");
        if (StringUtils.isNotBlank(staffName)) {
            staffName = URLDecoder.decode(staffName, "UTF-8");
            StaffQueryInfo qi = new StaffQueryInfo();
            qi.setName(staffName);
            list = this.manageService.selectAllStaff(qi);
            for (int i = 0; i < list.size(); i++) {
                staff = new Staff();
                toStaff = list.get(i);
                staff.setId(toStaff.getId());
                staff.setName(toStaff.getName());
                staff.setDepartment(new Department());
                staff.getDepartment().setId(toStaff.getDepartment().getId());
                staff.getDepartment().setName(toStaff.getDepartment().getName());
                staff.setMobile(toStaff.getMobile());
                if (staff.getMobile() == null) {
                    staff.setMobile("");
                }
                jsonList.add(staff);
            }
        }
        GsonBuilder json = new GsonBuilder();
        PrintWriter out;
        out = response.getWriter();
        Gson gson = json.create();
        out.print(gson.toJson(jsonList, new TypeToken<List<Staff>>() {
        }.getType()));
        out.flush();
        out.close();
        return null;
    }

    /**
     * ϵͳͨѶ¼��ͼ
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView staffContacts(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // ������Ϣthis.manageService.selectAllRootDepartment()
        //model.put("departmentData", this.manageService.selectAllRootDepartment());
        model.put("departmentData", this.manageService.selectSubDepartment("10"));
        // ��Ա��Ϣ
        StaffQueryInfo info = new StaffQueryInfo();
        // info.setStaffType(BasicHelper.STAFF_TYPE_MAN);
        info.setDepCode("10%");
        List<Staff> list = this.manageService.selectAllStaff(info);
        for (Staff staff : list) {
            // staff.setInstance(BasicHelper.getWorkManByStaff(staff.getId()));
            staff.setStaffType(staff.getStaffType());
        }
        model.put("staffList", list);
        return new ModelAndView(this.staffContactsView, model);
    }

    // public ModelAndView selectAllStaffList(HttpServletRequest request,
    // HttpServletResponse response, StaffInfo command)
    // throws Exception {
    // Map<String, Object> model = new HashMap<String, Object>();
    // StaffInfo staffInfo = (StaffInfo)
    // SpringlineCommandHelper.getSpringlineCommand(request);
    // if(staffInfo==null){
    // staffInfo = command;
    // }
    // List data = this.manageService.selectAllStaff(command);
    // model.put("data", data);
    // model.put("command", staffInfo);
    // return new ModelAndView(this.selectAllStaffListView,model);
    // }

    public void setSelectAllStaffListView(String selectAllStaffListView) {
        this.selectAllStaffListView = selectAllStaffListView;
    }

    public void setStaffSubDepartmentListInnerView(String staffSubDepartmentListInnerView) {
        this.staffSubDepartmentListInnerView = staffSubDepartmentListInnerView;
    }

}
