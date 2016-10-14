package com.plugins.dictionary.entity;

/**
 * DicData entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DicData implements java.io.Serializable{

	//�����ֹ�¼�뷽ʽ
	public static final String NOT_AUTO_GENERATE = "1";
	//�����Զ�������ʽ
	public static final String AUTO_GENERATE = "2";
    // Fields

    /**�ֵ�����ID*/
    private String dataId;

    /**�ֵ���������*/
    private String dataName;

    /**�ֵ����ݱ���*/
    private String dataCode;

    /**�ֵ�����״̬1��ʾ������0��ʾ������*/
    private String isValid;

    /**�ֵ���������*/
    private Integer dataSort;

    /**���ͱ���*/
	private String typeCode;

	/**˵��*/
	private String remark;

    // Constructors

    /** default constructor */
    public DicData() {
    }

    /** minimal constructor */
    public DicData(String dataId, String dataName, String typeCode,
            String isValid, String dataCode) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.typeCode = typeCode;
        this.isValid = isValid;
        this.dataCode = dataCode;
    }

    /** full constructor */
    public DicData(String dataId, String dataName, String typeCode,
            String isValid, Integer dataSort, String dataCode) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.typeCode = typeCode;
        this.isValid = isValid;
        this.dataSort = dataSort;
        this.dataCode = dataCode;
    }

    // Property accessors

    /**
     * ����dataId
     */
    public String getDataId() {
        return this.dataId;
    }

    /**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
     *
     * @param dataId ����dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    /**
     *
     * @return ����dataName
     */
    public String getDataName() {
        return this.dataName;
    }

    /**
     *
     * @param dataName ����dataName
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }


    /**
	 * @return the isValid
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
     * @return the dataSort
     */
    public Integer getDataSort() {
        return dataSort;
    }

    /**
     * @param dataSort the dataSort to set
     */
    public void setDataSort(Integer dataSort) {
        this.dataSort = dataSort;
    }

    /**
     * @return the dataCode
     */
    public String getDataCode() {
        return dataCode;
    }

    /**
     * @param dataCode the dataCode to set
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}