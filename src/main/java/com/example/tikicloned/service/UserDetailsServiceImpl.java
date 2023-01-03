package com.example.tikicloned.service;

import com.example.tikicloned.domain.entity.UserDetailsImpl;
import com.example.tikicloned.domain.entity.Users;
import com.example.tikicloned.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Users user = userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + username));
        return new UserDetailsImpl(user);
    }
}
