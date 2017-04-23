package com.bizideal.whoami.facade.meeting.entity;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @ClassName MeetingLinkUser
 * @Description TODO(用户关注的会议)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:04:33
 */
public class MeetingLinkUser extends BaseEntity{
	
	private static final long serialVersionUID = 6535539678596305988L;

	/**用户编号**/
	private String userId;

	/**会议编号**/
    private Integer meeId;
    
    /**关注时间**/
    private Long createTime;
    
	public MeetingLinkUser() {
		super();
	}

	public MeetingLinkUser(String userId, Integer meeId, Long createTime) {
		super();
		this.userId = userId;
		this.meeId = meeId;
		this.createTime = createTime;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MeetingLinkUser [userId=" + userId + ", meeId=" + meeId
				+ ", createTime=" + createTime + "]";
	}

}