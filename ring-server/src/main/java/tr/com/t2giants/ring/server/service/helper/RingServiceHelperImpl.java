package tr.com.t2giants.ring.server.service.helper;

import com.google.android.gcm.server.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.dao.RingDao;
import tr.com.t2giants.ring.server.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class RingServiceHelperImpl implements RingServiceHelper {

    @Autowired
    private PushServiceHelper pushServiceHelper;

    @Autowired
    private RingDao ringDao;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public void requestToAddToRing(long loggedInUserID, long id) {
        userServiceHelper.checkUser(id);
        final User user = userServiceHelper.checkUser(loggedInUserID);

        final Message message = new Message.Builder()
                .addData("type", "new_friend_request")
                .addData("from_username", user.getUsername())
                .addData("from_user_id", String.valueOf(user.getId()))
                .addData("full_name", user.getFullName())
                .addData("avatar", user.getAvatarImage())
                .addData("avatar_thumb", user.getAvatarThumbnail()).build();

        pushServiceHelper.informUser(loggedInUserID, message);

        ringDao.requestToAddToRing(loggedInUserID, id);
    }

    @Override
    public void acceptRequest(Long loggedInUserID, long id) {
        final User user = userServiceHelper.checkUser(id);

        final Message message = new Message.Builder()
                .addData("type", "request_accepted")
                .addData("from_username", user.getUsername())
                .addData("from_user_id", String.valueOf(user.getId()))
                .addData("full_name", user.getFullName())
                .addData("avatar", user.getAvatarImage())
                .addData("avatar_thumb", user.getAvatarThumbnail()).build();

        pushServiceHelper.informUser(id, message);

        ringDao.acceptRequest(loggedInUserID, id);
    }

    @Override
    public void discardRequest(Long loggedInUserID, long id) {
        userServiceHelper.checkUser(id);
        ringDao.discardRequest(loggedInUserID, id);
    }

    @Override
    public void removeFromRing(long loggedInUserID, long id) {
        userServiceHelper.checkUser(id);
        ringDao.removeFromRing(loggedInUserID, id);
    }

    @Override
    public List<User> getRingOfUser(long loggedInUserID) {
        userServiceHelper.checkUser(loggedInUserID);
        final List<Long> ringOfUser = ringDao.getRingOfUser(loggedInUserID);
        if (ringOfUser == null) {
            return null;
        }

        List<User> ringList = new ArrayList<User>();
        for (long id : ringOfUser) {
            ringList.add(userServiceHelper.checkUser(id));
        }
        return ringList;
    }
}
