package tr.com.t2giants.ring.server.cache;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.domain.User;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class RingCacheManagerImpl implements RingCacheManager {

    @Qualifier("memCachedClient")
    @Autowired
    private MemCachedClient memCachedClient;

    private final String USER_ID_PREFIX = "user-id-";

    private final String USER_GCM_REG_ID_PREFIX = "user-gcm-reg-id-";

    @Override
    public void addUserToCache(User user) {
        memCachedClient.add(USER_ID_PREFIX + user.getId(), user);
    }

    @Override
    public void addGCMRegIDToCache(long userID, String gcmRegID) {
        memCachedClient.add(USER_GCM_REG_ID_PREFIX + userID, gcmRegID);
    }

    @Override
    public void removeUserFromCache(long id) {
        memCachedClient.delete(USER_ID_PREFIX + id);
    }

    @Override
    public void updateUserAvatar(long id, String[] avatars) {
        final User userFromCache = getUserFromCache(id);
        if (userFromCache != null) {
            userFromCache.setAvatarThumbnail(avatars[0]);
            userFromCache.setAvatarImage(avatars[1]);
            memCachedClient.replace(USER_ID_PREFIX + id, userFromCache);
        }
    }

    @Override
    public User getUserFromCache(long id) {
        return (User) memCachedClient.get(USER_ID_PREFIX + id);
    }

    @Override
    public String getGCMRegIDFromCache(long id) {
        return (String) memCachedClient.get(USER_GCM_REG_ID_PREFIX + id);
    }
}
