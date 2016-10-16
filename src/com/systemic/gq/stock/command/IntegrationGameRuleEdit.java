package com.systemic.gq.stock.command;

import org.springline.web.mvc.SpringlineCommand;

public class IntegrationGameRuleEdit extends SpringlineCommand {

	/**
	 * 系统规则
	 * 积分游戏
	 */
	 private static final long serialVersionUID = -1170023726464191595L;
     private String id;
     private Integer areaNumber;//区域数量 对应的数值从左至右 0至10
     private String front_switch;//前台开关
     private String front_switch_content;//前台开关说明
     private String network_diagram_switch;//网络图开关
     private Integer network_diagram_number;//玩家网络图显示层数
     
     private Integer registerAuditTime;//注册审核时间
     private Integer registerLoginNameNum;//玩家编号生成位数
     private Integer upgradeAuditTime;//升级审核时间
	
     
     
     public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAreaNumber() {
		return areaNumber;
	}
	public void setAreaNumber(Integer areaNumber) {
		this.areaNumber = areaNumber;
	}
	public String getFront_switch() {
		return front_switch;
	}
	public void setFront_switch(String front_switch) {
		this.front_switch = front_switch;
	}
	public String getFront_switch_content() {
		return front_switch_content;
	}
	public void setFront_switch_content(String front_switch_content) {
		this.front_switch_content = front_switch_content;
	}
	public String getNetwork_diagram_switch() {
		return network_diagram_switch;
	}
	public void setNetwork_diagram_switch(String network_diagram_switch) {
		this.network_diagram_switch = network_diagram_switch;
	}
	public Integer getNetwork_diagram_number() {
		return network_diagram_number;
	}
	public void setNetwork_diagram_number(Integer network_diagram_number) {
		this.network_diagram_number = network_diagram_number;
	}
	public Integer getRegisterAuditTime() {
		return registerAuditTime;
	}
	public void setRegisterAuditTime(Integer registerAuditTime) {
		this.registerAuditTime = registerAuditTime;
	}
	public Integer getRegisterLoginNameNum() {
		return registerLoginNameNum;
	}
	public void setRegisterLoginNameNum(Integer registerLoginNameNum) {
		this.registerLoginNameNum = registerLoginNameNum;
	}
	public Integer getUpgradeAuditTime() {
		return upgradeAuditTime;
	}
	public void setUpgradeAuditTime(Integer upgradeAuditTime) {
		this.upgradeAuditTime = upgradeAuditTime;
	}
}
