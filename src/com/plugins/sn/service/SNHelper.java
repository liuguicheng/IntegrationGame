
package com.plugins.sn.service;


public class SNHelper {

    /**��ʵ��ģʽ */
    private static ISerialNumberService serialNumberService;

    /** ��ȡ����ʵ��
     * @return Returns the me.
     */
    public static ISerialNumberService getSNService() {
        return serialNumberService;
    }

    /**
     * @param serialNumberService The serialNumberService to set.
     */
    public void setSerialNumberService(
            ISerialNumberService serialNumberService) {
        SNHelper.serialNumberService = serialNumberService;
    }

}
