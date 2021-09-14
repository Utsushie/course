package com.course.business.Controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.SectionDto;
import com.course.server.service.SectionService;
import com.course.server.util.ValidatorUtil;
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
@RequestMapping("/admin/section")
public class SectionController {

	private static Logger logger = LoggerFactory.getLogger(SectionController.class);

	private final String BUSINESS_NAME = "小节";

	@Resource
	private SectionService sectionService;

	/**
	 * 获取课程列表
	 * @param sectionDto
	 * @return
	 */
	@RequestMapping(value="/getSectionList")
	public Object getSectionList(SectionDto sectionDto){
		Object object = sectionService.getSectionList(sectionDto);
		return object;
	}

	/**
	 * 保存课程信息
	 * @param sectionDto
	 * @return
	 */
	@RequestMapping(value="/saveSection",method = {RequestMethod.POST})
	public Object saveSection(@RequestBody SectionDto sectionDto){
		logger.info("saveSection:" + JSONObject.toJSONString(sectionDto));

		//ValidatorUtil.require(sectionDto.getName(),"名称");
		//ValidatorUtil.require(sectionDto.getCourseId(),"课程ID");
		//ValidatorUtil.length(sectionDto.getCourseId(),"课程ID",1,8);

		Object object = sectionService.saveSection(sectionDto);
		return object;
	}

	/**
	 * 获取课程信息
	 * @param sectionDto
	 * @return
	 */
	@RequestMapping(value="/getSection")
	public Object getSection(SectionDto sectionDto){
		Object object = sectionService.getSection(sectionDto);
		return object;
	}

	/**
	 * 删除课程信息
	 * @param sectionDto
	 * @return
	 */
	@RequestMapping(value="/deleteSection")
	public Object deleteSection(SectionDto sectionDto){
		Object object = sectionService.deleteSection(sectionDto);
		return object;
	}

}
