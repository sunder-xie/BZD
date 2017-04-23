package com.bizideal.whoami.signup.facade;

import java.util.List;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月16日 上午10:15:00
 * @version 1.0
 */
public interface SignupFieldFacade {
	/**
	 * 新创建会议时，插入一条纪录
	 * 
	 * @param meeId
	 *            主会议id
	 * @return
	 */
	DubboxResult insertInit(int meeId);

	/**
	 * 查询可编辑报名字段，根据会议选择的模块确定
	 * 
	 * @param moduleIds
	 *            模块id列表
	 * @return
	 */
	List<SignupField> selectSignupFieldsByModuids(List<Integer> moduleIds);

	/**
	 * 查询会议已经选择了哪些字段
	 * 
	 * @param meeId
	 *            主会议id
	 * @return
	 */
	SignupFieldMeetingLink selectSignupFieldMeetingLinkByMeeId(int meeId);

	/**
	 * 根据会议id，修改会议下的报名字段
	 * 
	 * @param link
	 * @return
	 */
	DubboxResult updateField(SignupFieldMeetingLink link);

	/**
	 * 新增基础字段
	 * 
	 * @param signupField
	 * @return
	 */
	DubboxResult insertBasicField(SignupField signupField);

	/**
	 * 修改基础字段
	 * 
	 * @param signupField
	 * @return
	 */
	DubboxResult updateBasicField(SignupField signupField);

	/**
	 * 将单个字段分配给某个模块
	 * 
	 * @param signupFieldModuleLink
	 * @return
	 */
	DubboxResult insertSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink);

	/**
	 * 修改将单个字段分配给某个模块
	 * 
	 * @param signupFieldModuleLink
	 * @return
	 */
	DubboxResult updateSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink);

	/**
	 * 删除某个字段的模块关联
	 * 
	 * @param signupFieldModuleLink
	 * @return
	 */
	DubboxResult deleteSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink);

	/**
	 * 查询出所有字段和模块的关联信息
	 * 
	 * @return
	 */
	List<SignupFieldModuleLink> selectAllLinks();

}
