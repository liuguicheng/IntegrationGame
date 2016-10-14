package com.plugins.dictionary.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.service.IDictionaryService;
/**
 *�ֵ����ͷ�ҳ������
 * @author wanqiuyu
 *
 */
public class DicTypeQueryController extends PaginationQueryController{

	/**�ֵ�dictionaryService�ӿ�*/
	private IDictionaryService dictionaryService;

	/**
	 * ���� IDictionaryService
	 * @return
	 */
	public IDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 * ����IDictionaryService
	 * @param dictionaryService
	 */
	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@Override
	protected Page selectQueryResult(HttpServletRequest request,
			HttpServletResponse response, PaginationInfo command, Map model)
			throws Exception {
		DicTypeQueryInfo info = (DicTypeQueryInfo)command;
		Page page = this.dictionaryService.getDicTypeQueryPage(info);
		return page;
	}

}
