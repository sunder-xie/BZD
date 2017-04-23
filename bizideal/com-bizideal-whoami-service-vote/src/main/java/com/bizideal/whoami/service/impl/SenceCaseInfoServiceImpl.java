package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.SenceCaseCountMapper;
import com.bizideal.whoami.mapper.SenceCaseInfoMapper;
import com.bizideal.whoami.sencevote.entity.SenceCaseCount;
import com.bizideal.whoami.sencevote.entity.SenceCaseInfo;
import com.bizideal.whoami.service.SenceCaseInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName SenceCaseInfoServiceImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Service("senceCaseInfoService")
public class SenceCaseInfoServiceImpl implements SenceCaseInfoService{

	@Autowired
	private SenceCaseInfoMapper senceCaseInfoMapper;
	@Autowired
	private SenceCaseCountMapper senceCaseCountMapper;
	
	@Override
	public int insert(SenceCaseInfo senceCaseInfo) {
		int i=senceCaseInfoMapper.insertSelective(senceCaseInfo);
		SenceCaseCount senceCaseCount=new SenceCaseCount();
		if(i==1){
			senceCaseCount.setSenceCaseId(senceCaseInfo.getId());
			senceCaseCount.setUpdateTime(System.currentTimeMillis());
			i=senceCaseCountMapper.insertSelective(senceCaseCount);
		}
		return i;
	}

	@Override
	public int update(SenceCaseInfo senceCaseInfo) {
		return senceCaseInfoMapper.updateByPrimaryKeySelective(senceCaseInfo);
	}

	@Override
	public int delete(SenceCaseInfo senceCaseInfo) {
		SenceCaseInfo temp=new SenceCaseInfo();
		temp.setId(senceCaseInfo.getId());
		temp.setDelFlag((byte) 1);
		return senceCaseInfoMapper.updateByPrimaryKeySelective(temp);
	}

	@Override
	public SenceCaseInfo selectSenceCaseInfoById(Integer id) {
		return senceCaseInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SenceCaseInfo> selectAllSenceCaseInfoByMeeId(Integer meeId) {
		return senceCaseInfoMapper.selectAllSenceCaseInfoByMeeId(meeId);
	}

	@Override
	public PageInfo<SenceCaseInfo> selectSenceCaseInfoByMeeIdCondition(SenceCaseInfo senceCaseInfo) {
		PageHelper.startPage(senceCaseInfo.getPageNum(), senceCaseInfo.getPageSize());
		List<SenceCaseInfo> li=senceCaseInfoMapper.selectSenceCaseInfoByMeeIdCondition(senceCaseInfo);
		return new PageInfo<SenceCaseInfo>(li);
	}

	@Override
	public PageInfo<SenceCaseInfo> selectSenceCaseInfoByUserIdMeeId(String userId,
			Integer meeId,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SenceCaseInfo> li=senceCaseInfoMapper.selectSenceCaseInfoByUserIdMeeId(userId,meeId);
		return new PageInfo<SenceCaseInfo>(li);
	}

}
