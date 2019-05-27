package com.springboot.controller;

import com.springboot.entity.People;
import com.springboot.service.PeopleService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Louis
 * @title: PeopleController
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 16:22
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    /**
     * @param []
     * @return com.springboot.util.result.Result
     * @description: //TODO 查询全部用户
     * @author Louis
     * @date 2019/5/27 21:35
     */
    @GetMapping("/")
    public Result findAll() {
        return new Result(ResultCode.SUCCESS, peopleService.findAll());
    }

    /**
     * @param [id]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据Id查询用户
     * @author Louis
     * @date 2019/5/27 21:36
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Integer id) {
        return new Result(ResultCode.SUCCESS, peopleService.findById(id).get());
    }

    /**
     * @param [id]
     * @return com.springboot.util.result.Result
     * @description: //TODO 删除用户
     * @author Louis
     * @date 2019/5/27 21:38
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        peopleService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * @param [people]
     * @return com.springboot.util.result.Result
     * @description: //TODO 新增用户
     * @author Louis
     * @date 2019/5/27 21:38
     */
    @PostMapping("/")
    public Result save(People people) {
        return new Result(ResultCode.SUCCESS, peopleService.save(people));
    }

    /**
     * @param [people]
     * @return com.springboot.util.result.Result
     * @description: //TODO 修改用户
     * @author Louis
     * @date 2019/5/27 21:39
     */
    @PutMapping("/")
    public Result update(People people) {
        return new Result(ResultCode.SUCCESS, peopleService.flush(people));
    }

}
