package com.soccer.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.payment.bo.PaymentBO;
import com.soccer.payment.domain.Payment;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

	@Autowired
	private PaymentBO paymentBO;
	
	@PostMapping("/check")
	public Map<String, Object> paymentCheck(
			@RequestParam("teamId") int teamId,
			@RequestParam("region") String region,
			@RequestParam("stadiumName") String stadiumName,
			@RequestParam("matchTime") String matchTime,
			@RequestParam("matchDate") String matchDate
			){
		
		Map<String, Object> result = new HashMap<>();
		
		// 다른팀 중복 결제 검사 
		Payment checkDuplicatepayment = paymentBO.getPaymentByRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
		if (checkDuplicatepayment != null) {
			result.put("duplicate", true);
			return result;
		}
		
		//db select
		Payment payment = paymentBO.getPaymentByTeamNameRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
		if (payment != null) {
			result.put("valid", true);
		} else {
			result.put("valid", false);
		}
		return result;
	}
	
	@PostMapping("/add")
	public Map<String, Object> paymentAdd(
			@RequestParam("teamId") int teamId,
			@RequestParam("region") String region,
			@RequestParam("stadiumName") String stadiumName,
			@RequestParam("matchTime") String matchTime,
			@RequestParam("matchDate") String matchDate
			){
		
		Map<String, Object> result = new HashMap<>();
		
		//db insert
		int insertResult = paymentBO.addPaymentByTeamNameRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
		if (insertResult > 0) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "결제정보 저장 실패");
		}
		
		return result;
		
	}
}










