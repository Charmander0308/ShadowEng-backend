package com.bremenband.shadowengapi.domain.study.client;

import com.bremenband.shadowengapi.domain.study.dto.youtube.YoutubeApiResponse;
import com.bremenband.shadowengapi.global.exception.CustomException;
import com.bremenband.shadowengapi.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class YoutubeApiClient {

    private final WebClient webClient;

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.base-url}")
    private String baseUrl;

    public YoutubeApiResponse fetchVideoInfo(String videoId) {
        try {
            return webClient.get()
                    .uri(baseUrl + "/videos?part=snippet,contentDetails&id={videoId}&key={key}",
                            videoId, apiKey)
                    .retrieve()
                    .bodyToMono(YoutubeApiResponse.class)
                    .block();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.EXTERNAL_API_ERROR);
        }
    }
}
