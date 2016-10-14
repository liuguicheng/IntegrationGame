package com.plugins.dirtree.service;

import java.util.List;

import com.plugins.dirtree.command.DirDataEditInfo;
import com.plugins.dirtree.command.DirTypeEditInfo;
import com.plugins.dirtree.entity.DirData;
import com.plugins.dirtree.entity.DirType;

public interface IDirService {
	/**
	 * ���� Ŀ¼����
	 * @param info Ŀ¼������Ϣ
	 */
	boolean saveDirType(DirTypeEditInfo info);
	/**
	 * ɾ�� Ŀ¼���ͣ���������Ϊ��Ч
	 * @param id Ŀ¼���ͱ��
	 */
	void deleteDirType(String[] ids);
	/**
	 * ����Ŀ¼����Ϊ��Ч
	 * @param id
	 */
	void doDirTypeUse(String id);
	/**
	 * ��ȡĿ¼�����б�
	 * @return Ŀ¼�����б�
	 */
	List selectAllDirTypes();
	/**
	 * ��ȡ��ЧĿ¼�����б�
	 * @return
	 */
	List selectValidDirTypes();
	/**
	 * ��ȡ��Ч��Ŀ¼�������
	 * @param parentId
	 * @return
	 */
	Integer selectUsableDirTypeOrderNumber();
	/**
	 * ����Ŀ¼������
	 * @param ids
	 */
	void doDirTypeSort(String[] ids);
	/**
	 * ����Ŀ¼���ͱ�Ż�ȡ����
	 * @param dirTypeId
	 * @return
	 */
	DirType selectDirType(String dirTypeId);
	/**
	 * ����Ŀ¼���ͱ�ʶ��ȡ����
	 * @param dirTypeCode
	 * @return
	 */
	DirType selectDirTypeByCode(String dirTypeCode);
	/**
	 * ����Ŀ¼�����ͻ�ȡĿ¼�����ýӿڴӻ����л�ȡ��Ч��Ŀ¼��
	 * @param typecode
	 * @param parentid
	 * @return
	 */
	List selectDirDataTreeByType(String typecode, String parentid);
	/**
	 * ����Ŀ¼�����ͻ�ȡĿ¼��
	 * @param typecode
	 * @param parentid
	 * @return
	 */
	List selectDirDataList(String typeid, String parentid);
	/**
	 * �����ϼ�Ŀ¼��ȡĿ¼���Ŀ������
	 * @param parentId
	 * @return
	 */
	Integer selectUsableDirDataOrderNumber(String typeId,String parentId);
	/**
	 * ��ѯĿ¼��Ϣ
	 * @param id Ŀ¼���
	 * @return
	 */
	DirData selectDirData(String id);
	/**
	 * ����Ŀ¼
	 * @param info
	 * @return
	 */
	boolean saveDirData(DirDataEditInfo info);
	/**
	 * �����ֵ�
	 * @param dirtypeid
	 * @param dirTypecode
	 */
	void doRefreshDirDataDic(String dirtypeid,String dirTypecode);
	/**
	 * �߼�ɾ��Ŀ¼����
	 * @param dirDataid Ŀ¼ID
	 */
	void doDirDataDelete(String dirDataid);
	/**
	 * ����Ŀ¼����
	 * @param dirDataid Ŀ¼ID
	 */
	void doDirDataUse(String dirDataid);
	/**
	 * ����Ŀ¼����ID�����˳������
	 * @param ids ID����
	 */
	void doDirDataSort(String[] ids);
}
