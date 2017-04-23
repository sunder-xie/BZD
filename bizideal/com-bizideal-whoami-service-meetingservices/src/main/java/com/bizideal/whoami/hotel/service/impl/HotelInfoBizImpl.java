package com.bizideal.whoami.hotel.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.facade.hotel.entity.HotelInfo;
import com.bizideal.whoami.facade.hotel.enums.HotelEnums;
import com.bizideal.whoami.hotel.mapper.HotelInfoMapper;
import com.bizideal.whoami.hotel.mapper.HotelUserLinkMapper;
import com.bizideal.whoami.hotel.mapper.RoomInfoMapper;
import com.bizideal.whoami.hotel.service.HotelInfoBiz;

/**
 * 
 * @ClassName HotelInfoBizImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Service("hotelInfoBiz")
public class HotelInfoBizImpl implements HotelInfoBiz {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomInfoBizImpl.class);

	@Autowired
	private HotelInfoMapper hotelInfoMapper;

	@Autowired
	private RoomInfoMapper roomInfoMapper;

	@Autowired
	private HotelUserLinkMapper hotelUserLinkMapper;

	@Override
	public Map<String, Object> insertHotelInfo(HotelInfo hotelInfo) {
		// 添加酒店信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断同一会议下酒店名称不重复
			if (null != (hotelInfoMapper.verifyHotelName(hotelInfo.getMeetingId(), hotelInfo.getHotelName()))) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_REPEAT);
			} else {
				// 保存酒店信息
				int insertHotelInfo = hotelInfoMapper.insertHotelInfo(hotelInfo);
				if (1 == insertHotelInfo) {
					HotelInfo hotelInfo2 = hotelInfoMapper.selectOne(hotelInfo);
					map.put("entity", hotelInfo2);
					return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_SUCCESS);
				} else {
					return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_EXCEPTION);
				}
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> updateHotelInfo(HotelInfo hotelInfo) {
		// 根据酒店id更新酒店信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == hotelInfo.getHotelId()) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDNULL);
			} else {
				if (0 >= hotelInfo.getHotelId()) {
					return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDERROR);
				}
			}
			int updateHotelInfo = hotelInfoMapper.updateHotelInfo(hotelInfo);
			if (1 == updateHotelInfo) {
				map.put("entity", hotelInfo);
				return makeMapMsg(map, HotelEnums.HOTELINFO_UPDATE_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_UPDATE_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_UPDATE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> deleteHotelInfoById(int hotelId) {
		// 根据酒店id删除酒店信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hotelId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDERROR);
			}
			int deleteHotelInfo = hotelInfoMapper.deleteHotelInfoById(hotelId);
			if (1 == deleteHotelInfo) {
				roomInfoMapper.deleteByHotelId(hotelId);
				hotelUserLinkMapper.deleteByHotelId(hotelId);
				return makeMapMsg(map, HotelEnums.HOTELINFO_DELETE_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_DELETE_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_DELETE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectHotelInfoByHotelId(int hotelId) {
		// 根据酒店id查询唯一酒店信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hotelId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDERROR);
			}
			HotelInfo selectHotelInfo = hotelInfoMapper.selectHotelInfoByHotelId(hotelId);
			if (null != selectHotelInfo) {
				map.put("hotelInfo", selectHotelInfo);
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectHotelInfoByMeetingIdAndLikeHotelName(int meetingId, String hotelName) {
		// 根据会议id和模糊酒店名称查询酒店list
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			if (hotelName == null || "".equals(hotelName.trim())) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELNAMENULL);
			}
			List<HotelInfo> list = hotelInfoMapper.selectHotelInfoByMeetingIdAndLikeHotelName(meetingId,
					hotelName.trim());
			if (null != list) {
				map.put("hotelInfoList", list);
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectHotelInfoByMeetingId(int meetingId) {
		// 根据会议id查询酒店list
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			List<HotelInfo> list = hotelInfoMapper.selectHotelInfoByMeetingIdAndLikeHotelName(meetingId, null);
			if (null != list) {
				map.put("hotelInfoList", list);
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> verifyHotelName(int meetingId, String hotelName) {
		// 根据会议id和酒店名称验证是否酒店重名
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			if (hotelName == null || "".equals(hotelName.trim())) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELNAMENULL);
			}
			HotelInfo verifyHotelName = hotelInfoMapper.verifyHotelName(meetingId, hotelName.trim());
			if (null != verifyHotelName) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_REPEAT);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_INSERT_NOREPEAT);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
		}
	}

	private Map<String, Object> makeMapMsg(Map<String, Object> map, HotelEnums enums) {

		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}

	private void printException(Exception e) {
		StringWriter errorsWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(errorsWriter));
		LOGGER.error(errorsWriter.toString());
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	@Override
	public Map<String, Object> hotelListQuery(int meetingId) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			List<HotelInfo> list = hotelInfoMapper.hotelListQuery(meetingId);
			if (null != list) {
				map.put("hotelListQuery", list);
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELINFO_SELECT_EXCEPTION);
		}
	}

}
