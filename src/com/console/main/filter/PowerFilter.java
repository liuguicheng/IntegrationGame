package com.console.main.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.support.MenuManager;
import com.systemic.gq.entity.Member;

/**
 * @description url���ʹ�����
 * �û���¼ϵͳ�󣬶���ͨ�û��������ֱ������url��ַ���ʱ���û���е�Ȩ��ҳ����п��ơ�
 * ��ע��Ŀǰ����Ȩ�޿���ֻ�ܿ����û����ʵĲ˵����ڲ˵��µķ���·��Ŀǰû�и��õİ취���ƣ���
 */
public class PowerFilter  extends HttpServlet implements Filter {
    /**
     * 
     */
    private static final long serialVersionUID = -1295765347804389970L;

    /**  
     * �ض����URL  
     */  
    private String redirectURl = null; 
    
    private static Logger LOG = Logger.getLogger(PowerFilter.class); 
    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // ָ��Ҫ�ض����ҳ��   
        redirectURl = "/jump.html"; 
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; 
        HttpServletResponse sresponse = (HttpServletResponse) response;   
        HttpSession session = req.getSession();
        String advancedPassword=(String) session.getAttribute("advancedPassword");
        String threePassword=(String) session.getAttribute("threePassword");
        Staff staff =  (Staff) AuthenticationFilter.getAuthenticator(req);   
        if(staff == null){
            sresponse.sendRedirect(redirectURl); 
        } else {
             try {    
                    //��������
                    Department dep = ConsoleHelper.getInstance().getManageService().selectDepartment(staff.getDepartment().getId());
                    //��ȡURL��ַ
                    String url = ".."+req.getServletPath();
                    //����Url��ѯȨ����Ϣ
                    if(url != null && url.length() > 0){
                       Power power = ConsoleHelper.getInstance().getManageService().selectPowerByUrl(url);
                       if(power !=null){
                    	   //��֤�Ƿ񱻰���������
                    	   Member member=ConsoleHelper.getInstance().getSpringMemberService().selectMemberByStaffid(staff.getId());
                    	   if(member!=null){
                    		   if(member.getIsok()==2){
                    			   //�ų��������url
                    			   if(url.equals("../plugins/messageEmailList.do")
                    					   ||url.equals("../plugins/addEmailMessage.do")
                    					   ||url.equals("../plugins/toaddEmailMessage.do")){
                    			   }else{
                    				   redirectURl="/main/goPromptPage.do";
                      			   	 // �������������˺󣬹��������������������Ӧ   
                      			     String path = req.getContextPath()+redirectURl;
                      			    ((HttpServletResponse) response).sendRedirect(path);
                    			   }
                    			    
                    		   }
                    	   }
                    	   //��֤�Ƿ���Ҫ�߼�����
                    	   boolean isadv= MenuManager.isAdvancedPassword(advancedPassword, threePassword, power);
                    	   if(isadv){
                    			 redirectURl="/main/goVerifyAdvancedPasswrod.do?url="+url+"&level="+power.getPasswordLevel();
                  			   	 // �������������˺󣬹��������������������Ӧ   
                  			     String path = req.getContextPath()+redirectURl;
                  			    ((HttpServletResponse) response).sendRedirect(path);
                    	   }
                           boolean isPower = MenuManager.hasPower(power,staff,dep);
                           if(!isPower){
                               // �������������˺󣬹��������������������Ӧ   
                               String path = req.getContextPath()+redirectURl;
                                ((HttpServletResponse) response).sendRedirect(path);
                           } else {
                            // �������������˺󣬹��������������������Ӧ   
                               chain.doFilter(request, response);
                           }
                       } else {
                           chain.doFilter(request, response);
                       }   
                    }  
             } catch (Throwable e) {   
                 LOG.error("Ȩ�޹���ʱ����ִ���", e);   
                 String path = req.getContextPath()+redirectURl;
                 ((HttpServletResponse) response).sendRedirect(path);
//                 throw new RuntimeException("Ȩ�޹���ʱ����ִ���", e);   
             }   
        }
    
    }

	

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        //
    }

}

