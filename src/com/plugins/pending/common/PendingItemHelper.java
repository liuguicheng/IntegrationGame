package com.plugins.pending.common;

public abstract class PendingItemHelper {

    /** �������ı�ʶ */
    public static String FLAG_PENDINGITEM = "pendingItemFlag";

    /** ����ģ���Url */
    private static String pendingItemBackUrl = "";

    /**
     *
     */
    private static IPendingItem pendingItem;

    /**
     * @param pendingItem
     *            IPendingItem
     */
    protected void setPendingItem(IPendingItem pendingItem) {
        PendingItemHelper.pendingItem = pendingItem;
    }

    /**
     * @return IPendingItem
     */
    public static IPendingItem getInstance() {
        return pendingItem;
    }

    /**
     * ����ҵ��󷵻ش���ģ���Url
     * 
     * @return
     */
    public static String getPendingItemBackUrl() {
        return pendingItemBackUrl;
    }

    protected void setPendingItemBackUrl(String url) {
        PendingItemHelper.pendingItemBackUrl = url;

    }
}
