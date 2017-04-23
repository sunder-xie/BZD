package com.bizideal.whoami.member.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.member.entity.MemberUnitInfo;
import com.bizideal.whoami.member.facade.MemberUnitInfoFacade;
import com.bizideal.whoami.member.service.MemberUnitInfoService;
import com.github.pagehelper.PageInfo;

@Component("memberUnitInfoFacade")
public class MemberUnitInfoFacadeImpl implements MemberUnitInfoFacade {

	@Autowired
	private MemberUnitInfoService memberUnitInfoService;

	public Integer save(MemberUnitInfo t) {
		return memberUnitInfoService.saveSelective(t);
	}

	public Integer update(MemberUnitInfo t) {
		return memberUnitInfoService.updateSelective(t);
	}

	public Integer deleteById(Integer id) {
		return memberUnitInfoService.deleteById(id);
	}

	public MemberUnitInfo selectById(Integer id) {
		return memberUnitInfoService.queryById(id);
	}

	public PageInfo<MemberUnitInfo> selectListByHallId(Integer hallId, Integer page, Integer rows) {
		return memberUnitInfoService.selectListByHallIdAndString(hallId, null, page, rows);
	}

	public PageInfo<MemberUnitInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows) {
		return memberUnitInfoService.selectListByHallIdAndString(hallId, str, page, rows);
	}

	public PageInfo<MemberUnitInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows) {
		return memberUnitInfoService.selectListByTypeId(typeId, page, rows);
	}

	@Override
	public MemberUnitInfo selectByUnitNameAndHallId(String name, Integer hallId) {
		return memberUnitInfoService.selectByUnitNameAndHallId(name, hallId);
	}

	@Override
	public Integer insertList(List<MemberUnitInfo> list) {
		return memberUnitInfoService.insertList(list);
	}

	@Override
	public List<MemberUnitInfo> selectListByMeeId(Integer meeId, String str) {
		return memberUnitInfoService.selectListByMeeId(meeId, str);
	}
}
