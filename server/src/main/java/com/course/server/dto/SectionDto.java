package com.course.server.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonIgnoreProperties({"page","size"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SectionDto extends PageDto{
	/**
	 *	ID
	 */
	private String id;
	/**
	 *	课程ID
	 */
	private String courseId;
	/**
	 *	章节ID
	 */
	private String chapterId;
	/**
	 *	标题
	 */
	private String title;
	/**
	 *	视频
	 */
	private String video;
	/**
	 *	名称
	 */
	private Integer time;
	/**
	 *	收费:0-否;1-是
	 */
	private String charge;
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

		public String getId(){
			return id;
		}
		public void setId(String id){
			this.id = id;
		}
		public String getCourseId(){
			return courseId;
		}
		public void setCourseId(String courseId){
			this.courseId = courseId;
		}
		public String getChapterId(){
			return chapterId;
		}
		public void setChapterId(String chapterId){
			this.chapterId = chapterId;
		}
		public String getTitle(){
			return title;
		}
		public void setTitle(String title){
			this.title = title;
		}
		public String getVideo(){
			return video;
		}
		public void setVideo(String video){
			this.video = video;
		}
		public Integer getTime(){
			return time;
		}
		public void setTime(Integer time){
			this.time = time;
		}
		public String getCharge(){
			return charge;
		}
		public void setCharge(String charge){
			this.charge = charge;
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