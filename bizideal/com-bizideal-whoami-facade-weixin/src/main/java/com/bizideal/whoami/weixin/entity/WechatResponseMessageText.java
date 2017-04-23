package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 文本响应消息
 * @author zhu_shangjin
 * @version 2016年12月22日 上午10:11:07
 */
@Table(name = "wechat_response_message_text")
public class WechatResponseMessageText extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	//主键
	private Integer responseId;
//响应内容
	private String respContent;
//关键字
	private String reqKeyWords;

	public Integer getResponseId() {
		return responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public String getReqKeyWords() {
		return reqKeyWords;
	}

	public void setReqKeyWords(String reqKeyWords) {
		this.reqKeyWords = reqKeyWords;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}

}
