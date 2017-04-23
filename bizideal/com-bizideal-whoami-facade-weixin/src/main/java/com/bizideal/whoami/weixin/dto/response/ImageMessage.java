package com.bizideal.whoami.weixin.dto.response;
/**
 * 回复消息--回复媒体消息（图片）
 * @author Administrator
 */
public class ImageMessage extends BaseMessage{

	//通过素材管理中的接口上传多媒体文件，得到的id。
	private MediaId Image;

	public MediaId getImage() {
		return Image;
	}

	public void setImage(MediaId image) {
		Image = image;
	}
}
