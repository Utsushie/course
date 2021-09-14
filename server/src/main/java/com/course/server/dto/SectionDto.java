package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties({"page","size"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SectionDto extends PageDto{
    private String id;

    private String courseId;

    private String chapterId;

    private String title;

    private String video;

    private Integer time;

    private String charge;

    private Integer sort;

    private int isDel;

    private String createdTime;

    private String updatedTime;

    private String createdBy;

    private String updatedBy;

    private List<SectionDto> chapterDtoList;

}