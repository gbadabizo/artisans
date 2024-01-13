package com.ipnetinstitute.artisans.security;

import com.ipnetinstitute.artisans.commons.UtilsFunctions;
import com.ipnetinstitute.artisans.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private final  Utilisateur utilisateur ;

    public UserPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return this.utilisateur.getEmail();
    }

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
        return this.utilisateur.getStatus()== UtilsFunctions.ACTIVE_STATUS;
    }
}
