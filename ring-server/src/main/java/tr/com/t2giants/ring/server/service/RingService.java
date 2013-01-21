package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;
import tr.com.t2giants.ring.server.domain.User;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface RingService {

    @Secured("ROLE_USER")
    Response addToRing(long id);

    @Secured("ROLE_USER")
    Response removeFromRing(long id);

    @Secured("ROLE_USER")
    List<User> getRingOfUser(long id);

}
