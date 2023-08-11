package com.soccer.common;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Service
public class SoccerStadiumApiService {
	
	private final String BASE_URL = "https://openapi.gg.go.kr/PublicTrainingFacilitySoccer";
    private final String API_KEY = "49ae9fa134524c1eb22a22a1067f0f85"; // 발급받은 API 키

        public Mono<String> fetchDataFromApi() {
            WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
                    .queryParam("Key", API_KEY)
                    .queryParam("Type", "json")
                    .queryParam("pIndex", 1)
                    .queryParam("pSize", 10);

            return webClient.get()
                    .uri(builder.toUriString())
                    .retrieve()
                    .bodyToMono(String.class);
        }
}

