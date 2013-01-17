package tr.com.t2giants.ring.server.domain.validator;

import com.mysql.jdbc.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.dao.UserDao;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import tr.com.t2giants.ring.server.util.WebDesignParameters;

/**
 * User: sonic
 * Date: 1/13/13
 */
@Service
public class CommonValidationsImpl implements CommonValidator {

    @Autowired
    private UserDao userDao;

    private final UrlValidator urlValidator = new UrlValidator();

    private static final int MAX_LENGTH_USERNAME = 15;

    private static final int EMAIL_MAX_LENGTH = 255;

    private final int PASSWORD_MAX_LENGTH = 15;

    private final int PASSWORD_MIN_LENGTH = 5;

    public ErrorMessages validateUsername(String username, long id) {

        if (StringUtils.isNullOrEmpty(username)) {
            return ErrorMessages.EMPTY_USERNAME;
        } else {
            if (!userDao.isUsernameAvailable(username, id)) {
                return ErrorMessages.USERNAME_ALREADY_TAKEN;
            }
            if (username.length() > MAX_LENGTH_USERNAME) {
                return ErrorMessages.USERNAME_MAX_CHAR_EXCEEDED;
            }
            if (org.springframework.util.StringUtils.containsWhitespace(username)) {
                return ErrorMessages.USERNAME_CONTAINS_SPACE;
            }
            if (!urlValidator.isValid(WebDesignParameters.DEFAULT_URL_PATH + username)) {
                return ErrorMessages.INVALID_USERNAME;
            }
        }
        return null;
    }

    public ErrorMessages validateEmail(String email, long id) {
        if (StringUtils.isNullOrEmpty(email)) {
            return ErrorMessages.EMPTY_EMAIL;
        } else {
            if (!userDao.isEmailAvailable(email, id)) {
                return ErrorMessages.EMAIL_ALREADY_USED;
            }
            if (!EmailValidator.getInstance().isValid(email)) {
                return ErrorMessages.INVALID_EMAIL;
            }
            if (email.length() > EMAIL_MAX_LENGTH) {
                return ErrorMessages.EMAIL_MAX_LENGTH_EXCEEDED;
            }
        }
        return null;
    }

    @Override
    public ErrorMessages validatePassword(String password) {
        if (StringUtils.isNullOrEmpty(password)) {
            return ErrorMessages.EMPTY_PASSWORD;
        } else {
            if (password.length() > PASSWORD_MAX_LENGTH) {
                return ErrorMessages.PASSWORD_MAX_CHAR_EXCEEDED;
            }
            if (password.length() < PASSWORD_MIN_LENGTH) {
                return ErrorMessages.PASSWORD_TOO_SHORT;
            }
        }
        return null;
    }
}
