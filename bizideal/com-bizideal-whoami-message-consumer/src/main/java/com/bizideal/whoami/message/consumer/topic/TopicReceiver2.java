package com.bizideal.whoami.message.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  Topic消息监听器
 * 
 */
@Component
public class TopicReceiver2 implements SessionAwareMessageListener<Message>{

	private static final Log log = LogFactory.getLog(TopicReceiver2.class);
	
	@Override
	public void onMessage(Message message, Session session) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("== TopicReceiver2接收到消息:" + ms);
		} catch (Exception e) {
			log.error("== TopicReceiver2:" + e);
		}
	}
	
}
