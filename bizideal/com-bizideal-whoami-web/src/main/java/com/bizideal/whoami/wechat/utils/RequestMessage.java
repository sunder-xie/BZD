package com.bizideal.whoami.wechat.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 微信请求消息工具类
 * 
 * @author Administrator
 * 
 */
public class RequestMessage {

	/**
	 * 普通请求消息==============================================================
	 */

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

	/**
	 * 请求消息类型：小视频
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

	/**
	 * 事件请求消息==============================================================
	 */

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅 "用户未关注时，进行关注后的事件推送")
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：SCAN(扫描带参数二维码事件 用户已关注时的事件推送)
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";

	/**
	 * 事件类型：LOCATION(上报地理位置事件)
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件-点击菜单跳转链接时的事件推送)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件-点击菜单拉取消息时的事件推送)
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";

	/**
	 * 事件类型：MASSSENDJOBFINISH(群发任务事件推送群发结果)
	 */
	public static final String MASSS_END_JOB_FINISH = "MASSSENDJOBFINISH";

	/**
	 * 解析微信发来的请求request（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在HashMap 中
		Map<String, String> map = new HashMap<String, String>();
		// 从request 中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml 根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			Object obj = parse(e);
			map.put(e.getName(), obj.toString());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 遍历所有的XML子元素
	 * 
	 * @param root
	 * @return
	 */
	public static Object parse(Element root) {
		List<?> elements = root.elements();
		if (elements.size() == 0) {
			// 没有子元素
			return root.getTextTrim();
		} else {
			// 有子元素
			String prev = null;
			boolean guess = true; // 默认按照数组处理

			Iterator<?> iterator = elements.iterator();
			while (iterator.hasNext()) {
				Element elem = (Element) iterator.next();
				String name = elem.getName();
				if (prev == null) {
					prev = name;
				} else {
					guess = name.equals(prev);
					break;
				}
			}
			iterator = elements.iterator();
			if (guess) {
				List<Object> data = new ArrayList<Object>();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();
					data.add(parse(elem));
				}
				return data;
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				while (iterator.hasNext()) {
					Element elem = (Element) iterator.next();
					((Map<String, Object>) data).put(elem.getName(),
							parse(elem));
				}
				return data;
			}
		}
	}

	/**
	 * 计算采用utf-8 编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8 编码时占3 个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

	/**
	 * 判断是否是QQ 表情
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;
		// 判断QQ 表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\("
				+ "|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D"
				+ "|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x"
				+ "|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)"
				+ "|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>"
				+ "|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart"
				+ "|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift"
				+ "|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love"
				+ "|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn"
				+ "|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/**
	 * emoji 表情转换(hex -> utf-16)
	 *
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

}
