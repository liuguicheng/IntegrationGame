package com.plugins.dirtree.command;

import java.sql.Timestamp;

import org.springline.web.mvc.SpringlineCommand;

import com.plugins.dirtree.entity.DirType;

public class DirTypeEditInfo extends SpringlineCommand {
    /**
     *
     */
    private static final long serialVersionUID = -5493009052998010267L;
    // Fields

    private String dirTypeId;
    /**
     * ���ͱ���
     */
    private String dirTypeCode;
    /**
     * Ŀ¼��������
     */
    private String dirTypeName;
    /**
     * �����
     */
    private Integer sortOrder;
    /**
     * ����ʱ��
     */
    private Timestamp createTime;
    /**
     * �Ƿ���Ч
     */
    private String isValid=DirType.NORMAL_STATE;

    // Constructors

    /** default constructor */
    public DirTypeEditInfo() {
        InitDatetime();
    }

    /** minimal constructor */
    public DirTypeEditInfo(String dirTypeId) {
        this.dirTypeId = dirTypeId;
    }

    /** full constructor */
    public DirTypeEditInfo(String dirTypeId, String dirTypeCode, String dirTypeName,
            Integer sortOrder, Timestamp createTime, String isValid) {
        this.dirTypeId = dirTypeId;
        this.dirTypeCode = dirTypeCode;
        this.dirTypeName = dirTypeName;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.isValid = isValid;
    }

    /**
     * ��ʼ��ʱ��
     */
    private void InitDatetime(){
        createTime=java.sql.Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
    }

    // Property accessors

    public String getDirTypeId() {
        return this.dirTypeId;
    }

    public void setDirTypeId(String dirTypeId) {
        this.dirTypeId = dirTypeId;
    }

    public String getDirTypeCode() {
        return this.dirTypeCode;
    }

    public void setDirTypeCode(String dirTypeCode) {
        this.dirTypeCode = dirTypeCode;
    }

    public String getDirTypeName() {
        return this.dirTypeName;
    }

    public void setDirTypeName(String dirTypeName) {
        this.dirTypeName = dirTypeName;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
