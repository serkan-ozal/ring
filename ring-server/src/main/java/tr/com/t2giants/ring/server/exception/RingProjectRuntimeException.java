package tr.com.t2giants.ring.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: mertcaliskan
 * Date: 7/25/12
 *
 * This exception is thrown when a runtime exception occurs like saving an object via DAO.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RingProjectRuntimeException extends RuntimeException {

    public RingProjectRuntimeException() {
    }

    public RingProjectRuntimeException(String message) {
        super(message);
    }
}
