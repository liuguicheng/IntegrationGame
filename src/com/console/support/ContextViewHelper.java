
package com.console.support;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springline.web.view.SpringlineViewHelper;


public class ContextViewHelper extends SpringlineViewHelper {

//    public static final String CONTEXT_FLAG_TEMPLATE = "skinPath";
//    public static final String CONTEXT_FLAG_APPNAME = "appName";

    private Properties appContext;

//    private String appName = "";

    /**
     * @see org.springline.web.view.SpringlineViewHelper#exposeHelpers(java.util.Map, javax.servlet.http.HttpServletRequest)
     */
    public void exposeHelpers(Map model, HttpServletRequest request) {
        super.exposeHelpers(model, request);
//        model.put("currentTemplate", "../template/style.css"); //���ݾ�ҳ�������������ҳ��ģ�����ȥ��
//        model.put(CONTEXT_FLAG_TEMPLATE, "skins"); //ģ��·��
//        model.put("currentTemplate", "../skins/css/mainpage.css"); //���ݾ�ҳ�������������ҳ��ģ�����ȥ��
//        model.put(CONTEXT_FLAG_APPNAME, appName); //Ӧ�ó�������
        if (appContext != null) {
            model.put("appContext", appContext);
        }
    }
//
//    /**
//     * @param appName the appName to set
//     */
//    public void setAppName(String appName) {
//        this.appName = appName;
//    }



    /**
     * @param contextModel the contextModel to set
     */
    public void setAppContext(Properties contextModel) {
        this.appContext = contextModel;
    }


}
