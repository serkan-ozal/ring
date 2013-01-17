package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.core.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * User: sonic
 * Date: 1/7/13
 */
public interface UserServiceHelper {

    User addUser(User user);

    User updateUser(Long id, User user);

    String addUpdateAvatar(Long userID, HttpServletRequest request);

    Boolean isUsernameAvailable(String username, Long id);

    Boolean isEmailAvailable(String email, Long id);

    User getUser(long id);

    User getUser(String username);

    String getAvatarThumb(long id);

    String getAvatar(long id);
}
