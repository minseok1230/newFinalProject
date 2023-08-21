package com.soccer.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.member.bo.MemberBO;

@RestController
@RequestMapping("/member")
public class MemberRestController {

	@Autowired
	private MemberBO memberBO;
	
	@PostMapping("/create_member")
	public Map<String, Object> createMember(
			@RequestParam("teamId") int teamId,
			HttpSession session){
		
		//session
		int userId = (int)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		// db insert
		int insertMemberResult = memberBO.addMember(userId, teamId);
		if (insertMemberResult > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "팀 가입신청에 실패하였습니다.");
		}
		
		return result;
	}
	
	@DeleteMapping("/{teamId}/{userId}")
	public Map<String, Object> deleteMember(
			@PathVariable int teamId,
			@PathVariable int userId){
		Map<String, Object> result = new HashMap<>();
		
		// db 삭제 (member)
		int deleteResult = memberBO.deleteMemberByTeamIdAndUserId(teamId, userId);
		if (deleteResult > 0) {
			result.put("code", 1);
		} else {
			result.put("errorMessage", "취소 요청 실패하였습니다.");
		}
		
		
		return result;
	}
	
	@PutMapping("/{teamId}/{userId}")
	public Map<String, Object> updateMember(
			@PathVariable int teamId,
			@PathVariable int userId){
		Map<String, Object> result = new HashMap<>();
		
		// db update (member)
		memberBO.updateMemberByTeamIdAndUserId(teamId, userId);
		result.put("code", 1);
		
		
		return result;
	}
	
}
