/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.command;

import org.springline.web.pagination.PaginationInfo;

/**
 * @description
 */
public class CmsChannelQueryInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = 3875622466440828763L;

    /**
     * ���ڵ㶨��
     */
    public static final String ROOT = "root";
    /**
     * �ϼ���Ŀ������ĿΪroot
     */
    private String parentId;
    
    private String channelPath;
    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * @return the channelPath
     */
    public String getChannelPath() {
        return channelPath;
    }
    /**
     * @param channelPath the channelPath to set
     */
    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }
}
