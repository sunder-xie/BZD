package com.bizideal.whoami.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.member.entity.MemberTypeInfo;
import com.bizideal.whoami.member.mapper.MemberTypeInfoMapper;
import com.bizideal.whoami.member.service.MemberTypeInfoService;

@Service("memberTypeInfoService")
public class MemberTypeInfoServiceImpl extends BaseBizImpl<MemberTypeInfo> implements MemberTypeInfoService {

	@Autowired
	private MemberTypeInfoMapper memberTypeInfoMapper;

	@Override
	public List<MemberTypeInfo> selectListByHallId(Integer hallId) {
		MemberTypeInfo record = new MemberTypeInfo();
		record.setHallId(hallId);
		return memberTypeInfoMapper.select(record);
	}

	@Override
	public MemberTypeInfo selectByNameAndType(Integer hallId, String name, Integer type) {
		MemberTypeInfo record = new MemberTypeInfo();
		record.setHallId(hallId);
		record.setName(name);
		record.setType(type);
		return memberTypeInfoMapper.selectOne(record);
	}

	@Override
	public List<MemberTypeInfo> selectListByHallIdAndType(Integer hallId, Integer type) {
		MemberTypeInfo record = new MemberTypeInfo();
		record.setHallId(hallId);
		record.setType(type);
		return memberTypeInfoMapper.select(record);
	}

}
