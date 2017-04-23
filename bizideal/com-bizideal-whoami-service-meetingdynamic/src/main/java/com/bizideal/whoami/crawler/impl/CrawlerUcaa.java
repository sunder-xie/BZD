package com.bizideal.whoami.crawler.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bizideal.whoami.crawler.Crawler;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicCrawler;

public class CrawlerUcaa implements Crawler{

	MeetingDynamicCrawler meetingDynamicCrawler;
	private Document document;
	private Map<String,String> cookies;
	private Connection connection;
	private String __VIEWSTATE="";
	private String __VIEWSTATEGENERATOR="";
	private String __EVENTVALIDATION="";
	private Integer pageIndex;
	private Integer pageSize;
	private Integer totalDynamicNum;
	private Integer dynamicIndex;
	
	@Override
	public boolean initCrawler(MeetingDynamicCrawler meetingDynamicCrawler) {
		try {
			this.meetingDynamicCrawler=meetingDynamicCrawler;
			this.pageIndex=0;
			this.pageSize=0;
			this.totalDynamicNum=0;
			this.dynamicIndex=0;
			
			this.initPage();
			//初始化页面的数据信息
			initCrawlerDate();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean consultDynamicNextPage(Integer start,Integer end) {
		try {
			if(start>0 && start>this.dynamicIndex){
				//初次查询的时候初始化当前需要查询的开始页码和动态指针
				this.pageIndex=start/this.pageSize+1;
				//如果是最后一条需要修正页码
				if(start%this.pageSize==0){
					this.pageIndex-=1;
				}
				//动态指针永远是当前页第一个的动态信息
				this.dynamicIndex=(this.pageIndex-1)*this.pageSize+1;
			}
			
			//当前的动态序列大于现在需要的结束指针
			if(end>0 && end<this.dynamicIndex){
				return false;
			}
			
			if(this.totalDynamicNum<=this.dynamicIndex && !this.dynamicIndex.equals(new Integer(0))){
				return false;
			}

			this.initPage();
			Map<String,String> map=installMap(__VIEWSTATE, __VIEWSTATEGENERATOR, __EVENTVALIDATION,"请输入关键词", this.pageIndex+"");
			this.document=this.connection.data(map).cookies(this.cookies)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
					.timeout(20000).post();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<MeetingDynamic> getDynamicNextPage(Integer start,Integer end) {
		List<MeetingDynamic> listDynamic = getAllMeetingDynamic(this.document, this.meetingDynamicCrawler);
		List<MeetingDynamic> listTemp = new ArrayList<MeetingDynamic>();
		if(null!=listDynamic && listDynamic.size()>0){
			//如果有数据则更新当前的数据信息
			start=start>this.dynamicIndex?(start-1)%this.pageSize+1:1;
			if(end==0){
				end=this.pageSize;
			}else{
				end=end>(this.pageIndex)*this.pageSize?this.pageSize:end%this.pageSize;
			}
			//防止越界
			end=end>listDynamic.size()?listDynamic.size():end;
			this.dynamicIndex+=listDynamic.size();
			this.pageIndex++;
			for(int i=start-1;i<end;++i){
				listTemp.add(listDynamic.get(i));
			}
			
			return listTemp;
		}
		return listTemp;
	}

	//此处可以抽取到抽象类中
	@Override
	public List<MeetingDynamicContent> getDynamicContentBatch(
			List<MeetingDynamic> list) {
		List<MeetingDynamicContent> listContent=new ArrayList<MeetingDynamicContent>();
		for(MeetingDynamic dynamic:list){
			listContent.add(this.getDynamicContent(dynamic));
		}
		return listContent;
	}

	@Override
	public MeetingDynamicContent getDynamicContent(MeetingDynamic dynamic) {
		MeetingDynamicContent dynamicContent=new MeetingDynamicContent();
		dynamicContent.setContent(analysisPageContent(dynamic).toString());
		dynamicContent.setDynamicId(dynamic.getDynamicId());
		dynamicContent.setMeetHallId(this.meetingDynamicCrawler.getMeetHallId());
		dynamicContent.setMeetingId(this.meetingDynamicCrawler.getMeetingId());
		
		return dynamicContent;
	}
	
	/**
	 * 解析有关会议动态的内容页面
	 * @param dynamic
	 * @return
	 */
	public StringBuilder analysisPageContent(MeetingDynamic dynamic){
		StringBuilder text = new StringBuilder();
		try {
			String url=this.meetingDynamicCrawler.getArticleUrl();
			Document document =null;
			url+=dynamic.getDynamicUrl();
			document = Jsoup.connect(url).get();
			List<Element> les=getCssFile(document);
			String text2=document.getElementsByClass("article").html();
			document = Jsoup.parse(text2);
			document = dealNavButtom(document);
			document = replaceTagA(document);
			document = replaceTagImg(document);
			document=dealEmptyP(document);
			//移除空的<p>标签和换行<p>标签
			for(Element e:les){
				text.append(e);
			}
			text.append(document.getElementsByTag("body").html());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return text;
	}
	
	private void initCrawlerDate(){
		try {
			Map<String,String> map=installMap(__VIEWSTATE, __VIEWSTATEGENERATOR, __EVENTVALIDATION,"请输入关键词", "1");
			this.document=this.connection.data(map).cookies(this.cookies)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
					.timeout(20000).post();
			Elements eles=this.document.getElementsByClass("newslist").first().getElementsByTag("a");
			this.pageIndex=1;
			this.pageSize=eles.size();
			this.dynamicIndex=1;
			this.totalDynamicNum=Integer.parseInt(this.document.getElementById("ContentPlaceHolder1_lblRecordCount").html());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 处理css的问题
	 * @param document
	 * @return
	 */
	private List<Element> getCssFile(Document document){
		List<Element> les=document.getElementsByTag("link");
		List<Element> temps=new ArrayList<Element>();
		String href="";
		for(Element e:les){
			if(e.attr("href").startsWith("www.") || e.attr("href").startsWith("http://www.")){
			}else{
				href=e.attr("href");
				href="http://www.tech.net.cn/web/"+href;
				e.attr("href", href);
			}
			temps.add(e);
		}
		return temps;
	}
	
	/**
	 * 处理控标签P
	 * @param document
	 * @return
	 */
	private Document dealEmptyP(Document document){
		Element article=document.getAllElements().first();
		List<Element> les=article.getElementsByTag("p");
		for(Element e:les){
			if((e.html().replace("<br>", "").replace("&nbsp;", "").trim()).equals("")){
				e.remove();
			}
		}
		
		return document;
	}
	/**
	 * 处理图片路径问题
	 * @param document
	 * @return
	 */
	private Document replaceTagImg(Document document){
		List<Element> les=document.getElementsByTag("img");
		String src="";
		for(Element e:les){
			if(e.tagName()=="img"){
				if(e.attr("src").startsWith("www.") || e.attr("src").startsWith("http://www.")){
				}else if(e.attr("src").startsWith("../")){
					e.attr("src","http://www.tech.net.cn/web/"+e.attr("src"));
				}else{
					src=e.attr("src");
					src="http://www.tech.net.cn/"+src;
					e.attr("src", src);
				}
			}
		}
		return document;
	}
	
	/**
	 * 删除导航条链接
	 * @param document
	 * @return
	 */
	private Document dealNavButtom(Document document){
		Element element=document.getElementById("ContentPlaceHolder1_currentpage").parent();
		element=element.nextElementSibling();
		Element temp=null;
		while(element!=null){
			temp=element;
			element=element.nextElementSibling();
			temp.remove();
		}
		return document;
	}
	
	/**
	 * 处理a标签的路径问题
	 * @param document
	 * @return
	 */
	private Document replaceTagA(Document document){
		List<Element> les=document.getElementsByTag("a");
		String href="";
		for(Element e:les){
//		
		}
		return document;
	}
	
	/**
	 * 获取页面的动态信息
	 * @param document
	 * @param crawlerInfo
	 * @return
	 */
	private List<MeetingDynamic> getAllMeetingDynamic(Document document,MeetingDynamicCrawler crawlerInfo){
		Element lrp=document.getElementsByClass("newslist").first();
		List<Element> les=lrp.getElementsByTag("a");
		String href="";
		Element parent=null;
		List<MeetingDynamic> lds=new ArrayList<MeetingDynamic>();
		MeetingDynamic dy=null;
		String title="";
		try{
			for(Element e:les){
				parent=e.parent();
				if(!parent.getElementsByTag("i").isEmpty() && parent.tagName().equals("li")
						&& !parent.getElementsByTag("a").isEmpty()){
					dy=new MeetingDynamic();
					dy.setDynamicUrl(parent.getElementsByTag("a").attr("href"));
					title=e.html();
					dy.setDynamicTitle(title);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(parent.getElementsByTag("i").html().replaceAll(" ", ""));
					dy.setCreateTime(date.getTime());
					//增加其他东西crawlerInfo的
					dy.setMeetHallId(crawlerInfo.getMeetHallId());
					dy.setMeetingId(crawlerInfo.getMeetingId());
					dy.setUserId(crawlerInfo.getUserId());
					
					dy.setType(1);
					dy.setDelFlag("2");
					lds.add(dy);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<MeetingDynamic>();
		}
		return lds;
	}
	
	/**
	 * 获取页面中需要的参数信息
	 * @param __VIEWSTATE
	 * @param __VIEWSTATEGENERATOR
	 * @param __EVENTVALIDATION
	 * @param find_txt
	 * @param pageNum
	 * @return
	 */
	private Map<String,String> installMap(String __VIEWSTATE,String __VIEWSTATEGENERATOR,String __EVENTVALIDATION,String find_txt,String pageNum){
		Map<String,String> map=new HashMap<String,String>();
		map.put("__VIEWSTATE", __VIEWSTATE);
		map.put("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
		map.put("__EVENTVALIDATION", __EVENTVALIDATION);
		map.put("ctl00$find_txt", find_txt);
		map.put("ctl00$find_bt", "");
		map.put("ctl00$ContentPlaceHolder1$pagenum", pageNum);
		map.put("ctl00$ContentPlaceHolder1$Button1", "跳转");
		
		return map;
	}
	
	private void initPage() throws IOException{
		this.connection=Jsoup.connect(this.meetingDynamicCrawler.getUrl());
		this.cookies=this.connection.execute().cookies();
		this.document=this.connection.post();
		//保存页面的必要数据
		this.__VIEWSTATE=document.getElementsByAttributeValue("name", "__VIEWSTATE").val();
		this.__VIEWSTATEGENERATOR=document.getElementsByAttributeValue("name", "__VIEWSTATEGENERATOR").val();
		this.__EVENTVALIDATION=document.getElementsByAttributeValue("name", "__EVENTVALIDATION").val();
	}
}
