package dev.practice.lf.service;

import dev.practice.lf.dto.LoginDto;
import dev.practice.lf.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
