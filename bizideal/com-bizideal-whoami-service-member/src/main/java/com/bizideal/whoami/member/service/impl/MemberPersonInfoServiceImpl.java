package com.bizideal.whoami.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.member.entity.MemberPersonInfo;
import com.bizideal.whoami.member.mapper.MemberPersonInfoMapper;
import com.bizideal.whoami.member.service.MemberPersonInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("memberPersonInfoService")
public class MemberPersonInfoServiceImpl extends BaseBizImpl<MemberPersonInfo> implements MemberPersonInfoService {

	@Autowired
	private MemberPersonInfoMapper memberPersonInfoMapper;

	@Override
	public PageInfo<MemberPersonInfo> selectListByHallIdAndString(Integer hallId, String str, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<MemberPersonInfo> list = memberPersonInfoMapper.selectListByHallIdAndString(hallId, str);
		return new PageInfo<MemberPersonInfo>(list);
	}

	@Override
	public PageInfo<MemberPersonInfo> selectListByTypeId(Integer typeId, Integer page, Integer rows) {
		MemberPersonInfo record = new MemberPersonInfo();
		record.setMemberTypeId(typeId);
		PageHelper.startPage(page, rows);
		List<MemberPersonInfo> list = memberPersonInfoMapper.select(record);
		return new PageInfo<MemberPersonInfo>(list);
	}

	@Override
	public MemberPersonInfo selectByPhoneAndHallId(String phone, Integer hallId) {

		MemberPersonInfo record = new MemberPersonInfo();
		record.setPhone(phone);
		record.setHallId(hallId);
		return memberPersonInfoMapper.selectOne(record);
	}

	@Override
	public Integer insertList(List<MemberPersonInfo> list) {
		return memberPersonInfoMapper.insertList(list);
	}
}
