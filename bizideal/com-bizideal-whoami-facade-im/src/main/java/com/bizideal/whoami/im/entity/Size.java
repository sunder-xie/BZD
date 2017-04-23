package com.bizideal.whoami.im.entity;

import java.io.Serializable;

public class Size implements Serializable{

	private static final long serialVersionUID = 4511721224240481725L;
	
	private String width;
	private String height;

	public Size() {
	}

	public Size(String width, String height) {
		this.width = width;
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Size [width=" + width + ", height=" + height + "]";
	}
}
