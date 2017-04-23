package com.bizideal.whoami.facade.im.service.rest.impl;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.core.im.biz.FilesService;
import com.bizideal.whoami.im.facade.rest.FilesRestFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName FilesRestFacadeImpl
 * @Description (聊天文件上传)
 * @Author Zj.Qu
 * @Date 2017-01-05 14:08:15
 */
@Path("/chatfiles")
@Component("filesRestFacade")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class FilesRestFacadeImpl implements FilesRestFacade{

	private Logger LOGGER = LoggerFactory.getLogger(FilesRestFacadeImpl.class);

	@Autowired
	private FilesService filesService;

//	@POST
//	@Path("mediaUpload")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response mediaUpload(
//			@FormDataParam("file") InputStream inputStream,
//			@FormDataParam("file") FormDataContentDisposition info) {
//
//		File upload = new File(info.getFileName());
//
//		if (upload.exists()) {
//			String message = "file: " + upload.getName() + " already exists";
//			LOGGER.error(String.format("文件:<< %s >>已经存在!",upload.getName()));
//			return Response.status(Status.CONFLICT).entity(message).build();
//		} else {
//
//			ObjectNode imgDataNode = null;
//
//			try {
//				
//				Files.copy(inputStream, upload.toPath());
//				imgDataNode = filesService.mediaUpload(upload);
//				
//			} catch (IOException e) {
//				LOGGER.error("上传文件失败", e);
//				e.printStackTrace();
//			} finally {
//				upload.delete();
//			}
//
//			if (null != imgDataNode) {
//				LOGGER.info("上传图片文件: " + imgDataNode.toString());
//			}
//			return Response.status(Status.OK).entity(imgDataNode.toString()).build();
//		}
//	}
	

	@GET
	@Path("mediaDownload")
	@Produces(MediaType.APPLICATION_JSON)
	public String mediaDownload() throws CustomException{
		File uploadImgFile = new File("/tmp/24849.jpg");
		ObjectNode imgDataNode = filesService.mediaUpload(uploadImgFile);
		String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid").asText();
		String shareSecret = imgDataNode.path("entities").get(0).path("share-secret").asText();
		File downloadedImgFileLocalPath = new File(uploadImgFile.getPath().substring(0, uploadImgFile.getPath().lastIndexOf("."))
				+ "-1.jpg");
		boolean isThumbnail = false;
		ObjectNode downloadImgDataNode = filesService.mediaDownload(
				imgFileUUID, shareSecret, downloadedImgFileLocalPath,isThumbnail
			);
		if (null != downloadImgDataNode) {
			LOGGER.info("下载图片文件: " + downloadImgDataNode.toString());
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadImgDataNode.toString();
	}


	@Override
	public String mediaUpload(File uploadFile) throws CustomException{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) throws CustomException{
		// TODO Auto-generated method stub
		return null;
	}

}
