package tr.com.t2giants.ring.server.exception;

import tr.com.t2giants.ring.server.data.validator.ValidationList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: mertcaliskan
 * Date: 7/25/12
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StritFunValidationException extends RuntimeException {

    public StritFunValidationException(ValidationList validationList) {
        super(validationList.toString());
    }

    public StritFunValidationException(String message) {
        super(message);
    }
}
