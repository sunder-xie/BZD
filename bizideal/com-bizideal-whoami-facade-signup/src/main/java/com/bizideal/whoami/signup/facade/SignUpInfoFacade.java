package com.bizideal.whoami.signup.facade;

import java.util.List;

import com.bizideal.whoami.dto.SignUpInfoDto;
import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.signup.entity.SignUpInfo;

/**
 * @ClassName UserSignInfoFacade
 * @Description TODO(detail)
 * @Author Cui.yt
 * @Date 2016-12-07 09:54:14
 */
public interface SignUpInfoFacade {

	/**
	 * 根据用户ID查询已经报名的会议ID
	 * 
	 * @param userId
	 * @return list
	 */
	List<Integer> selectMeetingIdByUserId(String userId);

	/**
	 * 根据用户ID和主会议ID查询已经报名的子会议ID
	 * 
	 * @param userId
	 * @param meeId
	 * @return
	 */
	List<Integer> selectSubMeetingIdByUserId(String userId, Integer meeId);

	/**
	 * 根据用户ID和会议ID查询报名会议没有
	 * 
	 * @param signUpInfo
	 * @return
	 */
	SignUpInfo selectSignUpInfoByUserIdMeeId(SignUpInfo signUpInfo);

	/**
	 * 用户报名接口
	 * 
	 * @param signUpInfoDto
	 * @return 返回int，大于0表示成功
	 */
	int userSignUp(SignUpInfoDto signUpInfoDto);
	
	/**
	 * 根据id查询报名信息
	 * 
	 * @param id
	 * @return
	 */
	SignUpInfo selectBySignUpId(String id);
	
	/**
	 * 根据id通过或拒绝审核
	 * 
	 * @param id 主键id
	 * @param isPend 0表示待审核，1表示审核通过，2表示审核被拒
	 * @param rejectReason 拒绝理由
	 * @return
	 */
	int updatePendById(String id, String isPend, String rejectReason);
	
	/**
	 * 根据id拒绝审核
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(String id);
	
	/**
	 * 查询单位下有多少人报名
	 * 
	 * @param meeId 会议id
	 * @param unitId 单位id
	 * @return
	 */
	int selectCountByUnitIdMeeId(int meeId, int unitId);
	
	
	/**
	 * 查询主会议下有多少人报名
	 * 
	 * @param meeId 会议id
	 * @param isPend 是否已经审核通过
	 * @return
	 */
	Integer selectCountByMeeId(Integer meeId, Integer isPend);
	
	/**
	 * 用户修改报名
	 * 
	 * @param signUpInfoEditDto
	 * @return
	 */
	boolean updateSignUp(SignUpInfoEditDto signUpInfoEditDto);
}
