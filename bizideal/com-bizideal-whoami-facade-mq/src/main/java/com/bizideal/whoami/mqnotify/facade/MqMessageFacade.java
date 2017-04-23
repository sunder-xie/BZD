package com.bizideal.whoami.mqnotify.facade;

import com.bizideal.whoami.mqnotify.entity.MqMessage;
import com.bizideal.whoami.pojo.DubboxResult;

/**
 * @author zhu_shangjin
 * @version 2016年12月2日 上午10:21:12
 */
public interface MqMessageFacade {
	//执行发送消息的接口
	DubboxResult notifyMessage(MqMessage message);

}
