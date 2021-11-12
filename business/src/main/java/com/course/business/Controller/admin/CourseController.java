package com.course.business.Controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.CourseDto;
import com.course.server.service.CourseService;
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
@RequestMapping("/admin/course")
public class CourseController {

	private static Logger logger = LoggerFactory.getLogger(CourseController.class);

	private final String BUSINESS_NAME = "课程";

	@Resource
	private CourseService courseService;

	/**
	 * 获取课程列表
	 * @param courseDto
	 * @return
	 */
	@RequestMapping(value="/getCourseList")
	public Object getCourseList(CourseDto courseDto){
		Object object = courseService.getCourseList(courseDto);
		return object;
	}

	/**
	 * 保存课程信息
	 * @param courseDto
	 * @return
	 */
	@RequestMapping(value="/saveCourse",method = {RequestMethod.POST})
	public Object saveCourse(@RequestBody CourseDto courseDto){
		logger.info("saveCourse:" + JSONObject.toJSONString(courseDto));

		//ValidatorUtil.require(courseDto.getName(),"名称");
		//ValidatorUtil.require(courseDto.getCourseId(),"课程ID");
		//ValidatorUtil.length(courseDto.getCourseId(),"课程ID",1,8);

		Object object = courseService.saveCourse(courseDto);
		return object;
	}

	/**
	 * 获取课程信息
	 * @param courseDto
	 * @return
	 */
	@RequestMapping(value="/getCourse")
	public Object getCourse(CourseDto courseDto){
		Object object = courseService.getCourse(courseDto);
		return object;
	}

	/**
	 * 删除课程信息
	 * @param courseDto
	 * @return
	 */
	@RequestMapping(value="/deleteCourse")
	public Object deleteCourse(CourseDto courseDto){
		Object object = courseService.deleteCourse(courseDto);
		return object;
	}

}
