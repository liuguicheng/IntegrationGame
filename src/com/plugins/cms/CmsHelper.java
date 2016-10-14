package com.plugins.cms;

import java.util.Observable;

import org.springline.orm.Page;

import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.entity.CmsChannel;
import com.plugins.cms.entity.CmsContent;

public abstract class CmsHelper extends Observable {

    /**
     * ��ʵ�����๤�� Spring IOC����
     */
    private static CmsHelper instance = null;
    /**
     * ����״̬
     */
    public static final String STATUS_DELETED = "0";
    /**
     * ����״̬
     */
    public static final String STATUS_NORMAL = "1";
    /**
     * @return the instance
     */
    public static CmsHelper getInstance() {
        if (instance == null) {
            throw new RuntimeException("δ����CMSHelper��ʵ������");
        }
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(CmsHelper instance) {
        CmsHelper.instance = instance;
    }
    
    /**
     * ����Id������Ŀ
     * @param channelId
     * @return
     */
    public abstract CmsChannel loadCmsChannel(String channelId);
    /**
     * ����·�������ȡ��Ŀ
     * @param channelPath
     * @return
     */
    public abstract CmsChannel selectCmsChannel(String channelPath);
    
    /**
     * ��ȡ�����嵥
     * @param info
     * @return
     */
    public abstract Page selectCmsContentList(CmsContentQueryInfo info);

    /**
     * @param contentId
     * @return
     */
    public abstract CmsContent loadCmsContent(String contentId);

}
