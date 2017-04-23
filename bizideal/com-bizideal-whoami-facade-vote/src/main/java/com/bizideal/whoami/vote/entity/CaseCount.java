package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 案例投票总数
 * 
 * @ClassName CaseCount
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public class CaseCount extends BaseEntity {

	private static final long serialVersionUID = 6868649114661908854L;

	/**
	 * 案例总数id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 案例id
	 */
	private Integer caseId;

	/**
	 * 总数
	 */
	private Integer number;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	public CaseCount() {
		super();
	}

	public CaseCount(Integer id, Integer caseId, Integer number, Long updateTime) {
		super();
		this.id = id;
		this.caseId = caseId;
		this.number = number;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
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
		return "CaseCount [id=" + id + ", caseId=" + caseId + ", number=" + number + ", updateTime=" + updateTime + "]";
	}
}