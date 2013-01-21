package tr.com.t2giants.ring.server.service.helper;

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
    private RingDao ringDao;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public void requestToAddToRing(long loggedInUserID, long id) {
        userServiceHelper.checkUser(id);
        ringDao.requestToAddToRing(loggedInUserID, id);
    }

    @Override
    public void acceptRequest(Long loggedInUserID, long id) {
        userServiceHelper.checkUser(id);
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
