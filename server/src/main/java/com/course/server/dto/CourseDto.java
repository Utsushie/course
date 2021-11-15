package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
		import java.math.BigDecimal;

@JsonIgnoreProperties({"page","size"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CourseDto extends PageDto{
	/**
	 *	ID
	 */
	private String id;
	/**
	 *	名称
	 */
	private String name;
	/**
	 *	概述
	 */
	private String summary;
	/**
	 *	时长|单位秒
	 */
	private Integer time;
	/**
	 *	价格(元)
	 */
	private BigDecimal price;
	/**
	 *	封面
	 */
	private String image;
	/**
	 *	级别|ONE("1","初级"),TWO("2","中级"),THREE("3","高级")
	 */
	private String level;
	/**
	 *	收费|CHARGE("1",收费),FREE("0","免费")
	 */
	private String charge;
	/**
	 *	状态|PUBLISH("1","发布"),DRAFT("0","草稿")
	 */
	private String status;
	/**
	 *	报名数
	 */
	private Integer enroll;
	/**
	 *	顺序
	 */
	private Integer sort;
	/**
	 *	是否删除:0-否;1-是
	 */
	private Integer isDel;
	/**
	 *	创建时间
	 */
	private String createdTime;
	/**
	 *	更新时间
	 */
	private String updatedTime;
	/**
	 *	创建人
	 */
	private String createdBy;
	/**
	 *	修改人
	 */
	private String updatedBy;

	private String optionType;

	public String getOptionType(){
		return optionType;
	}
	public void setOptionType(String optionType){
		this.optionType = optionType;
	}

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getSummary(){
		return summary;
	}
	public void setSummary(String summary){
		this.summary = summary;
	}
	public Integer getTime(){
		return time;
	}
	public void setTime(Integer time){
		this.time = time;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getLevel(){
		return level;
	}
	public void setLevel(String level){
		this.level = level;
	}
	public String getCharge(){
		return charge;
	}
	public void setCharge(String charge){
		this.charge = charge;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public Integer getEnroll(){
		return enroll;
	}
	public void setEnroll(Integer enroll){
		this.enroll = enroll;
	}
	public Integer getSort(){
		return sort;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getIsDel(){
		return isDel;
	}
	public void setIsDel(Integer isDel){
		this.isDel = isDel;
	}
	public String getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}
	public String getUpdatedTime(){
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime){
		this.updatedTime = updatedTime;
	}
	public String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	public String getUpdatedBy(){
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}
}