package com.bizideal.whoami.signup.service;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.signup.entity.Identity;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午6:02:52
 * @version 1.0
 * @description 身份接口
 */
public interface IdentityService extends BaseBiz<Identity> {

	/**
	 * 分页查询会议下身份，根据会议id
	 * 
	 * @param identity
	 * @return
	 */
	PageInfo<Identity> getbyPage(Identity identity);

}
