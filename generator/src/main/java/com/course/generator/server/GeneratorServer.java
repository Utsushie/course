package com.course.generator.server;

import com.course.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author YanXin
 * @Date 2021/9/14
 **/
public class GeneratorServer {

	static String MODULE = "business";
	static String toServerPath = "business\\src\\main\\java\\com\\course\\business\\controller\\admin\\";
	//static String toServerPath = "server\\src\\main\\java\\com\\course\\server\\service\\";
	//static String toServerPath = "server\\src\\main\\java\\com\\course\\server\\service\\impl\\";
	static String toControllerPath = "business\\src\\main\\java\\com\\course\\" + MODULE +"\\controller\\admin\\";

	public static void main(String[] args) throws IOException, TemplateException {
		String Domain = "Section";
		String domain = "section";
		String tableName = "小节";
		String module = "business";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Domain",Domain);
		map.put("domain",domain);
		map.put("tableName",tableName);
		map.put("module",module);
		FreeMarkerUtil.initConfig("controller.ftl");
		FreeMarkerUtil.generator(toControllerPath + Domain + "Controller.java",map);
	}

}
