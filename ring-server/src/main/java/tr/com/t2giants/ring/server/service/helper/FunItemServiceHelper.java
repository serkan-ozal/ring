package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.server.data.FunItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface FunItemServiceHelper {

    FunItem addFunItemData(HttpServletRequest request, FunItem funItem);

    void removeFunItem(String id);

    List<FunItem> getFunItemsByBound(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon);
}
