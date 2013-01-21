package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.server.domain.User;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface RingServiceHelper {

    void requestToAddToRing(long loggedInUserID, long id);

    void acceptRequest(Long loggedInUserID, long id);

    void discardRequest(Long loggedInUserID, long id);

    void removeFromRing(long loggedInUserID, long id);

    List<User> getRingOfUser(long loggedInUserID);

}
