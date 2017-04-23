package com.bizideal.whoami.user.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月4日 下午12:55:41
 * @version 1.0 用户模块所有异常定义
 */
public enum UserEnum {
	USER_OK(0, "success"), 
	USER_EXCEPTION(6201, "发生系统异常"), 
//	USER_DIET_UPDATE(6202, "饮食更新失败"), 
//	USER_DIET_SET(6203, "饮食添加失败"), 
//	USER_DIET_DELETE(6204, "饮食删除失败"), 
	USER_MSG_NOT_FOUND(6205, "验证码失效"), 
	USER_MSG_EMPTY(6206, "输入验证码为空"), 
	USER_MSG_ERROR(6207, "验证码错误"), 
	USER_MSG_SEND_FAILED(6208, "验证码发送失败"), 
	USER_LOGIN_FAILED(6209, "用户名或密码错误，登陆失败"), 
	USER_MSG_USER_ISREGISTERD(6210, "该手机号已注册，请直接登陆"), 
	USER_MSG_USER_NOREGISTERD(6211,"该手机号未注册，验证码发送失败"),
	USER_UPDATE_NAMEANDWEIXIN_FAILED(6212,"更新真实姓名和微信失败"),
	USER_BINDPHONE_FAILED(6213,"微信绑定手机号失败"),
	USER_PASSWORD_ERROR(6215,"原密码不正确"),
	USER_PASSWORD_UPDATE_FAILED(6216,"修改密码失败"),
	USER_PARAMETER_EMTPY(6217,"参数为空"),
	USER_USER_NOT_FOUND(6218,"用户不存在"),
	USER_IM_UP_PWD_FAILED(6219,"调用环信修改密码失败"),
	USER_IM_UP_PWD_SAME(6220,"新旧密码不能相同"),
	USER_HUANXIN_REGISTER_FAILED(6214,"环信注册失败");
	private int errcode;
	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	UserEnum(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public static UserEnum getEnum(int errcode) {
		UserEnum resultEnum = null;
		UserEnum[] enumAry = UserEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getErrcode() == errcode) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		UserEnum[] ary = UserEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getErrcode()));
			map.put("errcode", String.valueOf(ary[num].getErrcode()));
			map.put("errmsg", ary[num].getErrmsg());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	public static void main(String[] args) {
		System.out.println(toMap());
	}

}
