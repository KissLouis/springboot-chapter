package com.springboot.util;

/**
 * @author Louis
 * @title: BaseApi
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/22 20:08
 */
public class BaseApi {

    /**
     * @apiDefine error_msg 全局配置失败响应信息
     * @apiError 1001 保存失败
     * @apiError 1002 修改失败
     * @apiError 1003 删除失败
     * @apiError 1004 上传失败
     * @apiError 1005 注册失败
     * @apiError 1101 输入参数格式不正确
     * @apiError 1102 用户名或者密码错误
     * @apiError 1103 用户名不存在
     * @apiError 1201 发送手机注册验证码失败
     * @apiError 1202 用户注册失败
     * @apiError 1203 机构不存在
     * @apiError 1204 注册验证码输入错误
     * @apiError 1205 手机号码已存在
     * @apiError 1206 用户名已存在
     * @apiError 1207 机构不存在
     * @apiError 1208 手机或者用户名已存在
     * @apiError 4101 token过期
     * @apiError 4102 token签名错误
     * @apiError 4103 无效token
     * @apiError 4104 token格式错误
     * @apiError 5000 接口内部错误
     * @apiErrorExample 错误响应例子:
     *     {
     *       "code": 1101,
     *       "msg": "输入参数格式不正确",
     *       "res": "",
     *       "timestamp": 1489110927975
     *     }
     *
     */

    /**
     * @apiDefine success_msg 全局配置成功响应信息
     * @apiSuccess (success 2000) {Date}  timestamp     时间戳
     * @apiSuccess (success 2000) {Number} code        响应码
     * @apiSuccess (success 2000) {String}  msg       响应信息
     * @apiSuccess (success 2000) {Object}  res   响应实体
     */

    /**
     * @apiDefine token_msg 全局配置token鉴权请求头
     * @apiError 4101 token过期
     * @apiError 4102 token签名错误
     * @apiError 4103 无效token
     * @apiError 4104 token格式错误
     * @apiHeader {String}  Authorization 鉴权信息：为Bearer + "空格" +  {token}
     * @apiHeaderExample {json} 请求头例子:
     *     {
     *       "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNDg5NjAiLCJpYXQiOjE0OTUxNjYyMzgsImV4cCI6MTQ5Nzc1ODIzOH0.Mv8BfTIGxGZ6AGkYqHFTRhp40x5xHV6k7Hpwo6OdgiA"
     *     }
     */


    /**
     * @apiDefine result_msg 全局配置成功响应信息
     * @apiSuccess (success 2000) {String} success   是否成功
     * @apiSuccess (success 2000) {Number} code   返回码
     * @apiSuccess (success 2000) {String} message   返回信息
     * @apiSuccess (success 2000) {Object} data   返回数据
     *
     */

}
