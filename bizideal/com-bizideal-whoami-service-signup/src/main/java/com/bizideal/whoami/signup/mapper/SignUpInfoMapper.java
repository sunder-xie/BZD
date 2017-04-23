package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.dto.SignUpInfoEditDto;
import com.bizideal.whoami.signup.entity.SignUpInfo;
import com.bizideal.whoami.signup.entity.UserSignupSubmeeting;

/**
 * @ClassName UserWeixinInfoMapper
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-01 16:35:48
 */
public interface SignUpInfoMapper extends Mapper<SignUpInfo> {

	/**
	 * 根据用户ID查询已经报名的会议ID
	 * 
	 * @param userId
	 * @return list
	 */
	List<Integer> selectMeetingIdByUserId(@Param("userId") String userId);

	/**
	 * 根据用户ID查询已经报名的会议ID
	 * 
	 * @param userId
	 * @return list
	 */
	List<Integer> selectSubMeetingIdByUserId(@Param("userId") String userId, @Param("meeId") Integer meeId);

	/**
	 * 根据用户ID和会议ID查询报名的信息
	 * 
	 * @param userId
	 * @param meeId
	 * @return
	 */
	List<SignUpInfo> selectSignUpInfoByUserIdMeeId(@Param("userId") String userId, @Param("meeId") Integer meeId);

	/**
	 * 根据id查询报名信息
	 * 
	 * @param id
	 * @return
	 */
	SignUpInfo selectBySignUpId(String id);

	/**
	 * 根据id通过审核
	 * 
	 * @param id
	 *            主键id
	 * @param isPend
	 *            0表示待审核，1表示审核通过，2表示审核被拒
	 * @return
	 */
	@Update("UPDATE user_signup_info u SET u.is_pend = #{isPend},reject_reason = #{rejectReason} WHERE u.id = #{id}")
	int updatePendById(@Param("id") String id, @Param("isPend") String isPend, @Param("rejectReason") String rejectReason);

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
	 * @param meeId
	 *            会议id
	 * @param unitId
	 *            单位id
	 * @return
	 */
	int selectCountByUnitIdMeeId(@Param("meeId") int meeId, @Param("unitId") int unitId);

	/**
	 * 查询主会议下有多少人报名
	 * 
	 * @param meeId
	 *            会议id
	 * @param isPend
	 *            是否已经审核通过
	 * @return
	 */
	Integer selectCountByMeeId(@Param("meeId") Integer meeId, @Param("isPend") Integer isPend);

	/**
	 * 用户修改报名
	 * 
	 * @param signUpInfoEditDto
	 * @return
	 */
	Integer updateSignUp(SignUpInfoEditDto signUpInfoEditDto);

	/**
	 * 根据id删除之前报名的会议
	 * 
	 * @param signUpInfoEditDto
	 * @return
	 */
	int deleteMySubMeetings(SignUpInfoEditDto signUpInfoEditDto);

	/**
	 * 根据id删除报名信息
	 * 
	 * @param signUpId
	 * @return
	 */
	int deleteSignUpInfoById(String signUpId);

	/**
	 * 批量插入子会议信息
	 * 
	 * @param list
	 * @return
	 */
	int insertSubMeeBatch(List<UserSignupSubmeeting> list);

}
