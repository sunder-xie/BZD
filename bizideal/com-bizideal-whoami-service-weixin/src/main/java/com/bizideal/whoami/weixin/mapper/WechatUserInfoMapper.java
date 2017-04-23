package com.bizideal.whoami.weixin.mapper;


import java.util.List;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月13日 下午4:23:05
 */
public interface WechatUserInfoMapper extends Mapper<WechatUserInfo>{
	/**
	 * 获取totalUsers 存在且数据库也存在的用户
	 * @param totalUsers
	 * @return
	 */
	List<WechatUserInfo> getUserByOpenids(List<WechatTotalUser> totalUsers);
	/**
	 * 获取openids 存在且数据库也存在的用户
	 * @param openids
	 * @return
	 */
	List<WechatUserInfo> getUserinfoByOpenids(List<String> openids);
	/**
	 * 根据openid删除用户
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	int deleteWechatUser(String openid) throws Exception;
	/**
	 * 批量新增微信关注用户
	 * @param wechatUserInfos
	 * @return
	 * @throws Exception
	 */
	int batchSaveUser(List<WechatUserInfo> wechatUserInfos) throws Exception;
	/**
	 * 获取openids存在且数据库也存在的用户openid集合
	 * @param openids
	 * @return
	 */
	List<String> getUserFromDBByOpenids(List<String> openids);

}
