package com.bizideal.whoami.message.scheduled.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.message.biz.MessageBiz;
import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.message.enums.MessageStatusEnum;
import com.bizideal.whoami.message.enums.PublicEnum;
import com.bizideal.whoami.message.scheduled.MessageScheduled;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.utils.PublicConfigUtil;
import com.github.pagehelper.PageInfo;


/**
 * 消息定时器接口实现
 *
 * 
 * @author：shenjialong
 */
@Component("messageScheduled")
public class MessageScheduledImpl implements MessageScheduled {
	
	private static final Log log = LogFactory.getLog(MessageScheduledImpl.class);

	@Autowired
	private TransactionMessageService rpTransactionMessageService;
	@Autowired
	private MessageBiz messageBiz;

	/**
	 * 处理状态为“待确认”但已超时的消息.
	 */
	public void handleWaitingConfirmTimeOutMessages() {
		try {
			int maxHandlePageCount = 3; // 一次最多处理页数
		
			TransactionMessage transactionMessage = new TransactionMessage();
			//从第一页开始
			transactionMessage.setPageNum(1);
			// 每页条数
			transactionMessage.setPageSize(2000);
			// 获取配置的开始处理的时间
			transactionMessage.setCreateTimeBefore(getCreateTimeBefore());
			// 取状态为发送中的消息
			transactionMessage.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
			// 分页查询的排序方式，正向排序
			transactionMessage.setListPageSortType("ASC");
			
			Map<String, TransactionMessage> messageMap = getMessageMap(maxHandlePageCount, transactionMessage);

			messageBiz.handleWaitingConfirmTimeOutMessages(messageMap);
			
		} catch (Exception e) {
			log.error("处理[waiting_confirm]状态的消息异常" + e);
		}
	}
	
	
	/**
	 * 处理状态为“发送中”但超时没有被成功消费确认的消息
	 */
	public void handleSendingTimeOutMessage() {
		try {

			int maxHandlePageCount = 3; // 一次最多处理页数

			TransactionMessage transactionMessage = new TransactionMessage();
			//从第一页开始
			transactionMessage.setPageNum(1);
			// 每页条数
			transactionMessage.setPageSize(2000);
			// 获取配置的开始处理的时间
			transactionMessage.setCreateTimeBefore(getCreateTimeBefore());
			// 取状态为发送中的消息
			transactionMessage.setStatus(MessageStatusEnum.SENDING.name());
			// 取存活的发送中消息
			transactionMessage.setAreadlyDead(PublicEnum.NO.name());
			// 分页查询的排序方式，正向排序
			transactionMessage.setListPageSortType("ASC");
			
			Map<String, TransactionMessage> messageMap = getMessageMap(maxHandlePageCount, transactionMessage);
			
			messageBiz.handleSendingTimeOutMessage(messageMap);
		} catch (Exception e) {
			log.error("处理发送中的消息异常" + e);
		}
	}
	
	/**
	 * 根据分页参数及查询条件批量获取消息数据.
	 * @param numPerPage 每页记录数.
	 * @param maxHandlePageCount 最多获取页数.
	 * @param paramMap 查询参数.
	 * @return
	 */
	private Map<String, TransactionMessage> getMessageMap(int maxHandlePageCount, TransactionMessage transactionMessage){
		
		int pageNum = 1; // 当前页
		
		Map<String, TransactionMessage> messageMap = new HashMap<String, TransactionMessage>(); // 转换成map
		int pageCount = 1; // 总页数
		
		log.info("==>pageNum:" + transactionMessage.getPageNum() + ", numPerPage:" + transactionMessage.getPageSize());
		PageInfo<TransactionMessage> listPage = rpTransactionMessageService.listPage(transactionMessage);
		List<TransactionMessage> recordList = listPage.getList();
		

		if (recordList == null || recordList.isEmpty()) {
			log.info("==>recordList is empty");
			return messageMap;
		}
		log.info("==>now page size:" + recordList.size());
		
		for (TransactionMessage message : recordList) {
			messageMap.put(message.getMessageId(), message);
		}
		
		pageCount = listPage.getPages(); // 总页数(可以通过这个值的判断来控制最多取多少页)
		log.info("==>pageCount:" + pageCount);
		if (pageCount > maxHandlePageCount){
			pageCount = maxHandlePageCount;
			log.info("==>set pageCount:" + pageCount);
		}

		for (pageNum = 2; pageNum <= pageCount; pageNum++) {
			log.info("==>pageNum:" + pageNum + ", numPerPage:" + transactionMessage.getPageSize());
			transactionMessage.setPageNum(pageNum);
			listPage = rpTransactionMessageService.listPage(transactionMessage);
			recordList = listPage.getList();
			if (recordList == null || recordList.isEmpty()) {
				break;
			}
			log.info("==>now page size:" + recordList.size());
			
			for (TransactionMessage message : recordList) {
				messageMap.put(message.getMessageId(), message);
			}
		}
		
		recordList = null;
		listPage = null;
		
		return messageMap;
	}

	/**
	 * 获取配置的开始处理的时间
	 * 
	 * @return
	 */
	private String getCreateTimeBefore() {
		String duration = PublicConfigUtil.readConfig("message.handle.duration");
		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
		Date date = new Date(currentTimeInMillis - Integer.valueOf(duration) * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
}
