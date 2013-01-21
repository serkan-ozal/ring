package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;

import javax.ws.rs.core.Response;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface PushService {

    @Secured("ROLE_USER")
    Response addGCMRegistrationID(String gcmRegID);

}
