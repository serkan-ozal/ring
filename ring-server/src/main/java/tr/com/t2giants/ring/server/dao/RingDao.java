package tr.com.t2giants.ring.server.dao;

import tr.com.t2giants.ring.server.domain.User;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface RingDao {

    void requestToAddToRing(long requesterID, long requestedID);

    void acceptRequest(long requesterID, long requestedID);

    void discardRequest(long requesterID, long requestedID);

    void removeFromRing(long requesterID, long requestedID);

    List<Long> getRingOfUser(long loggedInUserID);
}
