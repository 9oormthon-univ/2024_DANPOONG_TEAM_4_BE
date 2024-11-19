package com.univ.sohwakhaeng.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.univ.sohwakhaeng.auth.jwt.JwtManagement;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtManagement jwtManagement;
    private static final int TOKEN_INDEX = 7;
    private static final String TYPE = "Bearer";
    private static final String HEADER = "Authorization";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        try {
            String token = resolveToken(request);
            if (StringUtils.hasText(token)) {
                jwtManagement.deleteToken(token);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred during logout: " + e.getMessage());
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(HEADER);
        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith(TYPE)) {
            return refreshToken.substring(TOKEN_INDEX);
        }
        return null;
    }
}
