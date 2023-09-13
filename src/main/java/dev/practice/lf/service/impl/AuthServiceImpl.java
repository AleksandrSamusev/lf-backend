package dev.practice.lf.service.impl;

import dev.practice.lf.dto.LoginDto;
import dev.practice.lf.dto.RegisterDto;
import dev.practice.lf.entity.Role;
import dev.practice.lf.entity.User;
import dev.practice.lf.exception.ItemAPIException;
import dev.practice.lf.repository.RoleRepository;
import dev.practice.lf.repository.UserRepository;
import dev.practice.lf.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        // check if username already in DB
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ItemAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // check if email is already in DB
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ItemAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully!";
    }


}
