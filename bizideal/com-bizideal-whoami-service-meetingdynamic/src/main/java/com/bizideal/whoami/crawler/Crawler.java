package com.bizideal.whoami.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicCrawler;


/**
 * 爬虫虚拟类
 * @ClassName Crawler
 * @Description TODO(detail)
 * @Author yt.Cui
 * @Date 2016年12月15日
 */
public interface Crawler {
	
//	public MeetingDynamicCrawler meetingDynamicCrawler;
	
	/**
	 * 根据爬虫信息初始化爬虫实例
	 * @param crawlerInfo
	 * @return
	 */
	boolean initCrawler(MeetingDynamicCrawler meetingDynamicCrawler);
	
	/**
	 * 咨询是否还有下一页内容
	 * @return
	 */
	boolean consultDynamicNextPage(Integer start, Integer end);
	
	/**
	 * 获取下一页的动态信息数据
	 * @return
	 */
	List<MeetingDynamic> getDynamicNextPage(Integer start, Integer end);
	
	/**
	 * 根据动态信息列表获取动态列表
	 * @param list
	 * @return
	 */
	List<MeetingDynamicContent> getDynamicContentBatch(List<MeetingDynamic> list);
//	{
//		List<MeetingDynamicContent> listContent=new ArrayList<MeetingDynamicContent>();
//		for(MeetingDynamic dynamic:list){
//			listContent.add(this.getDynamicContent(dynamic));
//		}
//		return listContent;
//	}
	
	/**
	 * 根据动态信息获取动态内容
	 * @param dynamic
	 * @return
	 */
	MeetingDynamicContent getDynamicContent(MeetingDynamic dynamic);
}
