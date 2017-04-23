package com.bizideal.whoami.facade.leaflets.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

public class MeetingLeaflets extends BaseEntity{
	
	private static final long serialVersionUID = -6220390721251105379L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

    private Integer meeId;

    private String leafletName;

    private String remark;

    private String createUser;

    private String url;

    private String isInUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeeId() {
        return meeId;
    }

    public void setMeeId(Integer meeId) {
        this.meeId = meeId;
    }

    public String getLeafletName() {
        return leafletName;
    }

    public void setLeafletName(String leafletName) {
        this.leafletName = leafletName == null ? null : leafletName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(String isInUse) {
        this.isInUse = isInUse == null ? null : isInUse.trim();
    }
}