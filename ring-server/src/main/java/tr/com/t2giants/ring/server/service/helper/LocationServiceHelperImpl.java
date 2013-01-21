package tr.com.t2giants.ring.server.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.Coordinates;
import tr.com.t2giants.ring.core.domain.RingUserLastPosition;
import tr.com.t2giants.ring.server.dao.mongo.MongoUtility;
import tr.com.t2giants.ring.server.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Service
public class LocationServiceHelperImpl implements LocationServiceHelper {

    @Autowired
    private MongoUtility mongoUtility;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public void informCurrentLocation(Long loggedInUserID, double lat, double lon) {
        RingUserLastPosition ringUserLastPosition = new RingUserLastPosition();
        ringUserLastPosition.setUserID(loggedInUserID);

        Coordinates lastPosition = new Coordinates(lat, lon);
        ringUserLastPosition.setLastPosition(lastPosition);

        mongoUtility.addLastPosition(ringUserLastPosition);
    }

    @Override
    public Coordinates getLastPositionOfUser(long id) {
        return mongoUtility.getLastPosition(id).getLastPosition();
    }

    @Override
    public List<User> getNearByUsers(double topLeftLat, double topLeftLon, double bottomRightLat, double bottomRightLon) {
        List<RingUserLastPosition> nearByPositions = mongoUtility.getNearByUsers(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
        List users = new ArrayList();

        for (RingUserLastPosition lastPositionItem : nearByPositions) {
            users.add(userServiceHelper.checkUser(lastPositionItem.getUserID()));
        }
        return users;
    }
}
