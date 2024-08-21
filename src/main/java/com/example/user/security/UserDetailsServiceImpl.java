package com.example.user.security;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        HashSet<GrantedAuthority> setAuthorities = new HashSet<>();
        setAuthorities.add(new SimpleGrantedAuthority(user.getUserType().name()));
        return new UserDetailsImpl(user, new ArrayList<>(setAuthorities));
    }
}
