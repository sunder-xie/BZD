package com.bizideal.whoami.facade.hotel.enums;

import java.util.HashMap;
import java.util.Map;

public enum HotelEnums {

	HOTELINFO_HOTELIDNULL("6391","酒店id不能为空"),
	HOTELINFO_HOTELIDERROR("6392","酒店id错误"),
	HOTELINFO_MEETINGIDNULL("6393","会议id不能为空"),
	HOTELINFO_MEETINGIDERROR("6393","会议id错误"),
	HOTELINFO_HOTELNAMENULL("6394","会议名称不能为空"),
	HOTELUSERLINK_HUIDNULL("6395","HUId不能为空"),
	HOTELUSERLINK_HUIDERROR("6396","HUId错误"),
	ROOMSPARERESERVEDNUM_NOT_ENOUGH("6397","备用房间数不足"),
	ROOMNORMALRESERVEDNUM_NOT_ENOUGH("6397","普通房间数不足"),
	
	HOTELINFO_INSERT_SUCCESS("6370","酒店插入成功"),
	HOTELINFO_INSERT_EXCEPTION("6371","酒店插入失败"),
	HOTELINFO_INSERT_NOREPEAT("6370","酒店名称不重复"),
	HOTELINFO_INSERT_REPEAT("6372","酒店名重复"),
	HOTELINFO_UPDATE_SUCCESS("6370","酒店更新成功"),
	HOTELINFO_UPDATE_EXCEPTION("6373","酒店更新失败"),
	HOTELINFO_DELETE_SUCCESS("6370","酒店删除成功"),
	HOTELINFO_DELETE_EXCEPTION("6374","酒店删除失败"),
	HOTELINFO_SELECT_SUCCESS("6370","酒店查询成功"),
	HOTELINFO_SELECT_EXCEPTION("6375","酒店查询失败"),
	
	HOTELUSERLINK_INSERT_SUCCESS("6380","酒店用户关联插入成功"),
	HOTELUSERLINK_INSERT_EXCEPTION("6381","酒店用户关联插入失败"),
	HOTELUSERLINK_UPDATE_SUCCESS("6380","酒店用户关联更新成功"),
	HOTELUSERLINK_UPDATE_EXCEPTION("6382","酒店用户关联更新失败"),
	HOTELUSERLINK_DELETE_SUCCESS("6380","酒店用户关联删除成功"),
	HOTELUSERLINK_DELETE_EXCEPTION("6383","酒店用户关联删除失败"),
	HOTELUSERLINK_SELECT_SUCCESS("6380","酒店用户关联查询成功"),
	HOTELUSERLINK_SELECT_EXCEPTION("6384","酒店用户关联查询失败"),
	
	ROOMTYPE_INSERT_SUCCESS("6350","房间类型插入成功"),
	ROOMTYPE_INSERT_EXCEPTION("6351","房间类型插入失败"),
	ROOMTYPE_INSERT_REPEAT("6352","房间类型名重复"),
	ROOMTYPE_UPDATE_SUCCESS("6350","房间类型更新成功"),
	ROOMTYPE_UPDATE_EXCEPTION("6353","房间类型更新失败"),
	ROOMTYPE_UPDATE_REPEAT("6354","房间类型更新房间类型名重复"),
	ROOMTYPE_DELETE_EXCEPTION("6355","房间类型删除失败"),
	ROOMTYPE_DELETE_SUCCESS("6350","房间类型删除成功"),
	ROOMTYPE_SELECT_EXCEPTION("6356","房间类型查询失败"),
	ROOMTYPE_SELECT_IDNULL("6357","房间类型查询房间类型ID不能为空"),
	ROOMTYPE_SELECT_NAMENULL("6358","房间类型查询房间类型名不能为空"),
	ROOMTYPE_SELECT_SUCCESS("6350","房间类型查询成功"),
	
	ROOMINFO_INSERT_SUCCESS("6360","房间信息插入成功"),
	ROOMINFO_INSERT_EXCEPTION("6361","房间信息插入失败"),
	ROOMINFO_INSERT_REPEAT("6362","房间信息插入重复"),
	ROOMINFO_UPDATE_SUCCESS("6360","房间信息更新成功"),
	ROOMINFO_UPDATE_EXCEPTION("6363","房间信息更新失败"),
	ROOMINFO_ADJUST_EXCEPTION("6363","已经预定人数超过调整的现有房间数量，调整失败"),
	ROOMINFO_ADJUST_SUCCESS("6360","房间数量调整调整成功"),
	ROOMINFO_UPDATE_TYPEREPEAT("6363","房间信息更新房间类型不能重复"),
	ROOMINFO_UPDATE_IDNULL("6364","房间信息更新房间ID不能为空"),
	ROOMINFO_DELETE_IDNULL("6365","房间信息ID不能为空"),
	ROOMINFO_ADJUST_IDNULL("6365","房间信息ID不能为空"),
	ROOMINFO_DELETE_EXCEPTION("6366","房间信息删除失败"),
	ROOMINFO_DELETE_SUCCESS("6360","房间信息删除成功"),
	ROOMINFO_SELECT_EXCEPTION("6367","房间信息查询失败"),
	ROOMINFO_SELECT_IDNULL("6368","房间信息查询房间信息ID不能为空"),
	ROOMINFO_SELECT_ROOMTYPENULL("6369","房间信息查询房间类型和酒店ID不能为空"),
	ROOMINFO_SELECT_HOTELIDNULL("6369","房间信息查询酒店ID不能为空"),
	ROOMINFO_SELECT_SUCCESS("6360","房间信息查询成功"),
	
	
	SYSTEM_EXCEPTION("6399","系统异常");
	
	private String code;
	private String msg;
	HotelEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public HotelEnums getEnum(String value) {
		HotelEnums meetingDynamicEnums = null;
		HotelEnums[] ary = HotelEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getCode()));
			map.put("code", ary[num].getCode());
			map.put("msg", ary[num].getMsg());
			enumMap.put(key, map);
		}
		return meetingDynamicEnums;
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}
}
