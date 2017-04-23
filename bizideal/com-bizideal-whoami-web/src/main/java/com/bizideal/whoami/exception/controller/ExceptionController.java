package com.bizideal.whoami.exception.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bizideal.whoami.entity.BaseResData;
import com.bizideal.whoami.exception.entity.ExceptionLog;
import com.bizideal.whoami.exception.facade.ExceptionFacade;
import com.bizideal.whoami.handler.CustomExceptionHandlerController;
import com.github.pagehelper.PageInfo;

/**
 * 
 *
 * @author ct 2017年3月15日
 *
 */
@Controller("exception")
@RequestMapping("/exception")
public class ExceptionController extends CustomExceptionHandlerController{

	@Autowired
	private ExceptionFacade exceptionFacade;	

	/**
	 * 查询异常日志列表
	 * @param exceptionLog
	 * @return
	 */
	@RequestMapping("/lists")
	@ResponseBody
	public BaseResData<PageInfo<ExceptionLog>> queryExceptionLogs(@RequestBody ExceptionLog exceptionLog) {
		BaseResData<PageInfo<ExceptionLog>> baseResData = new BaseResData<PageInfo<ExceptionLog>>();
		baseResData.setStatus("0");
		PageInfo<ExceptionLog> lists = exceptionFacade.queryExceptionLogs(exceptionLog);
		if(null != lists && null != lists.getList()){
			baseResData.setStatus("1");
			baseResData.setResdata(lists);
		}
		return baseResData;
	}	
	
	/**
	 * 删除异常日志
	 * @param exceptionLog
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public BaseResData<Boolean> delExceptionLogs(@RequestBody ExceptionLog exceptionLog) {
		BaseResData<Boolean> BaseResData = new BaseResData<Boolean>();
		BaseResData.setStatus("0");
		boolean flag = exceptionFacade.deleteExceptionLogs(exceptionLog);
		if(flag){
			BaseResData.setStatus("1");
			BaseResData.setResdata(true);
		}
		return BaseResData;
	}	

	/**
	 * 查询单个异常日志
	 * @param exceptionLog
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public String queryExceptionLogById(@PathVariable("id") long id , ModelMap model) {
		ExceptionLog exceptionLog  = exceptionFacade.queryExceptionLogById(id);
		model.addAttribute("exceptionLog",exceptionLog);
		return "exception/exception-detail";
	}	
	
	/**
	 * 根据异常日志类的时间段、控制层类名、控制层方法名、服务层类名、服务层方法名
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView toExceptionIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("exception/exception-index");
		return modelAndView;
	}
}
