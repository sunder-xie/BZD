package com.bizideal.whoami.facade.meeting.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName MeetingInfoEnum
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月6日
 */
public enum MeetingInfoEnum {

	MEETINGINFO_INSERT_SUCCESS("6300","插入主会议成功。"),
	MEETINGINFO_INSERT_EXCEPTION("6301","插入主会议异常。"),
	MEETINGINFO_INSERT_NOHALL("6302","会议厅信息不合法。"),
	MEETINGINFO_INSERTSUB_SUCCESS("6310","插入子会议成功。"),
	MEETINGINFO_INSERTSUB_EXCEPTION("6311","插入子会议异常。"),
	MEETINGINFO_INSERTSUB_NOHALL("6312","会议厅信息不合法。"),
	MEETINGINFO_INSERTSUB_NOPARENTID("6312","该子会议的主会议存在异常"),
	MEETINGINFO_UPDATE_SUCCESS("6320","修改会议成功。"),
	MEETINGINFO_UPDATE_EXCEPTION("6321","修改会议异常。"),
	MEETINGINFO_DELETE_SUCCESS("6340","删除主会议成功。"),
	MEETINGINFO_DELETE_EXCEPTION("6341","删除主会议异常。"),
	MEETINGINFO_DELETESUB_SUCCESS("6350","删除子会议成功。"),
	MEETINGINFO_DELETESUB_EXCEPTION("6351","删除子会议异常。"),
	MEETINGINFO_SELECT_SUCCESS("6360","查询主会议成功。"),
	MEETINGINFO_SELECT_NOREPEAT("6370","主会议名称不重复。"),
	MEETINGINFO_SELECT_EXCEPTION("6371","查询主会议异常。"),
	MEETINGINFO_SELECT_USERISNULL("6374","查询主会议异常，用户ID不能为空。"),
	MEETINGINFO_SELECTPOP_EXCEPTION("6372","查询热门会议异常。"),
	MEETINGINFO_SELECT_REPEAT("6373","主会议名称重复。"),
	MEETINGINFO_SELECTSUB_SUCCESS("6380","查询子会议成功。"),
	MEETINGINFO_SELECTSUB_EXCEPTION("6381","查询子会议异常。"),
	MEETINGINFO_SYSTEM_EXCEPTION("6391","系统异常，请稍后。");
	
	private String code;
	private String msg;
	
	
	MeetingInfoEnum(String code, String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public MeetingInfoEnum getEnum(String value){
		MeetingInfoEnum meetingInfoEnum=null;
		MeetingInfoEnum[] ary=MeetingInfoEnum.values();
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
