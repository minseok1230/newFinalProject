package com.soccer.payment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.payment.domain.Payment;

@Repository
public interface PaymentMapper {
	
	
	public Payment selectPaymentByTeamNameRegionStadiumTimeDate(
			@Param("teamId") int teamId, 
			@Param("region") String region, 
			@Param("stadiumName") String stadiumName, 
			@Param("matchTime") String matchTime, 
			@Param("matchDate") String matchDate);
	
	public Payment selectPaymentByRegionStadiumTimeDate(
			@Param("teamId") int teamId, 
			@Param("region") String region, 
			@Param("stadiumName") String stadiumName, 
			@Param("matchTime") String matchTime, 
			@Param("matchDate") String matchDate);
	
	public int insertPaymentByTeamNameRegionStadiumTimeDate(
			@Param("teamId") int teamId, 
			@Param("region") String region, 
			@Param("stadiumName") String stadiumName, 
			@Param("matchTime") String matchTime, 
			@Param("matchDate") String matchDate);
	
	public void deletePaymentByTeamIdRegionStadiumTimeDate(
			@Param("teamId")int teamId,
			@Param("region")String region,
			@Param("stadiumName")String stadiumName,
			@Param("matchTime")String matchTime,
			@Param("matchDate")String matchDate);

}
