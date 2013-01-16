package tr.com.t2giants.ring.server.dao.mongo;

import tr.com.t2giants.ring.server.data.FunItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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

    private static final String COLLECTION_NAME = "funItem";

    private static final String LOCATION = "coordinates";

    @Autowired
    @Qualifier("funItemsMongoTemplate")
    private MongoOperations mongoTemplate;

    @PostConstruct
    private void initializeIndexes() {
        mongoTemplate.indexOps(COLLECTION_NAME).ensureIndex(new GeospatialIndex("location"));
    }

    @Override
    public void addFunItem(FunItem funItem) {
        mongoTemplate.insert(funItem);
    }

    @Override
    public void removeFunItem(String id) {
        mongoTemplate.remove(query(where("id").is(id)), COLLECTION_NAME);
    }

    @Override
    public List<FunItem> getFunItemsByBound(Double topLeftLat, Double topLeftLon, Double bottomRightLat, Double bottomRightLon) {
        Criteria criteria = new Criteria(LOCATION).within(new Box(new Point(topLeftLon, topLeftLat), new Point(bottomRightLon, bottomRightLat)));
        return mongoTemplate.find(new Query(criteria), FunItem.class);
    }

    @Override
    public FunItem getFunItem(String id) {
        return mongoTemplate.findById(id, FunItem.class);
    }
}
