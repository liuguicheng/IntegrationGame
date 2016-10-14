/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.cms.content.command.CmsContentQueryInfo;

/**
 * @description
 */
public interface ICmsContentDao extends ICommonDao {

    /**
     * ��ѯ��Ŀ
     * @param info
     * @return
     */
    Page selectCmsContentList(CmsContentQueryInfo info);

    /**
     * ��ȡ����˳���
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);
}
