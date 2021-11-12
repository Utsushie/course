package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 * @Author YanXin
 * @Date 2021/9/14
 **/
public class GeneratorServer {

	static String MODULE = "business";
	static String toDtoPath = "server\\src\\main\\java\\com\\course\\server\\dto\\";
	static String toServerPath = "server\\src\\main\\java\\com\\course\\server\\service\\";
	static String toServerImplPath = "server\\src\\main\\java\\com\\course\\server\\service\\impl\\";
	static String toControllerPath = "business\\src\\main\\java\\com\\course\\" + MODULE +"\\controller\\admin\\";

	public static void main(String[] args) throws Exception {
		String Domain = "Course";
		String domain = "course";
		String tableName = "课程";
		String module = "business";
		List<Field> fieldList = DbUtil.getColumnByTableName("cs_course");
		Set<String> typeSet = getJavaTypes(fieldList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Domain",Domain);
		map.put("domain",domain);
		map.put("tableName",tableName);
		map.put("module",module);
		map.put("fieldList", fieldList);
		map.put("typeSet", typeSet);

		//生成dto
		FreeMarkerUtil.initConfig("dto.ftl");
		FreeMarkerUtil.generator(toDtoPath + Domain + "Dto.java",map);

		FreeMarkerUtil.initConfig("serviceImpl.ftl");
		FreeMarkerUtil.generator(toServerImplPath + Domain + "ServiceImpl.java",map);

		FreeMarkerUtil.initConfig("service.ftl");
		FreeMarkerUtil.generator(toServerPath + Domain + "Service.java",map);

		FreeMarkerUtil.initConfig("controller.ftl");
		FreeMarkerUtil.generator(toControllerPath + Domain + "Controller.java",map);
	}

	private static Set<String> getJavaTypes(List<Field> fieldList){
		Set<String> set = new HashSet<>();
		for(int i = 0; i<fieldList.size();i++){
			Field field = fieldList.get(i);
			set.add(field.getJavaType());
		}
		return set;
	}

}
