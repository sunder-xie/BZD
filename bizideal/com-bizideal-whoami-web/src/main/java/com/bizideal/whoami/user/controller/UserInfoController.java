package com.bizideal.whoami.user.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.rolemodule.facade.RoleModuleWriteFacade;
import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserInfoFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.CookieUtils;
import com.bizideal.whoami.utils.HttpClientUtils;
import com.bizideal.whoami.utils.ObjectId;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 下午12:48:50
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

	private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	@Autowired
	private UserInfoFacade userInfoFacade;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	RoleModuleWriteFacade roleModuleWriteFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;
	
	@Value("${imageHead}")
	private String imageHead;//我的头像图片

	@RequestMapping("/register")
	@ResponseBody
	public JSONObject insertRegister(@RequestBody UserInfoDto userInfoDto,
			HttpServletRequest request) {
		return userInfoFacade.insertRegister(userInfoDto);
	}

	@RequestMapping("/loginByPwd_app")
	@ResponseBody
	public JSONObject loginByPasswordApp(UserInfo userInfo, String loginType,
			HttpServletRequest request) {
		JSONObject json = userInfoFacade.loginByPassword(userInfo);
		// 登陆类型，app或者是weixin
		if (StringUtils.isBlank(loginType)) {
			loginType = "weixin";
		}
		if ("0".equals(json.get("errcode").toString())) {
			// 登陆成功,hall表示会议厅,meeting表示会议界面
			JSONObject userJson = json.getJSONObject("user");
			String headimgurl = userJson.get("headimgurl").toString();
			if (StringUtils.isNotBlank(headimgurl)
					|| !StringUtils.startsWith(headimgurl, "http://wx")) {
				headimgurl = fastdfsurl + headimgurl;
				userJson.put("headimgurl", headimgurl);
				json.put("user", userJson);
			}
			// 会议url
			json.put("meetingUrl", "user/returnPage?loginType=" + loginType
					+ "&userId=" + userJson.getString("userId")
					+ "&type=meeting");
			// 会议厅url
			json.put("meetingHallUrl", "user/returnPage?loginType=" + loginType
					+ "&userId=" + userJson.getString("userId") + "&type=hall");
		} else {
			json.put("meetingUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			json.put("meetingHallUrl",
					"user/returnPage?loginType=weixin&userId=9527");
		}
		return json;
	}

	/**
	 * app登陆，返回页面接口
	 * 
	 * @param loginType
	 *            登陆类型
	 * @param userId
	 * @param type
	 *            meeting和hall两种，判断盅两个页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/returnPage")
	public String returnPage(String loginType, String userId, String type, HttpServletRequest request, HttpServletResponse response) {
		UserWeixinInfo userWeixinInfo = userWeixinInfoFacade.selectByUserId(userId);
		// 登陆类型，app或者是weixin
		if (StringUtils.isBlank(loginType)) {
			loginType = "weixin";
		}
		if (StringUtils.isBlank(type)) {
			type = "meeting";
		}
		loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}
		if (null == userWeixinInfo) {
			return "redirect:/";
		} else {
			request.getSession().setAttribute("user", userWeixinInfo);
			// 写入cookie 设置时间为 秒 关闭浏览器还会存在
			CookieUtils.setCookie(request, response, "loginType", loginType,
					3600 * 24 * 7);
			CookieUtils.setCookie(request, response, "userId",
					userWeixinInfo.getUserId(), 3600 * 24 * 7);
			request.getSession().setAttribute("loginType", loginType);
			if ("meeting".equals(type)) {
				return "meeting/meet-index";
			} else {
				// 内部重定向，判断这个人有没有会议厅
				return "redirect:/meetingHall/room/";
			}
		}
	}

	@RequestMapping("/loginByPwd")
	public String loginByPassword(UserInfo userInfo, String loginType,ModelMap map, HttpServletRequest request,HttpServletResponse response) {

		JSONObject json = userInfoFacade.loginByPassword(userInfo);
		// 登陆类型，app或者是weixin
		loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}

		request.getSession().setAttribute("loginType", loginType);

		if ("0".equals(json.get("errcode").toString())) {
			// 登陆成功
			JSONObject user = json.getJSONObject("user");
			UserWeixinInfo userWeixinInfo = (UserWeixinInfo) JSONObject.toBean(user, UserWeixinInfo.class);
			String headimgurl = userWeixinInfo.getHeadimgurl();
			if (!("".equals(headimgurl) || null == headimgurl)) {
				if (!(headimgurl.startsWith("http"))) { // 判断用户的头像是否是默认头像
					userWeixinInfo.setHeadimgurl(fastdfsurl + headimgurl);
				}
			}else {
				userWeixinInfo.setHeadimgurl("/css/img/"+imageHead);
			}
			
			request.getSession().setAttribute("user", userWeixinInfo);
			// 写入cookie 设置时间为 秒 关闭浏览器还会存在
			CookieUtils.setCookie(request, response, "userId",
					userWeixinInfo.getUserId(), 3600 * 24 * 7);
			CookieUtils.setCookie(request, response, "loginType", loginType,
					3600 * 24 * 7);
			return "meeting/meet-index";
		}
		request.getSession().setAttribute("message", "用户名或密码错误");
		return "redirect:/";
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JSONObject exceptionHandler(Exception e) {
		JSONObject json = new JSONObject();
		json.put("errcode", "8001");
		json.put("errmsg", e.getMessage());
		e.printStackTrace();
		logger.error(e.getMessage());
		return json;
	}

	@RequestMapping("/meetindex")
	public String meetindex(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		return "meeting/meet-index";
	}

	/**
	 * 手机二维码登陆
	 * 
	 * @param userInfo
	 * @param loginType
	 * @param session
	 * @return
	 * @throws
	 */
	@RequestMapping("/loginByQRCode")
	@ResponseBody
	public Map<String, String> loginByQRCode(String userId, String redirect,HttpServletRequest request, HttpServletResponse response) {
		UserWeixinInfo userWeixinInfo = userWeixinInfoFacade.selectByUserId(userId);
		String loginType = HttpClientUtils.isApp(request);
		if (HttpClientUtils.isWeixin(request)) {
			loginType = "weixin";
		}
		request.getSession().setAttribute("loginType", loginType);
		request.getSession().setAttribute("user", userWeixinInfo);
		// 写入cookie 设置时间为 秒 关闭浏览器还会存在
		CookieUtils.setCookie(request, response, "userId",
				userWeixinInfo.getUserId(), 3600 * 24 * 7);
		CookieUtils.setCookie(request, response, "loginType", loginType,
				3600 * 24 * 7);
		Map<String, String> map = new HashMap<String, String>();
		DubboxResult dubboxResult = null;
		try {
			dubboxResult = roleModuleWriteFacade.updateQRCodeScan(userId,
					redirect);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("msg", "服务器异常");
		}
		if (Objects.equals("0", dubboxResult.getStatus())) {
			map.put("status", "200");
			map.put("msg", "success");
		}

		return map;
	}

	/* app上传头像 */
	@RequestMapping("/updateHeadimgurl")
	@ResponseBody
	public DubboxResult update(@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("userId") String userId, HttpServletRequest request) {
		if (file.getSize() == 0) {
			return DubboxResult.build("1", "上传文件出错", null);
		}
		if (file.getSize() > 300 * 1024) {
			return DubboxResult.build("1", "头像大小不能超过250k", null);
		}
		if (!isImage(file)) {
			return DubboxResult.build("1", "不支持的文件格式", null);
		}
		String originalFilename = file.getOriginalFilename();
		String suffix = StringUtils.substring(originalFilename,
				originalFilename.lastIndexOf(".") + 1);
		DiskFileItem filetemp = (DiskFileItem) file.getFileItem();
		File file_img = filetemp.getStoreLocation();
		String fileUrl = FastDFSClient.uploadFile(file_img, ObjectId.get()
				.toString() + "." + suffix);
		if (StringUtils.isBlank(fileUrl)) {
			return DubboxResult.build("1", "上传文件服务器失败", null);
		}
		int updateResult = userInfoFacade.updateHeadimgurl(userId, fileUrl);
		if (updateResult == 0) {
			return DubboxResult.build("1", "更新头像失败", null);
		}
		return DubboxResult.build("0", "ok", fastdfsurl + fileUrl);
	}

	// 判断文件类型是否符合要求
	public boolean isImage(CommonsMultipartFile file) {
		if (file.getSize() == 0) {
			return false;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename.indexOf(".") == -1) {
			return false;
		}
		String suffix = StringUtils.substring(originalFilename,
				originalFilename.lastIndexOf(".") + 1);
		return suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg")
				|| suffix.equalsIgnoreCase("jpeg")
				|| suffix.equalsIgnoreCase("bmp")
				|| suffix.equalsIgnoreCase("gif");
	}
}
