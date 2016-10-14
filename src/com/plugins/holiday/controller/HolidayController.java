package com.plugins.holiday.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.holiday.entity.Holiday;
import com.plugins.holiday.service.IHolidayService;

public class HolidayController extends SpringlineMultiActionController{

    private IHolidayService holidayService;
    private String holidayQueryView;



    public String getHolidayQueryView() {
        return holidayQueryView;
    }

    public void setHolidayQueryView(String holidayQueryView) {
        this.holidayQueryView = holidayQueryView;
    }

    public IHolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }
    /**
     * ɾ��������Ϣ
     * @param request
     * @param response
     * @param holidayModel
     * @return
     * @throws Exception
     */
    public ModelAndView holidayDelete(HttpServletRequest request,
            HttpServletResponse response, HashMap holidayModel) throws Exception {
        Map model=new HashMap();
        try{
            String[] holidayIds=request.getParameterValues("holidayId");
            this.holidayService.deleteHoliday(holidayIds);
            model.put("message", "ɾ���ɹ���");
        } catch (Exception e) {
            model.put("message", "ɾ��ʧ�ܣ�" + e.getMessage());
        }
        String token=request.getParameter("token");
        model.put("token",token);
        return new ModelAndView(new GBRedirectView(this.holidayQueryView), model);
    }
    /**
     * ��ʼ�����ݿ�
     * @param request
     * @param response
     * @param holidayModel
     * @return
     * @throws Exception
     */
    public ModelAndView holidayinit(HttpServletRequest request,
            HttpServletResponse response, HashMap holidayModel) throws Exception {
        Map model=new HashMap();
        try{
            //Ĭ�ϳ�ʼ�����������
            //Calendar cal = Calendar.getInstance();
            //int year =cal.get(Calendar.YEAR);
            int year=Integer.valueOf(request.getParameter("year"));

            this.holidayService.doInitHolidy(year);
            model.put("message", "��ʼ���ɹ���");
        }catch(Exception e){
            e.printStackTrace();
            model.put("message", "��ʼ��ʧ�ܣ�");
        }
        return new ModelAndView(new GBRedirectView(this.holidayQueryView), model);
    }
    /**
     * ����յ�ʱ��  ajax
     * @param request
     * @param response
     * @return
     */
    public ModelAndView HolidayDWRFind(HttpServletRequest request,
            HttpServletResponse response){
       //response.setContentType("text/html;charSet=gbk");
        //�������Ա��⻺��
       response.setHeader("Cache-Control", "no-cache, must-revalidate");
       String time = request.getParameter("time");
       int Y=0,M=0;
       Map model = new HashMap();
       try {
           if (time != null && time.trim().length() > 0) {
               Y=Integer.valueOf(time.substring(0,4));
               M=Integer.valueOf(time.substring(5,time.indexOf("-",5)));
               M=M-1;
               //��������
               Holiday holiday=this.holidayService.updateHoliday(time);
               model.put("isLeave",holiday.getIsLeave());
           }
          GsonBuilder json = new GsonBuilder();
          PrintWriter out = response.getWriter();
          Gson gson = json.create();
          String holidayList = gson.toJson(model);
          out.print(holidayList);
          out.flush();
          out.close();
    }catch(Exception e){
       e.printStackTrace();
    }
    return null;
 }


}
