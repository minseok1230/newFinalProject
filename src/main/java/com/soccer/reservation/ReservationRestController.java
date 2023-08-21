package com.soccer.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/create_reservation")
	public Map<String, Object> createReservation(
			@RequestParam("matchDate") String matchDate,
			@RequestParam("region") String region,
			@RequestParam("stadium") String stadium,
			@RequestParam("matchTime") String matchTime,
			@RequestParam("teamName") String teamName,
			HttpSession session
			){
		
		int teamId =  (int)session.getAttribute("userTeamId");
		
		Map<String, Object> result = new HashMap<>();
		
		/* ******************************팀장 확인*******************************/
		/* ******************************팀장 확인*******************************/
		/* ******************************팀장 확인*******************************/
		
		// DB에 있는지 확인, DB insert
		int insertResult =  reservationBO.addReservation(teamId, matchDate, region, stadium, matchTime, teamName);
		
		if (insertResult == 300) {
			result.put("errorMessage", "이미 예약이 되어있습니다.");
		} else if (insertResult == 1) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "경기장 예약에 실패하였습니다.");
		}
		return result;
	}
	
	// 예약 삭제
	@DeleteMapping("/{reservationId}")
	public Map<String, Object> deleteReservation(
			@PathVariable int reservationId){
		
		
		Map<String, Object> result = new HashMap<>();
		
		// db delete
		int deleteResult = reservationBO.deleteReservationById(reservationId);
		if (deleteResult > 0) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "경기장 취소 실패했습니다.");
		}
		
		return result;
		
	}
}

















