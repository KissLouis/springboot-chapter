package com.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.entity.People;
import com.springboot.service.impl.PeopleServiceImpl;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Louis
 * @since 2019-06-02
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleServiceImpl peopleService;

    /**
     * @param []
     * @return com.springboot.util.result.Result
     * @description: //TODO 查询全部用户
     * @author Louis
     * @date 2019/6/1 21:45
     */
    @GetMapping("/")
    public Result findAll() {
        return new Result(ResultCode.SUCCESS, peopleService.list(null));
    }


    /**
     * @param [id]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据Id查询用户
     * @author Louis
     * @date 2019/6/1 21:45
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Integer id) {
        return new Result(ResultCode.SUCCESS, peopleService.getById(id));
    }

    /**
     * @param [id]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据Id删除用户
     * @author Louis
     * @date 2019/6/1 21:45
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        peopleService.removeById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * @param [people]
     * @return com.springboot.util.result.Result
     * @description: //TODO 新增用户
     * @author Louis
     * @date 2019/6/1 21:45
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
     * @date 2019/6/1 21:45
     */
    @PutMapping("/")
    public Result update(People people) {
        peopleService.updateById(people);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * @param [page, size]
     * @return com.springboot.util.result.Result
     * @description: //TODO 根据Mybatis-Plus进行分页
     * @author Louis
     * @date 2019/6/2 14:19
     */
    @GetMapping("/list")
    public Result getList(@RequestParam(value = "p", defaultValue = "1") Integer page, @RequestParam(value = "l",
            defaultValue = "3") Integer size) {
        //通过PageHelper返回分页列表数据
        return new Result(ResultCode.SUCCESS,
                peopleService.page(new Page<People>(page, size)));
    }

}
