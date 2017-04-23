package com.bizideal.whoami.weixin.entity;


import javax.persistence.Id;
import javax.persistence.Table;

import com.bizideal.whoami.entity.BaseEntity;

/**
 * 公众号图片素材
 * @author zhu_shangjin
 * @version 2016年12月22日 上午10:06:47
 */
@Table(name = "wechat_image")
public class WechatImage extends BaseEntity {
	@Id
	// 素材mediaId
	private String mediaId;
    //  图片素材名称
	private String name;
// 图片url
	private String url;
//素材更新时间
	private long updateTime;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	

}
