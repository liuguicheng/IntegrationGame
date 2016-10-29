package com.systemic.unit;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.springline.orm.Page;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.msg.entity.SysMessage;

/**
 * ������
 * 
 * @author lgc
 *
 */
public class ConUnit {
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;

	public static Calendar calendar = Calendar.getInstance();

	// ������λС��
	public static double takePercentage(double a, double b) {
		// �����������ӡ�D���Ǳ�������Double���ͣ���������Ļ�ȡ�����޷�����ʹ��
		double percent = a / b;
		// ���һ�£�ȷ�����С������
		System.out.println("С����" + percent);
		// ��ȡ��ʽ������
		NumberFormat nt = NumberFormat.getPercentInstance();
		// ���ðٷ�����ȷ��2��������λС��
		nt.setMinimumFractionDigits(2);
		// ����ʽ�������
		System.out.println("�ٷ�����" + nt.format(percent));
		return percent;
	}

	// ת���ַ�������
	public static String toutf(String st) {
		String str = "";
		try {
			str = URLDecoder.decode(st, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return str;
	}

	// �жϵ�ǰ���������ڼ�
	public static int dayForWeek(Date newdate) {
		Calendar c = Calendar.getInstance();
		c.setTime(newdate);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	// ��װjson
	public static String tojson(ErrorDataMsg ed) {
		List list = new ArrayList();
		list.add(ed);
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		ed = null;
		list = null;
		g = null;
		return gsonString;
	}
	public static String tojson(Object  ed) {
		List list = new ArrayList();
		list.add(ed);
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		ed = null;
		list = null;
		g = null;
		return gsonString;
	}
	public static String tojson(List<T> list) {
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		list = null;
		g = null;
		return gsonString;
	}
	public static String formateDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 
	 * @param qy
	 *            ����
	 * @param cen
	 *            ��ʾ���ٲ�
	 */
	public static void CalculationTable(int qy, int cen) {
		// ����x��y����ֵ ��������� ������Ĳ����
		double totalTd = Math.pow(qy, (cen - 1));
		System.out.println("��ǰ����" + qy + ",һ��" + cen + "�㡣��������" + totalTd);
		// ȥ��ǰ��ҳ��tableѭ������������*�����ȥ���һ�㣩
		// int num = (cen * 2 - 1);
		// System.out.println("table ѭ��tr������" + num);
		// ��һ�㡢�ڶ����td ��Ȼ����̶������ü���
		// System.out.println("��1��td��ȣ�" + totalTd + ",������1");
		// System.out.println("��2��td��ȣ�" + (totalTd / qy) + ",������" + qy);
		// ÿ�� td ���
		// for (int i = 3; i <= cen; i++) {
		// if (i == cen) {
		// System.out.println("��" + i + "��td��ȣ�1,������" + Math.pow(qy, (i - 1)));
		// } else {
		// System.out.println("��" + i + "��td��ȣ�" + Math.floor((totalTd / i)) +
		// ",������" + Math.pow(qy, (i - 1)));
		// }
		// }

	}

	public static String uid(int num) {
		StringBuilder str = new StringBuilder();// ����䳤�ַ���
		Random random = new Random();
		// ����������֣�����ӵ��ַ���
		for (int i = 0; i < num; i++) {
			str.append(random.nextInt(10));
		}
		// ���ַ���ת��Ϊ���ֲ����
		String retnum = str.toString();
		return retnum;
	}

	public static void deleteImgFile(String filepath){
		try{
			if(filepath!=null&&!"".equals(filepath)){
				File file=new File(filepath);
		        file.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Date dateCalculation(Date date,int num){
		try{ 	
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.add(Calendar.HOUR,num);
	        Date dt1=rightNow.getTime();
	        return dt1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * �����ֹ���ڻ��ж೤ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return  remain / ONE_MINUTE + "����";
		else if (remain <= ONE_DAY)
			return  remain / ONE_HOUR + "Сʱ"
					+ (remain % ONE_HOUR / ONE_MINUTE) + "����";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return  day + "��" + hour + "Сʱ" + minute + "����";
		}

	}
	public static Page doPage(Page page){
		if(page.getData()!=null&&!page.getData().isEmpty()){
        	List<SysMessage> sysmessagelist=page.getData();
        	List<SysMessage> newmessagesle=new ArrayList<SysMessage>();
        	SysMessage sym=null;
        	SysMessage sym2=null;
        	if(sysmessagelist.size()>1){
        	for (int i = 0; i < sysmessagelist.size(); i++)  //��ѭ����ѭ���Ĵ���
            {
        		sym=sysmessagelist.get(i);
                for (int j = sysmessagelist.size() - 1 ; j > i; j--)  //��ѭ���� ��ѭ��һ�αȽϵĴ���
                {	
                	sym2=sysmessagelist.get(j);
                	boolean fs=ConUnit.isSameDate(sym.getSendTime(), sym2.getSendTime());
                    if (sym.getSendManId().equals(sym2.getSendManId())
                    		&&fs==true
                    		&&sym.getMessageType().equals(sym2.getMessageType()))
                    {
                    	newmessagesle.add(sym2);
                    	sysmessagelist.remove(sym2);
                    }else{
                    	newmessagesle.add(sym2);
                    	sysmessagelist.remove(sym2);
                    }

                }
            }
        	page.getData().clear();
        	page.setData(newmessagesle);
        	return page;
        	}
        }
		return page;
	}
	public static void main(String[] args) {
	}
}
