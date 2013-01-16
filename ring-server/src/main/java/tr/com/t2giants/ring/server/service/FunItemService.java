package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.data.FunItem;
import org.springframework.security.access.annotation.Secured;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
public interface FunItemService {

    @Secured("ROLE_USER")
    FunItem addFunItemData(HttpServletRequest request, String comment, Double lat, Double lon);

    @Secured("ROLE_USER")
    Response removeFunItem(String id);

    @Secured("ROLE_USER")
    List<FunItem> getFunItemsByBounds(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon);
}
