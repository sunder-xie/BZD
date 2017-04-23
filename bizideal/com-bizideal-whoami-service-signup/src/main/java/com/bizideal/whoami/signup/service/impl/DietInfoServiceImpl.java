package com.bizideal.whoami.signup.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.signup.entity.DietInfo;
import com.bizideal.whoami.signup.enums.SignupEnum;
import com.bizideal.whoami.signup.mapper.DietInfoMapper;
import com.bizideal.whoami.signup.service.DietInfoService;
import com.github.pagehelper.PageHelper;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午10:55:11
 * @version 1.0
 */
@Service("dietInfoService")
public class DietInfoServiceImpl implements DietInfoService {

	@Autowired
	private DietInfoMapper dietInfoMapper;

	@Override
	public JSONObject selectByPageSelf(DietInfo dietInfo) {
		JSONObject json = new JSONObject();
		PageHelper.startPage(dietInfo.getPageNum(), dietInfo.getPageSize());
		Example example = new Example(DietInfo.class);
		example.createCriteria().andEqualTo("hallId", dietInfo.getHallId())
				.andEqualTo("meeId", dietInfo.getMeeId());
		List<DietInfo> dietInfos = dietInfoMapper.selectByExample(example);
		if (null != dietInfos && dietInfos.size() > 0) {
			json.put("count", dietInfoMapper.selectCountByExample(example));
			json.put("rows", JSONArray.fromObject(dietInfos));
			return json;
		}
		json.put("count", 0);
		return json;
	}

	@Override
	public JSONObject selectByPage(DietInfo dietInfo) {
		JSONObject json = new JSONObject();
		PageHelper.startPage(dietInfo.getPageNum(), dietInfo.getPageSize());
		List<DietInfo> dietInfos = dietInfoMapper.selectByPage(
				dietInfo.getHallId(), dietInfo.getMeeId());
		if (null != dietInfos && dietInfos.size() > 0) {
			int count = dietInfoMapper.selectCountByPage(dietInfo.getHallId(),
					dietInfo.getMeeId());
			json.put("count", count);
			json.put("rows", JSONArray.fromObject(dietInfos));
			SignupEnum.SIGNUP_OK.getErrcode();
			return json;
		}
		json.put("count", 0);
		return json;
	}

	@Override
	public JSONObject updateDiet(DietInfo dietInfo) {
		JSONObject json = new JSONObject();
		int result = dietInfoMapper.updateDiet(dietInfo.getDietId(),
				dietInfo.getDietDisp());
		if (result == 0) {
			json.put("errcode", SignupEnum.SIGNUP_DIET_UPDATE.getErrcode());
			json.put("errmsg", SignupEnum.SIGNUP_DIET_UPDATE.getErrmsg());
			return json;
		}
		json.put("errcode", SignupEnum.SIGNUP_OK.getErrcode());
		json.put("errmsg", SignupEnum.SIGNUP_OK.getErrmsg());
		return json;
	}

	@Override
	public JSONObject deleteDiet(DietInfo dietInfo) {
		JSONObject json = new JSONObject();
		int result = dietInfoMapper.deleteByPrimaryKey(dietInfo.getDietId());
		if (result == 0) {
			json.put("errcode", SignupEnum.SIGNUP_DIET_DELETE.getErrcode());
			json.put("errmsg", SignupEnum.SIGNUP_DIET_DELETE.getErrmsg());
			return json;
		}
		json.put("errcode", SignupEnum.SIGNUP_OK.getErrcode());
		json.put("errmsg", SignupEnum.SIGNUP_OK.getErrmsg());
		return json;
	}

	@Override
	public JSONObject insertDiet(DietInfo dietInfo) {
		JSONObject json = new JSONObject();
		dietInfoMapper.insert(dietInfo);
		json.put("errcode", SignupEnum.SIGNUP_OK.getErrcode());
		json.put("errmsg", SignupEnum.SIGNUP_OK.getErrmsg());
		return json;
	}

}
