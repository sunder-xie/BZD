package com.bizideal.whoami.facade;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicCrawler;
import com.bizideal.whoami.meetingdynamic.facade.MeetingDynamicCrawlerFacade;
import com.bizideal.whoami.service.MeetingDynamicCrawlerBiz;

/**
 * 会议动态的爬虫相关业务逻辑
 * @ClassName MeetingDynamicCrawlerFacadeImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月18日
 */
@Component("meetingDynamicCrawlerFacade")
public class MeetingDynamicCrawlerFacadeImpl implements MeetingDynamicCrawlerFacade{

	@Autowired
	MeetingDynamicCrawlerBiz meetingDynamicCrawlerBiz;
	@Override
	public Map<String, Object> climbAllDynamics(MeetingDynamicCrawler crawlerInfo) {
		return meetingDynamicCrawlerBiz.climbAllDynamics(crawlerInfo);
	}

	@Override
	public Map<String, Object> climbCurrentDynamics(
			MeetingDynamicCrawler crawlerInfo, Integer pageStart,
			Integer pageEnd) {
		return meetingDynamicCrawlerBiz.climbCurrentDynamics(crawlerInfo, pageStart, pageEnd);
	}

	@Override
	public Map<String, Object> addCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		return meetingDynamicCrawlerBiz.addCrawlerUrlToMeeting(meetingDynamicCrawler);
	}

	@Override
	public Map<String, Object> deleteCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		return meetingDynamicCrawlerBiz.deleteCrawlerUrlToMeeting(meetingDynamicCrawler);
	}

	@Override
	public Map<String, Object> updateCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		return meetingDynamicCrawlerBiz.updateCrawlerUrlToMeeting(meetingDynamicCrawler);
	}

	@Override
	public Map<String, Object> selectCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		return meetingDynamicCrawlerBiz.selectCrawlerUrlToMeeting(meetingDynamicCrawler);
	}

}
