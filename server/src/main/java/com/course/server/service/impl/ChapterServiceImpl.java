package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.dto.ChapterDto;
import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.service.ChapterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
@Service
public class ChapterServiceImpl implements ChapterService{

	private static Logger logger = LoggerFactory.getLogger(ChapterServiceImpl.class);

	@Resource
	private ChapterMapper chapterMapper;

	@Override
	public Object getChapterList(ChapterDto chapterDto) {
		PageHelper.startPage(chapterDto.getPage(),chapterDto.getSize());
		ChapterExample chapterExample = new ChapterExample();
		List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
		logger.info("getChapterList--->selectchapterList chapterList:" + JSONObject.toJSONString(chapterList));
		PageInfo<Chapter> pageInfo = new PageInfo<Chapter>(chapterList);
		PageDto pageDto = new PageDto();
		pageDto.setTotal(pageInfo.getTotal());
		List<ChapterDto> chapterDtosList = new ArrayList<ChapterDto>();
		for(int i = 0, l = chapterList.size(); i < l;i++){
			Chapter chapter = chapterList.get(i);
			ChapterDto chapterDtoInfo = new ChapterDto();
			BeanUtils.copyProperties(chapter,chapterDtoInfo);
			chapterDtosList.add(chapterDtoInfo);
		}
		pageDto.setList(chapterDtosList);
		logger.info("getChapterList--->return pageDto:" + JSONObject.toJSONString(pageDto));
		return pageDto;
	}
}
