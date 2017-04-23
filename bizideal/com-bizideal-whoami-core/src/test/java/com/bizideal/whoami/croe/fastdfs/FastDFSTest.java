package com.bizideal.whoami.croe.fastdfs;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;

/**
 * 
 * @描述: FastDFS测试 .
 * @作者: WuShuicheng .
 * @创建时间: 2015-3-29,下午8:11:36 .
 * @版本号: V1.0 .
 */
public class FastDFSTest {
	
	/**
	 * 上传测试.
	 * @throws Exception
	 */
	public static void upload() throws Exception {
		String filePath = "E:/个人会员导入模板.xlsx";
		File file = new File(filePath);
		String fileId = FastDFSClient.uploadFile(file, filePath);
		System.out.println("Upload local file " + filePath + " ok, fileid=" + fileId);
		// fileId:	group1/M00/00/00/wKgBz1g4OraAc3jXAAP3btxj__E890.jpg
		// url:	http://192.168.1.207:8888/group1/M00/00/00/wKgBz1g4OraAc3jXAAP3btxj__E890.jpg
	}
	
	/**
	 * 下载测试.
	 * @throws Exception
	 */
	public static void download() throws Exception {
		String fileId = "group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg";
		InputStream inputStream = FastDFSClient.downloadFile(fileId);
		File destFile = new File("E:/WorkSpaceSpr10.6/edu-demo-fdfs/TestFile/DownloadTest.jpg");
		FileUtils.copyInputStreamToFile(inputStream, destFile);
	}

	/**
	 * 删除测试
	 * @throws Exception
	 */
	public static void delete() throws Exception {
		String fileId = "group1/M00/00/00/wKgBz1g4OraAc3jXAAP3btxj__E890.jpg";
		int result = FastDFSClient.deleteFile(fileId);
		System.out.println(result == 0 ? "删除成功" : "删除失败:" + result);
	}


	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	        
		upload();
		//download();
		//delete();

	}

}
