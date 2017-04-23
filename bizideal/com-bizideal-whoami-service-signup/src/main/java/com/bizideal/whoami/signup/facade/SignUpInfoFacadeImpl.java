package com.bizideal.whoami.signup.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.service.SignUpService;

/**
 * 作用：报名
 * 
 * @ClassName UserSignUpInfoFacadeImpl
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-07 10:05:13
 */
@Component("signUpInfoFacade")
public class SignUpInfoFacadeImpl implements SignUpInfoFacade {

	@Autowired
	private SignUpService signUpService;

	@Override
	public List<Integer> selectMeetingIdByUserId(String userId) {
		return signUpService.selectMeetingIdByUserId(userId);
	}

	@Override
	public List<Integer> selectSubMeetingIdByUserId(String userId, Integer meeId) {
		return signUpService.selectSubMeetingIdByUserId(userId, meeId);
	}

	@Override
	public SignUpInfo selectSignUpInfoByUserIdMeeId(SignUpInfo signUpInfo) {
		return signUpService.selectSignUpInfoByUserIdMeeId(signUpInfo);
	}

	@Override
	public int userSignUp(SignUpInfoDto upInfoDto) {
		if (null == upInfoDto.getSubmeetingId()) {
			// 如果分论坛会议不存在,走此处保存方法
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
			// 插入到数据库中,保存用户报名信息
			return signUpService.insertSignUpInfo(userSignUpInfo);
		} else {
			// 有子会议
			return signUpService.insertSignUpInfoAndSubMeeting(upInfoDto);
		}
	}

	@Override
	public SignUpInfo selectBySignUpId(String id) {
		return signUpService.selectBySignUpId(id);
	}

	@Override
	public int updatePendById(String id, String pend, String rejectReason) {
		return signUpService.updatePendById(id, pend, rejectReason);
	}

	@Override
	public int deleteById(String id) {
		return signUpService.deleteById(id);
	}

	@Override
	public int selectCountByUnitIdMeeId(int meeId, int unitId) {
		return signUpService.selectCountByUnitIdMeeId(meeId, unitId);
	}

	@Override
	public Integer selectCountByMeeId(Integer meeId, Integer isPend) {
		return signUpService.selectCountByMeeId(meeId, isPend);
	}

	@Override
	public boolean updateSignUp(SignUpInfoEditDto signUpInfoEditDto) {
		Integer updateSignUp = signUpService.updateSignUp(signUpInfoEditDto);
		return updateSignUp != 0;
	}

}
