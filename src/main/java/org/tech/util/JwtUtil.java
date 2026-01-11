package org.tech.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.tech.common.constants.JwtConstants;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtUtil {

    /**
     * 生成 JWT Token
     *
     * @param userId   用户ID
     * @param username 用户名
     * @param phone    手机号
     * @return Token 字符串
     */
    public static String generateToken(Long userId, String username, String phone) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstants.CLAIM_USER_ID, userId);
        claims.put(JwtConstants.CLAIM_USERNAME, username);
        claims.put(JwtConstants.CLAIM_PHONE, phone);

        return Jwts.builder()
                .claims(claims)
                .issuer(JwtConstants.ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION))
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * 解析 Token
     *
     * @param token Token 字符串
     * @return Claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 验证 Token 是否有效
     *
     * @param token Token 字符串
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从 Token 中获取用户ID
     *
     * @param token Token 字符串
     * @return 用户ID
     */
    public static Long getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.get(JwtConstants.CLAIM_USER_ID, Long.class);
    }

    /**
     * 从 Token 中获取用户名
     *
     * @param token Token 字符串
     * @return 用户名
     */
    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.get(JwtConstants.CLAIM_USERNAME, String.class);
    }

    /**
     * 从 Token 中获取手机号
     *
     * @param token Token 字符串
     * @return 手机号
     */
    public static String getPhone(String token) {
        Claims claims = parseToken(token);
        return claims.get(JwtConstants.CLAIM_PHONE, String.class);
    }

    /**
     * 获取签名密钥
     *
     * @return SecretKey
     */
    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(JwtConstants.SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private JwtUtil() {
    }
}
