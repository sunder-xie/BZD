package com.bizideal.whoami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.sencevote.entity.SenceCaseInfo;

/**
 * 现场案例信息
 * 
 * @ClassName SenceCaseInfoMapper
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月9日
 */
public interface SenceCaseInfoMapper extends Mapper<SenceCaseInfo> {

	/**
	 * 根据会议ID查询现场投票的所有案例信息
	 * 
	 * @param id
	 * @return
	 */
	List<SenceCaseInfo> selectAllSenceCaseInfoByMeeId(Integer meeId);

	/**
	 * 根据会议ID和会议信息分页查询案例
	 * 
	 * @param senceCaseInfo
	 * @return
	 */
	List<SenceCaseInfo> selectSenceCaseInfoByMeeIdCondition(SenceCaseInfo senceCaseInfo);

	/**
	 * 根据用户ID和会议ID查询投票的案例信息
	 * 
	 * @param userId
	 * @param meeId
	 * @return
	 */
	List<SenceCaseInfo> selectSenceCaseInfoByUserIdMeeId(@Param("userId") String userId,
														 @Param("meeId") Integer meeId);

}