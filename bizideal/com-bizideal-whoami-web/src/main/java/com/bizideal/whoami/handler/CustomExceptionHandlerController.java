package com.bizideal.whoami.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bizideal.whoami.entity.BaseResData;
import com.bizideal.whoami.exception.facade.ExceptionFacade;
import com.bizideal.whoami.pojo.CustomException;

/**
 * 异常处理handler
 * @ClassName CustomExceptionHandlerController
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年3月13日
 */
@Controller
public class CustomExceptionHandlerController {

	private Logger logger = LoggerFactory.getLogger(CustomExceptionHandlerController.class);
	
	@Autowired
	private ExceptionFacade exceptionFacade;
	
	@ExceptionHandler(CustomException.class)
    public void operateException(CustomException ex,HttpServletRequest request,HttpServletResponse response,HandlerMethod handler){  
		HandlerMethod handlerMethod = handler;
    	try {
    		ex.setControllerClassName(handlerMethod.getBeanType().toString());
    		ex.setControllerMethodName(handlerMethod.getMethod().getName());
    		if(null==ex.getResendUrl() || ex.getResendUrl().equals("")){
    			ex.setResendUrl("/page/exception/error.html");
    		}
    		if(null==ex.getCode() || ex.getCode().equals("")){
    			ex.setCode("404");
    		}
    		if(null==ex.getMsg() || ex.getMsg().equals("")){
    			ex.setMsg("未知异常");
    		}
//    		插入数据库异常信息
			exceptionFacade.insertServiceException(ex);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.debug("数据库插入记录错误信息出错",e1);
		}finally{
			dealException(handlerMethod, ex, response, request);
		}
    }
	
	@ExceptionHandler(Exception.class)
    public void operateException(Exception ex,HttpServletRequest request,HttpServletResponse response,HandlerMethod handler){  
		HandlerMethod handlerMethod = handler;
		CustomException customException = new CustomException();
    	try {
    		customException.setControllerClassName(handlerMethod.getBeanType().toString());
    		customException.setControllerMethodName(handlerMethod.getMethod().getName());
    		customException.setResendUrl("/page/exception/error.html");
    		customException.setCode("404");
    		customException.setMsg("未知异常");
    		ex.printStackTrace();
//    	插入数据库异常信息
			exceptionFacade.insertServiceException(customException);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.debug("数据库插入记录错误信息出错");
		}finally{
			dealException(handlerMethod, customException, response, request);
		}
	}
	public void dealException(HandlerMethod handlerMethod,CustomException customException,HttpServletResponse response,HttpServletRequest request){
		Class c=handlerMethod.getMethod().getReturnType();
    	try {
//    		如果不是返回页面的直接返回一个json数据
    		if(!c.equals(ModelAndView.class) && !c.equals(String.class)){
//    			String jsonStr="{\"code\":\""+ex.getCode()+"\",\"msg\":\""+ex.getMsg()+"\"}";
    			
    			//修改为fastjson 返回 
    			BaseResData<Object> baseResData = new BaseResData<Object>();
    			baseResData.setStatus("0");
    			baseResData.setCode(customException.getCode());
    			String[] split = customException.getMsg().split(",");
    			if(null != split){
	    			int count = split.length;
	    			baseResData.setMsg(split[count-1]);
    			}
    			String jsonString = JSON.toJSONString(baseResData,false);
				response.setCharacterEncoding("UTF-8");
				
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().println(jsonString);
				response.getWriter().flush();
	    	}else{
//	    		否则跳转到一个错误页面
	    		request.getRequestDispatcher(customException.getResendUrl()).forward(request, response);
	    	}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}