package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.BaseRestTestCase;
import tr.com.t2giants.ring.server.data.Coordinates;
import tr.com.t2giants.ring.server.data.FunItem;
import tr.com.t2giants.ring.server.data.builder.CoordinatesBuilder;
import tr.com.t2giants.ring.server.data.builder.FunItemBuilder;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import tr.com.t2giants.ring.server.util.WebDesignParameters;
import org.apache.commons.lang.RandomStringUtils;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * User: sonic
 * Date: 1/15/13
 */
public class FunServiceTest extends BaseRestTestCase {

    @Test
    public void addFunItemWithoutLocation() {

        final String token = getAuthTokenUser();
        final String comment = RandomStringUtils.random(199, "comment withspace");

        File file = new File("src/test/resources/funItem.jpg");

        given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                multiPart(file).

                expect().statusCode(OK).

                body(ENTITY, is(ErrorMessages.INVALID_COORDINATES.getErrorMessage())).
                body(STATUS, is(BAD_REQUEST)).

                when().post(BASE_JETTY_API_URL + "/fun-items/add?comment=" + comment);
    }

    @Test
    public void addFunItemWithoutLongitude() {

        final String token = getAuthTokenUser();

        final String comment = RandomStringUtils.random(199);

        File file = new File("src/test/resources/funItem.jpg");

        given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                multiPart(file).

                expect().statusCode(OK).

                body(ENTITY, is(ErrorMessages.INVALID_COORDINATES.getErrorMessage())).
                body(STATUS, is(BAD_REQUEST)).

                when().post(BASE_JETTY_API_URL + "/fun-items/add?lat=1&comment=" + comment);
    }

    @Test
    public void addFunItemWithoutLatitude() {

        final String token = getAuthTokenUser();

        final String comment = RandomStringUtils.random(199);

        File file = new File("src/test/resources/funItem.jpg");

        given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                multiPart(file).

                expect().statusCode(OK).

                body(ENTITY, is(ErrorMessages.INVALID_COORDINATES.getErrorMessage())).
                body(STATUS, is(BAD_REQUEST)).

                when().post(BASE_JETTY_API_URL + "/fun-items/add?lon=1&comment=" + comment);
    }

    @Test
    public void addFunItemSuccessfully() {

        final String token = getAuthTokenUser();

        final String comment = RandomStringUtils.random(199, "random comment");

        File file = new File("src/test/resources/funItem.jpg");

        given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                multiPart(file).

                expect().statusCode(OK).

                body("location.lon", is(13f)).
                body("location.lat", is(12f)).
                body("comment", is(comment)).
                body("photoURL", containsString(CLOUD_DOWNLOAD_PATH + "/fun-items/")).
                body("userID", is(2)).

                when().post(BASE_JETTY_API_URL + "/fun-items/add?comment=" + comment + "&lat=12&lon=13");
    }
}
