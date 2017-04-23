package com.bizideal.whoami.user.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.im.entity.IMUser;
import com.bizideal.whoami.im.facade.IMUsersFacade;
import com.bizideal.whoami.user.dto.UserInfoDto;
import com.bizideal.whoami.user.entity.UserInfo;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.enums.UserEnum;
import com.bizideal.whoami.user.mapper.UserInfoMapper;
import com.bizideal.whoami.user.mapper.UserWeixinInfoMapper;
import com.bizideal.whoami.user.service.UserInfoService;
import com.bizideal.whoami.utils.MD5;
import com.bizideal.whoami.utils.ObjectId;
import com.bizideal.whoami.utils.ProtostuffConvert;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月5日 上午9:32:21
 * @version 1.0
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserWeixinInfoMapper userWeixinInfoMapper;
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	@Autowired
	private IMUsersFacade imUsersFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Override
	public JSONObject insertRegister(UserInfoDto userInfoDto) {
		JSONObject json = new JSONObject();
		byte[] msgCodeByte = redisClientTemplate.getObject(userInfoDto
				.getPhone());
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		if (StringUtils.isBlank(userInfoDto.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_EMPTY.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_EMPTY.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(userInfoDto.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		// 验证码验证结束
		UserInfo userInfo = new UserInfo();
		String userId = ObjectId.get().toString();
		String password = MD5.getMD5(userInfoDto.getPassword());
		// 先注册环信账号
		IMUser imUser = new IMUser(userId, password, "");
		String nodeString = imUsersFacade.createNewIMUserSingle(imUser);
		try {
			ObjectMapper mapper = new ObjectMapper();
			if (StringUtils.isBlank(nodeString)
					|| !"200".equals(mapper.readTree(nodeString)
							.get("statusCode").asText())) {
				json.put("errcode",
						UserEnum.USER_HUANXIN_REGISTER_FAILED.getErrcode());
				json.put("errmsg",
						UserEnum.USER_HUANXIN_REGISTER_FAILED.getErrmsg());
				return json;
			}
		} catch (IOException e) {
			e.printStackTrace();
			json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
			json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg());
			return json;
		}
		userInfo.setUserId(userId);
		userInfo.setPhone(userInfoDto.getPhone());
		userInfo.setPassword(password);
		userInfoMapper.insert(userInfo);
		UserWeixinInfo weixinInfo = new UserWeixinInfo();
		weixinInfo.setUserId(userId);
		weixinInfo.setSubscribeTime(new Date().getTime());
		userWeixinInfoMapper.insert(weixinInfo);
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("errmsg", UserEnum.USER_OK.getErrmsg());
		json.put("userId", userInfo.getUserId());
		return json;
	}

	@Override
	public JSONObject loginByPassword(UserInfo userInfo) {
		JSONObject json = new JSONObject();
		userInfo.setPassword(MD5.getMD5(userInfo.getPassword()));
		List<UserWeixinInfo> useInfo = userInfoMapper.login(userInfo);
		if (null == useInfo || useInfo.size() == 0) {
			json.put("errcode", UserEnum.USER_LOGIN_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_LOGIN_FAILED.getErrmsg());
			return json;
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("user", useInfo.get(0));
		return json;
	}

	@Override
	public JSONObject updateBindPhone(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		if (null == userWeixinInfo
				|| StringUtils.isBlank(userWeixinInfo.getUnionid())
				|| StringUtils.isBlank(userWeixinInfo.getPhone())
				|| StringUtils.isBlank(userWeixinInfo.getMsgCode())
				|| StringUtils.isBlank(userWeixinInfo.getPassword())) {
			json.put("errcode", 1);
			json.put("errmsg", "缺少参数");
			return json;
		}
		// 验证码校验
		byte[] msgCodeByte = redisClientTemplate.getObject(userWeixinInfo
				.getPhone());
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		// 验证码较验结束
		UserWeixinInfo weixinInfo = userWeixinInfoMapper
				.selectByUnionid(userWeixinInfo.getUnionid()); // 已经有的unionid数据行
		UserWeixinInfo weixinInfo2 = userInfoMapper
				.selectByPhone(userWeixinInfo.getPhone()); // 号码是否已经使用
		if (null == weixinInfo2) {
			// 手机号没有使用过
			weixinInfo2 = new UserWeixinInfo();
			UserInfo userInfo = new UserInfo();
			String userId = ObjectId.get().toString();
			weixinInfo2.setUserId(userId);
			userInfo.setUserId(userId);
			userInfo.setPhone(userWeixinInfo.getPhone());
			userInfo.setPassword(MD5.getMD5(userWeixinInfo.getPassword()));
			// 先注册环信账号
			IMUser imUser = new IMUser(userId, userInfo.getPassword(), null);
			String nodeString = imUsersFacade.createNewIMUserSingle(imUser);
			ObjectMapper mapper = new ObjectMapper();
			try {
				if (StringUtils.isBlank(nodeString)
						|| !"200".equals(mapper.readTree(nodeString)
								.get("statusCode").asText())) {
					json.put("errcode",
							UserEnum.USER_HUANXIN_REGISTER_FAILED.getErrcode());
					json.put("errmsg",
							UserEnum.USER_HUANXIN_REGISTER_FAILED.getErrmsg());
					return json;
				}
			} catch (IOException e) {
				e.printStackTrace();
				json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
				json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg());
				return json;
			}
			userInfoMapper.insert(userInfo); // 新建用户
			weixinInfo.setUserId(userId);
			int result = userInfoMapper.updateBindPhone(weixinInfo); // 微信表中与user_id关联
			if (result == 0) {
				json.put("errcode", UserEnum.USER_BINDPHONE_FAILED.getErrcode());
				json.put("errmsg", UserEnum.USER_BINDPHONE_FAILED.getErrmsg());
				return json;
			}
		} else {
			// 手机号已经被注册，合并到已经有user_id的数据行
			weixinInfo2.setUnionid(weixinInfo.getUnionid());
			weixinInfo2.setOpenid(weixinInfo.getOpenid());
			weixinInfo2.setSubscribe(weixinInfo.getSubscribe());
			weixinInfo2.setCountry(weixinInfo.getCountry());
			weixinInfo2.setProvince(weixinInfo.getProvince());
			weixinInfo2.setCity(weixinInfo.getCity());
			weixinInfo2.setLanguage(weixinInfo.getLanguage());
			weixinInfo2.setRemark(weixinInfo.getRemark());
			weixinInfo2.setGroupid(weixinInfo.getGroupid());
			weixinInfo2.setWeixin(userWeixinInfo.getWeixin());
			weixinInfo2.setSubscribeTime(weixinInfo.getSubscribeTime());
			// 判断用户是否有信息
			if (StringUtils.isBlank(weixinInfo2.getNickname())) {
				weixinInfo2.setNickname(weixinInfo.getNickname());
			}
			if (StringUtils.isBlank(weixinInfo2.getHeadimgurl())) {
				weixinInfo2.setHeadimgurl(weixinInfo.getHeadimgurl());
			}
			if (StringUtils.isBlank(weixinInfo2.getSex())) {
				weixinInfo2.setSex(weixinInfo.getSex());
			}
			// 先修改环信密码
			String userId = weixinInfo2.getUserId();
			String password = userWeixinInfo.getPassword();
			String md5Pwd = MD5.getMD5(password);
			String nodeString = imUsersFacade
					.modifyIMUserPasswordWithAdminToken(userId, md5Pwd);
			ObjectMapper mapper = new ObjectMapper();
			try {
				if (StringUtils.isBlank(nodeString)
						|| !"200".equals(mapper.readTree(nodeString)
								.get("statusCode").asText())) {
					json.put("errcode",
							UserEnum.USER_IM_UP_PWD_FAILED.getErrcode());
					json.put("errmsg",
							UserEnum.USER_IM_UP_PWD_FAILED.getErrmsg());
					return json;
				}
			} catch (IOException e) {
				e.printStackTrace();
				json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
				json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg());
				return json;
			}
			// 修改本地密码和其它信息
			int result1 = userInfoMapper.updateMergeWeixin(weixinInfo2);
			int result2 = userInfoMapper.updatePassword(
					weixinInfo2.getUserId(), md5Pwd);
			if (result1 == 0 || result2 == 0) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				json.put("errcode", UserEnum.USER_BINDPHONE_FAILED.getErrcode());
				json.put("errmsg", UserEnum.USER_BINDPHONE_FAILED.getErrmsg());
				return json;
			}
			userInfoMapper.deleteMergeWeixin(weixinInfo.getUnionid());
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("errmsg", UserEnum.USER_OK.getErrmsg());
		json.put("user",
				userWeixinInfoMapper.selectByUserId(weixinInfo2.getUserId()));
		return json;
	}

	@Override
	public JSONObject updatePassword(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		String userId = userWeixinInfo.getUserId();
		String password = userWeixinInfo.getPassword();
		String phone = userWeixinInfo.getPhone();
		if (null == userWeixinInfo || StringUtils.isBlank(userId)
				|| StringUtils.isBlank(password) || StringUtils.isBlank(phone)
				|| StringUtils.isBlank(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_PARAMETER_EMTPY.getErrcode());
			json.put("errmsg", UserEnum.USER_PARAMETER_EMTPY.getErrmsg());
			return json;
		}
		// 验证码校验
		byte[] msgCodeByte = redisClientTemplate.getObject(userWeixinInfo
				.getPhone());
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		UserWeixinInfo weixinInfo = userWeixinInfoMapper.selectByUserId(userId);
		if (null == weixinInfo) {
			json.put("errcode", UserEnum.USER_USER_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_USER_NOT_FOUND.getErrmsg());
			return json;
		}
		// 先修改环信密码
		String md5Pwd = MD5.getMD5(password);
		if (md5Pwd.equals(weixinInfo.getPassword())) {
			//判断新旧密码是否相同，相同不让修改
			json.put("errcode", UserEnum.USER_IM_UP_PWD_SAME.getErrcode());
			json.put("errmsg", UserEnum.USER_IM_UP_PWD_SAME.getErrmsg());
			return json;
		}
		String nodeString = imUsersFacade.modifyIMUserPasswordWithAdminToken(
				userId, md5Pwd);
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (StringUtils.isBlank(nodeString)
					|| !"200".equals(mapper.readTree(nodeString)
							.get("statusCode").asText())) {
				json.put("errcode", UserEnum.USER_IM_UP_PWD_FAILED.getErrcode());
				json.put("errmsg", UserEnum.USER_IM_UP_PWD_FAILED.getErrmsg());
				return json;
			}
		} catch (IOException e) {
			e.printStackTrace();
			json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
			json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg());
			return json;
		}
		int result = userInfoMapper.updatePassword(userId, md5Pwd);
		if (result == 0) {
			json.put("errcode",
					UserEnum.USER_PASSWORD_UPDATE_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_PASSWORD_UPDATE_FAILED.getErrmsg());
			return json;
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("errmsg", UserEnum.USER_OK.getErrmsg());
		return json;
	}

	@Override
	public JSONObject updateForgetPassword(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		String password = userWeixinInfo.getPassword();
		String phone = userWeixinInfo.getPhone();
		if (null == userWeixinInfo || StringUtils.isBlank(password)
				|| StringUtils.isBlank(phone)
				|| StringUtils.isBlank(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_PARAMETER_EMTPY.getErrcode());
			json.put("errmsg", UserEnum.USER_PARAMETER_EMTPY.getErrmsg());
			return json;
		}
		// 验证码校验
		byte[] msgCodeByte = redisClientTemplate.getObject(userWeixinInfo
				.getPhone());
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		UserWeixinInfo weixinInfo = userInfoMapper.selectByPhone(phone);

		if (null == weixinInfo) {
			json.put("errcode", UserEnum.USER_USER_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_USER_NOT_FOUND.getErrmsg());
			return json;
		}
		// 先修改环信密码
		String md5Pwd = MD5.getMD5(password);
		String nodeString = imUsersFacade.modifyIMUserPasswordWithAdminToken(
				weixinInfo.getUserId(), md5Pwd);
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (StringUtils.isBlank(nodeString)
					|| !"200".equals(mapper.readTree(nodeString)
							.get("statusCode").asText())) {
				json.put("errcode", UserEnum.USER_IM_UP_PWD_FAILED.getErrcode());
				json.put("errmsg", UserEnum.USER_IM_UP_PWD_FAILED.getErrmsg());
				return json;
			}
		} catch (IOException e) {
			e.printStackTrace();
			json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
			json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg());
			return json;
		}
		int result = userInfoMapper.updatePassword(weixinInfo.getUserId(),
				md5Pwd);
		if (result == 0) {
			json.put("errcode",
					UserEnum.USER_PASSWORD_UPDATE_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_PASSWORD_UPDATE_FAILED.getErrmsg());
			return json;
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		json.put("errmsg", UserEnum.USER_OK.getErrmsg());
		return json;
	}

	@Override
	public int updateImgAndName(UserWeixinInfo userWeixinInfo) {
		return userWeixinInfoMapper.updateImgAndName(userWeixinInfo);
	}

	@Override
	public int updateRealname(String userId, String realName) {
		return userInfoMapper.updateRealname(userId, realName);
	}

	@Override
	public int updateNickname(String userId, String nickname) {
		return userInfoMapper.updateNickname(userId, nickname);
	}

	@Override
	public int updateHeadimgurl(String userId, String headimgurl) {
		return userInfoMapper.updateHeadimgurl(userId, headimgurl);
	}

	@Override
	public JSONObject loginByMsgCode(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		String phone = userWeixinInfo.getPhone();
		String code = userWeixinInfo.getMsgCode();
		String loginType = userWeixinInfo.getLoginType();
		// 验证码校验
		byte[] msgCodeByte = redisClientTemplate.getObject(phone);
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(
				String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(code)) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		UserWeixinInfo weixinInfo = userInfoMapper.selectByPhone(phone);
		// 登陆类型，app或者是weixin
		if (StringUtils.isBlank(loginType)) {
			loginType = "weixin";
		}
		if (null != weixinInfo) {
			// 登陆成功,hall表示会议厅,meeting表示会议界面
			if (StringUtils.isNotBlank(weixinInfo.getHeadimgurl())
					&& !StringUtils.startsWith(weixinInfo.getHeadimgurl(),
							"http://wx")) {
				weixinInfo.setHeadimgurl(fastdfsurl
						+ weixinInfo.getHeadimgurl());
			}
			// 会议url
			json.put("errcode", UserEnum.USER_OK.getErrcode());
			json.put("errmsg", UserEnum.USER_OK.getErrmsg());
			json.put("user", weixinInfo);
			json.put("meetingUrl", "user/returnPage?loginType=" + loginType
					+ "&userId=" + weixinInfo.getUserId() + "&type=meeting");
			// 会议厅url
			json.put("meetingHallUrl", "user/returnPage?loginType=" + loginType
					+ "&userId=" + weixinInfo.getUserId() + "&type=hall");
		} else {
			json.put("errcode", UserEnum.USER_LOGIN_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_LOGIN_FAILED.getErrmsg());
			json.put("user", null);
			json.put("meetingUrl",
					"user/returnPage?loginType=weixin&userId=9527");
			json.put("meetingHallUrl",
					"user/returnPage?loginType=weixin&userId=9527");
		}
		return json;
	}
}
