package tr.com.t2giants.ring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.service.helper.UserServiceHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: sonic
 * Date: 1/7/13
 */
@Controller
@RequestMapping(value = "/users")
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserServiceHelper userServiceHelper;

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    User addUser(@RequestBody User user) {
        return userServiceHelper.addUser(user);
    }

    @Override
    public Response deactivateUser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody User updateUser(@RequestBody User user) {
        return userServiceHelper.updateUser(getLoggedInUserID(), user);
    }

    @RequestMapping(value = "/get/by-id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    User getUser(@PathVariable("id") long id) {
        return userServiceHelper.getUser(id);
    }

    @RequestMapping(value = "/get/by-username/{username}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    User getUser(@PathVariable("username") String username) {
        return userServiceHelper.getUser(username);
    }

    @RequestMapping(value = "/avatar",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA)
    public @ResponseBody
    String addUpdateAvatar(@Context HttpServletRequest request) {
        return userServiceHelper.addUpdateAvatar(getLoggedInUserID(), request);
    }

    @RequestMapping(value = "/check/username",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Boolean isUserNameAvailable(@FormParam("username") String username) {
        return userServiceHelper.isUsernameAvailable(username, getLoggedInUserID());
    }

    @RequestMapping(value = "/check/email",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Boolean isEmailAvailable(@FormParam("email") String email) {
        return userServiceHelper.isEmailAvailable(email, getLoggedInUserID());
    }
}
