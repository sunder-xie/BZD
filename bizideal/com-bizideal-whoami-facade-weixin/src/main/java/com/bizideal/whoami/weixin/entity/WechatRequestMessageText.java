package com.bizideal.whoami.weixin.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 文本请求消息  一个请求只能对应文本、图文、图片消息中的一种
 * @author zhu_shangjin
 * @version 2016年12月22日 上午10:06:47
 */
@Table(name = "wechat_request_message_text")
public class WechatRequestMessageText extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Mysql")
	// 主键
	private Integer requestId;
//自动回复规则名称
	private String reqRuleName;
//响应类型
	private String reqRespType;
//关键字
	private String keyWords;

	@Transient
	//对应的文本回复消息
	private WechatResponseMessageText textResponse;
	@Transient
	//对应的图文回复消息
	private WechatNews newsResponse;
	@Transient
	//对应的图片回复消息
	private WechatImage imageResponse;


	public WechatResponseMessageText getTextResponse() {
		return textResponse;
	}

	public void setTextResponse(WechatResponseMessageText textResponse) {
		this.textResponse = textResponse;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getReqRespType() {
		return reqRespType;
	}

	public void setReqRespType(String reqRespType) {
		this.reqRespType = reqRespType;
	}

	public String getReqRuleName() {
		return reqRuleName;
	}

	public void setReqRuleName(String reqRuleName) {
		this.reqRuleName = reqRuleName;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public WechatNews getNewsResponse() {
		return newsResponse;
	}

	public void setNewsResponse(WechatNews newsResponse) {
		this.newsResponse = newsResponse;
	}

	public WechatImage getImageResponse() {
		return imageResponse;
	}

	public void setImageResponse(WechatImage imageResponse) {
		this.imageResponse = imageResponse;
	}

}
