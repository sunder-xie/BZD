package com.bizideal.whoami;

import java.io.Serializable;

/**
 * @author zhu_shangjin
 * @version 2016年12月2日 上午10:18:31
 */
public class MqMessage implements Serializable{
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	

}
