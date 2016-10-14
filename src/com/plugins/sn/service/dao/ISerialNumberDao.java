
package com.plugins.sn.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;



public interface ISerialNumberDao extends ICommonDao {

    /** �������ͱ�ʶ��ǰ׺��ʶ��ȡ���
     * @param classifyIdentifier ���ͱ�ʶ
     * @param prefixIdentifier ǰ׺��ʶ
     * @return ����б�
     */
    List selectSysSerialNumber(String classifyIdentifier, String prefixIdentifier);
}
