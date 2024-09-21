package team.hackerping.nanuri.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import team.hackerping.nanuri.security.application.JwtProvider;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JwtProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var httpServletRequest = (HttpServletRequest) request;
        var token = httpServletRequest.getHeader("Authorization");

        if (isInvalidToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        var authentication = getUsernamePasswordAuthenticationToken(token.substring(7));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        var claims = jwtTokenProvider.getAccessClaims(token);
        var role = (String) claims.get("role");
        log.info("role: {}", role);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> role);
        log.info("claims: {}", claims);
        return new UsernamePasswordAuthenticationToken(claims.get("id"), null, authorities);
    }

    private boolean isInvalidToken(String token) {
        return isInvalidTokenForm(token) || jwtTokenProvider.isInvalidAccessToken(token.substring(7));
    }

    private boolean isInvalidTokenForm(String token) {
        return Objects.isNull(token) || !token.startsWith("Bearer ");
    }
}
