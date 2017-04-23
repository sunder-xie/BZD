package com.bizideal.whoami.wechat.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.bizideal.whoami.wechat.service.CoreService;
import com.bizideal.whoami.wechat.utils.PropertiesUtils;
import com.bizideal.whoami.wechat.utils.RequestMessage;
import com.bizideal.whoami.wechat.utils.ResponseMessage;
import com.bizideal.whoami.wechat.utils.SignUtil;
import com.bizideal.whoami.wechat.utils.TokenSignUtil;
import com.bizideal.whoami.wechat.utils.WeChatUtil;
import com.bizideal.whoami.weixin.dto.WechatAccessToken;
import com.bizideal.whoami.weixin.dto.response.Article;
import com.bizideal.whoami.weixin.dto.response.MediaId;
import com.bizideal.whoami.weixin.facade.WechatUserReadFacade;

@Service
public class CoreServiceImpl implements CoreService {
	@Autowired
	private WechatUserReadFacade wechatUserReadFacade;
	@Autowired(required = false)
	WechatWebConfig wechatConfig;

	/**
	 * 微信平台开发认证
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String checkSignature(HttpServletRequest request) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		String token = wechatUserReadFacade.getWechatConfig(
				wechatConfig.getProfile()).getToken();
		if (Objects.equals("test", wechatConfig.getProfile())) {
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (TokenSignUtil
					.checkSignature(signature, timestamp, nonce, token)) {
				return echostr;
			} else {
				return "-1";
			}
		} else {
			// 通过检验signature 对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce, token)) {
				return echostr;
			} else {
				return "-1";
			}

		}

	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
    
		Map<String, String> messageMap = new HashMap<String, String>();
		// 默认返回的文本消息内容
		String respContent = "默认的返回消息";
		// xml 请求解析
		try {
			//把微信公众号接收到的消息解析为map
			messageMap = RequestMessage.parseXml(request);
			// 微信加密签名
			String msgSignature = request.getParameter("msg_signature");
			if (msgSignature == null) {
				messageMap.put("flag", "false");
			}
			// 消息类型
			String msgType = messageMap.get("MsgType");
			String openid = messageMap.get("FromUserName");

			// 回复处理=============================================================

			if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_TEXT)) {
				// 文本消息 机器人 回复 找不到规则 回复默认的 回复文本消息
				respMessage = ResponseMessage.textMessage("文本消息", messageMap);
				MediaId mediaId = new MediaId();
				mediaId.setMediaId("O_s23MHIVGn7_lVQ_0d2h3F2hYQMolGve8f6Euq3lY0");
				// 回复图片消息
				respMessage = ResponseMessage.imageMessage(mediaId, messageMap);
				List<Article> articles = new ArrayList<Article>();
				Article article = null;
				for (int i = 0; i < 2; i++) {
					article = new Article();
					article.setTitle("全国高职高专校长联席会议2016年年会在江西南昌召开");
					article.setDescription("全国高职高专校长联席会议2016年年会在江西南昌召开     10月28日至29日，全国高职高专校长联席会议");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwMDkyOTcyMw==&mid=100000010&idx=1&sn=e14fd4882111f3e46f889b064eea0979&chksm=16f4f9de218370c8c96cf285b33f12d7695a2245b0c1c9ab90c817ec949ee7b07f5329ceaed2#rd");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/drBFjZ9JCBh14hicQsSALUetk37bzSeKXFZ8WlibMGzedfOyKPKia24pFHUMcD7yu96HukL0ibFjFY1tqPibcJA6IVQ/0?wx_fmt=jpeg");
					articles.add(article);
				}
				// 回复多图文消息
				respMessage = ResponseMessage.newsMessage(articles, messageMap);
				articles.clear();
				for (int i = 0; i < 1; i++) {
					article = new Article();
					article.setTitle("坐车提示");
					article.setDescription("笑脸会坐车提示");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwMDkyOTcyMw==&mid=100000019&idx=1&sn=4a909cf3d6b7f6a6e8ae477589d5c657&chksm=16f4f9c7218370d14251bc6a6405cefb3a266e40232e0b67d366ced91dbfa5ae9e0ca7ed4fe9#rd");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/drBFjZ9JCBh14hicQsSALUetk37bzSeKXFZ8WlibMGzedfOyKPKia24pFHUMcD7yu96HukL0ibFjFY1tqPibcJA6IVQ/0?wx_fmt=jpeg");
					articles.add(article);
				}
				// 单图文消息
				respMessage = ResponseMessage.newsMessage(articles, messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_IMAGE)) {
				// 图片消息 回复默认的
				respMessage = ResponseMessage
						.textMessage("你发送的是图片", messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_VOICE)) {
				// 音频消息 回复默认的
				respMessage = ResponseMessage
						.textMessage("你发送的是音频", messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_VIDEO)
					|| msgType
							.equals(RequestMessage.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
				// 视频和小视频消息 回复默认的
				respMessage = ResponseMessage
						.textMessage("你发送的是视屏", messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_LOCATION)) {
				// 地理位置消息 回复默认的
				respMessage = ResponseMessage.textMessage("你发送的是地理位置",
						messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_LINK)) {
				// 链接消息 回复默认的
				respMessage = ResponseMessage
						.textMessage("你发送的是连接", messageMap);
			} else if (msgType.equals(RequestMessage.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件推送
				String eventType = messageMap.get("Event");
				// System.out.println("事件类型"+eventType);

				if (eventType.equals(RequestMessage.EVENT_TYPE_SUBSCRIBE)) {
					// 关注
					WechatAccessToken wechatAccessToken = this
							.getWechatAccessToken(wechatConfig.getAppId(),
									wechatConfig.getAppSecret());
					if (null != wechatAccessToken) {
						if (StringUtils.isNotBlank(openid)) {
							String flag = messageMap.get("FromUserName")
									+ messageMap.get("CreateTime");
							respMessage = ResponseMessage.textMessage("关注",
									messageMap);
						}
					}
				} else if (eventType
						.equals(RequestMessage.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复
					//

				} else if (Objects.equals("scancode_waitmsg",
						eventType.toLowerCase())) {
					// scancode_waitmsg，回应该事件给用户，用户可收到消息
					// 扫描带参数二维码事件 用户已关注时的事件推送

					respMessage = ResponseMessage.textMessage(
							"scancode_waitmsg，回应该事件给用户，用户可收到消息", messageMap);
				} else if (Objects.equals("scancode_push", eventType)) {
					// scancode_push，回应该事件给用户，用户不能收到消息
					respMessage = ResponseMessage.textMessage(
							"scancode_push，回应该事件给用户，用户不能收到消息", messageMap);

				} else if (eventType.equals(RequestMessage.EVENT_TYPE_LOCATION)) {
					// 上报地理位置事件 微信公众号上报地理位置信息事件不需要返回信息给微信服务器
					// 保存用户地理位置

					respMessage = null;

				} else if (eventType.equals(RequestMessage.EVENT_TYPE_CLICK)) {
					// 自定义菜单点击事件-点击菜单给用户推送消息
					respMessage = ResponseMessage.textMessage(
							"自定义菜单点击事件-点击菜单给用户推送消息", messageMap);
				}

			} else {
				respMessage = ResponseMessage
						.textMessage("默认的消息回复", messageMap);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;

	}

	/**
	 * 获取微信公众号的accesstoken
	 */
	@Override
	public WechatAccessToken getWechatAccessToken(String appid, String appsecret) {
		WechatAccessToken wechatAccessToken = wechatUserReadFacade
				.getWechatAccessToken();
		if (wechatAccessToken == null) {
			wechatAccessToken = WeChatUtil.getWechatAccessToken(appid,
					appsecret);
			try {
				wechatUserReadFacade.setWechhatAccessToken(wechatAccessToken);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return wechatAccessToken;
	}

}
