package com.bizideal.whoami.signup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.pojo.DubboxResult;
import com.bizideal.whoami.signup.entity.SignupField;
import com.bizideal.whoami.signup.entity.SignupFieldMeetingLink;
import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;
import com.bizideal.whoami.signup.mapper.SignupFieldMapper;
import com.bizideal.whoami.signup.mapper.SignupFieldMeetingLinkMapper;
import com.bizideal.whoami.signup.mapper.SignupFieldModuleLinkMapper;
import com.bizideal.whoami.signup.service.SignupFieldService;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月16日 上午9:54:00
 * @version 1.0
 */
@Service("signupFieldService")
public class SignupFieldServiceImpl extends BaseBizImpl<SignupField> implements
		SignupFieldService {

	@Autowired
	private SignupFieldMapper signupFieldMapper;
	@Autowired
	private SignupFieldMeetingLinkMapper signupFieldMeetingLinkMapper;
	@Autowired
	private SignupFieldModuleLinkMapper signupFieldModuleLinkMapper;

	@Override
	public DubboxResult insertInit(int meeId) {
		List<SignupField> basics = signupFieldMapper.selectBasics();
		StringBuffer fields = new StringBuffer();
		for (int i = 0; i < basics.size(); i++) {
			if (i + 1 == basics.size()) {
				fields.append(basics.get(i).getFieldId());
			} else {
				fields.append(basics.get(i).getFieldId() + ",");
			}
		}
		SignupFieldMeetingLink meeting = new SignupFieldMeetingLink();
		meeting.setMeeId(meeId);
		meeting.setFieldIds(fields.toString());
		signupFieldMeetingLinkMapper.insertInit(meeting);
		return DubboxResult.build("0", "ok", meeting.getId() + "");
	}

	@Override
	public List<SignupField> selectSignupFieldsByModuids(List<Integer> moduleIds) {
		return signupFieldMapper.selectSignupFieldsByModuids(moduleIds);
	}

	@Override
	public SignupFieldMeetingLink selectSignupFieldMeetingLinkByMeeId(int meeId) {
		return signupFieldMeetingLinkMapper
				.selectSignupFieldMeetingLinkByMeeId(meeId);
	}

	@Override
	public DubboxResult updateField(SignupFieldMeetingLink link) {
		int updateField = signupFieldMeetingLinkMapper.updateField(link);
		if (updateField == 0) {
			DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public DubboxResult insertSignupFieldModuleLink(SignupFieldModuleLink link) {
		signupFieldModuleLinkMapper.insert(link);
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public DubboxResult updateSignupFieldModuleLink(SignupFieldModuleLink link) {
		int updateField = signupFieldModuleLinkMapper.updateByPrimaryKey(link);
		if (updateField == 0) {
			DubboxResult.build("1", "更新失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public DubboxResult deleteSignupFieldModuleLink(SignupFieldModuleLink link) {
		int updateField = signupFieldModuleLinkMapper.deleteByPrimaryKey(link);
		if (updateField == 0) {
			DubboxResult.build("1", "删除失败", null);
		}
		return DubboxResult.build("0", "ok", null);
	}

	@Override
	public List<SignupFieldModuleLink> selectAllLinks() {
		return signupFieldModuleLinkMapper.selectAllLinks();
	}

}
