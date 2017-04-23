package com.bizideal.whoami.message.biz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.facade.SignUpInfoFacade;
import com.bizideal.whoami.utils.PublicConfigUtil;

/**
 * message业务处理类
 * 
 * @author：chaojin
 */
@Service("messageBiz")
public class MessageBiz {

	private static final Log log = LogFactory.getLog(MessageBiz.class);
	//报名主业务接口
	@Autowired
	private SignUpInfoFacade signUpInfoFacade; 
	
	@Autowired
	private TransactionMessageService transactionMessageService;

	/**
	 * 处理[waiting_confirm]状态的消息
	 * 首先去主业务下查询当前消息是否执行成功，如果成功则将消息发送到队列，失败则将消息删除
	 * @param messages
	 */
	public void handleWaitingConfirmTimeOutMessages(Map<String, TransactionMessage> messageMap) {
		//log.debug("开始处理[waiting_confirm]状态的消息,总条数[" + messageMap.size() + "]");
		// 单条消息处理（目前该状态的消息，消费队列全部是accounting，如果后期有业务扩充，需做队列判断，做对应的业务处理。）
		for (Map.Entry<String, TransactionMessage> entry : messageMap.entrySet()) {
			TransactionMessage message = entry.getValue();
			try {
				
				//log.debug("开始处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
				boolean flag = false;
				if("USER".equals(message.getConsumerQueue())){
					flag = WaitingConfirmTimeOutMessages(message);
				}else if ("HOTEL".equals(message.getConsumerQueue())) {
					flag = WaitingConfirmTimeOutMessages(message);
				}else if ("MEETING".equals(message.getConsumerQueue())) {
					flag = WaitingConfirmTimeOutMessages(message);
				}
				
				// 如果订单成功，把消息改为待处理，并发送消息
				if (flag) { //判断数据的状态，如果主业务执行成功将消息发送到队列
					// 确认并发送消息
					transactionMessageService.confirmAndSendMessage(message.getMessageId());
					
				} else{ //判断数状态，如果业务执行失败删除消息
					log.debug("主业务逻辑没有执行成功,删除[waiting_confirm]消息id[" + message.getMessageId() + "]的消息");
					transactionMessageService.deleteMessageByMessageId(message.getMessageId());
				}

				//log.debug("结束处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
			} catch (Exception e) {
				log.error("处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
			}
		}
	}
	
	private boolean WaitingConfirmTimeOutMessages(TransactionMessage message){
		boolean flag = false;
		String messageBody = message.getMessageBody();
		JSONObject jsonObject = JSONObject.fromObject(messageBody);
		switch (jsonObject.getString("type")) {
			case "SignUp":
				//获取需要查询的数据的主键
				String str = message.getField1();  
				SignUpInfo signUpInfo = signUpInfoFacade.selectBySignUpId(str);
				if(signUpInfo != null && "1".equals(signUpInfo.getIsPend())){
					flag = true;
				}else if(signUpInfo != null && "0".equals(signUpInfo.getIsPend())){
					flag = true;
				}
				break;
		}	
		return flag;
	}

	/**
	 * 处理[SENDING]状态的消息
	 * 
	 * @param messages
	 */
	public void handleSendingTimeOutMessage(Map<String, TransactionMessage> messageMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//log.debug("开始处理[SENDING]状态的消息,总条数[" + messageMap.size() + "]");

		// 根据配置获取通知间隔时间
		Map<Integer, Integer> notifyParam = getSendTime();

		// 单条消息处理
		for (Map.Entry<String, TransactionMessage> entry : messageMap.entrySet()) {
			TransactionMessage message = entry.getValue();
			try {
				//log.debug("开始处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
				// 判断发送次数
				int maxTimes = Integer.valueOf(PublicConfigUtil.readConfig("message.max.send.times"));
				//log.debug("[SENDING]消息ID为[" + message.getMessageId() + "]的消息,已经重新发送的次数[" + message.getMessageSendTimes() + "]");

				// 如果超过最大发送次数直接退出
				if (maxTimes < message.getMessageSendTimes()) {
					// 标记为死亡
					transactionMessageService.setMessageToAreadlyDead(message.getMessageId());
					continue;
				}
				// 判断是否达到发送消息的时间间隔条件
				
				int reSendTimes = message.getMessageSendTimes();
				//更具次数设定发送时间
				int times = notifyParam.get(reSendTimes == 0 ? 1 : reSendTimes);
				//获取当前时间
				long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
				//设置下一次发送时间
				long needTime = currentTimeInMillis - times * 60 * 1000;
				//最后一次发送时间
				long hasTime = message.getEditTime().getTime();
				// 判断是否达到了可以再次发送的时间条件
				if (hasTime > needTime) {
					//log.debug("currentTime[" + sdf.format(new Date()) + "],[SENDING]消息上次发送时间[" + sdf.format(message.getEditTime()) + "],必须过了[" + times + "]分钟才可以再发送。");
					continue;
				}
				// 重新发送消息
				transactionMessageService.reSendMessage(message);
				//log.debug("结束处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
			} catch (Exception e) {
				log.error("处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
			}
		}

	}

	/**
	 * 根据配置获取通知间隔时间
	 * 
	 * @return
	 */
	private Map<Integer, Integer> getSendTime() {
		Map<Integer, Integer> notifyParam = new HashMap<Integer, Integer>();
		notifyParam.put(1, Integer.valueOf(PublicConfigUtil.readConfig("message.send.1.time")));
		notifyParam.put(2, Integer.valueOf(PublicConfigUtil.readConfig("message.send.2.time")));
		notifyParam.put(3, Integer.valueOf(PublicConfigUtil.readConfig("message.send.3.time")));
		notifyParam.put(4, Integer.valueOf(PublicConfigUtil.readConfig("message.send.4.time")));
		notifyParam.put(5, Integer.valueOf(PublicConfigUtil.readConfig("message.send.5.time")));
		return notifyParam;
	}
}
