package com.console.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.springline.beans.tree.support.paternity.IPaternityTreeAbility;

/**
 * The <code>Department</code> class �������Ŷ���.
 */
public class Department implements Serializable, IPaternityTreeAbility {
    /**
     * ����id
     */
    private static final long serialVersionUID = -4360139611589850736L;
    /**
     * ���ֵ��ʶ
     */
    public static final String TREE_DIC_IDENTIFICATION = "dicDepartmentTree";
    /**
     * ���ֵ��ʶ
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicDepartment";

    /** ����״̬������ */
    public static final String NORMAL_STATE = "1";
    /** ����״̬������ */
    public static final String HIDDEN_STATE = "0";

    /** ����Ĭ�ϵ� ����ID �����ڻ�۲ɼ������ ������Դ ά��ģ�飩*/
    public static final String DEFAULT_PARENT_ID = "1";

    /** ����ID���ⲿ��֯����10000. */
    private String id;
    /** ��������. */
    private String name;
    /** ��������. */
    private String distinguishedName;
    /**���ű��� */
    private String code;
    /** ���ż��. */
    private String alias;
    /** �����쵼. */
    private String lead;
    /** ״̬����ֵ��0Ϊ������1����״̬. */
    private String state;
    /** �ؾֱ�־��1Ϊ�ؾ�. */
    private String rank;
    /** ������Ϣ. */
    private String inf;
    /** ������ID. */
    private String parentId;
    /**���ڲ���Id*/
    private String ancestorDep;
    /**˳���*/
    private Integer orderNumber;

    /**���Ŷ�Ӧ��Ȩ�޼��ϣ��޸Ĳ�����Ϣ��ʱ��һͬ���£�����޸Ĳ�����Ϣʱ�������ʼ���ü���**/
    private Set powerSet;

    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isParent(org.springline.beans.tree.support.paternity.IPaternityTreeAbility)
     */
    public boolean isParent(IPaternityTreeAbility obj) {
        Department tmp = (Department) obj;
        return (tmp != null && !equals(tmp) && tmp.getParentId().equals(this.getId()));
    }

    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isRoot()
     */
    public boolean isRoot() {
        return (this.parentId == null || this.parentId.equals(this.id));
    }

    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeKey()
     */
    public String getNodeKey() {
        if (this.id == null) {
            return "";
        }
        return this.id.toString();
    }

    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeName()
     */
    public String getNodeName() {
        return this.name;
    }

    /**
     * @hibernate.property column = "dep_alias" length = "20" not-null = "false"
     * @return Returns the alias.
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * @param alias The alias to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @hibernate.id column = "dep_id" generator-class = "assigned"
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
     * @hibernate.property column = "dep_inf" length = "100" not-null = "false"
     * @return Returns the inf.
     */
    public String getInf() {
        return this.inf;
    }

    /**
     * @param inf The inf to set.
     */
    public void setInf(String inf) {
        this.inf = inf;
    }

    /**
     * @hibernate.property column = "dep_lead" length = "20" not-null = "false"
     * @return Returns the lead.
     */
    public String getLead() {
        return this.lead;
    }

    /**
     * @param lead The lead to set.
     */
    public void setLead(String lead) {
        this.lead = lead;
    }

    /**
     * @hibernate.property column = "dep_name" length = "60" not-null = "true"
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
     * @hibernate.property column = "dep_rank" length = "1" not-null = "true"
     * @return Returns the rank.
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * @param rank The rank to set.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @hibernate.property column = "dep_state" length = "1" not-null = "true"
     * @return Returns the state.
     */
    public String getState() {
        return this.state;
    }

    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @hibernate.property column = "parent_dep" not-null = "true"
     * @return Returns the parentId.
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * @param parentId The parentId to set.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

//    /**
//     * @hibernate.collection-one-to-many class = "com.spower.basesystem.manage.valueobject.Department"
//     * @hibernate.collection-key column = "parent_dep"
//     * @hibernate.set lazy = "true" where = "dep_state = '0'" order-by = "dep_id asc"
//     * @return Returns the subDepartment.
//     */
//    public Set getSubDepartment() {
//        return this.subDepartment;
//    }

//    /**
//     * @param subDepartment The subDepartment to set.
//     */
//    public void setSubDepartment(Set subDepartment) {
//        this.subDepartment = subDepartment;
//    }

//    /**
//     * @hibernate.collection-one-to-many class = "com.spower.basesystem.manage.valueobject.Staff"
//     * @hibernate.collection-key column = "dep_id"
//     * @hibernate.set lazy = "true" where = "valid = '0'" order-by = "staff_id asc"
//     * @return Returns the staffSet.
//     */
//    public Set getStaffSet() {
//        return this.staffSet;
//    }

//    /**
//     * @param staffSet The staffSet to set.
//     */
//    public void setStaffSet(Set staffSet) {
//        this.staffSet = staffSet;
//    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Department) {
            if (this.id != null) {
                return this.id.equals(((Department) obj).getId());
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[��ţ�" + this.id + " ���ƣ�" + this.name + "]";
    }

    /**
     * @return Returns the ancestorDep.
     */
    public String getAncestorDep() {
        return ancestorDep;
    }

    /**
     * @param ancestorDep The ancestorDep to set.
     */
    public void setAncestorDep(String ancestorDep) {
        this.ancestorDep = ancestorDep;
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
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return Returns the powerSet.
     */
    public Set getPowerSet() {
        return powerSet;
    }

    /**
     * @param powerSet The powerSet to set.
     */
    public void setPowerSet(Set powerSet) {
        this.powerSet = powerSet;
    }

    /** �жϵ�ǰ�����Ƿ�ӵ�и�Ȩ��
     * @param powerCode Ȩ�ޱ��
     * @return true or false
     */
    public boolean hasPower(String powerCode) {
        if (powerCode != null && this.powerSet != null) {
            for (Iterator it = this.powerSet.iterator(); it.hasNext();) {
                Power power = (Power) it.next();
                if (powerCode.equals(power.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the distinguishedName
     */
    public String getDistinguishedName() {
        return distinguishedName;
    }

    /**
     * @param distinguishedName the distinguishedName to set
     */
    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }
}
