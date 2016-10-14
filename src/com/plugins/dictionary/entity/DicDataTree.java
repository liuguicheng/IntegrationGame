package com.plugins.dictionary.entity;

import java.io.Serializable;

import org.springline.beans.tree.support.layered.ILayerTreeAbility;

public class DicDataTree implements Serializable,ILayerTreeAbility {

	/**
     * ����ʶ
     */
    public static final String TREE_DIC_QUALIFYTYPE = "qualifyTree";

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

	/**
     * Ϊʵ�ֲ˵��������ӵ����ԣ�
     *
     */
    private int layer;

    /**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * @return the dataName
	 */
	public String getDataName() {
		return dataName;
	}

	/**
	 * @param dataName the dataName to set
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
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
		//�趨����ǰ���� ����һ��Ҫ��ȷ����
		if(dataCode.length() == 5){
			//������λΪ��000���ģ��ǵ�һ��
			if(dataCode.substring(2).equals("000")){
				this.layer = 1;
			}else{
				//������λΪ��00���ģ��ǵڶ����������Ϊ������
				if(dataCode.substring(3).equals("00")){
					this.layer = 2;
				}else{
					this.layer = 3;
				}
			}
		}
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
     * @return the layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * @param layer the layer to set
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

	public String getNodeKey() {
		if (this.typeCode == null) {
            return null;
        }
		return this.typeCode.toString();
	}

	public String getNodeName() {

		return this.dataName;
	}

}
