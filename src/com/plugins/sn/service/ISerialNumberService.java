
package com.plugins.sn.service;

public interface ISerialNumberService {


    /** ����ǰ׺��ʶ��ȡָ�����ȵ����
     * @param classify ���ͱ�ʶ��
     * @param prefix ǰ׺��ʶ��
     * @param withPrefix ���ص�����Ƿ��ǰ׺
     * @return  �������ȵ����
     */
    String getSerialNumber(String classify, String prefix);
    /** ����ǰ׺��ʶ��ȡָ�����ȵ����
     * @param classify ���ͱ�ʶ��
     * @param prefix ǰ׺��ʶ��
     * @param length ��ų���
     * @return ָ�����ȵ����
     */
    String getSerialNumber(String classify, String prefix, int length);

    /** ����ǰ׺��ʶ��ȡָ�����ȵ����
     * @param classify ���ͱ�ʶ��
     * @param prefix ǰ׺��ʶ��
     * @param withPrefix ���ص�����Ƿ��ǰ׺
     * @return  �������ȵ����
     */
    String getSerialNumber(String classify, String prefix, boolean withPrefix);

    /** ����ǰ׺��ʶ��ȡָ�����ȵ����
     * @param classify ���ͱ�ʶ��
     * @param prefix ǰ׺��ʶ��
     * @param length ��ų���
     * @return ָ�����ȵ����
     */
    String getSerialNumber(String classify, String prefix, int length, boolean withPrefix);

}
