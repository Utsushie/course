package com.course.business.Controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
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

	@Resource
	private ChapterService chapterService;

	@RequestMapping(value="/getChapterList")
	public List<ChapterDto> getChapterList(ChapterDto chapterDto){
		return chapterService.getChapterList(chapterDto);
	}

}
