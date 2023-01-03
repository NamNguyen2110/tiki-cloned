package com.example.tikicloned.service;

import com.example.tikicloned.domain.dto.request.LoginRequest;
import com.example.tikicloned.domain.dto.response.LoginResponse;

public interface UsersService {
    LoginResponse login(LoginRequest request);
}
