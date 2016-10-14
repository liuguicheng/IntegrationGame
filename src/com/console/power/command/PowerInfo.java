package com.console.power.command;

import java.util.LinkedHashMap;
import java.util.Map;


public class PowerInfo {
    /** Ȩ�ޱ��. */
    private String id;
    /** Ȩ������. */
    private String name;


    /** Ȩ�޹��ܵ�url. */
    private String url;
    /** Ȩ��ͼ���url. */
    private String imgUrl;
    /** Ȩ��������ģ������. */
    private String moduleName=DIC_MODULE_NAME_WEB;
    /** Ȩ�ޱ���. */
    private String code;
    /** Ȩ�����ͱ�ʶ. */
    private String typeFlag;
    private int passwordLevel;
    
    public static final String DIC_MODULE_NAME_WEB = "01";
    
    public static final String DIC_MODULE_NAME_PHONE = "02";

    /**
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return Returns the imgUrl.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl The imgUrl to set.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName The moduleName to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return Returns the orderNumber.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param orderNumber The orderNumber to set.
     */
    public void setCode(String orderNumber) {
        this.code = orderNumber;
    }
    /**
     * @return Returns the powerTypeFlag.
     */
    public String getTypeFlag() {
        return typeFlag;
    }
    /**
     * @param powerTypeFlag The powerTypeFlag to set.
     */
    public void setTypeFlag(String powerTypeFlag) {
        this.typeFlag = powerTypeFlag;
    }

    /**
     * @return ��ʼ��Ȩ������
     */
    public Map getTypeFlagMap() {
        Map map = new LinkedHashMap();
        map.put("1", "��ɫ��Ȩ�˵�");
        map.put("2", "������Ȩ�˵�");
        map.put("3", "�����Ȩ�˵�");
        map.put("4", "����Ȩ�޲˵�");
        return map;
    }

	/**
	 * @return the passwordLevel
	 */
	public int getPasswordLevel() {
		return passwordLevel;
	}

	/**
	 * @param passwordLevel the passwordLevel to set
	 */
	public void setPasswordLevel(int passwordLevel) {
		this.passwordLevel = passwordLevel;
	}
    
}
