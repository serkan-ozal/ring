package tr.com.t2giants.ring.server.service;

import tr.com.t2giants.ring.server.data.Coordinates;
import tr.com.t2giants.ring.server.data.FunItem;
import tr.com.t2giants.ring.server.data.builder.CoordinatesBuilder;
import tr.com.t2giants.ring.server.data.builder.FunItemBuilder;
import tr.com.t2giants.ring.server.service.helper.FunItemServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
@Controller
@RequestMapping(value = "/fun-items")
public class FunItemServiceImpl extends BaseService implements FunItemService {

    @Autowired
    private FunItemServiceHelper funItemServiceHelper;

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA)
    public @ResponseBody
    FunItem addFunItemData(@Context HttpServletRequest request, @FormParam("comment") String comment,
                           @FormParam("lat") Double lat, @FormParam("lon") Double lon) {
        final FunItem funItem = new FunItemBuilder().comment(comment).userID(getLoggedInUserID()).
                location(new CoordinatesBuilder().latitude(lat).longitude(lon).build()).build();
        return funItemServiceHelper.addFunItemData(request, funItem);
    }

    @RequestMapping(value = "/remove/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    Response removeFunItem(@PathVariable("id") String id) {
        funItemServiceHelper.removeFunItem(id);
        return responseOK();
    }

    @RequestMapping(value = "/get/fun-items/byBounds/{topLeftLat}/{topLeftLon}/{bottomRightLat}/{bottomRightLon}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<FunItem> getFunItemsByBounds(@PathVariable("topLeftLat") Double topLeftLat, @PathVariable("topLeftLon") Double topLeftLon,
                                                           @PathVariable("bottomRightLat") Double bottomRightLat, @PathVariable("bottomRightLon") Double bottomRightLon) {
        return funItemServiceHelper.getFunItemsByBound(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
    }
}
