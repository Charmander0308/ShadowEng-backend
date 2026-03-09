package com.bremenband.shadowengapi.domain.study.client;

import com.bremenband.shadowengapi.domain.study.dto.python.PythonGenerateReferenceRequest;
import com.bremenband.shadowengapi.domain.study.dto.python.PythonGenerateReferenceResponse;
import com.bremenband.shadowengapi.global.exception.CustomException;
import com.bremenband.shadowengapi.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PythonApiClient {

    private final WebClient webClient;

    @Value("${python.api.base-url}")
    private String baseUrl;

    public PythonGenerateReferenceResponse generateReference(String videoId, double startSec, double endSec) {
        PythonGenerateReferenceRequest request =
                new PythonGenerateReferenceRequest(videoId, startSec, endSec);
        try {
            return webClient.post()
                    .uri(baseUrl + "/api/v1/generate-reference")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(PythonGenerateReferenceResponse.class)
                    .block();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXTERNAL_API_ERROR);
        }
    }
}
