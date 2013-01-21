package tr.com.t2giants.ring.server.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tr.com.t2giants.ring.core.domain.Friendship;
import tr.com.t2giants.ring.server.dao.RingDao;
import tr.com.t2giants.ring.server.domain.User;
import tr.com.t2giants.ring.server.exception.RingProjectRuntimeException;
import tr.com.t2giants.ring.server.util.ErrorMessages;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Repository
public class RingDaoJDBCImpl extends BaseDaoJDBC<Friendship> implements RingDao {

    private final String tableNameFriendship = "friendship";

    private final String tableNameFriendshipRequests = "friendship_requests";

    private SimpleJdbcInsert insertFriendship;

    private SimpleJdbcInsert insertFriendshipRequests;

    @PostConstruct
    public void afterPropertiesSet() {
        insertFriendship= new SimpleJdbcInsert(getJDBCTemplate())
                .withTableName(tableNameFriendship).usingGeneratedKeyColumns("id");

        insertFriendshipRequests= new SimpleJdbcInsert(getJDBCTemplate())
                .withTableName(tableNameFriendshipRequests).usingGeneratedKeyColumns("id");
    }

    @Override
    public void requestToAddToRing(long requesterID, long requestedID) {
        try {
            MapSqlParameterSource userParameters = new MapSqlParameterSource()
                    .addValue("requesterID", requestedID)
                    .addValue("requestedID", requestedID)
                    .addValue("creationTime", System.currentTimeMillis());

            insertFriendshipRequests.execute(userParameters);
        } catch (Exception e) {
            logger.error("Exception occurred during adding request with requesterID: " + requesterID + " and requestedID: " + requestedID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public void acceptRequest(long requesterID, long requestedID) {
        try {
            MapSqlParameterSource userParameters = new MapSqlParameterSource()
                    .addValue("following", requestedID)
                    .addValue("follower", requestedID)
                    .addValue("creationTime", System.currentTimeMillis());

            insertFriendship.execute(userParameters);

            userParameters = new MapSqlParameterSource()
                    .addValue("following", requestedID)
                    .addValue("follower", requestedID)
                    .addValue("creationTime", System.currentTimeMillis());

            insertFriendship.execute(userParameters);

            discardRequest(requesterID, requestedID);
        } catch (Exception e) {
            logger.error("Exception occurred during adding friendship with requesterID: " + requesterID + " and requestedID: " + requestedID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public void discardRequest(long requesterID, long requestedID) {
        try {
            getJDBCTemplate().update("DELETE FROM " + tableNameFriendshipRequests +
                    " WHERE requesterID = ? and requestedID = ? ",
                    requesterID, requestedID);
        } catch (Exception e) {
            logger.error("Exception occurred during removing request with requesterID: " + requesterID + " and requestedID: " + requestedID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public void removeFromRing(long requesterID, long requestedID) {
        try {
            getJDBCTemplate().update("DELETE FROM " + tableNameFriendship +
                    " WHERE requesterID = ? and requestedID = ? ",
                    requesterID, requestedID);
            getJDBCTemplate().update("DELETE FROM " + tableNameFriendship +
                    " WHERE requesterID = ? and requestedID = ? ",
                    requestedID, requesterID);
        } catch (Exception e) {
            logger.error("Exception occurred during removing friendship with requesterID: " + requesterID + " and requestedID: " + requestedID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public List<Long> getRingOfUser(long loggedInUserID) {
        try{
            return getJDBCTemplate().queryForList("SELECT follower FROM " + tableNameFriendship +
                    " WHERE following = ? ",
                    Long.class,
                    loggedInUserID);

        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.debug("Exception thrown during getting friends of the user with id: " + loggedInUserID, e);
        }
        return null;
    }
}
