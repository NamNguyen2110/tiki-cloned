package com.example.tikicloned.domain.entity;

import com.example.tikicloned.domain.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Users user;
    private Map<String, Object> attributes;


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getStatus().equals(UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getStatus().equals(UserStatus.DEACTIVATED);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        // TODO: Handle role base management
        return grantedAuthorities;
    }

}
