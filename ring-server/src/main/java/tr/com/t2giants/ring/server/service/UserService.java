package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;

import tr.com.t2giants.ring.server.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * User: sonic
 * Date: 1/7/13
 */
public interface UserService {

    User addUser(User user);

    @Secured("ROLE_USER")
    Response deactivateUser();

    @Secured("ROLE_USER")
    User updateUser(User user);

    @Secured("ROLE_USER")
    User getUser(long id);

    @Secured("ROLE_USER")
    User getUser(String username);

    @Secured("ROLE_USER")
    String addUpdateAvatar(HttpServletRequest request);

    Boolean isUserNameAvailable(String username);

    Boolean isEmailAvailable(String email);
}
