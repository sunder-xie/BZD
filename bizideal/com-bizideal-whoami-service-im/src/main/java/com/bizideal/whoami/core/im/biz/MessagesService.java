package com.bizideal.whoami.core.im.biz;

import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.im.entity.IMMsg;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName MessagesService
 * @Description (发送消息的接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
public interface MessagesService {

	/**
	 * 发送消息
	 * @param targetType  消息投递者类型：users 用户, chatgroups 群组
	 * @param target 接收者ID 必须是数组,数组元素为用户ID或者群组ID
	 * @param msg 消息内容
	 * @param from 发送者
	 * @param ext 扩展字段
	 * @return 请求响应
	 */
	ObjectNode sendMessages(IMMsg msg) throws CustomException;
	

	/**
	 * 向某个群组的所有成员发送消息
	 * @param groupIds
	 * @param msg
	 * @return
	 */
	ObjectNode sendGroupMessages(String groupId,IMMsg msg) throws CustomException;


	/**
	 * 拜访消息特殊处理方法
	 * @param msg
	 * @return
	 */
	ObjectNode sendOpenMessage(IMMsg msg) throws CustomException;

}