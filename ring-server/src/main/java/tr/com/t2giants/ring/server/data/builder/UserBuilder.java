package tr.com.t2giants.ring.server.data.builder;

import tr.com.t2giants.ring.server.data.User;

/**
 * User: sonic
 * Date: 1/15/13
 */
public class UserBuilder extends Builder<User, UserBuilder> {

    private String username;
    private String fullName;
    private String password;
    private String email;
    private String about;
    private String avatarImage;
    private String avatarThumbnail;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean activated;
    private boolean enabled;
    private long birthDate;
    private int funItemCount;
    private int peopleLikedFunItemCount;
    private int funItemLikedCount;
    private long creationTime;

    @Override
    public User build() {
        User user = new User();

        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setEmail(email);
        user.setAbout(about);
        user.setAvatarImage(avatarImage);
        user.setAvatarThumbnail(avatarThumbnail);
        user.setCredentialsNonExpired(credentialsNonExpired);
        user.setAccountNonLocked(accountNonLocked);
        user.setActivated(activated);
        user.setEnabled(enabled);
        user.setBirthDate(birthDate);
        user.setFunItemCount(funItemCount);
        user.setPeopleLikedFunItemCount(peopleLikedFunItemCount);
        user.setFunItemLikedCount(funItemLikedCount);
        user.setCreationTime(creationTime);

        return user;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder about(String about) {
        this.about = about;
        return this;
    }

    public UserBuilder avatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
        return this;
    }

    public UserBuilder avatarThumbnail(String avatarThumbnail) {
        this.avatarThumbnail = avatarThumbnail;
        return this;
    }

    public UserBuilder credentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserBuilder accountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public UserBuilder activated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public UserBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder birthDate(long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UserBuilder funItemCount(int funItemCount) {
        this.funItemCount = funItemCount;
        return this;
    }

    public UserBuilder peopleLikedFunItemCount(int peopleLikedFunItemCount) {
        this.peopleLikedFunItemCount = peopleLikedFunItemCount;
        return this;
    }

    public UserBuilder funItemLikedCount(int funItemLikedCount) {
        this.funItemLikedCount = funItemLikedCount;
        return this;
    }

    public UserBuilder creationTime(long creationTime) {
        this.creationTime = creationTime;
        return this;
    }
}
