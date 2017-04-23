package com.bizideal.whoami.knowledgebase.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午3:05:52
 * @version 1.0 知识库收藏实体类
 */
@Table(name = "knowledge_collection")
public class KnowledgeCollection extends BaseEntity {
	/**
	 * 知识库收藏id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 会议id
	 */
	private Integer meeId;

	/**
	 * 分类链接
	 */
	private String typeUrl;

	/**
	 * 文件id
	 */
	private Integer fileId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 收藏时间
	 */
	private Long collectionTime;

	private static final long serialVersionUID = 1L;

	public KnowledgeCollection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KnowledgeCollection(Integer id, Integer meeId, String typeUrl, Integer fileId, String userId,
			Long collectionTime) {
		super();
		this.id = id;
		this.meeId = meeId;
		this.typeUrl = typeUrl;
		this.fileId = fileId;
		this.userId = userId;
		this.collectionTime = collectionTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public String getTypeUrl() {
		return typeUrl;
	}

	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Long collectionTime) {
		this.collectionTime = collectionTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", meeId=").append(meeId);
		sb.append(", typeUrl=").append(typeUrl);
		sb.append(", fileId=").append(fileId);
		sb.append(", userId=").append(userId);
		sb.append(", collectionTime=").append(collectionTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}