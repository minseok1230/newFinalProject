<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 회원가입 화면 -->

<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>SIGN UP</h2>
		</div>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<!-- 이메일 -->
			<label for="loginEmail">이메일</label>
			<div class="d-flex">
				<input type="text" class="form-control col-7" id="loginEmail" name="loginEmail" placeholder="이메일을 입력하세요">
				<button type="button" id="emailCheckBtn" class="btn btn-info col-2">확인</button>
				<button type="button" id="certificationBtn" class="btn btn-secondary col-3">인증번호 발송</button>
			</div>
			<%-- 이메일 체크 결과 --%>
			<%-- d-none 클래스: display none (보이지 않게) --%>
			<div>
				<div id="emailCheckDuplicated" class="small text-danger d-none">이미 사용중입니다.</div>
				<div id="emailCheckOk" class="small text-success d-none">사용 가능합니다.</div>
				<div id="emailFormatCheck" class="small text-success d-none">이메일 형식이 맞지 않습니다.</div>				
			</div>
			
			<!-- 인증번호 확인 -->
			<div class="d-flex mt-3">
				<input type="text" class="form-control col-9" id="certificationNum"  placeholder="인증번호">
				<button type="button" id="certificationCheckBtn" class="btn btn-secondary col-3">인증</button>
			</div>
			
			<!-- 비밀번호  -->
			<div class="mt-3">
				<label for="password">비밀번호</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
				<div id="passwordCheck" class="small text-danger d-none">6자 이상 입력하세요.</div>
			</div>
			
			<!-- 비밀번호 확인  -->
			<div class=" mt-3">
				<label for="confirmPassword">비밀번호 확인</label>
				<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 입력하세요">
			</div>
			
			<!-- 이름  -->
			<div class="mt-3">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요">
			</div>
			
			<!-- 휴대전화번호  -->
			<div class="mt-3">
				<label for="phoneNumber">휴대전화번호</label>
				<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="휴대전화번호를 입력하세요 (ex. 010-1111-1111)">
				<div id="phoneNumberFormatCheck" class="small text-success d-none">휴대전화번호 형식이 맞지 않습니다.</div>	
			</div>
			
			<!-- 생년월일  -->
			<div class="my-3">
				<label for="birth">생년월일</label>
				<input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일을 입력하세요 (ex. 20021022)">
			</div>
			
			<!--  가입 버튼 -->
			<button type="submit" id="signUpBtn" class="btn btn-secondary mt-4 w-100">가입하기</button>	
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		
		$('#password').keyup(function(){
			
			$('#passwordCheck').addClass('d-none');
			let password = $('#password').val();
			
			if (password.length < 6){
				$('#passwordCheck').removeClass('d-none');
			}
		});
		
		// 아이디(이메일) 유형검사 및 중복 검사
		$("#emailCheckBtn").on('click', function(){
			
			// 경고 문구 초기화 
			$('#emailCheckDuplicated').addClass('d-none');
			$('#emailCheckOk').addClass('d-none');
			$('#emailFormatCheck').addClass('d-none');
			
			let loginEmail = $('#loginEmail').val().trim();
			let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			
			
			// 이메일 형식 체크 
			if (loginEmail.match(regExp) != null){
				$('#emailCheckOk').removeClass('d-none');
			}
			else{
				$('#emailFormatCheck').removeClass('d-none');
			}
			
			$.ajax({
				url: "/user/is_duplicated_email"
				, data: {"loginEmail" : loginEmail}
			
				, success: function(data){
					if (data.isDuplicationEmial){
						$('#emailCheckOk').addClass('d-none');
						$('#emailCheckDuplicated').removeClass('d-none');
					}
				}
				
				, error: function(request, status, error){
					alert("중복확인에 실패했습니다. 관리자에게 문의 부탁드립니다.")
				}
			});
		});
		
		
		// 회원 가입
		$('#signUpForm').on('submit', function(e){
			e.preventDefault(); // 서브밋 기능 중단 // 화면 내려가기 방지
			
			$('#phoneNumberFormatCheck').addClass('d-none');
			
			// validation
			let loginEmail = $('#loginEmail').val().trim();
			let password = $("#password").val();
			let confirmPassword = $('#confirmPassword').val();
			let name = $('#name').val().trim();
			let phoneNumber = $('#phoneNumber').val().trim();
			let birth = $('#birth').val().trim();
			
			// 전화번호 유효성 검사
			let patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
			
			if (!loginEmail) {
				alert("이메일을 입력하세요");
				return false;
			}
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요");
				return false;
			}
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}
			if (!name) {
				alert("이름을 입력하세요");
				return false;
			}
			if (!phoneNumber) {
				alert("전화번호를 입력하세요");
				return false;
			}
			if (!birth) {
				alert("생년월일을 입력하세요");
				return false;
			}
			
			if (patternPhone.test(phoneNumber) == false){
				$('#phoneNumberFormatCheck').removeClass('d-none');
				return false;
			}
			// 아이디 중복확인 완료 됐는지 확인
			if ($('#emailCheckOk').hasClass('d-none')) {
				alert("이메일 확인을 다시 해주세요");
				return false;
			}
			
			let url = $(this).attr('action');
			console.log(url);
			let params = $(this).serialize(); // 폼태그에 있는 name 속성-값들로 파라미터 구성
			console.log(params);
			
			$.post(url, params)
			.done(function(data){
				if (data.code == 1){
					alert("가입을 환영합니다!!");
					location.href = "/user/sign_in_view";
				} else{
					alert(errorMessage);
				}
			});
		});
	});
</script>
















