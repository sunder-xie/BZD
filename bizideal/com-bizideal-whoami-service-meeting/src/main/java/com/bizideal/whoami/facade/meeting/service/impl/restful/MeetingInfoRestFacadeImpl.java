package com.bizideal.whoami.facade.meeting.service.impl.restful;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.meeting.biz.MeetingInfoBiz;
import com.bizideal.whoami.core.meeting.biz.SubMeetingInfoBiz;
import com.bizideal.whoami.facade.meeting.dto.MeetingInfoDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.entity.SubMeetingInfo;
import com.bizideal.whoami.facade.meeting.service.restful.MeetingInfoRestFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 会议rest接口
 * @ClassName MeetingInfoRestFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月7日
 */
@Path("meetingInfo")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Component("meetingInfoRestFacade")
public class MeetingInfoRestFacadeImpl implements MeetingInfoRestFacade{

	@Autowired
	private MeetingInfoBiz meetingInfoBiz;

	@Autowired
	private SubMeetingInfoBiz subMeetingInfoBiz;
	
	@PUT
	@Path("addMeeting")
	@Override
	public int insertMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.insertMeetingInfo(meetingInfo);
	}

	@POST
	@Path("update")
	@Override
	public Map<String,Object> updateMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.updateMeetingInfo(meetingInfo);
	}

	@POST
	@Path("deleteSub")
	@Override
	public Map<String,Object> deleteSubMeetingInfoById(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.deleteSubMeetingInfoById(subMeetingInfo);
	}

	@POST
	@Path("selectMeeting")
	@Override
	public Map<String,Object> selectMeetingInfo(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.selectMeetingInfo(meetingInfo);
	}

	@POST
	@Path("deleteSubMeetingByIds")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Override
	public Map<String,Object> deleteSubMeetingInfoByIds(@FormParam("ids")List<String> ids) 
			throws CustomException{
		return subMeetingInfoBiz.deleteSubMeetingInfoByIds(ids);
	}

	@POST
	@Path("selectMeetingByName")
	@Override
	public Map<String,Object> selectMeetingInfoByName(MeetingInfo meetingInfo) 
			throws CustomException{
		return meetingInfoBiz.selectMeetingInfoByName(meetingInfo);
	}

	@PUT
	@Path("addSubMeeting")
	@Override
	public Map<String, Object> insertSubMeetingInfo(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.insertSubMeetingInfo(subMeetingInfo);
	}

	@POST
	@Path("deleteMeetingByIds")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Override
	public Map<String, Object> deleteMeetingInfoByIds(@FormParam("ids")List<String> ids) 
			throws CustomException{
		return meetingInfoBiz.deleteMeetingInfoByIds(ids);
	}

	@POST
	@Path("selectSubMeeByParentId")
	@Override
	public Map<String, Object> selectSubMeetingInfo(SubMeetingInfo subMeetingInfo) 
			throws CustomException{
		return subMeetingInfoBiz.selectSubMeetingInfoByParentId(subMeetingInfo);
	}

	@POST
	@Path("selectPopMeetingInfo")
	@Override
	public Map<String, Object> selectPopMeetingInfo(MeetingInfoDto meetingInfoDto) 
			throws CustomException{
		
		return meetingInfoBiz.selectPopMeetingInfo(meetingInfoDto);
	}
}