package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    @Secured({"ROLE_USER"})
    void logout(UserDetails userDetails);
}
