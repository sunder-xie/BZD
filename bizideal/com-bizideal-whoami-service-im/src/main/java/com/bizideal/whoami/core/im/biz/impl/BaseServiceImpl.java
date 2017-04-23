package com.bizideal.whoami.core.im.biz.impl;

import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.common.Constants;
import com.bizideal.whoami.core.im.common.Roles;
import com.bizideal.whoami.core.im.vo.ClientSecretCredential;
import com.bizideal.whoami.core.im.vo.Credential;
import org.codehaus.jackson.node.JsonNodeFactory;

/**
 * 
 * @ClassName BaseServiceImpl
 * @Description (基础类)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("baseService")
public class BaseServiceImpl {

	// 创建一个提供所有节点的JsonNodeFactory,false表示不创建DecimalNode
	protected static final JsonNodeFactory factory = JsonNodeFactory.instance;

	// 通过app的client_id和client_secret来获取app管理员token
	protected static Credential credential = new ClientSecretCredential(
			Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET,Roles.USER_ROLE_APPADMIN);
	
}