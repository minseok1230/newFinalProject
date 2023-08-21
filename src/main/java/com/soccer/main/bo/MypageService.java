package com.soccer.main.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.main.domain.MypageView;
import com.soccer.match.bo.MatchBO;
import com.soccer.match.bo.MatchService;
import com.soccer.match.domain.MatchView;
import com.soccer.member.bo.MemberBO;
import com.soccer.member.bo.MemberService;
import com.soccer.member.domain.Member;
import com.soccer.member.domain.MemberView;
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
	private MatchService matchService;
	
	@Autowired
	private MemberBO memberBO;
	
	@Autowired
	private MemberService memberService;
	
	public MypageView generateMyPageView(int userId,  String role) {
		
		MypageView mypageView = new MypageView();
		
		// user
		User user = userBO.getUserById(userId);
		mypageView.setUser(user);
		
		Integer teamId = user.getTeamId();
		if (teamId == null) {
			Member member = memberBO.getMemberByUserId(userId);
			mypageView.setRequestMember(member);
			if (member != null) {
				TeamEntity team = teamBO.getTeamById(member.getTeamId());
				mypageView.setTeam(team);
			}
		}
		
		/* 팀이 없는 경우에는 안보여줌 */
		if (teamId != null) {
			
		// team
		TeamEntity team = teamBO.getTeamById(user.getTeamId());
		mypageView.setTeam(team);
		
		// 경기장 내역들
		List<Reservation> reservationList = reservationBO.getReservationByTeamId(user.getTeamId());
		mypageView.setReservationList(reservationList);
		
		
		// 매칭글 목록들
		List<MatchView> matchViewList = matchService.generateMatchViewList(user.getTeamId(), null, null);
		mypageView.setMatchViewList(matchViewList);
		
		// 가입 신청 목록 
		List<MemberView> memberList = memberService.generateMemberViewByApproval(user.getTeamId(), false);
		mypageView.setMemberViewList(memberList);
		}
		return mypageView;
		
	}
}
