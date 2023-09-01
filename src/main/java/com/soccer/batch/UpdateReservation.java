package com.soccer.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;

@Component
public class UpdateReservation {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReservationBO reservationBO;
	
	
	/* 경기날짜가 일주일 이하로남은 경기 or 이미 날짜가 지난 경기 취소 못하게 변경*/
	@Scheduled(cron = "0 30 0 * * *") // 매일 밤 12시 30분
//	@Scheduled(cron = "*/10 * * * * *")
	public void updateReservationAtMidnight() {
		List<Reservation> updateReservationList = reservationBO.getReservationWeekAgoByMatchDate();
		
		if (updateReservationList == null) {
			logger.info("[#####updateReservation] update할게 존재하지 않는다.");
		} else {
			for (Reservation reservation : updateReservationList) {
				logger.info("[#####updateReservation] reservationId:{}", reservation.getId());
				reservationBO.updateReservationById(reservation.getId());
			}
		}
		
	}

}











