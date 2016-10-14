package com.plugins.dictionary.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.plugins.dictionary.command.DicDataEditInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DicDataEditController extends SpringlineSimpleFormController{

    /**�ֵ�DictionaryService�ӿ�*/
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
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
     */
     @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        DicDataEditInfo dicDataInfo = (DicDataEditInfo) command;
        Map model = new HashMap();
        model.put("typeCode", dicDataInfo.getTypeCode());
        //�����ֵ�����
        try {
        	this.dictionaryService.saveDicData(dicDataInfo);
        	model.put("message", "����ɹ���");
        } catch (Exception ex) {
			model.put("message", "����ʧ�ܣ�" + ex.getMessage());
		}
        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

        /**
         * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
         */
        @SuppressWarnings("unchecked")
        protected Map referenceData(HttpServletRequest request) throws Exception {
            String dataId = request.getParameter("dataId");
            String typeCode = request.getParameter("typeCode");
            Map model = new HashMap();
            DicDataEditInfo dicDataInfo = new DicDataEditInfo();
            DicType dicType = new DicType();
            //�ж�ID�Ƿ�Ϊ�գ���Ϊ�յ����������󶨵�ҳ����
            if (dataId != null && dataId.trim().length() > 0) {
            	DicData dicData = this.dictionaryService.getDicDataById(dataId);
                model.put("dicDataInfo", dicData);
                dicType = this.dictionaryService.getDicTypeByCode(dicData.getTypeCode());
            } else {
            	if (typeCode != null && typeCode.trim().length() != 0) {
                	dicType = this.dictionaryService.getDicTypeByCode(typeCode);
                    if (dicType.getTypeWay().equals(DicData.AUTO_GENERATE)) {//�Զ������ķ�ʽ
                    	//�ѵ�ǰ�����������а󶨵�������
                    	dicDataInfo.setDataCode(this.dictionaryService.getMaxDataCode(typeCode, dicType.getIsLonger(), dicType.getDataLength()));
                    }
                }
                dicDataInfo.setIsValid(ConsoleHelper.YES);//����ʹ��
                dicDataInfo.setDataSort(this.dictionaryService.getMaxDataSort(typeCode));
                model.put("dicDataInfo", dicDataInfo);
            }

            model.put("dicType", dicType);
            return model;
        }
}
