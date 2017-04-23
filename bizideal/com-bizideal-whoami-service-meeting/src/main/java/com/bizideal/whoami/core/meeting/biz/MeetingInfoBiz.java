package com.bizideal.whoami.core.meeting.biz;

import java.util.List;
import java.util.Map;

import com.bizideal.whoami.croe.service.BaseOpBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoSignUpDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingInfoBiz
 * @Description TODO(会议信息业务层)
 * @Author Zj.Qu
 * @Date 2016-12-04 16:24:31
 */
public interface MeetingInfoBiz extends BaseOpBiz<MeetingInfo> {
	
	/**
	 * 插入一条会议记录
	 * @param meetingInfo
	 * @return
	 */
	public Integer insertMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException;

	/**
	 * 更新一条会议记录
	 * @param meetingInfo
	 * @return
	 */
	public Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException;

	/**
	 * 删除多条主会议记录
	 * @param meetingInfo
	 * @return
	 */
	public Map<String,Object> deleteMeetingInfoByIds(List<String> ids) 
			throws CustomException;
	
	/**
	 * 根据主会议ID删除主会议信息
	 * @param meeId
	 * @return
	 */
	Integer deleteMeetingInfoById(Integer meeId)
			throws CustomException;
	
	/**
	 * 根据条件查询会议信息
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
	 * 根据会议的名称来查询会议
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo)
			throws CustomException;
	
	/**
	 * 查询热门主会议
	 * 注：这里不是模糊查询,返回中如果是null就没有重复，否则返回重复的实体类
	 * @param meetingInfo
	 * @return
	 */
	Map<String,Object> selectPopMeetingInfo(MeetingInfoDto meetingInfoDto)
			throws CustomException;
	
	/**
	 * 根据会议ID查询会议信息
	 * @param meetingInfo
	 * @return
	 */
	MeetingInfo selectMeetingInfoById(MeetingInfo meetingInfo)
			throws CustomException;

	/**
	 * 根据用户ID查询用户报名的主会议信息
	 * @param userId
	 * @return
	 */
//	public List<MeetingInfo> selectSignUpMeetingInfo(String userId) throws CustomException;

	/**
	 * 根据用户ID查询用户关注的主会议信息
	 * @param userId
	 * @return
	 */
	List<MeetingInfo> selectFollowMeetingInfo(String userId)
			throws CustomException;

	/**
	 * 根据用户ID查询当前关注过的会议信息（已结束的）
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdOver(String userId, int pageNum, int pageSize)
			throws CustomException;

	/**
	 * 根据用户ID查询当前关注过的会议信息（未开始的）
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdFuture(String userId, int pageNum, int pageSize)
			throws CustomException;
	
	/**
	 * 根据用户ID查询当前参加的会议信息（未开始的）
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdFuture(List<Integer> list, List<Integer> subList, int pageNum, int pageSize)
			throws CustomException;
	
	/**
	 * 根据用户ID查询当前参加的会议信息（已开始的）
	 * @param userId
	 * @return
	 */
	PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdOver(List<Integer> list, List<Integer> subList, int pageNum, int pageSize)
			throws CustomException;

	/**
	 * 根据会议ID list查询的会议信息（未结束的）
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<MeetingInfo> selectMeetingInfoByListFuture(List<Integer> list, int pageNum, int pageSize)
			throws CustomException;
	
	/**
	 * 根据会议ID list查询的会议信息（已结束的）
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<MeetingInfo> selectMeetingInfoByListOver(List<Integer> list, int pageNum, int pageSize)
			throws CustomException;

}
