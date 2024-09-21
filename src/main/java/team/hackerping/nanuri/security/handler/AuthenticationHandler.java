package team.hackerping.nanuri.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import team.hackerping.nanuri.security.application.JwtService;
import team.hackerping.nanuri.security.application.dto.PrincipleDetails;
import team.hackerping.nanuri.security.application.dto.TokenDetails;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var principleDetails = (PrincipleDetails) authentication.getPrincipal();
        var tokens = jwtService.generateLoginToken(principleDetails.getClaims());

        setTokenResponse(response, tokens);
    }

    private void setTokenResponse(HttpServletResponse response, TokenDetails tokens)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(tokens));
        response.getWriter().flush();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("Authentication failed: {}", exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
