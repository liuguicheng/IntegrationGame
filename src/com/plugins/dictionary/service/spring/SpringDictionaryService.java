package com.plugins.dictionary.service.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springline.beans.cache.CacheHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.plugins.dictionary.DictionaryHelper;
import com.plugins.dictionary.command.DicDataEditInfo;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;
import com.plugins.dictionary.service.dao.IDictionaryDao;

public class SpringDictionaryService implements IDictionaryService {


	/**ע��dictionaryDao*/
	private IDictionaryDao dictionaryDao;

	/**
	 * @param dictionaryDao the dictionaryDao to set
	 */
	public void setDictionaryDao(IDictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}


	/**
	 * @see com.plugins.dionary.service.IDictionaryService#getDicTQueryPage(com.plugins.dictionary.command.DicTypeQueryInfo)
	 */
	public Page getDicTypeQueryPage(DicTypeQueryInfo info) {
		return this.dictionaryDao.getDicTypeQueryPage(info);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#selectAllDicType()
	 */
	public List selectAllDicType() {
		return this.dictionaryDao.loadAll(DicType.class, "typeSort");
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicTypeById(java.lang.String)
	 */
	public DicType getDicTypeById(String id) {
		return (DicType)this.dictionaryDao.load(DicType.class, id);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#saveDicType(com.plugins.dictionary.command.DicTypeEditInfo)
	 */
	public void saveDicType(DicTypeEditInfo info) {
		DicType dicType = new DicType();
		String currentCode = info.getTypeCode();
		if (info.getTypeId() != null && info.getTypeId().trim().length() != 0) {
			dicType = this.getDicTypeById(info.getTypeId());
			currentCode = dicType.getTypeCode();
		}
		//�ж����ͱ����Ƿ�����ظ�������ظ����쳣
		if (this.dictionaryDao.checkExitTypeCode(dicType.getTypeCode(), info.getTypeCode())) {
			throw new RuntimeException("���ͱ��롺" + info.getTypeCode() + "���ѱ�ʹ�ã������±༭���ͱ��룡");
		}
		try {
			BeanUtils.copyProperties(info, dicType, new String[] {"typeId"});//��������
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		this.dictionaryDao.save(dicType);
		if (!info.getTypeCode().equals(currentCode)) {
			//���ֵ���뷢���ı�ʱ�����������ֵ����ݱ�����ݣ��Ա����ͬ��
			this.dictionaryDao.updateDicData(info.getTypeCode(), currentCode);
			//���ֵ����ı�ʱ��ע���µ��ֵ䣬��ע���ɵ��ֵ�
			this.registerData(dicType.getTypeCode());
		}
		//������ʱע���ֵ�
		if (info.getTypeId() == null || info.getTypeId().trim().length() == 0) {
			this.registerData(info.getTypeCode());
		}

	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxTypeSort()
	 */
	public Integer getMaxTypeSort() {
		return this.dictionaryDao.getMaxTypeSort();
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxDataCode(java.lang.String, java.lang.String)
	 */
	public String getMaxDataCode(String typeCode, String isLonger, Integer dataLong) {
		int maxDataCode = this.dictionaryDao.getMaxDataCode(typeCode);
		String dataCode = "" + maxDataCode;
		if (isLonger.equals(ConsoleHelper.YES)) {//����
			dataCode = String.format("%0" + dataLong + "d", maxDataCode);
		}
		return dataCode;
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#selectAllDicDataByCode(java.lang.String)
	 */
	public List selectAllDicDataByCode(String typeCode) {
		return this.dictionaryDao.loadAll(DicData.class, "typeCode", typeCode, "dataSort");
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicTypeByCode(java.lang.String)
	 */
	public DicType getDicTypeByCode(String typeCode) {
		DicType dicType = null;
		List list = this.dictionaryDao.loadAll(DicType.class, "typeCode", typeCode, "typeSort");
		if (list != null && list.size() > 0) {
			dicType = new DicType();
			dicType = (DicType)list.get(0);
		}
		return dicType;
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicDataById(java.lang.String)
	 */
	public DicData getDicDataById(String dataId) {
		return (DicData)this.dictionaryDao.load(DicData.class, dataId);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxDataSort(java.lang.String)
	 */
	public Integer getMaxDataSort(String typeCode) {
		return this.dictionaryDao.getMaxDataSort(typeCode);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#saveDicData(com.plugins.dictionary.command.DicDataEditInfo)
	 */
	public void saveDicData(DicDataEditInfo info) {
		DicData dicData = new DicData();
		if (info.getDataId() != null && info.getDataId().trim().length() != 0) {
			BeanUtils.copyProperties(info, dicData);//��������
		} else {
			if (info.getTypeWay().equals(DicData.AUTO_GENERATE)) {//�����ϵͳ���ɷ�ʽ
				dicData.setDataCode(this.getMaxDataCode(info.getTypeCode(), info.getIsLonger(), info.getDataLength()));
			} else {//�ֹ�¼�뷽ʽ
				//�������ͱ����ж����ݱ����Ƿ����
				if (!this.dictionaryDao.checkExitDataCode(info.getDataCode(), info.getTypeCode())) {
					throw new RuntimeException("�ֵ���롺" + info.getDataCode() + "���ѱ�ʹ�ã������±༭�ֵ���룡");
				}
			}
			BeanUtils.copyProperties(info, dicData, new String[] {"dataId"});//��������
		}
		this.dictionaryDao.save(dicData);
		//����֮��ˢ���ֵ�
		this.refreshData(dicData.getTypeCode());
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#deleteDicData(java.lang.String[])
	 */
	public void deleteDicData(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicData dicData = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicData = new DicData();
			dicData = this.getDicDataById(ids[nI]);
			if (dicData != null) {
				dicData.setIsValid(ConsoleHelper.NO);
				this.dictionaryDao.update(dicData);
				//���º�ˢ���ֵ�
				this.refreshData(dicData.getTypeCode());
			}
		}
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#updateDicData(java.lang.String[])
	 */
	public void updateDicData(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicData dicData = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicData = new DicData();
			dicData = this.getDicDataById(ids[nI]);
			if (dicData != null) {
				dicData.setIsValid(ConsoleHelper.YES);//����ʹ��
				this.dictionaryDao.update(dicData);
				//���º�ˢ���ֵ�
				this.refreshData(dicData.getTypeCode());
			}
		}
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#deleteDicType(java.lang.String[])
	 */
	public void deleteDicType(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicType dicType = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicType = new DicType();
			dicType = this.getDicTypeById(ids[nI]);
			if (dicType != null) {
				//ɾ����ص��ֵ���������
				this.dictionaryDao.deleteDicData(dicType.getTypeCode());
				this.dictionaryDao.delete(dicType);
				//ɾ����ע��ԭ�е��ֵ�
			}
		}
	}

	/**
	 * ˢ���ֵ�
	 * @param typeCode
	 */
	private void refreshData(String typeCode) {
		CacheHelper.getInstance().dispatchRefreshEvent(typeCode);
	}

	/**
	 * ע���ֵ�
	 * @param typeCode
	 */
	private void registerData(String typeCode) {
		DictionaryHelper.getInstance().registerConnector(typeCode);
	}


	@Override
	public String getDicDataByType(String typeCode, String code) {
		 List<DicData> dics = this.selectAllDicDataByCode(typeCode);
	        for(DicData dic: dics){
	            if(dic.getDataCode().equals(code)){
	                return  dic.getDataName();
	            }
	        }
	        return "";
	}

}
