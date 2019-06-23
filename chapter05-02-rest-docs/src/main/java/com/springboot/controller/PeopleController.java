package com.springboot.controller;

import com.springboot.entity.People;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @GetMapping(value = "/")
    public Result findAllPeople() {
        List<People> peopleList = new ArrayList<>();
        People people1 = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        People people2 = new People(2, "Louis", "psassword", "salt", 1, new Date(), new Date());
        peopleList.add(people1);
        peopleList.add(people2);
        return new Result(ResultCode.SUCCESS, peopleList);
    }

    @GetMapping("/{id}")
    public Result findOnePeople(String id) {
        People people = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, people);
    }

    @DeleteMapping("/{id}")
    public Result deletePeople(Integer id) {
        People people = new People(1, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, "删除成功", people);
    }

    @PostMapping("/")
    public Result addPeople(People people) {
        return new Result(ResultCode.SUCCESS, "新增成功", people);
    }

    @PutMapping("/")
    public Result updatePeople(People people) {
        return new Result(ResultCode.SUCCESS, "更新成功", people);
    }

}
