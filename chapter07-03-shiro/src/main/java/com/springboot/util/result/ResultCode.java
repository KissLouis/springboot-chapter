package com.springboot.util.result;

/**
 * @author Louis
 * @title: ResultCode
 * @projectName springboot-study
 * @description: TODO
 * @date 2019/5/27 21:24
 */
public enum ResultCode {

	/**
	 * 操作成功！
	 */
	SUCCESS(true, 200, "操作成功！"),
	/**
	 * 登录成功！
	 */
	LOGIN_SUCCESS(true, 200, "登录成功！"),

	/**
	 * 登录失败
	 */
	LOGIN_FAIL(true, 405, "登录失败！"),
	/**
	 * 已退出登录！
	 */
	LOGOUT_SUCCESS(true, 200, "已退出登录！"),
	// ---系统错误返回码-----
	/**
	 * 数据已存在
	 */
	SUCCESS_IS_HAVE(false, 208, "数据已存在"),
	/**
	 * 操作失败
	 */
	FAIL(false, 205, "操作失败"),
	/**
	 * 您还未登录
	 */
	UNAUTHENTICATED(false, 402, "您还未登录"),
	/**
	 * 接口不存在
	 */
	NOT_FOUND(false, 404, "接口不存在"),
	/**
	 * 权限不足
	 */
	UNAUTHORISE(false, 401, "权限不足"),

	/**
	 * 没有结果
	 */
	NOT_DATA(false, 911, "没有结果"),

	/**
	 * 没有登录
	 */
	NOT_LOGIN(false, 600, "没有登录"),

	/**
	 * 发生异常
	 */
	EXCEPTION(false, 401, "发生异常"),

	/**
	 * 系统错误
	 */
	SYS_ERROR(false, 402, "系统错误"),

	/**
	 * 参数错误
	 */
	PARAMS_ERROR(false, 403, "参数错误 "),

	/**
	 * 不支持或已经废弃
	 */
	NOT_SUPPORTED(false, 410, "不支持或已经废弃"),

	/**
	 * AuthCode错误
	 */
	INVALID_AUTHCODE(false, 444, "无效的AuthCode"),

	/**
	 * 太频繁的调用
	 */
	TOO_FREQUENT(false, 445, "太频繁的调用"),

	/**
	 * 未知的错误
	 */
	UNKNOWN_ERROR(false, 499, "未知错误"),

	/**
	 * 未设置方法
	 */
	NOT_METHOD(false, 4004, "未设置方法"),

	/**
	 * 系统错误
	 */
	SERVER_ERROR(false, 500, "抱歉，系统繁忙，请稍后重试！");

	// ---用户操作返回码----
	// ---企业操作返回码----
	// ---权限操作返回码----
	// ---其他操作返回码----

	// 操作是否成功
	boolean success;
	// 操作代码
	int code;
	// 提示信息
	String message;

	ResultCode(boolean success, int code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public boolean success() {
		return success;
	}

	public int code() {
		return code;
	}

	public String message() {
		return message;
	}

}
