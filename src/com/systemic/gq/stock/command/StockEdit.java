package com.systemic.gq.stock.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class StockEdit extends SpringlineCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7924203400716879114L;

	private String id;
    private String gradeNum;//�ȼ�
    private Double money;//�����Ǯ
    private int stockMoney;//��Ȩ�ݶ�
    private Double cap;//Ͷ�ʷⶥ
    private Integer istrue;//�Ƿ񿪷�
    private Date createTime;//����ʱ��
    
    private String bonusContentID;//��Ȩ����id�������

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}


	public int getStockMoney() {
		return stockMoney;
	}

	public void setStockMoney(int stockMoney) {
		this.stockMoney = stockMoney;
	}

	public Double getCap() {
		return cap;
	}

	public void setCap(Double cap) {
		this.cap = cap;
	}

	public Integer getIstrue() {
		return istrue;
	}

	public void setIstrue(Integer istrue) {
		this.istrue = istrue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getBonusContentID() {
		return bonusContentID;
	}

	public void setBonusContentID(String bonusContentID) {
		this.bonusContentID = bonusContentID;
	}

    
    
}
