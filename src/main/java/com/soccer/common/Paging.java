package com.soccer.common;

import java.util.HashMap;
import java.util.Map;

public class Paging {

	
	//input : topPageNum , page, prevId, nextId
	//output: Map<String, Object>
	
	public static Map<String, Object> paging(Integer topPageNum , Integer page, Integer prevId, Integer nextId){
		
		Map<String, Object> result = new HashMap<>();
		
		if (page == null) {
			page = 1;
		}
		
		if (prevId != null) {
			page -= 1;
			if (page <= 0) {
				page = 1;
			}
		}
		
		if (nextId != null) {
				page += 1;
		}
		topPageNum = 5 * page - 4;
		
		result.put("topPageNum", topPageNum);
		result.put("page", page);
		result.put("prevId", prevId);
		result.put("nextId", nextId);
		
		return result;
	}
}
