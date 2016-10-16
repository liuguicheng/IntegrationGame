package com.systemic.unit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ������
 * 
 * @author lgc
 *
 */
public class ConUnit {

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
		//int num = (cen * 2 - 1);
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
    
	public static void main(String[] args) {
		CalculationTable(3,6);
	}
}
