package com.bizideal.whoami.crawler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bizideal.whoami.crawler.impl.CrawlerUcaa;


public class CrawlerFactory {

	private static Map<String,Crawler> factory=new HashMap<String,Crawler>();
	
	public static Crawler getCrawlerById(String crawlerId){
		Crawler crawler=factory.get(crawlerId);
		if(null==crawler){
			if(installCrawlerById(crawlerId)){
				return factory.get(crawlerId);
			}
		}
		return crawler;
	}
	
	private static boolean installCrawlerById(String crawlerId){
		//这里暂时简单处理
		Properties prop =  new  Properties();
		InputStream in = CrawlerFactory.class.getClassLoader().getResourceAsStream("crawlerImpl.properties");
		
		try{
			prop.load(in);
			String pro="";
			for(Object s:prop.keySet()){
				pro=(String) prop.get(s);

				if(s.toString().equals(crawlerId)){
					Class clazz=Class.forName(pro);
					factory.put(crawlerId, (Crawler) clazz.newInstance());
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
}
