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
 * 工具类
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

	// 换算两位小数
	public static double takePercentage(double a, double b) {
		// 这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		double percent = a / b;
		// 输出一下，确认你的小数无误
		System.out.println("小数：" + percent);
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		// 最后格式化并输出
		System.out.println("百分数：" + nt.format(percent));
		return percent;
	}

	// 转换字符串编码
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

	// 判断当前日期是星期几
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

	// 封装json
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
	 *            区域
	 * @param cen
	 *            显示多少层
	 */
	public static void CalculationTable(int qy, int cen) {
		// 传回x的y次幂值 求出最大个数 ：区域的层次幂
		double totalTd = Math.pow(qy, (cen - 1));
		System.out.println("当前区域：" + qy + ",一共" + cen + "层。最大格数：" + totalTd);
		// 去除前端页面table循环次数（层数*区域减去最后一层）
		// int num = (cen * 2 - 1);
		// System.out.println("table 循环tr行数：" + num);
		// 第一层、第二层的td 跨度基本固定，不用计算
		// System.out.println("第1层td跨度：" + totalTd + ",格数：1");
		// System.out.println("第2层td跨度：" + (totalTd / qy) + ",格数：" + qy);
		// 每层 td 跨度
		// for (int i = 3; i <= cen; i++) {
		// if (i == cen) {
		// System.out.println("第" + i + "层td跨度：1,格数：" + Math.pow(qy, (i - 1)));
		// } else {
		// System.out.println("第" + i + "层td跨度：" + Math.floor((totalTd / i)) +
		// ",格数：" + Math.pow(qy, (i - 1)));
		// }
		// }

	}

	public static String uid(int num) {
		StringBuilder str = new StringBuilder();// 定义变长字符串
		Random random = new Random();
		// 随机生成数字，并添加到字符串
		for (int i = 0; i < num; i++) {
			str.append(random.nextInt(10));
		}
		// 将字符串转换为数字并输出
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
	 * 距离截止日期还有多长时间
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return  remain / ONE_MINUTE + "分钟";
		else if (remain <= ONE_DAY)
			return  remain / ONE_HOUR + "小时"
					+ (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return  day + "天" + hour + "小时" + minute + "分钟";
		}

	}
	public static Page doPage(Page page){
		if(page.getData()!=null&&!page.getData().isEmpty()){
        	List<SysMessage> sysmessagelist=page.getData();
        	List<SysMessage> newmessagesle=new ArrayList<SysMessage>();
        	SysMessage sym=null;
        	SysMessage sym2=null;
        	if(sysmessagelist.size()>1){
        	for (int i = 0; i < sysmessagelist.size(); i++)  //外循环是循环的次数
            {
        		sym=sysmessagelist.get(i);
                for (int j = sysmessagelist.size() - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
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
