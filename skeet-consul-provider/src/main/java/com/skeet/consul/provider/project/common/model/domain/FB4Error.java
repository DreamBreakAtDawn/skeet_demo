/*
 * Copyright (c) 2017 4PX Information Technology Co.,Ltd. All rights reserved.
 */
package com.skeet.consul.provider.project.common.model.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author luoxb
 * @date 2017年3月13日
 */
public class FB4Error implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorMsg;
    /**
     * 错误数据
     */
    private String errorData;
    /**
     * 错误数据
     */
    private List<String> errorDataList;
    /**
     * 业务参考码
     */
    private String referenceCode;

    public FB4Error() {
    }

    public FB4Error(String errorCode) {
        this.errorCode = errorCode;
    }

    public FB4Error(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public FB4Error(String errorCode, String errorMsg, String errorData) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorData = errorData;
    }

    public FB4Error(String errorCode, String errorMsg, String errorData, String referenceCode) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorData = errorData;
        this.referenceCode = referenceCode;
    }

    @Override
    public String toString() {
        return "Error [errorCode=" + errorCode + ", errorMsg=" + errorMsg
                + ", referenceCode=" + referenceCode
                + ", errorData=" + errorData + "]";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorData() {
        return errorData;
    }

    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }

    public List<String> getErrorDataList() {
        return errorDataList;
    }

    public void setErrorDataList(List<String> errorDataList) {
        this.errorDataList = errorDataList;
    }

    /**
     * @return the referenceCode
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * @param referenceCode the referenceCode to set
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
}

