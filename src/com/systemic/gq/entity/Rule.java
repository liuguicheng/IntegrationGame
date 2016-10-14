package com.systemic.gq.entity;



/**
 * Rule entity. @author MyEclipse Persistence Tools
 */

public class Rule  implements java.io.Serializable {
     /**
	 * ϵͳ����
	 */
	private static final long serialVersionUID = -1170023726464191595L;
	private String id;
     private Integer counterFee;//������
     private Double dayCap;//�ս���ⶥֵ
     private Integer areaNumber;//�������� ��Ӧ����ֵ�������� 0��10
     private String regionPriority;//�������ȼ�
     private String activationConditionOne;//��Ա��������
     private Integer activationConditionTwo;//
     private String  electronic_Currency_TX;//���ӱ�����
     private String  electronic_Currency_ZH;//���ӱ�ת��
     private String  electronic_Currency_ZZ;//���ӱ�ת��
     private String  gold_Award_TX;//���������
     private String  gold_Award_ZH;//�����ת��
     private String  gold_Award_ZZ;//�����ת��
     
     private String front_switch;//ǰ̨����
     private String front_switch_content;//ǰ̨����˵��
     private String network_diagram_switch;//����ͼ����
     private String withdrawals_time;//����ʱ��  ��һ�����գ���¼1,2,3,4,5,6,7
     private int withdrawals_count;//���ִ���
     private int withdrawals_counter_fee;//����������
     private int withdrawals_money;//����ÿ�����ⶥ
     private int transfer_count;//ת�˴���
     private String transfer_time;//ת��ʱ�� ��һ�����գ���¼1,2,3,4,5,6,7
     private int transfer_counter_fee;//ת��������
     private int transfer_money;//ת���շⶥ


	/** default constructor */
    public Rule() {
    }

    
    /** full constructor */
    public Rule(Integer counterFee, Double dayCap, Integer areaNumber, String regionPriority, String activationConditionOne, Integer activationConditionTwo) {
        this.counterFee = counterFee;
        this.dayCap = dayCap;
        this.areaNumber = areaNumber;
        this.regionPriority = regionPriority;
        this.activationConditionOne = activationConditionOne;
        this.activationConditionTwo = activationConditionTwo;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public Integer getCounterFee() {
        return this.counterFee;
    }
    
    public void setCounterFee(Integer counterFee) {
        this.counterFee = counterFee;
    }

    public Double getDayCap() {
        return this.dayCap;
    }
    
    public void setDayCap(Double dayCap) {
        this.dayCap = dayCap;
    }

    public Integer getAreaNumber() {
        return this.areaNumber;
    }
    
    public void setAreaNumber(Integer areaNumber) {
        this.areaNumber = areaNumber;
    }


    public Integer getActivationConditionTwo() {
        return this.activationConditionTwo;
    }
    
    public void setActivationConditionTwo(Integer activationConditionTwo) {
        this.activationConditionTwo = activationConditionTwo;
    }


	public String getRegionPriority() {
		return regionPriority;
	}


	public void setRegionPriority(String regionPriority) {
		this.regionPriority = regionPriority;
	}


	public String getActivationConditionOne() {
		return activationConditionOne;
	}


	public void setActivationConditionOne(String activationConditionOne) {
		this.activationConditionOne = activationConditionOne;
	}


	public String getElectronic_Currency_TX() {
		return electronic_Currency_TX;
	}


	public void setElectronic_Currency_TX(String electronic_Currency_TX) {
		this.electronic_Currency_TX = electronic_Currency_TX;
	}


	public String getElectronic_Currency_ZH() {
		return electronic_Currency_ZH;
	}


	public void setElectronic_Currency_ZH(String electronic_Currency_ZH) {
		this.electronic_Currency_ZH = electronic_Currency_ZH;
	}


	public String getElectronic_Currency_ZZ() {
		return electronic_Currency_ZZ;
	}


	public void setElectronic_Currency_ZZ(String electronic_Currency_ZZ) {
		this.electronic_Currency_ZZ = electronic_Currency_ZZ;
	}


	public String getGold_Award_TX() {
		return gold_Award_TX;
	}


	public void setGold_Award_TX(String gold_Award_TX) {
		this.gold_Award_TX = gold_Award_TX;
	}


	public String getGold_Award_ZH() {
		return gold_Award_ZH;
	}


	public void setGold_Award_ZH(String gold_Award_ZH) {
		this.gold_Award_ZH = gold_Award_ZH;
	}


	public String getGold_Award_ZZ() {
		return gold_Award_ZZ;
	}


	public void setGold_Award_ZZ(String gold_Award_ZZ) {
		this.gold_Award_ZZ = gold_Award_ZZ;
	}


	/**
	 * @return the front_switch
	 */
	public String getFront_switch() {
		return front_switch;
	}


	/**
	 * @param front_switch the front_switch to set
	 */
	public void setFront_switch(String front_switch) {
		this.front_switch = front_switch;
	}


	/**
	 * @return the front_switch_content
	 */
	public String getFront_switch_content() {
		return front_switch_content;
	}


	/**
	 * @param front_switch_content the front_switch_content to set
	 */
	public void setFront_switch_content(String front_switch_content) {
		this.front_switch_content = front_switch_content;
	}


	/**
	 * @return the network_diagram_switch
	 */
	public String getNetwork_diagram_switch() {
		return network_diagram_switch;
	}


	/**
	 * @param network_diagram_switch the network_diagram_switch to set
	 */
	public void setNetwork_diagram_switch(String network_diagram_switch) {
		this.network_diagram_switch = network_diagram_switch;
	}


	/**
	 * @return the withdrawals_time
	 */
	public String getWithdrawals_time() {
		return withdrawals_time;
	}


	/**
	 * @param withdrawals_time the withdrawals_time to set
	 */
	public void setWithdrawals_time(String withdrawals_time) {
		this.withdrawals_time = withdrawals_time;
	}


	/**
	 * @return the withdrawals_count
	 */
	public int getWithdrawals_count() {
		return withdrawals_count;
	}


	/**
	 * @param withdrawals_count the withdrawals_count to set
	 */
	public void setWithdrawals_count(int withdrawals_count) {
		this.withdrawals_count = withdrawals_count;
	}


	/**
	 * @return the withdrawals_counter_fee
	 */
	public int getWithdrawals_counter_fee() {
		return withdrawals_counter_fee;
	}


	/**
	 * @param withdrawals_counter_fee the withdrawals_counter_fee to set
	 */
	public void setWithdrawals_counter_fee(int withdrawals_counter_fee) {
		this.withdrawals_counter_fee = withdrawals_counter_fee;
	}


	/**
	 * @return the withdrawals_money
	 */
	public int getWithdrawals_money() {
		return withdrawals_money;
	}


	/**
	 * @param withdrawals_money the withdrawals_money to set
	 */
	public void setWithdrawals_money(int withdrawals_money) {
		this.withdrawals_money = withdrawals_money;
	}


	/**
	 * @return the transfer_count
	 */
	public int getTransfer_count() {
		return transfer_count;
	}


	/**
	 * @param transfer_count the transfer_count to set
	 */
	public void setTransfer_count(int transfer_count) {
		this.transfer_count = transfer_count;
	}


	/**
	 * @return the transfer_time
	 */
	public String getTransfer_time() {
		return transfer_time;
	}


	/**
	 * @param transfer_time the transfer_time to set
	 */
	public void setTransfer_time(String transfer_time) {
		this.transfer_time = transfer_time;
	}


	/**
	 * @return the transfer_counter_fee
	 */
	public int getTransfer_counter_fee() {
		return transfer_counter_fee;
	}


	/**
	 * @param transfer_counter_fee the transfer_counter_fee to set
	 */
	public void setTransfer_counter_fee(int transfer_counter_fee) {
		this.transfer_counter_fee = transfer_counter_fee;
	}


	/**
	 * @return the transfer_money
	 */
	public int getTransfer_money() {
		return transfer_money;
	}


	/**
	 * @param transfer_money the transfer_money to set
	 */
	public void setTransfer_money(int transfer_money) {
		this.transfer_money = transfer_money;
	}


	
   








}