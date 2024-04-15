package com.example.recipeapp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement logic to convert your user roles to GrantedAuthority objects.
        // This example assumes no roles and returns an empty collection.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Implement remaining methods based on your requirements.
    // The following implementations are basic and may need to be adapted based on your application's needs.
    @Override
    public boolean isAccountNonExpired() {
        return true; // Consider your own logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Consider your own logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Consider your own logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Consider your own logic
    }
}
