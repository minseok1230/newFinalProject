package com.soccer.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccer.aop.TimeTrace;
import com.soccer.user.bo.UserBO;
import com.soccer.user.bo.UserService;
import com.soccer.user.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 로그인
	 * @param model
	 * @return
	 */
	@TimeTrace
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("view", "user/signIn");
		return "template/layout";
	}
	
	/**
	 * 회원가입
	 * @param model
	 * @return
	 */
	@TimeTrace
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/layout";
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginEmail");
		session.removeAttribute("userName");
		session.removeAttribute("userTeamId");
		
		return "redirect:/user/sign_in_view";
	}
	
	/**
	 * 비밀번호 찾기
	 * @param model
	 * @return
	 */
	@GetMapping("/find_password_view")
	public String findPasswordView(Model model) {
		model.addAttribute("view", "user/findPassword");
		return "template/layout";
	}
	
	/**
	 * 프로필 수정
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/user_update_view")
	public String userUpdateView(Model model, HttpSession session) {
		
		//session
		int userId = (int)session.getAttribute("userId");
		// 수정할 프로필 정보 DB 가져오기 
		User user = userBO.getUserById(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("view", "user/userModify");
		return "template/layout";
	}
	
	// 카카오 회원가입 화면
	@GetMapping("/kakao_sign_up_view")
	public String kakaoSignUpView(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		
		model.addAttribute("password", password);
		model.addAttribute("email", email);
		model.addAttribute("view", "user/kakaoSignUp");
		return "template/layout";
	}
	
	// 카카오 로그인 / 회원가입
	// 카카오톡 로그인
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(@RequestParam("code") String code, HttpSession session) {
		
		Map<String,Object> kakao = userService.possibleKakaoLogin(code);
		User user = userBO.getUserByEmail((String)kakao.get("email"));
		if (user == null) {
			return "redirect:/user/kakao_sign_up_view?email=" + kakao.get("email") + "&password=" + kakao.get("password");
		} else {
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginEmail", user.getEmail());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userTeamId", user.getTeamId());
			session.setAttribute("userRole", user.getRole());
			return "redirect:/main/main_view";
		}
	}
}










