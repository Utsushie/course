package com.course.server.util.result;

import org.springframework.util.StringUtils;

/**
 * @Author YanXin
 * @Date 2021/6/29
 **/
public class ResultUtil {

    /**成功且带数据**/
    public static Result success(Object object,String msg){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        if(StringUtils.hasLength(msg)){
            result.setMsg(msg);
        }else{
            result.setMsg(ResultEnum.SUCCESS.getMsg());
        }
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){
        return success(null,null);
    }
    /**失败**/
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**成功操作**/
    public static Result successOption(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
