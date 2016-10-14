package com.systemic.gq.entity;

import java.util.Date;


/**
 * Transformation entity. @author MyEclipse Persistence Tools
 */

public class Transformation  implements java.io.Serializable {


    // Fields    

     /**
	 * ת������
	 */
	private static final long serialVersionUID = -5660921618899385461L;
	private String TId;//id
     private String TMemberstaffid;//���
     private String TMemeberusername;//����
     private Date TTime;//ת��ʱ��
     private String TType;//ת����ʽ
     private Double TBeginA;//ת��ǰa���
     private Double TEndA;//ת����a���
     private Double TBeginB;//ת��bǰ���
     private Double TEndB;//ת����b���
     private Double TMoney;//���


    // Constructors

    /** default constructor */
    public Transformation() {
    }

    
    /** full constructor */
    public Transformation(String TMemberstaffid, String TMemeberusername, Date TTime, String TType, Double TBeginA, Double TEndA, Double TBeginB, Double TEndB, Double TMoney) {
        this.TMemberstaffid = TMemberstaffid;
        this.TMemeberusername = TMemeberusername;
        this.TTime = TTime;
        this.TType = TType;
        this.TBeginA = TBeginA;
        this.TEndA = TEndA;
        this.TBeginB = TBeginB;
        this.TEndB = TEndB;
        this.TMoney = TMoney;
    }

   
    // Property accessors

    public String getTId() {
        return this.TId;
    }
    
    public void setTId(String TId) {
        this.TId = TId;
    }

    public String getTMemberstaffid() {
        return this.TMemberstaffid;
    }
    
    public void setTMemberstaffid(String TMemberstaffid) {
        this.TMemberstaffid = TMemberstaffid;
    }

    public String getTMemeberusername() {
        return this.TMemeberusername;
    }
    
    public void setTMemeberusername(String TMemeberusername) {
        this.TMemeberusername = TMemeberusername;
    }

    public Date getTTime() {
        return this.TTime;
    }
    
    public void setTTime(Date TTime) {
        this.TTime = TTime;
    }

    public String getTType() {
        return this.TType;
    }
    
    public void setTType(String TType) {
        this.TType = TType;
    }

    public Double getTBeginA() {
        return this.TBeginA;
    }
    
    public void setTBeginA(Double TBeginA) {
        this.TBeginA = TBeginA;
    }

    public Double getTEndA() {
        return this.TEndA;
    }
    
    public void setTEndA(Double TEndA) {
        this.TEndA = TEndA;
    }

    public Double getTBeginB() {
        return this.TBeginB;
    }
    
    public void setTBeginB(Double TBeginB) {
        this.TBeginB = TBeginB;
    }

    public Double getTEndB() {
        return this.TEndB;
    }
    
    public void setTEndB(Double TEndB) {
        this.TEndB = TEndB;
    }

    public Double getTMoney() {
        return this.TMoney;
    }
    
    public void setTMoney(Double TMoney) {
        this.TMoney = TMoney;
    }
   








}