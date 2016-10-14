package com.plugins.dictionary.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DicTypeEditController extends SpringlineSimpleFormController{

	/**�ֵ�dictionaryService�ӿ�*/
	private IDictionaryService dictionaryService;

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
	 * @see org.springline.web.mvc.SpringlineSimpleFormController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
			throws Exception {
		super.initBinder(request, binder);
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", true));
	}

	/**
	 *
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
	 */
	 @SuppressWarnings("unchecked")
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
		 Map model = new HashMap();
        DicTypeEditInfo dicTypeInfo = (DicTypeEditInfo) command;
    	String preTypeNo = request.getParameter("preTypeNo");
    	try {
	    	//��typeNo��Ϊ��ʱ��������ĸ��дת��
	    	StringBuffer typeCode = new StringBuffer(dicTypeInfo.getTypeCode());
	    	if (typeCode != null && typeCode.length() != 0) {
	    		typeCode.setCharAt(0, Character.toTitleCase(typeCode.charAt(0)));
	    		dicTypeInfo.setTypeCode(preTypeNo + typeCode.toString());

	    	}
	        model.put("message", "����ɹ�");
	        this.dictionaryService.saveDicType(dicTypeInfo);
    	} catch (Exception e) {
			model.put("message", "����ʧ�ܣ�" + e.getMessage());
		}
        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

	    /**
	     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	     */
	   @SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
    	Map model = new HashedMap();
    	String typeId = request.getParameter("typeId");
        //ID��Ϊ�ս�����󶨵�ҳ����
        if (typeId != null && typeId.trim().length() > 0) {
        	//��typeCode����λ��ȡ
        	DicType dicType = this.dictionaryService.getDicTypeById(typeId);
        	if (dicType.getTypeCode() != null && dicType.getTypeCode().trim().length() != 0) {
        		dicType.setTypeCode(dicType.getTypeCode().substring(3));
        	}
        	model.put("dicTypeInfo", dicType);
        } else {
        	DicTypeEditInfo dicTypeInfo = new DicTypeEditInfo();
	        //����ǰ���������set��dataSort��
        	dicTypeInfo.setTypeSort(this.dictionaryService.getMaxTypeSort());
	        dicTypeInfo.setIsLonger(ConsoleHelper.YES);//1:����,0����������������Ϊ����
	        dicTypeInfo.setTypeWay(DicData.AUTO_GENERATE);//1�ֹ�¼�룻2ϵͳ����,��������Ϊϵͳ����
	        model.put("dicTypeInfo", dicTypeInfo);
        }
        return model;
    }
}
