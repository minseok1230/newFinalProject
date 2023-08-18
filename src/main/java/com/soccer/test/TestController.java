package com.soccer.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.board.bo.BoardBO;


@RestController
public class TestController {


	@Autowired
	private TestApiService apiService;
	
	@Autowired
	private BoardBO boardBO;
	
 
	@GetMapping("/getRowValues")
    public List<Object> getRowValues() {
		List<Map<String, Object>> aaa = new ArrayList<>();
		List<Object> name = new ArrayList<>();
		aaa = apiService.getAllRowValues();
		for (Map<String, Object> bbb : aaa) {
			name.add(bbb.get("SIGUN_NM"));
		}
		
		
        return name;
    }
	
//	 @GetMapping("/Test")
//	 public Map<String, Object> testWebClient() {
//		 Map<String, Object> aaa = new HashMap<>();
//		 aaa = apiService.getFirstTodosTestAsMap();
//	     return aaa;
//	 }
	 
	 @GetMapping("/mmm")
	 public Map<String, Object> test1(){
		 Map<String, Object> aaa = new HashMap<>();
		 aaa.put("사과", 1);
		 aaa.put("바나나", 1);
		 aaa.put("ㅇㅇㅇ", 1);
		 return aaa;
	 }
	 
	 @GetMapping("aaa")
	 public int test333() {
		 return boardBO.getBoardCount("게시판");
	 }
	
}
