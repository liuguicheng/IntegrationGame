package com.console.entity;

import java.io.Serializable;

public class Duty implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 6031286214411413990L;

    /**
     * ���ֵ��ʶ
     */
    public static final String DIC_DUTY = "dicDuty";

	/** ְ��id */
	private Integer id;
	/**  */
	private String mainDuty;
	/** ְ������ */
	private String name;
	/** ��ע */
	private String remark;


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the mainDuty
	 */
	public String getMainDuty() {
		return mainDuty;
	}

	/**
	 * @param mainDuty the mainDuty to set
	 */
	public void setMainDuty(String mainDuty) {
		this.mainDuty = mainDuty;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
