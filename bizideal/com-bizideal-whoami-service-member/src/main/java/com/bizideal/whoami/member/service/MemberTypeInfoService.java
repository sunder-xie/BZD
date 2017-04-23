package com.bizideal.whoami.member.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.member.entity.MemberTypeInfo;

/**
 * 会员角色信息服务
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberTypeInfoService extends BaseBiz<MemberTypeInfo> {

	/**
	 * 根据会议厅id查询会员角色信息列表
	 * 
	 * @param hallId
	 * @return
	 */
	List<MemberTypeInfo> selectListByHallId(Integer hallId);

	/**
	 * 根据会议角色名称和分类查询会员角色信息
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	MemberTypeInfo selectByNameAndType(Integer hallId, String name, Integer type);

	/**
	 * 根据会议厅id和分类查询会员角色信息列表
	 * 
	 * @param hallId
	 * @param type
	 * @return
	 */
	List<MemberTypeInfo> selectListByHallIdAndType(Integer hallId, Integer type);

}
