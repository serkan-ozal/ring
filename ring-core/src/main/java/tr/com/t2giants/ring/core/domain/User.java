package tr.com.t2giants.ring.core.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class User extends BaseObject {

	protected String username;
	protected String fullName;
	protected String password;
	protected String email;
	protected String about;
	protected String avatarImage;
	protected String avatarThumbnail;
	protected boolean credentialsNonExpired;
	protected boolean accountNonLocked;
	protected boolean activated;
	protected boolean enabled;
	protected long birthDate;
	protected int ringFriendCount;
	protected long creationTime;
	protected List<? extends Role> roles = new ArrayList<Role>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getAvatarThumbnail() {
        return avatarThumbnail;
    }

    public void setAvatarThumbnail(String avatarThumbnail) {
        this.avatarThumbnail = avatarThumbnail;
    }


    public boolean isAccountNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public int getRingFriendCount() {
        return ringFriendCount;
    }

    public void setRingFriendCount(int ringFriendCount) {
        this.ringFriendCount = ringFriendCount;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public List<? extends Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<? extends Role> roles) {
		this.roles = roles;
	}

}
