package com.course.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.course.server.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @Author YanXin
 * @Date 2021/9/10
 **/
@Aspect
@Component
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(public * com.course.*.controller..*Controller.*(..))")

	public void controllerpoint(){}

	@Before("controllerpoint()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{

		//日志编号
		MDC.put("UUID", UuidUtil.getShortUuid());

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Signature singnature = joinPoint.getSignature();
		String name = singnature.getName();

		String nameCn = "";
		if(name.contains("list") || name.contains("query") || name.contains("get")){
			nameCn = "查询";
		}else if (name.contains("save")){
			nameCn = "保存";
		}else if (name.contains("delete")){
			nameCn = "删除";
		}else{
			nameCn = "操作";
		}

		Class clazz = singnature.getDeclaringType();
		Field field;
		String businessName = "";

		try {
			field = clazz.getField("BUSINESS_NAME");
			if(!StringUtils.isEmpty(field)){
				businessName = (String) field.get(clazz);
			}
		} catch (NoSuchFieldException e) {
			logger.error("为获取到业务名称");
		} catch (SecurityException e) {
			logger.error("获取业务名称失败", e);
		}

		logger.info("-------------[{}]{}开始-------------",businessName,nameCn);
		logger.info("请求地址:{}{}",request.getRequestURL().toString(),request.getMethod());
		logger.info("类名方法:{}.{}",singnature.getDeclaringTypeName(), name);
		logger.info("远程地址: {}",request.getRemoteAddr());

		Object[] args = joinPoint.getArgs();
		Object[] arguments = new Object[args.length];
		for(int i = 0; i < args.length;i++){
			if(args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile){
				continue;
			}
			arguments[i] = args[i];
		}


		//排除敏感字段
		String[] excludeProperties = {};
		PropertyPreFilters filters = new PropertyPreFilters();
		PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
		excludefilter.addExcludes(excludeProperties);
		logger.info("请求参数: {}", JSONObject.toJSONString(arguments,excludefilter));
	}

	@Around("controllerPointcut()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		String[] excludeProperties = {"password"};
		PropertyPreFilters filters = new PropertyPreFilters();
		PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
		excludefilter.addExcludes(excludeProperties);
		logger.info("返回结果: {}",JSONObject.toJSONString(result,excludefilter));
		logger.info("------------- 结束 耗时:{} ms -------------",System.currentTimeMillis() - startTime);
		return result;
	}

}
