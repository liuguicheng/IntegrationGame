package com.plugins.dictionary.service;

import java.util.List;

import org.springline.orm.Page;

import com.plugins.dictionary.command.DicDataEditInfo;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;


public interface IDictionaryService {

	/**
	 * ��ȡ�ֵ����͵ķ�ҳ����
	 * @param info
	 * @return
	 */
	Page getDicTypeQueryPage(DicTypeQueryInfo info);

	/**
     * �������е��ֵ�����
     * @return
     */
    List selectAllDicType();

	/**
	 * ����ID�����ֵ����͵�����
	 * @param id
	 * @return
	 */
	DicType getDicTypeById(String id);

	/**
	 * ��ȡ�ֵ����͵��������
	 * @return
	 */
	Integer getMaxTypeSort();

	/**
	 * �����ֵ����͵�����
	 * @param info
	 */
	void saveDicType(DicTypeEditInfo info);

	/**
	 * �Զ��������ͱ��룬�������Ƿ񶨳��Զ���0
	 * @param isLonger
	 * @param typeCode
	 * @return
	 */
	String getMaxDataCode(String typeCode, String isLonger, Integer dataLong);

	/**
	 * �������ͱ���������е��ֵ�����
	 * @param typeCode
	 * @return
	 */
	List selectAllDicDataByCode(String typeCode);

	/**
	 * ���ݱ����ȡһ�����ͼ�¼
	 * @param typeCode
	 * @return
	 */
	DicType getDicTypeByCode(String typeCode);

	/**
	 * �����ֵ�ID��ȡһ������
	 * @param dataId
	 * @return
	 */
	DicData getDicDataById(String dataId);

	/**
	 * ��ȡ�ֵ���������
	 * @param typeCode
	 * @return
	 */
	Integer getMaxDataSort(String typeCode);

	/**
	 * �����ֵ�����
	 * @param info
	 */
	void saveDicData(DicDataEditInfo info);

	/**
	 * �߼�����ɾ���ֵ�����
	 * @param ids
	 */
	void deleteDicData(String[] ids);

	/**
	 * �����ֵ�������Ϣ
	 * @param ids
	 */
	void updateDicData(String[] ids);

	/**
	 * ɾ����������
	 * @param ids
	 */
	void deleteDicType(String[] ids);
	/**
     * �������ͱ����ȡһ������
     * @param typeCode,code
     * @return DicData
     */
	String getDicDataByType(String typeCode,String code);
}
