package com.bizideal.whoami.signup.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;
import com.bizideal.whoami.signup.service.SignupFieldService;

/**
 * 作用：报名字段编辑
 * @author 作者 liulq:
 * @data 创建时间：2017年2月16日 上午10:16:01
 * @version 1.0
 */
@Component("signupFieldFacade")
public class SignupFieldFacadeImpl implements SignupFieldFacade {

	@Autowired
	private SignupFieldService signupFieldService;

	@Override
	public DubboxResult insertInit(int meeId) {
		return signupFieldService.insertInit(meeId);
	}

	@Override
	public List<SignupField> selectSignupFieldsByModuids(List<Integer> moduleIds) {
		return signupFieldService.selectSignupFieldsByModuids(moduleIds);
	}

	@Override
	public SignupFieldMeetingLink selectSignupFieldMeetingLinkByMeeId(int meeId) {
		return signupFieldService.selectSignupFieldMeetingLinkByMeeId(meeId);
	}

	@Override
	public DubboxResult updateField(SignupFieldMeetingLink link) {
		return signupFieldService.updateField(link);
	}

	@Override
	public DubboxResult insertBasicField(SignupField signupField) {
		signupFieldService.save(signupField);
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public DubboxResult updateBasicField(SignupField signupField) {
		Integer update = signupFieldService.update(signupField);
		if (update == 0) {
			return DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public DubboxResult insertSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink) {
		return signupFieldService
				.insertSignupFieldModuleLink(signupFieldModuleLink);
	}

	@Override
	public DubboxResult updateSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink) {
		return signupFieldService
				.updateSignupFieldModuleLink(signupFieldModuleLink);
	}

	@Override
	public DubboxResult deleteSignupFieldModuleLink(
			SignupFieldModuleLink signupFieldModuleLink) {
		return signupFieldService
				.deleteSignupFieldModuleLink(signupFieldModuleLink);
	}

	@Override
	public List<SignupFieldModuleLink> selectAllLinks() {
		return signupFieldService.selectAllLinks();
	}

}
