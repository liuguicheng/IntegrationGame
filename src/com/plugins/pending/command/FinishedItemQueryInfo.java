
package com.plugins.pending.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;


public class FinishedItemQueryInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = 5037594195766188133L;

     /** identifier field */
    private String recipientBakId;

    /** not nullable persistent field */
    private String finishedId;

    /** not nullable persistent field */
    private String recipientId;

    /** not nullable persistent field */
    private String forDoId;

    /** nullable persistent field */
    private Date readTime;

    /** nullable persistent field */
    private String status;

    /**
     *
     */
    private Date beginTime;

    /**
     *
     */
    private Date endTime;

    /**
     *
     */
    private String sender;

    /**
     *
     */
    private String title;


    /**
     *
     */
    private com.plugins.pending.entity.FinishedItem finishedItem;

   // private com.spower.basesystem.pending.valueobject.PendingItem forDoc ;

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
     * @return ���� finishedItem��
     */
    public com.plugins.pending.entity.FinishedItem getFinishedItem() {
        return finishedItem;
    }

    /**
     * @param finishedItem Ҫ���õ� finishedItem��
     */
    public void setFinishedItem(
            com.plugins.pending.entity.FinishedItem finishedItem) {
        this.finishedItem = finishedItem;
    }

    /**
     * @return ���� forDoc��
     */
//    public com.spower.basesystem.pending.valueobject.PendingItem getForDoc() {
//        return forDoc;
//    }

    /**
     * @param forDoc Ҫ���õ� forDoc��
     */
//    public void setForDoc(com.spower.basesystem.pending.valueobject.PendingItem forDoc) {
//        this.forDoc = forDoc;
//    }

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
     * @return ���� recipientBakId��
     */
    public String getRecipientBakId() {
        return recipientBakId;
    }

    /**
     * @param recipientBakId Ҫ���õ� recipientBakId��
     */
    public void setRecipientBakId(String recipientBakId) {
        this.recipientBakId = recipientBakId;
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
     * @return ���� beginTime��
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime Ҫ���õ� beginTime��
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return ���� endTime��
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime Ҫ���õ� endTime��
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return ���� sender��
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender Ҫ���õ� sender��
     */
    public void setSender(String sender) {
        this.sender = sender;
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

}
