package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.data.validator.ValidationList;
import tr.com.t2giants.ring.server.exception.StritFunNotFoundException;
import tr.com.t2giants.ring.server.exception.StritFunRuntimeException;
import tr.com.t2giants.ring.server.exception.StritFunValidationException;
import tr.com.t2giants.ring.server.util.WebDesignParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * User: mertcaliskan
 * Date: 6/12/12
 */
public abstract class BaseService {

    @Autowired
    protected SecurityService securityService;

    Long getLoggedInUserID() {
        Long userId = (Long) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(WebDesignParameters.CLUBS_USER_ID);
        if (userId != null) {
            return userId;
        }
        return -1l;
    }

    protected void throwBadRequest(ValidationList validationList) {
        Response response = Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(validationList).build();
        throw new WebApplicationException(response);
    }

    Response responseOK() {
        return Response.ok().build();
    }

    @ExceptionHandler(StritFunNotFoundException.class)
    public
    @ResponseBody
    Response handleNotFoundException(Exception ex) {
        return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
    }

    @ExceptionHandler(StritFunRuntimeException.class)
    public
    @ResponseBody
    Response handleRuntimeException(Exception ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
    }

    @ExceptionHandler(StritFunValidationException.class)
    public
    @ResponseBody
    Response handleValidationException(StritFunValidationException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
    }
}
