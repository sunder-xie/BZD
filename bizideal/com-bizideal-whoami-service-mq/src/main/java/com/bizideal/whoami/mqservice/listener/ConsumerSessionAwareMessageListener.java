package com.bizideal.whoami.mqservice.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;



/**
 * 
 * @描述: 通知队列监听器.
 * @作者: zhushangjin
 * @创建: 2016-12-2,下午3:58:28
 * @版本: V1.0
 * 
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log log = LogFactory.getLog(ConsumerSessionAwareMessageListener.class);

	
	@Autowired
	private JmsTemplate notifyJmsTemplate;
	@Autowired
	private Destination sessionAwareQueue;

	@SuppressWarnings("static-access")
	public synchronized void onMessage(Message message, Session session) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("== receive message:" + ms);
			System.out.println(ms);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			//消费失败 保存到数据库 或者发送到mq的error队列
		}
	}
}
