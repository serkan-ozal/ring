package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * User: mertcaliskan
 * Date: 11/14/12
 */
public interface SecurityService extends UserDetailsService {

    @Secured({"ROLE_ADMIN", "ROLE_SUPPORTER"})
    void logout(UserDetails userDetails);
}
