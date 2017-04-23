package com.bizideal.whoami.signup.facade;

import com.bizideal.whoami.signup.entity.Identity;
import com.github.pagehelper.PageInfo;

/**
 * @author 作者 liulq:
 * @data 创建时间：2017年3月15日 下午6:02:03
 * @version 1.0
 * @description 身份接口
 */
public interface IdentityFacade {

	/**
	 * 通过id查询身份
	 * 
	 * @param id
	 * @return
	 */
	Identity getById(int id);

	/**
	 * 分页查询会议下身份，根据会议id
	 * 
	 * @param identity
	 * @return
	 */
	PageInfo<Identity> getByPage(Identity identity);

	/**
	 * 身份信息更新
	 * 
	 * @param identity
	 * @return
	 */
	int updateById(Identity identity);

	/**
	 * 身份信息添加
	 * 
	 * @param identity
	 * @return
	 */
	int insert(Identity identity);

	/**
	 * 身份信息删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(int id);

}
