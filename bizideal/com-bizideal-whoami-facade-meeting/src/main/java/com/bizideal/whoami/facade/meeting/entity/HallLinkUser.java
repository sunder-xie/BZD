package com.bizideal.whoami.facade.meeting.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @ClassName HallLinkUser
 * @Description TODO(关注会议厅用户的实体)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:29:39
 */
@Table(name = "hall_link_user")
public class HallLinkUser extends BaseEntity{

	private static final long serialVersionUID = -2704612332742788448L;

	/**用户编号**/
	@Id
	private String userId;

	/**会议厅编号**/
	@Id
	private Integer hallId;

	/**关注时间**/
	private Long createTime;
	
	/**真实姓名**/
	@Transient
	private String realName;
	
	/**用户头像**/
	@Transient
	private String headimgurl;
	
	/**会议厅名称**/
	@Transient
	private String hallName;
	
	/**会议厅封面**/
	@Transient
	private String hallCover;
	
	public HallLinkUser() {

	}

	public HallLinkUser(String userId, Integer hallId) {
		this.userId = userId;
		this.hallId = hallId;
	}
	
	public HallLinkUser(String userId, Integer hallId, Long createTime, String realName, String headimgurl,
			String hallName, String hallCover) {
		super();
		this.userId = userId;
		this.hallId = hallId;
		this.createTime = createTime;
		this.realName = realName;
		this.headimgurl = headimgurl;
		this.hallName = hallName;
		this.hallCover = hallCover;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Integer getHallId() {
		return hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getHallCover() {
		return hallCover;
	}

	public void setHallCover(String hallCover) {
		this.hallCover = hallCover;
	}

	@Override
	public String toString() {
		return "HallLinkUser [userId=" + userId + ", hallId=" + hallId
				+ ", createTime=" + createTime + ", realName=" + realName
				+ ", headimgurl=" + headimgurl + ", hallName=" + hallName
				+ ", hallCover=" + hallCover + "]";
	}

}