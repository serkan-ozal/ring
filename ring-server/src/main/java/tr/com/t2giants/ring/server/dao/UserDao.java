package tr.com.t2giants.ring.server.dao;

import java.util.List;

import tr.com.t2giants.ring.core.domain.User;

public interface UserDao {

	public User getUser(String username);
	public void updateUser(User user);
	public void deleteUser(String username);
	public List<User> getConnectedUsers(String username);
	public List<User> getUsersWaitingForAccept(String username);
	
}
