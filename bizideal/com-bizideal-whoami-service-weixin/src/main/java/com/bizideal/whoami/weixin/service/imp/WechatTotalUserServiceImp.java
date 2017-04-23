package com.bizideal.whoami.weixin.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.mapper.WechatTotalUserMapper;
import com.bizideal.whoami.weixin.service.WechatTotalUserService;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 下午1:35:25
 */
@Service
public class WechatTotalUserServiceImp implements WechatTotalUserService {
	@Autowired
	WechatTotalUserMapper wechatTotalUserMapper;

	@Override
	public List<WechatTotalUser> getTotalusersByOpenids(
			List<WechatTotalUser> totalUsers) {
	
		return wechatTotalUserMapper.getTotalusersByOpenids(totalUsers);
	}

	@Override
	public List<WechatTotalUser> getTotalByOpenids(List<String> openids) {
	
		return wechatTotalUserMapper.getTotalByOpenids(openids);
	}

	@Override
	public int batchSaveTotalUser(List<String> openids) throws Exception {
		
		return wechatTotalUserMapper.batchSaveTotalUser(openids);
	}

	@Override
	public int insertTotalUser(String openid) throws Exception {
		WechatTotalUser totalUser = new WechatTotalUser();
		totalUser.setOpenid(openid);
		return wechatTotalUserMapper.insert(totalUser);
	}

	@Override
	public List<String> getFromDBByOpenids(List<String> openids) {
		
		return wechatTotalUserMapper.getFromDBByOpenids(openids);
	}

}
