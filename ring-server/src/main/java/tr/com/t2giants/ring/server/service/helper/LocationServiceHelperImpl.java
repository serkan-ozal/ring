package tr.com.t2giants.ring.server.service.helper;

import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.server.domain.User;

import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class LocationServiceHelperImpl implements LocationServiceHelper {

    @Override
    public void informCurrentLocation(Long loggedInUserID, double lat, double lon) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Coordinates getLastPositionOfUser(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> getNearByUsers(double topLeftLat, double topLeftLon, double bottomRightLat, double bottomRightLon) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
