package tr.com.t2giants.ring.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: mertcaliskan
 * Date: 7/24/12
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RingProjectNotFoundException extends RuntimeException {

    public RingProjectNotFoundException(String message) {
        super(message);
    }
}
