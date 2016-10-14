package com.console.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.springline.beans.tree.support.layered.ILayerTreeAbility;

/**
 * The <code>Power</code> class ����һ��ϵͳȨ�޶���.
 */
public class Power implements Serializable, ILayerTreeAbility {
    /**
     * ����id
     */
    private static final long serialVersionUID = 3471103268794882275L;

    /**  */
    public static final int LEVEL_LENGTH = 2;

    /**
     * ��Ӧ���ֵ��ʶ
     */
    public static final String TREE_DIC_IDENTIFICATION = "menuTree";

    /**
     * ���밴��ɫ��Ȩ����Ч�Ĳ˵�
     */
    public static final String POWER_TYPE_ROLE = "1";
    /**
     * ���밴������Ȩ����Ч�Ĳ˵�
     */
    public static final String POWER_TYPE_DEPT = "2";
    /**
     * ��������������Ч��Ȩ��
     * ��ɫ�����Ŷ�ӵ�и�Ȩ�޲���Ч
     */
    public static final String POWER_TYPE_ITERATE = "3";
    /**
     * ����Ȩ�ޣ����÷�����Ѿ�����ʹ��
     */
    public static final String POWER_TYPE_COMMON = "4";


    /** Ȩ�ޱ��. */
    private String id;
    /** Ȩ�ޱ���. */
    private String code;
    /** Ȩ������. */
    private String name;
    /** Ȩ�޹��ܵ�url. */
    private String url;
    /** Ȩ��ͼ���url. */
    private String imgUrl;
    /** Ȩ��������ģ������. */
    private String moduleName;
    /** Ȩ�����ͱ�־. */
    private String typeFlag;

    /**
     * Ϊʵ�ֲ˵��������ӵ����ԣ�
     * ��Σ�����layerCodeʱ�Զ�����4λһ��ı��������м���
     */
    private int layer;

//    /** ������Ȩ�޵Ľ�ɫ��. */
//    private Set roles;
    /**������Ȩ�޵ĵ�λ����**/
    private Set departments;
  
    /**
     * ���ʸ�ҳ����Ҫ��������
     */
    private int passwordLevel;
    /**
     * @hibernate.id column = "power_id" generator-class = "identity"
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
     * @hibernate.property column = "power_code" length = "40" not-null = "true"
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
//        String tempStr = code;
//        while (tempStr.charAt(tempStr.length() - 1) == '0') {
//            tempStr = tempStr.substring(0, tempStr.length() - 1);
//        }
        this.layer = code.length() / LEVEL_LENGTH;
//        if (tempStr.length() % LEVEL_LENGTH > 0) {
//            layer++;
//        }
    }

    /**
     * @hibernate.property column = "power_name" length = "30" not-null = "true"
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
     * @hibernate.property column = "url" length = "100"
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @hibernate.property column = "imgurl" length = "100"
     * @return Returns the imgUrl.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl The imgUrl to set.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @hibernate.property column = "module_name" length = "20"
     * @return Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName The moduleName to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @hibernate.property column = "power_type_flag" length = "1" not-null = "true"
     * @return Returns the typeFlag.
     */
    public String getTypeFlag() {
        return typeFlag;
    }

    /**
     * @param typeFlag The typeFlag to set.
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

//    /**
//     * @hibernate.set table = "cg_sys_role_power" cascade = "none"
//     *                inverse = "true" lazy = "true" order-by = "role_id"
//     * @hibernate.collection-key column = "power_id"
//     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Role"
//     *                column = "role_id"
//     * @return Returns the roles.
//     */
//    public Set getRoles() {
//        return this.roles;
//    }

//    /**
//     * @param roles The roles to set.
//     */
//    public void setRoles(Set roles) {
//        this.roles = roles;
//    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Power) {
            if (this.id != null) {
                return this.id.equals(((Power) obj).getId());
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
     * @return Returns the departments.
     */
    public Set getDepartments() {
        return departments;
    }

    /**
     * @param departments The departments to set.
     */
    public void setDepartments(Set departments) {
        this.departments = departments;
    }

    /**
     * @return ��Ӧ����Id���ַ�����Id֮���Զ��ŷָ�
     */
    public String getDepartmentIdString() {
        StringBuffer ids = new StringBuffer();
        for (Iterator it = this.departments.iterator(); it.hasNext();) {
            Department dep = (Department) it.next();
            ids.append(dep.getId()).append(",");
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(ids.length() - 1);
        }
        return ids.toString();
    }

    /**
     * @return ��Ӧ�������Ƶ��ַ�����Id֮���Զ��ŷָ�
     */
    public String getDepartmentNameString() {
        StringBuffer ids = new StringBuffer();
        for (Iterator it = this.departments.iterator(); it.hasNext();) {
            Department dep = (Department) it.next();
            ids.append(dep.getName()).append(",");
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(ids.length() - 1);
        }
        return ids.toString();
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getLayer()
     */
    public int getLayer() {
        return this.layer;
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getNodeKey()
     */
    public String getNodeKey() {
        if (this.id == null) {
            return null;
        }
        return this.id.toString();
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getNodeName()
     */
    public String getNodeName() {
        return this.name;
    }

	/**
	 * @return the passwordLevel
	 */
	public int getPasswordLevel() {
		return passwordLevel;
	}

	/**
	 * @param passwordLevel the passwordLevel to set
	 */
	public void setPasswordLevel(int passwordLevel) {
		this.passwordLevel = passwordLevel;
	}


}
