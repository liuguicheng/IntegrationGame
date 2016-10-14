package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.console.entity.Staff;

/**
 *        @hibernate.class
 *         table="fd_finished"
 *
 */
public class FinishedItem implements Serializable {

    /**
     * ����id
     */
    private static final long serialVersionUID = -5403466429251523370L;

    /** identifier field */
    private String finishedId;

    /** nullable persistent field */
    private String forDoId;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String moduleName;

    /** nullable persistent field */
    private String flagId;

    /** nullable persistent field */
    private String url;

    /** nullable persistent field */
    private Staff sender;

    /** nullable persistent field */
    private String senderName;

    /** nullable persistent field */
    private Date sendTime;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private Staff finisher;

    /** nullable persistent field */
    private String finisherName;

    /** nullable persistent field */
    private Date finishTime;
    /**
     * ��չ���ݴ洢����json��ʽд��
     */
    private String jsonData;

    /** default constructor */
    public FinishedItem() {
    }

    /**
     * @return ���� flagId��
     */
    public String getFlagId() {
        return flagId;
    }

    /**
     * @param flagId Ҫ���õ� flagId��
     */
    public void setFlagId(String flagId) {
        this.flagId = flagId;
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
     * @return ���� moduleName��
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName Ҫ���õ� moduleName��
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return ���� sender��
     */
    public Staff getSender() {
        return sender;
    }

    /**
     * @param sender Ҫ���õ� sender��
     */
    public void setSender(Staff sender) {
        this.sender = sender;
    }

    /**
     * @return ���� senderName��
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName Ҫ���õ� senderName��
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return ���� sendTime��
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime Ҫ���õ� sendTime��
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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
     * @return ���� url��
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url Ҫ���õ� url��
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return ���� finishedId��
     */
    public String getFinishedId() {
        return finishedId;
    }

    /**
     * @param finishedId Ҫ���õ� finishedId��
     */
    public void setFinishedId(String finishedId) {
        this.finishedId = finishedId;
    }

    /**
     * @return ���� finisher��
     */
    public Staff getFinisher() {
        return finisher;
    }

    /**
     * @param finisher Ҫ���õ� finisher��
     */
    public void setFinisher(Staff finisher) {
        this.finisher = finisher;
    }

    /**
     * @return ���� finisherName��
     */
    public String getFinisherName() {
        return finisherName;
    }

    /**
     * @param finisherName Ҫ���õ� finisherName��
     */
    public void setFinisherName(String finisherName) {
        this.finisherName = finisherName;
    }

    /**
     * @return ���� finishTime��
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime Ҫ���õ� finishTime��
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("finishedId", getFinishedId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof FinishedItem)) {
            return false;
        }
        FinishedItem castOther = (FinishedItem) other;
        return new EqualsBuilder()
            .append(this.getFinishedId(), castOther.getFinishedId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFinishedId())
            .toHashCode();
    }

    /**
     * @return the jsonData
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * @param jsonData the jsonData to set
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

}
