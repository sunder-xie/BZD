package com.bizideal.whoami.vote.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/** 
 * @author  作者 zhu_shangjin: 
 * @date 创建时间：2016年11月29日 下午1:44:47 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Table(name="demo")
public class Demo extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	private Integer demoId;
	private String name;
	public Integer getDemoId() {
		return demoId;
	}
	public void setDemoId(Integer demoId) {
		this.demoId = demoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
