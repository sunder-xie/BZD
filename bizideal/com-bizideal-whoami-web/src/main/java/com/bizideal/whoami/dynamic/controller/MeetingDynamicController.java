package com.bizideal.whoami.dynamic.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicCrawler;
import com.bizideal.whoami.meetingdynamic.facade.MeetingDynamicCrawlerFacade;
import com.bizideal.whoami.meetingdynamic.facade.MeetingDynamicFacade;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.utils.RandomUUID;
import com.bizideal.whoami.utils.RoleModuleUtils;

/**
 * @ClassName MeetingDynamicController
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-16 13:33:11
 */
@Controller
@RequestMapping("/meetingDynamic")
public class MeetingDynamicController {

	@Autowired
	private MeetingDynamicFacade meetingDynamicFacade;
	@Autowired
	RedisClientTemplate redisClientTemplate;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;// 权限模块接口
	@Autowired
	MeetingHallFacade meetingHallFacade;
	@Autowired
	private MeetingDynamicCrawlerFacade meetingDynamicCrawlerFacade;

	@Value("${fastdfsurl}")
	private String fastdfsurl;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/addynamic")
	public String home(Integer meetingId, Integer meetHallId, ModelMap mode) {

		mode.addAttribute("meetingId", meetingId);
		mode.addAttribute("meetHallId", meetHallId);
		return "meeting_dynamic/admin-addMeetDetail";
	}

	/**
	 * 上传图片
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("file") CommonsMultipartFile file, String ShortUuid, HttpServletRequest request) {

		if ("".equals(ShortUuid)) {
			ShortUuid = RandomUUID.generateShortUuid();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			DiskFileItem fi = (DiskFileItem) file.getFileItem();
			File image = fi.getStoreLocation();
			String uploadFile = FastDFSClient.uploadFile(image, file.getOriginalFilename());
			int timeOut = 7000;
			// 存Redis
			long ok = redisClientTemplate.saddSET(ShortUuid, timeOut, uploadFile);
			if (ok == 1) {
				map.put("status", 0);
				map.put("msg", fastdfsurl + uploadFile);
				map.put("ShortUuid", ShortUuid);
			} else {
				FastDFSClient.deleteFile(uploadFile);
				map.put("status", 1);
				map.put("msg", "redis保存失败！");
			}
			return map;
		} catch (Exception e) {
			map.put("status", 1);
			map.put("msg", e);
			return map;
		}
	}

	/**
	 * 添加会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/addDynamic")
	@ResponseBody
	public Map<String, Object> addDynamic(@RequestBody MeetingDynamic meetingDynamic) {
		meetingDynamic.setCreateTime(System.currentTimeMillis());
		Map<String, Object> insertMeetingDynamic = meetingDynamicFacade.insertMeetingDynamic(meetingDynamic);
		return insertMeetingDynamic;
	}

	/**
	 * 删除会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/deleteDynamic")
	public String deleteDynamic(Integer dynamicId, Integer meetingId, Integer meetHallId) {

		MeetingDynamic meetingDynamic = new MeetingDynamic();
		meetingDynamic.setDynamicId(dynamicId);
		Map<String, Object> deleteMeetingDynamic = meetingDynamicFacade.deleteMeetingDynamic(meetingDynamic);
		return "redirect:/meetingDynamic/selectallDynamic";
	}

	/**
	 * 修改会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/updateDynamic")
	@ResponseBody
	public Map<String, Object> updateDynamic(@RequestBody MeetingDynamic meetingDynamic) {
		meetingDynamic.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> updateMeetingDynamic = meetingDynamicFacade.updateMeetingDynamic(meetingDynamic);
		return updateMeetingDynamic;
	}

	/**
	 * 查看单个会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/selectoneDynamic/{dynamicId}")
	public String selectOneDynamic(@PathVariable("dynamicId") Integer dynamicId, Model mode, HttpServletRequest request) {
		// 获取用户Id和和当前会议
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userId, meetingInfo.getMeeId());

		RoleModuleUtils.permissions(hallrole, role, userId, meetingInfo, meetingHall, 3, mode);
		MeetingDynamic meetingDynamic = new MeetingDynamic();
		meetingDynamic.setDynamicId(dynamicId);
		Map<String, Object> selectMeetingDynamicByDynamicId = meetingDynamicFacade.selectMeetingDynamicByDynamicId(meetingDynamic);
		mode.addAttribute("onedynamic", selectMeetingDynamicByDynamicId.get("list"));
		// 页面
		return "meeting_dynamic/admin-article-details";
	}

	/**
	 * 编辑会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/selectDynamic")
	public String selectDynamic(Integer dynamicId, ModelMap mode) {

		MeetingDynamic meetingDynamic = new MeetingDynamic();
		meetingDynamic.setDynamicId(dynamicId);
		Map<String, Object> selectMeetingDynamicByDynamicId = meetingDynamicFacade.selectMeetingDynamicByDynamicId(meetingDynamic);
		mode.addAttribute("onedynamic", selectMeetingDynamicByDynamicId.get("list"));
		// 页面
		return "meeting_dynamic/admin-editMeetDetail";
	}

	/**
	 * 查询所有会议动态
	 * 
	 * @param meetingDynamic
	 * @return
	 */
	@RequestMapping("/selectallDynamic")
	public String selectAllDynamic(Model mode, HttpServletRequest request) {
		// 获取用户Id
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userId, meetingInfo.getMeeId());

		RoleModuleUtils.permissions(hallrole, role, userId, meetingInfo, meetingHall, 3, mode);

		MeetingDynamic meetingDynamic = new MeetingDynamic();
		meetingDynamic.setMeetingId(meetingInfo.getMeeId());
		meetingDynamic.setMeetHallId(meetingInfo.getHallId());
		mode.addAttribute("meetingId", meetingInfo.getMeeId());
		mode.addAttribute("meetHallId", meetingInfo.getHallId());
		Map<String, Object> selectMeetingDynamic = meetingDynamicFacade.selectMeetingDynamic(meetingDynamic);
		mode.addAttribute("alldynamic", selectMeetingDynamic.get("list"));
		// 页面
		return "meeting_dynamic/admin-meetDynamic";
	}

	@RequestMapping(value = "/linkNetwork")
	public String linkNetwork(HttpServletRequest request, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		Integer meetHallId = meetingHall.getHallId();
		MeetingInfo meeting = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Integer meetingId = meeting.getMeeId();

		MeetingDynamicCrawler meetingDynamicCrawler = new MeetingDynamicCrawler();
		meetingDynamicCrawler.setMeetingId(meetingId);
		meetingDynamicCrawler.setMeetHallId(meetHallId);
		Map<String, Object> map = meetingDynamicCrawlerFacade.selectCrawlerUrlToMeeting(meetingDynamicCrawler);

		MeetingDynamicCrawler mdc = (MeetingDynamicCrawler) map.get("entity");
		model.addAttribute("flag", 0);
		if (null != mdc) {
			meetingDynamicCrawler = mdc;
			model.addAttribute("flag", 1);
		}
		model.addAttribute("crawler", meetingDynamicCrawler);
		return "meet-func/link-network";
	}

	@RequestMapping(value = "/crawler")
	public @ResponseBody Map<String, Object> crawler(HttpServletRequest request, HttpServletResponse response, String url, Integer flag, Integer crawlerId, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		Integer meetHallId = meetingHall.getHallId();
		MeetingInfo meeting = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Integer meetingId = meeting.getMeeId();
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();

		MeetingDynamicCrawler meetingDynamicCrawler = new MeetingDynamicCrawler();
		meetingDynamicCrawler.setMeetingId(meetingId);
		meetingDynamicCrawler.setMeetHallId(meetHallId);
		meetingDynamicCrawler.setUserId(userId);
		meetingDynamicCrawler.setUrl(url);

		Map<String, Object> map = null;
		if (0 == flag) {
			map = meetingDynamicCrawlerFacade.addCrawlerUrlToMeeting(meetingDynamicCrawler);
		} else {
			meetingDynamicCrawler.setCrawlerId(crawlerId);
			map = meetingDynamicCrawlerFacade.updateCrawlerUrlToMeeting(meetingDynamicCrawler);
		}
		return ("6360".equals(map.get("code")) || "6310".equals(map.get("code"))) ? null : map;
	}

	@RequestMapping(value = "/climb")
	public @ResponseBody Map<String, Object> climb(HttpServletRequest request, HttpServletResponse response, String url, Integer flag, Integer crawlerId, Model model) {

		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		Integer meetHallId = meetingHall.getHallId();
		MeetingInfo meeting = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		Integer meetingId = meeting.getMeeId();

		MeetingDynamicCrawler meetingDynamicCrawler = new MeetingDynamicCrawler();
		meetingDynamicCrawler.setMeetingId(meetingId);
		meetingDynamicCrawler.setMeetHallId(meetHallId);

		Map<String, Object> map = meetingDynamicCrawlerFacade.selectCrawlerUrlToMeeting(meetingDynamicCrawler);
		MeetingDynamicCrawler mdc = (MeetingDynamicCrawler) map.get("entity");

		Map<String, Object> map2 = meetingDynamicCrawlerFacade.climbAllDynamics(mdc);
		return map2;
	}
}
