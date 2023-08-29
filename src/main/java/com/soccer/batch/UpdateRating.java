package com.soccer.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soccer.rating.bo.RatingBO;
import com.soccer.team.bo.TeamBO;
import com.soccer.team.domain.Team;

@Component
public class UpdateRating {

	@Autowired
	private TeamBO teamBO;
	
	@Autowired
	private RatingBO ratingBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(cron = "0 0 2 * * *") // 매일 새벽 2시
	//@Scheduled(cron = "*/10 * * * * *")
	public void updateRatingAtMidnight() {
		List<Team> teamList = teamBO.getTeam();
		
		for(Team team : teamList) {
			Double rating = ratingBO.getAverageTemperatureByRatedTeamId(team.getId());
			if (rating == null) {
				logger.info("[#####ratingUpdate] update할게 없습니다. teamId:{}", team.getId());
				continue;
			}
			teamBO.updateTeamRatingById(team.getId(), rating);
			logger.info("[#####ratingUpdate] update성공 teamId:{}", team.getId());
		}
	}
}











