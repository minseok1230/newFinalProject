package com.soccer.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.reservation.bo.ReservationBO;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationBO reservationBO;
	
	@GetMapping("/reservation_view")
	public String reservationView(Model model) {
		
		// 경기장 api 가져오기 (BO 시키기)
		List<String> regionList = reservationBO.regionList();
		
		model.addAttribute("regionList", regionList);
		model.addAttribute("view", "reservation/reservationField");
		return "template/layout";
	}
}


