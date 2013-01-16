package tr.com.t2giants.ring.server.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: sonic
 * Date: 11/13/12
 *
 * filters static contents and
 */
public class IncomingWebRequestAuditFilter implements Filter {

    private final Log logger = LogFactory.getLog(IncomingWebRequestAuditFilter.class);

    private void logErrorMessage(String logMessage, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(logMessage, e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /***
     * this method filters mainURL.com/username requests
     * it does not filter other requests such as security calls, api calls and static html calls
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println(requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
//        String queryString = ((HttpServletRequest) servletRequest).getQueryString();
//
//        boolean noNeedToFilter = requestURI.contains(".html") ||
//                requestURI.contains("/api/") ||
//                requestURI.contains("/css/") ||
//                requestURI.contains("/images/") ||
//                requestURI.contains("/js/") ||
//                requestURI.contains("/j_spring_security_check") ||
//                requestURI.equals("/") ||
//                requestURI.equals("/favicon.ico");
//
//
//        if (queryString != null && queryString.contains("haber=")) {
//            forwardRequestTo("./club.html", servletRequest, servletResponse);
//            return;
//        }
//
//        if (requestURI.contains("yorum=")) {
//            forwardRequestTo("./comment.html", servletRequest, servletResponse);
//            return;
//        }
//
//
//        if (!noNeedToFilter) {
//            String username = requestURI.substring(1, requestURI.length());
//            Supporter supporter = supporterDao.getSupporter(username);
//            if (supporter == null) {
//                forwardRequestTo("notFound.html", servletRequest, servletResponse);
//            } else if (supporter.isFootballClub()) {
//                forwardRequestTo("club.html", servletRequest, servletResponse);
//            } else if (supporter.isStadium()) {
//                forwardRequestTo("stadium.html", servletRequest, servletResponse);
//            } else {
//                forwardRequestTo("profile.html", servletRequest, servletResponse);
//            }
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

    }

//    private void forwardRequestTo(String pageURL, ServletRequest servletRequest, ServletResponse servletResponse) {
//        try {
//            servletRequest.getRequestDispatcher(pageURL).forward(servletRequest, servletResponse);
//        } catch (ServletException e) {
//            logErrorMessage("Exception occurred during forwarding forwarding to: " + pageURL, e);
//        } catch (IOException e) {
//            logErrorMessage("Exception occurred during forwarding forwarding to: " + pageURL, e);
//        }
//    }

    @Override
    public void destroy() {

    }
}
