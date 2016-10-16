package com.systemic.gq.entity;

public class Level {

	/**
	 * 玩家等级
	 */
	     private String id;
	     private String v1_type;//编号
	     private String v1_zdtype;//名称
	     private Integer v1_upgrade_num;//升级 数量
	     private Integer v1_upgrade_tj;//升级审核匹配向上层数
	     private Integer v1_bonus_num;//奖励数量
	     private String v1_bonus_content;//奖励内容
	     
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getV1_type() {
			return v1_type;
		}
		public void setV1_type(String v1_type) {
			this.v1_type = v1_type;
		}
		public String getV1_zdtype() {
			return v1_zdtype;
		}
		public void setV1_zdtype(String v1_zdtype) {
			this.v1_zdtype = v1_zdtype;
		}
		public Integer getV1_upgrade_num() {
			return v1_upgrade_num;
		}
		public void setV1_upgrade_num(Integer v1_upgrade_num) {
			this.v1_upgrade_num = v1_upgrade_num;
		}
		public Integer getV1_upgrade_tj() {
			return v1_upgrade_tj;
		}
		public void setV1_upgrade_tj(Integer v1_upgrade_tj) {
			this.v1_upgrade_tj = v1_upgrade_tj;
		}
		public Integer getV1_bonus_num() {
			return v1_bonus_num;
		}
		public void setV1_bonus_num(Integer v1_bonus_num) {
			this.v1_bonus_num = v1_bonus_num;
		}
		public String getV1_bonus_content() {
			return v1_bonus_content;
		}
		public void setV1_bonus_content(String v1_bonus_content) {
			this.v1_bonus_content = v1_bonus_content;
		}
	     
	     
}
