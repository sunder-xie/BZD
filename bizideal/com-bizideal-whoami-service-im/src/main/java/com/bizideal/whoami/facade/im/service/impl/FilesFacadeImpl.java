package com.bizideal.whoami.facade.im.service.impl;

import java.io.File;

import org.springframework.stereotype.Component;

import com.bizideal.whoami.im.facade.FilesFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * @ClassName FilesFacadeImpl
 * @Description (聊天文件上传)
 * @Author Zj.Qu
 * @Date 2017-01-05 14:08:15
 */
@Component("filesFacade")
public class FilesFacadeImpl implements FilesFacade {

	@Override
	public String mediaUpload(File upload) throws CustomException{
		return null;
	}

	@Override
	public String mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) throws CustomException{
		return null;
	}

}
