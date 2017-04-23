package com.bizideal.whoami.user.facade;

import java.util.List;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserFriend;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月8日 下午2:12:55
 * @version 1.0 环信好友rest接口类
 */
public interface HuanxinFriendFacade {
	/**
	 * 添加好友
	 * 
	 * @param userFriend
	 * @return
	 */
	DubboxResult insert(UserFriend userFriend);

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
	DubboxResult delete(UserFriend userFriend);

	/**
	 * 根据真实姓名或者手机号搜索好友
	 * 
	 * @param userFriend
	 * @return
	 */
	List<UserFriendDto> searchFriend(UserFriend userFriend);
}
