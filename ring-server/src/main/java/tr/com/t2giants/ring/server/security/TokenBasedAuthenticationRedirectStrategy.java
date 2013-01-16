package tr.com.t2giants.ring.server.security;

import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: mertcaliskan
 * Date: 6/27/12
 */
public class TokenBasedAuthenticationRedirectStrategy extends DefaultRedirectStrategy {

    private String defaultTargetUrl;

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        try {
            request.getRequestDispatcher(defaultTargetUrl).forward(request, response);
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultTargetUrl(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }
}