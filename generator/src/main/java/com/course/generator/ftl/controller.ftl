package com.course.business.Controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.course.server.dto.${Domain}Dto;
import com.course.server.service.${Domain}Service;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

	private static Logger logger = LoggerFactory.getLogger(${Domain}Controller.class);

	private final String BUSINESS_NAME = "章节";

	@Resource
	private ${Domain}Service ${domain}Service;

	/**
	 * 获取课程列表
	 * @param ${domain}Dto
	 * @return
	 */
	@RequestMapping(value="/get${Domain}List")
	public Object get${Domain}List(${Domain}Dto ${domain}Dto){
		Object object = ${domain}Service.get${Domain}List(${domain}Dto);
		return object;
	}

	/**
	 * 保存课程信息
	 * @param ${domain}Dto
	 * @return
	 */
	@RequestMapping(value="/save${Domain}",method = {RequestMethod.POST})
	public Object save${Domain}(@RequestBody ${Domain}Dto ${domain}Dto){
		logger.info("save${Domain}:" + JSONObject.toJSONString(${domain}Dto));

		ValidatorUtil.require(${domain}Dto.getName(),"名称");
		ValidatorUtil.require(${domain}Dto.getCourseId(),"课程ID");
		ValidatorUtil.length(${domain}Dto.getCourseId(),"课程ID",1,8);

		Object object = ${domain}Service.save${Domain}(${domain}Dto);
		return object;
	}

	/**
	 * 获取课程信息
	 * @param ${domain}Dto
	 * @return
	 */
	@RequestMapping(value="/get${Domain}")
	public Object get${Domain}(${Domain}Dto ${domain}Dto){
		Object object = ${domain}Service.get${Domain}(${domain}Dto);
		return object;
	}

	/**
	 * 删除课程信息
	 * @param ${domain}Dto
	 * @return
	 */
	@RequestMapping(value="/delete${Domain}")
	public Object delete${Domain}(${Domain}Dto ${domain}Dto){
		Object object = ${domain}Service.delete${Domain}(${domain}Dto);
		return object;
	}

}
