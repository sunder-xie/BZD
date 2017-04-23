package com.bizideal.whoami.sencevote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 现场案例投票总数
 * 
 * @ClassName SenceCaseCount
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public class SenceCaseCount extends BaseEntity {

	private static final long serialVersionUID = -8491583357662165623L;

	/**
	 * 现场案例投票总数id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 现场案例id
	 */
	private Integer senceCaseId;

	/**
	 * 数量
	 */
	private Integer number;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	public SenceCaseCount() {
		super();
	}

	public SenceCaseCount(Integer id, Integer senceCaseId, Integer number, Long updateTime) {
		super();
		this.id = id;
		this.senceCaseId = senceCaseId;
		this.number = number;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSenceCaseId() {
		return senceCaseId;
	}

	public void setSenceCaseId(Integer senceCaseId) {
		this.senceCaseId = senceCaseId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SenceCaseCount [id=" + id + ", senceCaseId=" + senceCaseId + ", number=" + number + ", updateTime="
				+ updateTime + "]";
	}
}