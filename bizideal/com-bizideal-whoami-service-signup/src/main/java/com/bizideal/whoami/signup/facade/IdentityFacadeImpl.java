package com.bizideal.whoami.signup.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.signup.entity.Identity;
import com.bizideal.whoami.signup.service.IdentityService;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午6:20:11
 * @version 1.0
 * @description 身份接口实现类
 */
@Component("identityFacade")
public class IdentityFacadeImpl implements IdentityFacade {

	@Autowired
	private IdentityService identityService;

	@Override
	public Identity getById(int id) {
		return identityService.queryById(id);
	}

	@Override
	public PageInfo<Identity> getByPage(Identity identity) {
		return identityService.getbyPage(identity);
	}

	@Override
	public int updateById(Identity identity) {
		return identityService.update(identity);
	}

	@Override
	public int insert(Identity identity) {
		return identityService.save(identity);
	}

	@Override
	public int deleteById(int id) {
		return identityService.deleteById(id);
	}

}
