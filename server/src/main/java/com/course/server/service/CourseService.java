package com.course.server.service;

import java.util.List;
import com.course.server.dto.CourseDto;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
public interface CourseService {

	/* 获取课程列表 */
	public Object getCourseList(CourseDto courseDto);

	/* 获取课程信息 */
	public Object getCourse(CourseDto courseDto);

	/* 保存课程 */
	public Object saveCourse(CourseDto courseDto);

	/* 删除课程 */
	public Object deleteCourse(CourseDto courseDto);

}
