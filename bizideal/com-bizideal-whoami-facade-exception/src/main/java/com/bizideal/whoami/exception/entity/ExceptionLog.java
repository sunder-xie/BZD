package com.bizideal.whoami.exception.entity;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.CommonModel;

@Table(name = "exception_log")
public class ExceptionLog extends CommonModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="JDBC")
	private Long id;

    private String controllerClassName;

    private String controllerMethodName;

    private String serviceClassName;

    private String serviceMethodName;

    private String resendUrl;

    private String params;

    private String code;

    private String msg;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getControllerClassName() {
        return controllerClassName;
    }

    public void setControllerClassName(String controllerClassName) {
        this.controllerClassName = controllerClassName == null ? null : controllerClassName.trim();
    }

    public String getControllerMethodName() {
        return controllerMethodName;
    }

    public void setControllerMethodName(String controllerMethodName) {
        this.controllerMethodName = controllerMethodName == null ? null : controllerMethodName.trim();
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName == null ? null : serviceClassName.trim();
    }

    public String getServiceMethodName() {
        return serviceMethodName;
    }

    public void setServiceMethodName(String serviceMethodName) {
        this.serviceMethodName = serviceMethodName == null ? null : serviceMethodName.trim();
    }

    public String getResendUrl() {
        return resendUrl;
    }

    public void setResendUrl(String resendUrl) {
        this.resendUrl = resendUrl == null ? null : resendUrl.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}