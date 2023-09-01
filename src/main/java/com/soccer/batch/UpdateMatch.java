package com.soccer.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soccer.comment.bo.CommentBO;
import com.soccer.match.bo.MatchBO;
import com.soccer.match.bo.MatchService;
import com.soccer.match.domain.Match;
import com.soccer.match.domain.MatchUpdateView;
import com.soccer.matchRelation.bo.MatchRelationBO;
import com.soccer.reservation.bo.ReservationBO;
import com.soccer.reservation.domain.Reservation;

@Component
public class UpdateMatch {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchBO matchBO;
	
	@Autowired
	private MatchRelationBO matchRelationBO;
	
	@Autowired
	private ReservationBO reservationBO;
	
	@Autowired
	private CommentBO commentBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/* 경기 정상적으로 진행 -> 경기 날짜 지나고 state 매칭완료 -> 경기 완료로 변경 */
	@Scheduled(cron = "0 0 0 * * *") //매일밤 12시
//	@Scheduled(cron = "*/10 * * * * *")
	public void updateMatchAtMidnight() {
		List<MatchUpdateView> matchUpdateList = matchService.generateMatchUpdateViewList();
		
		if (matchUpdateList == null) {
			logger.info("[#####matchUpdate] update할게 존재하지 않는다.");
			return;
		}
		
			for (MatchUpdateView matchUpdateView : matchUpdateList) {
				
				// match update
				Match match = matchBO.getMatchByReservationIdAndStateOne(matchUpdateView.getReservation().getId(), "매칭완료");
				if (match == null) {
					continue;
				}
				
				logger.info("[###matchUpdate] matchId:{}", match.getId());
				matchBO.updateMatchByIdState(match.getId(), "경기완료");

				// matchRelation update
				int matchRelationId = matchUpdateView.getMatchRelation().getId();
				logger.info("[###matchRelationUpdate] matchRelationId:{}", matchRelationId);
				matchRelationBO.updateMatchRelationByIdState(matchRelationId, "경기완료");
			}
	}
	
	
	/* 경기 날짜 지나고 state "모집중" match, comment 삭제 ==> 매칭이 안된 경기이기 때문에 매너온도 필요 x  댓글도 삭제해야되네....*/
	@Scheduled(cron = "0 0 1 * * *") //매일밤 1시
//	@Scheduled(cron = "*/10 * * * * *")
	public void deleteMatchOverdue() {
		List<Reservation> reservationList = reservationBO.getReservationYesterday();
		
		if (reservationList == null) {
			logger.warn("[#####matchDeleteOverdue] 삭제할 게시물이 존재하지 않는다.");
		} else {
			for (Reservation reservation : reservationList) {
				logger.info("[#####matchDeleteOverdue] 매칭안된 경기들 삭제 reservationId:{}", reservation.getId());
				
				String state = "모집중";
				Match match = matchBO.getMatchByReservationIdAndStateOne(reservation.getId(), state);
				if (match == null) {
					continue;
				}
				
				logger.info("[#####matchDeleteOverdue] 매칭안된 매칭글 삭제 matchId:{}", match.getId());
				commentBO.deleteCommentByBoardIdAndType(match.getId(), "매칭글");
				matchBO.deleteMatchByReservationIdAndState(reservation.getId(), state);
			}
		}
	}
	
	
	/* 경기 날짜 지나고 state "경기완료" 삭제 ==> 매칭이되어 경기가 진행되었기 때문에 7일뒤에 일괄 삭제 */
	@Scheduled(cron = "0 30 0 * * *") //매일밤 12시
//	@Scheduled(cron = "*/10 * * * * *")
	public void deleteEndMatch() {
		List<Reservation> reservationList = reservationBO.getReservationByMatchDateSevenDaysAgo();
		
		if (reservationList == null) {
			logger.warn("[#####matchDeleteEndMatch] 삭제할 경기장이 존재하지 않는다.");
		} else {
			for (Reservation reservation : reservationList) {
				logger.info("[#####matchDeleteEnd] 매너온도 기간 지난것들  reservationId:{}", reservation.getId());
				
				Match match = matchBO.getMatchByReservationIdAndStateOne(reservation.getId(), "경기완료");
				if (match == null) {
					continue;
				}
				
				reservationBO.deleteReservationById(reservation.getId());
				matchBO.deleteMatchByReservationIdAndState(reservation.getId(), "경기완료");
				matchRelationBO.deleteMatchRelationByMatchId(match.getId());
				commentBO.deleteCommentByBoardIdAndType(match.getId(), "매칭글");
			}
			
		}
	}
	

}
