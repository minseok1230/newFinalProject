package com.soccer.reservation;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.reservation.bo.ReservationBO;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.entity.TeamEntity;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.User;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/reservation_view")
	public String reservationView(HttpSession session, Model model) {
		
		// 경기장 api 가져오기 (BO 시키기)
		List<String> regionList = reservationBO.regionList();
		int userId = (int)session.getAttribute("userId");
		User user = userBO.getUserById(userId);
		
		// 팀 정보 가져오기
		int userTeamId = (int)session.getAttribute("userTeamId");
		TeamEntity team = teamBO.getTeamById(userTeamId);
		
		// 예약 내역 가져오기 (예약가능한지 확인용)
		
		model.addAttribute("user", user);
		model.addAttribute("team", team);
		model.addAttribute("regionList", regionList);
		model.addAttribute("view", "reservation/reservationField");
		return "template/layout";
	}
	
}


