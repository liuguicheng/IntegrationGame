package com.plugins.dictionary;

import com.plugins.dictionary.service.IDictionaryService;

public abstract class DictionaryHelper {

	private static DictionaryHelper instance = null;

	/**
	 * @return the instance
	 */
	public static DictionaryHelper getInstance() {
		if (instance == null) {
			throw new RuntimeException("δ����DictionaryHelper��ʵ������");
		}
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	protected void setInstance(DictionaryHelper instance) {
		DictionaryHelper.instance = instance;
	}

	/**
	 * ע��
	 * @param objName
	 */
	public abstract void registerConnector(String objName);
	/**
	 * @return IAppItemService
	 */
	public abstract IDictionaryService getDictionaryService();

}
