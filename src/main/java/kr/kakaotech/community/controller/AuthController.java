package kr.kakaotech.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kakaotech.community.dto.ApiResponse;
import kr.kakaotech.community.dto.request.UserLoginRequest;
import kr.kakaotech.community.dto.response.UserLoginResponse;
import kr.kakaotech.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<ApiResponse<UserLoginResponse>> getTokenByLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        UserLoginResponse userLoginResponse = authService.getToken(userLoginRequest.getEmail(), userLoginRequest.getPassword(), response);
        log.info(response.toString());
        return ApiResponse.success("로그인 성공", userLoginResponse);
    }

    @PostMapping("/auth/token")
    public ResponseEntity<ApiResponse<Object>> getToken(HttpServletRequest request, HttpServletResponse response) {
        authService.deleteToken(request, response);
        return ApiResponse.success("로그아웃 성공", null);
    }

    @GetMapping("/auth/refresh")
    public ResponseEntity<ApiResponse<Object>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
        return null;
    }
}
