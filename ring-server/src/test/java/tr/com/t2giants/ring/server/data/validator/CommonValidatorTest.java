package tr.com.t2giants.ring.server.data.validator;

import tr.com.t2giants.ring.server.dao.UserDao;
import tr.com.t2giants.ring.server.data.User;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * User: sonic
 * Date: 1/15/13
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonValidatorTest {

    @InjectMocks
    private final CommonValidationsImpl impl = new CommonValidationsImpl();

    @Mock
    private UserDao<User> userDao;

    private final int USERNAME_MAX_LENGTH = 15;
    private final int PASSWORD_MAX_LENGTH = 15;
    private final int PASSWORD_MIN_LENGTH = 5;
    
    @Test
    public void validateNullUsername() {

        String username = null;

        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_USERNAME));
    }

    @Test
    public void validateEmptyUsername() {

        String username = "";

        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_USERNAME));
    }

    @Test
    public void validateAlreadyTakenUsername() {

        String username = RandomStringUtils.random(5, "ABCDEF");

        when(userDao.isUsernameAvailable(anyString(), anyLong())).thenReturn(false);
        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.USERNAME_ALREADY_TAKEN));
    }

    @Test
    public void validateMaximumUsername() {

        String username = RandomStringUtils.random(USERNAME_MAX_LENGTH + 1, "ABCDEF");

        when(userDao.isUsernameAvailable(username, -1)).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.USERNAME_MAX_CHAR_EXCEEDED));
    }

    @Test
    public void validateUserWithoutError() {

        String username = RandomStringUtils.random(USERNAME_MAX_LENGTH -1, "abc");
        String email = RandomStringUtils.random(249, "abc") + "@a.com";
        String password = RandomStringUtils.random(PASSWORD_MAX_LENGTH -1, "abcdefgh");

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        when(userDao.isUsernameAvailable(username, -1)).thenReturn(true);

        ErrorMessages errorMessage = impl.validateUsername(username, -1);
        assertThat(errorMessage, is(nullValue()));

        errorMessage = impl.validateEmail(email, -1);
        assertThat(errorMessage, is(nullValue()));

        errorMessage = impl.validatePassword(password);
        assertThat(errorMessage, is(nullValue()));
    }

    @Test
    public void validateWhiteSpaceUsername() {

        String username = RandomStringUtils.random(5, "ABCDEF") + " 1";

        when(userDao.isUsernameAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.USERNAME_CONTAINS_SPACE));
    }

    @Test
    public void validateInvalidUsername() {

        String username = RandomStringUtils.random(15, "ü1234");

        when(userDao.isUsernameAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateUsername(username, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_USERNAME));
    }

    @Test
    public void validateEmptyEmail() {

        String email = "";

        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_EMAIL));
    }

    @Test
    public void validateNullEmail() {

        String email = null;

        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_EMAIL));
    }

    @Test
    public void validateAlreadyUsedEmail() {

        String email = "asd";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(false);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.EMAIL_ALREADY_USED));
    }

    @Test
    public void validateInvalidEmail() {

        String email = "a@a";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    public void validateInvalidEmail2() {

        String email = "aa.com";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    public void validateInvalidEmail3() {

        String email = "@a.com";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    public void validateInvalidEmail4() {

        String email = "ş@a.com";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    public void validateInvalidEmail5() {

        String email = "a@.com";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.INVALID_EMAIL));
    }

    @Test
    public void validateMaxEmail() {

        String email = RandomStringUtils.random(250, "abc") + "@a.com";

        when(userDao.isEmailAvailable(anyString(), anyLong())).thenReturn(true);
        final ErrorMessages errorMessage = impl.validateEmail(email, -1);

        assertThat(errorMessage, is(ErrorMessages.EMAIL_MAX_LENGTH_EXCEEDED));
    }

    @Test
    public void validateEmptyPassword() {

        String password = "";

        final ErrorMessages errorMessage = impl.validatePassword(password);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_PASSWORD));
    }

    @Test
    public void validateNullPassword() {

        String password = null;

        final ErrorMessages errorMessage = impl.validatePassword(password);

        assertThat(errorMessage, is(ErrorMessages.EMPTY_PASSWORD));
    }

    @Test
    public void validateMinPassword() {

        String password = RandomStringUtils.random(PASSWORD_MIN_LENGTH - 1, "abcd");

        final ErrorMessages errorMessage = impl.validatePassword(password);

        assertThat(errorMessage, is(ErrorMessages.PASSWORD_TOO_SHORT));
    }

    @Test
    public void validateMaxPassword() {

        String password = RandomStringUtils.random(PASSWORD_MAX_LENGTH + 1, "abcd");

        final ErrorMessages errorMessage = impl.validatePassword(password);

        assertThat(errorMessage, is(ErrorMessages.PASSWORD_MAX_CHAR_EXCEEDED));
    }
}
