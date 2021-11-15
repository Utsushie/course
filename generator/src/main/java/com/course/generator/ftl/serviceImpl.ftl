package com.course.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.dto.${Domain}Dto;
import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.service.${Domain}Service;
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
public class ${Domain}ServiceImpl implements ${Domain}Service{

	private static Logger logger = LoggerFactory.getLogger(${Domain}ServiceImpl.class);

	@Resource
	private ${Domain}Mapper ${domain}Mapper;

	@Override
	public Object get${Domain}List(${Domain}Dto ${domain}Dto) {
		logger.info("get${Domain}List:" + JSONObject.toJSONString(${domain}Dto));
		PageHelper.startPage(${domain}Dto.getPage(),${domain}Dto.getSize());
		${Domain}Example ${domain}Example = new ${Domain}Example();
		${domain}Example.createCriteria().andIsDelEqualTo(0);
		${domain}Example.setOrderByClause("updated_time DESC");
		List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
		logger.info("get${Domain}List--->select${domain}List ${domain}List:" + JSONObject.toJSONString(${domain}List));
		PageInfo<${Domain}> pageInfo = new PageInfo<${Domain}>(${domain}List);
		PageDto pageDto = new PageDto();
		pageDto.setTotal(pageInfo.getTotal());
		List<${Domain}Dto> ${domain}DtosList = new ArrayList<${Domain}Dto>();
		for(int i = 0, l = ${domain}List.size(); i < l;i++){
			${Domain} ${domain} = ${domain}List.get(i);
			${Domain}Dto ${domain}DtoInfo = new ${Domain}Dto();
			BeanUtils.copyProperties(${domain},${domain}DtoInfo);
			${domain}DtosList.add(${domain}DtoInfo);
		}
		pageDto.setList(${domain}DtosList);
		logger.info("get${Domain}List--->return pageDto:" + JSONObject.toJSONString(pageDto));
		return pageDto;
	}

	@Override
	public Object get${Domain}(${Domain}Dto ${domain}Dto) {
		logger.info("get${Domain}:" + JSONObject.toJSONString(${domain}Dto));
		Result returnResult = new Result();
		${Domain} ${domain} = ${domain}Mapper.selectByPrimaryKey(${domain}Dto.getId());
		returnResult.setCode(100);
		returnResult.setMsg("操作成功");
		returnResult.setData(${domain});
		return returnResult;
	}

	@Override
	public Object save${Domain}(${Domain}Dto ${domain}Dto) {
		logger.info("save${Domain}--->${domain}Dto:" + JSONObject.toJSONString(${domain}Dto));
		${Domain} ${domain} = new ${Domain}();
		BeanUtils.copyProperties(${domain}Dto,${domain});
		${domain}.setUpdatedTime(new Date());
		${domain}.setCreatedBy("Yx");
		${domain}.setUpdatedBy("Yx");

		//校验课程ID是否存在
		${Domain}Example ${domain}Example = new ${Domain}Example();
		${domain}Example.createCriteria().andIdEqualTo(${domain}Dto.getId()).andIsDelEqualTo(0);

		//判断id是否为空(spring5.3之后启用StringUtils的isEmpty方法)
		if(OptionEnum.EDIT.getCode().equals(${domain}Dto.getOptionType())){
			//规避当前ID
			${domain}Example.getOredCriteria().get(0).andIdNotEqualTo(${domain}Dto.getId());;
			//课程ID是否已经存在
			if(this.checkExistedCourseId(${domain}Example)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//修改课程信息
				/*${domain}Example = new ${Domain}Example();
				${domain}Example.createCriteria().andIdEqualTo(${domain}Dto.getId());*/
				int count = ${domain}Mapper.updateByPrimaryKeySelective(${domain});
			}
		}else{
			//课程ID是否已经存在
			if(this.checkExistedCourseId(${domain}Example)){
				return ResultUtil.error(900,"课程ID已经存在");
			}else{
				//保存课程信息
				${domain}.setId(UuidUtil.getShortUuid());
				${domain}.setCreatedTime(new Date());
				${domain}.setIsDel(0);
				${domain}Mapper.insert(${domain});
			}
		}
		return ResultUtil.success();
	}

	//检查课程ID是否已经存在
	public boolean checkExistedCourseId(${Domain}Example ${domain}Example){
		boolean resultFlag = false;
		//查询课程ID是否被使用
		List<${Domain}> existed${Domain}List = ${domain}Mapper.selectByExample(${domain}Example);
		if(!CollectionUtils.isEmpty(existed${Domain}List)){
			resultFlag = true;
		}
		return resultFlag;
	}

	@Override
	public Object delete${Domain}(${Domain}Dto ${domain}Dto) {
		logger.info("delete${Domain} ---> ${domain}Dto:" + JSONObject.toJSONString(${domain}Dto));
		${Domain} ${domain} = this.copyProperties(${domain}Dto);
		${domain}.setIsDel(1);
		${domain}.setUpdatedTime(new Date());
		${domain}.setUpdatedBy("Yx");
		int count = ${domain}Mapper.updateByPrimaryKeySelective(${domain});
		return ResultUtil.success();
	}

	//实体类转换
	public ${Domain} copyProperties(${Domain}Dto ${domain}Dto){
		${Domain} ${domain} = new ${Domain}();
		BeanUtils.copyProperties(${domain}Dto,${domain});
		return ${domain};
	}

}