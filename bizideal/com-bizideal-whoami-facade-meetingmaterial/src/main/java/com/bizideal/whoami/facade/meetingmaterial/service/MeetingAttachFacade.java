package com.bizideal.whoami.facade.meetingmaterial.service;

import java.util.List;

import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingAttachFacade
 * @Description MeetingAttachODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:38:47
 */
public interface MeetingAttachFacade {
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	MeetingAttach queryById(int id);


	/**
	 * 根据条件查询数据列表
	 * @param record
	 * @return
	 */
	List<MeetingAttach> queryListByWhere(MeetingAttach record);

	/**
	 * 分页查询数据列表
	 * 
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	PageInfo<MeetingAttach> queryPageListByWhere(Integer page, Integer rows, MeetingAttach record);

	/**
	 * 有选择的保存，null的属性不会保存，会使用数据库默认值
	 * @param t
	 * @return
	 */
	Integer saveSelective(MeetingAttach t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 * @param t
	 * @return
	 */
	Integer updateSelective(MeetingAttach t);

	/**
	 * 根据id逻辑删除数据
	 * @param t
	 * @return
	 */
	Integer deleteLogicById(MeetingAttach t);


}
