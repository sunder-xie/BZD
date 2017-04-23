package com.bizideal.whoami.weixin.service;

import com.bizideal.whoami.weixin.entity.WechatUserLocation;

/**
 * @author zhu_shangjin
 * @version 2016年12月26日 上午10:58:52
 */
public interface WechatUserLocationService {
     /**
      * 根据用户 unionid 删除用户地理位置信息
      * @param unionid  用户unionid
      * @return
      * @throws Exception
      */
	 int deleteLocationByUnionid(String unionid) throws Exception;
	/**
	 * 新增或修改用户地理位置信息
	 * @param wechatUserLocation
	 * @return
	 * @throws Exception
	 */
	int insertOrUpdateLocation(WechatUserLocation wechatUserLocation) throws Exception;
   /**
    * 根据用户unionid  获取用户地理位置信息
    * @param unionid  用户unionid
    * @return
    */
   WechatUserLocation getUserLocation(String unionid);
	/**
	 * 根据用户openid 获取用户地理位置信息
	 * @param openid  用户openid
	 * @return
	 */
	WechatUserLocation getLocationByOpenid(String openid);

}
