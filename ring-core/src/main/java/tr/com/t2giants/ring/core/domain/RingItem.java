package tr.com.t2giants.ring.core.domain;

@SuppressWarnings("serial")
public class RingItem extends BaseObject {

	protected User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
