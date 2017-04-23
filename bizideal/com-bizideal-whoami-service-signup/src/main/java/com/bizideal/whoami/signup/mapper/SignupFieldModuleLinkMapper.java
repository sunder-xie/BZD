package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.signup.entity.SignupFieldModuleLink;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午10:59:57
 * @version 1.0
 */
public interface SignupFieldModuleLinkMapper extends
		Mapper<SignupFieldModuleLink> {

	@Update("UPDATE signup_field_module_link SET ...")
	int updateSignupFieldModuleLink(SignupFieldModuleLink link);

	@Update("DELETE FROM signup_field_module_link WHERE field_id = #{fieldId} AND module_id = #{moduleId}")
	int deleteSignupFieldModuleLink(SignupFieldModuleLink link);

	@Select("SELECT * FROM signup_field_module_link WHERE module_id != 999")
	List<SignupFieldModuleLink> selectAllLinks();

}
