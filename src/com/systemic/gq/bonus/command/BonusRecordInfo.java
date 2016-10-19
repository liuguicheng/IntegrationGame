package com.systemic.gq.bonus.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class BonusRecordInfo extends PaginationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119115641994929801L;
	private String id;
	private double money;//���
	private String userid;//����û�
	private String username;//����û�����
	private Date create_time;//���ʱ��
	private Date end_time;//����ʱ��
	private Integer isSend;//�Ƿ񷢷� 0δ���� 1�ѷ���
	private Date send_time;//����ʱ��
	private Integer num;//����
	private String content;//��������
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
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	private String bonustype;//��������
	private Integer proportion;//����
	private String remark;//��ע
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
