package com.bizideal.whoami.core.im.biz;

import java.io.File;

import org.codehaus.jackson.node.ObjectNode;

import com.bizideal.whoami.pojo.CustomException;

/**
 * 
 * @ClassName FilesService
 * @Description (文件上传接口)
 * @Author yt.Cui
 * @Date 2017年1月5日
 */
public interface FilesService {

	/**
	 * 图片/语音文件上传
	 * @param uploadFile
	 */
	ObjectNode mediaUpload(File uploadFile) throws CustomException;

	/**
	 * 图片语音文件下载
	 * @param fileUUID 文件在DB的UUID
	 * @param shareSecret 文件在DB中保存的shareSecret
	 * @param localPath 下载后文件存放地址
	 * @param isThumbnail 是否下载缩略图 true:缩略图 ,false:非缩略图
	 * @return
	 */
	ObjectNode mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) throws CustomException;

}