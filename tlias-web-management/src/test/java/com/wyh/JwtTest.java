package com.wyh;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenerateJwt(){
        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"d3lobGQ=") //指定加密算法，密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }
    @Test
    public void testParseJWT() {
        // 注意：截图中间部分被遮挡，Token 字符串已用省略号代替
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc5MzQ1MDE5MH0.RlpYJ9zN7gFDs-lkcKeMXMqrDautGRXRm-R7KBg29zE";

        Claims claims = Jwts.parser().setSigningKey("d3lobGQ=")//指定密钥
                .parseClaimsJws(token)//解析令牌
                .getBody();//获取自定义信息

        System.out.println(claims);
    }
}
