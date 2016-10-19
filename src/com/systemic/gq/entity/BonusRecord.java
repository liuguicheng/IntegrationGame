package com.systemic.gq.entity;

import java.util.Date;

public class BonusRecord  implements java.io.Serializable {

	/**
	 * �����¼
	 */
	private static final long serialVersionUID = 7368658302919645387L;
	
	private String id;
	private double money;//���
	private String userid;//����û�id
	private String username;//����û�����
	private Date create_time;//���ʱ��
	private String bonustype;//��������
	private Integer proportion;//����
	private Integer isSend;//�Ƿ񷢷� 0δ���� 1�ѷ���
	private Date send_time;//����ʱ��
	private String remark;//��ע
	private Integer num;//����
	private String content;//��������
	public BonusRecord() {
		// TODO Auto-generated constructor stub
	}
	
	public BonusRecord(String id,double money,String userid,String username,
			Date create_tiem,String bonustype,Integer proportion,String remark) {
		this.id=id;
		this.money=money;
		this.userid=userid;
		this.username=username;
		this.create_time=create_tiem;
		this.bonustype=bonustype;
		this.proportion=proportion;
		this.remark=remark;
	}
	public BonusRecord(double money,String userid,String username,
			Date create_tiem,String bonustype,Integer proportion,String remark) {
		this.money=money;
		this.userid=userid;
		this.username=username;
		this.create_time=create_tiem;
		this.bonustype=bonustype;
		this.proportion=proportion;
		this.remark=remark;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getBonustype() {
		return bonustype;
	}
	public void setBonustype(String bonustype) {
		this.bonustype = bonustype;
	}
	public Integer getProportion() {
		return proportion;
	}
	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the isSend
	 */
	public Integer getIsSend() {
		return isSend;
	}

	/**
	 * @param isSend the isSend to set
	 */
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the send_time
	 */
	public Date getSend_time() {
		return send_time;
	}

	/**
	 * @param send_time the send_time to set
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
