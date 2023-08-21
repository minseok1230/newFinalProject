package com.soccer.match;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccer.match.bo.MatchService;
import com.soccer.match.domain.MatchView;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;

@Controller
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private MatchService matchService;
	
	/**
	 * 매칭글 작성
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/match_create_view")
	public String matchCreateView(Model model, HttpSession session) {
		
		// 매칭 경기장 예약 목록 
		int teamId = (int)session.getAttribute("userTeamId");
//		List<Reservation> reservationList = reservationBO.getReservationByTeamId(teamId);
		List<MatchView> matchViewList = matchService.generateMatchForReservationViewList(teamId);
		TeamEntity team = teamBO.getTeamById(teamId);
		
		model.addAttribute("matchViewList", matchViewList);
//		model.addAttribute("reservationList", reservationList);
		model.addAttribute("team", team);
		model.addAttribute("view", "match/matchCreate");
		return "template/layout";
	}
	
	/**
	 * 매칭글 목록
	 * @param model
	 * @return
	 */
	@GetMapping("/match_list_view")
	public String matchListView(Model model) {
		
		List<MatchView> matchViewList = matchService.generateMatchViewList(null);
		
		model.addAttribute("matchViewList", matchViewList);
		model.addAttribute("view", "match/matchList");
		return "template/layout";
	}
	
	// 매칭 세부글 
	@GetMapping("/match_detail_view")
	public String matchDetailView(
			@RequestParam("matchId") int matchId,
			HttpSession session,
			Model model) {
		
		int teamId = (int)session.getAttribute("userTeamId");
		String userRole = (String)session.getAttribute("userRole");
		
		// DB에서 상세 매칭글 가져오기 
		MatchView matchView = matchService.generateMatchView(matchId);
		
		model.addAttribute("userRole", userRole);
		model.addAttribute("teamId", teamId);
		model.addAttribute("matchView", matchView);
		model.addAttribute("view", "match/matchDetail");
		return "template/layout";
	}
	
	
	/**
	 * 매칭 수정페이지
	 * @param model
	 * @param matchId
	 * @return
	 */
	@GetMapping("/match_update_view")
	public String matchUpdateView(
			Model model,
			@RequestParam("matchId") int matchId) {
		
		MatchView matchView = matchService.generateMatchView(matchId);
		
		model.addAttribute("matchView", matchView);
		model.addAttribute("view", "match/matchModify");
		return "template/layout";
	}
}

















