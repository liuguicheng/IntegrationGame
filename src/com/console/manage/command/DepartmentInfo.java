
package com.console.manage.command;


public class DepartmentInfo {

    /** ����ID��*/
    private String id;
    /** ��������. */
    private String name;
    /** ����������������. */
    private String code;
    /** ���ż��. */
    private String alias;
    /** �����쵼. */
    private String lead;
    /** �ؾֱ�־��1Ϊ�ؾ�. */
    private String rank = "0";
    /** ������Ϣ. */
    private String inf;
    /** ������ID. */
    private String parentId;
    /** ������Name. */
    private String parentName;
    /**˳���*/
    private Integer orderNumber;

    /**
     * true ��ʶ�ǵ�λ������������IdΪ����ID
     * ���� ����������IdΪ�����ŵ�����Id
     * */
    private Boolean isIndependent;

    /**
     * @return Returns the alias.
     */
    public String getAlias() {
        return alias;
    }
    /**
     * @param alias The alias to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return Returns the inf.
     */
    public String getInf() {
        return inf;
    }
    /**
     * @param inf The inf to set.
     */
    public void setInf(String inf) {
        this.inf = inf;
    }
    /**
     * @return Returns the lead.
     */
    public String getLead() {
        return lead;
    }
    /**
     * @param lead The lead to set.
     */
    public void setLead(String lead) {
        this.lead = lead;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
     * @return Returns the parentId.
     */
    public String getParentId() {
        return parentId;
    }
    /**
     * @param parentId The parentId to set.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * @return Returns the rank.
     */
    public String getRank() {
        return rank;
    }
    /**
     * @param rank The rank to set.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }
    /**
     * @return Returns the parentName.
     */
    public String getParentName() {
        return parentName;
    }
    /**
     * @param parentName The parentName to set.
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    /**
     * @return Returns the orderNumber.
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }
    /**
     * @param orderNumber The orderNumber to set.
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * @return Returns the isIndependent.
     */
    public Boolean getIsIndependent() {
        return isIndependent;
    }
    /**
     * @param isIndependent The isIndependent to set.
     */
    public void setIsIndependent(Boolean isIndependent) {
        this.isIndependent = isIndependent;
    }
}
