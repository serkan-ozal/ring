package tr.com.t2giants.ring.server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * User: sonic
 * Date: 1/15/13
 */
public class BaseRestTestCase extends BaseTestCase {

    protected static final String JSON = MediaType.APPLICATION_JSON;

    protected static final String CLOUD_DOWNLOAD_PATH = "http://d1dgt2apkkbq37.cloudfront.net";
    public static final String CLOUD_STREAM_PATH = "s1cp0j2rq4xbq.cloudfront.net";
    public static final String CLOUD_S3_CONTENT_PATH = "http://s3.amazonaws.com/idacontent";
    protected static final String CLOUD_S3_STREAM_PATH = "http://s3.amazonaws.com/idastreaming";

    protected static final int INTERNAL_SERVER_ERROR = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    protected static final int BAD_REQUEST = Response.Status.BAD_REQUEST.getStatusCode();
    protected static final int OK = Response.Status.OK.getStatusCode();
    protected static final int UNAUTHORIZED = Response.Status.UNAUTHORIZED.getStatusCode();
    protected static final int FORBIDDEN = Response.Status.FORBIDDEN.getStatusCode();
    protected static final int FOUND = 302;
    protected static final int NOT_FOUND = Response.Status.NOT_FOUND.getStatusCode();

    private static final Integer SERVER_PORT = 9090;
    protected static final String BASE_JETTY_URL = "http://localhost:" + SERVER_PORT;
    protected static final String BASE_JETTY_API_URL = BASE_JETTY_URL + "/api";

    protected static final String STATUS = "status";
    protected static final String ENTITY = "entity";

    private static Server server;
    protected final RestTemplate restTemplate;

    public BaseRestTestCase() {
        restTemplate = new RestTemplate();
    }

    @BeforeClass
    public static void setUpBase() throws Exception {
        server = new Server(SERVER_PORT);
        WebAppContext appContext = new WebAppContext();
        appContext.setContextPath("/");

        File rd = new File(".");
        File warPath = new File(rd.getCanonicalPath(), "/src/main/webapp");

        appContext.setWar(warPath.getAbsolutePath());

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{appContext, new DefaultHandler()});

        server.setHandler(handlers);
        server.start();
    }

    @AfterClass
    public static void finishBase() throws Exception {
        server.stop();
    }

    protected String getAuthTokenUser() {
        return getAuthToken("abc", "admin");
    }

    protected String getAuthToken(String username, String password) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        requestHeaders.set(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY, password);
        requestHeaders.set(AbstractRememberMeServices.DEFAULT_PARAMETER, "true");

        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
        HttpEntity<String> response = restTemplate.exchange(BASE_JETTY_URL + "/j_spring_security_check", HttpMethod.GET, requestEntity, String.class);

        return response.getHeaders().getFirst(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
    }
}
