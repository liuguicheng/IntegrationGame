/**
 * Description:
 * History:  2009-7-22 Create
**/

package com.console.main.service.dao;


import org.springline.orm.dao.ICommonDao;

import com.console.entity.Staff;

/**
 * @description
 */
public interface IMainDao extends ICommonDao {
    /**
     * ���ݵ�¼����ȡԱ����Ϣ��δ�һ�Ա��״̬��Ч������null.
     * @param loginName ��¼����
     * @return  Staffʵ��
     */
    Staff selectStaff(String loginName);
    
 
    
    /**
     * ���ݵ�¼����ȡԱ����Ϣ��������Ч״̬��Ա����δ�ҵ�����null.
     * @param loginName ��¼����
     * @return  Staffʵ��
     */
    Staff selectAllStaff(String loginName);
    
}
