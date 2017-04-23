package com.bizideal.whoami.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bizideal.whoami.message.entity.TransactionMessage;
import com.bizideal.whoami.message.enums.PublicEnum;
import com.bizideal.whoami.message.service.TransactionMessageService;
import com.bizideal.whoami.utils.StringUtil;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月23日 上午9:19:32
 * @version 1.0
 */
public class MessageJunitTest {

	private ApplicationContext applicationContext = null;
	private TransactionMessageService transactionMessageService = null;
	private ObjectMapper mapper = null;

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/spring-context.xml");
		transactionMessageService = applicationContext
				.getBean(TransactionMessageService.class);
		mapper = new ObjectMapper();
	}

	@Test
	public void testSaveMessageWaitingConfirm() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 预存储消息测试
		ObjectNode node = mapper.createObjectNode();
		node.put("a", "3");
		node.put("b", "2");
		node.put("z", 66);
		TransactionMessage message = new TransactionMessage();
		message.setMessageId(StringUtil.get32UUID());
		message.setMessageBody(mapper.writeValueAsString(node));
		message.setConsumerQueue("testQueue");
		message.setCreater("5876e0c5d4181772b7ebfc20");
		int saveMessageWaitingConfirm = transactionMessageService
				.saveMessageWaitingConfirm(message);
		System.out.println(saveMessageWaitingConfirm);
	}

	@Test
	public void testSaveMessageWaitingConfirmBatch()
			throws JsonGenerationException, JsonMappingException, IOException {
		// 批量预存储消息测试
		ObjectNode node = mapper.createObjectNode();
		node.put("a", "3");
		node.put("b", "2");
		node.put("z", 888);
		TransactionMessage message = new TransactionMessage();
		message.setMessageId(StringUtil.get32UUID());
		message.setMessageBody(mapper.writeValueAsString(node));
		message.setConsumerQueue("testQueue");
		message.setCreater("5876e0c5d4181772b7ebfc20");
		TransactionMessage message1 = new TransactionMessage();
		message1.setMessageId(StringUtil.get32UUID());
		message1.setMessageBody(mapper.writeValueAsString(node));
		message1.setConsumerQueue("testQueue");
		message1.setCreater("5876e0c5d4181772b7ebfc20");
		TransactionMessage message2 = new TransactionMessage();
		message2.setMessageId(StringUtil.get32UUID());
		message2.setMessageBody(mapper.writeValueAsString(node));
		message2.setConsumerQueue("testQueue");
		message2.setCreater("5876e0c5d4181772b7ebfc20");
		List<TransactionMessage> list = new ArrayList<TransactionMessage>();
		list.add(message);
		list.add(message1);
		list.add(message2);
		transactionMessageService.saveMessageWaitingConfirm(list);
	}

	@Test
	public void confirmAndSendMessage() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 确认并发送消息.
		String messageId = "58be6f4df61738186c5cc77b";
		transactionMessageService.confirmAndSendMessage(messageId);
		System.out.println("ok");
	}

	@Test
	public void saveAndSendMessage() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 存储并发送消息.
		ObjectNode node = mapper.createObjectNode();
		node.put("a", "3");
		node.put("b", "2");
		node.put("c", "1");
		node.put("d", "d");
		TransactionMessage message = new TransactionMessage();
		message.setMessageId(StringUtil.get32UUID());
		message.setMessageBody(mapper.writeValueAsString(node));
		message.setConsumerQueue("testQueue");
		int saveMessageWaitingConfirm = transactionMessageService
				.saveAndSendMessage(message);
		System.out.println(saveMessageWaitingConfirm);
	}

	@Test
	public void directSendMessage() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 直接发送消息.不存储在数据库中
		ObjectNode node = mapper.createObjectNode();
		node.put("a", "3");
		node.put("b", "2");
		node.put("c", "1");
		node.put("d", "123");
		TransactionMessage message = new TransactionMessage();
		message.setMessageId(StringUtil.get32UUID());
		message.setMessageBody(mapper.writeValueAsString(node));
		message.setConsumerQueue("testQueue");
		transactionMessageService.directSendMessage(message);
	}

	@Test
	public void reSendMessage() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 重发消息.
		TransactionMessage message = transactionMessageService
				.getMessageByMessageId("2ebd5bbdaa3a4014ad640bfa88bf2c7d");
		transactionMessageService.reSendMessage(message);
	}

	@Test
	public void reSendMessageByMessageId() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 根据messageId重发某条消息.
		String messageId = "eb81e62333a94ac7ab81e8f1b3843c95";
		transactionMessageService.reSendMessageByMessageId(messageId);
	}

	@Test
	public void getMessageByMessageId() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 根据消息ID获取消息.
		String messageId = "2ebd5bbdaa3a4014ad640bfa88bf2c7d";
		TransactionMessage message = transactionMessageService
				.getMessageByMessageId(messageId);
		System.out.println(message);
	}

	@Test
	public void setMessageToAreadlyDead() throws Exception {
		// 将消息标记为死亡消息.
		String messageId = "2ebd5bbdaa3a4014ad640bfa88bf2c7d";
		int message = 0;
		try {
			message = transactionMessageService
					.setMessageToAreadlyDead(messageId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(message);
	}

	@Test
	public void deleteMessageByMessageId() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 根据消息ID删除消息
		String messageId = "58c0f62610d866032cfad5ab";
		int message = transactionMessageService
				.deleteMessageByMessageId(messageId);
		System.out.println(message);
	}

	@Test
	public void reSendAllDeadMessageByQueueName()
			throws JsonGenerationException, JsonMappingException, IOException {
		// 重发某个消息队列中的全部已死亡的消息.
		transactionMessageService.reSendAllDeadMessageByQueueName(
				"testQueue", 200);
	}

	@Test
	public void listPage() throws JsonGenerationException,
			JsonMappingException, IOException {
		// 获取分页数据
		TransactionMessage r = new TransactionMessage();
		r.setConsumerQueue("testQueue");// 队列名称
		r.setAreadlyDead(PublicEnum.YES.name());// 已经死亡队列
		r.setListPageSortType("desc");// 升序排列
		r.setPageNum(2);
		r.setPageSize(2);
		PageInfo<TransactionMessage> listPage = transactionMessageService
				.listPage(r);
		for (TransactionMessage message : listPage.getList()) {
			System.out.println(message);
		}
	}
}
