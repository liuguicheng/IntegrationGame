/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service;

import java.util.List;

import org.springline.orm.Page;

import com.plugins.cms.channel.command.CmsChannelEditInfo;
import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.entity.CmsChannel;

/**
 * @description
 */
public interface ICmsChannelService {

    /**
     * ��ѯ��Ŀ
     * @param info
     * @return
     */
    Page selectChannelList(CmsChannelQueryInfo info);

    /**
     * ������Ŀ����
     * @param channelId
     * @return
     */
    CmsChannel loadChannel(String channelId);

    /**
     * ����
     * @param info
     */
    void saveChannelEditInfo(CmsChannelEditInfo info);

    /**
     * ��ȡ����˳���
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);

    /**
     * ɾ����Ŀ
     * @param ids
     */
    void deleteChannels(String[] ids);
    
	/**
	 * �������ͱ���������е��ֵ�����
	 * @param typeCode
	 * @return
	 */
	List loadAllCmsChannel();
}
