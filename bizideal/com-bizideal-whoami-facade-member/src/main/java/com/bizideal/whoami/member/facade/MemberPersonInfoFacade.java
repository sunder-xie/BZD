package com.bizideal.whoami.member.facade;

import java.util.List;

import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.github.pagehelper.PageInfo;

/**
 * 个人会员信息接口
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberPersonInfoFacade {

	/**
	 * 保存个人会员信息
	 * 
	 * @param t
	 * @return
	 */
	Integer save(MemberPersonInfo t);

	/**
	 * 更新个人会员信息
	 * 
	 * @param t
	 * @return
	 */
	Integer update(MemberPersonInfo t);

	/**
	 * 根据id删除个人会员信息
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteById(Integer id);

	/**
	 * 根据手机号查找个人会员信息
	 * 
	 * @param phone
	 * @return
	 */
	MemberPersonInfo selectByPhoneAndHallId(String phone, Integer hallId);

	/**
	 * 根据会议厅id查找个人会员信息列表
	 * 
	 * @param hallId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MemberPersonInfo> selectListByHallId(Integer hallId, Integer page, Integer rows);

	/**
	 * 根据会议厅id和关键字查找个人会员信息列表
	 * 
	 * @param hallId
	 * @param str
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MemberPersonInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows);

	/**
	 * 根据会员分类id查找个人会员信息列表
	 * 
	 * @param typeId
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<MemberPersonInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows);

	/**
	 * 批量保存个人会员信息
	 * 
	 * @param list
	 * @return
	 */
	Integer insertList(List<MemberPersonInfo> list);
}
