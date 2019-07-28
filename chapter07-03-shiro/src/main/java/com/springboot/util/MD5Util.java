package com.springboot.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {

	/**
	 * 
	 * @描述: 密码加密
	 * @方法名: MD5Pwd
	 * @param username
	 * @param pwd
	 * @return
	 * @返回类型 String
	 * @创建人 T-liul4
	 * @创建时间 2019年7月23日下午2:41:34
	 */
	public static String MD5Pwd(String username, String pwd) {
		String md5Pwd = new SimpleHash("MD5", pwd, ByteSource.Util.bytes(username + "salt"), 2).toHex();
		return md5Pwd;
	}

}
