package tr.com.t2giants.ring.server.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tr.com.t2giants.ring.core.domain.Role;
import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.server.dao.UserDao;
import tr.com.t2giants.ring.server.exception.RingProjectRuntimeException;
import tr.com.t2giants.ring.server.util.ErrorMessages;

import javax.annotation.PostConstruct;

/**
 * User: sonic
 * Date: 1/7/13
 */
@Repository
public class UserDaoJDBCImpl extends BaseDaoJDBC<User> implements UserDao<User> {

    private final Log logger = LogFactory.getLog(UserDaoJDBCImpl.class);

    private final String tableNameUser = "User";

    private SimpleJdbcInsert insertSupporter;

    @Autowired
    private RowMapper<User> userRowMapper;

    private Role userRole;

    @PostConstruct
    public void afterPropertiesSet() {
        userRole = new Role();
        userRole.setId(1);
        userRole.setName("ROLE_USER");

        this.insertSupporter = new SimpleJdbcInsert(getJDBCTemplate())
                .withTableName(tableNameUser).usingGeneratedKeyColumns("id");
    }

    @Override
    public int insertUser(User user) {

        MapSqlParameterSource userParameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("fullName", user.getFullName())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("about", user.getAbout())
                .addValue("avatarImage", user.getAvatarImage())
                .addValue("avatarThumbnail", user.getAvatarThumbnail())
                .addValue("credentialsNonExpired", user.isCredentialsNonExpired())
                .addValue("accountNonLocked", user.isAccountNonLocked())
                .addValue("activated", user.isActivated())
                .addValue("enabled", user.isEnabled())
                .addValue("birthDate", user.getBirthDate())
                .addValue("ringFriendCount", user.getRingFriendCount())
                .addValue("creationTime", user.getCreationTime());

        try {
            return insertSupporter.executeAndReturnKey(userParameters).intValue();
        } catch (Exception e) {
            logger.error("Exception occurred during creating supporter " + user.toString(), e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public User getUser(String username) {
        try {
            User user = getJDBCTemplate().queryForObject(
                    "SELECT * FROM " + tableNameUser + " WHERE username = ?",
                    userRowMapper, username);

            user.getRoles().add(userRole);

            return user;
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.error("Exception occurred during fetching user with username: " + username, e);
        }
        return null;
    }

    @Override
    public User getUser(long id) {
        try {
            User user = getJDBCTemplate().queryForObject(
                    "SELECT * FROM " + tableNameUser + " WHERE id = ?",
                    userRowMapper, id);

            user.getRoles().add(userRole);

            return user;
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.error("Exception occurred during fetching user with id: "+ id, e);
        }
        return null;
    }

    @Override
    public void updateUser(long id, User user) {
        try {
            getJDBCTemplate().update("UPDATE " + tableNameUser + " SET " +
                    "username = ? ," +
                    "email = ? ," +
                    "fullName = ? ," +
                    "birthDate = ? ," +
                    "WHERE id = ?",
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName(),
                    user.getBirthDate(),
                    id
            );
        } catch (Exception e) {
            logger.error("Exception occurred during updating user with id " + id, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
    }

    @Override
    public boolean isUsernameAvailable(String username, long id) {
        try {
            return id == getJDBCTemplate().queryForObject(
                    "SELECT ID FROM " + tableNameUser + " WHERE username = ?",
                    Long.class,
                    username);
        } catch (EmptyResultDataAccessException e) {
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred during fetching user with username " + username, e);
            return false;
        }
    }

    @Override
    public boolean isEmailAvailable(String email, long id) {
        try {
            return id == getJDBCTemplate().queryForObject(
                    "SELECT ID FROM " + tableNameUser + " WHERE EMAIL = ?",
                    Long.class, email);
        } catch (EmptyResultDataAccessException e) {
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred during fetching supporter with email " + email, e);
            return false;
        }
    }

    @Override
    public String getAvatarURL(long id) {
        try {
            return getJDBCTemplate().queryForObject(
                    "SELECT avatarImage FROM " + tableNameUser + " WHERE id = ?",
                    String.class, id);
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.error("Exception occurred during fetching avatar of user with id " + id, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
        return null;
    }

    @Override
    public String getAvatarThumb(long id) {
        try {
            return getJDBCTemplate().queryForObject(
                    "SELECT avatarThumbnail FROM " + tableNameUser + " WHERE id = ?",
                    String.class, id);
        } catch (EmptyResultDataAccessException ignored) {
        } catch (Exception e) {
            logger.error("Exception occurred during fetching avatar of user with id " + id, e);
            throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
        }
        return null;
    }

    @Override
    public String setAvatarURL(long id, String[] avatars) {
        try {
            getJDBCTemplate().update(
                    "UPDATE " + tableNameUser + " SET avatarImage = ? ,"
                            + "avatarThumbnail = ? " + "WHERE id = ?",
                    avatars[1], avatars[0], id);
        } catch (Exception e) {
            logger.error("Exception occurred during updating avatar of supporter with id " + id, e);
        }
        throw new RingProjectRuntimeException(ErrorMessages.CANNOT_COMPLETE_OPERATION.getErrorMessage());
    }

}
