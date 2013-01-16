package tr.com.t2giants.ring.server.service.helper;

import tr.com.t2giants.ring.server.dao.mongo.MongoUtility;
import tr.com.t2giants.ring.server.data.FunItem;
import tr.com.t2giants.ring.server.data.enums.StreamType;
import tr.com.t2giants.ring.server.data.validator.FunItemValidator;
import tr.com.t2giants.ring.server.data.validator.ValidationList;
import tr.com.t2giants.ring.server.exception.StritFunValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: sonic
 * Date: 1/13/13
 */
@Service
public class FunItemServiceHelperImpl implements FunItemServiceHelper {

    @Autowired
    private MongoUtility mongoUtility;

    @Autowired
    private StreamServiceHelper streamServiceHelper;

    @Autowired
    private FunItemValidator funItemValidator;

    @Override
    public FunItem addFunItemData(HttpServletRequest request, FunItem funItem) {

        final ValidationList validationList = funItemValidator.validateCreationData(funItem);

        if (!validationList.isEmpty()) {
            throw new StritFunValidationException(validationList.get(0));
        }

        final String[] funItemPath = streamServiceHelper.uploadStreamRequest(request, funItem.getUserID(), StreamType.FUN_ITEM);

        funItem.setPhotoURL(funItemPath[0]);

        mongoUtility.addFunItem(funItem);
        final FunItem item = mongoUtility.getFunItem(funItem.getId());
        return funItem;
    }

    @Override
    public void removeFunItem(String id) {
        mongoUtility.removeFunItem(id);
    }

    @Override
    public List<FunItem> getFunItemsByBound(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon) {
        return mongoUtility.getFunItemsByBound(topLeftLat, topLeftLon, bottomRightLat, bottomRightLon);
    }
}
