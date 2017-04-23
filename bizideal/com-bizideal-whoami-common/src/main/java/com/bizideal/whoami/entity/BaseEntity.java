package com.bizideal.whoami.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonUnwrapped;

/** 
 * @author  作者 zsj
 * @date 创建时间：2016年11月29日 上午10:50:04 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1449875354570226204L;
	//頁碼
	@Transient

	protected Integer pageNum;
//	每頁多少條 
	@Transient
	
	protected Integer pageSize;
	@JsonUnwrapped
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@JsonUnwrapped
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
