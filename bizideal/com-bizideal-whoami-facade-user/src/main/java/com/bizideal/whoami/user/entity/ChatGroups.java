package com.bizideal.whoami.user.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月10日 下午1:45:22
 * @version 1.0
 */
@Table(name = "chat_groups")
public class ChatGroups extends BaseEntity {
	private static final long serialVersionUID = -3534051293053460386L;
	@Id
	private String groupId; // 群id
	private String groupName;// 群名称
	private String groupDescription;// 群描述
	private boolean groupIsPublic;// 是否公开
	private int groupMaxusers;// 群最大人数
	private String groupOwner;// 群主id
	private boolean approval;// 入群是否需要群主同意

	public ChatGroups() {
		super();
	}

	public ChatGroups(String groupId, String groupName,
			String groupDescription, boolean groupIsPublic, int groupMaxusers,
			String groupOwner, boolean approval) {
		this.groupId = groupId;
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

	public void setApproval(boolean approval) {
		this.approval = approval;
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

	@Override
	public String toString() {
		return "ChatGroups [groupId=" + groupId + ", groupName=" + groupName
				+ ", groupDescription=" + groupDescription + ", groupIsPublic="
				+ groupIsPublic + ", groupMaxusers=" + groupMaxusers
				+ ", groupOwner=" + groupOwner + ", approval=" + approval + "]";
	}

}
