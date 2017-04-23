package com.bizideal.whoami.facade.meeting.service.impl.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.meeting.biz.MeetingHallBiz;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.service.restful.MeetingHallRestFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.pojo.DubboxResult;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName MeetingHallRestFacadeImpl
 * @Description TODO(会议厅rest接口)
 * @Author Zj.Qu
 * @Date 2016-12-29 16:13:46
 */
@Path("meetinghall")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Component("meetingHallRestFacade")
public class MeetingHallRestFacadeImpl implements MeetingHallRestFacade {

	@Autowired
	private MeetingHallBiz meetingHallBiz;

	@POST
	@Override
	public DubboxResult addMeetingHall(MeetingHall meetingHall) 
			throws CustomException{

		if (StringUtils.isBlank(meetingHall.getUserId())) {
			return DubboxResult.build("6509", "用户信息不存在!", "-1");
		}

		Integer returnValue = meetingHallBiz.saveSelective(meetingHall);
		if (returnValue > 0)
			return DubboxResult.build("0", "操作成功!", returnValue.toString());
		else
			return DubboxResult.build("6505", "操作失败!", returnValue.toString());
	}

	@PUT
	@Override
	public DubboxResult updateMeetingHall(MeetingHall meetingHall) 
			throws CustomException{
		Integer returnValue = meetingHallBiz.updateMeetingHall(meetingHall);
		if (returnValue > 0) {
			return DubboxResult.build("0", "操作成功!", meetingHall.getHallId().toString());
		} else {
			return DubboxResult.build("6505", "操作失败!", returnValue.toString());
		}
	}

	@GET
	@Path("{hall_id}")
	@Override
	public Response queryByIdMeetingHall(@PathParam("hall_id") Integer hallId) 
			throws CustomException{
		MeetingHall meetingHall = meetingHallBiz.queryById(hallId);
		return Response.status(Response.Status.OK).entity(meetingHall).build();
	}

	@DELETE
	@Path("{hall_id}")
	@Override
	public DubboxResult delMeetingHall(@PathParam("hall_id") Integer hallId) 
			throws CustomException{
		Integer returnValue = meetingHallBiz.delLogicMeetingHall(hallId);
		if (returnValue > 0) {
			return DubboxResult.build("0", "操作成功!", hallId.toString());
		} else {
			return DubboxResult.build("6505", "操作失败!", hallId.toString());
		}
	}

	@GET
	@Path("user/{user_id}")
	@Override
	public Response queryByUserIdMeetingHall(@PathParam("user_id") String userId) 
			throws CustomException{
		MeetingHall meetingHall = meetingHallBiz.queryByUserIdMeetingHall(userId);
		if (meetingHall == null) {
			DubboxResult build = DubboxResult.build("6509", "该用户没有创建会议厅!", userId);
			return Response.status(Response.Status.OK).entity(build).build();
		}
		return Response.status(Response.Status.OK).entity(meetingHall).build();
	}

	@GET
	@Path("user/{user_id}/list")
	@Override
	public Response queryPageListByUserId(@PathParam("user_id") String userId, @QueryParam("pageNum") Integer pageNum,
			@QueryParam("pageSize") Integer pageSize) throws CustomException{
		PageInfo<MeetingHall> hallList = meetingHallBiz.queryPageListByUserId(userId, pageNum, pageSize);
		return Response.status(Response.Status.OK).entity(hallList).build();
	}

}