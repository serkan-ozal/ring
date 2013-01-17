package tr.com.t2giants.ring.server.data.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.core.domain.builder.UserBuilder;
import tr.com.t2giants.ring.server.helper.ValidUserValues;
import tr.com.t2giants.ring.server.util.ErrorMessages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * User: sonic
 * Date: 1/15/13
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @InjectMocks
    private final UserValidator impl = new UserValidator();

    @Mock
    private CommonValidator commonValidator;

    @Test
    public void validateNullFullName() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(validUserValues.getValidBirthDate()).
                fullName(null).build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.EMPTY_FULL_NAME.getErrorMessage()));
        assertThat(validationList.size(), is(1));
    }

    @Test
    public void validateAbout() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(validUserValues.getValidBirthDate()).
                about(validUserValues.getMaxAbout() + "a").
                fullName(validUserValues.getMaxFullName()).build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateUpdateData(user);

        assertThat(validationList.get(0), is(ErrorMessages.COMMENT_MAX_CHAR_EXCEEDED.getErrorMessage()));
        assertThat(validationList.size(), is(1));
        assertThat(validationList.isEmpty(), is(false));
        assertThat(validationList.iterator().hasNext(), is(true));
    }

    @Test
    public void validateEmptyFullName() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(validUserValues.getValidBirthDate()).
                fullName("").build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.EMPTY_FULL_NAME.getErrorMessage()));
        assertThat(validationList.size(), is(1));
    }

    @Test
    public void validateMaxCharExceededFullName() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(validUserValues.getValidBirthDate()).
                fullName(validUserValues.getMaxFullName() + "a").build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.FULL_NAME_MAX_CHAR_EXCEEDED.getErrorMessage()));
        assertThat(validationList.size(), is(1));
    }

    @Test
    public void validateEmptyAge() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(-1).
                fullName(validUserValues.getMaxFullName()).build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.EMPTY_AGE.getErrorMessage()));
        assertThat(validationList.size(), is(1));
    }

    @Test
    public void validateInvalidAge() {

        long oneDayInMillis = 24 * 60 * 60 * 1000;

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword()).
                birthDate(validUserValues.getValidBirthDate() + oneDayInMillis).
                fullName(validUserValues.getMaxFullName()).build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(null);
        when(commonValidator.validatePassword(anyString())).thenReturn(null);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.INVALID_AGE.getErrorMessage()));
        assertThat(validationList.size(), is(1));
    }

    @Test
    public void validateInvalidUsernameFullNamePassword() {

        ValidUserValues validUserValues = new ValidUserValues();

        final User user = new UserBuilder().
                username(validUserValues.getMaxUsername()).
                email(validUserValues.getMaxEmail()).
                password(validUserValues.getMaxPassword() + "a").
                birthDate(validUserValues.getValidBirthDate()).
                fullName(validUserValues.getMaxFullName()).build();

        when(commonValidator.validateEmail(anyString(), anyLong())).thenReturn(ErrorMessages.EMAIL_MAX_LENGTH_EXCEEDED);
        when(commonValidator.validateUsername(anyString(), anyLong())).thenReturn(ErrorMessages.USERNAME_MAX_CHAR_EXCEEDED);
        when(commonValidator.validatePassword(anyString())).thenReturn(ErrorMessages.PASSWORD_MAX_CHAR_EXCEEDED);

        ValidationList validationList = impl.validateCreationData(user);

        assertThat(validationList.get(0), is(ErrorMessages.USERNAME_MAX_CHAR_EXCEEDED.getErrorMessage()));
        assertThat(validationList.get(1), is(ErrorMessages.EMAIL_MAX_LENGTH_EXCEEDED.getErrorMessage()));
        assertThat(validationList.get(2), is(ErrorMessages.PASSWORD_MAX_CHAR_EXCEEDED.getErrorMessage()));
        assertThat(validationList.size(), is(3));
    }

}
