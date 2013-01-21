package tr.com.t2giants.ring.server.service;

import org.springframework.security.access.annotation.Secured;
import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.server.domain.User;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface LocationService {

    @Secured("ROLE_USER")
    Response informCurrentLocation(double lat, double lon);

    @Secured("ROLE_USER")
    Coordinates getLastPositionOfUser(long id);

    @Secured("ROLE_USER")
    List<User> getNearByUsers(double topLeftLat, double topLeftLon, double bottomRightLat, double bottomRightLon);

}
