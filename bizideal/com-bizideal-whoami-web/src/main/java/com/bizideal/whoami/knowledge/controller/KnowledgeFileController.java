package com.bizideal.whoami.knowledge.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.facade.meeting.entity.MeetingInfo;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;
import com.bizideal.whoami.knowledgebase.entity.KnowledgeFile;
import com.bizideal.whoami.knowledgebase.facade.KnowledgeFileFacade;
import com.bizideal.whoami.meeting.controller.MeetingMaterialController;
import com.bizideal.whoami.user.entity.UserWeixinInfo;
import com.bizideal.whoami.user.facade.UserWeixinInfoFacade;
import com.bizideal.whoami.utils.BrokenFileUtil;
import com.bizideal.whoami.utils.ObjectId;
import com.bizideal.whoami.utils.Pdf2htmlEXUtil;
import com.bizideal.whoami.utils.WordToHtml;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;

/**
 * 知识库资料
 * 
 * @author sy
 * @date 2017-3-14 14:16:37
 */
@Controller
@RequestMapping("/knowledgeFile")
public class KnowledgeFileController {

	@Autowired
	private KnowledgeFileFacade knowledgeFileFacade;

	@Autowired
	private UserWeixinInfoFacade userWeixinInfoFacade;

	@Value("${fastdfsurl}")
	private String fastdfsurl;

	private Logger logger = LoggerFactory.getLogger(MeetingMaterialController.class);

	/**
	 * 管理文件列表
	 * 
	 * @param request
	 * @param model
	 * @param parentId父分类id
	 * @return
	 */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request, Model model, Integer parentId) {

		MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

		PageInfo<KnowledgeFile> selectFilesByTypeId = knowledgeFileFacade.selectFilesByTypeId(user.getUserId(),
				parentId, 1, 1000);
		model.addAttribute("fileList", selectFilesByTypeId);
		model.addAttribute("parentId", parentId);
		model.addAttribute("fastdfsurl", fastdfsurl);
		return "/admin-repository/admin-repository-dataList";
	}

	/**
	 * 跳转添加文件页面
	 * 
	 * @param request
	 * @param model
	 * @param parentId父分类id
	 * @param type文件还是链接
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAddFile(HttpServletRequest request, Model model, Integer parentId, Integer type) {

		String url;
		model.addAttribute("parentId", parentId);
		if (type == 0) {
			// 添加文件页面
			url = "/admin-repository/repository-uploadData";
		} else {
			// 绑定链接页面
			url = "/admin-repository/repository-bandLink";
		}
		return url;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteType(HttpServletRequest request, Model model, Integer id) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

		return knowledgeFileFacade.delete(id, user.getUserId());
	}

	/**
	 * 查看文件列表
	 * 
	 * @param request
	 * @param model
	 * @param parentId父分类id
	 * @return
	 */
	@RequestMapping("/list")
	public String fileList(HttpServletRequest request, Model model, Integer parentId) {

		UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

		PageInfo<KnowledgeFile> selectFilesByTypeId = knowledgeFileFacade.selectFilesByTypeId(user.getUserId(),
				parentId, 1, 1000);
		model.addAttribute("fileList", selectFilesByTypeId);
		model.addAttribute("parentId", parentId);
		model.addAttribute("fastdfsurl", fastdfsurl);
		return "/repository/repository-data-list";
	}

	/**
	 * 上传文件分片
	 * 
	 * @param fileMd5
	 * @param chunk
	 * @param chunkSize
	 * @param request
	 * @return
	 */
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

	@RequestMapping("/mergeChunks")
	@ResponseBody
	public ObjectNode mergeChunks(String fileMd5, KnowledgeFile knowledgeFile, String filename, String chunks,
			HttpServletRequest request) {
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
			String realPath = request.getSession().getServletContext().getRealPath("/upload") + File.separator
					+ fileMd5;

			List<File> fileBlockList = BrokenFileUtil.getFileBlockList(realPath);

			String attachSuffix = filename.substring(filename.lastIndexOf(".") + 1);
			String fileId = FastDFSClient.mergeFiles(fileBlockList, attachSuffix);

			if (null != fileId) {
				node.put("errcode", 0);
				node.put("errmsg", "文件合并完成");

				MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
				UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

				knowledgeFile.setUrl(fileId);
				knowledgeFile.setSuffix(attachSuffix);
				knowledgeFile.setMeeId(meetingInfo.getMeeId());
				knowledgeFile.setCreateUser(user.getUserId());
				knowledgeFile.setCreateTime(System.currentTimeMillis());
				knowledgeFile.setIsDown("1");
				knowledgeFileFacade.saveFileOrUrl(knowledgeFile);

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

	/* 会议资料的大文件上传 */
	@RequestMapping("/materialUpload")
	@ResponseBody
	public ObjectNode upload(@RequestParam("file") CommonsMultipartFile file, Integer typeId, String name,
			String fileMd5, String chunk, String chunks, HttpServletRequest request) {

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
				KnowledgeFile knowledgeFile = new KnowledgeFile();
				knowledgeFile.setTypeId(typeId);
				knowledgeFile.setName(name);
				knowledgeFile.setSuffix(suffix);

				DiskFileItem filetemp = (DiskFileItem) file.getFileItem();
				File file_material = filetemp.getStoreLocation();
				String uploadFile = FastDFSClient.uploadFile(file_material, originalFilename);

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
					filepath = WordToHtml.word2003ToHtml(file_material.getName(), filename, realPath,
							file_material.getParent() + "/");
				} else if ("docx".equalsIgnoreCase(suffix)) {
					filepath = WordToHtml.word2007ToHtml(file_material.getName(), filename, realPath,
							file_material.getParent() + "/");
				} else {
					// 如果是图片直接上传到文件服务器
					filepath = tempAbsolutePath;
					filename = ObjectId.get().toString() + "." + suffix;
				}
				// 上传处理过后的文件到文件服务器
				File htmlFile = new File(filepath);
				String fileUrl = FastDFSClient.uploadFile(htmlFile, filename);
				knowledgeFile.setUrl(fileUrl);
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
				UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");

				knowledgeFile.setMeeId(meetingInfo.getMeeId());
				knowledgeFile.setCreateUser(user.getUserId());
				knowledgeFile.setCreateTime(System.currentTimeMillis());
				knowledgeFile.setIsDown(uploadFile);
				knowledgeFileFacade.saveFileOrUrl(knowledgeFile);

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
		return suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("pdf")
				|| suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("bmp") || suffix.equalsIgnoreCase("gif")
				|| suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx");
	}

	/* 会议资料绑定其它链接 */
	@RequestMapping("/bindurl")
	@ResponseBody
	public ObjectNode bindurl(@RequestBody KnowledgeFile knowledgeFile, HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		try {
			UserWeixinInfo user = (UserWeixinInfo) request.getSession().getAttribute("user");
			MeetingInfo meetingInfo = (MeetingInfo) request.getSession().getAttribute("click_meeting");
			// 创建人id
			knowledgeFile.setSuffix("url");
			knowledgeFile.setMeeId(meetingInfo.getMeeId());
			knowledgeFile.setCreateUser(user.getUserId());
			knowledgeFile.setCreateTime(System.currentTimeMillis());
			knowledgeFile.setIsDown("0");
			knowledgeFileFacade.saveFileOrUrl(knowledgeFile);
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
	@RequestMapping("/play/{fileId}")
	public String bindurl_play(@PathVariable("fileId") int fileId, HttpServletRequest request, Model model) {
		KnowledgeFile knowledgeFile = knowledgeFileFacade.selectById(fileId);
		UserWeixinInfo selectByUserId = userWeixinInfoFacade.selectByUserId(knowledgeFile.getCreateUser());
		// 封装成会议资料实体类
		MeetingAttach queryById = new MeetingAttach();
		queryById.setAttachName(knowledgeFile.getName());
		queryById.setCreateTime(knowledgeFile.getCreateTime());
		queryById.setAttachUrl(knowledgeFile.getUrl());
		queryById.setRemarks(knowledgeFile.getDsp());
		model.addAttribute("material", queryById);
		model.addAttribute("createUserName", selectByUserId.getRealName());
		// 使用会议资料的播放视频链接页面
		return "meet-func/meet-data-viedo";
	}

}
