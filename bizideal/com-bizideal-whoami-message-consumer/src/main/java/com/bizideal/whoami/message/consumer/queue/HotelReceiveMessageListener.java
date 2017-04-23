package com.bizideal.whoami.message.consumer.queue;

import javax.jms.JMSException;
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
import com.bizideal.whoami.facade.hotel.facade.HotelUserLinkFacade;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingUserGroup;
import com.bizideal.whoami.message.service.TransactionMessageService;

/**
 * 报名-酒店入住消息监听器
 *
 */
@Component
public class HotelReceiveMessageListener implements SessionAwareMessageListener<Message> {

	private static final Log log = LogFactory.getLog(HotelReceiveMessageListener.class);
	
	@Autowired
	HotelUserLinkFacade hotelUserLinkFacade;
	
	@Autowired
	TransactionMessageService transactionMessageService;
	
	@Override
	public synchronized void onMessage(Message message, Session session) throws JMSException {

		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			log.info("== SingUpHotelReceive message:" + ms);
			JSONObject jsonObject = JSONObject.fromObject(ms);
			ObjectMapper objectMapper = new ObjectMapper();
			
			switch (jsonObject.getString("type")) {
				case "SignUp":
					// 把用户报名信息转换成实体对象

					SignUpInfoDto singUpInfoDto  = objectMapper.readValue(ms, SignUpInfoDto.class);
					boolean flag = hotelUserLinkFacade.hotelSignUp(singUpInfoDto);
					if(flag){
						transactionMessageService.deleteMessageByMessageId(singUpInfoDto.getMessageId());
					}
					break;
				default:
					break;
				
			}		
		} catch (Exception e) {
			log.error("== SingUpHotelReceive message:" + e);
		}
	}

}
