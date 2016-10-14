package com.plugins.dictionary.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DicDataController extends SpringlineMultiActionController{

    /**�ֵ�Serviceע��*/
    private IDictionaryService dictionaryService;

    /**�ֵ������б���ͼ*/
    private String dicDataListView;

    /**�ֵ���Ƕ�����б���ͼ*/
    private String dicDataInnerListView;



    /**
     * @param dictionaryService the dictionaryService to set
     */
    public void setDictionaryService(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * @param dicDataListView the dicDataListView to set
     */
    public void setDicDataListView(String dicDataListView) {
        this.dicDataListView = dicDataListView;
    }

    /**
     * @param dicDataInnerListView the dicDataInnerListView to set
     */
    public void setDicDataInnerListView(String dicDataInnerListView) {
        this.dicDataInnerListView = dicDataInnerListView;
    }

    /**
     * �г����е��ֵ�������Ϣ
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ModelAndView dicDataList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashedMap();
        //�������е��ֵ�����
        model.put("dicTypeList", this.dictionaryService.selectAllDicType());
        return new ModelAndView(this.dicDataListView,model);
    }

    /**
     *�����ֵ�����ID������ʾ�����е��ֵ����ݼ�¼
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ModelAndView dicDataInnerList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashedMap();
        String typeCode = request.getParameter("typeCode");
        String message = request.getParameter("message");
        model.put("message", message);
        if (typeCode != null && typeCode.trim().length() != 0) {
        	DicType dicType = this.dictionaryService.getDicTypeByCode(typeCode);
            List dicDataList = this.dictionaryService.selectAllDicDataByCode(typeCode);
            model.put("dicDataList", dicDataList);
            model.put("dicType", dicType);
        }
        return new ModelAndView(this.dicDataInnerListView, model);
    }

    /**
     * �߼�����ɾ���ֵ�������Ϣ
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView dicDataDelete(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	Map model = new HashedMap();
    	String[] checkValue = request.getParameterValues("dataId");
    	this.dictionaryService.deleteDicData(checkValue);
    	return dicDataInnerList(request, response);
    }

    /**
     * �����ֵ��������Ϣ
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dicDataUse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	Map model = new HashedMap();
        String[] checkValue = request.getParameterValues("dataId");
        this.dictionaryService.updateDicData(checkValue);
        return dicDataInnerList(request, response);
    }


}
