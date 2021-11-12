package com.course.server.enums;

/**
 * @Author YanXin
 * @Date 2021/11/10
 **/
public enum CourseStatusEnum {

	PUBLISH("1","发布"),
	DRAFT("0","草稿");

	private String code;
	private String desc;

	CourseStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
