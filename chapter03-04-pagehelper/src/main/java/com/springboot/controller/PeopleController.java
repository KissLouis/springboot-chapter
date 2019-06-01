package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.entity.People;
import com.springboot.service.PeopleService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

/**
 * @author Louis
 * @title: PeopleController
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/28 10:45
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    /**
     * @return com.springboot.util.result.Result
     * @description: //TODO 查询全部用户
     * @author Louis
     * @date 2019/5/28 10:49
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
     * @date 2019/5/28 10:49
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Integer id) {
        return new Result(ResultCode.SUCCESS, peopleService.findById(id));
    }

    /**
     * @param [id]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据Id删除用户
     * @author Louis
     * @date 2019/5/28 10:50
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
     * @date 2019/5/28 10:50
     */
    @PostMapping("/")
    public Result save(People people) {
        peopleService.save(people);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * @param [people]
     * @return com.springboot.util.result.Result
     * @description: //TODO 修改用户
     * @author Louis
     * @date 2019/5/28 10:50
     */
    @PutMapping("/")
    public Result update(People people) {
        peopleService.update(people);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * @param [page]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据页数分页：通过PageHelper返回分页列表数据
     * @author Louis
     * @date 2019/6/1 17:23
     */
    @GetMapping("/list")
    public Result getList(@RequestParam(value = "p", defaultValue = "1") Integer page, @RequestParam(value = "l",
            defaultValue = "3") Integer size) {
        //通过PageHelper返回分页列表数据
        return new Result(ResultCode.SUCCESS,
                peopleService.peoplePageList(page, size));
    }

    /**
    *
    * @description: //TODO 根据页数分页：通过PageInfo返回数据
    * @author Louis
    * @date 2019/6/1 17:56
    * @param [page, size]
    * @return com.springboot.util.result.Result
    */
    @GetMapping("/page")
    public Result getPage(@RequestParam(value = "p", defaultValue = "1") Integer page, @RequestParam(value = "l",
            defaultValue = "3") Integer size) {
        //通过PageInfo返回数据
        return new Result(ResultCode.SUCCESS,
                peopleService.peoplePageInfo(page, size));
    }

}
