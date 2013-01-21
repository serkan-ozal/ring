package tr.com.t2giants.ring.server.cache;

import tr.com.t2giants.ring.server.domain.User;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface RingCacheManager {

    void addUserToCache(User user);

    void removeUserFromCache(long id);

    void updateUserAvatar(long id, String[] avatars);

    User getUserFromCache(long id);
}
