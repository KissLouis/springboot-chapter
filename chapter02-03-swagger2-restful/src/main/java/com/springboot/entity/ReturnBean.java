package com.springboot.entity;

import java.io.Serializable;

/**
 * @author Louis
 * @title: ReturnBean
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/21 15:37
 */
public class ReturnBean implements Serializable {
    /**
     * 序列化
     */

    private static final long serialVersionUID = -2867756409591322259L;
    /**
     * 成功
     */

    private final String SUCCESS = "SUCCESS";
    /**
     * 失败
     */

    private final String FAIL = "FAIL";
    /**
     * 返回类型
     */

    private String returnType;
    /**
     * 返回结果
     */

    private Object returnValue;


    /**
     * 构造函数
     */

    public ReturnBean() {


    }

    /**
     * toString
     */

    @Override

    public String toString() {

        return "ReturnBean [returnType=" + returnType + ", returnValue=" + returnValue + ", SUCCESS=" + SUCCESS

                + ", FAIL=" + FAIL + "]";

    }


    /**
     * @param returnValue
     * @Title: setSuccessReturn
     * @Description: 设置成功返回
     */

    public void setSuccessReturn(Object returnValue) {

        this.returnType = this.SUCCESS;

        this.returnValue = returnValue;

    }


    /**
     * @param returnValue
     * @Title: setFailReturn
     * @Description: 设置失败返回
     */

    public void setFailReturn(Object returnValue) {

        this.returnType = this.FAIL;

        this.returnValue = returnValue;

    }


    public String getReturnType() {

        return returnType;

    }


    public void setReturnType(String returnType) {

        this.returnType = returnType;

    }


    public Object getReturnValue() {

        return returnValue;

    }


    public void setReturnValue(Object returnValue) {

        this.returnValue = returnValue;

    }
}
