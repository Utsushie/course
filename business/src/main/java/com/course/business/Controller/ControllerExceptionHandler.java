package com.course.business.Controller;

import com.course.business.Controller.admin.ChapterController;
import com.course.server.exception.ValidatorException;
import com.course.server.util.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author YanXin
 * @Date 2021/9/10
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(value = ValidatorException.class)
	@ResponseBody
	public Object validatorExceptionHandler(ValidatorException e){
		logger.info(e.getMessage());
		return ResultUtil.error(900,"请求参数异常");
	}

}
