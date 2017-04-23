package com.bizideal.whoami.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.member.entity.MemberPersonInfo;

/**
 * 个人会员信息Dao
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberPersonInfoMapper extends Mapper<MemberPersonInfo> {

	/**
	 * 批量保存个人会员信息
	 * 
	 * @param list
	 * @return
	 */
	Integer insertList(@Param("list") List<MemberPersonInfo> list);

	/**
	 * 根据会议厅id和关键字查找个人会员信息列表
	 * 
	 * @param record
	 * @return
	 */
	List<MemberPersonInfo> selectListByHallIdAndString(@Param("hallId") Integer hallId, @Param("str") String str);
}