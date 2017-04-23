package com.bizideal.whoami.signup.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.entity.UserSignupSubmeeting;
import com.bizideal.whoami.signup.mapper.SignUpInfoMapper;
import com.bizideal.whoami.signup.mapper.UserSignupSubmeetingMapper;
import com.bizideal.whoami.signup.service.SignUpService;

/**
 * @ClassName UserSignUpServiceImpl
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-05 15:14:12
 */
@Service("signUpService")
public class SignUpServiceImpl implements SignUpService {

	private static final Log log = LogFactory.getLog(SignUpServiceImpl.class);
	@Autowired
	private SignUpInfoMapper signUpInfoMapper;

	@Autowired
	private UserSignupSubmeetingMapper userSignupSubmeetingMapper;

	@Override
	public int insertSignUpInfo(SignUpInfo userSignUpInfo) {
		// 判断用户有没有报名
		List<SignUpInfo> list = signUpInfoMapper.selectSignUpInfoByUserIdMeeId(userSignUpInfo.getUserId(), userSignUpInfo.getMeetingId());
		if (null != list && list.size() > 0) {
			SignUpInfo signUpInfo = list.get(0);
			if (!"2".equals(signUpInfo.getIsPend())) {
				// 已经报名,正在审核中
				return 0;
			} else {
				// 用户审核被拒绝，正在重新报名
				signUpInfoMapper.deleteSignUpInfoById(signUpInfo.getId());
			}
		}
		if (null != userSignUpInfo) {
			return signUpInfoMapper.insert(userSignUpInfo);
		} else {
			return 0;
		}
	}

	@Override
	public int insertSignUpInfoAndSubMeeting(SignUpInfoDto upInfoDto) {
		SignUpInfo userSignUpInfo = new SignUpInfo();
		userSignUpInfo.setId(upInfoDto.getSignUpId());
		userSignUpInfo.setDietId(upInfoDto.getDietId());
		userSignUpInfo.setDisp(upInfoDto.getDisp());
		userSignUpInfo.setEmail(upInfoDto.getEmail());
		userSignUpInfo.setMeethallId(upInfoDto.getMeethallId());
		userSignUpInfo.setUserId(upInfoDto.getUserId());
		userSignUpInfo.setMeetingId(upInfoDto.getMeetingId());
		userSignUpInfo.setCreateDatatime(upInfoDto.getCreateDatatime());
		userSignUpInfo.setUnitId(upInfoDto.getUnitId());
		userSignUpInfo.setIdentityId(upInfoDto.getIdentityId());
		userSignUpInfo.setPost(upInfoDto.getPost());
		userSignUpInfo.setMessageIds(upInfoDto.getMessageIds());
		userSignUpInfo.setIsPend(upInfoDto.getIsPend());

		// 判断用户有没有报名
		List<SignUpInfo> list = signUpInfoMapper.selectSignUpInfoByUserIdMeeId(userSignUpInfo.getUserId(), userSignUpInfo.getMeetingId());
		if (null != list && list.size() > 0) {
			SignUpInfo signUpInfo = list.get(0);
			if (!"2".equals(signUpInfo.getIsPend())) {
				// 已经报名,正在审核中
				return 0;
			} else {
				// 用户审核被拒绝，正在重新报名
				signUpInfoMapper.deleteSignUpInfoById(signUpInfo.getId());
			}
		}

		if (null != userSignUpInfo) {
			int count = signUpInfoMapper.insert(userSignUpInfo);
			if (count > 0) {
				for (int i : upInfoDto.getSubmeetingId()) {
					UserSignupSubmeeting uss = new UserSignupSubmeeting();
					uss.setCreateDatatime(System.currentTimeMillis());
					uss.setDsp("");
					uss.setSignupId(userSignUpInfo.getId());
					uss.setSubmeetingId(i);
					if (userSignupSubmeetingMapper.insertSelective(uss) == 1) {
						log.info("*******" + upInfoDto.getRealName() + "用户报名子会议插入成功*******");
						continue;
					} else {
						// 抛出异常
						log.info("*******" + upInfoDto.getRealName() + ",submeeting:" + i + "用户报名子会议插入失败*******");
					}
				}
				return count;
			}
		}
		return 0;
	}

	@Override
	public List<Integer> selectMeetingIdByUserId(String userId) {
		return signUpInfoMapper.selectMeetingIdByUserId(userId);
	}

	@Override
	public List<Integer> selectSubMeetingIdByUserId(String userId, Integer meeId) {
		return signUpInfoMapper.selectSubMeetingIdByUserId(userId, meeId);
	}

	@Override
	public SignUpInfo selectSignUpInfoByUserIdMeeId(SignUpInfo signUpInfo) {
		SignUpInfo temp = null;
		List<SignUpInfo> li = signUpInfoMapper.selectSignUpInfoByUserIdMeeId(signUpInfo.getUserId(), signUpInfo.getMeetingId());
		if (null != li && li.size() > 0) {
			temp = li.get(0);
		}
		return temp;
	}

	@Override
	public SignUpInfo selectBySignUpId(String id) {
		return signUpInfoMapper.selectBySignUpId(id);
	}

	@Override
	public int updatePendById(String id, String pend, String rejectReason) {
		return signUpInfoMapper.updatePendById(id, pend, rejectReason);
	}

	@Override
	public int deleteById(String id) {
		return signUpInfoMapper.deleteById(id);
	}

	@Override
	public int selectCountByUnitIdMeeId(int meeId, int unitId) {
		return signUpInfoMapper.selectCountByUnitIdMeeId(meeId, unitId);
	}

	@Override
	public Integer selectCountByMeeId(Integer meeId, Integer isPend) {
		return signUpInfoMapper.selectCountByMeeId(meeId, isPend);
	}

	@Override
	public Integer updateSignUp(SignUpInfoEditDto signUpInfoEditDto) {
		SignUpInfo selectBySignUpId = signUpInfoMapper.selectBySignUpId(signUpInfoEditDto.getSignUpId());
		if (selectBySignUpId == null) {
			return 0;
		}
		Integer[] submeetingId = signUpInfoEditDto.getSubmeetingId();
		if (null == submeetingId || submeetingId.length == 0) {
			// 没有子会议的情况
			return signUpInfoMapper.updateSignUp(signUpInfoEditDto);
		}
		// 有子会议的情况
		List<UserSignupSubmeeting> list = new ArrayList<UserSignupSubmeeting>();
		for (int i = 0; i < submeetingId.length; i++) {
			UserSignupSubmeeting sub = new UserSignupSubmeeting();
			sub.setSignupId(signUpInfoEditDto.getSignUpId());
			sub.setSubmeetingId(submeetingId[i]);
			sub.setCreateDatatime(selectBySignUpId.getCreateDatatime());
		}
		signUpInfoMapper.deleteMySubMeetings(signUpInfoEditDto);
		signUpInfoMapper.insertSubMeeBatch(list);
		Integer updateSignUp = signUpInfoMapper.updateSignUp(signUpInfoEditDto);
		if (updateSignUp == 0) {
			// 手动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		return 1;
	}
}
