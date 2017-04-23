package com.bizideal.whoami.facade.meetingmaterial.service;

import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月16日 下午2:42:16
 * @version 1.0
 */
public interface MeetingAttachWriteFacade {

	/**
	 * 有选择的保存，null的属性不会保存，会使用数据库默认值
	 * 
	 * @param t
	 * @return
	 */
	Integer saveSelective(MeetingAttach t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 * 
	 * @param t
	 * @return
	 */
	Integer updateSelective(MeetingAttach t);

	/**
	 * 根据id逻辑删除数据
	 * 
	 * @param t
	 * @return
	 */
	Integer deleteLogicById(MeetingAttach t);

	/**
	 * 用户选择覆盖时，将其他文件的删除标志置为1
	 * 
	 * @param meetingAttach
	 * @return
	 */
	int updateCoverOther(MeetingAttach meetingAttach);
	
}
