package tr.com.t2giants.ring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.service.helper.LocationServiceHelper;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public class LocationServiceImpl extends BaseService implements LocationService {

    @Autowired
    private LocationServiceHelper locationServiceHelper;

    @Override
    public Response informCurrentLocation(double lat, double lon) {
        locationServiceHelper.informCurrentLocation(getLoggedInUserID(), lat, lon);
        return responseOK();
    }

    @Override
    public Coordinates getLastPositionOfUser(long id) {
        return locationServiceHelper.getLastPositionOfUser(id);
    }

    @Override
    public List<User> getNearByUsers(double topLeftLat, double topLeftLon, double bottomRightLat, double bottomRightLon) {
        return locationServiceHelper.getNearByUsers(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
    }
}
