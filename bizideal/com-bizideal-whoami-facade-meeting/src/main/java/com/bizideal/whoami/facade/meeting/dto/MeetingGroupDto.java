package com.bizideal.whoami.facade.meeting.dto;

import java.util.List;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 群dto
 * 
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:21:19
 * @version 1.0 app群信息传输类
 */
public class MeetingGroupDto extends BaseEntity {
	private static final long serialVersionUID = -831660692551102829L;

	/**
	 * 会议id
	 */
	private int meeId;

	/**
	 * 群id
	 */
	private String groupId;

	/**
	 * 群名称
	 */
	private String groupName;

	/**
	 * 头像图片URL
	 */
	private List<String> headimgurls;

	public MeetingGroupDto() {
		super();
	}

	public MeetingGroupDto(int meeId, String groupId, String groupName, List<String> headimgurls) {
		super();
		this.meeId = meeId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.headimgurls = headimgurls;
	}

	public int getMeeId() {
		return meeId;
	}

	public void setMeeId(int meeId) {
		this.meeId = meeId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<String> getHeadimgurls() {
		return headimgurls;
	}

	public void setHeadimgurls(List<String> headimgurls) {
		this.headimgurls = headimgurls;
	}

	@Override
	public String toString() {
		return "GroupDto [meeId=" + meeId + ", groupId=" + groupId + ", groupName=" + groupName + ", headimgurls="
				+ headimgurls + "]";
	}
}
