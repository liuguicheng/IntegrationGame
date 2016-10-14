package com.plugins.dictionary.entity;

/**
 * DicType entity. @author MyEclipse Persistence Tools
 */

public class DicType implements java.io.Serializable {

	// Fields

	/**�ֵ�����ID*/
	private String typeId;

	/**�ֵ����ͱ�ʶ*/
	private String typeCode;

	/**�ֵ���������*/
	private String typeName;

	/**�ֵ���������*/
	private Integer typeSort;

	/**��ʶ���ɷ�ʽ*/
	private String typeWay;

	/**�ֵ䳤��*/
	private Integer dataLength;

	/**�Ƿ񶨳�*/
	private String isLonger;
	// Constructors

	/** default constructor */
	public DicType() {
	}

	/** minimal constructor */
	public DicType(String typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public DicType(String typeId, String typeCode, String typeName,
			Integer typeSort, String typeWay, Integer dataLength,
			String isLonger) {
		this.typeId = typeId;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.typeSort = typeSort;
		this.typeWay = typeWay;
		this.dataLength = dataLength;
		this.isLonger = isLonger;
	}

	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typeSort
	 */
	public Integer getTypeSort() {
		return typeSort;
	}

	/**
	 * @param typeSort the typeSort to set
	 */
	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}

	/**
	 * @return the typeWay
	 */
	public String getTypeWay() {
		return typeWay;
	}

	/**
	 * @param typeWay the typeWay to set
	 */
	public void setTypeWay(String typeWay) {
		this.typeWay = typeWay;
	}

	/**
	 * @return the dataLength
	 */
	public Integer getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the isLonger
	 */
	public String getIsLonger() {
		return isLonger;
	}

	/**
	 * @param isLonger the isLonger to set
	 */
	public void setIsLonger(String isLonger) {
		this.isLonger = isLonger;
	}

}