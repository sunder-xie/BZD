package com.bizideal.whoami.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.enums.MeetingDynamicEnums;
import com.bizideal.whoami.meetingdynamic.facade.MeetingDynamicFacade;
import com.bizideal.whoami.service.MeetingDynamicBiz;

/**
 * @ClassName MeetingDynamicFacade
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-14 15:49:31
 */
@Component("meetingDynamicFacade")
public class MeetingDynamicFacadeImpl implements MeetingDynamicFacade {

	@Autowired
	private MeetingDynamicBiz meetingDynamicBiz;

	@Override
	public Map<String, Object> insertMeetingDynamic(MeetingDynamic meetingDynamic) {

		Integer save = meetingDynamicBiz.save(meetingDynamic);
		Map<String, Object> map = new HashMap<String, Object>();
		if (save > 0) {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_INSERT_SUCCESS);
		} else {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_INSERT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> updateMeetingDynamic(MeetingDynamic meetingDynamic) {

		Integer update = meetingDynamicBiz.update(meetingDynamic);
		Map<String, Object> map = new HashMap<String, Object>();
		if (update > 0) {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_UPDATE_SUCCESS);
		} else {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_UPDATE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> deleteMeetingDynamic(MeetingDynamic meetingDynamic) {

		Map<String, Object> map = new HashMap<String, Object>();
		Integer deleteByWhere = meetingDynamicBiz.deleteByWhere(meetingDynamic);
		if (deleteByWhere > 0) {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_DELETE_SUCCESS);
		} else {
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_DELETE_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectMeetingDynamic(MeetingDynamic meetingDynamic) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<MeetingDynamic> queryAllDynamic = meetingDynamicBiz.queryListByWhere(meetingDynamic);
		if(null != queryAllDynamic){
			map.put("list", queryAllDynamic);
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_SELECT_SUCCESS);
		}else{
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_SELECT_EXCEPTION);
		}
	}

	@Override
	public Map<String, Object> selectMeetingDynamicByDynamicId(MeetingDynamic meetingDynamic) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		MeetingDynamic queryOne = meetingDynamicBiz.queryOne(meetingDynamic);
		List<MeetingDynamic> list = new ArrayList<MeetingDynamic>();
		if(null != queryOne){
			list.add(queryOne);
			map.put("list", list.get(0));
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_SELECT_ONE_SUCCESS);
		}else{
			return makeMapMsg(map, MeetingDynamicEnums.MEETINGDYNAMIC_SELECT_ONE_EXCEPTION);
		}
	}

	private Map<String, Object> makeMapMsg(Map<String, Object> map, MeetingDynamicEnums enums) {
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}
}
