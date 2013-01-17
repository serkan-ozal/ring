package tr.com.t2giants.ring.server.dao.jdbc.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import tr.com.t2giants.ring.server.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: sonic
 * Date: 1/8/13
 */
@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User result = new User();

        result.setId(rs.getLong("id"));
        result.setUsername(rs.getString("username"));
        result.setFullName(rs.getString("fullName"));
        result.setPassword(rs.getString("password"));
        result.setEmail(rs.getString("email"));
        result.setAbout(rs.getString("about"));
        result.setAvatarImage(rs.getString("avatarImage"));
        result.setAvatarThumbnail(rs.getString("avatarThumbnail"));
        result.setCredentialsNonExpired(rs.getBoolean("credentialsNonExpired"));
        result.setAccountNonLocked(rs.getBoolean("accountNonLocked"));
        result.setActivated(rs.getBoolean("activated"));
        result.setEnabled(rs.getBoolean("enabled"));
        result.setBirthDate(rs.getLong("birthDate"));
        result.setRingFriendCount(rs.getInt("ringFriendCount"));
        result.setCreationTime(rs.getLong("creationTime"));

        return result;
    }
}
