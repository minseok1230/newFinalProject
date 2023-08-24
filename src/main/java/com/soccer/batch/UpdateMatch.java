package com.soccer.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soccer.match.bo.MatchBO;
import com.soccer.match.bo.MatchService;
import com.soccer.match.domain.MatchUpdateView;
import com.soccer.matchRelation.bo.MatchRelationBO;

@Component
public class UpdateMatch {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchBO matchBO;
	
	@Autowired
	private MatchRelationBO matchRelationBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Scheduled(cron = "0 0 0 * * *") //매일밤 12시
	@Scheduled(cron = "*/10 * * * * *")
	public void updateMatchAtMidnight() {
		List<MatchUpdateView> matchUpdateList = matchService.generateMatchUpdateViewList();
		
		if (matchUpdateList == null) {
			logger.info("[#####matchUpdate] update할게 존재하지 않는다.");
		} else {

			for (MatchUpdateView matchUpdateView : matchUpdateList) {

				// match update
				int matchId = matchUpdateView.getMatch().getId();
				logger.info("[###matchUpdate] matchId:{}", matchId);
				matchBO.updateMatchByIdState(matchId, "경기완료");

				// matchRelation update
				int matchRelationId = matchUpdateView.getMatchRelation().getId();
				logger.info("[###matchRelationUpdate] matchRelationId:{}", matchRelationId);
				matchRelationBO.updateMatchRelationByIdState(matchRelationId, "경기완료");
			}
		}
	}

}
