

package com.plugins.pending.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.entity.PendingItem;


public interface IPendingItemDao extends ICommonDao {

    /**
     * ��ȡ����������������ͳ����
     * @param modulename modulename
     * @param staffId staffId
     * @return ����������������ͳ����
     */
    List selectPendingItemCount(String modulename, String staffId);

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

    /**
     * ����ģ�����ͱ�ʶ��ȡ�����������
     * @param moduleName ��������������ģ����
     * @param flagId ��ͬһ��ģ���±�ʶ�ô��������Id
     * @return �����������
     *
     */
    public PendingItem getPendingItem(String moduleName, String flagId);
    /**
     * ��ȡĳ��������������н����˶���
     * @param forDoId ���������id
     * @return �����˶����б�
     *
     */
    public List getPendingItemRecipients(String forDoId);
}
