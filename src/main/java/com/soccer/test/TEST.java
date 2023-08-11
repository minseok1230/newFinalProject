package com.soccer.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class TEST {

    private final String BASE_URL = "https://openapi.gg.go.kr/PublicTrainingFacilitySoccer";
    private final String API_KEY = "49ae9fa134524c1eb22a22a1067f0f85"; // 발급받은 API 키

    public void fetchDataFromApi() {
        // RestTemplate을 사용하여 API 호출
        RestTemplate restTemplate = new RestTemplate();

        // API 호출을 위한 URI 생성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("Key", API_KEY)
                .queryParam("Type", "json")
                .queryParam("pIndex", 1)
                .queryParam("pSize", 10);

        // API 호출 및 응답 처리
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.toUriString(), String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            // responseBody를 파싱하여 원하는 데이터 추출
            System.out.println(responseBody);
        } else {
            System.out.println("API 호출 실패: " + responseEntity.getStatusCodeValue());
        }
    }

    public static void main(String[] args) {
    	TEST apiClient = new TEST();
        apiClient.fetchDataFromApi();
    }
}
