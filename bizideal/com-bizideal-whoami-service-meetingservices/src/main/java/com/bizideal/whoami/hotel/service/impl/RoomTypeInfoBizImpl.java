package com.bizideal.whoami.hotel.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.facade.hotel.entity.RoomTypeInfo;
import com.bizideal.whoami.facade.hotel.enums.HotelEnums;
import com.bizideal.whoami.hotel.mapper.RoomTypeInfoMapper;
import com.bizideal.whoami.hotel.service.RoomTypeInfoBiz;

/**
 * 
 * @ClassName RoomTypeInfoBizImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月21日
 */
@Service("roomTypeInfoBiz")
public class RoomTypeInfoBizImpl implements RoomTypeInfoBiz{

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeInfoBizImpl.class);
	
	@Autowired
	RoomTypeInfoMapper roomTypeInfoMapper;
	
	@Override
	public Map<String, Object> insertRoomTypeInfo(RoomTypeInfo roomTypeInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			List<RoomTypeInfo> list=new ArrayList<RoomTypeInfo>();
			RoomTypeInfo temp=new RoomTypeInfo();
			temp.setRoomTypeName(roomTypeInfo.getRoomTypeName());
			temp.setDelFlag("0");
			list=roomTypeInfoMapper.select(temp);
			if(null!=list && list.size()>0){
				map=makeMapMsg(map, HotelEnums.ROOMTYPE_INSERT_REPEAT);
				return map;
			}
			if(roomTypeInfoMapper.insertSelective(roomTypeInfo)==1){
				map=makeMapMsg(map, HotelEnums.ROOMTYPE_INSERT_SUCCESS);
				map.put("entity", roomTypeInfo);
			}
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_INSERT_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteRoomTypeInfoById(RoomTypeInfo roomTypeInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			roomTypeInfo.setDelFlag("1");
			if(roomTypeInfoMapper.updateByPrimaryKeySelective(roomTypeInfo)==1){
				map=makeMapMsg(map, HotelEnums.ROOMTYPE_DELETE_SUCCESS);
			}
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_DELETE_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> updateRoomTypeInfo(RoomTypeInfo roomTypeInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			List<RoomTypeInfo> list=new ArrayList<RoomTypeInfo>();
			RoomTypeInfo temp=new RoomTypeInfo();
			temp.setRoomTypeName(roomTypeInfo.getRoomTypeName());
			temp.setDelFlag("0");
			list=roomTypeInfoMapper.select(temp);
			if(null!=list && list.size()>0){
				boolean flag=false;
				for(RoomTypeInfo rt:list){
					if(rt.getRoomTypeId().equals(roomTypeInfo.getRoomTypeId())){
						flag=true;
						break;
					}
				}
				if(flag){
					map=makeMapMsg(map, HotelEnums.ROOMTYPE_UPDATE_REPEAT);
					return map;
				}
			}
			if(roomTypeInfoMapper.updateByPrimaryKeySelective(roomTypeInfo)==1){
				map=makeMapMsg(map, HotelEnums.ROOMTYPE_UPDATE_SUCCESS);
				map.put("entity", roomTypeInfo);
			}
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_UPDATE_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> selectRoomTypeInfoById(RoomTypeInfo roomTypeInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			if(null!=roomTypeInfo && null!=roomTypeInfo.getRoomTypeId()){
				RoomTypeInfo temp=new RoomTypeInfo();
				temp.setRoomTypeId(roomTypeInfo.getRoomTypeId());
				roomTypeInfo=roomTypeInfoMapper.selectOne(temp);
				if(null!=roomTypeInfo){
					map=makeMapMsg(map, HotelEnums.ROOMTYPE_SELECT_SUCCESS);
					map.put("entity",roomTypeInfo);
				}
			}else{
				map=makeMapMsg(map,HotelEnums.ROOMTYPE_SELECT_IDNULL);
			}
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_SELECT_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> selectRoomTypeInfoByName(
			RoomTypeInfo roomTypeInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			if(null==roomTypeInfo || roomTypeInfo.getRoomTypeName()==null){
				map=makeMapMsg(map, HotelEnums.ROOMTYPE_SELECT_NAMENULL);
				return map;
			}
			List<RoomTypeInfo> list=new ArrayList<RoomTypeInfo>();
			RoomTypeInfo temp=new RoomTypeInfo();
			temp.setRoomTypeName(roomTypeInfo.getRoomTypeName());
			temp.setDelFlag("0");
			list=roomTypeInfoMapper.select(temp);
			map=makeMapMsg(map, HotelEnums.ROOMTYPE_SELECT_SUCCESS);
			map.put("list", list);
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_SELECT_EXCEPTION);
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> selectAllRoomTypeInfo() {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			RoomTypeInfo temp=new RoomTypeInfo();
			temp.setDelFlag("0");
			List<RoomTypeInfo> list=roomTypeInfoMapper.select(temp);
			map=makeMapMsg(map, HotelEnums.ROOMTYPE_SELECT_SUCCESS);
			map.put("list", list);
		}catch(Exception e){
			printException(e);
			map=makeMapMsg(map,HotelEnums.ROOMTYPE_SELECT_EXCEPTION);
			return map;
		}
		return map;
	}

	
	private void printException(Exception e){
		StringWriter errorsWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(errorsWriter));
		LOGGER.error(errorsWriter.toString());
	}
	private Map<String,Object> makeMapMsg(Map<String,Object> map,HotelEnums enums){
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}
}
