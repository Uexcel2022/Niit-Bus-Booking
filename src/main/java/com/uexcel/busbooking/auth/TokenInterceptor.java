package com.uexcel.busbooking.auth;

import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.UtilsToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private final UtilsToken utils = new UtilsToken();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("rapid-transit");
        if (authHeader == null || authHeader.isBlank()) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Missing token");
//            return false;
            throw new CustomException("Missing token", "401");
        }
        if (utils.validateEmailFromToken(authHeader)) {
            log.info("authHeader: " + utils.decode(authHeader));
            return true;
        }
        response.sendError(HttpServletResponse.SC_FORBIDDEN,"Invalid token");
        return false;
    }
}
