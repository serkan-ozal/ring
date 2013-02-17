package tr.com.t2giants.ring.server.service;

import org.junit.Test;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import tr.com.t2giants.ring.server.BaseRestTestCase;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: sonic
 * Date: 2/17/13
 */
public class LocationServiceTestCase extends BaseRestTestCase {

    @Test
    public void addLocation() {
        final String token = getAuthTokenUser();

        final String s = given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                expect().statusCode(OK).

                body(STATUS, is(OK)).

                when().post(BASE_JETTY_API_URL + "/locations/add-current/12.1/11.1").asString();
        System.out.println(s);

    }

    @Test
    public void getLastLocation() {
        final String token = getAuthTokenUser();

        final String s = given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                expect().statusCode(OK).

                when().get(BASE_JETTY_API_URL + "/locations/last-position/4").asString();
        System.out.println(s);

    }
}
