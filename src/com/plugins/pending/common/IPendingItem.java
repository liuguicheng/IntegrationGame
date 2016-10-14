

package com.plugins.pending.common;

import java.util.List;
import java.util.Properties;

import org.springline.orm.Page;

import com.console.entity.Staff;


/**
 * @author Administrator
 *
 */
public interface IPendingItem {

//    /**
//     * ����һ����������
//     * @param moduleName ��������������ģ����
//     * @param flagId ��ͬһ��ģ���±�ʶ�ô��������Id
//     * @param url ��ô�����������Ĳ��������url
//     * @param title ��������ı���
//     * @param sender ������
//     * @param recipients ������
//     */
//    void openItem(String moduleName, String flagId, String  url,
//            String title, Staff sender, List recipients);
    /**
     * ����һ����������
     * @param moduleName ��������������ģ�������༶ģ����÷�б�ָܷ����繫�Ĺ���/���Ĺ���
     * ��ѯʱ��moduleNameʹ��ģ����ѯ���ơ������Ҫ��ȡ���������ģ�����ƣ���ʹ��PendingItem.getSimpleName()
     * @param flagId ��ͬһ��ģ���±�ʶ�ô��������Id
     * @param url ��ô�����������Ĳ��������url
     * @param title ��������ı���
     * @param sender ������
     * @param recipients ������
     * @param props ��չ���ԣ���Properties��ʽд�룬��ȡʱʹ��PendingItem.getProperty(String propertyName)
     */
    void doOpenItem(String moduleName, String flagId, String  url,
            String title, Staff sender, List recipients, Properties props);
    /**
     * ���һ����������
     * @param moduleName ��������������ģ����
     * @param flagId ��ͬһ��ģ���±�ʶ�ô��������Id
     * @param finisher ������
     */
    void doCloseItem(String moduleName, String flagId, Staff finisher);

    /**
     * ��ѯ���˴�������
     * @param userId �û�Id ��ȷ��λ������Ϊ��
     * @param moduleName ģ�����ƣ�ģ����ѯ
     * @return PageList Of PendingItem
     */
    Page selectPendingItemList(String userId, String moduleName);
}
