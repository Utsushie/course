package com.course.server.service.impl;

import com.course.server.mapper.ChapterMapper;
import com.course.server.dto.ChapterDto;
import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
public class ChapterServiceImpl{

	/*@Resource
	private ChapterMapper chapterMapper;

	@Override
	public List<ChapterDto> getChapterList(ChapterDto chapterDto) {
		ChapterExample chapterExample = new ChapterExample();
		List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
		List<ChapterDto> chapterDtosList = new ArrayList<ChapterDto>();
		for(int i = 0, l = chapterList.size(); i < l;i++){
			Chapter chapter = chapterList.get(i);
			ChapterDto chapterDtoInfo = new ChapterDto();
			BeanUtils.copyProperties(chapter,chapterDtoInfo);
			chapterDtosList.add(chapterDtoInfo);
		}
		return chapterDtosList;
	}*/
}
