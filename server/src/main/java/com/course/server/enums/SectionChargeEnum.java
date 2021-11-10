package com.course.server.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author YanXin
 * @Date 2021/11/10
 **/
public enum SectionChargeEnum {

	CHARGE("1","收费"),
	FREE("0","免费");

	private String code;
	private String desce;

	SectionChargeEnum(String code, String desce) {
		this.code = code;
		this.desce = desce;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesce() {
		return desce;
	}

	public void setDesce(String desce) {
		this.desce = desce;
	}
}
