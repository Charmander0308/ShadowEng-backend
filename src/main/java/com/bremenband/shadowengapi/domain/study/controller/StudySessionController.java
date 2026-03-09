package com.bremenband.shadowengapi.domain.study.controller;

import com.bremenband.shadowengapi.domain.study.dto.req.StudySessionCreateRequest;
import com.bremenband.shadowengapi.domain.study.dto.res.ActiveSessionsResponse;
import com.bremenband.shadowengapi.domain.study.dto.res.RecentStudySessionResponse;
import com.bremenband.shadowengapi.domain.study.dto.res.StudySessionCreateResponse;
import com.bremenband.shadowengapi.domain.study.service.StudySessionService;
import com.bremenband.shadowengapi.global.common.ApiResponse;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study-sessions")
@RequiredArgsConstructor
@Tag(name = "학습세션 API", description = "학습 세션 관련 기능을 위한 REST API")
public class StudySessionController {

    private final StudySessionService studySessionService;

    @GetMapping
    @Operation(
            summary = "사용자가 학습 중인 세션 목록 조회",
            description = "요청 헤더의 Access Token을 통해 인증된 사용자의 학습 중 세션 전체 목록을 조회합니다."
    )
    public ApiResponse<ActiveSessionsResponse> getStudySessions(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    ) {
        return ApiResponse.success(studySessionService.getActiveSessions(userId));
    }

    @PostMapping
    @Operation(
            summary = "학습 세션 생성",
            description = "embedUrl과 학습 구간(startSec, endSec)을 전달받아 세션을 생성하고 전사된 문장 목록을 반환합니다."
    )
    public ApiResponse<StudySessionCreateResponse> createStudySession(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId,
            @Valid @RequestBody StudySessionCreateRequest request
    ) {
        return ApiResponse.success(studySessionService.createStudySession(userId, request));
    }

    @GetMapping("/recent")
    @Operation(
            summary = "최근 학습 세션 조회",
            description = "인증된 사용자의 가장 최근 학습 세션 정보를 조회합니다."
    )
    public ApiResponse<RecentStudySessionResponse> getRecentSession(
            @Parameter(hidden = true) @AuthenticationPrincipal Long userId
    ) {
        return ApiResponse.success(studySessionService.getRecentSession(userId));
    }

    @PostMapping("/{sessionId}/evaluations")
    @Operation(
            summary = "사용자의 음성 데이터 전송",
            description = "특정 학습 세션에 대한 음성 데이터를 전송하고 평가를 요청합니다."
    )
    public ApiResponse<?> sendVoice(
            @Parameter(description = "음성을 전송할 학습 세션의 고유 ID", example = "1234")
            @PathVariable String sessionId
            // 실제 음성 파일이나 데이터는 @RequestPart 또는 @RequestBody로 받을 수 있습니다.
    ) {
        return null;
    }

    @GetMapping("/{sessionId}")
    @Operation(
            summary = "단일 학습 세션 조회",
            description = "특정 학습 세션의 상세 정보를 조회합니다."
    )
    public ApiResponse<?> getStudySession(
            @Parameter(description = "조회할 학습 세션의 고유 ID", example = "1234")
            @PathVariable String sessionId
    ) {
        return null;
    }


}
