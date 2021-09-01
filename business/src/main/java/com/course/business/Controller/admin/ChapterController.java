package com.course.business.Controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

	private static Logger logger = LoggerFactory.getLogger(ChapterController.class);

	@Resource
	private ChapterService chapterService;

	/**
	 * 获取课程列表
	 * @param chapterDto
	 * @return
	 */
	@RequestMapping(value="/getChapterList")
	public Object getChapterList(ChapterDto chapterDto){
		logger.info("getChapterList:" + JSONObject.toJSONString(chapterDto));
		return chapterService.getChapterList(chapterDto);
	}

}
