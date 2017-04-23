package com.bizideal.whoami.message.consumer.queue;

import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONObject;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;

/**
 * 
 * @描述: 报名-用户通知队列监听器
 * 
 */
@Component
public class UserReceiveMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log log = LogFactory.getLog(UserReceiveMessageListener.class);
		
	@Autowired
	UserWeixinInfoFacade userWeixinInfoFacade;
	
	@Autowired
	TransactionMessageService transactionMessageService;
	
	@Override
	public synchronized void onMessage(Message message, Session session) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("== UserReceive message:" + ms);
			JSONObject jsonObject = JSONObject.fromObject(ms);
			ObjectMapper objectMapper = new ObjectMapper();
			
			switch (jsonObject.getString("type")) {
				case "SignUp":
					// 把用户报名信息转换成实体对象
					
					SignUpInfoDto singUpInfoDto  = objectMapper.readValue(ms, SignUpInfoDto.class);
					boolean flag = userWeixinInfoFacade.userSignUp(singUpInfoDto);
					if(flag){
						transactionMessageService.deleteMessageByMessageId(singUpInfoDto.getMessageId());
					}
					break;
				default:
					break;
				
			}		

		} catch (Exception e) {
			log.error("== UserReceive message:" + e);
		}
	}
}
