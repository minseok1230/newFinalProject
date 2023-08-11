package com.soccer.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@GetMapping("/reservation_view")
	public String reservationView(Model model) {
		model.addAttribute("view", "reservation/reservationField");
		return "template/layout";
	}
}


