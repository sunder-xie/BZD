package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 案例投票主题信息
 * 
 * @ClassName CaseVoteThemeInfo
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
public class CaseVoteThemeInfo extends BaseEntity {

	private static final long serialVersionUID = -6453105848496641080L;

	/**
	 * 案例投票主题id
	 */
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 投票id
	 */
	private Integer voteId;

	/**
	 * 主题名称
	 */
	private String name;

	/**
	 * 备注
	 */
	private String dsp;

	/**
	 * 主题类型
	 */
	private Integer type;

	public CaseVoteThemeInfo() {
		super();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CaseVoteThemeInfo(Integer id, Integer voteId, String name, String dsp, Integer type) {
		super();
		this.id = id;
		this.voteId = voteId;
		this.name = name;
		this.dsp = dsp;
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDsp() {
		return dsp;
	}

	public void setDsp(String dsp) {
		this.dsp = dsp == null ? null : dsp.trim();
	}

	@Override
	public String toString() {
		return "CaseVoteThemeInfo [id=" + id + ", voteId=" + voteId + ", name=" + name + ", dsp=" + dsp + ", type="
				+ type + "]";
	}
}