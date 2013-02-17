package tr.com.t2giants.ring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.service.helper.LocationServiceHelper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Controller
@RequestMapping(value = "/locations")
public class LocationServiceImpl extends BaseService implements LocationService {

    @Autowired
    private LocationServiceHelper locationServiceHelper;

    @RequestMapping(value = "/add-current",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public Response informCurrentLocation(@PathVariable("lat") double lat,@PathVariable("lat") double lon) {
        locationServiceHelper.informCurrentLocation(getLoggedInUserID(), lat, lon);
        return responseOK();
    }

    @RequestMapping(value = "/last-position/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public Coordinates getLastPositionOfUser(@PathVariable("id") long id) {
        return locationServiceHelper.getLastPositionOfUser(id);
    }

    @RequestMapping(value = "/near-by/{topLeftLon}/{top-left-lat}/{bottomRightLon}/{bottomRightLat}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public List<User> getNearByUsers(@PathVariable("topLeftLat") double topLeftLat,@PathVariable("topLeftLon") double topLeftLon,
                                     @PathVariable("bottomRightLat") double bottomRightLat, @PathVariable("bottomRightLon") double bottomRightLon) {
        return locationServiceHelper.getNearByUsers(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
    }
}
