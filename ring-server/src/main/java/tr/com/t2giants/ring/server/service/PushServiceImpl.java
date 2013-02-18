package tr.com.t2giants.ring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.t2giants.ring.server.service.helper.PushServiceHelper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Controller
@RequestMapping(value = "/ring")
public class PushServiceImpl extends BaseService implements PushService {

    @Autowired
    private PushServiceHelper pushServiceHelper;

    @RequestMapping(value = "/add/{gcmRegID}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public Response addGCMRegistrationID(@PathVariable("gcmRegID") String gcmRegID) {
        pushServiceHelper.addUserRegistrationID(getLoggedInUserID(), gcmRegID);
        return responseOK();
    }
}
