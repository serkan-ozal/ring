package tr.com.t2giants.ring.server.dao.mongo;

import tr.com.t2giants.ring.server.data.FunItem;

import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface MongoUtility {

    void addFunItem(FunItem funItem);

    void removeFunItem(String id);

    List<FunItem> getFunItemsByBound(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon);

    FunItem getFunItem(String id);
}
