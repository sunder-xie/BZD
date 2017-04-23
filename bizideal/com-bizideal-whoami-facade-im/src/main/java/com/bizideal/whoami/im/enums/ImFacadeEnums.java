package com.bizideal.whoami.im.enums;

import java.util.HashMap;
import java.util.Map;

import com.bizideal.whoami.enums.exception.ExceptionModelEums;

/**
 * 
 * 异常枚举
 * @author ct
 * 2017年3月14日
 * 结构形式      类名_方法名_编号_error  其中编号00为服务异常       
 * 例如 1100:代表第一个类 类名1 第一个方法方法名1 参数路由00
 */
public enum ImFacadeEnums {
	
	ChatGroupsServiceImpl_getAllChatgroupids_00_ERROR ("10100","获取所有群的编号"),
	ChatGroupsServiceImpl_updateChatGroup_00_ERROR ("10200","修改群组"),
	ChatGroupsServiceImpl_getGroupDetailsByChatgroupid_00_ERROR ("10300","获取一个或者多个群组的详情"),
	ChatGroupsServiceImpl_creatChatGroups_00_ERROR ("10400","创建群组"),
	ChatGroupsServiceImpl_deleteChatGroups_00_ERROR ("10500","删除群组"),
	ChatGroupsServiceImpl_getAllMemberssByGroupId_00_ERROR ("10600","获取群组中的所有成员"),
	ChatGroupsServiceImpl_addUserToGroup_00_ERROR ("10700","在群组中添加一个人"),
	ChatGroupsServiceImpl_deleteUserFromGroup_00_ERROR ("10800","在群组中减少一个人"),
	ChatGroupsServiceImpl_getJoinedChatgroupsForIMUser_00_ERROR ("10900","获取一个用户参与的所有群组"),
	ChatGroupsServiceImpl_addUsersToGroupBatch_00_ERROR ("11000","群组批量添加成员"),
	ChatGroupsServiceImpl_attornChatGroup_00_ERROR ("11100","转让群组"),
	ChatGroupsServiceImpl_getBlockUsers_00_ERROR ("11200","获取一个群组的所有黑名单"),
	ChatGroupsServiceImpl_addUserToBlock_00_ERROR ("11300","添加用户到一个群组黑名单"),
	ChatGroupsServiceImpl_addUserToBlockBatch_00_ERROR ("11400","添加多个用户到一个群组黑名单"),
	ChatGroupsServiceImpl_deleteUserFromBlock_00_ERROR ("11500","从一个群组的黑名单中移除一个用户"),
	ChatGroupsServiceImpl_deleteUserFromBlockBatch_00_ERROR ("11600","从一个群组的黑名单中移除多个用户"),
	ChatGroupsServiceImpl_deleteUserFromGroupBatch_00_ERROR ("11700","在群组中减少多个人"),
	ChatMessageServiceImpl_getChatMessages_00_ERROR ("20100","获取聊天消息"),
	FilesServiceImpl_mediaUpload_00_ERROR ("30100","图片/语音文件上传"),
	FilesServiceImpl_mediaDownload_00_ERROR ("30200","图片语音文件下载"),
	IMUsersServiceImpl_createNewIMUserSingle_00_ERROR ("40100","注册IM用户[单个]"),
	IMUsersServiceImpl_createNewIMUserBatch_00_ERROR ("40200","注册IM用户[批量]"),
	IMUsersServiceImpl_createNewIMUserBatchGen_00_ERROR ("40300","注册IM用户[批量生成用户然后注册]"),
	IMUsersServiceImpl_getIMUsersByUserName_00_ERROR ("40400","获取IM用户"),
	IMUsersServiceImpl_deleteIMUserByUserName_00_ERROR ("40500","删除IM用户[单个]"),
	IMUsersServiceImpl_deleteIMUserByUsernameBatch_00_ERROR ("40600","删除IM用户[批量]"),
	IMUsersServiceImpl_modifyIMUserPasswordWithAdminToken_00_ERROR ("40700","重置IM用户密码 提供管理员token"),
	IMUsersServiceImpl_addFriendSingle_00_ERROR ("40800","添加好友[单个]"),
	IMUsersServiceImpl_deleteFriendSingle_00_ERROR ("40900","解除好友关系"),
	IMUsersServiceImpl_getFriends_00_ERROR ("41000","查看好友"),
	IMUsersServiceImpl_imUserLogin_00_ERROR ("41100","IM用户登录"),
	IMUsersServiceImpl_getBlacklist_00_ERROR ("41200","获取 IM 用户的黑名单"),
	IMUsersServiceImpl_addBlacklist_00_ERROR ("41300","往 IM 用户的黑名单中加人,多个用户用逗号隔开"),
	IMUsersServiceImpl_delBlackList_00_ERROR ("41400","从 IM 用户的黑名单中减人"),
	IMUsersServiceImpl_userStatus_00_ERROR ("41500","查看用户状态"),
	IMUsersServiceImpl_offlineMsgCount_00_ERROR ("41600","查询离线消息数"),
	IMUsersServiceImpl_offlineMsgStatusByMsgId_00_ERROR ("41700","查询某条离线消息状态"),
	IMUsersServiceImpl_deactivateUser_00_ERROR ("41800","用户账号禁用"),
	IMUsersServiceImpl_activateUser_00_ERROR ("41900","用户账号解禁"),
	IMUsersServiceImpl_disconnectUser_00_ERROR ("42000","强制用户下线"),
	MessagesServiceImpl_sendMessages_00_ERROR ("50100","发送消息"),
	MessagesServiceImpl_sendGroupMessages_00_ERROR ("50200","向某个群组的所有成员发送消息"),
	MessagesServiceImpl_sendOpenMessage_00_ERROR ("50300","拜访消息特殊处理方法"),


	
	
	;
	private String code;
	private String msg;

	

	public String getCode() {
		return ExceptionModelEums.exception+code;
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

	
	
	

	ImFacadeEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ImFacadeEnums getEnum(String code) {
		ImFacadeEnums resultEnum = null;
		ImFacadeEnums[] enumAry = ImFacadeEnums.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getCode() == code) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		ImFacadeEnums[] ary = ImFacadeEnums.values();
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
