package com.bizideal.whoami.facade.meeting.service;

import java.util.List;
import java.util.Map;

import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingInfoFacade
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-02 17:34:07
 */
public interface MeetingInfoFacade {

	/**
	 * 插入一条主会议记录
	 * 无需重写
	 * @param meetingInfo
	 * @return
	 */
	Integer insertMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 插入一条子会议记录
	 * 需重写
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;

	/**
	 * 更新一条主会议记录
	 * 无需重写
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;

	/**
	 * 更新一条子会议记录
	 * 需要重写
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> updateSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;

	/**
	 * 根据ID删除多条主会议记录
	 * 无需重写
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> deleteMeetingInfoByIds(List<String> ids)
			throws CustomException;

	/**
	 * 根据主会议ID删除主会议信息
	 * @param meeId
	 * @return
	 */
	Integer deleteMeetingInfoById(Integer meeId)
			throws CustomException;
	
	/**
	 * 删除一条子会议记录
	 * 需要重写
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 物理删除一条子会议数据
	 * 需要重写
	 * @param subMeetingInfo
	 * @return
	 */
	int deleteSubMeetingInfoByIdReal(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据条件查询主会议信息
	 * 未知
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectMeetingInfo(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 根据会议厅ID查询主会议，隐含条件：DelFlag=0
	 * @param meetingInfo
	 * @return
	 */
	PageInfo<MeetingInfo> selectMeetingInfoByHallId(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 根据主会议ID查询子子会议信息
	 * 需要重写
	 * @param subMeetingInfo
	 * @return
	 */
	Map<String,Object> selectSubMeetingInfo(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据会议ID字符串来逻辑删除会议信息
	 * 需要重写
	 * @param ids
	 * @return
	 */
	Map<String,Object> deleteSubMeetingInfoByIds(List<String> ids)
			throws CustomException;
	
	/**
	 * 根据会议的名称来查询主会议
	 * 无需重写
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 查询热门主会议（带上用户信息，如果没有用户信息就返回错误
	 * 无需重写
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfoDto
	 * @return
	 */
	Map<String,Object> selectPopMeetingInfo(MeetingInfoDto meetingInfoDto)
			throws CustomException;
	
	/**
	 * 根据会议ID查询会议信息
	 * 未知
	 * @param meetingInfo
	 * @return meetingInfo
	 */
	MeetingInfo selectMeetingInfoById(MeetingInfo meetingInfo)
			throws CustomException;

	/**
	 * 根据会议ID查询会议信息
	 * 需要重写
	 * @param subMeetingInfo
	 * @return meetingInfo
	 */
	SubMeetingInfo selectSubMeetingInfoById(SubMeetingInfo subMeetingInfo)
			throws CustomException;
	
	/**
	 * 根据用户ID查询用户关注的会议信息(主会议) == 暂无使用
	 * @param userId
	 * @return
	 */
	/*public List<MeetingInfo> selectFollowMeetingInfo(String userId) throws CustomException;*/
	
	/**
	 * 根据用户ID查询用户报名的会议信息(主会议) == 暂无使用
	 * @param userId
	 * @return
	 */
	/*public List<MeetingInfo> selectSignUpMeetingInfo(String userId) throws CustomException;*/

	/**
	 * 根据用户ID查询当前关注过的会议信息（已结束的）
	 * 无需重写
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdOver(String userId, int pageNum, int pageSize)
			throws CustomException;

	/**
	 * 根据用户ID查询当前关注过的会议信息（未开始的）
	 * 无需重写
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdFuture(String userId, int pageNum, int pageSize)
			throws CustomException;
	
	/**
	 * 根据用户ID查询当前参加的会议信息（未开始的）== 暂无使用
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	/*public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdFuture(
			List<Integer> list,List<Integer> subList, int pageNum, int pageSize) throws CustomException;*/
	
	/**
	 * 根据用户ID查询当前参加的会议信息（已开始的）=== 暂时没有用
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	/*public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdOver(
			List<Integer> list,List<Integer> subList, int pageNum, int pageSize) throws CustomException;*/
	
	/**
	 * 根据会议ID list查询的会议信息（已结束的）
	 * 无需重写
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<MeetingInfo> selectMeetingInfoByListFuture(List<Integer> list, int pageNum, int pageSize)
			throws CustomException;

	/**
	 * 根据会议ID list查询的会议信息（未结束的）
	 * 无需重写
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<MeetingInfo> selectMeetingInfoByListOver(List<Integer> list, int pageNum, int pageSize)
			throws CustomException ;

	/**
	 * 根据会议ID list查询的子会议信息
	 * 需要重写
	 * @param list
	 * @return
	 */
	List<SubMeetingInfo> selectSubMeetingInfoByList(List<Integer> list)
			throws CustomException;
	
	/**
	 * 根据会议ID list分页查询的子会议信息
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<SubMeetingInfo> selectPageSubMeetingInfoByList(List<Integer> list, int pageNum, int pageSize)
			throws CustomException;
	
	/**
	 * 主会议判断子会议开始时间是否有提前于主会议开始时间
	 * @param startTime
	 * @param meeId
	 * @return(true可以用，false不可用）
	 * @throws CustomException
	 */
	boolean isMeetingStartTimeOut(long startTime, Integer meeId) throws CustomException;

	/**
	 * 主会议判断子会议结束时间是否有超过主会议结束时间
	 * @param endTime
	 * @param meeId
	 * @return (true可以用，false不可用）
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
