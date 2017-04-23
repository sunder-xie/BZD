package com.bizideal.whoami.signup.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.signup.entity.DietInfo;
import com.bizideal.whoami.signup.service.DietInfoService;

import net.sf.json.JSONObject;


/**
 * 作用：餐饮选择
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午11:08:39
 * @version 1.0
 */
@Component("dietInfoFacade")
public class DietInfoFacadeImpl implements DietInfoFacade {

	@Autowired
	private DietInfoService dietInfoService;

	@Override
	public JSONObject selectByPageSelf(DietInfo dietInfo) {
		return dietInfoService.selectByPageSelf(dietInfo);
	}

	@Override
	public JSONObject selectByPage(DietInfo dietInfo) {
		return dietInfoService.selectByPage(dietInfo);
	}

	@Override
	public JSONObject updateDiet(DietInfo dietInfo) {
		return dietInfoService.updateDiet(dietInfo);
	}

	@Override
	public JSONObject deleteDiet(DietInfo dietInfo) {
		return dietInfoService.deleteDiet(dietInfo);
	}

	@Override
	public JSONObject insertDiet(DietInfo dietInfo) {
		return dietInfoService.insertDiet(dietInfo);
	}

}
