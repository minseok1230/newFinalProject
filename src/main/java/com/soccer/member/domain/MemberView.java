package com.soccer.member.domain;

import java.util.List;

import com.soccer.team.entity.TeamEntity;

import lombok.Data;

@Data
public class MemberView {

		private TeamEntity team;
	
		private List<Member> member;
}
