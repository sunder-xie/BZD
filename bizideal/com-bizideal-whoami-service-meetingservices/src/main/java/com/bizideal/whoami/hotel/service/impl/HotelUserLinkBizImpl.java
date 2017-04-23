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

import com.bizideal.whoami.facade.hotel.entity.HotelUserLink;
import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.facade.hotel.enums.HotelEnums;
import com.bizideal.whoami.hotel.mapper.HotelUserLinkMapper;
import com.bizideal.whoami.hotel.mapper.RoomInfoMapper;
import com.bizideal.whoami.hotel.service.HotelUserLinkBiz;

/**
 * 
 * @ClassName HotelUserLinkBizImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Service("hotelUserLinkBiz")
public class HotelUserLinkBizImpl implements HotelUserLinkBiz {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomInfoBizImpl.class);

	@Autowired
	private HotelUserLinkMapper hotelUserLinkMapper;

	@Autowired
	private RoomInfoMapper roomInfoMapper;

	//
	// @Override
	// public Map<String, Object> insert(HotelUserLink hotelUserLink) {
	// // 添加酒店用户关联表信息
	// Map<String, Object> map = new HashMap<String, Object>();
	// try {
	// hotelUserLink.sethUId(null);
	// int insert = hotelUserLinkMapper.insert(hotelUserLink);
	// if (1 == insert) {
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_SUCCESS);
	// } else {
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
	// }
	// } catch (Exception e) {
	// printException(e);
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
	// }
	// }

	@Override
	public Map<String, Object> insertFromManager(HotelUserLink hotelUserLink) {
		// 添加酒店用户关联表信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelUserLink.sethUId(null);
			// 验证房间表是否有足够的房间数
			RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(hotelUserLink.getRoomId());
			if (roomInfo.getNormalReservedNum() >= roomInfo.getRoomNumber() - roomInfo.getRoomSpare()) {
				if (roomInfo.getSpareReservedNum() >= roomInfo.getRoomSpare()) {
					return makeMapMsg(map, HotelEnums.ROOMSPARERESERVEDNUM_NOT_ENOUGH);
				} else {
					int insert = hotelUserLinkMapper.insert(hotelUserLink);
					if (1 == insert) {
						// 修改房间表的房间数,备用房间预定数+1
						roomInfoMapper.updateRaiseRoomNumReserved(hotelUserLink.getRoomId(), 1);
						return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_SUCCESS);
					} else {
						return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
					}
				}
			} else {
				int insert = hotelUserLinkMapper.insert(hotelUserLink);
				if (1 == insert) {
					// 修改房间表的房间数,普通房间预定数+1
					roomInfoMapper.updateRaiseRoomNum(hotelUserLink.getRoomId(), 1);
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_SUCCESS);
				} else {
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
				}
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> insertFromParticipator(HotelUserLink hotelUserLink) {
		// 添加酒店用户关联表信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelUserLink.sethUId(null);
			// 验证房间表是否有足够的房间数
			RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(hotelUserLink.getRoomId());
			if (roomInfo.getNormalReservedNum() >= roomInfo.getRoomNumber() - roomInfo.getRoomSpare()) {
				return makeMapMsg(map, HotelEnums.ROOMNORMALRESERVEDNUM_NOT_ENOUGH);
			}
			int insert = hotelUserLinkMapper.insert(hotelUserLink);
			if (1 == insert) {
				// 修改房间表的房间数,普通房间预定数+1
				roomInfoMapper.updateRaiseRoomNum(hotelUserLink.getRoomId(), 1);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_INSERT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> updateFromManager(HotelUserLink hotelUserLink) {
		// 根据HUId更新酒店用户关联表
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (hotelUserLink.getHotelId() == null) {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDNULL);
			} else {
				if (0 >= hotelUserLink.getHotelId()) {
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
				}
			}
			// 查询旧数据
			HotelUserLink hotelUserLinkOld = hotelUserLinkMapper.selectByHUId(hotelUserLink.gethUId());
			// 验证房间表是否有足够的房间数
			RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(hotelUserLink.getRoomId());
			if (roomInfo.getNormalReservedNum() >= roomInfo.getRoomNumber() - roomInfo.getRoomSpare()) {
				if (roomInfo.getSpareReservedNum() >= roomInfo.getRoomSpare()) {
					return makeMapMsg(map, HotelEnums.ROOMSPARERESERVEDNUM_NOT_ENOUGH);
				} else {
					int update = hotelUserLinkMapper.updateByHUId(hotelUserLink);
					if (1 == update) {
						// 修改房间表的房间数
						roomInfoMapper.updateReduceRoomNumReserved(hotelUserLinkOld.getRoomId(), 1);
						roomInfoMapper.updateRaiseRoomNumReserved(hotelUserLink.getRoomId(), 1);
						return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_SUCCESS);
					} else {
						return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_EXCEPTION);
					}
				}
			} else {
				int update = hotelUserLinkMapper.updateByHUId(hotelUserLink);
				if (1 == update) {
					// 修改房间表的房间数
					roomInfoMapper.updateReduceRoomNum(hotelUserLinkOld.getRoomId(), 1);
					roomInfoMapper.updateRaiseRoomNum(hotelUserLink.getRoomId(), 1);
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_SUCCESS);
				} else {
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_EXCEPTION);
				}
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> updateFromParticipator(HotelUserLink hotelUserLink) {
		// 根据HUId更新酒店用户关联表
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (hotelUserLink.getHotelId() == null) {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDNULL);
			} else {
				if (0 >= hotelUserLink.getHotelId()) {
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
				}
			}
			// 查询旧数据
			HotelUserLink hotelUserLinkOld = hotelUserLinkMapper.selectByHUId(hotelUserLink.gethUId());
			// 验证房间表是否有足够的房间数
			RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(hotelUserLink.getRoomId());
			if (roomInfo.getNormalReservedNum() >= roomInfo.getRoomNumber() - roomInfo.getRoomSpare()) {
				return makeMapMsg(map, HotelEnums.ROOMNORMALRESERVEDNUM_NOT_ENOUGH);
			} else {
				int update = hotelUserLinkMapper.updateByHUId(hotelUserLink);
				if (1 == update) {
					// 修改房间表的房间数
					roomInfoMapper.updateReduceRoomNum(hotelUserLinkOld.getRoomId(), 1);
					roomInfoMapper.updateRaiseRoomNum(hotelUserLink.getRoomId(), 1);
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_SUCCESS);
				} else {
					return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_EXCEPTION);
				}
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_UPDATE_EXCEPTION);
		}
	}

	// @Override
	// public Map<String, Object> deleteHotelUserLinkByHUId(int hUId) {
	// // 根据HUId删除酒店用户关联表
	// Map<String, Object> map = new HashMap<String, Object>();
	// try {
	// if (0 >= hUId) {
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
	// }
	// int delete = hotelUserLinkMapper.deleteHotelUserLinkByHUId(hUId);
	// if (1 == delete) {
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_SUCCESS);
	// } else {
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
	// }
	// } catch (Exception e) {
	// printException(e);
	// return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
	// }
	// }

	@Override
	public Map<String, Object> deleteFromManager(int hUId) {
		// 根据HUId删除酒店用户关联表
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hUId) {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
			}
			HotelUserLink hotelUserLink = hotelUserLinkMapper.selectByHUId(hUId);
			int delete = hotelUserLinkMapper.deleteByHUId(hUId);
			if (1 == delete) {
				// 修改房间表的房间数
				RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(hotelUserLink.getRoomId());
				if (roomInfo.getSpareReservedNum() <= 0) {
					roomInfoMapper.updateReduceRoomNum(hotelUserLink.getRoomId(), 1);
				} else {
					roomInfoMapper.updateReduceRoomNumReserved(hotelUserLink.getRoomId(), 1);
				}
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> deleteFromParticipator(int hUId) {
		// 根据HUId删除酒店用户关联表
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hUId) {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
			}
			HotelUserLink hotelUserLink = hotelUserLinkMapper.selectByHUId(hUId);
			int delete = hotelUserLinkMapper.deleteByHUId(hUId);
			if (1 == delete) {
				// 修改房间表的房间数
				roomInfoMapper.updateReduceRoomNum(hotelUserLink.getRoomId(), 1);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_DELETE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectByHUId(int hUId) {
		// 根据HUId查找唯一酒店用户关联信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hUId) {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_HUIDERROR);
			}
			HotelUserLink link = hotelUserLinkMapper.selectByHUId(hUId);
			if (null != link) {
				map.put("hotelUserLink", link);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectByHotelIdAndQueryString(int hotelId, String queryString) {
		// 根据酒店id查询酒店用户关联信息list
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hotelId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDERROR);
			}
			List<HotelUserLink> list = hotelUserLinkMapper.selectByHotelId(hotelId, queryString);
			if (null != list) {
				map.put("hotelUserLinkList", list);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectByMeetingId(int meetingId, String queryString) {
		// 根据会议id查询酒店用户关联信息list
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			List<HotelUserLink> list = hotelUserLinkMapper.selectByMeetingId(meetingId, queryString);
			if (null != list) {
				map.put("hotelUserLinkList", list);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectByMeetingIdAndUserId(int meetingId, String userId) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= meetingId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_MEETINGIDERROR);
			}
			HotelUserLink userLink = hotelUserLinkMapper.selectByMeetingIdAndUserId(meetingId, userId);
			if (null != userLink) {
				map.put("entity", userLink);
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.HOTELUSERLINK_SELECT_EXCEPTION);
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
}
