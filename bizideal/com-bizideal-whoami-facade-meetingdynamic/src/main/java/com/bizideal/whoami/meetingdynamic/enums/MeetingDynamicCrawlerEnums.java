package com.bizideal.whoami.meetingdynamic.enums;

import java.util.HashMap;
import java.util.Map;


public enum MeetingDynamicCrawlerEnums {

	CRAWLER_INSERT_SUCCESS("6300","插入会议动态成功!"),
	CRAWLER_INSERT_EXCEPTION("6301","插入会议动态失败!"),
	CRAWLER_UPDATE_SUCCESS("6310","更新会议动态成功!"),
	CRAWLER_UPDATE_EXCEPTION("6311","更新会议动态失败!"),
	CRAWLER_DELETE_SUCCESS("6320","删除会议动态成功!"),
	CRAWLER_DELETE_EXCEPTION("6321","删除会议动态失败!"),
	CRAWLER_SELECT_SUCCESS("6330","查询会议动态成功!"),
	CRAWLER_SELECT_EXCEPTION("6331","查询会议动态失败!"),
	CRAWLER_ALLDYNAMIC_SUCCESS("6340","抓取全部会议动态成功!"),
	CRAWLER_ALLDYNAMIC_EXCEPTION("6341","抓取全部会议动态失败!"),
	CRAWLER_CURRENTDYNAMIC_SUCCESS("6350","抓取最新会议动态成功!"),
	CRAWLER_CURRENTDYNAMIC_EXCEPTION("6351","抓取最新会议动态失败!"),
	CRAWLER_INSERTCRAWLER_SUCCESS("6360","插入会议爬虫成功!"),
	CRAWLER_INSERTCRAWLER_EXCEPTION("6361","插入会议爬虫失败!"),
	CRAWLER_INSERTCRAWLER_REPEAT("6362","插入会议爬虫重复!"),
	CRAWLER_INSERTCRAWLER_NOTOPEN("6363","该爬虫功能暂未开放，请联系管理员!"),
	SYSTEM_EXCEPTION("6374","系统异常，请稍后!");
	
	private String code;
	private String msg;
	MeetingDynamicCrawlerEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public MeetingDynamicCrawlerEnums getEnum(String value){
		MeetingDynamicCrawlerEnums meetingInfoEnum=null;
		MeetingDynamicCrawlerEnums[] ary=MeetingDynamicCrawlerEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getCode()));
			map.put("code", ary[num].getCode());
			map.put("msg", ary[num].getMsg());
			enumMap.put(key, map);
		}
		return meetingInfoEnum;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
