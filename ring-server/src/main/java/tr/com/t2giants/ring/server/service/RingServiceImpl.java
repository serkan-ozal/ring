package tr.com.t2giants.ring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.service.helper.RingServiceHelper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Controller
@RequestMapping(value = "/ring")
public class RingServiceImpl extends BaseService implements RingService {

    @Autowired
    private RingServiceHelper ringServiceHelper;

    @RequestMapping(value = "/request/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public Response requestToAddToRing(@PathVariable("id") long id) {
        ringServiceHelper.requestToAddToRing(getLoggedInUserID(), id);
        return responseOK();
    }

    @RequestMapping(value = "/request/accept/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Response acceptRequest(@PathVariable("id") long id) {
        ringServiceHelper.acceptRequest(getLoggedInUserID(), id);
        return responseOK();
    }

    @RequestMapping(value = "/request/discard/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Response discardRequest(@PathVariable("id") long id) {
        ringServiceHelper.discardRequest(getLoggedInUserID(), id);
        return responseOK();
    }

    @RequestMapping(value = "/friends/destroy/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Response removeFromRing(@PathVariable("id") long id) {
        ringServiceHelper.removeFromRing(getLoggedInUserID(), id);
        return responseOK();
    }

    @RequestMapping(value = "/friends/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    @ResponseBody public
    List<User> getRingOfUser(@PathVariable("id") long id) {
        return ringServiceHelper.getRingOfUser(id);
    }
}
