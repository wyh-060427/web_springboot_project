package com.wyh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 1. 秘钥，与测试类保持一致
    private static final String SECRET_KEY = "d3lobGQ=";

    // 2. 过期时间：12小时 (12小时 * 60分钟 * 60秒 * 1000毫秒)
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义的载荷数据（如 id, username）
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定加密算法和秘钥
                .addClaims(claims)                               // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 设置12小时过期
                .compact();                                      // 生成并压缩成字符串
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token 传入的 JWT 字符串
     * @return 解析出来的 Claims 对象（包含自定义信息）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 必须使用生成时的同一个秘钥
                .parseClaimsJws(token)     // 解析并校验令牌
                .getBody();                // 获取载荷信息
    }
}
