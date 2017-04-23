package com.bizideal.whoami.user.service;

import java.util.List;

import com.bizideal.whoami.user.dto.UserFriendDto;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月8日 下午2:09:02
 * @version 1.0 环信好友接口类
 */
public interface HuanxinFriendService {

	/**
	 * 添加好友
	 * 
	 * @param userId
	 *            发起者用户id
	 * @param userIdFriend
	 *            好友用户id
	 * @return
	 */
	int insert(String userId, String userIdFriend);

	/**
	 * 获取好友列表
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<UserFriendDto> selectFriends(String userId);

	/**
	 * 删除好友
	 * 
	 * @param userIdFriend
	 * @return
	 */
	int delete(String userId, String userIdFriend);

	/**
	 * 根据真实姓名或者手机号搜索好友
	 * 
	 * @param userFriend
	 * @return
	 */
	List<UserFriendDto> searchFriend(String userId, String info);
}
