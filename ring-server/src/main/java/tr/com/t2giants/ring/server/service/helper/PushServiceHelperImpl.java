package tr.com.t2giants.ring.server.service.helper;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.server.dao.GCMDao;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class PushServiceHelperImpl implements PushServiceHelper {

    protected final Log logger = LogFactory.getLog(PushServiceHelperImpl.class);

    @Autowired
    private GCMDao gcmDao;

    private Sender sender;

    @Value("${gcmAPIKey}")
    private String apiKey;

    @PostConstruct
    protected void initializeSender() {
        sender = new Sender(apiKey);
    }

    @Override
    public void addUserRegistrationID(long userID, String regID) {
        gcmDao.addRegistrationID(userID, regID);
    }

    @Override
    public void informUser(long userID, Message message) {
        List<String> gcmRegIDs = gcmDao.getRegistrationID(userID);

        try {
            for (String gcmRegID : gcmRegIDs) {
                pushMessageTo(userID, gcmRegID, message);
            }
        } catch (IOException e) {
            logger.error("Exception during pushing message to user with id: " + userID + " with message" + message.toString(), e);
        }

    }

    private void pushMessageTo(long userID, String gcmRegID, Message message) throws IOException {
        Result result = sender.send(message, gcmRegID, 3);

        if (result.getMessageId() != null) {
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
                // same device has more than one registration ID: update database
            }
        } else {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                // application has been removed from device - unregister database
                gcmDao.removeUserGCMRegID(userID, gcmRegID);
            }
        }
    }
}
