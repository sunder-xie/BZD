package com.bizideal.whoami.utils;

import javax.servlet.http.HttpServletRequest;

import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.user.entity.UserWeixinInfo;

/**
 * @ClassName SessionUtil
 * @Description TODO(项目中Session相关的操作类)
 * @Author Zj.Qu
 * @Date 2017-01-11 15:54:51
 */
public class SessionUtil {

	/**
	 * 获取Session中的UserWeixinInfo
	 * 
	 * @param request
	 * @return
	 */
	public static UserWeixinInfo getSessionUser(HttpServletRequest request) {
		Object sessionOBJ = request.getSession().getAttribute("user");
		if (null != sessionOBJ) {
			return (UserWeixinInfo) sessionOBJ;
		}
		return null;
	}

	/**
	 * 获取Session中的UserId
	 * 
	 * @param request
	 * @return
	 */
	public static String getSessionUserId(HttpServletRequest request) {
		UserWeixinInfo sessionUser = SessionUtil.getSessionUser(request);
		if (null != sessionUser) {
			return sessionUser.getUserId();
		}
		return null;
	}

	/**
	 * 获取Session中的MeetingHall
	 * 
	 * @param request
	 * @return
	 */
	public static MeetingHall getSessionMeetingHall(HttpServletRequest request) {
		Object sessionOBJ = request.getSession().getAttribute("existMeeHall");
		if (null != sessionOBJ) {
			return (MeetingHall) sessionOBJ;
		}
		return null;
	}

	/**
	 * 获取Session中的HallId
	 * 
	 * @param request
	 * @return
	 */
	public static int getSessionHallId(HttpServletRequest request) {
		MeetingHall sessionUser = SessionUtil.getSessionMeetingHall(request);
		if (null != sessionUser) {
			return sessionUser.getHallId();
		}
		return -1;
	}

}
