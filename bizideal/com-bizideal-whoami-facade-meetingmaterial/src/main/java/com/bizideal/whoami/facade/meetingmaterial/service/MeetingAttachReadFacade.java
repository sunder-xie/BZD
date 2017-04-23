package com.bizideal.whoami.facade.meetingmaterial.service;

import java.util.List;

import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月16日 下午2:42:05
 * @version 1.0
 */
public interface MeetingAttachReadFacade {
	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	MeetingAttach queryById(int id);

	/**
	 * 根据条件查询数据列表
	 * 
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
	PageInfo<MeetingAttach> queryPageListByWhere(Integer page, Integer rows,
			MeetingAttach record);

}
