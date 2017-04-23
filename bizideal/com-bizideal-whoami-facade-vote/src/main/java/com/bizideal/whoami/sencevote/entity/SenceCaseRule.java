package com.bizideal.whoami.sencevote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 现场投票规则
 * 
 * @ClassName SenceCaseRule
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public class SenceCaseRule extends BaseEntity {

	private static final long serialVersionUID = -5090213896398432374L;

	/**
	 * 现场投票规则id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 投票id
	 */
	private Integer voteId;

	/**
	 * 规则名称
	 */
	private String ruleName;

	/**
	 * 用户投票数量
	 */
	private Integer user;

	/**
	 * 投票总数
	 */
	private Integer count;

	/**
	 * 是否可以重复投票
	 */
	private Integer repeatNum;

	/**
	 * 规则天数
	 */
	private Integer time;

	public SenceCaseRule() {
		super();
	}

	public SenceCaseRule(Integer id, Integer voteId, String ruleName, Integer user, Integer count, Integer repeat,
			Integer time) {
		super();
		this.id = id;
		this.voteId = voteId;
		this.ruleName = ruleName;
		this.user = user;
		this.count = count;
		this.repeatNum = repeat;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName == null ? null : ruleName.trim();
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(Integer repeat) {
		this.repeatNum = repeat;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SenceCaseRule [id=" + id + ", voteId=" + voteId + ", ruleName=" + ruleName + ", user=" + user
				+ ", count=" + count + ", repeat=" + repeatNum + ", time=" + time + "]";
	}
}