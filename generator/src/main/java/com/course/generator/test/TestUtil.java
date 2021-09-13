package com.course.generator.test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author YanXin
 * @Date 2021/9/13
 **/
public class TestUtil {

	static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";
	static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
		cfg.setDirectoryForTemplateLoading(new File(ftlPath));
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_31));
		Template temp = cfg.getTemplate("test.ftl");

		FileWriter fw = new FileWriter(toPath + "Test.java");
		BufferedWriter bw = new BufferedWriter(fw);
		temp.process(null,bw);
		bw.flush();
		fw.close();
	}
}
