package com.bizideal.whoami.signup.facade;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.signup.entity.DietInfo;
import com.bizideal.whoami.signup.service.DietInfoService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2016年12月2日 上午11:08:39
 * @version 1.0
 */
@Path("/diet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
@Component("dietInfoRestFacade")
public class DietInfoRestFacadeImpl implements DietInfoRestFacade {

	private Logger logger = Logger.getLogger(DietInfoRestFacadeImpl.class);
	@Autowired
	private DietInfoService dietInfoService;

	@POST
	@Path("/getSelfByPage")
	@Override
	public JSONObject selectByPageSelf(DietInfo dietInfo) {
		JSONObject selectByPageSelf = dietInfoService
				.selectByPageSelf(dietInfo);
		return selectByPageSelf;
	}

	@POST
	@Path("/getByPage")
	@Override
	public JSONObject selectByPage(DietInfo dietInfo) {
		return dietInfoService.selectByPage(dietInfo);
	}

	@POST
	@Path("/update")
	@Override
	public JSONObject updateDiet(DietInfo dietInfo) {
		return dietInfoService.updateDiet(dietInfo);
	}

	@POST
	@Path("/delete")
	@Override
	public JSONObject deleteDiet(DietInfo dietInfo) {
		return dietInfoService.deleteDiet(dietInfo);
	}

	@POST
	@Path("/set")
	@Override
	public JSONObject insertDiet(DietInfo dietInfo) {
		return dietInfoService.insertDiet(dietInfo);
	}

	@ExceptionHandler(Exception.class)
	public JSONObject handlerException(Exception e) {
		JSONObject json = new JSONObject();
		json.put("errcode", 1);
		json.put("errmsg", e.getMessage());
		logger.error(e.getMessage());
		e.printStackTrace();
		return json;
	}
}
