package com.bizideal.whoami.weixin.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.utils.ProtostuffConvert;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.bizideal.whoami.weixin.mapper.WechatTotalUserMapper;
import com.bizideal.whoami.weixin.mapper.WechatUserInfoMapper;
import com.bizideal.whoami.weixin.mapper.WechatUserLocationMapper;
import com.bizideal.whoami.weixin.service.WechatUserInfoService;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 下午1:33:39
 */
@Service
public class WechatUserInfoServiceImp implements WechatUserInfoService {
	@Autowired
	WechatUserInfoMapper  wechatUserInfoMapper;
	@Autowired
	WechatTotalUserMapper wechatTotalUserMapper;
	
	@Autowired
	WechatUserLocationMapper wechatUserLocationMapper;
	
	@Autowired
	RedisClientTemplate redisClientTemplate;

	@Override
	public List<WechatUserInfo> getUserByOpenids(
			List<WechatTotalUser> totalUsers) {
		
		return wechatUserInfoMapper.getUserByOpenids(totalUsers);
	}

	@Override
	public List<WechatUserInfo> getUserinfoByOpenids(List<String> openids) {
		
		return wechatUserInfoMapper.getUserinfoByOpenids(openids);
	}

	@Override
	public int deleteUserAndTotalUser(String openid) throws Exception {
		int i =  wechatTotalUserMapper.deleteTotalUser(openid);
		i = wechatUserInfoMapper.deleteWechatUser(openid);
		return i;
	}

	@Override
	public int insertUser(WechatUserInfo wechatUserInfo) throws Exception {
		WechatTotalUser wechatTotalUser = new WechatTotalUser();
		wechatTotalUser.setOpenid(wechatUserInfo.getOpenid());
		int i = wechatTotalUserMapper.insert(wechatTotalUser);
		i = wechatUserInfoMapper.insertSelective(wechatUserInfo);
		return i;
	}

	@Override
	public int batchSaveUser(List<WechatUserInfo> wechatUserInfos)
			throws Exception {
		
		return wechatUserInfoMapper.batchSaveUser(wechatUserInfos);
	}

	@Override
	public WechatAccessToken getWechatAccessToken() {
		ProtostuffConvert<WechatAccessToken> convert = new ProtostuffConvert<WechatAccessToken>(WechatAccessToken.class);
		byte[] bs =redisClientTemplate.getObject("WechatAccessToken");
		WechatAccessToken wechatAccessToken = null;
		if (null == bs) {		
		} else {
			 wechatAccessToken = convert.convert(bs);
		}
		return wechatAccessToken;
	}

	@Override
	public String setWechhatAccessToken(WechatAccessToken wechatAccessToken) throws Exception {
		ProtostuffConvert<WechatAccessToken> convert = new ProtostuffConvert<WechatAccessToken>(WechatAccessToken.class);
		byte[] bs = convert.convert(wechatAccessToken);
		String ok = redisClientTemplate.putObject("WechatAccessToken",7000, bs);
		return ok;
	}

	@Override
	public int insertSubscribe(WechatUserInfo wechatUserInfo) throws Exception {
		WechatTotalUser totalUser = new WechatTotalUser();
		totalUser.setOpenid(wechatUserInfo.getOpenid());
		int i = wechatTotalUserMapper.insert(totalUser);
		i = wechatUserInfoMapper.insert(wechatUserInfo);
		return i;
	}

	@Override
	public int deleteUnSubscribe(WechatUserInfo wechatUserInfo)
			throws Exception {
		Example example = new Example(WechatUserLocation.class);
		example.createCriteria().andEqualTo("unionid", wechatUserInfo.getUnionid());
		int i = wechatUserLocationMapper.deleteByExample(example);
		
		example = new Example(WechatTotalUser.class);
		example.createCriteria().andEqualTo("openid", wechatUserInfo.getOpenid());
		i = wechatTotalUserMapper.deleteByExample(example);
		
		example = new Example(WechatUserInfo.class);
		example.createCriteria().andEqualTo("unionid", wechatUserInfo.getUnionid());
		i = wechatUserInfoMapper.deleteByExample(example);
		return i;
	}

	@Override
	public List<String> getUserFromDBByOpenids(List<String> openids) {
		
		return wechatUserInfoMapper.getUserFromDBByOpenids(openids);
	}

}
