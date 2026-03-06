package com.bremenband.shadowengapi.domain.auth.controller;

import com.bremenband.shadowengapi.domain.auth.dto.req.KakaoLoginRequest;
import com.bremenband.shadowengapi.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "소셜 로그인 API", description = "소셜 로그인 & 로그아웃을 위한 REST API")
public class AuthController {

    @PostMapping("/login/kakao")
    @Operation(
            summary = "카카오 로그인",
            description = "클라이언트에서 카카오 SDK를 통해 발급받은 카카오 인가 코드를 Request Body로 전달받아 " +
                    "유저 정보를 확인하고, 서비스 자체 JWT(액세스 및 리프레시 토큰)를 발급합니다.")
    ApiResponse<?> kakaoLogin(@RequestBody KakaoLoginRequest request){
        return null;
    }

    @PostMapping("/logout")
    @Operation(
            summary = "로그아웃",
            description = "요청 헤더의 Access Token을 확인하여 해당 사용자를 로그아웃 처리합니다. (예: 서버의 Refresh Token 삭제 등)"
    )
    public ApiResponse<?> logout(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    ) {
        return null;
    }

}
