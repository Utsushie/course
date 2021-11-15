package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
<#list typeSet as type>
	<#if type == 'Date'>
		import java.utilDate;
		import com.fasterxml.jackson.annotation.JsonFormat;
	</#if>
	<#if type=='BigDecimal'>
		import java.math.BigDecimal;
	</#if>
</#list>

@JsonIgnoreProperties({"page","size"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ${Domain}Dto extends PageDto{
<#list fieldList as field>
	/**
	 *	${field.comment}
	 */
	<#if field.javaType == 'Date'>
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	</#if>
	private ${field.javaType} ${field.nameHump};
</#list>

	private String optionType;

	public String getOptionType(){
		return optionType;
	}
	public void setOptionType(String optionType){
		this.optionType = optionType;
	}

<#list fieldList as field>
	public ${field.javaType} get${field.nameBigHump}(){
		return ${field.nameHump};
	}
	public void set${field.nameBigHump}(${field.javaType} ${field.nameHump}){
		this.${field.nameHump} = ${field.nameHump};
	}
</#list>
}