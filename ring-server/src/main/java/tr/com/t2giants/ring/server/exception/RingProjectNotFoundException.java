package tr.com.t2giants.ring.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RingProjectNotFoundException extends RuntimeException {

    public RingProjectNotFoundException(String message) {
        super(message);
    }
}

