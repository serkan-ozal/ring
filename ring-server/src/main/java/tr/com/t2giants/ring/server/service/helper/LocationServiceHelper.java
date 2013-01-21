package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.server.domain.User;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
public interface LocationServiceHelper {

    void informCurrentLocation(Long loggedInUserID, double lat, double lon);

    Coordinates getLastPositionOfUser(long id);

    List<User> getNearByUsers(double topLeftLat, double topLeftLon, double bottomRightLat, double bottomRightLon);
}
