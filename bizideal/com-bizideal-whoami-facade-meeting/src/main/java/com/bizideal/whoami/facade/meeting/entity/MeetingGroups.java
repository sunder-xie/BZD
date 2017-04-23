package com.bizideal.whoami.facade.meeting.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 聊天群
 * 
 * @author 作者 sy:
 * @data 创建时间：2017年2月17日11:22:14
 * @version 1.0
 */
@Table(name = "meeting_groups")
public class MeetingGroups extends BaseEntity {
	private static final long serialVersionUID = -3534051293053460386L;

	/**
	 * 聊天群id
	 */
	@Id
	private String groupId;
	
	/**
	 * 会议id
	 */
	@Id
	private Integer meeId;

	/**
	 * 聊天群名称
	 */
	private String groupName;

	/**
	 * 聊天群说明
	 */
	private String groupDescription;

	/**
	 * 聊天群公开
	 */
	private boolean groupIsPublic;

	/**
	 * 聊天群最大人数
	 */
	private int groupMaxusers;

	/**
	 * 聊天群创建者
	 */
	private String groupOwner;

	/**
	 * 聊天群申请
	 */
	private boolean approval;

	public MeetingGroups() {
		super();
	}

	public MeetingGroups(String groupId, Integer meeId, String groupName,
			String groupDescription, boolean groupIsPublic, int groupMaxusers,
			String groupOwner, boolean approval) {
		super();
		this.groupId = groupId;
		this.meeId = meeId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.groupIsPublic = groupIsPublic;
		this.groupMaxusers = groupMaxusers;
		this.groupOwner = groupOwner;
		this.approval = approval;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public boolean isGroupIsPublic() {
		return groupIsPublic;
	}

	public void setGroupIsPublic(boolean groupIsPublic) {
		this.groupIsPublic = groupIsPublic;
	}

	public int getGroupMaxusers() {
		return groupMaxusers;
	}

	public void setGroupMaxusers(int groupMaxusers) {
		this.groupMaxusers = groupMaxusers;
	}

	public String getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(String groupOwner) {
		this.groupOwner = groupOwner;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "MeetingGroups [groupId=" + groupId + ", meeId=" + meeId
				+ ", groupName=" + groupName + ", groupDescription="
				+ groupDescription + ", groupIsPublic=" + groupIsPublic
				+ ", groupMaxusers=" + groupMaxusers + ", groupOwner="
				+ groupOwner + ", approval=" + approval + "]";
	}

	
}
