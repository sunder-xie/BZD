package com.bizideal.whoami.member.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.github.pagehelper.PageInfo;

/**
 * 个人会员信息服务
 * 
 * @author 作者 sy
 * @date 创建时间：2017-3-16 17:06:05
 * @version 1.0
 */
public interface MemberPersonInfoService extends BaseBiz<MemberPersonInfo> {

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
	 * 根据手机号查找个人会员信息
	 * 
	 * @param phone
	 * @return
	 */
	MemberPersonInfo selectByPhoneAndHallId(String phone, Integer hallId);

	/**
	 * 批量保存个人会员信息
	 * 
	 * @param list
	 * @return
	 */
	Integer insertList(List<MemberPersonInfo> list);

}
