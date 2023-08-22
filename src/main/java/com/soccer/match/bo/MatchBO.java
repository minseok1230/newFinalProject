package com.soccer.match.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.match.dao.MatchMapper;
import com.soccer.match.domain.Match;

@Service
public class MatchBO {

	@Autowired
	private MatchMapper matchMapper;
	
	public List<Match> getMatch(){
		return matchMapper.selectMatch();
	}
	
	public List<Match> getMatchForPaging(int startPage, int POST_MAX_SIZE){
		return matchMapper.selectMatchForPaging(startPage, POST_MAX_SIZE);
	}
	
	public Match getMatchById(int id) {
		return matchMapper.selectMatchById(id);
	}
	
	public Match getMatchByReservationIdOne(int reservationId) {
		return matchMapper.selectMatchByReservationIdOne(reservationId);
	}
	
	public List<Match> getMatchByTeamId(int teamId){
		return matchMapper.selectMatchByTeamId(teamId);
	}
	
	public List<Match> getMatchByTeamIdForPaging(int teamId,int startPage, int POST_MAX_SIZE){
		return matchMapper.selectMatchByTeamIdForPaging(teamId, startPage, POST_MAX_SIZE);
	}
	
	public List<Match> getMatchByReservationId(int reservationId){
		return matchMapper.selectMatchByReservationId(reservationId);
	}
	
	public int getMatchCount() {
		return matchMapper.selectMatchCount();
	}
	
	
	public int addMatch(int teamId, int reservationId, String title, int price, String content) {
		return matchMapper.insertMatch(teamId, reservationId, title, price, content);
	}
	
	public int updateMatchById(int id, String title, int price, String content) {
		return matchMapper.updateMatchById(id, title, price, content);
	}
	
	
	
	public void deleteMatchByReservationId(int reservationId) {
		matchMapper.deleteMatchByReservationId(reservationId);
	}
	
	public void updateMatchById(int id){
		matchMapper.updateMatchByIdState(id);
	}
	
	
	
	
}



















