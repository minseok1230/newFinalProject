package com.soccer.user.bo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soccer.common.KakaoOauthLogin;
import com.soccer.user.domain.KakaoProfile;
import com.soccer.user.domain.OAuthToken;

@Service
public class UserService {

	
	@Value("${cos.key}")
	private String cosKey;
	
	public Map<String, Object> possibleKakaoLogin(String code) {
		Map<String, Object> result = new HashMap<>();
		
		OAuthToken oauthToken = KakaoOauthLogin.getToken(code);
		KakaoProfile kakaoProfile = KakaoOauthLogin.getKakaoData(oauthToken);
		
		result.put("nickName", kakaoProfile.getProperties().getNickname());
		result.put("id", kakaoProfile.getId());
		result.put("email", kakaoProfile.getKakao_account().getEmail());
		result.put("password", cosKey);
		
		return result;
	}
	
	
	
}
