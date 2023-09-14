package com.soccer.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccer.user.domain.KakaoProfile;
import com.soccer.user.domain.OAuthToken;

public class KakaoOauthLogin {

	
	public static OAuthToken getToken(String code) {

		// HttpHeader 오브젝트 생성
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "295092b76861b97a10136dca538d0ed0");
		params.add("redirect_uri", "http://43.201.113.41:8080/user/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기- Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, Json Simpe, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return oauthToken;
	}
	
	public static KakaoProfile getKakaoData(OAuthToken oauthToken) {
		//HttpHeader 오브젝트 생성
 		RestTemplate rt = new RestTemplate();
 		HttpHeaders headers = new HttpHeaders();
 		headers.add("Authorization", "Bearer " + oauthToken.getAccess_token());
 		headers.add("Content-Type", "application/x-www-form-urlencoded");
 		
 	    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기 
 		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
 				new HttpEntity<>(headers);
 		
 		// Http 요청하기- Post방식으로 - 그리고 response 변수의 응답 받음.
 		ResponseEntity<String> response = rt.exchange(
 			"https://kapi.kakao.com/v2/user/me",
 			HttpMethod.GET,
 			kakaoProfileRequest,
 			String.class
 		);
 		
 		System.out.println(response.getBody());
		// Gson, Json Simpe, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		};
		
		return kakaoProfile;
	}
}










