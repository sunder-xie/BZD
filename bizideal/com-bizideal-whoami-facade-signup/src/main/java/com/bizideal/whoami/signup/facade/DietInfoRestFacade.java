package com.bizideal.whoami.signup.facade;

import com.bizideal.whoami.signup.entity.DietInfo;

import net.sf.json.JSONObject;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午11:08:01
 * @version 1.0 类说明
 */
public interface DietInfoRestFacade {
	/**
	 * 查看会议厅私有的饮食属性
	 * 
	 * @param dietInfo
	 * @return
	 */
	JSONObject selectByPageSelf(DietInfo dietInfo);

	/**
	 * 查看会议厅私有+公有的饮食属性
	 * 
	 * @param dietInfo
	 * @return
	 */
	JSONObject selectByPage(DietInfo dietInfo);

	/**
	 * 属性更新
	 * 
	 * @param dietInfo
	 * @return
	 */
	JSONObject updateDiet(DietInfo dietInfo);

	/**
	 * 删除一条饮食数据
	 * 
	 * @param dietInfo
	 * @return
	 */
	JSONObject deleteDiet(DietInfo dietInfo);

	/**
	 * 新增一条饮食数据
	 * 
	 * @param dietInfo
	 * @return
	 */
	JSONObject insertDiet(DietInfo dietInfo);
}
