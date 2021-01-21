package dev.gideon.maintenance.dynamic.model;

import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

@Setter
public class StompPrincipal implements Principal, UserDetails {
    private BigInteger id;
    private User user;
    private String password;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String getName() {
        // Username is not unique and updatable, fallback to id instead.
        return this.id.toString();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        // Username is not unique and updatable, fallback to id instead.
        return this.id.toString();
    }
}