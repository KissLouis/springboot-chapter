package com.springboot.controller;

import com.springboot.entity.People;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @ApiOperation(value = "查找所有People")
    @GetMapping(value = "/")
    public Result findAllPeople() {
        List<People> peopleList = new ArrayList<>();
        People people1 = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        People people2 = new People(2, "Louis", "psassword", "salt", 1, new Date(), new Date());
        peopleList.add(people1);
        peopleList.add(people2);
        return new Result(ResultCode.SUCCESS, peopleList);
    }

    @ApiOperation(value = "通过id查找people信息")
    @GetMapping("/{id}")
    public Result findOneUser(@ApiParam(value = "用户id") @PathVariable("id") String id) {
        People people = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, people);
    }

    @ApiOperation(value = "模拟删除一个用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@ApiParam(value = "用户id") @PathVariable("id") Integer id) {
        People people = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, "删除成功", people);
    }

    @ApiOperation(value = "模拟新增一个用户")
    @PostMapping("/")
    public Result addStudentRestful(@ApiParam(value = "用户或用户各个属性") People people) {
        return new Result(ResultCode.SUCCESS, "新增成功", people);
    }

    @ApiOperation(value = "模拟更新一个用户")
    @PutMapping("/")
    public Result updateUser(@ApiParam(value = "用户或用户各个属性") People people) {
        return new Result(ResultCode.SUCCESS, "更新成功", people);
    }

}
