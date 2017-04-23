package com.bizideal.whoami.member.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.bizideal.whoami.member.facade.MemberPersonInfoFacade;
import com.bizideal.whoami.member.service.MemberPersonInfoService;
import com.github.pagehelper.PageInfo;

@Component("memberPersonInfoFacade")
public class MemberPersonInfoFacadeImpl implements MemberPersonInfoFacade {

	@Autowired
	private MemberPersonInfoService memberPersonInfoService;

	@Override
	public Integer save(MemberPersonInfo t) {
		return memberPersonInfoService.saveSelective(t);
	}

	@Override
	public Integer update(MemberPersonInfo t) {
		return memberPersonInfoService.updateSelective(t);
	}

	@Override
	public Integer deleteById(Integer id) {
		return memberPersonInfoService.deleteById(id);
	}

	@Override
	public MemberPersonInfo selectByPhoneAndHallId(String phone, Integer hallId) {
		return memberPersonInfoService.selectByPhoneAndHallId(phone, hallId);
	}

	@Override
	public PageInfo<MemberPersonInfo> selectListByHallId(Integer hallId, Integer page, Integer rows) {
		return memberPersonInfoService.selectListByHallIdAndString(hallId, null, page, rows);
	}

	@Override
	public PageInfo<MemberPersonInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows) {
		return memberPersonInfoService.selectListByTypeId(typeId, page, rows);
	}

	@Override
	public Integer insertList(List<MemberPersonInfo> list) {
		return memberPersonInfoService.insertList(list);
	}

	@Override
	public PageInfo<MemberPersonInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows) {
		return memberPersonInfoService.selectListByHallIdAndString(hallId, str, page, rows);
	}
}
