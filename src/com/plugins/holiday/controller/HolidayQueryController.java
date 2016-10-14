package com.plugins.holiday.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.holiday.command.HolidayQueryInfo;
import com.plugins.holiday.entity.Holiday;
import com.plugins.holiday.service.IHolidayService;
import com.plugins.holiday.utils.HolidayUtil;

public class HolidayQueryController extends SpringlineMultiActionController{

    private IHolidayService holidayService;

    public  ModelAndView selectQueryResult(HttpServletRequest request,
            HttpServletResponse response, HashMap holidayModel)
            throws Exception {
        Map model=new HashMap();
        HolidayQueryInfo info = new HolidayQueryInfo();
        String str="";
        HolidayUtil util=new HolidayUtil();
        List li=new ArrayList();
        int Y=0,M=0;
        try {
            //������ŵ�ʱ�� �Ÿ�������
            String time = request.getParameter("time");
            if (time != null && time.trim().length() > 0) {
                Y=Integer.valueOf(time.substring(0,4));
                M=Integer.valueOf(time.substring(5,time.indexOf("-",5)));
                M=M-1;
                info.setMaxHolidayDate(util.change(time));
                info.setMinHolidayDate(util.change(time));
              //��������
                this.holidayService.updateHoliday(time);
            }
            //��ѯ
          //��һ�ν���ʱ  Ĭ����ʾ��ǰ�µĽ������
            if(info.getMinHolidayDate() != null && info.getMinHolidayDate().trim().length() > 0 
                  &&  info.getMaxHolidayDate() != null && info.getMaxHolidayDate().trim().length() > 0 ){
                 Calendar cal = Calendar.getInstance();
                 int year=cal.get(Calendar.YEAR);
                 int month=cal.get(Calendar.MONTH)+1;
                 Y=year;
                 M=month-1;
                 info.setMinHolidayDate(""+year+"-"+month+"-"+"01");
                 info.setMaxHolidayDate(""+year+"-"+month+"-"+"31");
            }
            //ѡ�����ʱ�򣬳�ʼ������Ľ���
            String year=request.getParameter("year");
            String month=request.getParameter("month");
            if(year != null && year.trim().length() > 0 
                    && month != null && month.trim().length() > 0 ){
               Y=Integer.valueOf(year);
               M=Integer.valueOf(month);
            }
            //�鿴���ݿ��Ƿ��ʼ����
            if(!Holiday.holidayYearMap.containsKey(Y)){
                 this.holidayService.doInitHolidy(Integer.valueOf(Y));
                 Holiday.holidayYearMap.put(Y,Y);
            }
            //��õ���ķżٵ�ʱ��
            li=this.holidayService.selectHolidays(String.valueOf(Y));
            model.put("Y",Y);
            model.put("M", M);
            model.put("li",li);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("holiday/holidayList", model);
    }

    public IHolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }

}
