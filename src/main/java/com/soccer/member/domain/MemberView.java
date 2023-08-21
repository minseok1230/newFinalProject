package com.soccer.member.domain;

import com.soccer.team.entity.TeamEntity;
import com.soccer.user.domain.User;

import lombok.Data;

@Data
public class MemberView {

		private Member member;
		
		private TeamEntity team;
		
		private User user;
}
