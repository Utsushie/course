package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
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
public interface ChapterService {

	public List<ChapterDto> getChapterList(ChapterDto chapterDto);

}
