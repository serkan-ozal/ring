package tr.com.t2giants.ring.server.dao.mongo;

import tr.com.t2giants.ring.core.domain.RingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.RingUserLastPosition;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * User: sonic
 * Date: 1/13/13
 */
@Service
public class MongoUtilityImpl implements MongoUtility {

    private static final String COLLECTION_NAME = "RingUserLastPosition";

    private static final String LOCATION = "lastPosition";

    @Autowired
    @Qualifier("ringMongoTemplate")
    private MongoOperations mongoTemplate;

	@PostConstruct
    private void initializeIndexes() {
        mongoTemplate.indexOps(COLLECTION_NAME).ensureIndex(new GeospatialIndex(LOCATION));
    }

    @Override
    public void addLastPosition(RingUserLastPosition ringUserLastPosition) {
        mongoTemplate.insert(ringUserLastPosition);
    }

    @Override
    public RingUserLastPosition getLastPosition(long id) {
        return mongoTemplate.findOne(query(where("id").is(id)), RingUserLastPosition.class);
    }

    @Override
    public List<RingUserLastPosition> getNearByUsers(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon) {
        Criteria criteria = new Criteria(LOCATION).within(new Box(new Point(topLeftLon, topLeftLat), new Point(bottomRightLon, bottomRightLat)));
        return mongoTemplate.find(new Query(criteria), RingUserLastPosition.class);
    }
}
