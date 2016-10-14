package com.systemic.gq.stock.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class BonusContentEdit extends SpringlineCommand {

	private static final long serialVersionUID = 4419613071506190488L;

	private String id;
    private Integer begin;//������������
    private Integer end;//������������
    private Integer proportion;//����
    private Date releaseTime;//����ʱ��
    private String bz;//��ע
    private String bonusType;//�������ͣ�����ֵ��  ��
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getProportion() {
		return proportion;
	}
	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}
    
    
}
