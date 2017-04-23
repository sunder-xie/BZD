package com.bizideal.whoami.core.meetingmaterial.biz;

import com.bizideal.whoami.croe.service.BaseOpBiz;
import com.bizideal.whoami.facade.meetingmaterial.entity.MeetingAttach;

/**
 * @ClassName MeetingAttachBiz
 * @Description TODO(detail)
 * @Author Zj.Qu
 * @Date 2016-12-14 16:51:50
 */
public interface MeetingAttachBiz extends BaseOpBiz<MeetingAttach> {

	/**
	 * 用户选择覆盖时，将其他文件的删除标志置为1
	 * 
	 * @param meetingAttach
	 * @return
	 */
	int updateCoverOther(MeetingAttach meetingAttach);

}
