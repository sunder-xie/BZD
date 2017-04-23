package com.bizideal.whoami.signup.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午10:39:32
 * @version 1.0 饮食基础类
 */
@Table(name = "diet_info")
public class DietInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dietId; // 自增id
	private int hallId; // 会议厅id
	private int meeId;// 主会议id
	private String dietDisp;// 备注

	public DietInfo() {
		super();
	}

	public DietInfo(int dietId, int hallId, int meeId, String dietDisp) {
		super();
		this.dietId = dietId;
		this.hallId = hallId;
		this.meeId = meeId;
		this.dietDisp = dietDisp;
	}

	public int getDietId() {
		return dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int getMeeId() {
		return meeId;
	}

	public void setMeeId(int meeId) {
		this.meeId = meeId;
	}

	public String getDietDisp() {
		return dietDisp;
	}

	public void setDietDisp(String dietDisp) {
		this.dietDisp = dietDisp;
	}

	@Override
	public String toString() {
		return "DietInfo [dietId=" + dietId + ", hallId=" + hallId + ", meeId="
				+ meeId + ", dietDisp=" + dietDisp + "]";
	}

}
