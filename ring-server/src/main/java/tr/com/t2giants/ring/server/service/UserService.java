package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.data.User;
import org.springframework.security.access.annotation.Secured;

import javax.servlet.http.HttpServletRequest;

/**
 * User: sonic
 * Date: 1/7/13
 */
public interface UserService {

    User addUser(User user);

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
