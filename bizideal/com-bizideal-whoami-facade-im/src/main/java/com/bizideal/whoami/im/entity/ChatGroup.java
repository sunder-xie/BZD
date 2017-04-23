package com.bizideal.whoami.im.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName ChatGroup
 * @Description TODO(群组数据结构)
 * @Author Yt.Cui
 * @Date 2017-01-04 13:13:36
 */
@XmlRootElement
public class ChatGroup implements Serializable{

	private static final long serialVersionUID = -57895910711871618L;

	/** 群组ID，群组唯一标识符，由环信服务器生成。 **/
	private String id;
	
	/** 群组ID，和id一样，群组唯一标识符，由环信服务器生成。 **/
	private String groupid;
	
	/** 群组名称，任意字符串。 **/
	private String name;
	
	/** 群组名称，任意字符串。 **/
	private String groupname;
	
	/** 群组描述，任意字符串。 **/
	private String description;
	
	/** 群组类型：true:公开群，false:私有群。 **/
	private Boolean isPublic;
	
	/** 是否只有群成员可以进来发言。true:是，false:否。该字段只能在群组详情中查看。 **/
	private Boolean membersonly;
	
	/** 是否允许群成员邀请别人加入此群。true:允许群成员邀请人加入此群，false:只有群主才可以往群里加人。该字段只能在群组详情中查看。 **/
	private Boolean allowinvites;
	
	/** 群成员上限，创建群组的时候设置，可修改。 **/
	private Integer maxusers;
	
	/** 现有成员总数。 **/
	private Integer affiliations_count;
	
	/** 群主的username。例如：{“owner”:“13800138001”}。 **/
	private String owner;
	
	/** 群成员的username。例如：{“member”:“xc6xrnbzci”}。 **/
	private String member;
	
	/** 加入公开群是否需要批准，默认值是false（加入公开群不需要群主批准），此属性为必选的，私有群必须为true **/
	private Boolean approval;

	public ChatGroup() {
		super();
	}

	public ChatGroup(String id, String groupid, String name, String groupname, String description, Boolean isPublic,
			Boolean membersonly, Boolean allowinvites, Integer maxusers, Integer affiliations_count, String owner,
			String member, Boolean approval) {
		super();
		this.id = id;
		this.groupid = groupid;
		this.name = name;
		this.groupname = groupname;
		this.description = description;
		this.isPublic = isPublic;
		this.membersonly = membersonly;
		this.allowinvites = allowinvites;
		this.maxusers = maxusers;
		this.affiliations_count = affiliations_count;
		this.owner = owner;
		this.member = member;
		this.approval = approval;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getMembersonly() {
		return membersonly;
	}

	public void setMembersonly(Boolean membersonly) {
		this.membersonly = membersonly;
	}

	public Boolean getAllowinvites() {
		return allowinvites;
	}

	public void setAllowinvites(Boolean allowinvites) {
		this.allowinvites = allowinvites;
	}

	public Integer getMaxusers() {
		return maxusers;
	}

	public void setMaxusers(Integer maxusers) {
		this.maxusers = maxusers;
	}

	public Integer getAffiliations_count() {
		return affiliations_count;
	}

	public void setAffiliations_count(Integer affiliations_count) {
		this.affiliations_count = affiliations_count;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "ChatGroup [id=" + id + ", groupid=" + groupid + ", name="
				+ name + ", groupname=" + groupname + ", description="
				+ description + ", isPublic=" + isPublic + ", membersonly="
				+ membersonly + ", allowinvites=" + allowinvites
				+ ", maxusers=" + maxusers + ", affiliations_count="
				+ affiliations_count + ", owner=" + owner + ", member="
				+ member + ", approval=" + approval + "]";
	}

}
