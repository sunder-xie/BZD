package com.bizideal.whoami.rolemodule.service;


import com.bizideal.whoami.rolemodule.entity.QrcodeScan;


/**
 * @author zhu_shangjin
 * @version 2017年1月10日 下午1:38:13
 */
public interface QrcodeScanService {
	/**
	 * 新增或更新二维码
	 * @param qrcodeScan
	 * @return
	 * @throws Exception
	 */
	int insertOrUpdateCode(QrcodeScan qrcodeScan) throws Exception;
	/**
	 * 根据 二维码的redirect属性查询到对应的二维码
	 * @param redirect
	 * @return
	 */
	QrcodeScan selectByRedirect(String redirect);
	/**
	 * 根据主键查询出对应的二维码
	 * @param codeid
	 * @return
	 */
	QrcodeScan selectByPK(Integer codeid);
	/**
	 * 根据二维码的redirect 属性更新userid（谁扫的二维码）
	 * @param userid
	 * @param redirect
	 * @return
	 * @throws Exception
	 */
	int updateQrcode(String userid, String redirect) throws Exception;

}
