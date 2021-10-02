package com.course.server.service;

import java.util.List
import com.course.server.dto.${Domain}Dto;

/**
 * @Author YanXin
 * @Date 2021/8/31
 **/
public interface ${Domain}Service {

	/* 获取课程列表 */
	public Object get${Domain}List(${Domain}Dto ${domain}Dto);

	/* 获取课程信息 */
	public Object get${Domain}(${Domain}Dto ${domain}Dto);

	/* 保存课程 */
	public Object save${Domain}(${Domain}Dto ${domain}Dto);

	/* 删除课程 */
	public Object delete${Domain}(${Domain}Dto ${domain}Dto);

}
