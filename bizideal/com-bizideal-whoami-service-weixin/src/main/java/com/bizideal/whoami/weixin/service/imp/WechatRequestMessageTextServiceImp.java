package com.bizideal.whoami.weixin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.weixin.entity.WechatRequestMessageText;
import com.bizideal.whoami.weixin.entity.WechatResponseMessageText;
import com.bizideal.whoami.weixin.mapper.WechatRequestMessageTextMapper;
import com.bizideal.whoami.weixin.mapper.WechatResponseMessageTextMapper;
import com.bizideal.whoami.weixin.service.WechatRequestMessageTextService;

/**
 * @author zhu_shangjin
 * @version 2016年12月22日 下午12:54:34
 */
@Service
public class WechatRequestMessageTextServiceImp implements
		WechatRequestMessageTextService {
	@Autowired
	WechatResponseMessageTextMapper wechatResponseMessageTextMapper;
	@Autowired 
	WechatRequestMessageTextMapper wechatRequestMessageTextMapper;

	@Override
	public WechatRequestMessageText getReqAndRespByKey(String reqKeyWords) {
		return wechatRequestMessageTextMapper.getReqAndRespByKey(reqKeyWords);
	}

	@Override
	public WechatRequestMessageText getRequestByKey(String reqKeyWords) {
		return wechatRequestMessageTextMapper.getRequestByKey(reqKeyWords);
	}

	@Override
	public int insertReqAndResp(
			WechatRequestMessageText wechatRequestMessageText) throws Exception {
		WechatResponseMessageText textResponse = wechatRequestMessageText.getTextResponse();
		int i = 0;
		if (textResponse == null) {
			i = wechatRequestMessageTextMapper.insert(wechatRequestMessageText);
		}else {
			i = wechatRequestMessageTextMapper.insert(wechatRequestMessageText);
			i = wechatResponseMessageTextMapper.insert(textResponse);
		}
		return i;
	}

	@Override
	public int deleteReqAndRespByKey(String keyWords) throws Exception {
		int i = wechatResponseMessageTextMapper.deleteRespByKey(keyWords);
		i = wechatRequestMessageTextMapper.deleteReqByKey(keyWords);
		return i;
	}

	@Override
	public int updateReqAndResp(
			WechatRequestMessageText wechatRequestMessageText) throws Exception {
		WechatResponseMessageText textResponse = wechatRequestMessageText.getTextResponse();
		int i = 0;
		if (textResponse == null) {
			i = wechatRequestMessageTextMapper.updateByPrimaryKey(wechatRequestMessageText);
		}else {
			i = wechatRequestMessageTextMapper.updateByPrimaryKey(wechatRequestMessageText);
			i = wechatResponseMessageTextMapper.updateByPrimaryKey(textResponse);
		}
		return i;
	}

}
