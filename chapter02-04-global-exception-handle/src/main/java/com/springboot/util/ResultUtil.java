package com.springboot.util;

/**
 * @author Louis
 * @title: ResultUtil
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/25 16:50
 */
public class ResultUtil {

    /**
     * 返回成功
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(true);
        result.setCode("200");//成功
        result.setMessage("success");//提示语
        result.setData(object);//返回内容
        return result;
    }

    /**
     * 返回失败
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(code);//失败
        result.setMessage(msg);//提示语
        return result;
    }

}
