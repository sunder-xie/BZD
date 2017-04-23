package com.bizideal.whoami.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bizideal.whoami.signup.entity.Identity;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午6:00:31
 * @version 1.0
 * @description 身份接口
 */
public interface IdentityMapper extends Mapper<Identity> {

	@Select("SELECT * FROM identity i WHERE i.mee_id = #{meeId}")
	List<Identity> selectByMeeId(Identity identity);

}
