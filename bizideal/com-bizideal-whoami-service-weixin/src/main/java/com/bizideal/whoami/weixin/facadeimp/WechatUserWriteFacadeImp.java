package com.bizideal.whoami.weixin.facadeimp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.enums.RoleAndMqEnums;
import com.bizideal.whoami.enums.WeiXinEnums;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.weixin.entity.WechatImage;
import com.bizideal.whoami.weixin.entity.WechatNews;
import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatTotalUser;
import com.bizideal.whoami.weixin.entity.WechatUserInfo;
import com.bizideal.whoami.weixin.entity.WechatUserLocation;
import com.bizideal.whoami.weixin.facade.WechatUserWriteFacade;
import com.bizideal.whoami.weixin.service.WechatConfigService;
import com.bizideal.whoami.weixin.service.WechatRequestMessageTextService;
import com.bizideal.whoami.weixin.service.WechatResponseMessageNewsImageService;
import com.bizideal.whoami.weixin.service.WechatResponseMessageTextService;
import com.bizideal.whoami.weixin.service.WechatTotalUserService;
import com.bizideal.whoami.weixin.service.WechatUserInfoService;
import com.bizideal.whoami.weixin.service.WechatUserLocationService;
import com.sun.istack.FinalArrayList;

/**
 * @author zhu_shangjin
 * @version 2016年12月19日 10:45
 */
@Component("wechatUserWriteFacade")
public class WechatUserWriteFacadeImp implements WechatUserWriteFacade {

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

	@Override
	public DubboxResult batchSaveTotalUser(List<WechatTotalUser> totalUsers) {
		List<String> openids = new ArrayList<String>();
		for (WechatTotalUser wechatTotalUser : totalUsers) {
			openids.add(wechatTotalUser.getOpenid());
		}
		return this.batchInsertTotalUser(openids);
	}

	@Override
	public DubboxResult deleteWechatUser(WechatUserInfo wechatUserInfo) {
		int i = 0;
		DubboxResult dubboxResult = null;
		try {
			i = wechatUserInfoService.deleteUserAndTotalUser(wechatUserInfo
					.getOpenid());
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_DELETE.getErrcode() + "",
						WeiXinEnums.WEIXIN_DELETE.getErrmsg(), i + "");
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
	public DubboxResult batchSaveUser(List<WechatUserInfo> wechatUserInfos) {
		int i = 0;
		DubboxResult dubboxResult = null;
		try {
			i = wechatUserInfoService.batchSaveUser(wechatUserInfos);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_BATCH_ADD_USER.getErrcode() + "",
						WeiXinEnums.WEIXIN_BATCH_ADD_USER.getErrmsg(), i + "");
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
	public DubboxResult saveUser(WechatUserInfo wechatUserInfo) {
		int i = 0;
		DubboxResult dubboxResult = null;
		try {
			i = wechatUserInfoService.insertUser(wechatUserInfo);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_ADD.getErrcode() + "",
						WeiXinEnums.WEIXIN_ADD.getErrmsg(), i + "");
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
	public DubboxResult batchInsertTotalUser(List<String> openids) {
		int i = 0;
		DubboxResult dubboxResult = null;
		try {
			i = wechatTotalUserService.batchSaveTotalUser(openids);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_BATCH_ADD_TOTALUSER.getErrcode()
								+ "",
						WeiXinEnums.WEIXIN_BATCH_ADD_TOTALUSER.getErrmsg(), i
								+ "");
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
	public DubboxResult deleteWechatUserByOpenid(String openid) {
		int i = 0;
		DubboxResult dubboxResult = null;
		try {
			i = wechatUserInfoService.deleteUserAndTotalUser(openid);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_DELETE.getErrcode() + "",
						WeiXinEnums.WEIXIN_DELETE.getErrmsg(), i + "");
			}
		} catch (final Exception e) {
			
			dubboxResult = DubboxResult.build(
					WeiXinEnums.WEIXIN_EXCEPTION.getErrcode() + "",
					WeiXinEnums.WEIXIN_EXCEPTION.getErrmsg(), e.getMessage());
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertReqAndResp(
			WechatRequestMessageText wechatRequestMessageText) throws Exception {
		DubboxResult dubboxResult = null;
		try {
			int i = wechatRequestMessageTextService
					.insertReqAndResp(wechatRequestMessageText);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_REQADD.getErrcode() + "",
						WeiXinEnums.WEIXIN_REQADD.getErrmsg(), i + "");
			}

		} catch (final Exception ex) {
			dubboxResult = DubboxResult.build(
					WeiXinEnums.WEIXIN_EXCEPTION.getErrcode() + "",
					WeiXinEnums.WEIXIN_EXCEPTION.getErrmsg(), ex.getMessage());

		}
		return dubboxResult;
	}

	@Override
	public DubboxResult deleteReqAndRespByKey(String keyWords) throws Exception {
		DubboxResult dubboxResult = null;
		try {
			int i = wechatRequestMessageTextService
					.deleteReqAndRespByKey(keyWords);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_REQDELETE.getErrcode() + "",
						WeiXinEnums.WEIXIN_REQDELETE.getErrmsg(), i + "");
			}

		} catch (final Exception ex) {
			dubboxResult = DubboxResult.build(
					WeiXinEnums.WEIXIN_EXCEPTION.getErrcode() + "",
					WeiXinEnums.WEIXIN_EXCEPTION.getErrmsg(), ex.getMessage());

		}
		return dubboxResult;
	}

	@Override
	public DubboxResult updateReqAndResp(
			WechatRequestMessageText wechatRequestMessageText) throws Exception {
		DubboxResult dubboxResult = null;
		try {
			int i = wechatRequestMessageTextService.updateReqAndResp(wechatRequestMessageText);
			if (i > 0) {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_OK.getErrcode() + "",
						WeiXinEnums.WEIXIN_OK.getErrmsg(), i + "");
			} else {
				dubboxResult = DubboxResult.build(
						WeiXinEnums.WEIXIN_REQUPDATE.getErrcode() + "",
						WeiXinEnums.WEIXIN_REQUPDATE.getErrmsg(), i + "");
			}

		} catch (final Exception ex) {
			dubboxResult = DubboxResult.build(
					WeiXinEnums.WEIXIN_EXCEPTION.getErrcode() + "",
					WeiXinEnums.WEIXIN_EXCEPTION.getErrmsg(), ex.getMessage());

		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertSubscribe(WechatUserInfo wechatUserInfo)
			throws Exception {
		DubboxResult dubboxResult = null;
		int i = wechatUserInfoService.insertSubscribe(wechatUserInfo);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatUserInfo.getOpenid());
		}else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_SUB.getErrcode()+"", WeiXinEnums.WEIXIN_SUB.getErrmsg(), wechatUserInfo.getOpenid());
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult deleteUnSubscribe(WechatUserInfo wechatUserInfo)
			throws Exception {
		DubboxResult dubboxResult = null;
		int i = wechatUserInfoService.deleteUnSubscribe(wechatUserInfo);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatUserInfo.getOpenid());
		}else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_DELETE.getErrcode()+"", WeiXinEnums.WEIXIN_DELETE.getErrmsg(), wechatUserInfo.getOpenid());
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertOrUpdateLocation(
			WechatUserLocation wechatUserLocation) throws Exception {
		DubboxResult dubboxResult = null;
		int i = wechatUserLocationService.insertOrUpdateLocation(wechatUserLocation);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatUserLocation.getOpenid());
		}else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_LOCATION.getErrcode()+"", WeiXinEnums.WEIXIN_LOCATION.getErrmsg(), wechatUserLocation.getOpenid());
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertWechatImage(WechatImage wechatImage) throws Exception {
		DubboxResult dubboxResult = null;
		int i = wechatResponseMessageNewsImageService.insertWechatImage(wechatImage);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatImage.getMediaId()+"");
		} else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_MESSAGE.getErrcode()+"", WeiXinEnums.WEIXIN_MESSAGE.getErrmsg(), wechatImage.getMediaId()+"");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult batchInsertWechatImage(List<WechatImage> wechatImages) throws Exception {
		
		DubboxResult dubboxResult = null;
		int i = wechatResponseMessageNewsImageService.batchInsertWechatImage(wechatImages);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatImages.size()+"");
		} else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_MESSAGE.getErrcode()+"", WeiXinEnums.WEIXIN_MESSAGE.getErrmsg(), wechatImages.size()+"");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult insertWechatNews(WechatNews wechatNews) throws Exception {
		
		DubboxResult dubboxResult = null;
		int i = wechatResponseMessageNewsImageService.insertWechatNews(wechatNews);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatNews.getMediaId()+"");
		} else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_MESSAGE.getErrcode()+"", WeiXinEnums.WEIXIN_MESSAGE.getErrmsg(), wechatNews.getMediaId()+"");
		}
		return dubboxResult;
	}

	@Override
	public DubboxResult batchInsertWechatNews(List<WechatNews> wechatNews) throws Exception {
		
		DubboxResult dubboxResult = null;
		int i = wechatResponseMessageNewsImageService.batchInsertWechatNews(wechatNews);
		if (i>0) {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_OK.getErrcode()+"", WeiXinEnums.WEIXIN_OK.getErrmsg(), wechatNews.size()+"");
		} else {
			dubboxResult = DubboxResult.build(WeiXinEnums.WEIXIN_MESSAGE.getErrcode()+"", WeiXinEnums.WEIXIN_MESSAGE.getErrmsg(), wechatNews.size()+"");
		}
		return dubboxResult;
	}

}
