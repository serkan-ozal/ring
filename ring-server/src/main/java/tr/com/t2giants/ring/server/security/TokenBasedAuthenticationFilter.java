package tr.com.t2giants.ring.server.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class TokenBasedAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    protected String obtainPassword(HttpServletRequest request) {
        return request.getHeader(SPRING_SECURITY_FORM_PASSWORD_KEY);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getHeader(SPRING_SECURITY_FORM_USERNAME_KEY);
    }
}
