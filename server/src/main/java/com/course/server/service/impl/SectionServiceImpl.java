package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.enums.OptionEnum;
import com.course.server.enums.SectionChargeEnum;
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
import java.text.DecimalFormat;
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
		/*SectionExample sectionExample = new SectionExample();
		sectionExample.createCriteria().andIsDelEqualTo(0);
		sectionExample.setOrderByClause("updated_time DESC");*/
		List<SectionDto> sectionList = sectionMapper.getSectionList(sectionDto);
		logger.info("getSectionList--->selectsectionList sectionList:" + JSONObject.toJSONString(sectionList));
		PageInfo<SectionDto> pageInfo = new PageInfo<SectionDto>(sectionList);
		PageDto pageDto = new PageDto();
		pageDto.setTotal(pageInfo.getTotal());
		/*List<SectionDto> sectionDtosList = new ArrayList<SectionDto>();
		for(int i = 0, l = sectionList.size(); i < l;i++){
			Section section = sectionList.get(i);
			SectionDto sectionDtoInfo = new SectionDto();
			BeanUtils.copyProperties(section,sectionDtoInfo);
			sectionDtosList.add(sectionDtoInfo);
		}*/
		pageDto.setList(sectionList);
		logger.info("getSectionList--->return pageDto:" + JSONObject.toJSONString(pageDto));
		return pageDto;
	}

	@Override
	public Object getSection(SectionDto sectionDto) {
		logger.info("getSection:" + JSONObject.toJSONString(sectionDto));
		Result returnResult = new Result();
		SectionDto section = sectionMapper.getSectionInfo(sectionDto);
		returnResult.setCode(100);
		returnResult.setMsg("????????????");
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

		//????????????ID????????????
		SectionExample sectionExample = new SectionExample();
		sectionExample.createCriteria().andIsDelEqualTo(0);

		//??????id????????????(spring5.3????????????StringUtils???isEmpty??????)
		if(OptionEnum.EDIT.getCode().equals(sectionDto.getOptionType())){
			//????????????ID
			sectionExample.getOredCriteria().get(0).andIdNotEqualTo(sectionDto.getId());
			//??????ID??????????????????
			if(this.checkExistedCourseId(sectionExample)){
				return ResultUtil.error(900,"??????ID????????????");
			}else{
				//??????????????????
				/*sectionExample = new SectionExample();
				sectionExample.createCriteria().andIdEqualTo(sectionDto.getId());*/
				int count = sectionMapper.updateByPrimaryKeySelective(section);
			}
		}else{
			//??????ID??????????????????
			if(this.checkExistedCourseId(sectionExample)){
				return ResultUtil.error(900,"??????ID????????????");
			}else{
				//??????????????????
				section.setCharge(SectionChargeEnum.CHARGE.getCode());
				section.setId(UuidUtil.getShortUuid());
				section.setCreatedTime(new Date());
				section.setIsDel(0);
				sectionMapper.insert(section);
			}
		}
		return ResultUtil.success();
	}

	//????????????ID??????????????????
	public boolean checkExistedCourseId(SectionExample sectionExample){
		boolean resultFlag = false;
		//????????????ID???????????????
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

	//???????????????
	public Section copyProperties(SectionDto sectionDto){
		Section section = new Section();
		BeanUtils.copyProperties(sectionDto,section);
		return section;
	}

	public String getProbability(int x,int y,int count){
		float num = (x*y+x*(100-y))/100;
		float prCount = 0;
		double xMol = Math.pow(x,count);
		double xDen = Math.pow(100,count);
		double yMol = 0;
		double yDen = 0;
		List xPrCount = new ArrayList();
		List yPrCount = new ArrayList();
		for(int i=1;i<=count;i++){
			double xCount = x*(100-x)/100;
			double yCount = y*(100-y)/100;
			yMol = (100-x)*(100-y)/100+(100-x) * (100-x)*(y*(100-y)/100);
		}
		DecimalFormat decimalFormat= new  DecimalFormat(".0000");
		return decimalFormat.format(num) + "%";
	}

	public static void main(String[] args) {
		System.out.println(new SectionServiceImpl().getProbability(80,90,1));;
	}

}