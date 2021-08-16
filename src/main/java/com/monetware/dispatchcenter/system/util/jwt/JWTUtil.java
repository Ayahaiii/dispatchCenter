package com.monetware.dispatchcenter.system.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.UUID;

public class JWTUtil {

    /**
     * JWT 密钥
     */
    private static final String secret = "52MONETRINGSUPERVISE";

    /**
     * 生成TOKEN
     */
    public static String createToken() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //TODO Cookie 此处需要确认是否允许多点登陆
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return JWT.create().withClaim("uuid", uuid).sign(algorithm);
    }

    /**
     * 验证token是否合法
     */
    public static Boolean checkToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
