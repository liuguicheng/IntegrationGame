package com.console.role.command;

import org.springline.web.mvc.SpringlineCommand;



public class RoleInfo extends SpringlineCommand{
    /**
     *
     */
    private static final long serialVersionUID = 40567076086058256L;
    /** ��ɫ���. */
    private String id;
    /** ��ɫ����. */
    private String name;
    /** ��ɫ˵��. */
    private String remark;
    /** ˳��. */
    private Integer sortOrder;
    /**
     * @return the sortOrder
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder the sortOrder to set
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return the homeUrl
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * @param homeUrl the homeUrl to set
     */
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    /** ��ҳ. */
    private String homeUrl;
    /**
     * ��ɫӵ�еĹ��ܲ˵�
     */
    private String[] powerId;

    /**
     * ��ɫӵ�е�ҳ������˵�
     */
    private String[] operateId;

    /**
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the powerId
     */
    public String[] getPowerId() {
        return powerId;
    }

    /**
     * @param powerId the powerId to set
     */
    public void setPowerId(String[] powerId) {
        this.powerId = powerId;
    }

	/**
	 * @return the operateId
	 */
	public String[] getOperateId() {
		return operateId;
	}

	/**
	 * @param operateId the operateId to set
	 */
	public void setOperateId(String[] operateId) {
		this.operateId = operateId;
	}

}
