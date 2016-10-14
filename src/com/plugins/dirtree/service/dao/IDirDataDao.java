package com.plugins.dirtree.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;


public interface IDirDataDao extends ICommonDao {
	/**
	 * ����Ŀ¼�����ͻ��ϼ�Ŀ¼ȡĿ¼�б�
	 * @param typeid
	 * @param parentid
	 * @param isvalid �Ƿ���Ч
	 * @return
	 */
	List selectDirDataBy(String typeid,String parentid,String isvalid);
	/**
	 * ����Ŀ¼�����ͻ��ϼ�Ŀ¼ȡĿ¼�б�
	 * @param typeid
	 * @param isvalid
	 * @return
	 */
	List selectDirData(String typeid,String isvalid);
	/**
	 * �����ϼ�Ŀ¼��ȡĿ¼���Ŀ������
	 * @param parentId
	 * @return
	 */
	Integer selectUsableOrderNumber(String typeId,String parentId);
}
