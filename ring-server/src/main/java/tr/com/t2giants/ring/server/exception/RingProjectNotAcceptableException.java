package tr.com.t2giants.ring.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: sonic
 * Date: 11/12/12
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RingProjectNotAcceptableException extends RuntimeException{

    public RingProjectNotAcceptableException() {
        super();
    }
}
