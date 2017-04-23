package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.exception.enums.ExceptionFacadeEnums;
import com.bizideal.whoami.mapper.ExceptionLogMapper;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.service.ExceptionLogService;
import com.bizideal.whoami.utils.ExceptionUtilsCls;

@Service("exceptionLogService")
public class ExceptionLogServiceImpl implements ExceptionLogService{

	@Autowired
	ExceptionLogMapper exceptionLogMapper;
	
	@Override
	public int insertExceptionLog(ExceptionLog exceptionLog) throws CustomException{
		int flag = 0 ;
		try {
			flag = exceptionLogMapper.insert(exceptionLog);
		} catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_insertExceptionLog_00_ERROR.getCode(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_insertExceptionLog_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != exceptionLog){
				params = exceptionLog.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return flag;
	}

	@Override
	public List<ExceptionLog> queryExceptionLogs(ExceptionLog exceptionLog) throws CustomException {	
		List<ExceptionLog> lists = null;
		try {
			lists = exceptionLogMapper.queryExceptionLogs(exceptionLog);
		} catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_queryExceptionLogs_00_ERROR.getCode(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_queryExceptionLogs_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != exceptionLog){
				params = exceptionLog.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return lists;
	}

	@Override
	public ExceptionLog queryExceptionLogById(long id) throws CustomException {
		ExceptionLog exceptionLog = null;
		try {
			exceptionLog = exceptionLogMapper.selectByPrimaryKey(id);
		} catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_queryExceptionLogById_00_ERROR.getCode(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_queryExceptionLogById_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			params = "id:"+id;
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return exceptionLog;
	}

	@Override
	public boolean deleteExceptionLogById(long id) throws CustomException {
		boolean flag = false;
		try {
			int num = exceptionLogMapper.deleteByPrimaryKey(id);
			if(num > 0){
				flag = true;
			}
		} catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogById_00_ERROR.getCode(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogById_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			params = "id:"+id;
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return flag;
	}

	@Override
	public boolean deleteExceptionLogs(ExceptionLog exceptionLog) throws CustomException {
		boolean flag = false;
		try {
			if(null == exceptionLog){
				//抛出自定义异常
				throw new Exception("{'code':'"+
			 	ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogs_01_ERROR.getCode()+"','msg':'"+
				ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogs_01_ERROR.getMsg()+"'}");
			}
			int num = exceptionLogMapper.delExceptionLogs(exceptionLog);
			if(num > 0){
				flag = true;
			}
		} catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogs_00_ERROR.getCode(),
					ExceptionFacadeEnums.ExceptionLogServiceImpl_delExceptionLogs_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != exceptionLog){
				params = exceptionLog.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return flag;
	}

}
