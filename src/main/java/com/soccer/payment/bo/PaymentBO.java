package com.soccer.payment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.payment.dao.PaymentMapper;
import com.soccer.payment.domain.Payment;

@Service
public class PaymentBO {

	@Autowired
	private PaymentMapper paymentMapper;
	
	public Payment getPaymentByTeamNameRegionStadiumTimeDate(int teamId, String region, String stadiumName, String matchTime, String matchDate){
		return paymentMapper.selectPaymentByTeamNameRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
	}
	
	public Payment getPaymentByRegionStadiumTimeDate(int teamId, String region, String stadiumName, String matchTime, String matchDate) {
		return paymentMapper.selectPaymentByRegionStadiumTimeDate(teamId , region, stadiumName, matchTime, matchDate);
	}
	
	public int addPaymentByTeamNameRegionStadiumTimeDate(int teamId, String region, String stadiumName, String matchTime, String matchDate) {
		return paymentMapper.insertPaymentByTeamNameRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
	}
	
	public void deletePaymentByTeamIdRegionStadiumTimeDate(int teamId, String region, String stadiumName, String matchTime, String matchDate) {
		paymentMapper.deletePaymentByTeamIdRegionStadiumTimeDate(teamId, region, stadiumName, matchTime, matchDate);
	}
}
