package com.soccer.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.common.EncryptUtils;
import com.soccer.user.bo.UserBO;
import com.soccer.user.domain.Certification;
import com.soccer.user.domain.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	
	
	/**
	 * 중복확인 API
	 * @param loginEmail
	 * @return
	 */
	@RequestMapping("/is_duplicated_email")
	public Map<String, Object> isDuplicatedEmail(
			@RequestParam("loginEmail") String loginEmail){
		
		Map<String, Object> result = new HashMap<>();
		result.put("isDuplicationEmial", false);
		//db select
		User user = userBO.getUserByEmail(loginEmail);
		
		if (user != null) {
			result.put("isDuplicationEmial", true);
		}
		
		return result;
	}
	
	/**
	 * 회원가입
	 * @param loginEmail
	 * @param password
	 * @param name
	 * @param phoneNumber
	 * @param birth
	 * @return
	 */
	@RequestMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginEmail") String loginEmail,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("birth") String birth
			){
		
		Map<String, Object> result = new HashMap<>();
		boolean role = false;
		String loginType = "이메일";
		
		// 비밀번호 해싱
		// 123 : a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
		String hashedPassword = EncryptUtils.sha256(password);
		
		// db insert
		Integer insertUser = userBO.addUser(loginEmail, hashedPassword, name, phoneNumber, birth, role, loginType);
		
		if (insertUser != null) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데 실패했습니다.");
		}
		return result;
	}
	
	
	/**
	 * 로그인
	 * @param loginEmail
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginEmail") String loginEmail,
			@RequestParam("password") String password,
			HttpSession session
			){
		//해싱 암호 
		String hashedPassword = EncryptUtils.sha256(password);
		
		// db select
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByLoginEmailPassword(loginEmail, hashedPassword);
		
		// 처리
		if (user != null) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginEmail", user.getEmail());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userTeamId", user.getTeamId());
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("errorMessage", "아이디/비밀번호를 다시 확인해주세요");
		}
		
		return result;
	}
	
	/**
	 *  사용자 로그인 인증
	 *	인증번호 발송
	 * @param loginEmail
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/mail_certification")
	public Map<String, Object> mailCertification(
			@RequestParam("loginEmail") String loginEmail) throws Exception{
    	
    	Map<String, Object> result = new HashMap<>();
        
        // email 보내줘~ 하고 BO에 넘김  ( 나중에 결과 확인용)
        Integer authenticationResult = userBO.addCertification(loginEmail);
        
        if (authenticationResult != null) {
        	result.put("code", 1);
        } else {
        	result.put("errorMessage", "인증번호 전송에 실패했습니다.");
        }

        return result;
    }
	
	/**
	 * 인증번호 확인
	 * @param loginEmail
	 * @param certificationNum
	 * @return
	 */
    @RequestMapping("/mail_certification_check")
    public Map<String, Object> mailCertificationCheck(
    		@RequestParam("loginEmail") String loginEmail, 
    		@RequestParam("certificationNum") String certificationNum){
    	Map<String, Object> result = new HashMap<>();
    	
    	//DB에서 인증번호 확인
    	Certification certification = userBO.getCertificationByEmailNum(loginEmail, certificationNum);
    	
    	// 통과
    	if (certification != null ) {
    		result.put("code", 1);
    	} else {
    		result.put("errorMessage", "인증번호가 일치하지 않습니다.");
    	}
    	return result;
    }
    
    
    // 비밀번호 찾기
    @PostMapping("/find_password")
    public Map<String, Object> findPassword(
    		@RequestParam("loginEmail") String loginEmail,
    		@RequestParam("phoneNumber") String phoneNumber,
    		@RequestParam("name") String name
    		) throws Exception{
    	
    	Map<String, Object> result = new HashMap<>();
    	String rePassword = userBO.reissuePassword(name, loginEmail);
    	// 이메일 확인 후 비번 바꿔주기 
    	if (rePassword.equals("성공")){
    		result.put("code", 1);
    		result.put("result", "성공");
    	} else {
    		result.put("code", 500);
    		result.put("errorMessage", "존재하는 사용자가 없습니다.");
    	}
    	
    	return result;
    }
	
}
















