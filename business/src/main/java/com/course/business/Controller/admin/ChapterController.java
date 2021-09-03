package com.course.business.Controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
		Object object = chapterService.getChapterList(chapterDto);
		return object;
	}

	/**
	 * 保存课程信息
	 * @param chapterDto
	 * @return
	 */
	@RequestMapping(value="/saveChapter",method = {RequestMethod.POST})
	public Object saveChapter(@RequestBody ChapterDto chapterDto){
		logger.info("saveChapter:" + JSONObject.toJSONString(chapterDto));
		Object object = chapterService.saveChapter(chapterDto);
		return object;
	}

	/**
	 * 获取课程信息
	 * @param chapterDto
	 * @return
	 */
	@RequestMapping(value="/getChapter")
	public Object getChapter(ChapterDto chapterDto){
		Object object = chapterService.getChapter(chapterDto);
		return object;
	}

}
