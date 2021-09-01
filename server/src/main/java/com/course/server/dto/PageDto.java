package com.course.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author YanXin
 * date 2021-09-01
 */
@Data
@JsonIgnoreProperties({"page","size"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PageDto {

    protected int page;

    protected int size;

    protected long total;

    protected List<?> list;

}
