package com.bizideal.whoami.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bizideal.whoami.croe.fastdfs.FastDFSClient;
import com.bizideal.whoami.croe.service.impl.BaseOpBizImpl;
import com.bizideal.whoami.mapper.MeetingDynamicContentMapper;
import com.bizideal.whoami.mapper.MeetingDynamicImageMapper;
import com.bizideal.whoami.mapper.MeetingDynamicMapper;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamic;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicContent;
import com.bizideal.whoami.meetingdynamic.entity.MeetingDynamicImage;
import com.bizideal.whoami.service.MeetingDynamicBiz;

/**
 * @ClassName MeetingDynamicBizImpl
 * @Description TODO(detail)
 * @Author li.peng
 * @Date 2016-12-13 14:17:12
 */
@Service("meetingDynamicBiz")
public class MeetingDynamicBizImpl extends BaseOpBizImpl<MeetingDynamic> implements MeetingDynamicBiz {

	@Autowired
	private MeetingDynamicMapper meetingDynamicMapper;

	@Autowired
	private MeetingDynamicContentMapper meetingDynamicContentMapper;

	@Autowired
	private MeetingDynamicImageMapper meetingDynamicImageMapper;
	
	@Value("${fastdfsurl}")
	private String fastdfsurl;
	/**
	 * 保存会议动态信息
	 */
	public Integer save(MeetingDynamic meetingDynamic) {

		int dynamic = meetingDynamicMapper.insertSelective(meetingDynamic);
		int content = 0;
		int image = 0;
		if (dynamic > 0) {
			Integer dynamicId = meetingDynamic.getDynamicId();
			MeetingDynamicContent dynamicContent = new MeetingDynamicContent();
			dynamicContent.setContent(meetingDynamic.getContent());
			dynamicContent.setDynamicId(meetingDynamic.getDynamicId());
			content = meetingDynamicContentMapper.insertSelective(dynamicContent);
			Document doc = Jsoup.parseBodyFragment(meetingDynamic.getContent());
			Elements elementsByTag = doc.getElementsByTag("img");
			for (Element element : elementsByTag) {
				String attr = element.attr("src");
				String trim = attr.replaceAll(fastdfsurl, "").trim();
				MeetingDynamicImage dynamicImage = new MeetingDynamicImage();
				dynamicImage.setDynamicId(meetingDynamic.getDynamicId());
				dynamicImage.setImageUrl(trim);
				image = meetingDynamicImageMapper.insertSelective(dynamicImage);
			}
		}
		if (content > 0 || image > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 查询所有会议动态信息
	 */
	public List<MeetingDynamic> queryListByWhere(MeetingDynamic meetingDynamic) {

		List<MeetingDynamic> selectDynamic = meetingDynamicMapper.selectDynamic(meetingDynamic);
		return selectDynamic;
	}

	/**
	 * 查询单个会议动态信息
	 */
	public MeetingDynamic queryOne(MeetingDynamic meetingDynamic) {

		MeetingDynamic selectDynamicOne = meetingDynamicMapper.selectDynamicOne(meetingDynamic);
		return selectDynamicOne;
	}
	
	/**
	 * 删除会议动态信息
	 */
	public Integer deleteByWhere(MeetingDynamic meetingDynamic) {
		int dynamicId = meetingDynamic.getDynamicId();
		Integer dynamic = meetingDynamicMapper.deleteBydynamicId(meetingDynamic);
		MeetingDynamicContent meetingDynamicContent = new MeetingDynamicContent();
		meetingDynamicContent.setDynamicId(dynamicId);
		MeetingDynamicImage meetingDynamicImage = new MeetingDynamicImage();
		meetingDynamicImage.setDynamicId(dynamicId);
		List<MeetingDynamicImage> list = new ArrayList<MeetingDynamicImage>();
		List<MeetingDynamicImage> selectImageBydynamicId = meetingDynamicImageMapper
				.selectBydynamicId(meetingDynamicImage);
		for (MeetingDynamicImage meetingDynamicImage2 : selectImageBydynamicId) {
			int deleteFile = FastDFSClient.deleteFile(meetingDynamicImage2.getImageUrl());
		}
		Integer content = meetingDynamicContentMapper.deleteBydynamicId(meetingDynamicContent);
		Integer image = meetingDynamicImageMapper.deleteBydynamicId(meetingDynamicImage);
		if (dynamic > 0 && content > 0 && image > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 修改会议动态信息
	 */
	public Integer update(MeetingDynamic meetingDynamic) {

		int content = 0;
		int image = 0;
		int dynamic = meetingDynamicMapper.updateByPrimaryKeySelective(meetingDynamic);
		if (dynamic > 0) {
			
			MeetingDynamicContent dynamicContent = new MeetingDynamicContent();
			dynamicContent.setContent(meetingDynamic.getContent());
			dynamicContent.setDynamicId(meetingDynamic.getDynamicId());
			dynamicContent.setContentId(meetingDynamic.getContentId());
			content = meetingDynamicContentMapper.updateByPrimaryKeySelective(dynamicContent);

			MeetingDynamicImage dynamicImage = new MeetingDynamicImage();
			dynamicImage.setDynamicId(meetingDynamic.getDynamicId());
			Integer deleteBydynamicId = meetingDynamicImageMapper.deleteBydynamicId(dynamicImage);
			
			Document doc = Jsoup.parseBodyFragment(meetingDynamic.getContent());
			Elements elementsByTag = doc.getElementsByTag("img");
			for (Element element : elementsByTag) {
				String attr = element.attr("src");
				String trim = attr.replaceAll(fastdfsurl, "").trim();
				dynamicImage.setImageUrl(trim);
				image = meetingDynamicImageMapper.insertSelective(dynamicImage);
			}
		}
		if (content > 0 || image > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
