package com.bizideal.whoami.service;

import java.util.List;

import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.pojo.CustomException;
import com.github.pagehelper.PageInfo;

public interface ExceptionLogService {
	/**
	 * 插入异常
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	int insertExceptionLog(ExceptionLog exceptionLog) throws CustomException;
	/**
	 * 查询异常列表
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	List<ExceptionLog> queryExceptionLogs(ExceptionLog exceptionLog) throws CustomException ;
	/**
	 * 查询单个异常
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	ExceptionLog queryExceptionLogById(long id) throws CustomException ;
	/**
	 * 删除单个异常
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	boolean deleteExceptionLogById(long id) throws CustomException ;
	/**
	 * 批量删除异常
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	boolean deleteExceptionLogs(ExceptionLog exceptionLog) throws CustomException ;
}
