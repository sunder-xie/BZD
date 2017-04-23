package com.bizideal.whoami.member.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.member.entity.MemberUnitInfo;
import com.github.pagehelper.PageInfo;

/**
 * 组织会员信息接口
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberUnitInfoService extends BaseBiz<MemberUnitInfo> {

	/**
	 * 根据会议厅id和关键字查询组织会员信息列表
	 * 
	 * @param hallId
	 * @param str
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MemberUnitInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows);

	/**
	 * 根据会员分类id查询组织会员信息列表
	 * 
	 * @param typeId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MemberUnitInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows);

	/**
	 * 根据组织名称和会议厅id查询组织会员信息
	 * 
	 * @param name
	 * @param hallId
	 * @return
	 */
	MemberUnitInfo selectByUnitNameAndHallId(String name, Integer hallId);

	/**
	 * 批量保存组织会员信息
	 * 
	 * @param list
	 * @return
	 */
	Integer insertList(List<MemberUnitInfo> list);

	/**
	 * 根据会议id查询该会议下的组织会员信息
	 * 
	 * @param meeId
	 * @return
	 */
	List<MemberUnitInfo> selectListByMeeId(Integer meeId, String str);

}
