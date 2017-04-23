package com.bizideal.whoami.entity;

import java.util.Date;

public class TestDemo {

	private String name;
	private Date time;
	public TestDemo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestDemo(String name, Date time) {
		super();
		this.name = name;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TestDemo [name=" + name + ", time=" + time + "]";
	}
	
}
