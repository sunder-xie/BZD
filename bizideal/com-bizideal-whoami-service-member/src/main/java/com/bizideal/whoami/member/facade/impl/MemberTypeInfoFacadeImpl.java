package com.bizideal.whoami.member.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.facade.MemberTypeInfoFacade;
import com.bizideal.whoami.member.service.MemberTypeInfoService;

@Component("memberTypeInfoFacade")
public class MemberTypeInfoFacadeImpl implements MemberTypeInfoFacade {

	@Autowired
	private MemberTypeInfoService memberTypeInfoService;

	public Integer save(MemberTypeInfo t) {
		return memberTypeInfoService.saveSelective(t);
	}

	public Integer update(MemberTypeInfo t) {
		return memberTypeInfoService.updateSelective(t);
	}

	public Integer deleteById(Integer id) {
		return memberTypeInfoService.deleteById(id);
	}

	public MemberTypeInfo selectById(Integer id) {
		return memberTypeInfoService.queryById(id);
	}

	public List<MemberTypeInfo> selectListByHallId(Integer hallId) {
		return memberTypeInfoService.selectListByHallId(hallId);
	}

	@Override
	public MemberTypeInfo selectByNameAndType(Integer hallId, String name, Integer type) {
		return memberTypeInfoService.selectByNameAndType(hallId, name, type);
	}

	@Override
	public List<MemberTypeInfo> selectListByHallIdAndType(Integer hallId, Integer type) {
		return memberTypeInfoService.selectListByHallIdAndType(hallId, type);
	}

}
