package com.bizideal.whoami.im.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName IMMsg
 * @Description TODO(发送的消息)
 * @Author Yt.Cui
 * @Date 2017-01-04 13:14:06
 */
@XmlRootElement
public class IMMsg  implements Serializable{
	
	private static final long serialVersionUID = -3276984843401557826L;

	/** 发送的消息类型（users 给用户发消息。chatgroups 给群发消息，chatrooms 给聊天室发消息） **/
	private String target_type;
	
	/**消息的目标（注意这里需要用数组，数组长度建议不大于20，即使只有一个用户u1或者群组，也要用数组形式 ['u1']）**/
	private String[] target;
	
	/**示消息发送者**/
	private String from;
	
	/**发送的消息内容**/
	private IMMessage msg;
	
	private String userId;
	
	private String messageType;
	
	/**拜访或者回访消息**/
	private String userName;
	private String meetId;
	private String groupId;
	private String subMeetId;
	private String meetName;
	
	/**推送消息**/
	private String title;
	private String httpUrl;
	private String content;
	private String imageUrl;
	private String secret;
	private String subMeetName;
	 
	public IMMsg() {
		super();
	}
	public IMMsg(String target_type, String[] target, String from,
			IMMessage msg, String userId, String messageType, String userName,
			String meetId, String groupId, String subMeetId, String meetName,
			String title, String httpUrl, String content, String imageUrl,
			String secret,String subMeetName) {
		super();
		this.target_type = target_type;
		this.target = target;
		this.from = from;
		this.msg = msg;
		this.userId = userId;
		this.messageType = messageType;
		this.userName = userName;
		this.meetId = meetId;
		this.groupId = groupId;
		this.subMeetId = subMeetId;
		this.meetName = meetName;
		this.title = title;
		this.httpUrl = httpUrl;
		this.content = content;
		this.imageUrl = imageUrl;
		this.secret = secret;
		this.subMeetName=subMeetName;
	}
	public String getTarget_type() {
		return target_type;
	}
	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	public String[] getTarget() {
		return target;
	}
	public void setTarget(String[] target) {
		this.target = target;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public IMMessage getMsg() {
		return msg;
	}
	public void setMsg(IMMessage msg) {
		this.msg = msg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMeetId() {
		return meetId;
	}
	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSubMeetId() {
		return subMeetId;
	}
	public void setSubMeetId(String subMeetId) {
		this.subMeetId = subMeetId;
	}
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getSubMeetName() {
		return subMeetName;
	}
	public void setSubMeetName(String subMeetName) {
		this.subMeetName = subMeetName;
	}
	@Override
	public String toString() {
		return "IMMsg [target_type=" + target_type + ", target="
				+ Arrays.toString(target) + ", from=" + from + ", msg=" + msg
				+ ", userId=" + userId + ", messageType=" + messageType
				+ ", userName=" + userName + ", meetId=" + meetId
				+ ", groupId=" + groupId + ", subMeetId=" + subMeetId
				+ ", meetName=" + meetName + ", title=" + title + ", httpUrl="
				+ httpUrl + ", content=" + content + ", imageUrl=" + imageUrl
				+ ", secret=" + secret + ", subMeetName=" + subMeetName + "]";
	}
}