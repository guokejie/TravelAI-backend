package org.tech.common.constants;

/**
 * JWT 常量定义
 */
public class JwtConstants {

    /**
     * Token 请求头名称
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Token 前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Token 在请求头中的名称
     */
    public static final String TOKEN_HEADER_NAME = "token";

    /**
     * Token 过期时间（7天）单位：毫秒
     */
    public static final long EXPIRATION = 7 * 24 * 60 * 60 * 1000;

    /**
     * JWT 签名密钥
     */
    public static final String SECRET = "ai-travel-planner-secret-key-2024";

    /**
     * JWT 签发者
     */
    public static final String ISSUER = "ai-travel-planner";

    /**
     * 用户ID Claim名称
     */
    public static final String CLAIM_USER_ID = "userId";

    /**
     * 用户名 Claim名称
     */
    public static final String CLAIM_USERNAME = "username";

    /**
     * 手机号 Claim名称
     */
    public static final String CLAIM_PHONE = "phone";

    private JwtConstants() {
    }
}
