package tr.com.t2giants.ring.server.data.validator;

import tr.com.t2giants.ring.server.util.ErrorMessages;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface CommonValidator {

    ErrorMessages validateUsername(String username, long id);

    ErrorMessages validateEmail(String email, long id);

    ErrorMessages validatePassword(String password);
}
