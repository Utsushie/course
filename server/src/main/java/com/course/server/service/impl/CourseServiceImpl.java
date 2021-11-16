package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.dto.CourseDto;
import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.service.CourseService;
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
import com.course.server.enums.OptionEnum;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
@Service
public class CourseServiceImpl implements CourseService{

	private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Resource
	private CourseMapper courseMapper;

	@Override
	public Object getCourseList(CourseDto courseDto) {
		logger.info("getCourseList:" + JSONObject.toJSONString(courseDto));
		PageHelper.startPage(courseDto.getPage(),courseDto.getSize());
		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIsDelEqualTo(0);
		courseExample.setOrderByClause("sort ASC");
		List<Course> courseList = courseMapper.selectByExample(courseExample);
		logger.info("getCourseList--->selectcourseList courseList:" + JSONObject.toJSONString(courseList));
		PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
		PageDto pageDto = new PageDto();
		pageDto.setTotal(pageInfo.getTotal());
		List<CourseDto> courseDtosList = new ArrayList<CourseDto>();
		for(int i = 0, l = courseList.size(); i < l;i++){
			Course course = courseList.get(i);
			CourseDto courseDtoInfo = new CourseDto();
			BeanUtils.copyProperties(course,courseDtoInfo);
			courseDtosList.add(courseDtoInfo);
		}
		pageDto.setList(courseDtosList);
		logger.info("getCourseList--->return pageDto:" + JSONObject.toJSONString(pageDto));
		return pageDto;
	}

	@Override
	public Object getCourse(CourseDto courseDto) {
		logger.info("getCourse:" + JSONObject.toJSONString(courseDto));
		Result returnResult = new Result();
		Course course = courseMapper.selectByPrimaryKey(courseDto.getId());
		returnResult.setCode(100);
		returnResult.setMsg("操作成功");
		returnResult.setData(course);
		return returnResult;
	}

	@Override
	public Object saveCourse(CourseDto courseDto) {
		logger.info("saveCourse--->courseDto:" + JSONObject.toJSONString(courseDto));
		Course course = new Course();
		BeanUtils.copyProperties(courseDto,course);
		course.setUpdatedTime(new Date());
		course.setCreatedBy("Yx");
		course.setUpdatedBy("Yx");

		//校验课程ID是否存在
		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIdEqualTo(courseDto.getId()).andIsDelEqualTo(0);

		//判断id是否为空(spring5.3之后启用StringUtils的isEmpty方法)
		if(OptionEnum.EDIT.getCode().equals(courseDto.getOptionType())){
			//规避当前ID
			courseExample.getOredCriteria().get(0).andIdNotEqualTo(courseDto.getId());;
			//课程ID是否已经存在
			if(this.checkExistedCourseId(courseExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//修改课程信息
				/*courseExample = new CourseExample();
				courseExample.createCriteria().andIdEqualTo(courseDto.getId());*/
				int count = courseMapper.updateByPrimaryKeySelective(course);
			}
		}else{
			//课程ID是否已经存在
			if(this.checkExistedCourseId(courseExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//保存课程信息
				course.setId(UuidUtil.getShortUuid());
				course.setCreatedTime(new Date());
				course.setIsDel(0);
				courseMapper.insert(course);
			}
		}
		return ResultUtil.success();
	}

	//检查课程ID是否已经存在
	public boolean checkExistedCourseId(CourseExample courseExample){
		boolean resultFlag = false;
		//查询课程ID是否被使用
		List<Course> existedCourseList = courseMapper.selectByExample(courseExample);
		if(!CollectionUtils.isEmpty(existedCourseList)){
			resultFlag = true;
		}
		return resultFlag;
	}

	@Override
	public Object deleteCourse(CourseDto courseDto) {
		logger.info("deleteCourse ---> courseDto:" + JSONObject.toJSONString(courseDto));
		Course course = this.copyProperties(courseDto);
		course.setIsDel(1);
		course.setUpdatedTime(new Date());
		course.setUpdatedBy("Yx");
		int count = courseMapper.updateByPrimaryKeySelective(course);
		return ResultUtil.success();
	}

	//实体类转换
	public Course copyProperties(CourseDto courseDto){
		Course course = new Course();
		BeanUtils.copyProperties(courseDto,course);
		return course;
	}

}