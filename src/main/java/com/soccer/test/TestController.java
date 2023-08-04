package com.soccer.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	
	
	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		return "Hello world222";
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
