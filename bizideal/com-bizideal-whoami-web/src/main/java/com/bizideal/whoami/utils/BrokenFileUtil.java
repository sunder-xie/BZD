package com.bizideal.whoami.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Resume broken transfer
 * @ClassName BrokenFileUtil
 * @Description TODO(断点续传相关的文件操作类)
 * @Author Zj.Qu
 * @Date 2016-12-23 15:07:53
 */
public class BrokenFileUtil {

	/**
	 * 获取分片列表
	 * @param fileDirPath 文件目录
	 * @return List<File> 排序后的文件列表
	 */
	public static List<File> getFileBlockList(String fileDirPath) {
		
		File f = new File(fileDirPath);

		File[] fileArray = f.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		});

		List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));

		Collections.sort(fileList, new Comparator<File>() {
			public int compare(File o1, File o2) {
				if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
					return -1;
				}
				return 1;
			}
		});

		return fileList;
	}

	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param fileDirPath 将要删除的文件目录地址
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(String fileDirPath) {
    	File fileDir = new File(fileDirPath);
        if (fileDir.isDirectory()&& fileDir.exists()) {
            String[] children = fileDir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(fileDirPath + File.separator + children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        return fileDir.delete();
    }
    
}
