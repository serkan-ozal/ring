package tr.com.t2giants.ring.server.dao;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface GCMDao {

    void addRegistrationID(long userID, String gcmRegID);

    List getRegistrationID(long userID);

    void removeUserGCMRegID(long userID, String gcmRegID);
}
