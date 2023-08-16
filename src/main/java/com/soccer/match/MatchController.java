package com.soccer.match;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.match.bo.MatchBO;
import com.soccer.match.domain.MatchView;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;
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
	private MatchBO matchBO;
	
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
		List<Reservation> reservationList = reservationBO.getReservationByTeamId(teamId);
		TeamEntity team = teamBO.getTeamById(teamId);
		
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("team", team);
		model.addAttribute("view", "match/matchCreate");
		return "template/layout";
	}
	
	// 매칭글 목록
	@GetMapping("/match_list_view")
	public String matchListView(Model model) {
		
		List<MatchView> matchViewList = matchBO.generateMatchView();
		
		model.addAttribute("matchViewList", matchViewList);
		model.addAttribute("view", "match/matchList");
		return "template/layout";
	}
}

















