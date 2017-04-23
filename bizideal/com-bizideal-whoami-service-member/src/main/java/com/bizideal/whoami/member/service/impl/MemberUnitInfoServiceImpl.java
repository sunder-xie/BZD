package com.bizideal.whoami.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.member.entity.MemberUnitInfo;
import com.bizideal.whoami.member.mapper.MemberUnitInfoMapper;
import com.bizideal.whoami.member.service.MemberUnitInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("memberUnitInfoService")
public class MemberUnitInfoServiceImpl extends BaseBizImpl<MemberUnitInfo> implements MemberUnitInfoService {

	@Autowired
	private MemberUnitInfoMapper memberUnitInfoMapper;

	@Override
	public PageInfo<MemberUnitInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows) {

		PageHelper.startPage(page, rows);
		List<MemberUnitInfo> list = memberUnitInfoMapper.selectListByHallIdAndString(hallId, str);
		return new PageInfo<MemberUnitInfo>(list);
	}

	@Override
	public PageInfo<MemberUnitInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows) {
		MemberUnitInfo record = new MemberUnitInfo();
		record.setMemberTypeId(typeId);
		PageHelper.startPage(page, rows);
		List<MemberUnitInfo> list = memberUnitInfoMapper.select(record);
		return new PageInfo<MemberUnitInfo>(list);
	}

	@Override
	public MemberUnitInfo selectByUnitNameAndHallId(String name, Integer hallId) {
		MemberUnitInfo record = new MemberUnitInfo();
		record.setUnitName(name);
		record.setHallId(hallId);
		return memberUnitInfoMapper.selectOne(record);
	}

	@Override
	public Integer insertList(List<MemberUnitInfo> list) {
		return memberUnitInfoMapper.insertList(list);
	}

	@Override
	public List<MemberUnitInfo> selectListByMeeId(Integer meeId, String str) {
		return memberUnitInfoMapper.selectListByMeeId(meeId, str);
	}
}
