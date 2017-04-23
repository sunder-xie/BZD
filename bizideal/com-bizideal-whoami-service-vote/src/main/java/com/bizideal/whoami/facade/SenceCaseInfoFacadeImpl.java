package com.bizideal.whoami.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.sencevote.entity.SenceCaseInfo;
import com.bizideal.whoami.sencevote.facade.SenceCaseInfoFacade;
import com.bizideal.whoami.service.SenceCaseInfoService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName SenceCaseInfoFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Component("senceCaseInfoFacade")
public class SenceCaseInfoFacadeImpl implements SenceCaseInfoFacade{

	@Autowired
	SenceCaseInfoService senceCaseInfoService;
	
	@Override
	public int insert(SenceCaseInfo senceCaseInfo) {
		return senceCaseInfoService.insert(senceCaseInfo);
	}

	@Override
	public int update(SenceCaseInfo senceCaseInfo) {
		return senceCaseInfoService.update(senceCaseInfo);
	}

	@Override
	public int delete(SenceCaseInfo senceCaseInfo) {
		return senceCaseInfoService.delete(senceCaseInfo);
	}

	@Override
	public SenceCaseInfo selectSenceCaseInfoById(Integer id) {
		return senceCaseInfoService.selectSenceCaseInfoById(id);
	}

	@Override
	public List<SenceCaseInfo> selectAllSenceCaseInfoByMeeId(Integer meeId) {
		return senceCaseInfoService.selectAllSenceCaseInfoByMeeId(meeId);
	}

	@Override
	public PageInfo<SenceCaseInfo> selectSenceCaseInfoByMeeIdCondition(
			SenceCaseInfo senceCaseInfo) {
		return senceCaseInfoService.selectSenceCaseInfoByMeeIdCondition(senceCaseInfo);
	}

	@Override
	public PageInfo<SenceCaseInfo> selectSenceCaseInfoByUserIdMeeId(String userId,
			Integer meeId,int pageNum, int pageSize) {
		return senceCaseInfoService.selectSenceCaseInfoByUserIdMeeId(userId, meeId, pageNum, pageSize);
	}

}
