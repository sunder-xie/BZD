package com.bizideal.whoami.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.member.entity.MemberUnitInfo;

/**
 * 组织会员信息Dao
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberUnitInfoMapper extends Mapper<MemberUnitInfo> {

	/**
	 * 根据会议厅id和关键字查询组织会员信息列表
	 * 
	 * @param hallId
	 * @param str
	 * @return
	 */
	List<MemberUnitInfo> selectListByHallIdAndString(@Param("hallId") Integer hallId, @Param("str") String str);

	/**
	 * 批量保存组织会员信息
	 * 
	 * @param list
	 * @return
	 */
	Integer insertList(@Param("list") List<MemberUnitInfo> list);

	/**
	 * 根据会议id查询该会议下的组织会员信息
	 * 
	 * @param meeId
	 * @return
	 */
	List<MemberUnitInfo> selectListByMeeId(@Param("meeId") Integer meeId, @Param("str") String str);
}