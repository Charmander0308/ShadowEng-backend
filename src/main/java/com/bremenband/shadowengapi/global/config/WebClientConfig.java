package com.bremenband.shadowengapi.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        // 커넥션 풀 설정
        ConnectionProvider provider = ConnectionProvider.builder("custom-provider")
                .maxConnections(50)                         // 총 커넥션
                .pendingAcquireTimeout(Duration.ofSeconds(5)) // 풀에서 커넥션 꺼내는 시간 대기
                .build();

        // 타임아웃 설정 : 공공 API 지연 시 우리 서버 영향을 방지
        HttpClient httpClient = HttpClient.create(provider)
                .responseTimeout(Duration.ofSeconds(10));   // 데이터 읽는 시간 10초 타임아웃

        // Spring WebClient에 연결
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
