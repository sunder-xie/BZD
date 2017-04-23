package com.bizideal.whoami.hotel.facade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.facade.hotel.facade.HotelUserLinkFacade;
import com.bizideal.whoami.hotel.service.HotelUserLinkBiz;

/**
 * 
 * @ClassName HotelUserLinkFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Component("hotelUserLinkFacade")
public class HotelUserLinkFacadeImpl implements HotelUserLinkFacade {

	@Autowired
	private HotelUserLinkBiz hotelUserLinkBiz;

	// @Override
	// public Map<String, Object> insert(HotelUserLink hotelUserLink) {
	// return hotelUserLinkBiz.insert(hotelUserLink);
	// }

	@Override
	public Map<String, Object> updateFromManager(HotelUserLink hotelUserLink) {
		return hotelUserLinkBiz.updateFromManager(hotelUserLink);
	}

	@Override
	public Map<String, Object> updateFromParticipator(HotelUserLink hotelUserLink) {
		return hotelUserLinkBiz.updateFromParticipator(hotelUserLink);
	}

	// @Override
	// public Map<String, Object> deleteHotelUserLinkByHUId(int hUId) {
	// return hotelUserLinkBiz.deleteHotelUserLinkByHUId(hUId);
	// }

	@Override
	public Map<String, Object> selectByHUId(int hUId) {
		return hotelUserLinkBiz.selectByHUId(hUId);
	}

	@Override
	public Map<String, Object> selectByHotelIdAndQueryString(int hotelId, String queryString) {
		return hotelUserLinkBiz.selectByHotelIdAndQueryString(hotelId, queryString);
	}

	@Override
	public Map<String, Object> selectByMeetingId(int meetingId, String queryString) {
		return hotelUserLinkBiz.selectByMeetingId(meetingId, queryString);
	}

	@Override
	public Map<String, Object> insertFromManager(HotelUserLink hotelUserLink) {
		return hotelUserLinkBiz.insertFromManager(hotelUserLink);
	}

	@Override
	public Map<String, Object> insertFromParticipator(HotelUserLink hotelUserLink) {
		return hotelUserLinkBiz.insertFromParticipator(hotelUserLink);
	}
	
	@Override
	public boolean hotelSignUp(SignUpInfoDto signUpInfoDto) {
		// 封装数据
		HotelUserLink hotelUserLink = new HotelUserLink();
		hotelUserLink.setUserId(signUpInfoDto.getUserId());
		hotelUserLink.setHotelId(signUpInfoDto.getHotelId());
		hotelUserLink.setMeetingHallId(signUpInfoDto.getMeethallId());
		hotelUserLink.setMeetingId(signUpInfoDto.getMeetingId());
		hotelUserLink.setRoomId(signUpInfoDto.getRoomId());
		
		Map<String, Object> insertFromParticipator = hotelUserLinkBiz.insertFromParticipator(hotelUserLink);
		
		String code = insertFromParticipator.get("code").toString();
		return "6380".equals(code);
	}

	@Override
	public Map<String, Object> deleteFromManager(int hUId) {
		return hotelUserLinkBiz.deleteFromManager(hUId);
	}

	@Override
	public Map<String, Object> deleteFromParticipator(int hUId) {
		return hotelUserLinkBiz.deleteFromParticipator(hUId);
	}

	@Override
	public Map<String, Object> selectByMeetingIdAndUserId(int meetingId, String userId) {
		return hotelUserLinkBiz.selectByMeetingIdAndUserId(meetingId, userId);
	}

}
