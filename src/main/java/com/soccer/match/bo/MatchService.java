package com.soccer.match.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.comment.bo.CommentBO;
import com.soccer.comment.domain.CommentView;
import com.soccer.match.domain.Match;
import com.soccer.match.domain.MatchView;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class MatchService {

	@Autowired
	private MatchBO matchBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public List<MatchView> generateMatchViewList(Integer teamId) {
		// 리턴 값 ( 여러개의 MatchView )
		List<MatchView> teamViewList = new ArrayList<>();

		List<Match> matchList = new ArrayList<>();
		if (teamId == null) {
			matchList = matchBO.getMatch();
		} else {
			matchList = matchBO.getMatchByTeamId(teamId);
		}

		for (Match match : matchList) {
			MatchView matchView = new MatchView();

			// match 넣기
			matchView.setMatch(match);

			// 팀 정보 넣기
			TeamEntity team = teamBO.getTeamById(match.getTeamId());
			matchView.setTeam(team);

			// 경기장 정보 넣기
			Reservation reservation = reservationBO.getReservationById(match.getReservationId());
			matchView.setReservation(reservation);

			teamViewList.add(matchView);
		}

		return teamViewList;
	}

	public MatchView generateMatchView(int matchId) {

		MatchView matchView = new MatchView();

		Match match = matchBO.getMatchById(matchId);
		matchView.setMatch(match);
		
		// 팀 정보 넣기
		TeamEntity team = teamBO.getTeamById(match.getTeamId());
		matchView.setTeam(team);

		// 경기장 정보 넣기
		Reservation reservation = reservationBO.getReservationById(match.getReservationId());
		matchView.setReservation(reservation);

		// 팀장 정보 넣기
		User user = userBO.getUserByTeamIdAndRole(match.getTeamId(), "팀장");
		matchView.setLeader(user);
		
		// 댓글 넣기
		// 댓글 세팅
		List<CommentView> commentViewList = commentBO.generateCommentViewList(matchId);
		matchView.setCommentList(commentViewList);

		return matchView;
	}
	
	public List<MatchView> generateMatchForReservationViewList(Integer teamId) {
		
		// 리턴 값 ( 여러개의 MatchView )
		List<MatchView> teamViewList = new ArrayList<>();

		List<Reservation> reservationList = reservationBO.getReservationByTeamId(teamId);
		
		for (Reservation reservation : reservationList) {
			MatchView matchView = new MatchView();
			
			Match match = matchBO.getMatchByReservationIdOne(reservation.getId());
			
			if (match == null) {
				matchView.setReservation(reservation);
			} else {
				continue;
			}
			teamViewList.add(matchView);
		}
		

		return teamViewList;
	}
	
}











