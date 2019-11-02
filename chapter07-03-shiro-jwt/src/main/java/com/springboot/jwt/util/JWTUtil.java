package com.springboot.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@SuppressWarnings("all")
public class JWTUtil {

    /**
     * JWT验证过期时间 EXPIRE_TIME 分钟
     */
    private static final long EXPIRE_TIME = 1 * 60 * 1000;

    /**
     * @param token    密钥
     * @param username
     * @param secret   密码
     * @return
     * @描述: 校验token是否正确
     * @方法名: verify
     * @返回类型 boolean
     * @创建人 T-liul4
     * @创建时间 2019年7月25日下午3:30:09
     */
    public static boolean verify(String token, String username, String secret) {
        log.info("token:{},username:{},secret:{}", token, username, secret);
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("JwtUtil登录验证成功!");
            return true;
        } catch (Exception exception) {
            log.error("JwtUtil登录验证失败!" + exception);
            return false;
        }

    }

    /**
     * @param token
     * @return token中包含的用户名
     * @描述: 获得token中的信息无需secret解密也能获得
     * @方法名: getUsername
     * @返回类型 String
     * @创建人 T-liul4
     * @创建时间 2019年7月25日下午3:30:42
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @param username
     * @param secret
     * @return
     * @throws UnsupportedEncodingException
     * @throws IllegalArgumentException
     * @描述: 生成token签名EXPIRE_TIME 分钟后过期
     * @方法名: sign
     * @返回类型 String
     * @创建人 T-liul4
     * @创建时间 2019年7月25日下午3:31:14
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        /**
         * 测试生成一个token
         */
        String sign = sign("tom", "123");
        log.warn("测试生成一个token\n" + sign);
    }

}
