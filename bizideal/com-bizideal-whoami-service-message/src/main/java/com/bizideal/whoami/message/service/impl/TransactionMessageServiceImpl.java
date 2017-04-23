package com.bizideal.whoami.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.message.enums.MessageStatusEnum;
import com.bizideal.whoami.message.enums.PublicEnum;
import com.bizideal.whoami.message.mapper.TransactionMessageMapper;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.PublicConfigUtil;
import com.bizideal.whoami.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <b>功能说明: </b>
 *
 */
@Service("transactionMessageService")
public class TransactionMessageServiceImpl implements
		TransactionMessageService {

	private static final Log log = LogFactory
			.getLog(TransactionMessageServiceImpl.class);

	@Autowired
	private TransactionMessageMapper transactionMessageMapper;

	@Autowired
	private JmsTemplate notifyJmsTemplate;

	public int saveMessageWaitingConfirm(TransactionMessage message)
			throws CustomException {

		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"saveMessageWaitingConfirm", "7001", "保存的消息为空");
		}

		if (StringUtil.isEmpty(message.getConsumerQueue())) {
			throw new CustomException("TransactionMessageServiceImpl",
					"saveMessageWaitingConfirm", "7002", "消息的消费队列不能为空 ");
		}

		message.setEditTime(new Date());
		message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
		message.setAreadlyDead(PublicEnum.NO.name());
		message.setMessageSendTimes(0);
		return transactionMessageMapper.insertSelective(message);
	}

	public int saveMessageWaitingConfirm(List<TransactionMessage> list)
			throws CustomException {

		for (TransactionMessage message : list) {

			if (message == null) {
				throw new CustomException("TransactionMessageServiceImpl",
						"saveMessageWaitingConfirm", "7001", "保存的消息为空");
			}

			if (StringUtil.isEmpty(message.getConsumerQueue())) {
				throw new CustomException("TransactionMessageServiceImpl",
						"saveMessageWaitingConfirm", "7002", "消息的消费队列不能为空 ");
			}

			message.setEditTime(new Date());
			message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
			message.setAreadlyDead(PublicEnum.NO.name());
			message.setMessageSendTimes(0);

		}

		return transactionMessageMapper.insertList(list);

	}

	public void confirmAndSendMessage(String messageId) throws CustomException {
		final TransactionMessage message = getMessageByMessageId(messageId);
		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"confirmAndSendMessage", "7003", "根据消息id查找的消息为空");
		}

		message.setStatus(MessageStatusEnum.SENDING.name());
		message.setEditTime(new Date());
		transactionMessageMapper.updateByPrimaryKeySelective(message);

		notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.getMessageBody());
			}
		});
	}
	
	public void confirmAndSendMessage(List<String> messageIds) throws CustomException {
		List<TransactionMessage> messages = transactionMessageMapper.getByMessageIds(messageIds);
		
		for (final TransactionMessage message : messages) {
			
			if (message == null) {
				throw new CustomException("TransactionMessageServiceImpl",
						"confirmAndSendMessage", "7003", "根据消息id查找的消息为空");
			}
			
			message.setStatus(MessageStatusEnum.SENDING.name());
			message.setEditTime(new Date());
			transactionMessageMapper.updateByPrimaryKeySelective(message);
			
			notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
			notifyJmsTemplate.send(new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message.getMessageBody());
				}
			});
		}

	}

	public int saveAndSendMessage(final TransactionMessage message)
			throws CustomException {

		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"saveAndSendMessage", "7001", "保存的消息为空");
		}

		if (StringUtil.isEmpty(message.getConsumerQueue())) {
			throw new CustomException("TransactionMessageServiceImpl",
					"saveAndSendMessage", "7002", "消息的消费队列不能为空 ");
		}

		message.setStatus(MessageStatusEnum.SENDING.name());
		message.setAreadlyDead(PublicEnum.NO.name());
		message.setMessageSendTimes(0);
		message.setEditTime(new Date());
		int result = transactionMessageMapper.insertSelective(message);

		notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.getMessageBody());
			}
		});

		return result;
	}

	public void directSendMessage(final TransactionMessage message)
			throws CustomException {

		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"directSendMessage", "7001", "保存的消息为空");
		}

		if (StringUtil.isEmpty(message.getConsumerQueue())) {
			throw new CustomException("TransactionMessageServiceImpl",
					"directSendMessage", "7002", "消息的消费队列不能为空 ");
		}

		notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.getMessageBody());
			}
		});
	}

	public void reSendMessage(final TransactionMessage message)
			throws CustomException {

		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"reSendMessage", "7001", "保存的消息为空");
		}

		if (StringUtil.isEmpty(message.getConsumerQueue())) {
			throw new CustomException("TransactionMessageServiceImpl",
					"reSendMessage", "7002", "消息的消费队列不能为空 ");
		}

		message.addSendTimes();
		message.setEditTime(new Date());
		transactionMessageMapper.updateByPrimaryKeySelective(message);

		notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.getMessageBody());
			}
		});
	}

	public void reSendMessageByMessageId(String messageId)
			throws CustomException {
		final TransactionMessage message = getMessageByMessageId(messageId);
		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"reSendMessageByMessageId", "7003", "根据消息id查找的消息为空");
		}

		int maxTimes = Integer.valueOf(PublicConfigUtil
				.readConfig("message.max.send.times"));
		if (message.getMessageSendTimes() >= maxTimes) {
			message.setAreadlyDead(PublicEnum.YES.name());
		}

		message.setEditTime(new Date());
		message.setMessageSendTimes(message.getMessageSendTimes() + 1);
		transactionMessageMapper.updateByPrimaryKeySelective(message);

		notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
		notifyJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message.getMessageBody());
			}
		});
	}

	public int setMessageToAreadlyDead(String messageId) throws CustomException {
		TransactionMessage message = transactionMessageMapper
				.getByMessageId(messageId);
		if (message == null) {
			throw new CustomException("TransactionMessageServiceImpl",
					"setMessageToAreadlyDead", "7003", "根据消息id查找的消息为空");
		}

		message.setAreadlyDead(PublicEnum.YES.name());
		message.setEditTime(new Date());
		return transactionMessageMapper.updateByPrimaryKeySelective(message);
	}

	public TransactionMessage getMessageByMessageId(String messageId)
			throws CustomException {

		return transactionMessageMapper.getByMessageId(messageId);
	}

	public int deleteMessageByMessageId(String messageId)
			throws CustomException {
		return transactionMessageMapper.deleteByMessageId(messageId);
	}
	
	public int deleteMessageByMessageId(List<String> messageIds)
			throws CustomException {
		return transactionMessageMapper.deleteByMessageIds(messageIds);
	}

	public void reSendAllDeadMessageByQueueName(String queueName, int batchSize)
			throws CustomException {
		log.info("==>reSendAllDeadMessageByQueueName");

		int numPerPage = 1000;
		if (batchSize > 0 && batchSize < 100) {
			numPerPage = 100;
		} else if (batchSize > 100 && batchSize < 5000) {
			numPerPage = batchSize;
		} else if (batchSize > 5000) {
			numPerPage = 5000;
		} else {
			numPerPage = 1000;
		}

		int pageNum = 1;

		Map<String, TransactionMessage> messageMap = new HashMap<String, TransactionMessage>();
		List<TransactionMessage> recordList = new ArrayList<TransactionMessage>();
		int pageCount = 1;

		PageHelper.startPage(pageNum, numPerPage);
		TransactionMessage r = new TransactionMessage();
		r.setConsumerQueue(queueName); // 队列名称
		r.setAreadlyDead(PublicEnum.YES.name()); // 已经死亡队列
		r.setListPageSortType("ASC"); // 升序排列
		List<TransactionMessage> listPage = transactionMessageMapper
				.listPage(r);
		PageInfo<TransactionMessage> pageBean = new PageInfo<TransactionMessage>(
				listPage);

		recordList = pageBean.getList();
		if (recordList == null || recordList.isEmpty()) {
			log.info("==>recordList is empty");
			return;
		}
		pageCount = pageBean.getPages();
		for (final TransactionMessage message : recordList) {
			messageMap.put(message.getMessageId(), message);
		}

		for (pageNum = 2; pageNum <= pageCount; pageNum++) {
			PageHelper.startPage(pageNum, numPerPage);
			listPage = transactionMessageMapper.listPage(r);
			pageBean = new PageInfo<TransactionMessage>(listPage);

			recordList = pageBean.getList();

			if (recordList == null || recordList.isEmpty()) {
				break;
			}

			for (final TransactionMessage message : recordList) {
				messageMap.put(message.getMessageId(), message);
			}

		}

		recordList = null;
		pageBean = null;

		for (Map.Entry<String, TransactionMessage> entry : messageMap
				.entrySet()) {
			final TransactionMessage message = entry.getValue();

			message.setEditTime(new Date());
			message.setMessageSendTimes(message.getMessageSendTimes() + 1);
			transactionMessageMapper.updateByPrimaryKeySelective(message);

			notifyJmsTemplate.setDefaultDestinationName(message
					.getConsumerQueue());
			notifyJmsTemplate.send(new MessageCreator() {
				public Message createMessage(Session session)
						throws JMSException {
					return session.createTextMessage(message.getMessageBody());
				}
			});
		}

	}

	public PageInfo<TransactionMessage> listPage(
			TransactionMessage rpTransactionMessage) throws CustomException {
		PageHelper.startPage(rpTransactionMessage.getPageNum(),
				rpTransactionMessage.getPageSize());
		List<TransactionMessage> listPage = transactionMessageMapper
				.listPage(rpTransactionMessage);
		return new PageInfo<TransactionMessage>(listPage);
	}

}
