package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.dto.ChapterDto;
import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.service.ChapterService;
import com.course.server.util.UuidUtil;
import com.course.server.util.result.Result;
import com.course.server.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
		logger.info("getChapterList:" + JSONObject.toJSONString(chapterDto));
		PageHelper.startPage(chapterDto.getPage(),chapterDto.getSize());
		ChapterExample chapterExample = new ChapterExample();
		chapterExample.createCriteria().andIsDelEqualTo(0);
		chapterExample.setOrderByClause("updated_time DESC");
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

	@Override
	public Object getChapter(ChapterDto chapterDto) {
		logger.info("getChapter:" + JSONObject.toJSONString(chapterDto));
		Result returnResult = new Result();
		Chapter chapter = chapterMapper.selectByPrimaryKey(chapterDto.getId());
		returnResult.setCode(100);
		returnResult.setMsg("操作成功");
		returnResult.setData(chapter);
		return returnResult;
	}

	@Override
	public Object saveChapter(ChapterDto chapterDto) {
		logger.info("saveChapter--->chapterDto:" + JSONObject.toJSONString(chapterDto));
		Chapter chapter = new Chapter();
		BeanUtils.copyProperties(chapterDto,chapter);
		chapter.setUpdatedTime(new Date());
		chapter.setCreatedBy("Yx");
		chapter.setUpdatedBy("Yx");

		//校验课程ID是否存在
		ChapterExample chapterExample = new ChapterExample();
		chapterExample.createCriteria().andCourseIdEqualTo(chapterDto.getCourseId()).andIsDelEqualTo(0);

		//判断id是否为空(spring5.3之后启用StringUtils的isEmpty方法)
		if(StringUtils.hasLength(chapterDto.getId())){
			//规避当前ID
			chapterExample.createCriteria().andIdNotEqualTo(chapterDto.getId());;
			//课程ID是否已经存在
			if(this.checkExistedCourseId(chapterExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//修改课程信息
				/*chapterExample = new ChapterExample();
				chapterExample.createCriteria().andIdEqualTo(chapterDto.getId());*/
				int count = chapterMapper.updateByPrimaryKeySelective(chapter);
			}
		}else{
			//课程ID是否已经存在
			if(this.checkExistedCourseId(chapterExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//保存课程信息
				chapter.setId(UuidUtil.getShortUuid());
				chapter.setCreatedTime(new Date());
				chapter.setIsDel(0);
				chapterMapper.insert(chapter);
			}
		}
		return ResultUtil.success();
	}

	//检查课程ID是否已经存在
	public boolean checkExistedCourseId(ChapterExample chapterExample){
		boolean resultFlag = false;
		//查询课程ID是否被使用
		List<Chapter> existedChapterList = chapterMapper.selectByExample(chapterExample);
		if(!CollectionUtils.isEmpty(existedChapterList)){
			resultFlag = true;
		}
		return resultFlag;
	}

	@Override
	public Object deleteChapter(ChapterDto chapterDto) {
		logger.info("deleteChapter ---> chapterDto:" + JSONObject.toJSONString(chapterDto));
		Chapter chapter = this.copyProperties(chapterDto);
		chapter.setIsDel(1);
		chapter.setUpdatedTime(new Date());
		chapter.setUpdatedBy("Yx");
		int count = chapterMapper.updateByPrimaryKeySelective(chapter);
		return ResultUtil.success();
	}

	//实体类转换
	public Chapter copyProperties(ChapterDto chapterDto){
		Chapter chapter = new Chapter();
		BeanUtils.copyProperties(chapterDto,chapter);
		return chapter;
	}

}
