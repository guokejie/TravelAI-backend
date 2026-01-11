package org.tech.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tech.common.constants.JwtConstants;
import org.tech.util.JwtUtil;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT 认证过滤器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 获取 Token
        String token = getTokenFromRequest(request);

        // 验证 Token 并设置认证信息
        if (token != null && JwtUtil.validateToken(token)) {
            Long userId = JwtUtil.getUserId(token);
            String username = JwtUtil.getUsername(token);

            // 创建认证信息
            User user = new User(username, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 将用户ID存储到认证信息的principal中（用于后续获取）
            request.setAttribute(JwtConstants.CLAIM_USER_ID, userId);

            // 设置认证信息到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中获取 Token
     *
     * @param request HTTP 请求
     * @return Token 字符串
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtConstants.TOKEN_HEADER);

        // 检查 Bearer Token
        if (bearerToken != null && bearerToken.startsWith(JwtConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(JwtConstants.TOKEN_PREFIX.length());
        }

        // 检查查询参数中的 token
        String token = request.getParameter(JwtConstants.TOKEN_HEADER_NAME);
        if (token != null && !token.isEmpty()) {
            return token;
        }

        return null;
    }
}
