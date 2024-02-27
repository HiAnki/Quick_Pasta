package com.example.QuickPasta.config;

import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.model.Restaurant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailCreator implements UserDetails {

    String username;
    String password;
    List<GrantedAuthority> authorities;
    public UserDetailCreator(Customer customer) {
        this.username = customer.getEmail();
        this.password = customer.getPassword();
        this.authorities = new ArrayList<>();

        String[] roles = customer.getRole().split(",");
        for(String role: roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(simpleGrantedAuthority);
        }
    }

    public UserDetailCreator(Restaurant restaurant) {
        this.username = restaurant.getEmail();
        this.password = restaurant.getPassword();
        this.authorities = new ArrayList<>();

        String[] roles = restaurant.getRole().split(",");
        for(String role: roles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(simpleGrantedAuthority);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return true;
    }
}
