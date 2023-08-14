package com.soccer.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.reservation.bo.ReservationBO;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {

	@Autowired
	private ReservationBO reservationBO;
	
	@GetMapping("/stadium_list")
	public List<String> stadiumList(
			@RequestParam("region") String region){
		
		// 지역의 운동장 가져오기 
		List<String> stadiumList = reservationBO.stadiumList(region);
		
		return stadiumList;
	}
}
