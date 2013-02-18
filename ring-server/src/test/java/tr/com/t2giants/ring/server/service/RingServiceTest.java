package tr.com.t2giants.ring.server.service;

import org.junit.Test;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import tr.com.t2giants.ring.server.BaseRestTestCase;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: sonic
 * Date: 2/18/13
 */
public class RingServiceTest extends BaseRestTestCase {

    @Test
    public void getFriendship() {
        final String token = getAuthTokenUser();

        final String s = given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                expect().statusCode(OK).

                body(STATUS, is(OK)).

                when().get(BASE_JETTY_API_URL + "/ring/friends/get/4").asString();
        System.out.println(s);
    }
}
