package com.console.power.command;

import org.springline.web.pagination.PaginationInfo;

public class PowerGrantInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = -8504162822774684959L;

    /**
     * Ȩ��Id
     */
    private String powerId;

    /**
     * ��Ӧ�ĵ�λId�б���Id�Զ��ŷָ�
     */
    private String depId;

    /**
     * @return Returns the depId.
     */
    public String getDepId() {
        return depId;
    }

    /**
     * @param depId The depId to set.
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }

    /**
     * @return Returns the powerId.
     */
    public String getPowerId() {
        return powerId;
    }

    /**
     * @param powerId The powerId to set.
     */
    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }


}
