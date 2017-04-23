package com.bizideal.whoami.core.meeting.biz;

import java.util.List;
import java.util.Map;

import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @ClassName SubMeetingInfoBiz
 * @Description TODO(子会议信息业务层)
 * @Author yt.Cui
 * @Date 2017年3月10日
 */
public interface SubMeetingInfoBiz {

	/**
	 * 插入一条子会议记录
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;

	/**
	 * 删除一条子会议记录
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 物理删除一条子会议数据
	 * @param subMeetingInfo
	 * @return
	 */
	int deleteSubMeetingInfoByIdReal(SubMeetingInfo subMeetingInfo)
			throws CustomException;

	/**
	 * 根据会议ID字符串来逻辑删除子会议信息
	 * @param ids
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoByIds(List<String> ids)
			throws CustomException;
	
	/**
	 * 根据主会议的ID查询主会议下的子会议信息
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> selectSubMeetingInfoByParentId(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据会议ID list查询的子会议信息（已开始的）
	 * @param list
	 * @return
	 */
	List<SubMeetingInfo> selectSubMeetingInfoByList(List<Integer> list)
			throws CustomException;
	
	/**
	 * 更新子会议信息
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String, Object> updateSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据子会议ID查询子会议信息
	 * @param subMeetingInfo
	 * @return
	 */
	SubMeetingInfo selectSubMeetingInfoById(SubMeetingInfo subMeetingInfo)
			throws CustomException;

	/**
	 * 根据会议ID list分页查询的子会议信息
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<SubMeetingInfo> selectPageSubMeetingInfoByList(List<Integer> list,
															int pageNum, int pageSize) throws CustomException;

	/**
	 * 主会议判断子会议开始时间是否有提前于主会议开始时间
	 * @param startTime
	 * @param meeId
	 * @return
	 * @throws CustomException
	 */
	boolean isMeetingStartTimeOut(long startTime, Integer meeId) throws CustomException;

	/**
	 * 主会议判断子会议结束时间是否有超过主会议结束时间
	 * @param endTime
	 * @param meeId
	 * @return
	 * @throws CustomException
	 */
	boolean isMeetingEndTimeOut(long endTime, Integer meeId) throws CustomException;

	/**
	 * 根据主会议查询最早开始的子会议时间
	 * @param meeId
	 * @return
	 * @throws CustomException
	 */
	Long getFristStartTimeByParentMeeId(Integer meeId) throws CustomException;
	
	/**
	 * 根据主会议查询最晚结束的子会议时间
	 * @param meeId
	 * @return
	 * @throws CustomException
	 */
	Long getLastEndTimeByParentMeeId(Integer meeId) throws CustomException;
}
