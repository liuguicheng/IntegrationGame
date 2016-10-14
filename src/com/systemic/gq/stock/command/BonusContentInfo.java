package com.systemic.gq.stock.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class BonusContentInfo extends PaginationInfo {

	private static final long serialVersionUID = -1246895674723217960L;

	private String id;
    private Integer begin;//������������
    private Integer end;//������������
    private Integer proportion;//����
    private Date releaseTime;//����ʱ��
    private String bz;//��ע
    private String bonusType;//�������ͣ�����ֵ��  ��


   public String getBonusType() {
		return bonusType;
	}


	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}


	public String getId() {
       return this.id;
   }
   
   public void setId(String id) {
       this.id = id;
   }

   public Integer getBegin() {
       return this.begin;
   }
   
   public void setBegin(Integer begin) {
       this.begin = begin;
   }

   public Integer getEnd() {
       return this.end;
   }
   
   public void setEnd(Integer end) {
       this.end = end;
   }

   public Integer getProportion() {
       return this.proportion;
   }
   
   public void setProportion(Integer proportion) {
       this.proportion = proportion;
   }

   public Date getReleaseTime() {
       return this.releaseTime;
   }
   
   public void setReleaseTime(Date releaseTime) {
       this.releaseTime = releaseTime;
   }

   public String getBz() {
       return this.bz;
   }
   
   public void setBz(String bz) {
       this.bz = bz;
   }
}
