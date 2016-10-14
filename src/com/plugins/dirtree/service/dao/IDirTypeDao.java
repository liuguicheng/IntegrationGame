package com.plugins.dirtree.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.plugins.dirtree.entity.DirType;

public interface IDirTypeDao extends ICommonDao {

	/**
	 * ��� Ŀ¼���ͱ����Ƿ����ظ�
	 * @param oldTypeCode ԭ���ͱ���
	 * @param newTypeCode �����ͱ���
	 * @return true ���ظ���false δ�ظ�
	 */
	boolean checkEditDirTypeCode(String oldTypeCode, String newTypeCode);
	/**
	 * ��ȡ��Ч��Ŀ¼�����б�
	 * @return
	 */
	List selectValidDirTypes();
	/**
	 * ����Ŀ¼���ͱ�ʶ��ȡ����
	 * @param dirTypeCode
	 * @return
	 */
	DirType selectDirTypeByCode(String dirTypeCode);
	/**
	 * ��ȡ��Ч��Ŀ¼�������
	 * @return
	 */
	Integer selectUsableOrderNumber();

}
