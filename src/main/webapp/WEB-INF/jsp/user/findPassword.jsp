<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 비밀번호 찾기 화면  -->

<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-top mt-4">
			<h2>비밀번호 찾기</h2>
		</div>
		<span class="text-secondary">회원가입시 입력하신 이메일과 휴대폰 번호를 입력해주세요</span><br><br><br>
		<form id="findPasswordForm" action="/user/find_password" method="post">
			<!-- 이메일 / 휴대전화번호 입력 -->
			<label for="loginEmail">이메일</label>
			<input type="text" id="loginEmail" class="form-control mb-3" name="loginEmail" placeholder="이메일을 입력하세요"> 
			<label for="phoneNumber">휴대전화번호</label>
			<input type="password" id="phoneNumber" class="form-control" name="phoneNumber" placeholder="휴대전화번호를 입력하세요">
		
			<!--로그인 버튼-->
			<button class="btn btn-secondary w-100 mt-4" id="temporary">임시 비밀번호 발송</button>
		</form>
	</div>
</div>