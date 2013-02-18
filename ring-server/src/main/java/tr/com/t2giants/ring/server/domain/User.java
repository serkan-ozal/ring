package tr.com.t2giants.ring.server.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class User extends tr.com.t2giants.ring.core.domain.User implements UserDetails {

	public User() {

	}
	
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return credentialsNonExpired;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    @JsonIgnore
    public List<tr.com.t2giants.ring.server.domain.Role> getRoles() {
    	return (List<Role>) roles;
    }

    @SuppressWarnings("unchecked")
	@Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) roles;
    }
	
}
