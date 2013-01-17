package tr.com.t2giants.ring.server.dao.mongo;

import tr.com.t2giants.ring.core.domain.RingItem;

import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface MongoUtility {

    void addRingItem(RingItem ringItem);

    void removeRingItem(String id);

    List<RingItem> getRingItemsByBound(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon);

    RingItem getRingItem(String id);
}
