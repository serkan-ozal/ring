package tr.com.t2giants.ring.server.service;

import org.junit.Test;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import tr.com.t2giants.ring.server.BaseRestTestCase;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

/**
 * User: sonic
 * Date: 1/15/13
 */
public class UserServiceTest extends BaseRestTestCase {

    @Test
    public void createUser() {

        final String token = getAuthTokenUser();

        File file = new File("src/test/resources/funItem.jpg");

        given().header(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, token).
                multiPart(file).
                expect().statusCode(OK).
                when().post(BASE_JETTY_API_URL + "/users/avatar");

//        User user = new UserBuilder().username(getMaxUsername() + "x").fullName(getMaxFullName()).password(getMaxPassword()).email(getMaxEmail())
//                .about(getMaxAbout()).avatarImage(getRandomImageURL()).avatarThumbnail(getRandomImageURL()).credentialsNonExpired(false)
//                .accountNonLocked(false).activated(true).enabled(false).birthDate(getValidBirthDate()).funItemCount(123)
//                .peopleLikedFunItemCount(345).funItemLikedCount(456).creationTime(1233333).build();
//
//        given().contentType(JSON).body(user).
//                expect().statusCode(OK).
//
//                body(STATUS, is(BAD_REQUEST)).
//                body(ENTITY, is(ErrorMessages.USERNAME_MAX_CHAR_EXCEEDED.getErrorMessage())).
//
//                when().post(BASE_JETTY_API_URL + "/users/add");
    }

}
