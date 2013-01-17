package tr.com.t2giants.ring.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: sonic
 * Date: 8/9/12
 * this exception is thrown if credentials are not valid
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RingProjectUnauthorizedException extends RuntimeException{

    public RingProjectUnauthorizedException(String message) {
        super(message);
    }
}
