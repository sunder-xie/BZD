package com.bizideal.whoami.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.mapper.HuanxinFriendMapper;
import com.bizideal.whoami.user.service.HuanxinFriendService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月8日 下午2:10:09
 * @version 1.0
 */
@Service("huanxinFriendService")
public class HuanxinFriendServiceImpl implements HuanxinFriendService {

	@Autowired
	private HuanxinFriendMapper huanxinFriendMapper;

	@Override
	public int insert(String userId, String userIdFriend) {
		return huanxinFriendMapper.insertOne(userId, userIdFriend);
	}

	@Override
	public List<UserFriendDto> selectFriends(String userId) {
		return huanxinFriendMapper.selectFriends(userId);
	}

	@Override
	public int delete(String userId, String userIdFriend) {
		return huanxinFriendMapper.deleteOne(userId, userIdFriend);
	}

	@Override
	public List<UserFriendDto> searchFriend(String userId, String info) {
		List<UserFriendDto> searchFriend = huanxinFriendMapper.searchFriend(
				userId, info);
		List<UserFriendDto> friends = huanxinFriendMapper.selectFriends(userId);
		for (UserFriendDto userWeixinInfo : searchFriend) {
			A: for (UserFriendDto friend : friends) {
				if (friend.getUserId().equals(userWeixinInfo.getUserId())) {
					userWeixinInfo.setIsFriend("Y");
					break A;
				}
			}
		}
		return searchFriend;
	}

}
