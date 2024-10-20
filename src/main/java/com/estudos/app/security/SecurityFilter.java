package com.estudos.app.security;

import com.estudos.app.exception.AuthorizationHeaderException;
import com.estudos.app.exception.user.UserAuthenticationException;
import com.estudos.app.logging.SecurityLogging;
import com.estudos.app.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SecurityLogging log;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/swagger")
                || request.getRequestURI().startsWith("/v3/api-docs")
                || request.getRequestURI().startsWith("/auth/register")
                || request.getRequestURI().startsWith("/auth/login")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = this.recoverToken(request);
        String validatedToken = tokenService.validateToken(token);
        String email = tokenService.getEmailFromToken(validatedToken);

        if(email == null || email.isEmpty()){
            log.warnFindOperations("missing", "doFilterInternal", "email");
            throw new UserAuthenticationException();
        }
        if(validatedToken.isEmpty()){
            log.warnFindOperations("missing", "doFilterInternal", "token");
            throw new UserAuthenticationException();
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String recoverToken (@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader("x-auth");
        if (authHeader == null || authHeader.isEmpty()) {
            log.warnFindOperations("missing", "recoverToken", "authHeader");
            throw new AuthorizationHeaderException();
        }
        return authHeader.substring(7);
    }
}
