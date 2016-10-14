

package com.plugins.pending.service;

import org.springline.orm.Page;

import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;

 /**
 * @description
 */
public interface IPendingItemService  {

    /**
     * ��ȡ����������������ͳ����
     * @param modulename ģ������
     * @param id ��������˱��
     * @return ͳ����
     */
    Integer selectPendingItemCount(String modulename, String id);
    /**
     * ��ѯ��������
     * @param info PendingItemQureyInfo
     * @return Page
     */
    Page selectPendingItem(PendingItemQureyInfo info);

    /**��ѯ����������
     * @param info
     * @return
     */
    Page selectFinishedItems(FinishedItemQueryInfo info);
}
