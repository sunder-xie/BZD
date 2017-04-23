package com.bizideal.whoami.rolemodule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu_shangjin
 * @version 2016年12月23日 下午2:17:56
 */
public interface QrcodeScanMapper extends Mapper<QrcodeScan>{
	/**
	 * 根据 QrcodeScan 的redirect属性查询二维码
	 * @param redirect
	 * @return
	 */
	QrcodeScan selectByRedirect(String redirect);
	

}
