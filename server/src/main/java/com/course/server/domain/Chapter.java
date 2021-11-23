package com.course.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;

    private String courseId;

    private String courseName;

    private String name;

    private Integer isDel;

    private Date createdTime;

    private Date updatedTime;

    private String createdBy;

    private String updatedBy;

}