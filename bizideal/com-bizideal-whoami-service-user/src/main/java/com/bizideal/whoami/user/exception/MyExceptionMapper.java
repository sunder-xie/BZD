package com.bizideal.whoami.user.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import com.bizideal.whoami.user.enums.UserEnum;

import net.sf.json.JSONObject;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月3日 下午12:44:57
 * @version 1.0 模块级别的统一异常处理
 */
public class MyExceptionMapper implements ExceptionMapper<Exception> {
	private Logger logger = Logger.getLogger(MyExceptionMapper.class);

	@Override
	public Response toResponse(Exception e) {
		JSONObject json = new JSONObject();
		json.put("errcode", UserEnum.USER_EXCEPTION.getErrcode());
		json.put("errmsg", UserEnum.USER_EXCEPTION.getErrmsg()
				+ ",异常类型:" + e.getMessage());
		logger.error(e.getMessage());
		return Response.status(Response.Status.OK).entity(json)
				.type("application/json").build();
	}

}
