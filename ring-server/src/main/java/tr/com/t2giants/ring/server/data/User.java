package tr.com.t2giants.ring.server.data;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: sonic
 * Date: 1/6/13
 */
public class User extends BaseObject implements UserDetails {

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

    private List<Role> roles = new ArrayList<Role>();

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

    @Override
    @JsonProperty("credentialsNonExpired")
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

    public int getFunItemCount() {
        return funItemCount;
    }

    public void setFunItemCount(int funItemCount) {
        this.funItemCount = funItemCount;
    }

    public int getPeopleLikedFunItemCount() {
        return peopleLikedFunItemCount;
    }

    public void setPeopleLikedFunItemCount(int peopleLikedFunItemCount) {
        this.peopleLikedFunItemCount = peopleLikedFunItemCount;
    }

    public int getFunItemLikedCount() {
        return funItemLikedCount;
    }

    public void setFunItemLikedCount(int funItemLikedCount) {
        this.funItemLikedCount = funItemLikedCount;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}