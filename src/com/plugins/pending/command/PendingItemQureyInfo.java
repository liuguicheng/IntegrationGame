
package com.plugins.pending.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;


public class PendingItemQureyInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = -8666291908166523534L;

    /** not nullable persistent field */
    private String recipientId;

    /** not nullable persistent field */
    private String forDoId;

    /** Ӧ��ģ���� */
    private String moduleName;

    /** Ӧ��ģ������ */
    private String moduleType;

    /**  */
    private String moduleNames;

    /** nullable persistent field */
    private Date readTime;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String title;

    private Date sendTimeBegin;
    private Date sendTimeEnd;
    /**
     * @return ���� title��
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Ҫ���õ� title��
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
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
     * @return Returns the moduleType.
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType The moduleType to set.
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return Returns the moduleNames.
     */
    public String getModuleNames() {
        return moduleNames;
    }

    /**
     * @param moduleNames The moduleNames to set.
     */
    public void setModuleNames(String moduleNames) {
        this.moduleNames = moduleNames;
    }

    /**
     * @return ���� forDoId��
     */
    public String getForDoId() {
        return forDoId;
    }

    /**
     * @param forDoId Ҫ���õ� forDoId��
     */
    public void setForDoId(String forDoId) {
        this.forDoId = forDoId;
    }

    /**
     * @return ���� readTime��
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * @param readTime Ҫ���õ� readTime��
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * @return ���� recipientId��
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * @param recipientId Ҫ���õ� recipientId��
     */
    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    /**
     * @return ���� status��
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status Ҫ���õ� status��
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the sendTimeBegin
     */
    public Date getSendTimeBegin() {
        return sendTimeBegin;
    }

    /**
     * @param sendTimeBegin the sendTimeBegin to set
     */
    public void setSendTimeBegin(Date sendTimeBegin) {
        this.sendTimeBegin = sendTimeBegin;
    }

    /**
     * @return the sendTimeEnd
     */
    public Date getSendTimeEnd() {
        return sendTimeEnd;
    }

    /**
     * @param sendTimeEnd the sendTimeEnd to set
     */
    public void setSendTimeEnd(Date sendTimeEnd) {
        this.sendTimeEnd = sendTimeEnd;
    }



}
