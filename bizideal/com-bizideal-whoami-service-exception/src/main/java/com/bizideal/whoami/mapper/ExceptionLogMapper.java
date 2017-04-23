package com.bizideal.whoami.mapper;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.pojo.CustomException;

public interface ExceptionLogMapper extends Mapper<ExceptionLog>{
	/**
	 * 查询异常列表
	 * @param exceptionLog
	 * @return
	 * @throws CustomException
	 */
	List<ExceptionLog> queryExceptionLogs(ExceptionLog exceptionLog) ;
	/**
	 * 根据条件删除一组异常
	 * @param exceptionLog
	 * @return
	 */
	int delExceptionLogs(ExceptionLog exceptionLog);
}