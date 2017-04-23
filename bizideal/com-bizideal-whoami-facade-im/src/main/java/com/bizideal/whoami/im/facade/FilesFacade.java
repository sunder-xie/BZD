package com.bizideal.whoami.im.facade;

import java.io.File;

import com.bizideal.whoami.pojo.CustomException;


/**
 * @ClassName FilesFacade
 * @Description (文件上传接口)
 * @Author Zj.Qu
 * @Date 2017-01-05 10:12:17
 */
public interface FilesFacade {

	/**
	 * 图片/语音文件上传
	 * @param uploadFile
	 */
	String mediaUpload(File uploadFile) throws CustomException;

	/**
	 * 图片语音文件下载
	 * @param fileUUID 文件在DB的UUID
	 * @param shareSecret 文件在DB中保存的shareSecret
	 * @param localPath 下载后文件存放地址
	 * @param isThumbnail 是否下载缩略图 true:缩略图 ,false:非缩略图
	 * @return
	 */
	String mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) throws CustomException;

}