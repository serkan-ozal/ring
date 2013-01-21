package tr.com.t2giants.ring.server.service.helper;

import com.google.android.gcm.server.Message;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface PushServiceHelper {

    void addUserRegistrationID(long userID, String regID);

    void informUser(long userID, Message message);
}
