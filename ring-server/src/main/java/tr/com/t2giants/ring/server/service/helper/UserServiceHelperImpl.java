package tr.com.t2giants.ring.server.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.cache.RingCacheManager;
import tr.com.t2giants.ring.server.dao.UserDao;
import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.domain.enums.StreamType;
import tr.com.t2giants.ring.server.domain.validator.UserValidator;
import tr.com.t2giants.ring.server.domain.validator.ValidationList;
import tr.com.t2giants.ring.server.exception.RingProjectNotFoundException;
import tr.com.t2giants.ring.server.exception.RingProjectValidationException;
import tr.com.t2giants.ring.server.util.ErrorMessages;
import tr.com.t2giants.ring.server.util.WebDesignParameters;

import javax.servlet.http.HttpServletRequest;

/**
 * User: sonic
 * Date: 1/7/13
 */
@Service
public class UserServiceHelperImpl implements UserServiceHelper {

    @Autowired
    private RingCacheManager ringCacheManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier(value = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StreamServiceHelper streamServiceHelper;

    @Autowired
    private UserValidator userValidator;
    @Override
    public User addUser(User user) {

        final ValidationList validationList = userValidator.validateCreationData(user);
        if (!validationList.isEmpty()) {
            throw new RingProjectValidationException(validationList.get(0));
        }

        setDefaultValues(user);
        final int id = userDao.insertUser(user);

        user.setId(id);
        user.setPassword(null);

        ringCacheManager.addUserToCache(user);
        return user;
    }

    private void setDefaultValues(User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        user.setAbout("");
        user.setAvatarImage(WebDesignParameters.DEFAULT_THUMB_AVATAR);
        user.setAvatarThumbnail(WebDesignParameters.DEFAULT_THUMB_AVATAR);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setActivated(false);
        user.setEnabled(true);
        user.setRingFriendCount(0);
        user.setCreationTime(System.currentTimeMillis());
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);

        final ValidationList validationList = userValidator.validateCreationData(user);
        if (!validationList.isEmpty()) {
            throw new RingProjectValidationException(validationList);
        }

        userDao.updateUser(id, user);
        return user;
    }

    @Override
    public String addUpdateAvatar(Long userID, HttpServletRequest request) {
        final String[] avatars = streamServiceHelper.uploadStreamRequest(request, userID, StreamType.AVATAR);

        userDao.setAvatarURL(userID, avatars);
        ringCacheManager.updateUserAvatar(userID, avatars);
        return null;
    }

    @Override
    public Boolean isUsernameAvailable(String username, Long id) {
        return userDao.isUsernameAvailable(username, id);
    }

    @Override
    public Boolean isEmailAvailable(String email, Long id) {
        return userDao.isEmailAvailable(email, id);
    }

    public User checkUser(long id) {
        User user = ringCacheManager.getUserFromCache(id);
        if (user == null) {
            user = userDao.getUser(id);
            if (user == null) {
                throw new RingProjectNotFoundException(ErrorMessages.CANNOT_FIND_USER.getErrorMessage());
            }
            ringCacheManager.addUserToCache(user);
        }
        return user;
    }

    @Override
    public String getAvatarThumb(long id) {
        return userDao.getAvatarThumb(id);
    }

    @Override
    public String getAvatar(long id) {
        return userDao.getAvatarURL(id);
    }

    @Override
    public void deactivateUser(long loggedInUserID) {
        userDao.deactivateUser(loggedInUserID);
        ringCacheManager.removeUserFromCache(loggedInUserID);
    }

}
