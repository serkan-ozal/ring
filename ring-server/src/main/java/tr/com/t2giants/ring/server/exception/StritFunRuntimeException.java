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
public class StritFunRuntimeException extends RuntimeException {

    public StritFunRuntimeException() {
    }

    public StritFunRuntimeException(String message) {
        super(message);
    }
}
