package com.course.server.service;

import com.course.server.dto.ChapterDto;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
public interface ChapterService {

	/* 获取课程列表 */
	public Object getChapterList(ChapterDto chapterDto);

	/* 获取课程信息 */
	public Object getChapter(ChapterDto chapterDto);

	/* 保存课程 */
	public Object saveChapter(ChapterDto chapterDto);

	/* 删除课程 */
	public Object deleteChapter(ChapterDto chapterDto);

}
