package com.course.server.util;

import com.course.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/**
 * @Author YanXin
 * @Date 2021/9/9
 **/
public class ValidatorUtil {

	public static void require(String str, String fieldName) {
		if(StringUtils.hasLength(str)){
			throw new ValidatorException(fieldName + "不能为空");
		}
	}

	public static void length(String str, String fieldName,int min, int max) {
		int length = 0;
		if(!StringUtils.hasLength(str)){
			length = str.length();
		}
		if(length < min || length> max){
			throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
		}
	}

}
