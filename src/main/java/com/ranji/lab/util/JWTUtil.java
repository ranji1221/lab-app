package com.ranji.lab.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

/**
 * ClassName:    JWTUtil
 * Package:    com.ranji.lab.util
 * Description: JWT工具类
 * Datetime:    2020/9/11   6:00 下午
 * Author:   ranji
 */
public class JWTUtil {
    //-- 过期时间为 24小时
    private static final long EXPIRE_TIME = 60*60*24*1000;

    //-- 密钥
    private static final String SECRET = "RanJi-2770069467";

    /**
     * 根据登陆用户名生成token,24小时后过期
     * @param userName
     * @return
     */
    public static String createToken(String userName){
        //-- 1. 过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //-- 2. 加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        //--  附带username信息
        return JWT.create()
                //-- 带用户名的token
                .withClaim("userName",userName)
                //-- 到期时间
                .withExpiresAt(date)
                //-- 签名
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token
     * @param userName
     * @return
     */
    public static  boolean verifyToken(String token,String userName){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userName", userName)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}