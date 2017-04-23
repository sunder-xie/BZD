package com.bizideal.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.mapper.VoteInfoMapper;
import com.bizideal.whoami.service.VoteInfoService;
import com.bizideal.whoami.vote.entity.VoteInfo;

/**
 * 
 * @ClassName VoteInfoServiceImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Service("voteInfoService")
public class VoteInfoServiceImpl implements VoteInfoService {

	@Autowired
	private VoteInfoMapper voteInfoMapper;

	public int insert(VoteInfo voteInfo) {
		// 添加
		return voteInfoMapper.insertVoteInfo(voteInfo);
	}

	public int update(VoteInfo voteInfo) {
		// 修改
		return voteInfoMapper.updateByPrimaryKeySelective(voteInfo);
	}

	public int delete(int id) {
		// 逻辑删除 删除标记改为1
		VoteInfo voteInfo = new VoteInfo();
		voteInfo.setId(id);
		voteInfo.setDelFlag((byte) 1);
		return voteInfoMapper.updateByPrimaryKeySelective(voteInfo);
	}

	public VoteInfo selectVoteInfo(Integer meetingId, int type) {
		// 根据会议id查询全部
		VoteInfo record = new VoteInfo();
		record.setMeetingId(meetingId);
		record.setType(type);
		record.setDelFlag((byte) 0);
		VoteInfo selectOne = voteInfoMapper.selectOne(record);
		return selectOne;
	}

	public List<VoteInfo> selectOverdueVote(long time, int type) {
		// 查询过期投票
		List<VoteInfo> list = voteInfoMapper.selectOverdueVote(time, type);
		return list;
	}

	public int updateEmptyFlag(List<VoteInfo> list) {
		return voteInfoMapper.updateEmptyFlag(list);
	}

	public VoteInfo selectById(int id) {
		// 根据主键id查询
		VoteInfo record = new VoteInfo();
		record.setId(id);
		return voteInfoMapper.selectOne(record);
	}
}
