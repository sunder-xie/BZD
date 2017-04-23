package com.bizideal.whoami.rolemodule.service.imp;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.rolemodule.entity.QrcodeScan;
import com.bizideal.whoami.rolemodule.entity.UserRoleLink;
import com.bizideal.whoami.rolemodule.mapper.QrcodeScanMapper;
import com.bizideal.whoami.rolemodule.service.QrcodeScanService;

/**
 * @author zhu_shangjin
 * @version 2017年1月10日 下午1:40:20
 */
@Service
public class QrcodeScanServiceImp implements QrcodeScanService {
	@Autowired
	QrcodeScanMapper qrcodeScanMapper;

	@Override
	public int insertOrUpdateCode(QrcodeScan qrcodeScan) throws Exception {
		QrcodeScan getcode = this.selectByRedirect(qrcodeScan.getRedirect());
		//根据连接查询二维码
		int codeid = 0;
		if (getcode == null) {
//	 不存在 新增		
			qrcodeScan.setHasScan("no");
			qrcodeScan.setScanType("app");
			qrcodeScanMapper.insertSelective(qrcodeScan);
			codeid = qrcodeScan.getCodeId();
		}else {
			// 存在
			if (Objects.equals("yes", getcode.getHasScan())) {
				//已经被扫描  改为没有扫描 
				Example example = new Example( QrcodeScan.class);
				example.createCriteria().andEqualTo("codeId", getcode.getCodeId()).
				andEqualTo("redirect", getcode.getRedirect());
				getcode.setHasScan("no");
				getcode.setScanUserId("");
				qrcodeScanMapper.updateByExample(getcode, example);
				
			}
			codeid = getcode.getCodeId();
		}
		
		return codeid;
	}

	@Override
	public QrcodeScan selectByRedirect(String redirect) {
		
		return qrcodeScanMapper.selectByRedirect(redirect);
	}

	@Override
	public QrcodeScan selectByPK(Integer codeid) {
		
		return qrcodeScanMapper.selectByPrimaryKey(codeid);
	}

	@Override
	public int updateQrcode(String userid, String redirect) throws Exception {
		QrcodeScan qrcodeScan = this.selectByRedirect(redirect);
		qrcodeScan.setHasScan("yes");
		qrcodeScan.setScanUserId(userid);
		qrcodeScan.setScanType("app");
		qrcodeScanMapper.updateByPrimaryKeySelective(qrcodeScan);
		return qrcodeScan.getCodeId();
	}

}
