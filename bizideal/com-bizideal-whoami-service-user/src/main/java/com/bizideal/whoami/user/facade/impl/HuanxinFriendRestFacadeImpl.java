package com.bizideal.whoami.user.facade.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserFriend;
import com.bizideal.whoami.user.facade.HuanxinFriendRestFacade;
import com.bizideal.whoami.user.service.HuanxinFriendService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月8日 下午2:21:20
 * @version 1.0
 */
@Path("/friend")
@Component("huanxinFriendRestFacade")
public class HuanxinFriendRestFacadeImpl implements HuanxinFriendRestFacade {

	@Autowired
	private HuanxinFriendService huanxinFriendService;

	@Value("${fastdfsurl}")
	private String fastdfsurl;

	/* 添加好友 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult insert(UserFriend userFriend) {
		try {
			huanxinFriendService.insert(userFriend.getUserId(),
					userFriend.getUserIdFriend());
			return DubboxResult.build("0", "ok", null);
		} catch (Exception e) {
			e.printStackTrace();
			return DubboxResult.build("1", e.getMessage(), null);
		}
	}

	/* 获取好友列表 */
	@POST
	@Path("/getlist/{userId}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public List<UserFriendDto> selectFriends(@PathParam("userId") String userId) {
		List<UserFriendDto> selectFriends = huanxinFriendService
				.selectFriends(userId);
		for (UserFriendDto user : selectFriends) {
			if (StringUtils.isNotBlank(user.getHeadimgurl())
					&& !StringUtils.startsWith(user.getHeadimgurl(),
							"http://wx")) {
				user.setHeadimgurl(fastdfsurl + user.getHeadimgurl());
			}
		}
		return selectFriends;
	}

	/* 删除好友 */
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult delete(UserFriend userFriend) {
		int result = huanxinFriendService.delete(userFriend.getUserId(),
				userFriend.getUserIdFriend());
		if (result == 0) {
			return DubboxResult.build("1", "删除好友失败", null);
		} else {
			return DubboxResult.build("0", "ok", null);
		}
	}

	/* 搜索好友 */
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public List<UserFriendDto> searchFriend(UserFriend userFriend) {
		List<UserFriendDto> searchFriends = huanxinFriendService.searchFriend(
				userFriend.getUserId(), userFriend.getInfo());
		for (UserFriendDto user : searchFriends) {
			if (StringUtils.isNotBlank(user.getHeadimgurl())
					&& !StringUtils.startsWith(user.getHeadimgurl(),
							"http://wx")) {
				user.setHeadimgurl(fastdfsurl + user.getHeadimgurl());
			}
		}
		return searchFriends;
	}

}
