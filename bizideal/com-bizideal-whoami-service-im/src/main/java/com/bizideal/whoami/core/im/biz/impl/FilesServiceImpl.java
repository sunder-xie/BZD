package com.bizideal.whoami.core.im.biz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.im.biz.FilesService;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;
import com.bizideal.whoami.core.im.vo.EndPoints;
import com.bizideal.whoami.im.enums.ImFacadeEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

/**
 * 
 * @ClassName FilesServiceImpl
 * @Description (图片语音文件上传)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
@Service("filesService")
public class FilesServiceImpl extends BaseServiceImpl implements FilesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FilesServiceImpl.class);

	@Override
	public ObjectNode mediaUpload(File uploadFile) throws CustomException{

		try{
	
			ObjectNode objectNode = factory.objectNode();
	
			if (!uploadFile.exists()) {
	
				LOGGER.error("file: " + uploadFile.toString() + " is not exist!");
	
				objectNode.put("message", "File or directory not found");
	
				return objectNode;
			}
	
	
			try {
				ResteasyWebTarget webTarget = EndPoints.CHATFILES_TARGET;
	
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("restrict-access", "true"));
				objectNode = ResteasyUtils.uploadFile(webTarget, uploadFile, credential, headers);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.FilesServiceImpl_mediaUpload_00_ERROR.getCode(),
					ImFacadeEnums.FilesServiceImpl_mediaUpload_00_ERROR.getMsg());
			String params="";
			params=uploadFile+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}


	@Override
	public ObjectNode mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) {

		try{
			ObjectNode objectNode = factory.objectNode();
			File downLoadedFile = null;
	
			try {
				ResteasyWebTarget webTarget = EndPoints.CHATFILES_TARGET.path(fileUUID);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("share-secret", shareSecret));
				headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
				if (isThumbnail != null && isThumbnail) {
					headers.add(new BasicNameValuePair("thumbnail", String.valueOf(isThumbnail)));
				}
				downLoadedFile = ResteasyUtils.downLoadFile(webTarget, credential, headers, localPath);
	            LOGGER.error("File download successfully，file path : " + downLoadedFile.getAbsolutePath() + ".");
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			objectNode.put("message", "File download successfully .");
			return objectNode;
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ImFacadeEnums.FilesServiceImpl_mediaDownload_00_ERROR.getCode(),
					ImFacadeEnums.FilesServiceImpl_mediaDownload_00_ERROR.getMsg());
			String params="";
			params="fileUUID:"+fileUUID+","+"shareSecret:"+shareSecret+","+localPath+","+"isThumbnail:"+isThumbnail+"";
			ExceptionUtilsCls.putCustomException(customException, e , params);
			throw customException;
		}
	}

}
