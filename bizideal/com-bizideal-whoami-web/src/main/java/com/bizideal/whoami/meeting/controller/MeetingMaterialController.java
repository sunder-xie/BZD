package com.bizideal.whoami.meeting.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.facade.meeting.entity.MeetingHall;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meeting.service.MeetingHallFacade;
import com.bizideal.whoami.facade.meeting.service.MeetingInfoFacade;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachReadFacade;
import com.bizideal.whoami.facade.meetingmaterial.service.MeetingAttachWriteFacade;
import com.bizideal.whoami.rolemodule.entity.HallRole;
import com.bizideal.whoami.rolemodule.entity.Role;
import com.bizideal.whoami.rolemodule.facade.RoleModuleReadFacade;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.BrokenFileUtil;
import com.bizideal.whoami.utils.ObjectId;
import com.bizideal.whoami.utils.Pdf2htmlEXUtil;
import com.bizideal.whoami.utils.RoleModuleUtils;
import com.bizideal.whoami.utils.WordToHtml;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月16日 上午10:11:18
 * @version 1.0
 */
@Controller
@RequestMapping("/material")
public class MeetingMaterialController {

	@Autowired
	private MeetingAttachReadFacade meetingAttachReadFacade;
	@Autowired
	private MeetingAttachWriteFacade meetingAttachWriteFacade;
	@Autowired
	private RoleModuleReadFacade roleModuleReadFacade;// 权限模块接口
	@Autowired
	private MeetingInfoFacade meetingInfoFacade;
	@Autowired
	MeetingHallFacade meetingHallFacade;
	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;

	@Value("${fastdfsurl}")
	private String fastdfsurl;
	private Logger logger = LoggerFactory.getLogger(MeetingMaterialController.class);

	@RequestMapping("/t")
	@ResponseBody
	public String tailPolicy() {
		return null;
	}

	@RequestMapping("/upload")
	@ResponseBody
	public ObjectNode update(@RequestParam("file") CommonsMultipartFile file, MeetingAttach meetingAttach, int flag, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			long startTime = System.currentTimeMillis();

			if (!judgeFileFormat(file)) {
				node.put("errcode", "1");
				node.put("errmsg", "不支持的文件格式");
				return node;
			}

			String originalFilename = file.getOriginalFilename();

			String suffix = StringUtils.substring(originalFilename, originalFilename.lastIndexOf(".") + 1);
			meetingAttach.setAttachSuffix(suffix);

			DiskFileItem filetemp = (DiskFileItem) file.getFileItem();
			File file_material = filetemp.getStoreLocation();
			// 获取上传文件的路径
			String realPath = request.getSession().getServletContext().getRealPath("/upload");

			String tempAbsolutePath = file_material.getAbsolutePath();
			String filename = ObjectId.get().toString() + ".html"; // 转换后文件名
			String filepath = ""; // 转换后文件绝对路径
			if ("pdf".equalsIgnoreCase(suffix)) {
				// windows转换
				// Pdf2htmlEXUtil.pdf2html(
				// "E:/workspace/dubbo/pdf/pdf2htmlEX/pdf2htmlEX.exe",
				// file_material.getAbsolutePath(), realPath, filename);
				// linux代码
				Pdf2htmlEXUtil.pdf2html_linux(file_material.getAbsolutePath(), realPath, filename);
				filepath = realPath + "/" + filename;
			} else if ("doc".equalsIgnoreCase(suffix)) {
				filepath = WordToHtml.word2003ToHtml(file_material.getName(), filename, realPath, file_material.getParent() + "/");
			} else if ("docx".equalsIgnoreCase(suffix)) {
				filepath = WordToHtml.word2007ToHtml(file_material.getName(), filename, realPath, file_material.getParent() + "/");
			} else {
				// 如果是图片直接上传到文件服务器
				filepath = tempAbsolutePath;
				filename = ObjectId.get().toString() + "." + suffix;
			}
			// 上传处理过后的文件到文件服务器
			File htmlFile = new File(filepath);
			String fileUrl = FastDFSClient.uploadFile(htmlFile, filename);
			meetingAttach.setAttachUrl(fileUrl);
			if (StringUtils.isBlank(fileUrl)) {
				logger.error("上传文件服务器失败！");
				node.put("errcode", "1");
				node.put("errmsg", "上传文件服务器失败！");
				return node;
			}
			// 原文件删除
			file_material.delete();
			htmlFile.delete();

			MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
			meetingAttach.setHallId(meetingInfo.getHallId());
			meetingAttach.setParmeeId(meetingInfo.getMeeId());

			UserWeixinInfo weixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
			// 0表示正常上传，1表示覆盖
			if (flag == 1) {
				meetingAttach.setUpdateUser(weixinInfo.getUserId());
				meetingAttach.setUpdateTime(System.currentTimeMillis());
				meetingAttachWriteFacade.updateCoverOther(meetingAttach);
				meetingAttach.setUpdateUser(null);
				meetingAttach.setUpdateTime(null);
			}
			// 创建人id
			meetingAttach.setCreateUser(weixinInfo.getUserId());
			meetingAttach.setCreateTime(System.currentTimeMillis());
			meetingAttachWriteFacade.saveSelective(meetingAttach);

			long endTime = System.currentTimeMillis();
			logger.debug("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
			node.put("errcode", "0");
			node.put("errmsg", "文件上传成功");
			node.put("path", fileUrl);
			return node;
		} catch (Exception e) {
			e.printStackTrace();
			node.put("errcode", "1");
			node.put("errmsg", e.getMessage());
			return node;
		}
	}

	/** 附件类型:0:会议议程,1:会议导读,2:会议资料 **/
	@RequestMapping("/queryPage/{attachType}")
	public String queryPage(@PathVariable("attachType") String attachType, Model model, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		String userId = ((UserWeixinInfo) request.getSession().getAttribute("user")).getUserId();
		MeetingHall meetingHall = (MeetingHall) request.getSession().getAttribute("existMeeHall");
		HallRole hallrole = roleModuleReadFacade.findHallRoleByUserIdHallId(userId, meetingHall.getHallId());// 会议厅角色
		Role role = roleModuleReadFacade.findRoleByUserIdHallId(userId, meetingInfo.getMeeId());// 会议角色
		String urlPath = null;// 跳转的路径

		MeetingAttach meetingAttach = new MeetingAttach();
		meetingAttach.setHallId(meetingInfo.getHallId());
		meetingAttach.setParmeeId(meetingInfo.getMeeId());
		meetingAttach.setAttachType(attachType);
		PageInfo<MeetingAttach> pageInfo = meetingAttachReadFacade.queryPageListByWhere(1, 10, meetingAttach);
		List<MeetingAttach> attachs = pageInfo.getList();
		for (MeetingAttach ma : attachs) {
			if ("url".equals(ma.getAttachSuffix())) {
				ma.setAttachUrl("/material/play/" + ma.getAttachId());
				continue;
			}
			// 拼接文件服务器ip地址
			ma.setAttachUrl(fastdfsurl + ma.getAttachUrl());
		}
		model.addAttribute("materials", pageInfo);
		// 判断权限
		if ("1".equals(attachType)) {
			RoleModuleUtils.permissions(hallrole, role, userId, meetingInfo, meetingHall, 4, model);
			urlPath = "meet-func/meet-introduction";// 会议导读
		}
		if ("0".equals(attachType)) {
			RoleModuleUtils.permissions(hallrole, role, userId, meetingInfo, meetingHall, 2, model);
			urlPath = "meet-func/meet-agenda";// 会议议程
		}
		if ("2".equals(attachType)) {
			RoleModuleUtils.permissions(hallrole, role, userId, meetingInfo, meetingHall, 6, model);
			urlPath = "meet-func/meet-material";// 会议资料
		}
		return urlPath;
	}

	/** 滑动加载，附件类型:0:会议议程,1:会议导读,2:会议资料 **/
	@RequestMapping("/queryPage/{attachType}/{pageIndex}")
	@ResponseBody
	public PageInfo<MeetingAttach> queryPageSlide(@PathVariable("attachType") String attachType, @PathVariable("pageIndex") int pageIndex, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		MeetingAttach meetingAttach = new MeetingAttach();
		meetingAttach.setHallId(meetingInfo.getHallId());
		meetingAttach.setParmeeId(meetingInfo.getMeeId());
		meetingAttach.setAttachType(attachType);
		PageInfo<MeetingAttach> pageInfo = meetingAttachReadFacade.queryPageListByWhere(pageIndex, 10, meetingAttach);
		List<MeetingAttach> attachs = pageInfo.getList();
		for (MeetingAttach ma : attachs) {
			if ("url".equals(ma.getAttachSuffix())) {
				ma.setAttachUrl("material/play/" + ma.getAttachId());
				continue;
			}
			// 拼接文件服务器ip地址
			ma.setAttachUrl(fastdfsurl + ma.getAttachUrl());
		}
		return pageInfo;
	}

	// 查询是否已经存在
	@RequestMapping("/queryExists/{submeeId}/{attachType}")
	@ResponseBody
	public ObjectNode queryExists(@PathVariable("submeeId") int submeeId, @PathVariable("attachType") String attachType, ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		MeetingAttach meetingAttach = new MeetingAttach();
		meetingAttach.setHallId(meetingInfo.getHallId());
		meetingAttach.setParmeeId(meetingInfo.getMeeId());
		meetingAttach.setSubmeeId(submeeId);
		meetingAttach.setAttachType(attachType);
		PageInfo<MeetingAttach> pageInfo = meetingAttachReadFacade.queryPageListByWhere(1, 20, meetingAttach);
		if (null != pageInfo && pageInfo.getSize() > 0) {
			node.put("errcode", "1");
			node.put("errmsg", "已经存在");
		} else {
			node.put("errcode", "0");
			node.put("errmsg", "ok");
		}
		return node;
	}

	@RequestMapping("/queryById/{attachId}")
	public ModelAndView queryPageListByWhere(@PathVariable("attachId") int attachId, ModelAndView modelAndView) {
		MeetingAttach attach = meetingAttachReadFacade.queryById(attachId);
		modelAndView.addObject("material", attach);
		modelAndView.setViewName(""); // 这里还没有填写
		return modelAndView;
	}

	@RequestMapping("/del/{attachId}")
	@ResponseBody
	public ObjectNode delLogic(@PathVariable("attachId") int attachId, ModelAndView modelAndView, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		UserWeixinInfo weixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
		MeetingAttach meetingAttach = new MeetingAttach();
		meetingAttach.setAttachId(attachId);
		meetingAttach.setUpdateUser(weixinInfo.getUserId());
		Integer deleteLogicById = meetingAttachWriteFacade.deleteLogicById(meetingAttach);
		if (deleteLogicById == 0) {
			node.put("errcode", 1);
			node.put("errmsg", "删除失败");
			return node;
		}
		node.put("errcode", 0);
		node.put("errmsg", "ok");
		return node;
	}

	// 跳转会议议程导读上传页面
	@RequestMapping("/toUploadPage/{attachType}")
	public ModelAndView toUploadPage(@PathVariable("attachType") String attachType, ModelAndView modelAndView, HttpServletRequest request) {
		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		modelAndView.addObject("meeId", meetingInfo.getMeeId());
		if ("1".equals(attachType)) {
			modelAndView.setViewName("meet-func/upload-meetData"); // 会议导读
		}
		if ("0".equals(attachType)) {
			modelAndView.setViewName("meet-func/upload-meetAgenda"); // 会议议程
		}
		if ("2".equals(attachType)) {
			modelAndView.setViewName("meet-func/upload-meetMaterial"); // 会议资料
		}
		return modelAndView;
	}

	/* 会议资料的大文件上传 */
	@RequestMapping("/materialUpload")
	@ResponseBody
	public ObjectNode upload(@RequestParam("file") CommonsMultipartFile file, MeetingAttach meetingAttach, String fileMd5, String chunk, String chunks, HttpServletRequest request) {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		// 获取上传文件的路径
		String realPath = request.getSession().getServletContext().getRealPath("/upload");

		try {
			long startTime = System.currentTimeMillis();

			if (!judgeFileFormat(file)) {
				node.put("errcode", "1");
				node.put("errmsg", "不支持的文件格式");
				return node;
			}

			if ("1".equals(chunks) && "0".equals(chunk)) {
				// 文件只有一片，直接作转换处理
				String originalFilename = file.getOriginalFilename();

				String suffix = StringUtils.substring(originalFilename, originalFilename.lastIndexOf(".") + 1);
				meetingAttach.setAttachSuffix(suffix);

				DiskFileItem filetemp = (DiskFileItem) file.getFileItem();
				File file_material = filetemp.getStoreLocation();

				String tempAbsolutePath = file_material.getAbsolutePath();
				String filename = ObjectId.get().toString() + ".html"; // 转换后文件名
				String filepath = ""; // 转换后文件绝对路径
				if ("pdf".equalsIgnoreCase(suffix)) {
					// windows转换
					// Pdf2htmlEXUtil
					// .pdf2html(
					// "E:/workspace/dubbo/pdf/pdf2htmlEX/pdf2htmlEX.exe",
					// file_material.getAbsolutePath(), realPath,
					// filename);
					// linux代码
					Pdf2htmlEXUtil.pdf2html_linux(file_material.getAbsolutePath(), realPath, filename);
					filepath = realPath + "/" + filename;
				} else if ("doc".equalsIgnoreCase(suffix)) {
					filepath = WordToHtml.word2003ToHtml(file_material.getName(), filename, realPath, file_material.getParent() + "/");
				} else if ("docx".equalsIgnoreCase(suffix)) {
					filepath = WordToHtml.word2007ToHtml(file_material.getName(), filename, realPath, file_material.getParent() + "/");
				} else {
					// 如果是图片直接上传到文件服务器
					filepath = tempAbsolutePath;
					filename = ObjectId.get().toString() + "." + suffix;
				}
				// 上传处理过后的文件到文件服务器
				File htmlFile = new File(filepath);
				String fileUrl = FastDFSClient.uploadFile(htmlFile, filename);
				meetingAttach.setAttachUrl(fileUrl);
				if (StringUtils.isBlank(fileUrl)) {
					logger.error("上传文件服务器失败！");
					node.put("errcode", "1");
					node.put("errmsg", "上传文件服务器失败！");
					return node;
				}
				// 原文件删除
				file_material.delete();
				htmlFile.delete();

				UserWeixinInfo weixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
				// 创建人id
				meetingAttach.setCreateUser(weixinInfo.getUserId());
				meetingAttach.setSubmeeId(0);
				meetingAttach.setCreateTime(System.currentTimeMillis());
				meetingAttach.setAttachIsDown("0");// 小文件，直接打开
				meetingAttachWriteFacade.saveSelective(meetingAttach);

				long endTime = System.currentTimeMillis();
				logger.debug("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
				node.put("errcode", "0");
				node.put("errmsg", "文件上传成功");
				node.put("path", fileUrl);
				return node;
			}

			// 大文件，分片储存
			String path = realPath + File.separator + fileMd5;
			File tmpFile = new File(path);
			if (!tmpFile.exists()) {
				// 创建临时文件夹，存储分片文件
				tmpFile.mkdir();
			}

			File chunkFile = new File(path + File.separator + chunk);
			FileUtils.copyInputStreamToFile(file.getInputStream(), chunkFile);
		} catch (Exception e) {
			e.printStackTrace();
			node.put("errcode", 1);
			node.put("errmsg", e.getMessage());
			return node;
		}
		node.put("errcode", 0);
		node.put("errmsg", "ok,chunk=" + chunk);
		return node;
	}

	@RequestMapping("/check")
	@ResponseBody
	public ObjectNode check(String fileMd5, String chunk, String chunkSize, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		// 找到分块文件
		File checkFile = new File(realPath + File.separator + fileMd5 + File.separator + chunk);

		// 检查文件是否存在，且大小是否一致
		if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)) {
			// 上传过了
			node.put("errcode", 1);
			node.put("errmsg", "已经存在");
		} else {
			// 没有上传过
			node.put("errcode", 0);
			node.put("errmsg", "没有上传过");
		}
		return node;
	}

	/**
	 * @param fileMd5
	 * @param meetingAttach
	 * @param filename
	 * @param chunks
	 * @param request
	 * @return
	 */
	@RequestMapping("/mergeChunks")
	@ResponseBody
	public ObjectNode mergeChunks(String fileMd5, MeetingAttach meetingAttach, String filename, String chunks, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		if ("1".equals(chunks)) {
			// 已经在上传阶段处理过，直接返回结果
			node.put("errcode", 0);
			node.put("errmsg", "文件合并完成");
			return node;
		}

		try {
			// 大文件分片上传服务器处理
			String realPath = request.getSession().getServletContext().getRealPath("/upload") + File.separator + fileMd5;

			List<File> fileBlockList = BrokenFileUtil.getFileBlockList(realPath);

			String attachSuffix = filename.substring(filename.lastIndexOf(".") + 1);
			String fileId = FastDFSClient.mergeFiles(fileBlockList, attachSuffix);

			if (null != fileId) {
				node.put("errcode", 0);
				node.put("errmsg", "文件合并完成");

				UserWeixinInfo userWeixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
				String userId = userWeixinInfo.getUserId();

				meetingAttach.setAttachUrl(fileId);
				meetingAttach.setCreateUser(userId);
				meetingAttach.setAttachSuffix(attachSuffix);
				meetingAttach.setAttachIsDown("1");
				meetingAttachWriteFacade.saveSelective(meetingAttach);

				BrokenFileUtil.deleteDir(realPath);
			} else {
				node.put("errcode", 1);
				node.put("errmsg", "文件合并失败");
			}

		} catch (Exception e) {
			logger.error(e + "");
			node.put("errcode", 1);
			node.put("errmsg", "文件合并失败");
		}
		return node;
	}

	/* 跳转绑定其它URL页面 */
	@RequestMapping("/toBindUrlPage")
	public ModelAndView toBindUrlPage(ModelAndView modelAndView) {
		modelAndView.setViewName("meet-func/upload-bindurl");
		return modelAndView;
	}

	/* 会议资料绑定其它链接 */
	@RequestMapping("/bindurl")
	@ResponseBody
	public ObjectNode bindurl(@RequestBody MeetingAttach meetingAttach, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			UserWeixinInfo weixinInfo = (UserWeixinInfo) request.getSession().getAttribute("user");
			MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
			// 创建人id
			meetingAttach.setCreateUser(weixinInfo.getUserId());
			meetingAttach.setSubmeeId(0);
			meetingAttach.setParmeeId(meetingInfo.getMeeId());
			meetingAttach.setHallId(meetingInfo.getHallId());
			meetingAttach.setAttachSuffix("url");
			meetingAttach.setCreateTime(System.currentTimeMillis());
			meetingAttach.setAttachIsDown("0");// 小文件，直接打开
			meetingAttachWriteFacade.saveSelective(meetingAttach);
			node.put("errcode", "0");
			node.put("errmsg", "ok");
			return node;
		} catch (Exception e) {
			e.printStackTrace();
			node.put("errcode", 1);
			node.put("errmsg", e.getMessage());
			return node;
		}
	}

	/* 优酷视频播放 */
	@RequestMapping("/play/{attachId}")
	public ModelAndView bindurl_play(@PathVariable("attachId") int attachId, HttpServletRequest request, ModelAndView modelAndView) {
		MeetingAttach queryById = meetingAttachReadFacade.queryById(attachId);
		UserWeixinInfo selectByUserId = userWeixinInfoFacade.selectByUserId(queryById.getCreateUser());
		modelAndView.addObject("material", queryById);
		modelAndView.addObject("createUserName", selectByUserId.getRealName());
		modelAndView.setViewName("meet-func/meet-data-viedo");
		return modelAndView;
	}

	// 判断文件类型是否符合要求
	public boolean judgeFileFormat(CommonsMultipartFile file) {
		if (file.getSize() == 0) {
			return false;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename.indexOf(".") == -1) {
			return false;
		}
		String suffix = StringUtils.substring(originalFilename, originalFilename.lastIndexOf(".") + 1);
		return suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("pdf") || suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("bmp") || suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx");
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public JSONObject exceptionHandler(MaxUploadSizeExceededException ex, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
		response.setCharacterEncoding("utf-8");
		JSONObject jsonError = new JSONObject();
		long maxSize = ex.getMaxUploadSize();
		jsonError.put("errcode", "1");
		jsonError.put("errmsg", "上传文件大小不能超过5M,当前文件大小为:" + (maxSize / 1024 / 1024) + "M");
		return jsonError;
	}
}
