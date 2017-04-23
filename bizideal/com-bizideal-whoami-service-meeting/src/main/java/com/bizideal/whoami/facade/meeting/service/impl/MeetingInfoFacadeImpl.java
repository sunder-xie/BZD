package com.bizideal.whoami.facade.meeting.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.core.meeting.biz.MeetingInfoBiz;
import com.bizideal.whoami.core.meeting.biz.SubMeetingInfoBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.im.entity.ChatGroup;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName meetingInfoBiz
 * @Description (会议信息)
 * @Author Zj.Qu
 * @Date 2016-12-04 14:48:07
 */
@Component("meetingInfoFacade")
public class MeetingInfoFacadeImpl implements MeetingInfoFacade{

	@Autowired
	private MeetingInfoBiz meetingInfoBiz;
	
	@Autowired
	private SubMeetingInfoBiz subMeetingInfoBiz;
	
	@Override
	public Integer insertMeetingInfo(MeetingInfo meetingInfo) throws CustomException{
		return meetingInfoBiz.insertMeetingInfo(meetingInfo);
	}

	@Override
	public Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.updateMeetingInfo(meetingInfo);
	}
	
	@Override
	public Integer deleteMeetingInfoById(Integer meeId) 
			throws CustomException{
		return meetingInfoBiz.deleteMeetingInfoById(meeId);
	}
	
	@Override
	public Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.deleteSubMeetingInfoById(subMeetingInfo);
	}
	
	@Override
	public int deleteSubMeetingInfoByIdReal(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.deleteSubMeetingInfoByIdReal(subMeetingInfo);
	}

	@Override
	public Map<String,Object> selectMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.selectMeetingInfo(meetingInfo);
	}

	@Override
	public Map<String,Object> deleteSubMeetingInfoByIds(List<String> ids) 
			throws CustomException{
		return subMeetingInfoBiz.deleteSubMeetingInfoByIds(ids);
	}

	@Override
	public Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.selectMeetingInfoByName(meetingInfo);
	}

	@Override
	public Map<String, Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.insertSubMeetingInfo(subMeetingInfo);
	}

	@Override
	public Map<String, Object> deleteMeetingInfoByIds(List<String> ids) 
			throws CustomException{
		return meetingInfoBiz.deleteMeetingInfoByIds(ids);
	}

	@Override
	public Map<String, Object> selectSubMeetingInfo(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.selectSubMeetingInfoByParentId(subMeetingInfo);
	}

	@Override
	public Map<String, Object> selectPopMeetingInfo(MeetingInfoDto meetingInfoDto) 
			throws CustomException{
		
		return meetingInfoBiz.selectPopMeetingInfo(meetingInfoDto) ;
	}

	@Override
	public MeetingInfo selectMeetingInfoById(MeetingInfo meetingInfo) 
			throws CustomException{
		
		return meetingInfoBiz.selectMeetingInfoById(meetingInfo);
	}

	/*@Override
	public List<MeetingInfo> selectFollowMeetingInfo(String userId) throws CustomException{
		return meetingInfoBiz.selectFollowMeetingInfo(userId);
	}*/

	/*@Override
	public List<MeetingInfo> selectSignUpMeetingInfo(String userId) throws CustomException{
//		return meetingInfoBiz.selectSignUpMeetingInfo(userId);
		return null;
	}*/

	@Override
	public PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdOver(
			String userId, int pageNum, int pageSize) 
					throws CustomException{
		return meetingInfoBiz.selectFocusMeetingInfoByUserIdOver(userId, pageNum, pageSize);
	}

	@Override
	public PageInfo<MeetingInfo> selectFocusMeetingInfoByUserIdFuture(
			String userId, int pageNum, int pageSize) 
					throws CustomException{
		return meetingInfoBiz.selectFocusMeetingInfoByUserIdFuture(userId, pageNum, pageSize);
	}

	/*@Override
	public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdFuture(
			List<Integer> list,List<Integer> subList, int pageNum, int pageSize) throws CustomException{
		return meetingInfoBiz.selectJoinMeetingInfoByUserIdFuture(list,subList, pageNum, pageSize);
	}*/

	/*@Override
	public PageInfo<MeetingInfoSignUpDto> selectJoinMeetingInfoByUserIdOver(
			List<Integer> list,List<Integer> subList, int pageNum, int pageSize) throws CustomException{
		return meetingInfoBiz.selectJoinMeetingInfoByUserIdOver(list,subList, pageNum, pageSize);
	}*/

	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByListOver(List<Integer> list,
			int pageNum, int pageSize) 
					throws CustomException{
		return meetingInfoBiz.selectMeetingInfoByListOver(list, pageNum, pageSize);
	}
	
	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByListFuture(
			List<Integer> list, int pageNum, int pageSize) 
					throws CustomException{
		return meetingInfoBiz.selectMeetingInfoByListFuture(list, pageNum, pageSize);
	}

	@Override
	public List<SubMeetingInfo> selectSubMeetingInfoByList(List<Integer> list) 
			throws CustomException{
		return subMeetingInfoBiz.selectSubMeetingInfoByList(list);
	}

	@Override
	public Map<String, Object> updateSubMeetingInfo(
			SubMeetingInfo subMeetingInfo) throws CustomException{
		return subMeetingInfoBiz.updateSubMeetingInfo(subMeetingInfo);
	}

	@Override
	public SubMeetingInfo selectSubMeetingInfoById(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.selectSubMeetingInfoById(subMeetingInfo);
	}

	@Override
	public PageInfo<MeetingInfo> selectMeetingInfoByHallId(
			MeetingInfo meetingInfo) throws CustomException{
		
		return meetingInfoBiz.selectMeetingInfoByHallId(meetingInfo);
	}

	@Override
	public PageInfo<SubMeetingInfo> selectPageSubMeetingInfoByList(List<Integer> list,
			int pageNum, int pageSize) throws CustomException{
		return subMeetingInfoBiz.selectPageSubMeetingInfoByList(list, pageNum, pageSize);
	}

	@Override
	public boolean isMeetingStartTimeOut(long startTime, Integer meeId)
			throws CustomException {
		return subMeetingInfoBiz.isMeetingStartTimeOut(startTime, meeId);
	}

	@Override
	public boolean isMeetingEndTimeOut(long endTime, Integer meeId)
			throws CustomException {
		return subMeetingInfoBiz.isMeetingEndTimeOut(endTime, meeId);
	}
	
	@Override
	public Long getFristStartTimeByParentMeeId(Integer meeId)
			throws CustomException {
		return subMeetingInfoBiz.getFristStartTimeByParentMeeId(meeId);
	}

	@Override
	public Long getLastEndTimeByParentMeeId(Integer meeId)
			throws CustomException {
		return subMeetingInfoBiz.getLastEndTimeByParentMeeId(meeId);
	}
}