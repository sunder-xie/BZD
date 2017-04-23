package com.bizideal.whoami.wechat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.CookieUtils;
import com.bizideal.whoami.utils.HttpClientUtils;
import com.bizideal.whoami.wechat.config.WebAppConfig;
import com.bizideal.whoami.wechat.config.WechatWebConfig;
import com.bizideal.whoami.wechat.service.CoreService;
import com.bizideal.whoami.wechat.service.WechatOAuth2Service;

@Controller
@RequestMapping("/weixinoauth")
public class WeixinOauthController {
	@Autowired
	WechatOAuth2Service wechatOAuth2Service;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	private UserInfoFacade userInfoFacade;
	@Autowired
	CoreService coreService;
	@Autowired(required = false)
	WebAppConfig webAppConfig;
	@Autowired(required = false)
	WechatWebConfig wechatConfig;
	@Autowired
	RoleModuleWriteFacade roleModuleWriteFacade;
	@Autowired
	RoleModuleReadFacade roleModuleReadFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Value("${imageHead}")
	private String imageHead;// 我的头像图片
   //微信公众号网页授权的url
	//微信公众号网页登入
	@RequestMapping("/oauth")
	public String getWeixinUser(String code, String state,
			HttpServletRequest request, HttpServletResponse response) {
		String loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}
		try {
			String redirect = state;
			String flag = wechatOAuth2Service.getOauth2AccessTokenFlag(code,
					request);
			if (Objects.equals("false", flag)) {
				state = "/wechaterror";
				return "redirect:" + state;
			}
			UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request
					.getSession().getAttribute("user");

			if (userWeixinInfo == null) {
				state = "/wechaterror";
				return "redirect:" + state;
			} else {
				userWeixinInfo = userWeixinInfoFacade
						.weixinLogin(userWeixinInfo);
				String unionid = userWeixinInfo.getUnionid();
				if (StringUtils.isBlank(userWeixinInfo.getUserId())) {
					// 加 return 返回到绑定手机号的页面
					state = "/weixinoauth/bindphone/" + unionid + "?redirect="
							+ redirect;
					// 加 return 返回到绑定手机号
					return "redirect:" + state;
				} else {
					String headimgurl = userWeixinInfo.getHeadimgurl();
					if (!("".equals(headimgurl) || null == headimgurl)) {
						if (!(headimgurl.startsWith("http"))) { // 判断用户的头像是否是默认头像
							userWeixinInfo.setHeadimgurl(fastdfsurl
									+ headimgurl);
						}
					} else {
						userWeixinInfo.setHeadimgurl("/css/img/" + imageHead);
					}

					request.getSession().setAttribute("user", userWeixinInfo);
					// 写入cookie 设置时间为 秒 关闭浏览器还会存在
					CookieUtils.setCookie(request, response, "loginType",
							loginType, 3600 * 24 * 7);
					CookieUtils.setCookie(request, response, "userId",
							userWeixinInfo.getUserId(), 3600 * 24 * 7);
					request.getSession().setAttribute("loginType", loginType);
					// 不是restful风格的url：state= /user/meetindex 不带参数 需自己加
					// loginType
					return "redirect:" + state;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			state = "/wechaterror";
			// 定向到错误信息页面
			return "redirect:" + state;
		}

	}
    //定向到用户绑定手机号的页面
	@RequestMapping("/bindphone/{unionid}")
	public String bindphone(@PathVariable("unionid") String unionid,
			ModelMap map, @RequestParam("redirect") String redirect,
			HttpServletRequest request) {
		map.addAttribute("unionid", unionid);
		map.addAttribute("redirect", redirect);
		return "weixin/bindphone";

	}
    //用户绑定手机号的操作
	@ResponseBody
	@RequestMapping(value = "/dobind", method = RequestMethod.PUT)
	public Map<String, String> dobind(@RequestBody UserWeixinInfo userWeixin,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> maps = new HashMap<String, String>();
		String loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}
		try {
			JSONObject userjson = userInfoFacade.updateBindPhone(userWeixin);
			if (userjson.containsKey("user")) {
				maps.put("status", "200");
				maps.put("msg", "绑定手机号码成功");
				maps.put("redirect", userWeixin.getRedirect());
				userWeixin = (UserWeixinInfo) JSONObject.toBean(
						userjson.getJSONObject("user"), UserWeixinInfo.class);
				String headimgurl = userWeixin.getHeadimgurl();
				if (!("".equals(headimgurl) || null == headimgurl)) {
					if (!(headimgurl.startsWith("http"))) { // 判断用户的头像是否是默认头像
						userWeixin.setHeadimgurl(fastdfsurl + headimgurl);
					}
				} else {
					userWeixin.setHeadimgurl("/css/img/" + imageHead);
				}
				request.getSession().setAttribute("user", userWeixin);
				request.getSession().setAttribute("loginType", loginType);
				// 写入cookie 设置时间为 秒 关闭浏览器还会存在
				CookieUtils.setCookie(request, response, "loginType",
						loginType, 3600 * 24 * 7);
				CookieUtils.setCookie(request, response, "userId",
						userWeixin.getUserId(), 3600 * 24 * 7);
			} else {
				maps.put("status", "400");
				maps.put("msg", "绑定手机号码失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			maps.put("status", "500");
			maps.put("msg", "服务器异常");
		}
		return maps;

	}
 
	@RequestMapping("/applogin/{userId}")
	public String applogin(@PathVariable("userId") String userId,
			ModelMap modelMap, @PathParam("redirect") String redirect,
			HttpServletRequest request, HttpServletResponse response) {
		UserWeixinInfo userWeixinInfo = userWeixinInfoFacade
				.selectByUserId(userId);
		if (null == userWeixinInfo) {
			redirect = "/appNouser";
		} else {
			String loginType = HttpClientUtils.isApp(request);
			request.getSession().setAttribute("user", userWeixinInfo);
			// 写入cookie 设置时间为 秒 关闭浏览器还会存在
			CookieUtils.setCookie(request, response, "loginType", loginType,
					3600 * 24 * 7);
			CookieUtils.setCookie(request, response, "userId",
					userWeixinInfo.getUserId(), 3600 * 24 * 7);
			request.getSession().setAttribute("loginType", loginType);

		}

		return "redirect:" + redirect;
	}
   /**
    *  微信公众号开发者模式配置的url路径
    * @param request
    * @param response
    * @throws Exception
    */
	@RequestMapping("/checkSignature")
	public void checkSignature(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取数据提交方法
		String method = request.getMethod();
		if (Objects.equals("POST", method)) {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// 调用核心业务类接收消息、处理消息
			String respMessage = this.coreService.processRequest(request);

			// 响应消息
			if (StringUtils.isNotBlank(respMessage)) {
				PrintWriter out = response.getWriter();
				out.print(respMessage);
				out.close();
			}

		} else {
			// 配置微信公众号校验
			String echostr = this.coreService.checkSignature(request);

			if (!Objects.equals("-1", echostr)) {
				PrintWriter out = response.getWriter();

				out.print(echostr);
				out.flush();
				out.close();
				out = null;
			}

		}
	}

	// 二维码登录页面 该页面有微信二维码和手机app二维码
	@RequestMapping("/qrcodeLogin")
	public String qrcodeLogin(String redirect, ModelMap modelMap,
			HttpServletRequest request) {
		// redirect  页面的最初来源
		modelMap.addAttribute("redirect", redirect);
		//获取项目路径
		String httpaddress = HttpClientUtils.getHttpAddress(request);
		// httpaddress = "https://weixindeve.tunnel.qydev.com/whoami";
		//微信开放平台的扫码登录的url WeixinOauthController 中的wechatweb方法
		String redirectUri = httpaddress + "/weixinoauth/wechatweb";
		webAppConfig.setRedirectUri(redirectUri);
		webAppConfig.setState(httpaddress);
		modelMap.addAttribute("webAppConfig", webAppConfig);
         //是否在微信中打开的网页  
		boolean isweixin = HttpClientUtils.isWeixin(request);
		
		if (isweixin) {
//在微信中打开  直接走微信的网页授权实现登陆
			String redirect_uri = "";
             // 微信网页授权的url  WeixinOauthController 中的getWeixinUser方法
			redirect_uri = httpaddress + "/weixinoauth/oauth";
            // 微信网页授权获取用户信息的api
			String weixinoauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ wechatConfig.getAppId()
					+ "&redirect_uri="
					+ redirect_uri
					+ "&response_type=code&scope=snsapi_userinfo&state="
					+ redirect + "#wechat_redirect";
			// modelMap.addAttribute("weixinoauthUrl", weixinoauthUrl);
			return "redirect:" + weixinoauthUrl;
			// return "weixin/qrcodeLogin";
		} else {
			QrcodeScan qrcodeScan = new QrcodeScan();
			qrcodeScan.setHasScan("no");
			qrcodeScan.setRedirect(redirect);
			qrcodeScan.setScanType("app");
			DubboxResult dubboxResult = null;
			try {
				dubboxResult = roleModuleWriteFacade.setQRCodeScan(qrcodeScan);
				modelMap.addAttribute("dubboxResult", dubboxResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 定向到二维码页面
			return "weixin/qrcodeLogin";
		}

	}
	//扫微信二维码登入+跳转url
	@RequestMapping("/wechatweb")
	public String wechatweb(String code, String state, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		String loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}
		try {
			String redirect = state;
			//扫微信二维码登入
			String flag = wechatOAuth2Service.getWechatWebAccessTokenString(
					code, webAppConfig, request);
			
			if (Objects.equals("false", flag)) {
				state = "/wechaterror";
				return "redirect:" + state;
			}
			UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request
					.getSession().getAttribute("user");

			if (userWeixinInfo == null) {
				state = "/wechaterror";
				return "redirect:" + state;
			} else {
				userWeixinInfo = userWeixinInfoFacade
						.weixinLogin(userWeixinInfo);
				String unionid = userWeixinInfo.getUnionid();
				if (StringUtils.isBlank(userWeixinInfo.getUserId())) {
					// 加 return 返回到绑定手机号的页面
					state = "/weixinoauth/bindphone/" + unionid + "?redirect="
							+ redirect;
					// 加 return 返回到绑定手机号
					return "redirect:" + state;
				} else {
					String headimgurl = userWeixinInfo.getHeadimgurl();
					if (!("".equals(headimgurl) || null == headimgurl)) {
						if (!(headimgurl.startsWith("http"))) { // 判断用户的头像是否是默认头像
							userWeixinInfo.setHeadimgurl(fastdfsurl
									+ headimgurl);
						}
					} else {
						userWeixinInfo.setHeadimgurl("/css/img/" + imageHead);
					}
					request.getSession().setAttribute("user", userWeixinInfo);
					request.getSession().setAttribute("loginType", loginType);
					// 写入cookie 设置时间为 秒 关闭浏览器还会存在
					CookieUtils.setCookie(request, response, "loginType",
							loginType, 3600 * 24 * 7);
					CookieUtils.setCookie(request, response, "userId",
							userWeixinInfo.getUserId(), 3600 * 24 * 7);

					return "redirect:" + state;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			state = "/wechaterror";
			// 定向到错误信息页面
			return "redirect:" + state;
		}
	}

	// 手机app扫码成功
	@RequestMapping("/scanRedirect/{codeid}")
	@ResponseBody
	public Map<String, String> scanRedirect(
			@PathVariable("codeid") Integer codeid, String redirect,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		QrcodeScan qrcodeScan = roleModuleReadFacade.getQrcodeScanById(codeid);
		String logintype = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			logintype = "weixin";
		}
		if (qrcodeScan == null
				|| StringUtils.isBlank(qrcodeScan.getScanUserId())) {
			map.put("status", "400");
			map.put("redirect", redirect);
			return map;
		}
		UserWeixinInfo userWeixinInfo = userWeixinInfoFacade
				.selectByUserId(qrcodeScan.getScanUserId());
		request.getSession().setAttribute("loginType", logintype);
		request.getSession().setAttribute("user", userWeixinInfo);
		// 写入cookie 设置时间为 秒 关闭浏览器还会存在
		CookieUtils.setCookie(request, response, "userId",
				userWeixinInfo.getUserId(), 3600 * 24 * 7);
		CookieUtils.setCookie(request, response, "loginType", logintype,
				3600 * 24 * 7);
		if (userWeixinInfo != null) {
			map.put("status", "200");
			map.put("redirect", redirect);
		}
		return map;

	}

	@ResponseBody
	@RequestMapping("/testweixin")
	public DubboxResult testWeixinFacade() {
		return new DubboxResult(200 + "", "ddd", 11 + "");
	}
}
