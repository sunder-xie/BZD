package com.bizideal.whoami.facade.hotel.facade;

import java.util.Map;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;

/**
 * 酒店用户入住Facade
 * 
 * @ClassName HotelUserLinkFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
public interface HotelUserLinkFacade {

	/**
	 * 添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	// public Map<String, Object> insert(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId更新酒店用户入住表
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> updateFromManager(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId更新酒店用户入住表
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> updateFromParticipator(HotelUserLink hotelUserLink);

	/**
	 * 根据HUId删除酒店用户入住表
	 * 
	 * @param hUId
	 * @return
	 */
	// public Map<String, Object> deleteHotelUserLinkByHUId(int hUId);

	/**
	 * 根据HUId查找唯一酒店用户入住信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> selectByHUId(int hUId);

	/**
	 * 根据酒店id查询酒店用户入住信息list 酒店id必填，没有查询字段时填null
	 * 
	 * @param hotelId
	 * @return
	 */
	Map<String, Object> selectByHotelIdAndQueryString(int hotelId, String queryString);

	/**
	 * 根据会议id查询酒店用户入住信息list,会议id必填,没有查询字段时填null
	 * 
	 * @param meetingId
	 * @return
	 */
	Map<String, Object> selectByMeetingId(int meetingId, String queryString);

	/**
	 * 管理员添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> insertFromManager(HotelUserLink hotelUserLink);
	
	/**
	 * 报名添加酒店用户入住表信息
	 * 
	 * @param SignUpInfoDto
	 * @return boolean
	 */
	boolean hotelSignUp(SignUpInfoDto signUpInfoDto);

	/**
	 * 参加者添加酒店用户入住表信息
	 * 
	 * @param hotelUserLink
	 * @return
	 */
	Map<String, Object> insertFromParticipator(HotelUserLink hotelUserLink);

	/**
	 * 管理员删除酒店用户入住表信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> deleteFromManager(int hUId);

	/**
	 * 参加者删除酒店用户入住表信息
	 * 
	 * @param hUId
	 * @return
	 */
	Map<String, Object> deleteFromParticipator(int hUId);

	/**
	 * 根据会议id和用户id查找酒店用户入住信息
	 * 
	 * @param meetingId
	 * @param userId
	 * @return
	 */
	Map<String, Object> selectByMeetingIdAndUserId(int meetingId, String userId);
}
