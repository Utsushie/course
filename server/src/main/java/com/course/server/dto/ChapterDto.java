package com.course.server.dto;

import lombok.Data;

@Data
public class ChapterDto {
    private int id;

    private String courseId;

    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}