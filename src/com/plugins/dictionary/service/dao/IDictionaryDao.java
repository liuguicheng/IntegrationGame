package com.plugins.dictionary.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.dictionary.command.DicTypeQueryInfo;


/**
 *
 * @author wqy
 *@������DictionaryDao�ֵ�Dao�ӿ�
 */
public interface IDictionaryDao extends ICommonDao{

	/**
     *��ȡ�ֵ����͵ķ�ҳ����
     * @param info
     * @return
     */
    Page getDicTypeQueryPage(DicTypeQueryInfo info);

    /**
     * ��ȡ�ֵ����͵ĵ�ǰ�������
     * @return
     */
    Integer getMaxTypeSort();

    /**
     * �ж��ֵ��ʶ�Ƿ��Ѿ�����
     * @param oldTypeCode
     * @param newTypeCode
     * @return
     */
    public boolean checkExitTypeCode(String oldTypeCode, String newTypeCode);

    /**
     * ��ȡ�ֵ����͵ĵ�ǰ�������
     * @return
     */
    Integer getMaxDataSort(String typeCode);

    /**
     * �����ֵ�����ID�ж��Ƿ�����ֵ��ʶ
     * @param oldDataCode
     * @param dataCode
     * @param typeId
     * @return
     */
    public boolean checkExitDataCode(String dataCode,String typeCode);

    /**
     * �������ͱ�������ֵ��
     * @param newTypeCode
     * @param oldTypeCode
     */
    void updateDicData(String newTypeCode, String oldTypeCode);

    /**
	 * �Զ��������ͱ���
	 * @param isLonger
	 * @param typeCode
	 * @param dataLong
	 * @return
	 */
	int getMaxDataCode(String typeCode);

	/**
	 * �������ͱ�������ɾ���ֵ�����
	 * @param typeCode
	 */
	void deleteDicData(String typeCode);
}
