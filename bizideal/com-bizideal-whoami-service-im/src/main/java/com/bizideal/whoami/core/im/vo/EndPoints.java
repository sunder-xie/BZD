package com.bizideal.whoami.core.im.vo;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bizideal.whoami.core.im.common.Constants;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;


/**
 * @ClassName EndPoints
 * @Description TODO(ResteasyClient的请求路径)
 * @Author Zj.Qu
 * @Date 2017-01-04 13:35:26
 */
public interface EndPoints {

	ResteasyClient CLIENT = ResteasyUtils.getResteasyClient(true);

	ResteasyWebTarget ROOT_TARGET = CLIENT.target(Constants.API_HTTP_SCHEMA + "://" + Constants.API_SERVER_HOST + "/");

	ResteasyWebTarget APPLICATION_TEMPLATE = ROOT_TARGET.path(Constants.ORG_NAME).path(Constants.APP_NAME);

	ResteasyWebTarget TOKEN_APP_TARGET = APPLICATION_TEMPLATE.path("token");

	ResteasyWebTarget USERS_TARGET = APPLICATION_TEMPLATE.path("users");

	ResteasyWebTarget USERS_ADDFRIENDS_TARGET = USERS_TARGET.path("{ownerUserName}").path("contacts").path("users")
			.path("{friendUserName}");

	ResteasyWebTarget USERS_BLACKLIST_TARGET = USERS_TARGET.path("{ownerUserName}").path("blocks").path("users")
			.path("{blockedUserName}");

	ResteasyWebTarget MESSAGES_TARGET = APPLICATION_TEMPLATE.path("messages");

	ResteasyWebTarget CHATMESSAGES_TARGET = APPLICATION_TEMPLATE.path("chatmessages");

	ResteasyWebTarget CHATGROUPS_TARGET = APPLICATION_TEMPLATE.path("chatgroups");

	ResteasyWebTarget CHATFILES_TARGET = APPLICATION_TEMPLATE.path("chatfiles");
}
