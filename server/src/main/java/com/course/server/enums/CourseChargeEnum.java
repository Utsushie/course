package com.course.server.enums;

/**
 * @Author YanXin
 * @Date 2021/11/10
 **/
public enum CourseChargeEnum {

	CHARGE("1","收费"),
	FREE("0","免费");

	private String code;
	private String desc;

	CourseChargeEnum(String code, String desc) {
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
