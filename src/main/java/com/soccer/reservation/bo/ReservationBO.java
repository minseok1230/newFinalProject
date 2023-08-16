package com.soccer.reservation.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.common.StadiumApiService;
import com.soccer.reservation.dao.ReservationMapper;
import com.soccer.reservation.domain.Reservation;

@Service
public class ReservationBO {

	@Autowired
	private StadiumApiService stadiumApiService;
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	
	// 경기장 APi 지역(ex. 화성, 안양, 안성...) 가져오기 
	public List<String> regionList(){
		List<Map<String, Object>> regionList = stadiumApiService.getAllRowValues(null);
		List<String> result = new ArrayList<>();
		result.add((String) regionList.get(0).get("시군명"));
		
		for (int i = 1; i < regionList.size(); i++) {
			if (regionList.get(i).get("시군명").equals(regionList.get(i-1).get("시군명"))) {
				continue;
			}
			result.add((String) regionList.get(i).get("시군명"));
		}
		
		return result;
	}
	
	// 경기장 APi 경기장(ex, 자유공원, 비산체육공원...) 가져오기
	public List<String> stadiumList(String region){
		List<Map<String, Object>> stadiumList = stadiumApiService.getAllRowValues(region);
		List<String> result = new ArrayList<>();
		result.add((String) stadiumList.get(0).get("시설명"));
		
		for (int i = 1; i < stadiumList.size(); i++) {
			if (stadiumList.get(i).get("시설명").equals(stadiumList.get(i-1).get("시설명"))) {
				continue;
			}
			result.add((String) stadiumList.get(i).get("시설명"));
		}
		
		return result;
	}
	
	public int addReservation(int teamId, String matchDate,String region, String stadium, String matchTime, String teamName) {
		
		// 중복 확인
		Reservation reservationForCheck = reservationMapper.selectReservationByDateByStadiumBytime(matchDate, stadium, matchTime);
		if (reservationForCheck != null) {
			return 300;
		} else {
			return reservationMapper.insertReservation(teamId, matchDate, region, stadium, matchTime, teamName);
		}
	}
	
	public List<Reservation> getReservationByTeamId(int teamId){
		return reservationMapper.selectReservationByTeamId(teamId);
	}
	
	public Reservation getReservationById(int id) {
		return reservationMapper.selectReservationById(id);
	}
}

























