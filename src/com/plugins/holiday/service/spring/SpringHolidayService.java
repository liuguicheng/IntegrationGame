package com.plugins.holiday.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.utils.DateHelper;

import com.plugins.holiday.HolidayHelper;
import com.plugins.holiday.command.HolidayEditInfo;
import com.plugins.holiday.entity.Holiday;
import com.plugins.holiday.service.IHolidayService;
import com.plugins.holiday.service.dao.IHolidayDao;
import com.plugins.holiday.utils.DateBean;
import com.plugins.holiday.utils.HolidayUtil;

public class SpringHolidayService extends HolidayHelper implements IHolidayService,InitializingBean {

    private IHolidayDao holidayDao;

    public IHolidayDao getHolidayDao() {
        return holidayDao;
    }

    public void setHolidayDao(IHolidayDao holidayDao) {
        this.holidayDao = holidayDao;
    }

    /**
     * @see com.plugins.holiday.HolidayHelper#getDeadline(java.util.Date, int)
     */
    @Override
    public Date getDeadline(Date beginTime, int workDay) {
        int workday=workDay-1;  //��ʾ����Ҳ������Ч����
        //TODO FIXED ME �� ���ݽڼ��ն�����м���
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginTime);
        cal.add(Calendar.DATE, workday);
        Date temp=cal.getTime();  //���û�м��յ�������ֹ����(�����ֹ����һֱ�ı�)
        cal.setTime(beginTime);
        cal.add(Calendar.DATE, workDay);  //���ʱ�����зż�,��tempEndΪһ�´εݹ�Ŀ�ʼʱ��
        Date tempEnd=cal.getTime();
        /**��ѯ���ݿ���������ڷżٵ������ж��ٸ�*/
        List BetweenDays=this.doGetBetweenDays(beginTime, temp);
        /**�żٵ�����ռ���˶����죨��ȥ�ظ��żٵ����ڣ�*/
        int days=this.getdays(BetweenDays);
        if(days!=0){
            temp=getDeadline(tempEnd,days);
        }
        return temp;
    }
    /**
     * @see com.plugins.holiday.service.IHolidayService#getHolidayById(java.lang.String)
     */
    public Holiday selectHolidayById(String holidayId) {
        return (Holiday) this.holidayDao.load(Holiday.class, holidayId);
    }
    /**
     * @see com.plugins.holiday.service.IHolidayService#saveHoliday(com.plugins.holiday.command.HolidayEditInfo)
     */
    public Holiday saveHoliday(HolidayEditInfo editInfo) {

        String holidayStr=editInfo.getHolidayStr();
        String str="";
        Date date=null;
        List<Date> listDate=new ArrayList();  Date beginTime,endTime;
        String temp="",yearStr="";
        HolidayUtil util=new HolidayUtil();
        Holiday holiday = new Holiday();
        BeanUtils.copyProperties(editInfo, holiday,new String[] { "holidayId" });// ��������
       //�������ֶθ�ֵ
        str=editInfo.getHolidayStr();
        yearStr=str.substring(0,4);
        temp=util.holidayStr(str);
        date=util.stringToDate(util.holidayDateStr(str),"yyyy-MM-dd");
        holiday.setHolidayDate(date);
        listDate.add(date);
        holiday.setHolidayStr(temp);
        holiday.setHolidayYear(yearStr);
        holiday.setIsLeave(Holiday.HOLIDAY_YES);
        this.holidayDao.save(holiday);
//        beginTime=util.getMinDate(listDate);
//        endTime=util.getMaxDate(listDate);
//        super.doUpdate(beginTime, endTime);
        //super.doUpdate(date, date);
        return holiday;
    }
    /**
     * @see com.plugins.holiday.service.IHolidayService#deleteHoliday(java.lang.String[])
     */
    public void deleteHoliday(String[] holidayIds) {
        Holiday holiday = null;
        HolidayUtil util=new HolidayUtil();
        List<Date> listDate=new ArrayList();  Date beginTime,endTime;
        for (int i = 0; i < holidayIds.length; i++) {
            holiday = this.selectHolidayById(holidayIds[i]);
            listDate.add(holiday.getHolidayDate());
            this.holidayDao.delete(holiday);
        }
        beginTime=util.getMinDate(listDate);
        endTime=util.getMaxDate(listDate);
        //super.doUpdate(beginTime, endTime);
    }


    /**
     * ��ʼ������
     *
     * @param year
     */
    public void doInitHolidy(int year) {
        HolidayUtil util=new HolidayUtil();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List saturdayList=util.SunList(year,0);  // ���year�����е����յ�����
        List sundayList=util.SunList(year,6);  // ���year�����е�����������

        //��ʼ��֮ǰ������ݿ�(ֻ�ǽ��ض�ĳһ������ݶ����)
        List li=this.holidayDao.getHolidayByyearStr(String.valueOf(year));
//        this.holidayDao.deleteAll();
        this.holidayDao.deleteAll(li);
        String s="";

        //��ʼ��������
        for(int i=0;i<saturdayList.size();i++){
            if(saturdayList.get(i)!=null){
                s=saturdayList.get(i).toString();
                Holiday holiday = new Holiday();
                holiday.setHolidayType(Holiday.GregorianCalendar_TYPE);  //��������Ϊ����
                holiday.setHolidayName("");
                holiday.setIsLeave(Holiday.HOLIDAY_YES);  //��ʾ�ż�
                //holiday.setHolidayReason("0");  //��ʾ�ż�ԭ������ĩ
                holiday.setHolidayDate(util.getDate(s));
                holiday.setHolidayStr(s.substring(4));
                //holiday.setIsStatutory("0");  //��ʾ���Ƿ�������
                holiday.setHolidayYear(String.valueOf(year));
                holiday.setRemark("����");
                this.holidayDao.save(holiday);
            }
        }
        //��ʼ��������
        for(int i=0;i<sundayList.size();i++){
            if(sundayList.get(i)!=null){
                s=sundayList.get(i).toString();
                Holiday holiday = new Holiday();
                holiday.setHolidayType(Holiday.GregorianCalendar_TYPE);  //��������Ϊ����
                holiday.setHolidayName("");
                holiday.setIsLeave(Holiday.HOLIDAY_YES);  //��ʾ�ż�
                //holiday.setHolidayReason("0");  //��ʾ�ż�ԭ������ĩ
                holiday.setHolidayDate(util.getDate(s));
                holiday.setHolidayStr(s.substring(4));
                //holiday.setIsStatutory("0");  //��ʾ���Ƿ�������
                holiday.setHolidayYear(String.valueOf(year));
                holiday.setRemark("����");
                this.holidayDao.save(holiday);
            }
        }

        //����+�Ƿ��� ����
        Map GregorianMap=util.getStaregnHolidays(year);
        Iterator it=GregorianMap.entrySet().iterator();
        String key="",value="";
        Map.Entry entry=null;
//        while(it.hasNext()){
//            entry=(Map.Entry)it.next();
//            key=(String)entry.getKey();
//            value=(String)entry.getValue();
//            Holiday holiday = new Holiday();
//            holiday.setHolidayType(Holiday.GregorianCalendar_TYPE);  //��ʾ���� �� ���� ����
//            holiday.setHolidayName(value);
//            holiday.setIsLeave("0"); //��ʾ���ż�
//            holiday.setIsStatutory("0");  //��ʾ���Ƿ�������
//            holiday.setHolidayDate(util.getDate(key));
//            holiday.setHolidayStr(key.substring(4));
//            holiday.setHolidayYear(String.valueOf(year));
//            holiday.setRemark(value);
//            this.holidayDao.save(holiday);
//        }
        //����+���� ����
        Map aMap=util.getstatutoryHolidays(year);
        it=aMap.entrySet().iterator();
        while(it.hasNext()){
            entry=(Map.Entry)it.next();
            key=(String)entry.getKey();
            value=(String)entry.getValue();
            Holiday holiday=new Holiday();
            holiday.setHolidayType(Holiday.GregorianCalendar_TYPE);
            holiday.setHolidayName(value);
            holiday.setIsLeave(Holiday.HOLIDAY_YES);
            holiday.setHolidayReason("1");
            holiday.setIsStatutory("1");  //��ʾ�Ƿ�������
            holiday.setHolidayDate(util.getDate(key));
            holiday.setHolidayStr(key.substring(4));
            holiday.setHolidayYear(String.valueOf(year));
            holiday.setRemark(value);
            this.holidayDao.save(holiday);
        }



    //ũ�� + �Ƿ�������
    Map bMap=util.getStaregnHoliday(year);
    it=bMap.entrySet().iterator();
//    while(it.hasNext()){
//        entry=(Map.Entry)it.next();
//        key=(String)entry.getKey();
//        value=(String)entry.getValue();
//        Holiday holiday=new Holiday();
//        holiday.setHolidayType(Holiday.LunarCalendar_TYPE);  //��ʾũ��
//        holiday.setHolidayName(value);
//        holiday.setIsLeave("0"); //��ʾ���ż�
//        holiday.setIsStatutory("0"); //��ʾһ�����
//        holiday.setHolidayDate(util.getDate(key));
//        holiday.setHolidayStr(key.substring(4));
//        holiday.setHolidayYear(String.valueOf(year));
//        holiday.setRemark(value);
//        this.holidayDao.save(holiday);
//    }

    //ũ��+ ��������
    Map cMap=util.getstatutoryLunarHoliday(year);
    DateBean dateBean=new DateBean();
    it=cMap.entrySet().iterator();
    String temp="";
    while(it.hasNext()){
        entry=(Map.Entry)it.next();
        key=(String)entry.getKey();
        temp=sdf.format(dateBean.getGregorianCalendar(DateHelper.formatDate(util.getDate(key))));
        value=(String)entry.getValue();
        Holiday holiday=new Holiday();
        holiday.setHolidayType(Holiday.LunarCalendar_TYPE);  //��ʾũ��
        holiday.setHolidayName(value);
        holiday.setIsLeave(Holiday.HOLIDAY_YES);
        holiday.setHolidayReason("1");
        holiday.setIsStatutory("1");  //��ʾ�Ƿ�������
        holiday.setHolidayDate(util.stringToDate(temp,"yyyy-MM-dd"));
        holiday.setHolidayStr(util.holidayStr(temp));
        holiday.setHolidayYear(String.valueOf(year));
        holiday.setRemark(value);
        this.holidayDao.save(holiday);
    }

    Date begin=util.getDate(year+"0101");  //һ���еĵ�һ��1��1��
    Date end=util.getDate(year+"1231");  //һ���е����һ�� 12��31��

    //super.doUpdate(begin, end);

}
    /**
     * @see com.plugins.holiday.service.IHolidayService#getHolidayByName(java.lang.String)
     */
    public List<Holiday> getHolidayByName(String name) {
        return this.holidayDao.getHolidayByName(name);
    }
    /**
     * @see com.plugins.holiday.service.IHolidayService#getNameList()
     */
    public List getNameList(String name) {
        return this.holidayDao.getNameList(name);
    }
    /**
     * @see com.plugins.holiday.service.IHolidayService#getHolidayStr(java.lang.String)
     */
    public List selectHolidayStr(String year,String isStatutory,String holidayType){
        return this.holidayDao.getHolidayStr(year,isStatutory,holidayType);
    }
    public void afterPropertiesSet() throws Exception {
        //�鿴���ݿ�ĳ�ʼ�����
        List holidayYear=this.holidayDao.getYearFromHoliday();
        if(holidayYear!=null && holidayYear.size()>0){
           for(int i=0;i<holidayYear.size();i++){
              Holiday.holidayYearMap.put(holidayYear.get(i),holidayYear.get(i));
           }
        }
        super.setInstance(this);
    }
    /**
     * ������ݿ��д������������ڵ�����
     * @param begin ��ʼ����
     * @param end ��������
     * @return
     */
    public List  doGetBetweenDays(Date begin,Date end){
        int year=end.getYear() + 1900;
        String yy=String.valueOf(year);
        if(!Holiday.holidayYearMap.containsKey(yy)){
           this.doInitHolidy(year);
           Holiday.holidayYearMap.put(yy,yy);
        }
        return this.holidayDao.getBetweenDays(begin, end);
    }


   //Tools Methods
   /**
    * ���list�в��ظ��������ж�����
    */
    public int getdays(List li){
        List daysList=new ArrayList();
        int num=0;
        Holiday holiday=null;
        if(li!=null&&li.size()>0){
            for(int i=0;i<li.size();i++){
                holiday=(Holiday)li.get(i);
                if(!daysList.contains(holiday.getHolidayDate())){
                    daysList.add(holiday);
                }
            }
        }
        if(daysList!=null){
            return daysList.size();
        }else{
            return 0;
        }
    }
    /**���������ʱû�õ�*/
    public Date getendDate(Date tempEnd,int days){
        Calendar cal = Calendar.getInstance();
        Date temp=null;
        Boolean state;
        int  leftDays=days;
        while(leftDays!=0){
          cal.setTime(tempEnd);
          cal.add(Calendar.DATE,1);
          temp=cal.getTime();
          state=this.holidayDao.checkHolidayByDate(temp);
          if(!state){
              leftDays-=1;
          }
        }
        return temp;
    }
    /**
     * ���ĳһ������зżٵ�����
     * @param year
     * @return
     */
    public List selectHolidays(String year){
        return this.holidayDao.getHolidays(year);
    }
    public List selectHolidayStr(String date){
        return this.holidayDao.getHolidayStr(date);
    }
    public Holiday updateHoliday(String time){
        if(StringHelper.isEmpty(time)){
            return null;
        }
        String str="";
        Date beginTime,endTime,date;
        HolidayUtil util=new HolidayUtil();
        List<Holiday> temp=null;
        Holiday holiday=null;
         str=time.substring(0,4)+"-";
         String ff=time.substring(5,time.indexOf("-",5));
         if(ff.length()==1){
              str+="0"+ff+"-";
         }else{

             str+=ff+"-";
         }
         if(time.substring(time.lastIndexOf("-")+1).length()==1){
             str+="0"+time.substring(time.lastIndexOf("-")+1);
         }else{
             str+=time.substring(time.lastIndexOf("-")+1);
         }
         temp=this.selectHolidayStr(str);
         //�еĻ��޸ķżٻ��ǹ����� û�еĻ� ����һ���żٵ����ڼ���
         if(temp!=null&&temp.size()>0){
             holiday=temp.get(0);
             date=util.stringToDate(str, "yyyy-MM-dd");
             String isleave=holiday.getIsLeave();
             for(int i=0;i<temp.size();i++){
               holiday=temp.get(i);
               if(Holiday.HOLIDAY_NO.equals(holiday.getIsLeave())){
                 holiday.setIsLeave(Holiday.HOLIDAY_YES);
               }else{
                 holiday.setIsLeave(holiday.HOLIDAY_NO);
               }
               this.holidayDao.update(holiday);
             }
         }else{
             HolidayEditInfo info=new HolidayEditInfo();
             info.setHolidayStr(str);
             date=util.stringToDate(str,"yyyy-MM-dd");
             holiday=this.saveHoliday(info);
         }

         beginTime=endTime=date;
         super.doUpdate(beginTime, endTime);

         return holiday;
    }
}
