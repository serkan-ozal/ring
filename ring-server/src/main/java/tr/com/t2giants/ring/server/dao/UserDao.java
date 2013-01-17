package tr.com.t2giants.ring.server.dao;

import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.server.data.BaseObject;

/**
 * User: sonic
 * Date: 1/7/13
 */
public interface UserDao <T extends BaseObject> extends BaseDao<T> {

    int insertUser(User user);

    User getUser(String username);

    User getUser(long id);

    void updateUser(long id, User user);

    boolean isUsernameAvailable(String username, long id);

    boolean isEmailAvailable(String email, long id);

    String getAvatarURL(long requesterSupporterID);

    String getAvatarThumb(long requesterSupporterID);

    String setAvatarURL(long id, String[] avatarURL);
}