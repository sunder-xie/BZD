package com.bizideal.whoami.im.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName IMMessage
 * @Description TODO(发送消息的实体)
 * @Author Zj.Qu
 * @Date 2017-01-04 13:13:50
 */
@XmlRootElement
public class IMMessage  implements Serializable{

	private static final long serialVersionUID = -657062818649838227L;

	/** 消息类型 **/
	private String type;
	
	/** 文件名 **/
	private String filename;
	
	/** 成功上传视频缩略图返回的UUID **/
	private String thumb;
	
	/** //视频播放长度 **/
	private int length;
	
	/** 成功上传文件后返回的secret **/
	private String secret;
	
	/** 视频文件大小 **/
	private int file_length;
	
	/** 成功上传视频缩略图后返回的secret **/
	private String thumb_secret;
	
	/** 成功上传视频文件返回的UUID **/
	private String url;
	
	/** 文本消息内容 **/
	private String msg;
	
	/** 图片大小 **/
	private Size size;

	public IMMessage() {
	}

	public IMMessage(String type, String filename, String thumb, int length, String secret, int file_length,
			String thumb_secret, String url, String msg, Size size) {
		this.type = type;
		this.filename = filename;
		this.thumb = thumb;
		this.length = length;
		this.secret = secret;
		this.file_length = file_length;
		this.thumb_secret = thumb_secret;
		this.url = url;
		this.msg = msg;
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getFile_length() {
		return file_length;
	}

	public void setFile_length(int file_length) {
		this.file_length = file_length;
	}

	public String getThumb_secret() {
		return thumb_secret;
	}

	public void setThumb_secret(String thumb_secret) {
		this.thumb_secret = thumb_secret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "IMMessage [type=" + type + ", filename=" + filename + ", thumb=" + thumb + ", length=" + length
				+ ", secret=" + secret + ", file_length=" + file_length + ", thumb_secret=" + thumb_secret + ", url="
				+ url + ", msg=" + msg + ", size=" + size + "]";
	}

}
