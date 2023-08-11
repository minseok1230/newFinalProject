package com.soccer.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccer.common.SoccerStadiumApiService;

import reactor.core.publisher.Mono;

@Controller
public class TestController {
	
	@Autowired
	private SoccerStadiumApiService stadiumApi;
	
	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		
		
		return "Hello world222";
	}
	
	@ResponseBody
    @RequestMapping("/api")
    public Mono<String> apiTest() {
        return stadiumApi.fetchDataFromApi().map(responseBody -> {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // JSON 데이터를 Map<String, Object> 형태로 파싱
                Map<String, Object> parsedData = objectMapper.readValue(responseBody, Map.class);

                // 원하는 데이터 추출 및 처리
                // 예를 들어, parsedData에서 원하는 필드 추출
                String fieldName = (String) parsedData.get("0");

                return "Processed data: " + fieldName;
            } catch (IOException e) {
                e.printStackTrace();
                return "Error during parsing JSON";
            }
        });
    }
	
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> test2(){
		Map<String, Object> map = new HashMap<>();
		map.put("바나나", 2000);
		map.put("a", 2);
		map.put("축구", 3000);
		return map;
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "test/test3";
	}
	

	
	

}
