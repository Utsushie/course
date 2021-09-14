package com.course.generator.util;

import lombok.Data;

/**
 * @Author YanXin
 * @Date 2021/9/14
 **/
@Data
public class Field {

	private String name;
	private String nameHump;
	private String nameBigHump;
	private String nameCn;
	private String type;
	private String javaType;
	private String comment;

}
