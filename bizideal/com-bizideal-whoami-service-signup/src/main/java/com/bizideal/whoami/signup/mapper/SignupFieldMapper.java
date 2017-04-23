package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.signup.entity.SignupField;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年2月15日 上午11:00:29
 * @version 1.0
 */
public interface SignupFieldMapper extends Mapper<SignupField> {

	@Select("SELECT * FROM signup_field WHERE field_basics = 1")
	List<SignupField> selectBasics();

	List<SignupField> selectSignupFieldsByModuids(List<Integer> moduleIds);

	List<SignupField> selectSignupFieldsByMeeId(List<Integer> fieldIds);
}
