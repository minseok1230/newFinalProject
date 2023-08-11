package com.soccer.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soccer.common.EncryptUtils;
import com.soccer.common.FileManagerService;
import com.soccer.common.FindPasswordMailService;
import com.soccer.common.MailService;
import com.soccer.user.dao.UserMapper;
import com.soccer.user.dao.UserRepository;
import com.soccer.user.domain.Certification;
import com.soccer.user.domain.User;

@Service
public class UserBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private FindPasswordMailService findPasswordMail;
	
	@Autowired
	private FileManagerService fileManager;
	
	public User getUserByEmail(String loginEmail){
		return userMapper.selectUserByEmail(loginEmail);
	}
	
	public User getUserByLoginEmailPassword(String loginEmail, String password) {
		return userMapper.selectUserByLoginEmailPassword(loginEmail, password);
	}
	
	public User getUserById(int id) {
		return userMapper.selectUserById(id);
	}
	
	public Integer addUser(String loginEmail,String password, String name, String phoneNumber, String birth, boolean role,  String loginType, String position) {
		return userMapper.insertUser(loginEmail, password, name, phoneNumber, birth, role, loginType, position);
	}
	
	public void updateUserByTeamIdAndRole(int teamId, String role, int leaderId) {
		userMapper.updateUserByTeamIdAndRole(teamId, role, leaderId);
	}
	
	/* 이메일 인증 보내고 DB 저장 */
	public Integer addCertification(String loginEmail) throws Exception {
		
		//db에서 삭제 먼저해줘~
		userMapper.deleteByEmail(loginEmail);
		
		// 해시암호화 후 저장 
		String code = mailService.sendSimpleMessgae(loginEmail);
		String hashedCode = EncryptUtils.sha256(code);
		return userMapper.insertCertification(loginEmail, hashedCode);
	}
	
	/* 이메일 인증 받고 DB 확인 */
	public Certification getCertificationByEmailNum(String loginEmail, String code) {
		String hashedCode = EncryptUtils.sha256(code);
		return userMapper.selectCertificationByEmailNum(loginEmail, hashedCode);
	}
	
	/* 비밀번호 재발급 */ 
	public String reissuePassword(String name, String loginEmail) throws Exception {
		//db 확인
		User user = userMapper.selectUserByLoginEmailName(name, loginEmail);
		
		if (user != null) {
			// 이메일 코드 보내기 
			String code = findPasswordMail.sendSimpleMessgae(loginEmail);
			String hashedCode = EncryptUtils.sha256(code);
			
			// 해시암호화 DB 저장
			Integer updateResult = userMapper.updateUserByNameLoginEmail(name, loginEmail, hashedCode);
			
			return "성공";
		} else {
			return "실패";
		}
	}
	
	
	// 프로필 수정
	public void updateUserById(int userId, String name, String phoneNumber, String birth, String position, MultipartFile file, String password) {
		User user = userMapper.selectUserById(userId);
		
		if (user == null) {
			logger.warn("##########[팀 수정] team is null. userId:{}", userId);
			return;
		}
		
		String profileImagePath = null;
		if (file != null) {
			profileImagePath = fileManager.saveFile(userId, file);
			
			if (profileImagePath != null && user.getProfileImagePath() != null) {
				fileManager.deleteFile(user.getProfileImagePath());
			}
		}
		
		String hashedPassword = null;
		if (!password.equals("")) {
			hashedPassword = EncryptUtils.sha256(password);
		} else {
		}
		userMapper.updateUserById(userId, name, phoneNumber, birth, position, profileImagePath, hashedPassword);
	}
	
}











