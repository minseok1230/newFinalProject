<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 회원가입 화면 -->

<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>SIGN UP</h2>
		</div>
			<!-- 이메일 -->
			<label for="loginEmail">이메일</label>
			<div class="input-group">
				<input type="text" class="form-control" id="loginEmail" name="loginEmail" value="${email}" readonly>
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
			
			<!-- 주요 포지션 -->
			<div class="my-3">
				<label for="position">포지션</label>
				<select id="position" name="position" class="form-control" required>
					<option value="" disabled selected>포지션 선택</option>
					<option value="ST">ST</option>
					<option value="LF">LF</option>
					<option value="RF">RF</option>
					<option value="CAM">CAM</option>
					<option value="CDM">CDM</option>
					<option value="CM">CM</option>
					<option value="CB">CB</option>
					<option value="RB">RB</option>
					<option value="LB">LB</option>
				</select>
			</div>
			
			<!--  가입 버튼 -->
			<button type="submit" id="signUpBtn" class="btn btn-secondary mt-4 w-100" data-password="${password}">가입하기</button>	
	</div>
</div>

<script>
$(document).ready(function(){
	// 회원 가입
	$('#signUpBtn').on('click', function(e){
		e.preventDefault(); // 서브밋 기능 중단 // 화면 내려가기 방지
		
		// validation
		let loginEmail = $('#loginEmail').val().trim();
		let password = $(this).data("password");
		let name = $('#name').val().trim();
		let phoneNumber = $('#phoneNumber').val().trim();
		let birth = $('#birth').val().trim();
		let position = $('#position').val();
		
		// 전화번호 유효성 검사
		let patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
		
		
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
		
		
		$.ajax({
			
			type: "post"
			, url : "/user/sign_up"
			, data : {"loginEmail":loginEmail, "password":password, "name":name, "phoneNumber":phoneNumber, "birth": birth, "position":position}
			, success: function(data){
				if (data.code == 1){
					alert("가입을 환영합니다!!");
					location.href = "/user/sign_in_view";
				} else{
					alert(errorMessage);
				}
			}
		});
	});
});
</script>