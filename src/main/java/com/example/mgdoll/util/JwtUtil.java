package com.example.mgdoll.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mgdoll.service.impl.ManageUserInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Java web token 工具类
 */
public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 过期时间一天
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;
    /**
     * token私钥
     */
    static String TOKEN_SECRET = "d5773b0e84558211643e15bd5e46e079";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */

    @Autowired
    private static ManageUserInfoServiceImpl manageUserInfoServiceImpl;

    public static boolean verify(String token) {
        try {
//            String userName = System.getProperty("manage.name");
            String userId = getUserId(token);
            if(userId != ""){
                TOKEN_SECRET = userId;
            }
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userMobile").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,15min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username,String userId) throws UnsupportedEncodingException {
        //            过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//            私钥及加密算法
        TOKEN_SECRET = userId;

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 附带username，userId信息，生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("userMobile", username)
                .withClaim("userId",userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
