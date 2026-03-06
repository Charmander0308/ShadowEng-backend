package com.bremenband.shadowengapi.domain.report.controller;

import com.bremenband.shadowengapi.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "리포트 API", description = "리포트와 관련된 기능을 위한 REST API")
public class ReportController {

    @PostMapping("/{sessionId}/reports")
    @Operation(
            summary = "학습 세션 리포트 생성",
            description = "특정 학습 세션의 결과 리포트를 생성합니다."
    )
    public ApiResponse<?> createSessionReport(
            @Parameter(description = "리포트를 생성할 학습 세션의 고유 ID", example = "12345")
            @PathVariable String sessionId
    ) {
        return null;
    }

    @GetMapping("/{sessionId}/reports")
    @Operation(
            summary = "학습 세션 리포트 조회",
            description = "특정 학습 세션의 결과 리포트를 조회합니다."
    )
    public ApiResponse<?> getSessionReport(
            @Parameter(description = "조회할 학습 세션의 고유 ID", example = "12345")
            @PathVariable String sessionId
    ) {
        return null;
    }

    @GetMapping("/reports/daily")
    @Operation(
            summary = "일간 학습 리포트 조회",
            description = "요청 헤더의 Access Token을 통해 인증된 사용자의 당일 학습 리포트를 조회합니다."
    )
    public ApiResponse<?> getDailyReport(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    ) {
        return null;
    }

}
