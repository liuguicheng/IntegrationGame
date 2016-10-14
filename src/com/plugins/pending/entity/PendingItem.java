package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.console.entity.Staff;
import com.google.gson.Gson;

/**
 *        @hibernate.class
 *         table="fd_fordo"
 *
 */
public class PendingItem implements Serializable {

    /**
     * ����id
     */
    private static final long serialVersionUID = 905115379024794493L;

    /**
     * Gson�������ڶ�jsonData���ַ�����properties֮�����ת�����Ա�洢�Ͷ�ȡ
     */
    private static final Gson gson = new Gson();

    public static final String SEPARATOR = "/";

    /** identifier field */
    private String forDoId;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String moduleName;
//    /** Ӧ��ģ������ */
//    private String moduleType;
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

    /**
     * ��չ���Դ洢����json��ʽд��
     */
    private String jsonData;

    /**
     * ��չ���Լ�
     * д��ʱ����Properties����д�룬ת����json���д洢
     * ��ȡʱ����setJsonDataʱ��ת�ָ�Properties����Ȼ���ṩgetProps(String proName)������ȡ
     */
    private Properties props;

    /** default constructor */
    public PendingItem() {
    }

    /**
     * ������չ����
     * @param p �洢��չ���Ե�Properties�����ƺ�ֵֻ�����ַ���
     */
    public void setProps(Properties p) {
        props = p;
        if (p != null && !p.isEmpty()) {
            this.jsonData = gson.toJson(props);
        }
    }
    /**
     * ��ȡ��չ����ֵ
     * @param propertyName ������
     * @return ����ֵ�ַ���
     */
    public String getProperty(String propertyName) {
        if (props != null) {
            return props.getProperty(propertyName);
        }
        return "";
    }

    /**
     * ��д��jsonDataֵ����ת����Properties����
     * @param jsonData the jsonData to set
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
        try {
            props = gson.fromJson(jsonData, Properties.class);
        } catch (Exception ex) {
            props = null;
        }
    }

    /**
     *
     * @return �༶moduleName�����һ��
     */
    public String getSimpleName() {
        if (moduleName != null) {
            String[] s = moduleName.split(SEPARATOR);
            if (s != null && s.length > 0) {
                return s[s.length - 1];
            }
        }
        return moduleName;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("forDoId", getForDoId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof PendingItem)) {
            return false;
        }
        PendingItem castOther = (PendingItem) other;
        return new EqualsBuilder()
            .append(this.getForDoId(), castOther.getForDoId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getForDoId())
            .toHashCode();
    }

    /**
     * @return the jsonData
     */
    public String getJsonData() {
        return jsonData;
    }


}
