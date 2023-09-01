package com.soccer.match.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soccer.match.domain.Match;



@Repository
public interface MatchMapper {

	
	public List<Match> selectMatchByTeamId(int teamId);
	
	public List<Match> selectMatchByTeamIdForPaging(
			@Param("teamId") int teamId,
			@Param("startPage") int startPage, 
			@Param("POST_MAX_SIZE") int POST_MAX_SIZE);
	
	public List<Match> selectMatch();
	
	public Match selectMatchById(int id);
	
	public List<Match> selectMatchByReservationId(int reservationId);
	
	public List<Match> selectMatchForPaging(
			@Param("startPage") int startPage, 
			@Param("POST_MAX_SIZE") int POST_MAX_SIZE);
	
	public Match selectMatchByReservationIdOne(int reservationId);
	
	public Match selectMatchByReservationIdAndStateOne(
			@Param("reservationId") int reservationId,
			@Param("state") String state);
	
	public void deleteMatchByReservationId(int reservationId);
	
	public int deleteMatchById(int id);
	
	public int selectMatchCount();
	                
	
	public int insertMatch(
			@Param("teamId") int teamId, 
			@Param("reservationId") int reservationId, 
			@Param("title") String title, 
			@Param("price") int price, 
			@Param("content") String content);
	
	public int updateMatchById(
			@Param("id") int id, 
			@Param("title") String title,
			@Param("price") int price, 
			@Param("content") String content);
	
	public void updateMatchByIdState(
			@Param("id") int id, 
			@Param("state") String state);
	
	public void deleteMatchByReservationIdAndState(
			@Param("reservationId") int reservationId, 
			@Param("state") String state);
}





