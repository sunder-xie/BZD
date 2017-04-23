package com.bizideal.whoami.user.facade.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.dto.GroupDto;
import com.bizideal.whoami.user.dto.UserFriendDto;
import com.bizideal.whoami.user.entity.UserGroup;
import com.bizideal.whoami.user.facade.UserGroupRestFacade;
import com.bizideal.whoami.user.service.UserGroupService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年1月10日 下午3:04:42
 * @version 1.0
 */
@Path("/user/group")
@Component("userGroupRestFacade")
public class UserGroupRestFacadeImpl implements UserGroupRestFacade {

	@Autowired
	private UserGroupService userGroupService;

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult insertCreate(ChatGroup chatGroup) {
		return userGroupService.insertCreate(chatGroup);
	}

	@POST
	@Path("/addusers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult insertAddUsers(UserGroup userGroup) {
		return userGroupService.insertAddUsers(userGroup);
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult deleteUsers(UserGroup userGroup) {
		return userGroupService.deleteUsers(userGroup);
	}

	@POST
	@Path("/getlist/{userId}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public List<GroupDto> selectGroupIds(@PathParam("userId") String userId) {
		return userGroupService.selectGroupIds(userId);
	}

	@POST
	@Path("/getlist_members/{groupId}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public List<UserFriendDto> selectGroupMembers(
			@PathParam("groupId") String groupId) {
		return userGroupService.selectGroupMembers(groupId);
	}

	@POST
	@Path("/deletegroup/{groupId}")
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult deleteGroup(@PathParam("groupId") String groupId) {
		return userGroupService.deleteAllGroupMembers(groupId);
	}

	@POST
	@Path("/updateGroup")
	// 指定接收JSON格式的数据，REST框架会自动将JSON数据反序列化为实体对象
	@Consumes(MediaType.APPLICATION_JSON)
	// 指定输出JSON格式的数据。框架会自动将对象序列化为JSON数据
	@Produces(ContentType.APPLICATION_JSON_UTF_8)
	@Override
	public DubboxResult updateGroupName(ChatGroup chatGroup) {
		return userGroupService.updateGroupName(chatGroup);
	}

}
