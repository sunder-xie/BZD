/*
 * ====================================================================
 * 龙果学院： www.roncoo.com （微信公众号：RonCoo_com）
 * 超级教程系列：《微服务架构的分布式事务解决方案》视频教程
 * 讲师：吴水成（水到渠成），840765167@qq.com
 * 课程地址：http://www.roncoo.com/details/7ae3d7eddc4742f78b0548aa8bd9ccdb
 * ====================================================================
 */
package com.bizideal.whoami.message.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseEntity;
import com.bizideal.whoami.utils.StringUtil;

/**
 * 持久化消息实体
 *
 * 
 * @author：shenjialong
 */
@Table(name = "transaction_message")
public class TransactionMessage extends BaseEntity {

	private static final long serialVersionUID = 1757377457814546156L;
	/* 主键ID. */
	@Id
	private String id = StringUtil.get32UUID();

	/* 版本号默认为0 */
	private Integer version = 0;

	/* 状态 MessageStatusEnum */
	private String status;

	/* 创建人. */
	private String creater;

	/* 创建时间. */
	private Date createTime = new Date();

	/* 修改人. */
	private String editor;

	/* 修改时间. */
	private Date editTime;

	/* 描述 */
	private String remark;

	/* 消息id */
	private String messageId;

	/* 消息体 */
	private String messageBody;

	private String messageDataType;

	/* 接收消息的队列 */
	private String consumerQueue;

	/* 消息已发送次数 */
	private Integer messageSendTimes;

	/* 是否已经标记为死亡 YES/NO */
	private String areadlyDead;

	/* 预留字段1 查询条件一般为主键*/
	private String field1;

	/* 预留字段2 查询条件一般为主键*/
	private String field2;

	/* 预留字段3 查询条件一般为主键*/
	private String field3;

	/* 声明排序字段 */
	@Transient
	private String listPageSortType;

	/* 声明时间 */
	@Transient
	private String createTimeBefore;

	public TransactionMessage() {
		super();
	}

	public TransactionMessage(String messageId, String messageBody,
			String consumerQueue) {
		super();
		this.messageId = messageId;
		this.messageBody = messageBody;
		this.consumerQueue = consumerQueue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageDataType() {
		return messageDataType;
	}

	public void setMessageDataType(String messageDataType) {
		this.messageDataType = messageDataType;
	}

	public String getConsumerQueue() {
		return consumerQueue;
	}

	public void setConsumerQueue(String consumerQueue) {
		this.consumerQueue = consumerQueue;
	}

	public Integer getMessageSendTimes() {
		return messageSendTimes;
	}

	public void setMessageSendTimes(Integer messageSendTimes) {
		this.messageSendTimes = messageSendTimes;
	}

	public String getAreadlyDead() {
		return areadlyDead;
	}

	public void setAreadlyDead(String areadlyDead) {
		this.areadlyDead = areadlyDead;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getListPageSortType() {
		return listPageSortType;
	}

	public void setListPageSortType(String listPageSortType) {
		this.listPageSortType = listPageSortType;
	}

	public String getCreateTimeBefore() {
		return createTimeBefore;
	}

	public void setCreateTimeBefore(String createTimeBefore) {
		this.createTimeBefore = createTimeBefore;
	}

	public void addSendTimes() {
		messageSendTimes++;
	}

	@Override
	public String toString() {
		return "TransactionMessage [id=" + id + ", version=" + version
				+ ", status=" + status + ", creater=" + creater
				+ ", createTime=" + createTime + ", editor=" + editor
				+ ", editTime=" + editTime + ", remark=" + remark
				+ ", messageId=" + messageId + ", messageBody=" + messageBody
				+ ", messageDataType=" + messageDataType + ", consumerQueue="
				+ consumerQueue + ", messageSendTimes=" + messageSendTimes
				+ ", areadlyDead=" + areadlyDead + ", field1=" + field1
				+ ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}