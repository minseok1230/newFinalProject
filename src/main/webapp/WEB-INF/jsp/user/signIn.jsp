<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 화면-->
 
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>LOGIN</h2>
		</div>
		<form id="loginForm" action="/user/sign_in" method="post">
			<!-- 이메일 / 비밀번호 입력 -->
			<label for="loginEmail" class="text-secondary">이메일</label>
			<input type="text" id="loginEmail" class="form-control mb-3" name="loginEmail" placeholder="이메일을 입력하세요"> 
			<label for="password" class="text-secondary">비밀번호</label>
			<input type="password" id="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
			<div class="d-flex justify-content-end my-2">
				<a href="/user/find_password_view">비밀번호 찾기</a>
			</div>
		
			<!--로그인 버튼-->
			<button class="btn btn-secondary w-100" id="loginButton">로그인</button>
			<hr>
			<div class="w-100 text-center">
				<small>OR</small>
			</div>
			<button class="btn btn-warning w-100 mt-3" id="kakaoLoginButton">카카오 로그인</button>
			<hr>
			<div class="w-100 text-center text-info">
				<small>계정이 없으신가요?</small>
			</div>
			<a href="/user/sign_up_view" class="btn btn-secondary w-100 mt-3" id="signUpBtn">회원가입</a>
		</form>
	</div>
</div>


<script>
	$(document).ready(function(){
		$('#loginForm').on('submit',function(e){
			e.preventDefault();
			
			let loginEmail = $('#loginEmail').val().trim();
			let password = $('#password').val();
			
			if (!loginEmail){
				alert("아이디를 입력하세요");
				return false;
			}
			if (!password){
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			let url = $(this).attr('action');
			console.log(url);
			let params = $(this).serialize(); 
			console.log(params);
			
			$.post(url, params)
			.done(function(data){
				
				if(data.code == 1){
					location.href = "/main/main_view" // 메인화면
				} else {
					alert(data.errorMessage);
				}
			});

			
		});
		
	});
</script>

















