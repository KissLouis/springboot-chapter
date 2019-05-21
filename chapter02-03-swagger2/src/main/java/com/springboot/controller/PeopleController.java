package com.springboot.controller;

import com.springboot.entity.People;
import com.springboot.entity.ReturnBean;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    People people;

    @ApiOperation(value = "查找所有类型")
    @GetMapping(value = "/all")
    public ReturnBean findAllUser() {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnType("200");
        returnBean.setReturnValue(people);
        return returnBean;
    }

    @ApiOperation(value = "通过key值查找map信息")
    @GetMapping("/find/{key}")
    public ReturnBean findOneUser(@ApiParam(value = "key信息") @PathVariable("key") String key) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnType("200");
        returnBean.setReturnValue(people.getUser().get(key));
        return returnBean;
    }

    @ApiOperation(value = "模拟删除一个用户")
    @DeleteMapping("/delete/{id}")
    public ReturnBean deleteUser(@ApiParam(value = "用户id") @PathVariable("id") Integer id) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnType("200");
        returnBean.setReturnValue("模拟删除一个用户");
        return returnBean;
    }

    @ApiOperation(value = "模拟新增一个用户")
    @PostMapping("/add")
    public ReturnBean addStudentRestful(@ApiParam(value = "用户或用户各个属性") People people) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnType("200");
        returnBean.setReturnValue("模拟新增一个用户");
        return returnBean;
    }

    @ApiOperation(value = "模拟更新一个用户")
    @PutMapping("/update")
    public ReturnBean updateUser(@ApiParam(value = "用户或用户各个属性") People people) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setReturnType("200");
        returnBean.setReturnValue("模拟更新一个用户");
        return returnBean;
    }

}
