package com.bizideal.whoami.knowledgebase.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bizideal.whoami.entity.BaseOpEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月22日 下午3:05:52
 * @version 1.0 知识库资料实体类
 */
@Table(name = "knowledge_file")
public class KnowledgeFile extends BaseOpEntity {
	/**
	 * 文件id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 文件名称
	 */
	private String name;

	/**
	 * 分类id
	 */
	private Integer typeId;

	/**
	 * 会议id
	 */
	private Integer meeId;

	/**
	 * 会议名称
	 */
	@Transient
	private String meeName;

	/**
	 * URL地址
	 */
	private String url;

	/**
	 * 后缀名
	 */
	private String suffix;

	/**
	 * 是否直接下载，0是直接打开，1是下载
	 */
	private String isDown;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 真实姓名
	 */
	@Transient
	private String realName;

	/**
	 * 收藏时间
	 */
	@Transient
	private Long collectionTime;

	/**
	 * 是否已经收藏
	 */
	@Transient
	private boolean isCollected;

	/**
	 * 分类路径
	 */
	@Transient
	private String typeUrl;

	private static final long serialVersionUID = 1L;

	public KnowledgeFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KnowledgeFile(Integer id, String name, Integer typeId, Integer meeId, String meeName, String url,
			String suffix, String isDown, String dsp, String realName, Long collectionTime, boolean isCollected) {
		super();
		this.id = id;
		this.name = name;
		this.typeId = typeId;
		this.meeId = meeId;
		this.meeName = meeName;
		this.url = url;
		this.suffix = suffix;
		this.isDown = isDown;
		this.dsp = dsp;
		this.realName = realName;
		this.collectionTime = collectionTime;
		this.isCollected = isCollected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getMeeId() {
		return meeId;
	}

	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

	public String getMeeName() {
		return meeName;
	}

	public void setMeeName(String meeName) {
		this.meeName = meeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getIsDown() {
		return isDown;
	}

	public void setIsDown(String isDown) {
		this.isDown = isDown == null ? null : isDown.trim();
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Long collectionTime) {
		this.collectionTime = collectionTime;
	}

	public boolean getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public String getTypeUrl() {
		return typeUrl;
	}

	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", typeId=").append(typeId);
		sb.append(", meeId=").append(meeId);
		sb.append(", meeName=").append(meeName);
		sb.append(", url=").append(url);
		sb.append(", suffix=").append(suffix);
		sb.append(", isDown=").append(isDown);
		sb.append(", createUser=").append(createUser);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateUser=").append(updateUser);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", realName=").append(realName);
		sb.append(", collectionTime=").append(collectionTime);
		sb.append(", isCollected=").append(isCollected);
		sb.append(", delFlag=").append(delFlag);
		sb.append(", dsp=").append(dsp);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}