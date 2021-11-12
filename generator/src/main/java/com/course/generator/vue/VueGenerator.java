package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;

import java.util.*;

/**
 * @Author YanXin
 * @Date 2021/9/14
 **/
public class VueGenerator {

	static String MODULE = "business";
	static String toVuePath = "admin\\src\\views\\admin\\course\\";
	static String toEditVuePath = "admin\\src\\views\\admin\\course\\";

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
		/*FreeMarkerUtil.initConfig("vue.ftl");
		FreeMarkerUtil.generator(toVuePath + domain + ".vue",map);*/

		//生成dto
		FreeMarkerUtil.initConfig("vueEdit.ftl");
		FreeMarkerUtil.generator(toEditVuePath + domain + "Edit.vue",map);

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
