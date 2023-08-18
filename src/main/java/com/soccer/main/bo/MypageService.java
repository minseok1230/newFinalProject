package com.soccer.main.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.main.domain.MypageView;
import com.soccer.match.bo.MatchBO;
import com.soccer.match.domain.Match;
import com.soccer.member.bo.MemberBO;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class MypageService {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private MatchBO matchBO;
	
	@Autowired
	private MemberBO memberBO;
	
	public MypageView generateMyPageView(int userId) {
		
		MypageView mypageView = new MypageView();
		// user
		User user = userBO.getUserById(userId);
		mypageView.setUser(user);
		
		// team
		TeamEntity team = teamBO.getTeamById(user.getTeamId());
		mypageView.setTeam(team);
		
		// 경기장 내역들
		List<Reservation> reservationList = reservationBO.getReservationByTeamId(user.getTeamId());
		mypageView.setReservationList(reservationList);
		
		// 매칭글 목록들
		List<Match> matchList = matchBO.getMatchByTeamId(user.getTeamId());	
		mypageView.setMatchList(matchList);
		
		// 매칭 확정들 
//		List<MatchRelation> matchRelationList = matchRelationBO.get~	
		
		// 멤버신청들
//		List<Member> memberList = memberBO.GET
		
		return mypageView;
		
	}
}
