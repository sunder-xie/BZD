package com.bizideal.whoami.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.exception.facade.ExceptionFacade;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.service.ExceptionLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
 * 
 * @ClassName ExceptionFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年3月8日
 */
@Component("exceptionFacade")
public class ExceptionFacadeImpl implements ExceptionFacade{
	@Autowired
	ExceptionLogService exceptionLogService;

	@Override
	public long insertServiceException(CustomException customException) throws CustomException{
		long returnParam = 0L;
		
		ExceptionLog exceptionLog=new ExceptionLog();
		exceptionLog.setControllerClassName(customException.getControllerClassName());
		exceptionLog.setControllerMethodName(customException.getControllerMethodName());
		exceptionLog.setServiceClassName(customException.getServiceClassName());
		exceptionLog.setServiceMethodName(customException.getServiceMethodName());
		exceptionLog.setResendUrl(customException.getResendUrl());
		exceptionLog.setParams(customException.getParams());
		exceptionLog.setCode(customException.getCode());
		exceptionLog.setMsg(customException.getMsg());
		exceptionLog.setRemark(customException.getRemark());
		exceptionLog.setCreateTime(new Date());
		if(exceptionLogService.insertExceptionLog(exceptionLog)==1){
			returnParam = exceptionLog.getId();
		}
		return returnParam;
	}

	@Override
	public PageInfo<ExceptionLog> queryExceptionLogs(ExceptionLog exceptionLog) throws CustomException {
		// TODO Auto-generated method stub
		PageInfo<ExceptionLog> pageInfos = null;
		PageHelper.startPage(exceptionLog.getPageNum(), exceptionLog.getPageSize());
		PageHelper.startPage(exceptionLog.getPageNum(),exceptionLog.getPageSize());		
		List<ExceptionLog> list = exceptionLogService.queryExceptionLogs(exceptionLog);
		pageInfos = new PageInfo<ExceptionLog>(list);
		return pageInfos;
	}

	@Override
	public ExceptionLog queryExceptionLogById(long id) throws CustomException {
		ExceptionLog exceptionLog = null;
		exceptionLog = exceptionLogService.queryExceptionLogById(id);
		return exceptionLog;
	}

	@Override
	public boolean deleteExceptionLogById(long id) throws CustomException {
		boolean flag = false;
		flag = exceptionLogService.deleteExceptionLogById(id);
		return flag;
	}

	@Override
	public boolean deleteExceptionLogs(ExceptionLog exceptionLog) throws CustomException {
		boolean flag = false;
		flag = exceptionLogService.deleteExceptionLogs(exceptionLog);
		return flag;
	}


}
