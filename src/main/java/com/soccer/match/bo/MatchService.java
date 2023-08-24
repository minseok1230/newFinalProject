package com.soccer.match.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.comment.bo.CommentBO;
import com.soccer.comment.domain.CommentView;
import com.soccer.common.PageMaker;
import com.soccer.common.Paging;
import com.soccer.match.domain.Match;
import com.soccer.match.domain.MatchUpdateView;
import com.soccer.match.domain.MatchView;
import com.soccer.matchRelation.bo.MatchRelationBO;
import com.soccer.matchRelation.domain.MatchRelation;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Service
public class MatchService {
	
	private static final int POST_MAX_SIZE = 4;
	private static final int PAGE_MAX_SIZE = 5;

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
	
	@Autowired
	private MatchRelationBO matchRelationBO;
	
	public Map<String, Object> generateMatchViewList(Integer teamId , Integer clickPageNum) {
		Map<String, Object> result = new HashMap<>(); 
		// 리턴 값 ( 여러개의 MatchView )
		List<MatchView> teamViewList = new ArrayList<>();
		
		Integer clickPage;
		if (clickPageNum == null) {
			clickPage = 1;
		} else {
			clickPage = clickPageNum;
		}
		
		// 페이징
		Paging paging = new Paging();
		// total 구해주기 
		int total = matchBO.getMatchCount();
		paging.setTotalCount(total);
		paging.PagingList(clickPage, POST_MAX_SIZE, PAGE_MAX_SIZE );
		
		List<Match> matchList = new ArrayList<>();
		if (teamId == null) {
			matchList = matchBO.getMatchForPaging(paging.getBoardStartNum(), POST_MAX_SIZE);
		} else {
			matchList = matchBO.getMatchByTeamIdForPaging(teamId, paging.getBoardStartNum(), POST_MAX_SIZE);
		}
					
		PageMaker pageMaker = new PageMaker();;
		pageMaker.setPaging(paging);
		result.put("pageMaker", pageMaker);

		for (Match match : matchList) {
			MatchView matchView = new MatchView();

			Reservation reservation = reservationBO.getReservationById(match.getReservationId());
			matchView.setReservation(reservation);

			// match 넣기
			matchView.setMatch(match);

			// 팀 정보 넣기
			TeamEntity team = teamBO.getTeamById(match.getTeamId());
			matchView.setTeam(team);

			teamViewList.add(matchView);
		}
		result.put("teamViewList", teamViewList);
		
		return result;
	}
	
	public List<MatchView> generateMatchViewListForSearch(String regionSearch, String titleSearch){
		
		// 리턴 값 ( 여러개의 MatchView )
		List<MatchView> teamViewList = new ArrayList<>();
		
		List<Match> matchList = new ArrayList<>();
		
		matchList = matchBO.getMatch();
		if (regionSearch != null) {
			for (Match match : matchList) {
				MatchView matchView = new MatchView();

				Reservation reservation = reservationBO.getReservationById(match.getReservationId());
				if (!reservation.getRegion().contains(regionSearch)) {
					continue;
				}
				matchView.setReservation(reservation);

				// match 넣기
				matchView.setMatch(match);

				// 팀 정보 넣기
				TeamEntity team = teamBO.getTeamById(match.getTeamId());
				matchView.setTeam(team);

				teamViewList.add(matchView);
			}
			return teamViewList;
		} else {
			for (Match match : matchList) {
				MatchView matchView = new MatchView();
				
				if (!match.getTitle().contains(titleSearch)) {
					continue;
				}
				matchView.setMatch(match);
				
				Reservation reservation = reservationBO.getReservationById(match.getReservationId());
				matchView.setReservation(reservation);
				
				// 팀 정보 넣기
				TeamEntity team = teamBO.getTeamById(match.getTeamId());
				matchView.setTeam(team);

				teamViewList.add(matchView);
			}
			return teamViewList;
		}
	}
	
	public List<MatchView> generateMatchViewListForMyPage(Integer teamId){
		
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
			
			matchView.setMatch(match);

			// 경기장 정보 넣기 (region, content 조건이 있을대 생각)
			Reservation reservation = reservationBO.getReservationById(match.getReservationId());
			matchView.setReservation(reservation);

			// match 넣기

			// 팀 정보 넣기
			TeamEntity team = teamBO.getTeamById(match.getTeamId());
			matchView.setTeam(team);


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
	
	public List<MatchUpdateView> generateMatchUpdateViewList(){
		
		List<MatchUpdateView> matchUpdateViewList = new ArrayList<>();
		
		List<Reservation> reservationForUpdateList = reservationBO.getReservationYesterday();
		
			for (Reservation reservation : reservationForUpdateList) {
				MatchUpdateView matchUpdateView = new MatchUpdateView();

				matchUpdateView.setReservation(reservation);


				Match match = matchBO.getMatchByReservationIdOne(reservation.getId());
				MatchRelation matchRelation = matchRelationBO.getMatchRelationByMatchIdAndState(match.getId(), "매칭완료");
				
				if (matchRelation == null ) {
					return null;
				}
				matchUpdateView.setMatch(match);
				matchUpdateView.setMatchRelation(matchRelation);

				matchUpdateViewList.add(matchUpdateView);
			}
			return matchUpdateViewList;
	}
	
	
	
}











