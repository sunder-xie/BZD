package com.bizideal.whoami.message.service;

import java.util.List;

import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * <b>功能说明: 可靠消息接口</b>
 * @author Peter
 */
public interface TransactionMessageService {

	/**
	 * 预存储消息. 
	 * 
	 * @param TransactionMessage 消息体
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int saveMessageWaitingConfirm(TransactionMessage transactionMessage) throws CustomException;
	
	/**
	 * 批量预存储消息. 
	 * 
	 * @param list
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int saveMessageWaitingConfirm(List<TransactionMessage> list) throws CustomException;
	
	/**
	 * 确认并发送消息.
	 * 
	 * @param messageId 消息id
	 * @throws CustomException
	 */
	void confirmAndSendMessage(String messageId) throws CustomException;
	
	/**
	 * 批量确认并发送消息.
	 * 
	 * @param messageIds 消息id
	 * @throws CustomException
	 */
	void confirmAndSendMessage(List<String> messageIds) throws CustomException;
	
	/**
	 * 存储并发送消息.
	 * 
	 * @param TransactionMessage 消息体
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int saveAndSendMessage(TransactionMessage transactionMessage) throws CustomException;

	/**
	 * 直接发送消息.在数据库中不存储
	 * 
	 * @param TransactionMessage 消息体
	 * @throws CustomException
	 */
	void directSendMessage(TransactionMessage transactionMessage) throws CustomException;

	/**
	 * 重发消息.
	 * 
	 * @param transactionMessage 消息体
	 * @throws CustomException
	 */
	void reSendMessage(TransactionMessage transactionMessage) throws CustomException;
	
	/**
	 * 根据messageId重发某条消息.
	 * 
	 * @param messageId 消息id
	 * @throws CustomException
	 */
	void reSendMessageByMessageId(String messageId) throws CustomException;
	
	/**
	 * 将消息标记为死亡消息.
	 * 
	 * @param messageId 消息id
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int setMessageToAreadlyDead(String messageId) throws CustomException;

	/**
	 * 根据消息ID获取消息
	 * 
	 * @param messageId 消息id
	 * @return 消息体
	 * @throws CustomException
	 */
	TransactionMessage getMessageByMessageId(String messageId) throws CustomException;

	/**
	 * 根据消息ID删除消息
	 * 
	 * @param messageId 消息id
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int deleteMessageByMessageId(String messageId) throws CustomException;
	
	/**
	 * 根据消息ID批量删除消息
	 * 
	 * @param messageId 消息id
	 * @return int 返回操作影响行数
	 * @throws CustomException
	 */
	int deleteMessageByMessageId(List<String> messageIds) throws CustomException;
	
	/**
	 * 重发某个消息队列中的全部已死亡的消息.
	 * 
	 * @param queueName 队列名称
	 * @param batchSize 一次重发的数量
	 * @throws CustomException
	 */
	void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws CustomException;
	
	/**
	 * 获取分页数据
	 * 
	 * @param transactionMessage 消息体
	 * @see 可作筛选字段说明 
	 * @see 可作筛选字段说明 status 消息状态 WAITING_CONFIRM("待确认"), SENDING("发送中")
	 * @see 可作筛选字段说明 messageId 消息id
	 * @see 可作筛选字段说明 areadlyDead 消息是否已经死亡 YES("是"), NO("否")
	 * @see 可作筛选字段说明 consumerQueue 队列名称
	 * @see 可作筛选字段说明 createTimeBefore 筛选在什么时间之前的消息
	 * @see 可作筛选字段说明 listPageSortType 根据创建时间排序 ASC("升序"), DESC("降序")
	 * @return
	 * @throws CustomException
	 */
	PageInfo<TransactionMessage> listPage(TransactionMessage transactionMessage) throws CustomException;


}
