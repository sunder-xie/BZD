package com.bizideal.whoami.exception.facade;

import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

/**
 * 
 *
 * @author ct
 * 2017年3月13日
 *
 */
public interface ExceptionFacade {
	/**
	 * 插入异常处理
	 * @param serviceException
	 * @return
	 * @throws CustomException
	 */
	long insertServiceException(CustomException serviceException) throws CustomException;
	/**
	 * 异常列表
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	PageInfo<ExceptionLog> queryExceptionLogs(ExceptionLog exceptionLog) throws CustomException;
	/**
	 * 单个异常
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	ExceptionLog queryExceptionLogById(long id) throws CustomException;
	/**
	 * 单个删除异常
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	boolean deleteExceptionLogById(long id) throws CustomException;
	/**
	 * 批量删除异常
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	boolean deleteExceptionLogs(ExceptionLog exceptionLog) throws CustomException;
	
}