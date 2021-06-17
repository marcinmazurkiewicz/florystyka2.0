package dev.mazurkiewicz.florystyka.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import dev.mazurkiewicz.florystyka.exception.ErrorResponse;
import dev.mazurkiewicz.florystyka.exception.TokenExpiredException;
import dev.mazurkiewicz.florystyka.exception.UnauthorizedAccessException;
import dev.mazurkiewicz.florystyka.exception.validation.ErrorType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("jwtTokenFilter")
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(jwtProperties.getAuthorizationHeader());

        if (Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith(jwtProperties.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.replace(jwtProperties.getTokenPrefix(), "").trim();
        try {
            Jws<Claims> claimsJws = jwtTokenUtil.getJwt(token);
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<?> authList = (List<?>) body.get("authorities");
            Map<String, Object> details = new HashMap<>();
            details.put("userId", body.get("userId"));
            Set<SimpleGrantedAuthority> authorities = authList.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                    .collect(Collectors.toSet());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                    (username, null, authorities);
            authentication.setDetails(details);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (UnauthorizedAccessException | TokenExpiredException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(),
                    request.getRequestURI(), ErrorType.valueOfCode(ex.getClass().getSimpleName()));
            PrintWriter writer = response.getWriter();
            writer.write(convertObjectToJson(errorResponse));
            writer.flush();
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
