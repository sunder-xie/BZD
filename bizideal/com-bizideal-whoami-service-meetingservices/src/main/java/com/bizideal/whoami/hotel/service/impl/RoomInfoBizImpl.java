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

import com.bizideal.whoami.facade.hotel.entity.RoomInfo;
import com.bizideal.whoami.facade.hotel.enums.HotelEnums;
import com.bizideal.whoami.hotel.mapper.RoomInfoMapper;
import com.bizideal.whoami.hotel.mapper.RoomTypeInfoMapper;
import com.bizideal.whoami.hotel.service.RoomInfoBiz;

/**
 * 
 * @ClassName RoomInfoBizImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Service("roomInfoBiz")
public class RoomInfoBizImpl implements RoomInfoBiz {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomInfoBizImpl.class);

	@Autowired
	private RoomInfoMapper roomInfoMapper;
	@Autowired
	RoomTypeInfoMapper roomTypeInfoMapper;

	@Override
	public Map<String, Object> insertRoomInfo(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/*
			 * RoomInfo temp = new RoomInfo();
			 * temp.setRoomType(roomInfo.getRoomType());
			 * temp.setHotelId(roomInfo.getHotelId()); temp.setDelFlag("0");
			 * List<RoomInfo> list = roomInfoMapper.select(temp); if (null !=
			 * list && list.size() > 0) { map = makeMapMsg(map,
			 * HotelEnums.ROOMINFO_INSERT_REPEAT); return map; }
			 */
			if (roomInfoMapper.insertSelective(roomInfo) == 1) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_INSERT_SUCCESS);
				map.put("entity", roomInfo);
			}
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_INSERT_EXCEPTION);
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteRoomInfo(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == roomInfo || null == roomInfo.getRoomId()) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_DELETE_IDNULL);
				return map;
			}
			RoomInfo temp = new RoomInfo();
			temp.setRoomId(roomInfo.getRoomId());
			temp.setDelFlag("1");
			if (roomInfoMapper.updateByPrimaryKeySelective(temp) == 1) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_DELETE_SUCCESS);
			}
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_DELETE_EXCEPTION);
		}
		return map;
	}

	@Override
	public Map<String, Object> updateRoomInfo(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == roomInfo || null == roomInfo.getRoomId()) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_UPDATE_IDNULL);
				return map;
			}
			RoomInfo temp = new RoomInfo();
			// temp.setRoomTypeId(roomInfo.getRoomTypeId());
			// temp.setHotelId(roomInfo.getHotelId());
			// temp.setDelFlag("0");
			// List<RoomInfo> list = roomInfoMapper.select(temp);
			// if (null != list && list.size() > 0) {
			// boolean flag = false;
			// for (RoomInfo room : list) {
			// if (room.getRoomTypeId().equals(roomInfo.getRoomTypeId())) {
			// flag = true;
			// break;
			// }
			// }
			// if (!flag) {
			// map = makeMapMsg(map, HotelEnums.ROOMINFO_UPDATE_TYPEREPEAT);
			// }
			// }
			temp = roomInfoMapper.selectByPrimaryKey(roomInfo.getRoomId());
			// 如果房间数需要调整调用下面的方法来调整
			if (roomInfo.getRoomNumber().intValue() != temp.getRoomNumber().intValue()
					|| (roomInfo.getRoomSpare() != null && roomInfo.getRoomSpare().intValue() != temp.getRoomSpare()
							.intValue())) {
				temp = new RoomInfo();
				temp.setRoomId(roomInfo.getRoomId());
				temp = roomInfoMapper.selectOne(temp);
				// 判断现在预定总数是否超过修改的总房间数
				// 如果现在预定总数超过修改的总房间数，直接返回错误
				if (temp.getSpareReservedNum().intValue() + temp.getNormalReservedNum().intValue() > roomInfo
						.getRoomNumber().intValue()) {
					// 返回已经预定人数超过现在所修改的总房间数，修改失败
					map = makeMapMsg(map, HotelEnums.ROOMINFO_ADJUST_EXCEPTION);
					return map;
				}
				// // 修改总的房间数
				// roomInfo.setRoomNumber(null==roomInfo.getRoomNumber()?0:roomInfo.getRoomNumber());
				// // 修改备用房间数
				// roomInfo.setRoomSpare(null==roomInfo.getRoomSpare()?0:roomInfo.getRoomSpare());
				// 计算普通可预定房间数和备用可预定房间数
				// 现在总的预定人数
				int total = temp.getNormalReservedNum().intValue() + temp.getSpareReservedNum().intValue();
				// 现在普通的可预订房间数
				int normal = roomInfo.getRoomNumber().intValue() - roomInfo.getRoomSpare().intValue();
				// 修改普通预定房间数和备用预定房间数
				roomInfo.setNormalReservedNum(total > normal ? normal : total);
				roomInfo.setSpareReservedNum(total > normal ? total - normal : 0);
			}

			if (roomInfoMapper.updateByPrimaryKeySelective(roomInfo) == 1) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_UPDATE_SUCCESS);
				map.put("entity", roomInfo);
			}
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_UPDATE_EXCEPTION);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectRoomInfoByRoomType(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// if (null == roomInfo || null == roomInfo.getRoomTypeId() || null
			// == roomInfo.getHotelId()) {
			// map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_ROOMTYPENULL);
			// return map;
			// }
			roomInfo.setDelFlag("0");
			List<RoomInfo> list = roomInfoMapper.select(roomInfo);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_SUCCESS);
			map.put("list", list);
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_EXCEPTION);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectRoomInfoByHotelId(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == roomInfo || null == roomInfo.getHotelId()) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_HOTELIDNULL);
				return map;
			}
			roomInfo.setDelFlag("0");
			List<RoomInfo> list = roomInfoMapper.select(roomInfo);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_SUCCESS);
			map.put("list", list);
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_EXCEPTION);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectRoomInfoById(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == roomInfo || null == roomInfo.getRoomId()) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_IDNULL);
				return map;
			}
			RoomInfo temp=new RoomInfo();
			temp.setRoomId(roomInfo.getRoomId());
			roomInfo = roomInfoMapper.selectOne(temp);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_SUCCESS);
			map.put("entity", roomInfo);
		} catch (Exception e) {
			printException(e);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_EXCEPTION);
		}
		return map;
	}

	public Map<String, Object> selectHotelRoomList(int hotelId) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (0 >= hotelId) {
				return makeMapMsg(map, HotelEnums.HOTELINFO_HOTELIDERROR);
			}
			List<RoomInfo> list = roomInfoMapper.selectHotelRoomList(hotelId);
			if (null != list && list.size() > 0) {
				map.put("hotelRoomList", list);
				return makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_SUCCESS);
			} else {
				return makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_EXCEPTION);
			}
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.ROOMINFO_SELECT_EXCEPTION);
		}
	}

	public Map<String, Object> adjustRoomInfo(RoomInfo roomInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (null == roomInfo || null == roomInfo.getRoomId()) {
				map = makeMapMsg(map, HotelEnums.ROOMINFO_ADJUST_IDNULL);
				return map;
			}
			RoomInfo temp = new RoomInfo();
			temp.setRoomId(roomInfo.getRoomId());
			temp = roomInfoMapper.selectOne(temp);
			// 判断现在预定总数是否超过修改的总房间数
			// 如果现在预定总数超过修改的总房间数，直接返回错误
			if (temp.getSpareReservedNum().intValue() + temp.getNormalReservedNum().intValue() > roomInfo
					.getRoomNumber().intValue()) {
				// 返回已经预定人数超过现在所修改的总房间数，修改失败
				map = makeMapMsg(map, HotelEnums.ROOMINFO_ADJUST_EXCEPTION);
				return map;
			}
			// 修改总的房间数
			temp.setRoomNumber(null == roomInfo.getRoomNumber() ? 0 : roomInfo.getRoomNumber());
			// 修改备用房间数
			temp.setRoomSpare(null == roomInfo.getRoomSpare() ? 0 : roomInfo.getRoomSpare());
			// 计算普通可预定房间数和备用可预定房间数
			// 现在总的预定人数
			int total = temp.getNormalReservedNum().intValue() + temp.getSpareReservedNum().intValue();
			// 现在普通的可预订房间数
			int normal = temp.getRoomNumber().intValue() - temp.getRoomSpare().intValue();
			// 修改普通预定房间数和备用预定房间数
			temp.setNormalReservedNum(total > normal ? normal : total);
			temp.setSpareReservedNum(total > normal ? total - normal : 0);
			roomInfoMapper.updateByPrimaryKeySelective(temp);
			map = makeMapMsg(map, HotelEnums.ROOMINFO_ADJUST_SUCCESS);
		} catch (Exception e) {
			printException(e);
			return makeMapMsg(map, HotelEnums.ROOMINFO_ADJUST_EXCEPTION);
		}
		return map;
	}

	/**
	 * 判断房间类型是否存在
	 * 
	 * @param roomInfo
	 * @return
	 */
	private boolean roomTypeIsExist(RoomInfo roomInfo) {
		// RoomTypeInfo temp =
		// roomTypeInfoMapper.selectByPrimaryKey(roomInfo.getRoomTypeId());

		return false;
	}

	private void printException(Exception e) {
		StringWriter errorsWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(errorsWriter));
		LOGGER.error(errorsWriter.toString());
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	private Map<String, Object> makeMapMsg(Map<String, Object> map, HotelEnums enums) {
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}

}
