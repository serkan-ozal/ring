package tr.com.t2giants.ring.server.data.validator;

import com.mysql.jdbc.StringUtils;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.server.util.ErrorMessages;

/**
 * User: sonic
 * Date: 1/13/13
 */
@Service
public class UserValidator implements Validator<User> {

    private static final int FULL_NAME_MAX_LENGTH = 40;

    private static final int ABOUT_MAX_LENGTH = 200;

    private static final int MINIMUM_AGE = 13;

    @Autowired
    private CommonValidator commonValidator;

    private ValidationList validationList;

    public ValidationList validateCreationData(User user) {
        validationList = new ValidationList();

        validateUserName(user.getUsername(), -1);
        validateEmail(user.getEmail(), -1);
        validateFullName(user.getFullName());
        validateBirthDate(user.getBirthDate());
        validatePassword(user.getPassword());
        return validationList;
    }

    @Override
    public ValidationList validateUpdateData(User user) {
        validationList = new ValidationList();

        validateUserName(user.getUsername(), user.getId());
        validateEmail(user.getEmail(), user.getId());
        validateFullName(user.getFullName());
        validateBirthDate(user.getBirthDate());
        validateAbout(user.getAbout());
        return validationList;
    }

    private void validateAbout(String about) {
        if (!StringUtils.isNullOrEmpty(about) && about.length() > ABOUT_MAX_LENGTH) {
            addValidationError(ErrorMessages.COMMENT_MAX_CHAR_EXCEEDED);
        }
    }

    private void validatePassword(String password) {
        ErrorMessages errorMessages = commonValidator.validatePassword(password);
        if (errorMessages != null) {
            addValidationError(errorMessages);
        }
    }

    private void validateBirthDate(long birthDate) {
        DateTime now = new DateTime();
        if (birthDate != -1) {
            Years age = Years.yearsBetween(new DateMidnight().withMillis(birthDate), now);
            if (age.isLessThan(Years.years(MINIMUM_AGE))) {
                addValidationError(ErrorMessages.INVALID_AGE);
            }
        } else {
            addValidationError(ErrorMessages.EMPTY_AGE);
        }
    }

    private void validateFullName(String fullName) {
        if (StringUtils.isNullOrEmpty(fullName)) {
            addValidationError(ErrorMessages.EMPTY_FULL_NAME);
        } else if (fullName.length() > FULL_NAME_MAX_LENGTH) {
            addValidationError(ErrorMessages.FULL_NAME_MAX_CHAR_EXCEEDED);
        }
    }

    private void validateEmail(String email, long id) {
        ErrorMessages errorMessages = commonValidator.validateEmail(email, id);
        if (errorMessages != null) {
            addValidationError(errorMessages);
        }
    }

    private void validateUserName(String username, long id) {
        ErrorMessages errorMessages = commonValidator.validateUsername(username, id);
        if (errorMessages != null) {
            addValidationError(errorMessages);
        }
    }

    public void addValidationError(ErrorMessages errorMessages) {
        validationList.add(errorMessages.getErrorMessage());
    }
}
