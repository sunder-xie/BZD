package com.bizideal.whoami.core.meeting.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.core.meeting.biz.HallLinkUserBiz;
import com.bizideal.whoami.core.meeting.mapper.HallLinkUserMapper;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.facade.meeting.entity.HallLinkUser;
import com.bizideal.whoami.facade.meeting.enums.MeettingExceptionEnums;
import com.bizideal.whoami.pojo.CustomException;
import com.bizideal.whoami.utils.ExceptionUtilsCls;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * @ClassName HallLinkUserBiz
 * @Description TODO(会议厅关注的用户)
 * @Author Zj.Qu
 * @Date 2017-01-10 16:39:45
 */
@Service("hallLinkUserBiz")
public class HallLinkUserBizImpl extends BaseBizImpl<HallLinkUser> implements HallLinkUserBiz{
	
	@Autowired
	private HallLinkUserMapper hallLinkUserMapper;
	/**
	 * 保存关注会议厅用户
	 */
	@Override
	public Integer save(HallLinkUser t) 
			throws CustomException{
		Integer integer = null;
		try{
		 if(null == t){
			//抛出自定义异常
			 throw new Exception("{'code':'"+
			 MeettingExceptionEnums.HallLinkUserBizImpl_save_02_ERROR.getCode()+"','msg':'"+
			 MeettingExceptionEnums.HallLinkUserBizImpl_save_02_ERROR.getMsg()+"'}");
		 }				
		 t.setCreateTime(System.currentTimeMillis());			 
		 //插入一条关注会议厅记录
		 integer = hallLinkUserMapper.insert(t);
		 if(null == integer || 1 > integer ){
			 //抛出自定义异常
			 throw new Exception("{'code':'"+
			 MeettingExceptionEnums.HallLinkUserBizImpl_save_01_ERROR.getCode()+"','msg':'"+
			 MeettingExceptionEnums.HallLinkUserBizImpl_save_01_ERROR.getMsg()+"'}");
		 }		 
		}catch(Exception e){
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.HallLinkUserBizImpl_save_00_ERROR.getCode(),
					MeettingExceptionEnums.HallLinkUserBizImpl_save_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != t){
				params = t.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer;
	}

	@Override
	public Integer saveSelective(HallLinkUser t) 
			throws CustomException{
		Integer integer = null;
		try {			
			t.setCreateTime(System.currentTimeMillis());		 
			integer = hallLinkUserMapper.insertSelective(t);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.HallLinkUserBizImpl_saveSelective_00_ERROR.getCode(),
					MeettingExceptionEnums.HallLinkUserBizImpl_saveSelective_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != t){
				params = t.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return integer ;
	}
	
	@Override
	public int countUserIdByHallId(Integer hallId) 
			throws CustomException{
		int flag = -1;
		try {
			flag = hallLinkUserMapper.countUserIdByHallId(hallId);
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.HallLinkUserBizImpl_countUserIdByHallId_00_ERROR.getCode(),
					MeettingExceptionEnums.HallLinkUserBizImpl_countUserIdByHallId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != hallId){
				params = hallId.toString();
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return flag;
	}
	
	@Override
	public PageInfo<HallLinkUser> ListUserIdByHallId(Integer page,Integer rows,Integer hallId) 
			throws CustomException{
		PageInfo<HallLinkUser> pageInfos = null;
		try {
			
			PageHelper.startPage(page, rows);
			List<HallLinkUser> list = hallLinkUserMapper.ListUserIdByHallId(hallId);
			pageInfos = new PageInfo<HallLinkUser>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.HallLinkUserBizImpl_ListUserIdByHallId_00_ERROR.getCode(),
					MeettingExceptionEnums.HallLinkUserBizImpl_ListUserIdByHallId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != page){
				params += "page:"+page.toString()+";";
			}
			if(null != rows){
				params += "rows:"+rows.toString()+";";
			}
			if(null != hallId){
				params += "hallId:"+hallId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfos;
	}
	
	@Override
	public PageInfo<HallLinkUser> ListHallByUserId(Integer page,Integer rows,String userId) 
			throws CustomException{
		PageInfo<HallLinkUser> pageInfos = null;
		try {
			
			PageHelper.startPage(page, rows);
			List<HallLinkUser> list = hallLinkUserMapper.ListHallByUserId(userId);
			pageInfos = new PageInfo<HallLinkUser>(list);
			
		} catch (Exception e) {
			CustomException customException = new CustomException(
					Thread.currentThread().getStackTrace()[1].getClassName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					MeettingExceptionEnums.HallLinkUserBizImpl_ListHallByUserId_00_ERROR.getCode(),
					MeettingExceptionEnums.HallLinkUserBizImpl_ListHallByUserId_00_ERROR.getMsg());	
			//用于拼装异常信息的参数列表
			String params = null;
			if(null != page){
				params += "page:"+page.toString()+";";
			}
			if(null != rows){
				params += "rows:"+rows.toString()+";";
			}
			if(null != userId){
				params += "userId:"+userId.toString()+";";
			}
			ExceptionUtilsCls.putCustomException(customException, e , params);			
			throw customException;
		}
		return pageInfos ;
	}
	
	
}