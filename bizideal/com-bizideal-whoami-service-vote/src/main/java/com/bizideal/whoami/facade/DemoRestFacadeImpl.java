package com.bizideal.whoami.facade;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.bizideal.whoami.croe.RedisClientTemplate;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.service.DemoService;
import com.bizideal.whoami.utils.ProtostuffConvert;
import com.bizideal.whoami.vote.entity.Demo;
import com.bizideal.whoami.vote.facade.DemoRestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * dubbox restful 接口实现
 * @author zhushangjin
 *
 */
@Path("demo")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Component("demoRestFacade")
public class DemoRestFacadeImpl implements DemoRestFacade {

	@Autowired
	DemoService demoService;
	@Autowired
	RedisClientTemplate redisClientTemplate;

	@GET
	@Path("{id : \\d+}")
	@Override
	public Demo insertDemo(@PathParam("id") Integer id) {
		if (RpcContext.getContext().getRequest(HttpServletRequest.class) != null) {
			System.out.println("Client IP address from RpcContext: "
					+ RpcContext.getContext()
							.getRequest(HttpServletRequest.class)
							.getRemoteAddr());
		}
		if (RpcContext.getContext().getResponse(HttpServletResponse.class) != null) {
			System.out.println("Response object from RpcContext: "
					+ RpcContext.getContext().getResponse(
							HttpServletResponse.class));
		}
		Demo demo = new Demo();
		demo.setName("id" + id);
		redisClientTemplate.set("223213424", "dsadsadasd");
		// demoService.insertDemo(demo);
		return demo;
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public DubboxResult insetr(Demo demo) {
		int id = demoService.insertDemo(demo);
		return DubboxResult.build("200", "ok", id+"");
	}
	
	
	
	@GET
	@Path("get/{id : \\d+}")
	@Override
	public Demo get(@PathParam("id") Integer id) {
		if (RpcContext.getContext().getRequest(HttpServletRequest.class) != null) {
			System.out.println("Client IP address from RpcContext: "
					+ RpcContext.getContext()
							.getRequest(HttpServletRequest.class)
							.getRemoteAddr());
		}
		if (RpcContext.getContext().getResponse(HttpServletResponse.class) != null) {
			System.out.println("Response object from RpcContext: "
					+ RpcContext.getContext().getResponse(
							HttpServletResponse.class));
		}
		Demo demo = new Demo();
		demo.setName("id" + id);
		ProtostuffConvert<Demo> convert = new ProtostuffConvert<Demo>(Demo.class);
		byte[] bs = convert.convert(demo);
//		redisClientTemplate.putObject("demo1111", bs);
//		byte[] getbs =redisClientTemplate.getObject("demo1111");
		Demo mmm = convert.convert(bs);
		mmm.setName("中文");
		// demoService.insertDemo(demo);
		return mmm;
	}
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public DubboxResult testList(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return DubboxResult.build("200", "ok", list.size()+"");
	}
	@POST
	@Path("map")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public DubboxResult testMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return DubboxResult.build("200", "ok", map.get("id")+"");
	}
}