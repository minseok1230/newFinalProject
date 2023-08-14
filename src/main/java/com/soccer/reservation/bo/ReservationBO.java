package com.soccer.reservation.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.common.StadiumApiService;

@Service
public class ReservationBO {

	@Autowired
	private StadiumApiService stadiumApiService;
	
	
	// 경기장 APi 지역(ex. 화성, 안양, 안성...) 가져오기 
	public List<String> regionList(){
		List<Map<String, Object>> stadiumList = stadiumApiService.getAllRowValues();
		List<String> result = new ArrayList<>();
		//result.add((String) stadiumList.get(0).get("시군명"));
		
		for (int i = 0; i < stadiumList.size(); i++) {
//			if (stadiumList.get(i).get("시군명").equals(stadiumList.get(i-1).get("시군명"))) {
//				continue;
//			}
			result.add((String) stadiumList.get(i).get("시군명"));
		}
		
		return result;
	}
}
