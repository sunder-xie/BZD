package com.bizideal.whoami.facade.meeting.enums;

import java.util.HashMap;
import java.util.Map;

import com.bizideal.whoami.enums.exception.ExceptionModelEums;

/**
 * 
 * 会议异常
 * @author ct
 * 2017年3月9日
 * 结构形式      类名_方法名_编号_error  其中编号00为服务异常       
 * 例如 1100:代表第一个类 类名1 第一个方法方法名1 参数路由00
 */
public enum MeettingExceptionEnums {
	
	//HallLinkUserBizImpl类  会议厅关注的用户
	HallLinkUserBizImpl_save_00_ERROR               ("10100", "会议厅关注的用户,新增,服务异常"),
	HallLinkUserBizImpl_save_01_ERROR               ("10101", "会议厅关注的用户,新增,新增插入操作不成功"),	
	HallLinkUserBizImpl_save_02_ERROR               ("10102", "会议厅关注的用户,新增,传入参数为null"),	
	
	HallLinkUserBizImpl_saveSelective_00_ERROR      ("10200", "会议厅关注的用户,修改,服务异常"),
	HallLinkUserBizImpl_countUserIdByHallId_00_ERROR("10300", "会议厅关注的用户,统计某个会议厅的关注人数,服务异常"),
	HallLinkUserBizImpl_ListUserIdByHallId_00_ERROR ("10400", "会议厅关注的用户,会议厅的粉丝信息,服务异常"),
	HallLinkUserBizImpl_ListHallByUserId_00_ERROR   ("10500", "会议厅关注的用户,我关注的会议厅,服务异常"),
	
	
	//MeetingGroupBizImpl类   会议群
	MeetingGroupBizImpl_insert_00_ERROR       ("20100", "会议群,插入会议群,服务异常"),
	MeetingGroupBizImpl_deleteById_00_ERROR   ("20200", "会议群,根据会议id删除会议群,服务异常"),
	MeetingGroupBizImpl_selectByMeeId_00_ERROR("20300", "会议群,根据会议id查找会议群,服务异常"),
	
	
	//MeetingHallBizImpl类  会议厅业务层
	MeetingHallBizImpl_saveSelective_00_ERROR           ("30100", "会议厅业务层,新增会议厅,服务异常"),
	MeetingHallBizImpl_queryPageListByUserId_00_ERROR   ("30200", "会议厅业务层,根据用户编号查询会议厅,服务异常"),
	MeetingHallBizImpl_queryByUserIdMeetingHall_00_ERROR("30300", "会议厅业务层,用户最后离开的会议厅,服务异常"),
	MeetingHallBizImpl_delLogicMeetingHall_00_ERROR     ("30400", "会议厅业务层,根据主键逻辑删除会议厅,服务异常"),
	MeetingHallBizImpl_queryByUserIdsMeeHall_00_ERROR   ("30500", "会议厅业务层,查询会议厅信息,服务异常"),
	MeetingHallBizImpl_listUserFollowHotHall_00_ERROR   ("30600", "会议厅业务层,热门会议厅,服务异常"),
	MeetingHallBizImpl_queryByHallName_00_ERROR         ("30700", "会议厅业务层,根据会议厅名查询会议厅信息,服务异常"),
	MeetingHallBizImpl_updateMeetingHall_00_ERROR       ("30800", "会议厅业务层,修改会议厅信息,服务异常"),
	
	
	//MeetingInfoBizImpl类
	MeetingInfoBizImpl_insertMeetingInfo_00_ERROR                   ("40100", "会议信息业务层,插入一条会议记录,服务异常"), 
	MeetingInfoBizImpl_insertMeetingInfo_01_ERROR					("40101", "会议信息业务层,插入一条会议记录,会议厅信息不合法。"),
	MeetingInfoBizImpl_insertMeetingInfo_02_ERROR					("40102", "会议信息业务层,插入一条会议记录,主会议名称重复。"),
	MeetingInfoBizImpl_insertMeetingInfo_03_ERROR         			("40103", "会议用户聊天群,创建群并加入群成员,调用im创建群失败"), 
	MeetingInfoBizImpl_updateMeetingInfo_00_ERROR                   ("40200", "会议信息业务层,更新一条会议记录,服务异常"),
	MeetingInfoBizImpl_deleteMeetingInfoByIds_00_ERROR              ("40300", "会议信息业务层,删除多条主会议记录,服务异常"),
	MeetingInfoBizImpl_deleteMeetingInfoById_00_ERROR               ("40400", "会议信息业务层,根据主会议ID删除主会议信息,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfo_00_ERROR                   ("40500", "会议信息业务层,根据条件查询会议信息,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfoByHallId_00_ERROR           ("40600", "会议信息业务层,根据会议厅ID查询主会议，隐含条件：DelFlag=0,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfoByName_00_ERROR             ("40700", "会议信息业务层,根据会议的名称来查询会议,服务异常"),
	MeetingInfoBizImpl_selectPopMeetingInfo_00_ERROR                ("40800", "会议信息业务层,查询热门主会议,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfoById_00_ERROR               ("40900", "会议信息业务层,根据会议ID查询会议信息,服务异常"),
	MeetingInfoBizImpl_selectSignUpMeetingInfo_00_ERROR             ("41000", "会议信息业务层,根据用户ID查询用户报名的主会议信息,服务异常"),
	MeetingInfoBizImpl_selectFollowMeetingInfo_00_ERROR             ("41100", "会议信息业务层,根据用户ID查询用户关注的主会议信息,服务异常"),
	MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdOver_00_ERROR  ("41200", "会议信息业务层,根据用户ID查询当前关注过的会议信息（已结束的）,服务异常"),
	MeetingInfoBizImpl_selectFocusMeetingInfoByUserIdFuture_00_ERROR("41300", "会议信息业务层,根据用户ID查询当前关注过的会议信息（未开始的）,服务异常"),
	MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdFuture_00_ERROR ("41400", "会议信息业务层,根据用户ID查询当前参加的会议信息（未开始的）,服务异常"),
	MeetingInfoBizImpl_selectJoinMeetingInfoByUserIdOver_00_ERROR   ("41500", "会议信息业务层,根据用户ID查询当前参加的会议信息（已开始的）,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfoByListFuture_00_ERROR       ("41600", "会议信息业务层,根据会议ID list查询的会议信息（未结束的）,服务异常"),
	MeetingInfoBizImpl_selectMeetingInfoByListOver_00_ERROR         ("41700", "会议信息业务层,根据会议ID list查询的会议信息（已结束的）,服务异常"),
	
	
	//MeetingLinkUserBizImpl类 用户关注的会议
	MeetingLinkUserBizImpl_countMeetingFollowByMeeId_00_ERROR("50100", "用户关注的会议,根据会议ID查询会议粉丝数,服务异常"), 
	MeetingLinkUserBizImpl_save_00_ERROR                     ("50200", "用户关注的会议,新增,服务异常"), 
	MeetingLinkUserBizImpl_saveSelective_00_ERROR            ("50300", "用户关注的会议,修改,服务异常"), 
	
	
	//SubMeetingInfoBizImpl类 子会议信息业务层
	SubMeetingInfoBizImpl_insertSubMeetingInfo_00_ERROR          ("60100", "子会议信息业务层,插入一条子会议记录,服务异常"),  
	SubMeetingInfoBizImpl_deleteSubMeetingInfoById_00_ERROR      ("60200", "子会议信息业务层,删除一条子会议记录,服务异常"),  
	SubMeetingInfoBizImpl_deleteSubMeetingInfoByIdReal_00_ERROR  ("60300", "子会议信息业务层,物理删除一条子会议数据,服务异常"),  
	SubMeetingInfoBizImpl_deleteSubMeetingInfoByIds_00_ERROR     ("60400", "子会议信息业务层,根据会议ID字符串来逻辑删除子会议信息,服务异常"),  
	SubMeetingInfoBizImpl_selectSubMeetingInfoByParentId_00_ERROR("60500", "子会议信息业务层,根据主会议的ID查询主会议下的子会议信息,服务异常"),  
	SubMeetingInfoBizImpl_selectSubMeetingInfoByList_00_ERROR    ("60600", "子会议信息业务层,根据会议ID list查询的子会议信息（已开始的）,服务异常"),  
	SubMeetingInfoBizImpl_updateSubMeetingInfo_00_ERROR          ("60700", "子会议信息业务层,更新子会议信息,服务异常"),  
	SubMeetingInfoBizImpl_selectSubMeetingInfoById_00_ERROR      ("60800", "子会议信息业务层,根据子会议ID查询子会议信息,服务异常"),  
	SubMeetingInfoBizImpl_selectPageSubMeetingInfoByList_00_ERROR("60900", "子会议信息业务层,根据会议ID list分页查询的子会议信息,服务异常"),  
	SubMeetingInfoBizImpl_isMeetingStartTimeOut_00_ERROR("61000", "主会议判断子会议开始时间是否有提前于主会议开始时间"),  
	SubMeetingInfoBizImpl_isMeetingEndTimeOut_00_ERROR("61100", "主会议判断子会议结束时间是否有超过主会议结束时间"),  
	SubMeetingInfoBizImpl_getFristStartTimeByParentMeeId_00_ERROR("61200", "根据主会议查询子会议最早开始时间"),  
	SubMeetingInfoBizImpl_getLastEndTimeByParentMeeId_00_ERROR("61300", "根据主会议查询子会议最晚结束时间"),  

	
	//MeetingUserGroupServiceImpl类 用户聊天群
	MeetingUserGroupServiceImpl_insertCreate_00_ERROR         ("70100", "会议用户聊天群,创建群并加入群成员,服务异常"), 
	MeetingUserGroupServiceImpl_insertCreate_01_ERROR         ("70101", "会议用户聊天群,创建群并加入群成员,调用im创建群失败"), 
	MeetingUserGroupServiceImpl_insertAddUsers_00_ERROR       ("70200", "会议用户聊天群,批量加入群成员,服务异常"),
	MeetingUserGroupServiceImpl_insertAddUsers_01_ERROR       ("70201", "会议用户聊天群,批量加入群成员,空参数"), 	
	MeetingUserGroupServiceImpl_insertAddUsers_02_ERROR       ("70202", "会议用户聊天群,批量加入群成员,调用im添加群成员失败"), 	
	MeetingUserGroupServiceImpl_deleteUsers_00_ERROR          ("70300", "会议用户聊天群,删除群成员,服务异常"), 	
	MeetingUserGroupServiceImpl_deleteUsers_01_ERROR          ("70301", "会议用户聊天群,删除群成员,空参数"), 
	MeetingUserGroupServiceImpl_deleteUsers_02_ERROR          ("70302", "会议用户聊天群,删除群成员,调用im删除群成员失败"), 	
	MeetingUserGroupServiceImpl_selectGroupIds_00_ERROR       ("70400", "会议用户聊天群,查询用户群列表,服务异常"),	
	MeetingUserGroupServiceImpl_selectGroupMembers_00_ERROR   ("70500", "会议用户聊天群,查询群成员列表,服务异常"),	
	MeetingUserGroupServiceImpl_deleteAllGroupMembers_00_ERROR("70600", "会议用户聊天群,解散群,服务异常"), 	
	MeetingUserGroupServiceImpl_deleteAllGroupMembers_01_ERROR("70601", "会议用户聊天群,解散群,空参数"), 	
	MeetingUserGroupServiceImpl_deleteAllGroupMembers_02_ERROR("70602", "会议用户聊天群,解散群,调用im解散群失败"), 	
	MeetingUserGroupServiceImpl_deleteAllGroupMembers_03_ERROR("70603", "会议用户聊天群,解散群,本地删除数据库失败"), 	
	MeetingUserGroupServiceImpl_updateGroupName_00_ERROR      ("70700", "会议用户聊天群,修改群基本信息,服务异常"), 
	MeetingUserGroupServiceImpl_updateGroupName_01_ERROR      ("70701", "会议用户聊天群,修改群基本信息,调用im修改群信息失败"), 
	
	
	
	DATABASE_INSERT_ERROR("9001", "插入异常"),
	DATABASE_DELETE_ERROR("9002", "删除异常"),
	DATABASE_UPDATE_ERROR("9003", "修改异常"),
	DATABASE_SELECT_ERROR("9004", "查询异常")
	;
	private String code;
	private String msg;

	

	public String getCode() {
		return ExceptionModelEums.meetting+code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	

	MeettingExceptionEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static MeettingExceptionEnums getEnum(String code) {
		MeettingExceptionEnums resultEnum = null;
		MeettingExceptionEnums[] enumAry = MeettingExceptionEnums.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getCode().equals(code)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		MeettingExceptionEnums[] ary = MeettingExceptionEnums.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getCode()));
			map.put("code", String.valueOf(ary[num].getCode()));
			map.put("msg", ary[num].getMsg());
			enumMap.put(key, map);
		}
		return enumMap;
	}

//	public static void main(String[] args) {
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errcode);
//		System.out.println(RoleAndMqEnums.ROLEANDMQ_EXCEPTION.errmsg);
//		RoleAndMqEnums r = RoleAndMqEnums.getEnum(6101);
//		System.out.println(RoleAndMqEnums.getEnum(6101).errmsg);
//		System.out.println(toMap());
//	}

}
