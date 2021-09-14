package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.dto.SectionDto;
import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.service.SectionService;
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
public class SectionServiceImpl implements SectionService{

	private static Logger logger = LoggerFactory.getLogger(SectionServiceImpl.class);

	@Resource
	private SectionMapper sectionMapper;

	@Override
	public Object getSectionList(SectionDto sectionDto) {
		logger.info("getSectionList:" + JSONObject.toJSONString(sectionDto));
		PageHelper.startPage(sectionDto.getPage(),sectionDto.getSize());
		SectionExample sectionExample = new SectionExample();
		sectionExample.createCriteria().andIsDelEqualTo(0);
		sectionExample.setOrderByClause("updated_time DESC");
		List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
		logger.info("getSectionList--->selectsectionList sectionList:" + JSONObject.toJSONString(sectionList));
		PageInfo<Section> pageInfo = new PageInfo<Section>(sectionList);
		PageDto pageDto = new PageDto();
		pageDto.setTotal(pageInfo.getTotal());
		List<SectionDto> sectionDtosList = new ArrayList<SectionDto>();
		for(int i = 0, l = sectionList.size(); i < l;i++){
			Section section = sectionList.get(i);
			SectionDto sectionDtoInfo = new SectionDto();
			BeanUtils.copyProperties(section,sectionDtoInfo);
			sectionDtosList.add(sectionDtoInfo);
		}
		pageDto.setList(sectionDtosList);
		logger.info("getSectionList--->return pageDto:" + JSONObject.toJSONString(pageDto));
		return pageDto;
	}

	@Override
	public Object getSection(SectionDto sectionDto) {
		logger.info("getSection:" + JSONObject.toJSONString(sectionDto));
		Result returnResult = new Result();
		Section section = sectionMapper.selectByPrimaryKey(sectionDto.getId());
		returnResult.setCode(100);
		returnResult.setMsg("操作成功");
		returnResult.setData(section);
		return returnResult;
	}

	@Override
	public Object saveSection(SectionDto sectionDto) {
		logger.info("saveSection--->sectionDto:" + JSONObject.toJSONString(sectionDto));
		Section section = new Section();
		BeanUtils.copyProperties(sectionDto,section);
		section.setUpdatedTime(new Date());
		section.setCreatedBy("Yx");
		section.setUpdatedBy("Yx");

		//校验课程ID是否存在
		SectionExample sectionExample = new SectionExample();
		sectionExample.createCriteria().andCourseIdEqualTo(sectionDto.getCourseId()).andIsDelEqualTo(0);

		//判断id是否为空(spring5.3之后启用StringUtils的isEmpty方法)
		if(StringUtils.hasLength(sectionDto.getId())){
			//规避当前ID
			sectionExample.createCriteria().andIdNotEqualTo(sectionDto.getId());;
			//课程ID是否已经存在
			if(this.checkExistedCourseId(sectionExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//修改课程信息
				/*sectionExample = new SectionExample();
				sectionExample.createCriteria().andIdEqualTo(sectionDto.getId());*/
				int count = sectionMapper.updateByPrimaryKeySelective(section);
			}
		}else{
			//课程ID是否已经存在
			if(this.checkExistedCourseId(sectionExample)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//保存课程信息
				section.setId(UuidUtil.getShortUuid());
				section.setCreatedTime(new Date());
				section.setIsDel(0);
				sectionMapper.insert(section);
			}
		}
		return ResultUtil.success();
	}

	//检查课程ID是否已经存在
	public boolean checkExistedCourseId(SectionExample sectionExample){
		boolean resultFlag = false;
		//查询课程ID是否被使用
		List<Section> existedSectionList = sectionMapper.selectByExample(sectionExample);
		if(!CollectionUtils.isEmpty(existedSectionList)){
			resultFlag = true;
		}
		return resultFlag;
	}

	@Override
	public Object deleteSection(SectionDto sectionDto) {
		logger.info("deleteSection ---> sectionDto:" + JSONObject.toJSONString(sectionDto));
		Section section = this.copyProperties(sectionDto);
		section.setIsDel(1);
		section.setUpdatedTime(new Date());
		section.setUpdatedBy("Yx");
		int count = sectionMapper.updateByPrimaryKeySelective(section);
		return ResultUtil.success();
	}

	//实体类转换
	public Section copyProperties(SectionDto sectionDto){
		Section section = new Section();
		BeanUtils.copyProperties(sectionDto,section);
		return section;
	}

}