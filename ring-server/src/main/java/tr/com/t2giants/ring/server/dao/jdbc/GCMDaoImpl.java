package tr.com.t2giants.ring.server.dao.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tr.com.t2giants.ring.server.dao.GCMDao;
import tr.com.t2giants.ring.server.exception.RingProjectRuntimeException;
import tr.com.t2giants.ring.server.util.ErrorMessages;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * User: sonic
 * Date: 1/21/13
 */
@Repository
public class GCMDaoImpl extends BaseDaoJDBC implements GCMDao {

    private final String tableNameGCM = "gmc_users";

    private SimpleJdbcInsert insertGCMRegID;

    @PostConstruct
    public void afterPropertiesSet() {
        insertGCMRegID= new SimpleJdbcInsert(getJDBCTemplate())
                .withTableName(tableNameGCM).usingGeneratedKeyColumns("id");
    }

    @Override
    public void addRegistrationID(long userID, String gcmRegID) {
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("userID", userID)
                    .addValue("gcmRegID", gcmRegID)
                    .addValue("creationTime", System.currentTimeMillis());

            insertGCMRegID.execute(parameters);
        } catch (Exception e) {
            logger.error("Exception occurred during adding gcm reg id to user with id: " + userID + " and gcmRegID: " + gcmRegID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public List getRegistrationID(long userID) {
        try {
            return getJDBCTemplate().queryForList("SELECT gcmRegID FROM "
                    + tableNameGCM + " WHERE userID = ?",
                    String.class,
                    userID);
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.error("Exception occurred during fetching gcm reg id for user with id: " + userID, e);
        }
        return null;
    }

    @Override
    public void removeUserGCMRegID(long userID, String gcmRegID) {
        try {
            getJDBCTemplate().update("DELETE FROM " + tableNameGCM +
                    " WHERE userID = ? and gcmRegID = ? ",
                    userID, gcmRegID);
        } catch (Exception e) {
            logger.error("Exception occurred during removing gcm reg id with userID: " + userID + " and gcm reg id: " + gcmRegID, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }
}
