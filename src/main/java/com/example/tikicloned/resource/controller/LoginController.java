package com.example.tikicloned.resource.controller;

import com.example.tikicloned.common.ApiResponse;
import com.example.tikicloned.domain.dto.request.LoginRequest;
import com.example.tikicloned.domain.dto.response.LoginResponse;
import com.example.tikicloned.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("${app.version}/api/authentication")
public class LoginController {
    private final UsersService usersService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.created(usersService.login(request)));
    }
}
