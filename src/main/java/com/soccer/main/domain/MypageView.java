package com.soccer.main.domain;

import java.util.List;

import com.soccer.match.domain.Match;
import com.soccer.member.domain.Member;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class MypageView {

			// user
			private User user;
			
			// 소속 팀 정보 team
			private TeamEntity team;
			
			// 경기장 내역들
			private List<Reservation> ReservationList;
			
			// 매칭글
			private List<Match> matchList;
			
			// 매칭 확정들 
//			private List<MatchRelation> matchRelationList;
			
			// 멤버신청들 / 멤버들 
			private List<Member> memberList;
}
			
			
