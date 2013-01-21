package tr.com.t2giants.ring.server.service.helper;

import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.domain.User;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class RingServiceHelperImpl implements RingServiceHelper {

    @Override
    public void addToRing(long loggedInUserID, long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeFromRing(long loggedInUserID, long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getRingOfUser(long loggedInUserID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
