package com.plugins.holiday.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HolidayUtil {

    /**
     * ��ȡһ���е�ָ�������ڣ�����һ�������ж����죩
     *
     * @param year
     * @param Weekday
     *            ��0~6 0��ʾ���� 6 ��ʾ������
     * @return
     */
    public List SunList(int year, int Weekday) {
        // ����ĩ���ڱ��浽������

        List li = new ArrayList();

        int y = year; // ���
        int w = Weekday; // ��������ĩ��6-0,0��ʾ������ 5��ʾ����5

        int m = 1, d;

        int e[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // ÿ���ж�����
        boolean leap = y % 4 == 0 && y % 100 != 0 || y % 400 == 0; // �ж��Ƿ�������
        e[1] = leap ? 29 : 28;

        // Ԫ�����ڼ�?
        d = (y + ((y - 1) >> 2) - (y - 1) / 100 + (y - 1) / 400) % 7;

        // ��һ����ĩ����
        if (d == w) {
            d = 1;
        } else {
            d = (w > d ? w - d : (w - d + 7)) + 1;
        }

        // �Ƿ��е�53����ĩ
        boolean add = (d == 1 || d <= 2 && leap);

        // ����52����ĩ
        for (int i = 0; i < 52; ++i) {
            // System.out.println(i+1 + "   :   " + y + "- " + m + "- " + d);

            // ѭ������
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(y); // ��
            if (String.valueOf(m).length() == 1) { // ��
                strbuf.append("0").append(m);
            } else {
                strbuf.append(m);
            }
            if (String.valueOf(d).length() == 1) {
                strbuf.append("0").append(d);
            } else {
                strbuf.append(d);
            }
            if(strbuf!=null){
              li.add(strbuf.toString());
            }

            d += 7;
            if (d > e[m - 1]) {
                d -= e[m - 1];
                ++m;
            }
        }

        if (add) { // ��� �е�53����ĩ
            // System.out.println(53 + "   :   " + y + "- " + 12 + "- " + d);
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(y); // ��
            if (String.valueOf(m).length() == 1) { // ��
                strbuf.append("0").append(m);
            } else {
                strbuf.append(m);
            }
            if (String.valueOf(d).length() == 1) {
                strbuf.append("0").append(d);
            } else {
                strbuf.append(d);
            }

            if(strbuf!=null){
              li.add(strbuf);
            }
        }

        return li;

    }

    /**
     * ���ַ���ת��Ϊ����
     *
     * @param str
     *            ��ʾ���ڵ��ַ���
     * @param strFormat
     *            ת����ʽ
     * @return
     */
    public Date stringToDate(String str, String strFormat) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
        Date date = new Date();
        try {
            date = sdFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    /**
     * ����ʽΪ2010-03-06 ת��Ϊ0306 ��ʽ���ַ� ���ڳ�ʼ������
     *
     * @param str
     * @return
     */
    public String holidayStr(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                if(strs[1].length()==1){
                    strs[1]="0"+strs[1];
                }
                if(strs[2].length()==1){
                    strs[2]="0"+strs[2];
                }
                temp = strs[1] + strs[2];
            }
        }
        return temp;
    }
    /**
     * ����ʽΪ2010-03-06 ת��Ϊ0306 ��ʽ���ַ� ���ڳ�ʼ������
     * @param str
     * @return
     */
    public String holiday2Str(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                temp = strs[1] + strs[2];
            }
        }
        return temp;
    }

    /**
     * ����ʽΪ2010-03-06 ת��Ϊ2010-03-06 ��ʽ���ַ�
     *
     * @param str
     * @return
     */
    public String holidayDateStr(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                temp = strs[0] + "-" + strs[1] + "-" + strs[2];
            }
        }
        return temp;
    }

    /**
     * �����������
     *
     * @param li
     *            ������ڵ�ʱ�伯��
     * @return
     */
    public Date getMaxDate(List<Date> li) {
        Date max = null;
        if (li != null && li.size() > 0) {
            max = li.get(0);
            for (int i = 0; i < li.size(); i++) {
                if (max.getTime() < li.get(i).getTime()) {
                    max = li.get(i);
                }
            }
        }
        return max;
    }

    /**
     * ������С����
     *
     * @param li
     *            ������ڵ�ʱ�伯��
     * @return
     */
    public Date getMinDate(List<Date> li) {
        Date min = null;
        if (li != null && li.size() > 0) {
            min = li.get(0);
            for (int i = 0; i < li.size(); i++) {
                if (min.getTime() > li.get(i).getTime()) {
                    min = li.get(i);
                }
            }
        }
        return min;
    }

    /**
     * ����ʽΪ 20100306 ���ַ���ת��Ϊʱ��Ϊ2010-03-06������ʽ
     *
     * @param str
     *            �����ַ���
     * @return
     */
    public Date getDate(String str) {
        Date date = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                    + str.substring(6);
            date = this.stringToDate(temp, "yyyy-MM-dd");
        }
        return date;
    }

    /**
     * ��ȡ����+�Ƿ������� ���յ�List����( ÿ�궼�ǹ̶���)
     *
     * @param year
     * @return
     */
    public Map getStaregnHolidays(int year) {
        Map map = new HashMap();
        map.put(year + "0214", "���˽�");
        map.put(year + "0308", "��Ů��");
        map.put(year + "0312", "ֲ����");
        map.put(year + "0315", "������Ȩ����");
        map.put(year + "0317", "St. Patrick's");
        map.put(year + "0401", "���˽�");
        map.put(year + "0501", "�Ͷ���");
        map.put(year + "0504", "�����");
        map.put(year + "0512", "��ʿ��");
        map.put(year + "0601", "��ͯ��");
        map.put(year + "0614", "Flag Day");
        map.put(year + "0701", "������ ��ۻع����");
        map.put(year + "0703", "�׻����ߵ���");
        map.put(year + "0718", "���յ���");
        map.put(year + "0801", "������");
        map.put(year + "0808", "���׽�");
        map.put(year + "0909", "ë����������");
        map.put(year + "0910", "��ʦ��");
        map.put(year + "0928", "���ӵ���");
        map.put(year + "1006", "���˽�");
        map.put(year + "1024", "���Ϲ���");
        map.put(year + "1111", "Veteran's / Remembrance Day");
        map.put(year + "1112", "����ɽ��������");
        map.put(year + "1220", "���Żع����");
        map.put(year + "1225", "Christmas Day");
        map.put(year + "1226", "ë�󶫵�������");
        return map;
    }

    /**
     * ���� + ��������
     *
     * @param year
     * @return
     */
    public Map getstatutoryHolidays(int year) {
        Map map = new HashMap();
        //Ԫ��
        map.put(year + "0101", "Ԫ��");
        map.put(year + "0102", "Ԫ��");
        map.put(year + "0103", "Ԫ��");
        //������
        map.put(year + "0403", "������");
        map.put(year + "0404", "������");
        map.put(year + "0405", "������");
        //�Ͷ���
        map.put(year + "0501", "�Ͷ���");
        map.put(year + "0502", "�Ͷ���");
        map.put(year + "0503", "�Ͷ���");
        //�����
        map.put(year + "1001", "�����");
        map.put(year + "1002", "�����");
        map.put(year + "1003", "�����");
        map.put(year + "1004", "�����");
        map.put(year + "1005", "�����");
        map.put(year + "1006", "�����");
        map.put(year + "1007", "�����");
        return map;
    }

    /**
     * ���ũ��+�Ƿ��� ���յ�list
     *
     * @param year
     * @return
     */
    public Map getStaregnHoliday(int year) {
        Map map = new HashMap();
        map.put(year + "0115", "Ԫ����");
        map.put(year + "0505", "�����");
        map.put(year + "0707", "��Ϧ���˽�");
        map.put(year + "0715", "��Ԫ��");
        map.put(year + "0909", "������");
        map.put(year + "1208", "���˽�");
        map.put(year + "1224", "С��");
        return map;

    }

    /**
     * ���ũ��+���� ���յ�list
     *
     * @param year
     * @return
     */
    public Map getstatutoryLunarHoliday(int year) {
        Map map = new HashMap();
        //����
        //map.put(year + "0100", "��Ϧ");
        map.put(year + "0101", "����");
        map.put(year + "0102", "����");
        map.put(year + "0103", "����");
        map.put(year + "0104", "����");
        map.put(year + "0105", "����");
        map.put(year + "0106", "����");
        //�����
        map.put(year + "0813", "�����");
        map.put(year + "0814", "�����");
        map.put(year + "0815", "�����");
        return map;
    }
    /**
     * �� 2010-1-2  ת��Ϊ2010-01-02  �������ݿ�Ƚ�
     * @return
     */
    public String change(String str){
        if(str != null && str.trim().length() > 0){
            return "";
        }
        String temp="";
          String[] strs=str.split("-");
          StringBuffer sb=new StringBuffer();
          sb.append(strs[0]+"-");
          temp=strs[1];
          if(temp.length()<2){
             sb.append("0"+temp);
          }else{
              sb.append(temp);
          }
          sb.append("-");
          temp=strs[2];
          if(temp.length()<2){
              sb.append("0"+temp);
           }else{
               sb.append(temp);
           }
          return sb.toString();
    }
}
