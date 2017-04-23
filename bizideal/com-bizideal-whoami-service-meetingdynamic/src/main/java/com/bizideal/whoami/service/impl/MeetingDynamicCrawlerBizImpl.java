package com.bizideal.whoami.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bizideal.whoami.crawler.Crawler;
import com.bizideal.whoami.crawler.CrawlerFactory;
import com.bizideal.whoami.croe.service.impl.BaseBizImpl;
import com.bizideal.whoami.mapper.MeetingDynamicContentMapper;
import com.bizideal.whoami.mapper.MeetingDynamicCrawlerMapper;
import com.bizideal.whoami.mapper.MeetingDynamicMapper;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicCrawler;
import com.bizideal.whoami.meetingdynamic.enums.MeetingDynamicCrawlerEnums;
import com.bizideal.whoami.service.MeetingDynamicCrawlerBiz;

/**
 * 爬虫业务逻辑
 * @ClassName MeetingDynamicCrawlerBizImpl
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月18日
 */
@Service("meetingDynamicCrawlerBiz")
public class MeetingDynamicCrawlerBizImpl extends BaseBizImpl<MeetingDynamicCrawler> implements MeetingDynamicCrawlerBiz{


	private static final Logger LOGGER = LoggerFactory.getLogger(MeetingDynamicCrawlerBizImpl.class);
	
	@Autowired
	private MeetingDynamicMapper meetingDynamicMapper;
	
	@Autowired
	private MeetingDynamicContentMapper meetingDynamicContentMapper;
	
	@Autowired
	private MeetingDynamicCrawlerMapper meetingDynamicCrawlerMapper;
	@Override
	public Map<String, Object> climbAllDynamics(MeetingDynamicCrawler crawlerInfo) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			crawlerInfo=meetingDynamicCrawlerMapper.selectByPrimaryKey(crawlerInfo.getCrawlerId());
			if(null==crawlerInfo || crawlerInfo.getCrawlerFlag().equals(0)){
				map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_INSERTCRAWLER_NOTOPEN);
				return map;
			}
			
			Crawler crawler=CrawlerFactory.getCrawlerById(crawlerInfo.getCrawlerId()+"");
			MeetingDynamic dynamic = new MeetingDynamic();
			dynamic.setMeetHallId(crawlerInfo.getMeetHallId());
			dynamic.setMeetingId(crawlerInfo.getMeetingId());
			MeetingDynamicContent dynamicContent=new MeetingDynamicContent();
			dynamicContent.setMeetHallId(crawlerInfo.getMeetHallId());
			dynamicContent.setMeetingId(crawlerInfo.getMeetingId());
			List<MeetingDynamic> list=null;
			List<MeetingDynamicContent> listContent=null;
			
			//首先删除所有数据库的动态信息
			meetingDynamicMapper.deleteDynamicBatch(dynamic);
			//首先删除所有数据库的动态内容
			meetingDynamicContentMapper.deleteDynamicContentBatch(dynamicContent);
			
			if(crawler.initCrawler(crawlerInfo)){
				while(crawler.consultDynamicNextPage(0, 0)){
					list=crawler.getDynamicNextPage(0, 0);
					meetingDynamicMapper.addBatchDynamic(list);
					list=meetingDynamicMapper.selectCurrentDynamics(dynamic);
					listContent=crawler.getDynamicContentBatch(list);
					meetingDynamicContentMapper.addBatchDynamic(listContent);
					meetingDynamicMapper.updateBatchDynamic(list);
				}
			}
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_ALLDYNAMIC_SUCCESS);
			//////
//			for(int i=1;i<=crawler.getPageNum();++i){
//				if(crawler.initPageDocument(i)){
//					list=crawler.getDynamicByDocument();
//					meetingDynamicMapper.addBatchDynamic(list);
//					list=meetingDynamicMapper.selectCurrentDynamics(dynamic);
//					listContent=crawler.getDynamicContentBatch(list);
//					meetingDynamicContentMapper.addBatchDynamic(listContent);
//					meetingDynamicMapper.updateBatchDynamic(list);
//					map.put("status",200);
//					map.put("msg", "从网页抓取动态信息完成!");
//				}
//			}
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> climbCurrentDynamics(
			MeetingDynamicCrawler crawlerInfo, Integer start, Integer end) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			crawlerInfo=meetingDynamicCrawlerMapper.selectByPrimaryKey(crawlerInfo.getCrawlerId());
			if(null==crawlerInfo || crawlerInfo.getCrawlerFlag().equals(0)){
				map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_INSERTCRAWLER_NOTOPEN);
				return map;
			}
			Crawler crawler=CrawlerFactory.getCrawlerById(crawlerInfo.getCrawlerId()+"");
			MeetingDynamic dynamic = new MeetingDynamic();
			dynamic.setMeetHallId(crawlerInfo.getMeetHallId());
			dynamic.setMeetingId(crawlerInfo.getMeetingId());
			List<MeetingDynamic> list=null;
			List<MeetingDynamicContent> listContentInsert=null;
			List<MeetingDynamicContent> listContentUpdate=null;
			
			if(crawler.initCrawler(crawlerInfo)){
				while(crawler.consultDynamicNextPage(start, end)){
					list=crawler.getDynamicNextPage(start, end);
					//判断是否有重复的动态
					List<MeetingDynamic> exsitList=new ArrayList<MeetingDynamic>();
					Map<String,MeetingDynamic> exsitMap = new HashMap<String, MeetingDynamic>();
					exsitList=meetingDynamicMapper.selectDynamicByUrls(list, crawlerInfo.getMeetingId());
					List<MeetingDynamic> lastInsert=new ArrayList<MeetingDynamic>();
					if(null!=exsitList && exsitList.size()>0){
						for(MeetingDynamic md:exsitList){
							exsitMap.put(md.getDynamicUrl(), md);
						}
						for(MeetingDynamic mdc:list){
							if(null!=exsitMap.get(mdc.getDynamicUrl())){
								mdc.setDynamicId(exsitMap.get(mdc.getDynamicUrl()).getDynamicId());
							}else if(null==exsitMap.get(mdc.getDynamicUrl())){
								lastInsert.add(mdc);
							}
						}
					}else{
						lastInsert=list;
					}

					//批量插入动态信息
					if(null!=lastInsert && lastInsert.size()>0)
						meetingDynamicMapper.addBatchDynamic(lastInsert);
					lastInsert=meetingDynamicMapper.selectCurrentDynamics(dynamic);
					//插入新的动态内容
					if(null!=lastInsert && lastInsert.size()>0){
						listContentInsert=crawler.getDynamicContentBatch(lastInsert);
						meetingDynamicContentMapper.addBatchDynamic(listContentInsert);
					}
					
					//更新动态信息
					for(MeetingDynamic md:exsitList){
						if(meetingDynamicMapper.updateByPrimaryKey(md)<=0){
							map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_CURRENTDYNAMIC_EXCEPTION);
							return map;
						}
					}
					//更新已有的动态内容
					listContentUpdate=crawler.getDynamicContentBatch(exsitList);
					for(MeetingDynamicContent mdc:listContentUpdate){
						Example example=new Example(MeetingDynamicContent.class);
						example.createCriteria().andCondition("dynamic_id=", mdc.getDynamicId());
						if(meetingDynamicContentMapper.updateByExampleSelective(mdc, example)!=1){
							map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_CURRENTDYNAMIC_EXCEPTION);
						}
					}
					
					//更新动态信息的状态
					list=meetingDynamicMapper.selectCurrentDynamics(dynamic);
					if(null!=list && list.size()>0)
						meetingDynamicMapper.updateBatchDynamic(list);
					map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_CURRENTDYNAMIC_SUCCESS);
				}
			}
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> addCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			MeetingDynamicCrawler dynamicCrawler=new MeetingDynamicCrawler();
			dynamicCrawler.setMeetHallId(meetingDynamicCrawler.getMeetHallId());
			dynamicCrawler.setMeetingId(meetingDynamicCrawler.getMeetingId());
			List<MeetingDynamicCrawler> list=meetingDynamicCrawlerMapper.select(dynamicCrawler);
			if(null!=list && list.size()>0){
				map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_INSERTCRAWLER_REPEAT);
				return map;
			}
			meetingDynamicCrawler.setCrawlerFlag(0);
			meetingDynamicCrawlerMapper.insert(meetingDynamicCrawler);
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_INSERTCRAWLER_SUCCESS);
			map.put("entity", meetingDynamicCrawler);
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			meetingDynamicCrawlerMapper.deleteByPrimaryKey(meetingDynamicCrawler);
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_DELETE_SUCCESS);
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> updateCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			meetingDynamicCrawlerMapper.updateByPrimaryKeySelective(meetingDynamicCrawler);
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_UPDATE_SUCCESS);
			map.put("entity", meetingDynamicCrawler);
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, Object> selectCrawlerUrlToMeeting(
			MeetingDynamicCrawler meetingDynamicCrawler) {
		Map<String, Object> map=new HashMap<String,Object>();
		try{
			MeetingDynamicCrawler crawlerInfo = meetingDynamicCrawlerMapper.selectOne(meetingDynamicCrawler);
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.CRAWLER_SELECT_SUCCESS);
			map.put("entity", crawlerInfo);
		}catch(Exception e){
			map=this.makeMapMsg(map, MeetingDynamicCrawlerEnums.SYSTEM_EXCEPTION);
			e.printStackTrace();
			LOGGER.error(e.toString());
			return map;
		}
		return map;
	}
	
	
	private Map<String,Object> makeMapMsg(Map<String,Object> map,MeetingDynamicCrawlerEnums enums){
		map.put("code", enums.getCode());
		map.put("msg", enums.getMsg());
		return map;
	}
}
