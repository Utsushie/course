package com.course.server.service;

import com.course.server.dto.SectionDto;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
public interface SectionService {

	/* 获取课程列表 */
	public Object getSectionList(SectionDto sectionDto);

	/* 获取课程信息 */
	public Object getSection(SectionDto sectionDto);

	/* 保存课程 */
	public Object saveSection(SectionDto sectionDto);

	/* 删除课程 */
	public Object deleteSection(SectionDto sectionDto);

}
