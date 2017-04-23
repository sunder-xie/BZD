package com.bizideal.whoami.signup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.signup.entity.Identity;
import com.bizideal.whoami.signup.mapper.IdentityMapper;
import com.bizideal.whoami.signup.service.IdentityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午6:03:32
 * @version 1.0
 * @description 身份接口实现
 */
@Service("identityService")
public class IdentityServiceImpl extends BaseBizImpl<Identity> implements IdentityService {

	@Autowired
	private IdentityMapper identityMapper;

	@Override
	public PageInfo<Identity> getbyPage(Identity identity) {
		PageHelper.startPage(identity.getPageNum(), identity.getPageSize());
		List<Identity> list = identityMapper.selectByMeeId(identity);
		return new PageInfo<Identity>(list);
	}

}
