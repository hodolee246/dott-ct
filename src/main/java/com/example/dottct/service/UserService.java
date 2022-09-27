package com.example.dottct.service;

import com.example.dottct.model.dto.UserDto;
import com.example.dottct.model.entity.User;
import com.example.dottct.repository.UserRepository;
import com.example.dottct.config.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NoSuchElementException("Can not found user by email"));
        return CustomUserDetails.builder()
                .email(user.getEmail())
                .pw(user.getPw())
                .build();
    }

    public void signup(UserDto userDto) {
        User user = userDto.toEntity();
        user.encodePw(passwordEncoder.encode(user.getPw()));
        userRepository.save(user);
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Can not found user by email"))
                .toDto();
    }

}
