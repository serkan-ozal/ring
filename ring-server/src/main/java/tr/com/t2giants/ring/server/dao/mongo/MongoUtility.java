package tr.com.t2giants.ring.server.dao.mongo;

import tr.com.t2giants.ring.core.domain.RingItem;
import tr.com.t2giants.ring.core.domain.RingUserLastPosition;

import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface MongoUtility {

    void addLastPosition(RingItem funItem);

    RingUserLastPosition addLastPosition(long id);

    List<RingUserLastPosition> getNearByUsers(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon);
}
