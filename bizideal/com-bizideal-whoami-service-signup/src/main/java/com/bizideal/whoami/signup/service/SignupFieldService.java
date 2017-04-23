package com.bizideal.whoami.signup.service;

import java.util.List;

import com.bizideal.whoami.croe.service.BaseBiz;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月16日 上午9:52:53
 * @version 1.0
 */
public interface SignupFieldService extends BaseBiz<SignupField> {

	/* 新创建会议时，插入一条纪录 */
	DubboxResult insertInit(int meeId);

	/* 查询可编辑报名字段，根据会议选择的模块确定 */
	List<SignupField> selectSignupFieldsByModuids(List<Integer> moduleIds);

	/* 查询会议已经选择了哪些字段 */
	SignupFieldMeetingLink selectSignupFieldMeetingLinkByMeeId(int meeId);

	/* 根据会议id，修改会议下的报名字段 */
	DubboxResult updateField(SignupFieldMeetingLink link);

	/* 将单个字段分配给某个模块 */
	DubboxResult insertSignupFieldModuleLink(SignupFieldModuleLink link);

	/* 修改将单个字段分配给某个模块 */
	DubboxResult updateSignupFieldModuleLink(SignupFieldModuleLink link);

	/* 删除某个字段的模块关联 */
	DubboxResult deleteSignupFieldModuleLink(SignupFieldModuleLink link);

	/* 查询出所有字段和模块的关联信息 */
	List<SignupFieldModuleLink> selectAllLinks();

}
