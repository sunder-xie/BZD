package com.bizideal.whoami.leaflets.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.facade.leaflets.entity.MeetingLeaflets;
import com.bizideal.whoami.leaflets.mapper.MeetingLeafletsMapper;
import com.bizideal.whoami.leaflets.service.MeetingLeafletsBiz;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("meetingLeafletsBiz")
public class MeetingLeafletsBizImpl implements MeetingLeafletsBiz{

	@Autowired
	private MeetingLeafletsMapper meetingLeafletsMapper;
	
	@Override
	public MeetingLeaflets selectByMeeIdInUse(Integer meeId) {
		MeetingLeaflets temp=new MeetingLeaflets();
		temp.setMeeId(meeId);
		temp.setIsInUse("1");
		List<MeetingLeaflets> list=meetingLeafletsMapper.select(temp);
		if(null==list){
			return null;
		}else if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageInfo<MeetingLeaflets> selectByMeeId(Integer meeId,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		MeetingLeaflets temp=new MeetingLeaflets();
		temp.setMeeId(meeId);
		List<MeetingLeaflets> list=meetingLeafletsMapper.select(temp);
		return new PageInfo<MeetingLeaflets>(list);
	}

	@Override
	public MeetingLeaflets selectById(Integer id) {
		return meetingLeafletsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertMeetingLeaflets(MeetingLeaflets meetingLeaflets) {
		meetingLeaflets.setIsInUse("1");
		return meetingLeafletsMapper.insertSelective(meetingLeaflets);
	}

	@Override
	public int updateMeetingLeaflets(MeetingLeaflets meetingLeaflets) {
		return meetingLeafletsMapper.updateByPrimaryKeySelective(meetingLeaflets);
	}

	@Override
	public int deleteById(Integer id) {
		return meetingLeafletsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByIds(List<Integer> ids) {
		Example example = new Example(MeetingLeaflets.class);
		example.createCriteria().andIn("id", ids);
		return meetingLeafletsMapper.deleteByExample(example);
	}

}