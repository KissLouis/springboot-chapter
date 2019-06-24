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
@CrossOrigin
public class PeopleController {

    /**
     * @api {get} /people 查询People集合
     * @apiDescription 查询People集合
     * @apiName findAllPeople
     * @apiGroup People
     * @apiVersion 1.0.0
     * @apiSampleRequest /people
     * @apiUse result_msg
     */
    @GetMapping(value = "")
    public Result findAllPeople() {
        List<People> peopleList = new ArrayList<>();
        People people1 = new People("1", "Louis", "psassword", "salt", 1, new Date(), new Date());
        People people2 = new People("2", "Louis", "psassword", "salt", 1, new Date(), new Date());
        peopleList.add(people1);
        peopleList.add(people2);
        return new Result(ResultCode.SUCCESS, peopleList);
    }

    /**
     * @api {get} /people/{id} 查询People对象
     * @apiDescription 查询People对象
     * @apiName findOnePeople
     * @apiGroup People
     * @apiVersion 1.0.0
     * @apiParam {Number} id 用户编号
     * @apiSampleRequest /people/{id}
     * @apiUse result_msg
     * @apiSuccess (success 2000) {Number} data.peopleId   用户编号
     * @apiSuccess (success 2000) {String} data.peopleName   用户姓名
     * @apiSuccess (success 2000) {String} data.password   用户密码
     * @apiSuccess (success 2000) {String} data.salt   盐值
     * @apiSuccess (success 2000) {Number} data.status   状态值
     * @apiSuccess (success 2000) {Date} data.modifyTime   更新时间
     * @apiSuccess (success 2000) {Date} data.createTime   创建时间
     */
    @GetMapping("/{id}")
    public Result findOnePeople(String id) {
        People people = new People(id, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, people);
    }

    /**
     * @api {delete} /people/{id} 删除People对象
     * @apiDescription 删除People对象
     * @apiName deletePeople
     * @apiGroup People
     * @apiVersion 1.0.0
     * @apiParam {Number} id 用户编号
     * @apiSampleRequest /people/{id}
     * @apiUse result_msg
     * @apiSuccess (success 2000) {Number} data.peopleId   用户编号
     * @apiSuccess (success 2000) {String} data.peopleName   用户姓名
     * @apiSuccess (success 2000) {String} data.password   用户密码
     * @apiSuccess (success 2000) {String} data.salt   盐值
     * @apiSuccess (success 2000) {Number} data.status   状态值
     * @apiSuccess (success 2000) {Date} data.modifyTime   更新时间
     * @apiSuccess (success 2000) {Date} data.createTime   创建时间
     */
    @DeleteMapping("/{id}")
    public Result deletePeople(String id) {
        People people = new People(id, "Louis", "psassword", "salt", 1, new Date(), new Date());
        return new Result(ResultCode.SUCCESS, "删除成功", people);
    }

    /**
     * @api {post} /people/ 新增People对象
     * @apiDescription 新增People对象
     * @apiName addPeople
     * @apiGroup People
     * @apiVersion 1.0.0
     * @apiParam (people) {Number} peopleId 用户编号
     * @apiParam (people) {String} peopleName 用户姓名
     * @apiSampleRequest /people/
     * @apiUse result_msg
     * @apiSuccess (success 2000) {Number} data.peopleId   用户编号
     * @apiSuccess (success 2000) {String} data.peopleName   用户姓名
     * @apiSuccess (success 2000) {String} data.password   用户密码
     * @apiSuccess (success 2000) {String} data.salt   盐值
     * @apiSuccess (success 2000) {Number} data.status   状态值
     * @apiSuccess (success 2000) {Date} data.modifyTime   更新时间
     * @apiSuccess (success 2000) {Date} data.createTime   创建时间
     */
    @PostMapping("/")
    public Result addPeople(People people) {
        return new Result(ResultCode.SUCCESS, "新增成功", people);
    }

    /**
     * @api {put} /people/ 更新People对象
     * @apiDescription 更新People对象
     * @apiName updatePeople
     * @apiGroup People
     * @apiVersion 1.0.0
     * @apiParam (people) {Number} peopleId 用户编号
     * @apiParam (people) {String} peopleName 用户姓名
     * @apiSampleRequest /people/
     * @apiUse result_msg
     * @apiSuccess (success 2000) {Number} data.peopleId   用户编号
     * @apiSuccess (success 2000) {String} data.peopleName   用户姓名
     * @apiSuccess (success 2000) {String} data.password   用户密码
     * @apiSuccess (success 2000) {String} data.salt   盐值
     * @apiSuccess (success 2000) {Number} data.status   状态值
     * @apiSuccess (success 2000) {Date} data.modifyTime   更新时间
     * @apiSuccess (success 2000) {Date} data.createTime   创建时间
     */
    @PutMapping("/")
    public Result updatePeople(People people) {
        return new Result(ResultCode.SUCCESS, "更新成功", people);
    }

}
