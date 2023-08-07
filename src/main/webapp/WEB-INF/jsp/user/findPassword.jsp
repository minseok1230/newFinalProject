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
			<label for="name">이름</label>
			<input type="text" id="name" class="form-control mb-3" name="name" placeholder="이름을 입력하세요">
			<label for="loginEmail">이메일</label>
			<input type="text" id="loginEmail" class="form-control mb-3" name="loginEmail" placeholder="이메일을 입력하세요"> 
			<label for="phoneNumber">휴대전화번호</label>
			<input type="text" id="phoneNumber" class="form-control" name="phoneNumber" placeholder="휴대전화번호를 입력하세요">
		
			<!--로그인 버튼-->
			<button class="btn btn-secondary w-100 mt-4" id="sendBtn">임시 비밀번호 발송</button>
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		$('#sendBtn').on('click', function(){
			let loginEmail = $('#loginEmail').val().trim();
			let phoneNumber = $('#phoneNumber').val().trim();
			let name = $('#name').val().trim();
			
			if (!name){
				alert("이름을 입력하세요");
				return false;
			}
			
			if (!loginEmail){
				alert("이메일을 입력하세요");
				return false;
			}
			
			if (!phoneNumber){
				alert("휴대전화번호를 입력하세요");
				return false;
			}
			
			$.ajax({
				//request
				type: "post"
				, url: "/user/find_password"
				, data: {"name": name, "loginEmail": loginEmail, "phoneNumber": phoneNumber}
			
				// response
				, success: function(data){
					if (data.code == 1){
						alert("이메일로 임시 비밀번호를 발급해 드렸습니다. \n로그인 후 새로운 비밀번호로 바꿔주세요.");
						location.href =  "/user/sign_in_view";
					} else{
						alert(data.errorMessage);
					}
				}
				
				,error: function(request, status, error){
					alert("비밀번호 찾기에 실패하였습니다. 관리자 문의 바랍니다.")
				}
			});
		});
		
	});
</script>