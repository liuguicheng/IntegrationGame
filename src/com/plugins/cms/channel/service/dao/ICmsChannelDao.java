/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.cms.channel.command.CmsChannelQueryInfo;

/**
 * @description
 */
public interface ICmsChannelDao extends ICommonDao {

    /**
     * ��ѯ��Ŀ
     * @param info
     * @return
     */
    Page selectCmsChannelList(CmsChannelQueryInfo info);

    /**
     * ��ȡ����˳���
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);


}
