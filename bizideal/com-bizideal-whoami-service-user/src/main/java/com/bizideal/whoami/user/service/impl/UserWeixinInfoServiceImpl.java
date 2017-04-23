package com.bizideal.whoami.user.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.user.dto.SignUpMeetingDto;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.enums.UserEnum;
import com.bizideal.whoami.user.mapper.UserWeixinInfoMapper;
import com.bizideal.whoami.user.service.UserWeixinInfoService;
import com.bizideal.whoami.utils.ProtostuffConvert;
import com.bizideal.whoami.utils.cloopen.CCPRestSDK;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月1日 下午3:29:16
 * @version 1.0 类说明
 */
@Service("userWeixinInfoService")
public class UserWeixinInfoServiceImpl implements UserWeixinInfoService {

	@Autowired
	private UserWeixinInfoMapper userWeixinInfoMapper;
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	@Value("${imageHead}")
	private String imageHead;// 我的头像图片

	@Override
	public UserWeixinInfo insertWeixinLogin(UserWeixinInfo userWeixinInfo) {
		if (null == userWeixinInfo || StringUtils.isBlank(userWeixinInfo.getUnionid())) {
			return null;
		}
		UserWeixinInfo weixinInfo = userWeixinInfoMapper.selectByUnionid(userWeixinInfo.getUnionid());
		if (null == weixinInfo) {
			// 此微信用户没有登陆过系统
			String sex = userWeixinInfo.getSex();
			// 作性别转换，1为男，2为女
			if (StringUtils.isBlank(sex) || "1".equals(sex)) {
				sex = "男";
			} else {
				sex = "女";
			}
			userWeixinInfo.setSex(sex);
			userWeixinInfo.setSubscribeTime(System.currentTimeMillis());
			userWeixinInfoMapper.insert(userWeixinInfo);
			return userWeixinInfo;
		}
		return weixinInfo;
	}

	@Override
	public JSONObject sendMsgCode(String phone) {
		JSONObject json = new JSONObject();
		String selectPhone = userWeixinInfoMapper.selectPhone(phone);
		if (StringUtils.isNotBlank(selectPhone)) {
			json.put("errcode", UserEnum.USER_MSG_USER_ISREGISTERD.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_USER_ISREGISTERD.getErrmsg());
			return json;
		}
		HashMap<String, Object> map = CCPRestSDK.sendMsgCode(phone, 2);
		if ("000000".equals(map.get("statusCode"))) {
			json.put("errcode", UserEnum.USER_OK.getErrcode());
			json.put("errmsg", UserEnum.USER_OK.getErrmsg());
			// 短信验证码放在redis中
			ProtostuffConvert<String> proto = new ProtostuffConvert<String>(String.class);
			byte[] convert = proto.convert(map.get("code").toString());
			redisClientTemplate.putObject(phone, 120, convert); // redis中保存120秒
			return json;
		}
		json.put("errcode", UserEnum.USER_MSG_SEND_FAILED.getErrcode());
		json.put("errmsg", UserEnum.USER_MSG_SEND_FAILED.getErrmsg());
		return json;
	}

	@Override
	public JSONObject sendMsgCodeToAnyOne(String phone) {
		JSONObject json = new JSONObject();
		HashMap<String, Object> map = CCPRestSDK.sendMsgCode(phone, 2);
		if ("000000".equals(map.get("statusCode"))) {
			json.put("errcode", UserEnum.USER_OK.getErrcode());
			json.put("errmsg", UserEnum.USER_OK.getErrmsg());
			// 短信验证码放在redis中
			ProtostuffConvert<String> proto = new ProtostuffConvert<String>(String.class);
			byte[] convert = proto.convert(map.get("code").toString());
			redisClientTemplate.putObject(phone, 120, convert); // redis中保存120秒
			return json;
		}
		json.put("errcode", UserEnum.USER_MSG_SEND_FAILED.getErrcode());
		json.put("errmsg", UserEnum.USER_MSG_SEND_FAILED.getErrmsg());
		return json;
	}

	@Override
	public JSONObject sendMsgCodeToHasRegistered(String phone) {
		JSONObject json = new JSONObject();
		String selectPhone = userWeixinInfoMapper.selectPhone(phone);
		if (StringUtils.isBlank(selectPhone)) {
			json.put("errcode", UserEnum.USER_MSG_USER_NOREGISTERD.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_USER_NOREGISTERD.getErrmsg());
			return json;
		}
		HashMap<String, Object> map = CCPRestSDK.sendMsgCode(phone, 2);
		if ("000000".equals(map.get("statusCode"))) {
			json.put("errcode", UserEnum.USER_OK.getErrcode());
			json.put("errmsg", UserEnum.USER_OK.getErrmsg());
			// 短信验证码放在redis中
			ProtostuffConvert<String> proto = new ProtostuffConvert<String>(String.class);
			byte[] convert = proto.convert(map.get("code").toString());
			redisClientTemplate.putObject(phone, 120, convert); // redis中保存120秒
			return json;
		}
		json.put("errcode", UserEnum.USER_MSG_SEND_FAILED.getErrcode());
		json.put("errmsg", UserEnum.USER_MSG_SEND_FAILED.getErrmsg());
		return json;
	}

	@Override
	public int updateSignUpInfo(UserWeixinInfo weixinInfo) {
		return userWeixinInfoMapper.updateSignUpInfo(weixinInfo);
	}

	@Override
	public JSONObject updateCreateHallStep2(UserWeixinInfo userWeixinInfo) {
		JSONObject json = new JSONObject();
		byte[] msgCodeByte = redisClientTemplate.getObject(userWeixinInfo.getPhone());
		if (null == msgCodeByte) {
			json.put("errcode", UserEnum.USER_MSG_NOT_FOUND.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_NOT_FOUND.getErrmsg());
			return json;
		}
		if (StringUtils.isBlank(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_EMPTY.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_EMPTY.getErrmsg());
			return json;
		}
		ProtostuffConvert<String> proto = new ProtostuffConvert<String>(String.class);
		String msgCode = proto.convert(msgCodeByte);
		if (!msgCode.equals(userWeixinInfo.getMsgCode())) {
			json.put("errcode", UserEnum.USER_MSG_ERROR.getErrcode());
			json.put("errmsg", UserEnum.USER_MSG_ERROR.getErrmsg());
			return json;
		}
		// 验证码验证结束
		int result = userWeixinInfoMapper.updateCreateHallStep2(userWeixinInfo);
		if (0 == result) {
			json.put("errcode", UserEnum.USER_UPDATE_NAMEANDWEIXIN_FAILED.getErrcode());
			json.put("errmsg", UserEnum.USER_UPDATE_NAMEANDWEIXIN_FAILED.getErrmsg());
			return json;
		}
		json.put("errcode", UserEnum.USER_OK.getErrcode());
		// 用户信息有更新，返回到前台
		json.put("user", userWeixinInfoMapper.selectByUserId(userWeixinInfo.getUserId()));
		return json;
	}

	@Override
	public UserWeixinInfo selectByUserId(String userId) {
		UserWeixinInfo userWeixinInfo = userWeixinInfoMapper.selectByUserId(userId);
		if (null != userWeixinInfo && StringUtils.isNotBlank(userWeixinInfo.getHeadimgurl())
				&& !StringUtils.startsWith(userWeixinInfo.getHeadimgurl(), "http://wx")) {
			userWeixinInfo.setHeadimgurl(fastdfsurl + userWeixinInfo.getHeadimgurl());
		} else if (!StringUtils.startsWith(userWeixinInfo.getHeadimgurl(), "http://wx")) {
			userWeixinInfo.setHeadimgurl("/css/img/" + imageHead);
		}
		return userWeixinInfo;
	}

	@Override
	public List<UserWeixinInfo> selectByUserIds(List<String> userIds) {
		List<UserWeixinInfo> userWeixinInfos = userWeixinInfoMapper.selectByUserIds(userIds);
		for (UserWeixinInfo userWeixinInfo : userWeixinInfos) {
			if (StringUtils.isBlank(userWeixinInfo.getHeadimgurl()) || StringUtils.startsWith(userWeixinInfo.getHeadimgurl(), "http://wx")) {
				continue;
			}
			userWeixinInfo.setHeadimgurl(fastdfsurl + userWeixinInfo.getHeadimgurl());
		}
		return userWeixinInfos;
	}

	@Override
	public PageInfo<UserWeixinInfo> selectBySignUp(SignUpMeetingDto signUpMeetingDto) {
		PageHelper.startPage(signUpMeetingDto.getPageNum(), signUpMeetingDto.getPageSize());
		List<UserWeixinInfo> selectBySignUp = userWeixinInfoMapper.selectBySignUp(signUpMeetingDto);
		return new PageInfo<UserWeixinInfo>(selectBySignUp);
	}

	@Override
	public int updateSignup(SignUpInfoEditDto signUpInfoEditDto) {
		int updatePhone = userWeixinInfoMapper.updatePhone(signUpInfoEditDto);
		if (updatePhone == 0) {
			return 0;
		}
		int updateSignUp = userWeixinInfoMapper.updateSignUp(signUpInfoEditDto);
		if (updateSignUp == 0) {
			// 手动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		return 1;
	}

}
