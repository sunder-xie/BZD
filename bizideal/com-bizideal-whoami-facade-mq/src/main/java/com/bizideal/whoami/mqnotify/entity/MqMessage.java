package com.bizideal.whoami.mqnotify.entity;

import java.io.Serializable;

/**
 * @author zhu_shangjin
 * @version 2016年12月2日 上午10:18:31
 * 发送的消息的实体
 */
public class MqMessage implements Serializable{
	private String message;
	
	public MqMessage() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
