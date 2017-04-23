package com.bizideal.whoami.facade.meeting.dto;

public class MeetingLinkUserDto {

	private String userId;

	private Integer meeId;

	/** 昵称**/
	private String nickname;

	/**头像地址**/
	private String headimgurl;
	
	/**关注的时间**/
	private Long createTime;

	public MeetingLinkUserDto() {
		super();
	}

	public MeetingLinkUserDto(String userId, Integer meeId, String nickname,
			String headimgurl, Long createTime) {
		super();
		this.userId = userId;
		this.meeId = meeId;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MeetingLinkUserDto [userId=" + userId + ", meeId=" + meeId
				+ ", nickname=" + nickname + ", headimgurl=" + headimgurl
				+ ", createTime=" + createTime + "]";
	}

	
}
