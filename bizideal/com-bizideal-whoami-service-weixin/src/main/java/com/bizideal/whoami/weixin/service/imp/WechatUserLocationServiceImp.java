package com.bizideal.whoami.weixin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.bizideal.whoami.weixin.mapper.WechatUserLocationMapper;
import com.bizideal.whoami.weixin.service.WechatUserLocationService;

/**
 * @author zhu_shangjin
 * @version 2016年12月26日 上午11:04:53
 */
@Service
public class WechatUserLocationServiceImp implements WechatUserLocationService {
	@Autowired
	WechatUserLocationMapper wechatUserLocationMapper;

	@Override
	public int deleteLocationByUnionid(String unionid) throws Exception {
		Example example = new Example(WechatUserLocation.class);
		example.createCriteria().andEqualTo("unionid", unionid);
		return wechatUserLocationMapper.deleteByExample(example);

	}

	@Override
	public int insertOrUpdateLocation(WechatUserLocation wechatUserLocation)
			throws Exception {
		int i = 0;
		Long count = wechatUserLocationMapper.countLocationByUnionid(wechatUserLocation.getUnionid());
		if (count>0) {
			i = wechatUserLocationMapper.insert(wechatUserLocation);
		} else {
			Example example = new Example(WechatUserLocation.class);
			example.createCriteria().andEqualTo("unionid", wechatUserLocation.getUnionid());
			i = wechatUserLocationMapper.updateByExample(wechatUserLocation, example);
		}
		return i;
	}

	@Override
	public WechatUserLocation getUserLocation(String unionid) {
		Example example = new Example(WechatUserLocation.class);
		example.createCriteria().andEqualTo("unionid", unionid);
		WechatUserLocation wechatUserLocation = (WechatUserLocation) wechatUserLocationMapper.selectByExample(example);
		return wechatUserLocation;
	}

	@Override
	public WechatUserLocation getLocationByOpenid(String openid) {
		Example example = new Example(WechatUserLocation.class);
		example.createCriteria().andEqualTo("openid", openid);
		WechatUserLocation wechatUserLocation = (WechatUserLocation) wechatUserLocationMapper.selectByExample(example);
		return wechatUserLocation;
	}

}
