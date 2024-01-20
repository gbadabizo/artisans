package com.ipnetinstitute.artisans.security;

import com.ipnetinstitute.artisans.commons.UtilsFunctions;
import com.ipnetinstitute.artisans.entities.Role;
import com.ipnetinstitute.artisans.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private final  Utilisateur utilisateur ;

    public UserPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesLibelles = utilisateur.getRoles().stream().map(Role::getLibelle).toList();
         return getGrantedAuthorities(rolesLibelles);
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
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
