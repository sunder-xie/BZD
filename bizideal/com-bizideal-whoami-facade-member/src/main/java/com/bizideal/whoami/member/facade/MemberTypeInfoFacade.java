package com.bizideal.whoami.member.facade;

import java.util.List;

import com.bizideal.whoami.member.entity.MemberTypeInfo;

/**
 * 会员角色信息接口
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberTypeInfoFacade {

	/**
	 * 保存会员角色信息
	 * 
	 * @param t
	 * @return
	 */
	Integer save(MemberTypeInfo t);

	/**
	 * 更新会员角色信息
	 * 
	 * @param t
	 * @return
	 */
	Integer update(MemberTypeInfo t);

	/**
	 * 根据id删除会员角色信息
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteById(Integer id);

	/**
	 * 根据id查询会员角色信息
	 * 
	 * @param id
	 * @return
	 */
	MemberTypeInfo selectById(Integer id);

	/**
	 * 根据会议角色名称和分类查询会员角色信息
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	MemberTypeInfo selectByNameAndType(Integer hallId, String name, Integer type);

	/**
	 * 根据会议厅id查询会员角色信息列表
	 * 
	 * @param hallId
	 * @return
	 */
	List<MemberTypeInfo> selectListByHallId(Integer hallId);

	/**
	 * 根据会议厅id和分类查询会员角色信息列表
	 * 
	 * @param hallId
	 * @param type
	 * @return
	 */
	List<MemberTypeInfo> selectListByHallIdAndType(Integer hallId, Integer type);

}
