package com.bizideal.whoami.user.dto;

import java.util.List;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月9日 上午10:06:20
 * @version 1.0 app群信息传输类
 */
public class GroupDto extends BaseEntity {
	private static final long serialVersionUID = -831660692551102829L;

	private int meeId; // 会议id

	private String groupId;// 群id

	private String groupName;// 群名称

	private List<String> headimgurls;// 前9个群成员头像地址

	public GroupDto() {
		super();
	}

	public GroupDto(int meeId, String groupId, String groupName,
			List<String> headimgurls) {
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

	@Override
	public String toString() {
		return "GroupDto [meeId=" + meeId + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", headimgurls=" + headimgurls
				+ "]";
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

}
