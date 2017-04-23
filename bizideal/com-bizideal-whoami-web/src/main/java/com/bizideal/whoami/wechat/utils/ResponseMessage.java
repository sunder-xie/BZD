package com.bizideal.whoami.wechat.utils;

import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bizideal.whoami.weixin.dto.response.Article;
import com.bizideal.whoami.weixin.dto.response.ImageMessage;
import com.bizideal.whoami.weixin.dto.response.MediaId;
import com.bizideal.whoami.weixin.dto.response.NewsMessage;
import com.bizideal.whoami.weixin.dto.response.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
/**
 * 回复文本、图片 、图文消息的组装工具类
 * @author pc
 *
 */
public class ResponseMessage {

	/**
	 * 返回消息==============================================================
	 */
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：语音
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 返回消息类型：图片
	 */
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 返回消息类型：视频
	 */
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	
	/**
	 * 扩展xstream，使其支持CDATA 块
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml 节点的转换都增加CDATA 标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 返回消息转换成xml==============================================================
	 */
	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessage textMessage)
			throws Exception {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成xml
	 * 
	 * @param imageMessage
	 *            声音和图片消息对象
	 * @return xml
	 */
	public static String imageMessageToXml(ImageMessage imageMessage)
			throws Exception {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage)
			throws Exception {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}

	
    /**
     * 回复文本消息  组装文本消息
     * @param respContent
     * @param messageMap
     * @return
     * @throws Exception
     */
	public static String textMessage(String respContent,
			Map<String, String> messageMap) throws Exception {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(messageMap.get("FromUserName"));
		textMessage.setFromUserName(messageMap.get("ToUserName"));
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(ResponseMessage.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		textMessage.setContent(respContent);
		// 明码模式
		String replayMsg = ResponseMessage.textMessageToXml(textMessage);
		// 安全模式
		// if(messageMap.get("flag").equals("true")){
		// replayMsg = SignUtil.ecryptMsg(replayMsg,
		// messageMap.get("TimeStamp"), messageMap.get("Nonce"));
		// }
		return replayMsg;
	}
    /**
     * 回复图片消息  图片消息组装
     * @param mediaId
     * @param messageMap
     * @return
     * @throws Exception
     */
	public static String imageMessage(MediaId mediaId,
			Map<String, String> messageMap) throws Exception {

		// 创建图片消息
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setToUserName(messageMap.get("FromUserName"));
		imageMessage.setFromUserName(messageMap.get("ToUserName"));
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setMsgType(ResponseMessage.RESP_MESSAGE_TYPE_IMAGE);
		imageMessage.setFuncFlag(0);
		imageMessage.setImage(mediaId);
		// 明码模式
		String replayMsg = ResponseMessage.imageMessageToXml(imageMessage);
		// 安全模式
		// if(messageMap.get("flag").equals("true")){
		// replayMsg = SignUtil.ecryptMsg(replayMsg,
		// messageMap.get("TimeStamp"), messageMap.get("Nonce"));
		// }
		return replayMsg;
	}
    /**
     * 回复图文消息  图文消息组装
     * @param articles
     * @param messageMap
     * @return
     * @throws Exception
     */
	public static String newsMessage(List<Article> articles,
			Map<String, String> messageMap) throws Exception {
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(messageMap.get("FromUserName"));
		newsMessage.setFromUserName(messageMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ResponseMessage.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		newsMessage.setArticleCount(articles.size());
		newsMessage.setArticles(articles);
		// 明码模式
		String replayMsg = ResponseMessage.newsMessageToXml(newsMessage);
		// 安全模式
		// if(messageMap.get("flag").equals("true")){
		// replayMsg = SignUtil.ecryptMsg(replayMsg,
		// messageMap.get("TimeStamp"), messageMap.get("Nonce"));
		// }
		return replayMsg;
	}

}
