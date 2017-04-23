package com.bizideal.whoami.mqservice;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;




import com.bizideal.whoami.mqnotify.entity.MqMessage;
import com.bizideal.whoami.mqnotify.facade.MqMessageFacade;
import com.bizideal.whoami.pojo.ActiveMqQuenuesName;
import com.bizideal.whoami.pojo.DubboxResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author zhu_shangjin
 * @version 2016年12月2日 上午10:48:52
 * 发送mq消息
 */
@Component("mqMessageFacade")
public class MqMessageFacadeImp implements MqMessageFacade{
	@Autowired
	private JmsTemplate notifyJmsTemplate;
	@Autowired
	ActiveMqQuenuesName activeMqQuenuesName;

	@Override
	public DubboxResult notifyMessage(MqMessage mqMessage) {
		DubboxResult dubboxResult = null;
		try {
			String names = activeMqQuenuesName.getQuenuesNames();
			final String message = mqMessage.getMessage();
			JsonObject megJson = (JsonObject) new JsonParser().parse(message);
			String[] quenues = new String[]{};
			if (names.contains(",")) {
				quenues = names.split(",");
			}else {
				quenues[0] = names;
			}
			
			for (String quenueName : quenues) {
				if (megJson.has(quenueName)) {
					try {
						
						notifyJmsTemplate.send(quenueName, new MessageCreator() {
							@Override
							public Message createMessage(Session session) throws JMSException {
								return session.createTextMessage(message);
							}
						});
						dubboxResult =  new DubboxResult("200", "ok", "ok");
					} catch (Exception e) {
						e.printStackTrace();
						//报名信息发送到mq失败 
						dubboxResult = new DubboxResult("400", "exception", e.getMessage());
						break;
						
					}
				}
				
			}
			if (null == dubboxResult) {
				dubboxResult = DubboxResult.build("500", "消息格式不正确",mqMessage.getMessage());
			}
			return dubboxResult;
			
		} catch (Exception e) {
			e.printStackTrace();
			//报名信息发送到mq失败 
			dubboxResult = new DubboxResult("400", "exception", e.getMessage());
			return dubboxResult;
		}
		
	}


}
