/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service;

import org.springline.orm.Page;

import com.plugins.cms.content.command.CmsContentEditInfo;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.entity.CmsContent;

/**
 * @description
 */
public interface ICmsContentService {

    /**
     * ��ѯ��Ŀ
     * @param info
     * @return
     */
    Page selectContentList(CmsContentQueryInfo info);

    /**
     * ������Ŀ����
     * @param contentId
     * @return
     */
    CmsContent loadContent(String contentId);

    /**
     * ����
     * @param info
     */
    void saveContentEditInfo(CmsContentEditInfo info);

    /**
     * ��ȡ����˳���
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);

    /**
     * ɾ������
     * @param ids
     */
    void deleteContents(String[] ids);
}
