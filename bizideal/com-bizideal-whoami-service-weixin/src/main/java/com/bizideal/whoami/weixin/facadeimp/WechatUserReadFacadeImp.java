package com.bizideal.whoami.weixin.facadeimp;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.enums.WeiXinEnums;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.dto.response.ImageMessage;
import com.bizideal.whoami.weixin.dto.response.NewsMessage;
import com.bizideal.whoami.weixin.dto.response.TextMessage;
import com.bizideal.whoami.weixin.entity.WechatConfig;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.bizideal.whoami.weixin.facade.WechatUserReadFacade;
import com.bizideal.whoami.weixin.service.WechatConfigService;
import com.bizideal.whoami.weixin.service.WechatRequestMessageTextService;
import com.bizideal.whoami.weixin.service.WechatResponseMessageNewsImageService;
import com.bizideal.whoami.weixin.service.WechatResponseMessageTextService;
import com.bizideal.whoami.weixin.service.WechatTotalUserService;
import com.bizideal.whoami.weixin.service.WechatUserInfoService;
import com.bizideal.whoami.weixin.service.WechatUserLocationService;
import com.github.pagehelper.PageInfo;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 10:35
 */
@Component("wechatUserReadFacade")
public class WechatUserReadFacadeImp implements WechatUserReadFacade {
	@Autowired
	WechatConfigService wechatConfigService;
	@Autowired
	WechatTotalUserService wechatTotalUserService;
	@Autowired
	WechatUserInfoService wechatUserInfoService;
	@Autowired
	WechatRequestMessageTextService wechatRequestMessageTextService;
	@Autowired
	WechatResponseMessageTextService wechatResponseMessageTextService;
	@Autowired
	WechatUserLocationService wechatUserLocationService;
	@Autowired
	WechatResponseMessageNewsImageService wechatResponseMessageNewsImageService;

	/**
	 * 把从微信获取的和数据库中已有的对比出没有的进行新增 获取已有的用户
	 */
	@Override
	public List<WechatTotalUser> getTotalusersByOpenids(
			List<WechatTotalUser> totalUsers) {

		return wechatTotalUserService.getTotalusersByOpenids(totalUsers);
	}

	/**
	 * 获取已有的用户
	 */
	@Override
	public List<WechatUserInfo> getUserByOpenids(
			List<WechatTotalUser> totalUsers) {

		return wechatUserInfoService.getUserByOpenids(totalUsers);
	}

	@Override
	public WechatConfig getWechatConfig(String profile) {

		return wechatConfigService.getWechatConfig(profile);
	}

	@Override
	public List<WechatTotalUser> getTotalByOpenids(List<String> openids) {

		return wechatTotalUserService.getTotalByOpenids(openids);
	}

	@Override
	public List<WechatUserInfo> getUserinfoByOpenids(List<String> openids) {

		return wechatUserInfoService.getUserinfoByOpenids(openids);
	}

	@Override
	public WechatAccessToken getWechatAccessToken() {

		return wechatUserInfoService.getWechatAccessToken();
	}

	@Override
	public DubboxResult setWechhatAccessToken(
			WechatAccessToken wechatAccessToken) throws Exception {
		String ok = "";
		DubboxResult dubboxResult = null;
		try {
			ok = wechatUserInfoService.setWechhatAccessToken(wechatAccessToken);
			if (Objects.equals("OK", ok)) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), ok);
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_ACCESSTOKEN.getErrcode() + "",
						WeiXinEnums.WEIXIN_ACCESSTOKEN.getErrmsg(), ok);
			}
		} catch (Exception e) {

			e.printStackTrace();

			dubboxResult = DubboxResult.build(
					WeiXinEnums.WEIXIN_EXCEPTION.getErrcode() + "",
					WeiXinEnums.WEIXIN_EXCEPTION.getErrmsg(), e.getMessage());
		}
		return dubboxResult;
	}

	@Override
	public WechatRequestMessageText getReqAndRespByKey(String reqKeyWords) {

		return wechatRequestMessageTextService.getReqAndRespByKey(reqKeyWords);
	}

	@Override
	public WechatRequestMessageText getRequestByKey(String reqKeyWords) {

		return wechatRequestMessageTextService.getRequestByKey(reqKeyWords);
	}

	@Override
	public WechatResponseMessageText getResponseByKeyWords(String reqKeyWords) {

		return wechatResponseMessageTextService
				.getResponseByKeyWords(reqKeyWords);
	}

	@Override
	public WechatUserLocation getLocation(String unionid) {

		return wechatUserLocationService.getUserLocation(unionid);
	}

	@Override
	public WechatUserLocation getLocationByOpenid(String openid) {

		return wechatUserLocationService.getLocationByOpenid(openid);
	}

	// 获取 已关注且在 wechat_total_user 表中存在的用户opendis
	@Override
	public List<String> getFromDBByOpenids(List<String> openids) {

		return wechatTotalUserService.getFromDBByOpenids(openids);
	}

	// 获取 已关注且在 wechat_user_info 表中存在的用户opendis
	@Override
	public List<String> getUserFromDBByOpenids(List<String> openids) {

		return wechatUserInfoService.getUserFromDBByOpenids(openids);
	}

	@Override
	public WechatImage getWechatImageByMediaId(String mediaId) {
		
		return wechatResponseMessageNewsImageService.getWechatImageByMediaId(mediaId);
	}

	@Override
	public List<WechatImage> getAllWechatImages() {
		
		return wechatResponseMessageNewsImageService.getAllWechatImages();
	}

	@Override
	public PageInfo<WechatImage> getWechatIamage(Integer page, Integer rows) {
		
		return wechatResponseMessageNewsImageService.getWechatIamage(page, rows);
	}

	@Override
	public WechatNews getWechatNewsByMediaId(String mediaId) {
		
		return wechatResponseMessageNewsImageService.getWechatNewsByMediaId(mediaId);
	}

	@Override
	public List<WechatNews> getAllWechatNews() {
		
		return wechatResponseMessageNewsImageService.getAllWechatNews();
	}

	@Override
	public PageInfo<WechatNews> getWechatNews(Integer page, Integer rows) {
		
		return wechatResponseMessageNewsImageService.getWechatNews(page, rows);
	}

	@Override
	public TextMessage getTextMessageByKey(String key) {
		
		return wechatResponseMessageNewsImageService.getTextMessageByKey(key);
	}

	@Override
	public ImageMessage getImageMessageByKey(String key) {
		
		return wechatResponseMessageNewsImageService.getImageMessageByKey(key);
	}

	@Override
	public NewsMessage getNewsMessageByKey(String key) {
		
		return wechatResponseMessageNewsImageService.getNewsMessageByKey(key);
	}

}
