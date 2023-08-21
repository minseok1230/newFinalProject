package com.soccer.reservation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.reservation.domain.Reservation;

@Repository
public interface ReservationMapper {

	public Reservation selectReservationById(int id);
	
	public List<Reservation> selectReservationByTeamId(int teamId);

	public Reservation selectReservationByIdAndRegion(
			@Param("id") int id, 
			@Param("region") String region);
	
	public Reservation selectReservationByDateByStadiumBytime(
			@Param("matchDate") String matchDate, 
			@Param("stadium") String stadium, 
			@Param("matchTime") String matchTime);
	
	public int insertReservation(
			@Param("teamId") int teamId,
			@Param("matchDate") String matchDate,
			@Param("region") String region, 
			@Param("stadium") String stadium,
			@Param("matchTime") String matchTime, 
			@Param("teamName") String teamName);
	
	
	
	public int deleteReservationById(int id);
	
	public void updateReservationById(int id);
}
