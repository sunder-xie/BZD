package com.bizideal.whoami.im;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.entity.BaseResData;
import com.bizideal.whoami.facade.meeting.dto.MeetingUsersDto;
import com.bizideal.whoami.facade.meeting.entity.MeetingGroups;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingUserGroupFacade;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.user.entity.UserFriend;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.HuanxinFriendFacade;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;

@Controller
@RequestMapping("/imService")
public class IMserviceController {
	
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	
	@Autowired
	MeetingUserGroupFacade meetingUserGroupFacade;
	
	@Autowired
	HuanxinFriendFacade huanxinFriendFacade;
	
	@Value("${fastdfsurl}")
	private String fastdfsurl;
	
	@RequestMapping("/toWebChat")
	public ModelAndView toWebChat(ModelAndView model, HttpServletRequest request){
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		
		model.addObject("meeId", meetingInfo.getMeeId());
		model.setViewName("web-chat/group-chat");
//		model.set("web-chat/group-chat");
		return model;
	}
	
	@RequestMapping("/chatgroup/{meeId}")
	@ResponseBody
	public Map<String,Object> getUserMapper(@PathVariable("meeId") Integer meeId, HttpServletRequest request){
		UserWeixinInfo user=((UserWeixinInfo) request.getSession().getAttribute("user"));
		MeetingGroups meetingGroup=meetingUserGroupFacade.selectByMeeId(meeId);
		String groupId=meetingGroup.getGroupId();
		List<MeetingUsersDto> list=meetingUserGroupFacade.selectGroupMembers(groupId);/*"10823249494017"*/
		Map<String,Object> map=new HashMap<String,Object>();
		if(null!=list && list.size()>0){
			for(MeetingUsersDto ufd:list){
				if (!StringUtils.startsWith(ufd.getHeadimgurl(),"http://wx")) {
					if(StringUtils.isBlank(ufd.getHeadimgurl())){
						ufd.setHeadimgurl("/whoami/css/img/user-icon.jpg");
					}else {
						ufd.setHeadimgurl(fastdfsurl + ufd.getHeadimgurl());
					}
					if(StringUtils.isBlank(ufd.getNickname())){
						ufd.setNickname("路人"+(list.indexOf(ufd)+1));
					}
				}
				map.put(ufd.getUserId(), ufd);
			}
		}
		Map<String,Object> userAndMembers=new HashMap<String,Object>();
		userAndMembers.put("user", user);
		userAndMembers.put("groupId", groupId);
		userAndMembers.put("members", map);
		System.out.println(userAndMembers);
		return userAndMembers;
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserWeixinInfo getUser(HttpServletRequest request){
		UserWeixinInfo user=((UserWeixinInfo) request.getSession().getAttribute("user"));
		System.out.println(user);
		return user;
	}
	
	@RequestMapping("/addFriend/{userId}")
	@ResponseBody
	public BaseResData<String> addFriend(@PathVariable("userId") String userId,HttpServletRequest request){
		BaseResData<String> baseResData=new BaseResData<String>();
		UserWeixinInfo user=((UserWeixinInfo) request.getSession().getAttribute("user"));
		UserFriend userFriend=new UserFriend();
		userFriend.setUserId(user.getUserId());
		userFriend.setUserIdFriend(userId);
		DubboxResult dubboxResult=huanxinFriendFacade.insert(userFriend);
		if(dubboxResult.getStatus().equals("0")){
			baseResData.setCode("1");
		}else{
			baseResData.setCode("0");
		}
		baseResData.setResdata(userId);
		return baseResData;
	}
	
}
