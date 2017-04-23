package com.bizideal.whoami.message.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.message.entity.TransactionMessage;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月9日 下午12:47:41
 * @version 1.0
 */
public interface TransactionMessageMapper extends Mapper<TransactionMessage> {

	TransactionMessage getByMessageId(String messageId);

	List<TransactionMessage> getByMessageIds(List<String> messageIds);

	int deleteByMessageId(String messageId);

	int deleteByMessageIds(List<String> messageIds);

	List<TransactionMessage> listPage(TransactionMessage transactionMessage);

	int insertList(List<TransactionMessage> list);
}
