package com.bizideal.whoami.message.consumer.queue.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Demo消息监听器
 *
 */
@Component
public class DemoReceiveMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log log = LogFactory.getLog(DemoReceiveMessageListener.class);

	@Override
	public synchronized void onMessage(Message message, Session session) throws JMSException {

		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("== DeomReceive message:" + ms);
		} catch (Exception e) {
			log.error("== DeomReceive message:" + e);
		}
	}

}
