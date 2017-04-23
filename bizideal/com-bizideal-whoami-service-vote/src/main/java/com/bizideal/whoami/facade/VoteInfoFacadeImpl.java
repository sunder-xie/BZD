package com.bizideal.whoami.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.service.VoteInfoService;
import com.bizideal.whoami.vote.entity.VoteInfo;
import com.bizideal.whoami.vote.facade.VoteInfoFacade;

/**
 * 
 * @ClassName VoteInfoFacade
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年1月19日
 */
@Component("voteInfoFacade")
public class VoteInfoFacadeImpl implements VoteInfoFacade {

	@Autowired
	private VoteInfoService voteInfoService;

	@Override
	public int insert(VoteInfo voteInfo) {
		return voteInfoService.insert(voteInfo);
	}

	@Override
	public int update(VoteInfo voteInfo) {
		return voteInfoService.update(voteInfo);
	}

	@Override
	public int delete(int id) {
		return voteInfoService.delete(id);
	}

	/*
	 * @Override public int deleteVoteInfoByIds(List<Integer> list) { // return
	 * voteInfoService.deleteVoteInfoByIds(list); return 0; }
	 */

	@Override
	public VoteInfo selectById(int id) {
		return voteInfoService.selectById(id);
	}

	/*
	 * @Override public List<VoteInfo> selectVoteInfoSelective(VoteInfo
	 * voteInfo) { // return voteInfoService.selectVoteInfoSelective(voteInfo);
	 * return null; }
	 */

	public VoteInfo selectVoteInfo(Integer meetingId, int type) {
		return voteInfoService.selectVoteInfo(meetingId, type);
	}

}
