package com.systemic.gq.entity;

import java.util.Date;

public class DialOutRatio implements java.io.Serializable {
	private static final long serialVersionUID = 6787605101375735185L;
	/**
	 * 拨比记录表
	 */
	private String id;
	private Date bor_date;//结算日期
	private double grossed;//总收入
	private double totalExpenditure;//总支出
	private double totalProfit;//总盈利
	private int bor_ratio;//拨比率
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the bor_date
	 */
	public Date getBor_date() {
		return bor_date;
	}
	/**
	 * @param bor_date the bor_date to set
	 */
	public void setBor_date(Date bor_date) {
		this.bor_date = bor_date;
	}
	/**
	 * @return the grossed
	 */
	public double getGrossed() {
		return grossed;
	}
	/**
	 * @param grossed the grossed to set
	 */
	public void setGrossed(double grossed) {
		this.grossed = grossed;
	}
	/**
	 * @return the totalExpenditure
	 */
	public double getTotalExpenditure() {
		return totalExpenditure;
	}
	/**
	 * @param totalExpenditure the totalExpenditure to set
	 */
	public void setTotalExpenditure(double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	/**
	 * @return the totalProfit
	 */
	public double getTotalProfit() {
		return totalProfit;
	}
	/**
	 * @param totalProfit the totalProfit to set
	 */
	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}
	/**
	 * @return the bor_ratio
	 */
	public int getBor_ratio() {
		return bor_ratio;
	}
	/**
	 * @param bor_ratio the bor_ratio to set
	 */
	public void setBor_ratio(int bor_ratio) {
		this.bor_ratio = bor_ratio;
	}
	public DialOutRatio(String id, Date bor_date, double grossed, double totalExpenditure, double totalProfit,
			int bor_ratio) {
		super();
		this.id = id;
		this.bor_date = bor_date;
		this.grossed = grossed;
		this.totalExpenditure = totalExpenditure;
		this.totalProfit = totalProfit;
		this.bor_ratio = bor_ratio;
	}
	public DialOutRatio() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DialOutRatio [id=" + id + ", bor_date=" + bor_date + ", grossed=" + grossed + ", totalExpenditure="
				+ totalExpenditure + ", totalProfit=" + totalProfit + ", bor_ratio=" + bor_ratio + "]";
	}

}
