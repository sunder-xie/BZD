package com.bizideal.whoami.service;

import java.util.List;

import com.bizideal.whoami.sencevote.entity.SenceCaseInfo;
import com.github.pagehelper.PageInfo;

/**
 * 现场案例信息Service
 * 
 * @ClassName SenceCaseInfoService
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
public interface SenceCaseInfoService {

	/**
	 * 插入一条现场投票案例信息
	 * 
	 * @param senceCaseInfo
	 * @return
	 */
	int insert(SenceCaseInfo senceCaseInfo);

	/**
	 * 更新一条现场投票案例信息
	 * 
	 * @param senceCaseInfo
	 * @return
	 */
	int update(SenceCaseInfo senceCaseInfo);

	/**
	 * 删除一条现场投票案例信息
	 * 
	 * @param senceCaseInfo
	 * @return
	 */
	int delete(SenceCaseInfo senceCaseInfo);

	/**
	 * 根据ID查询一条现场投票案例信息
	 * 
	 * @param id
	 * @return
	 */
	SenceCaseInfo selectSenceCaseInfoById(Integer id);

	/**
	 * 根据会议ID查询会议下的所有现场投票案例信息
	 * 
	 * @param meeId
	 * @return
	 */
	List<SenceCaseInfo> selectAllSenceCaseInfoByMeeId(Integer meeId);

	/**
	 * 根据会议ID和案例的其他信息模糊分页查询案例信息
	 * 
	 * @param senceCaseInfo
	 * @return
	 */
	PageInfo<SenceCaseInfo> selectSenceCaseInfoByMeeIdCondition(SenceCaseInfo senceCaseInfo);

	/**
	 * 根据会议ID和用户ID查询用户在该会议下投票的案例信息
	 * 
	 * @param userId
	 * @param meeId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<SenceCaseInfo> selectSenceCaseInfoByUserIdMeeId(String userId, Integer meeId, int pageNum,
															 int pageSize);
}
