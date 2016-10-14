package com.systemic.basic.sms.support;

public class Tools
{

	/**
	 * �绰���볤�ȣ�11λ
	 */
	private static final Integer NUM_LENGTH = 11;
	/**
	 * ������Դ��ʾǰ׺���ȣ�3λ
	 */
	private static final Integer NUM_PREFIX_LENGTH = 3;

	public Tools()
	{
	}

	/**
	 * ��ż���ֻ������Ƿ���11λ���֣����Ƿ���13��ͷ
	 * @param sMobile String   ������ֻ�����
	 * @return boolean         true���Ϲ淶
	 */
	public static boolean checkMobile(String sMobile) {
		if (sMobile == null) {
			return false;
		}
		if (sMobile.length() != 11) {
			return false;
		}
		return true;
	}


	/**
	 * ȡ���ֻ���������Ӫ��
	 * @param mobileNum �绰����
	 * @return String (0�ƶ�,1��ͨ,2ģ��,3����)
	 */
	public static String  getMobileType(String mobileNum)
	{
		if (mobileNum == null || mobileNum.length() == 0||mobileNum.length() != 11)
		{
			return null;
		}

		if (isMoblie(mobileNum))
			return "0";
		else if (isUnicom(mobileNum))
			return "1";
		else if (isTelcom(mobileNum))
			return "3";
		else if (mobileNum.substring(0,3).equals("999"))
			return "2";
		else
			return null;
	}

	/**
	 * �ж��Ƿ�Ϊ�ƶ����ֻ�����
	 * @param num �绰����
	 * @return boolean
	 */
	public static boolean isMoblie(String num) {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "134,135,136,137,138,139,147,150,151,152,154,157,158,159,182,183,187,188";//BaseConfig.getInstance().getParamterValue("����ǰ׺", "�ƶ�", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;

	}

	/**
	 * �ж��Ƿ�Ϊ��ͨ���ֻ�����
	 * @param num �绰����
	 * @return boolean
	 */
	public static boolean isUnicom(String num)  {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "130,131,132,145,155,156,185,186";//BaseConfig.getInstance().getParamterValue("����ǰ׺", "��ͨ", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;
	}

	/**
	 * �жϵ绰�����Ƿ�Ϊ���ŵĺ���
	 * @param num �绰����
	 * @return boolean
	 */
	public static boolean isTelcom(String num) {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "133,153,180,189";// BaseConfig.getInstance().getParamterValue("����ǰ׺", "����", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;
	}


}
