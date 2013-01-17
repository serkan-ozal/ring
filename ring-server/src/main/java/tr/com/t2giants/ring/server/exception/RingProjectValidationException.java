package tr.com.t2giants.ring.server.exception;

import tr.com.t2giants.ring.server.domain.validator.ValidationList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RingProjectValidationException extends RuntimeException {

    public RingProjectValidationException(ValidationList validationList) {
        super(validationList.toString());
    }

    public RingProjectValidationException(String message) {
        super(message);
    }
}
