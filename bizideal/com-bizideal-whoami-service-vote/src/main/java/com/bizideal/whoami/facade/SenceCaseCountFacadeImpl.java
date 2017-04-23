package com.bizideal.whoami.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.sencevote.entity.SenceCaseCount;
import com.bizideal.whoami.sencevote.facade.SenceCaseCountFacade;
import com.bizideal.whoami.service.SenceCaseCountService;

/**
 * 
 * @ClassName SenceCaseCountFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2017年2月10日
 */
@Component("senceCaseCountFacade")
public class SenceCaseCountFacadeImpl implements SenceCaseCountFacade {

	@Autowired
	private SenceCaseCountService senceCaseCountService;

	@Override
	public SenceCaseCount selectSenceCaseCount(int senceCaseId) {
		return senceCaseCountService.selectSenceCaseCount(senceCaseId);
	}

}
