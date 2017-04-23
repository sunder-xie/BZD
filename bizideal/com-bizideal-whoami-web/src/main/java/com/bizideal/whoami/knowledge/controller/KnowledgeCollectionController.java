package com.bizideal.whoami.knowledge.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeCollection;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeCollectionFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.github.pagehelper.PageInfo;

/**
 * 知识库收藏
 * 
 * @author sy
 * @date 2017-3-14 14:16:37
 */
@Controller
@RequestMapping("/knowledgeCollection")
public class KnowledgeCollectionController {

	@Autowired
	private KnowledgeCollectionFacade knowledgeCollectionFacade;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;
	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Value("${fastdfsurl}")
	private String fastdfsurl;

	/**
	 * 知识库收藏列表
	 * 
	 * @param request
	 * @param model
	 * @param name查询关键字
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/list")
	public String collectList(HttpServletRequest request, Model model, String name) throws UnsupportedEncodingException {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		knowledgeCollection.setUserId(user.getUserId());
		knowledgeCollection.setPageNum(1);
		knowledgeCollection.setPageSize(1000);

		// 有查询关键字时
		if (null != name && !"".equals(name)) {
			// get请求关键字转码
			name = new String(name.getBytes("iso8859-1"), "UTF8");
			knowledgeCollection.setTypeUrl(name);
			model.addAttribute("name", name);
		}
		// 查询收藏文件列表
		PageInfo<KnowledgeFile> selectMyCollectionsByUserId = knowledgeCollectionFacade
				.selectMyCollectionsByUserId(knowledgeCollection);
		model.addAttribute("fileList", selectMyCollectionsByUserId);
		model.addAttribute("fastdfsurl", fastdfsurl);
		return "/mine/person-repository";
	}

	/**
	 * ajax添加收藏
	 * 
	 * @param request
	 * @param fileId文件id
	 * @param meeId会议id
	 * @return 成功为1
	 */
	@RequestMapping("/add")
	public @ResponseBody int addCollection(HttpServletRequest request, Integer fileId, Integer meeId) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeeId(meeId);
		meetingInfo = meetingInfoFacade.selectMeetingInfoById(meetingInfo);

		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		knowledgeCollection.setFileId(fileId);
		knowledgeCollection.setTypeUrl(meetingInfo.getMeeName());
		knowledgeCollection.setUserId(user.getUserId());
		knowledgeCollection.setMeeId(meetingInfo.getMeeId());
		return knowledgeCollectionFacade.saveCollection(knowledgeCollection);
	}

	/**
	 * ajax取消收藏
	 * 
	 * @param request
	 * @param fileId文件id
	 * @param meeId会议id
	 * @return 成功为1
	 */
	@RequestMapping("/cancel")
	public @ResponseBody int cancelCollection(HttpServletRequest request, Integer fileId, Integer meeId) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		knowledgeCollection.setFileId(fileId);
		knowledgeCollection.setUserId(user.getUserId());
		return knowledgeCollectionFacade.deleteCollection(knowledgeCollection);
	}

	@RequestMapping("/app/list/{userId}")
	public String appCollectList(@PathVariable("userId") String userId, HttpServletRequest request, Model model,
			String name) throws UnsupportedEncodingException {

		UserWeixinInfo user = userWeixinInfoFacade.selectByUserId(userId);
		request.getSession().setAttribute("user", user);

		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		knowledgeCollection.setUserId(user.getUserId());
		knowledgeCollection.setPageNum(1);
		knowledgeCollection.setPageSize(1000);

		// 有查询关键字时
		if (null != name && !"".equals(name)) {
			// get请求关键字转码
			name = new String(name.getBytes("iso8859-1"), "UTF8");
			knowledgeCollection.setTypeUrl(name);
			model.addAttribute("name", name);
		}
		// 查询收藏文件列表
		PageInfo<KnowledgeFile> selectMyCollectionsByUserId = knowledgeCollectionFacade
				.selectMyCollectionsByUserId(knowledgeCollection);
		model.addAttribute("fileList", selectMyCollectionsByUserId);
		model.addAttribute("fastdfsurl", fastdfsurl);
		return "/mine/person-repository";
	}
}
