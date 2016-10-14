package com.plugins.dictionary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dictionary.service.IDictionaryService;

public class DicTypeController extends SpringlineMultiActionController{

	/**�ֵ�dictionaryService�ӿ�*/
	private IDictionaryService dictionaryService;
	/**�ֵ������б���ͼ*/
	private String dicTypeQueryView;


	/**
	 *
	 * @return ����dictionaryService
	 */
	public IDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 *
	 * @param dictionaryService ����dictionaryService
	 */
	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 *
	 * @return ����dicTypeQueryView��ͼ
	 */

	public String getDicTypeQueryView() {
		return dicTypeQueryView;
	}

	/**
	 * ����dicTypeQueryView��ͼ
	 * @param dicTypeQueryView
	 */
	public void setDicTypeQueryView(String dicTypeQueryView) {
		this.dicTypeQueryView = dicTypeQueryView;
	}



    /**
     * ����ɾ����ѡ����ֵ���������
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
	public ModelAndView dicTypeDelete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] checkValue = request.getParameterValues("typeId");
		//ɾ��checkValue�������ֵ
		this.dictionaryService.deleteDicType(checkValue);
		//���ñ��ص�dicTypeList����
		return new ModelAndView(new GBRedirectView(this.dicTypeQueryView));
	}
}
