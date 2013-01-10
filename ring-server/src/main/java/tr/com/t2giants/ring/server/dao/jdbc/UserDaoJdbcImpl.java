package tr.com.t2giants.ring.server.dao.jdbc;

import java.util.List;

import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.server.dao.UserDao;

public class UserDaoJdbcImpl extends BaseDaoJdbcImpl implements UserDao {

	@Override
	public User getUser(String username) {
		return null;
	}

	@Override
	public void updateUser(User user) {
		
	}

	@Override
	public void deleteUser(String username) {
		
	}

	@Override
	public List<User> getConnectedUsers(String username) {
		return null;
	}

	@Override
	public List<User> getUsersWaitingForAccept(String username) {
		return null;
	}

}
